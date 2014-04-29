package net.sourceforge.plantuml.ecore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import net.sourceforge.plantuml.text.AbstractDiagramTextProvider;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

public abstract class AbstractEcoreDiagramTextProvider extends AbstractDiagramTextProvider {

	protected AbstractEcoreDiagramTextProvider(Class<?> editorType) {
		super(editorType);
	}

	public boolean supportsSelection(ISelection selection) {
		return selection instanceof IStructuredSelection && ((IStructuredSelection) selection).getFirstElement() instanceof EPackage;
	}

	private int maxResourceCount = 1, maxPackageCount = 1;
	private Collection<String> excludePackages = Arrays.asList();

	private List<EClassifier> classifiers;
	
	protected String getDiagramText(ResourceSet resourceSet) {
		int resourceCount = 0, packageCount = 0;
		this.classifiers = new ArrayList<EClassifier>();
		outer: for (Resource resource : resourceSet.getResources()) {
			EPackage pack = null;
			for (EObject root : resource.getContents()) {
				if (root instanceof EPackage) {
					pack = (EPackage) root;
					if (! excludePackages.contains(pack.getNsURI())) {
						addClassifiers(pack);
						packageCount++;
						if (packageCount == maxPackageCount) {
							break outer;
						}
					}
				}
			}
			if (pack != null) {
				resourceCount++;
				if (resourceCount == maxResourceCount) {
					break;
				}
			}
		}
		String result = classifiers.size() > 0 ? getDiagramText(GEN_MEMBERS | GEN_EXTENDS | GEN_IMPLEMENTS | GEN_ASSOCIATIONS) : null;
		this.classifiers = null;
		return result;
	}

	protected String getDiagramText(EPackage pack) {
		this.classifiers = new ArrayList<EClassifier>();
		if (! excludePackages.contains(pack.getNsURI())) {
			addClassifiers(pack);
		}
		String result = classifiers.size() > 0 ? getDiagramText(GEN_MEMBERS | GEN_EXTENDS | GEN_IMPLEMENTS | GEN_ASSOCIATIONS) : null;
		this.classifiers = null;
		return result;
	}

	protected void addClassifiers(EPackage pack) {
		TreeIterator<EObject> it = pack.eAllContents();
		while (it.hasNext()) {
			EObject eObject = it.next();
			if (eObject instanceof EClassifier) {
				classifiers.add((EClassifier) eObject);
				it.prune();
			}
		}
	}
	
	public static String PLANTUML_ANNOTATION_KEY = "net.sourceforge.plantuml";
	
	protected static String getAnnotation(EObject element, String key, boolean checkContainers) {
		while (element instanceof EModelElement) {
			String value = EcoreUtil.getAnnotation((EModelElement) element, PLANTUML_ANNOTATION_KEY, key);
			if (value != null) {
				return value;
			}
			if (! checkContainers) {
				break;
			}
			element = element.eContainer();
		}
		return null;
	}
	protected static boolean checkAnnotation(EObject element, String key, boolean checkContainers) {
		return "true".equals(getAnnotation(element, key, checkContainers));
	}
	
	protected static boolean shouldSuppress(EModelElement element, String name, String role) {
		String key = "suppress", containerKey = key + name;
		if (role != null) {
			key = key + "As" + role;
			containerKey = containerKey + "As" + role;
		}
		return checkAnnotation(element, key, false) || checkAnnotation(element.eContainer(), containerKey, true);
	}
	protected static boolean shouldSuppress(ENamedElement element, String role) {
		return shouldSuppress(element, element.getName(), role);
	}
	
	protected String getDiagramText(int genFlags) {
		StringBuilder buffer = new StringBuilder();
		for (EClassifier classifier : classifiers) {
			if (! shouldSuppress(classifier, null)) {
				if (classifier instanceof EClass) {
					appendClass((EClass) classifier, genFlags, buffer);
				} else if (classifier instanceof EEnum) {
					appendEnum((EEnum) classifier, genFlags, buffer);
				} else if (classifier instanceof EDataType) {
					appendDataType((EDataType) classifier, genFlags, buffer);
				}
			}
		}
		if (includes(genFlags, GEN_EXTENDS) || includes(genFlags, GEN_IMPLEMENTS)) {
			for (EClassifier eClassifier : classifiers) {
				if (eClassifier instanceof EClass) {
					EClass subClass = (EClass) eClassifier;
					for (EClass superClass : subClass.getESuperTypes()) {
						if (! shouldSuppress(superClass, "superType")) {
							boolean isImplements = superClass.isInterface() && (! subClass.isInterface());
							if (includes(genFlags, isImplements ? GEN_IMPLEMENTS : GEN_EXTENDS)) {
								appendGeneralisation(subClass.getName(), superClass.getName(), isImplements, buffer);
							}
						}
					}
				}
			}
		}
		if (includes(genFlags, GEN_ASSOCIATIONS)) {
			for (EClassifier eClassifier : classifiers) {
				if (eClassifier instanceof EClass) {
					for (EStructuralFeature feature : ((EClass) eClassifier).getEStructuralFeatures()) {
						if (feature.getEType() != null && feature instanceof EReference) {
							if (! (shouldSuppress(feature, null) || shouldSuppress(feature.getEType(), "reference"))) {
								appendAssociation((EReference) feature, buffer);
							}
						}
					}
				}
			}
		}
		return buffer.toString();
	}

	protected void appendClass(EClass eClass, int genFlags, StringBuilder buffer) {
		String modifiers = eClass.isAbstract() && (! eClass.isInterface()) ? "abstract" : null;
		appendClassStart(modifiers, eClass.isInterface() ? "interface" : "class", eClass.getName(), buffer);
		if (includes(genFlags, GEN_MEMBERS)) {
			for (EStructuralFeature feature : eClass.getEStructuralFeatures()) {
				EClassifier eType = feature.getEType();
				if (eType != null && feature instanceof EAttribute) {
					if (! (shouldSuppress(feature, null) || shouldSuppress(eType, "attribute"))) {
						appendAttribute(null, null, getTypeName(eType), feature.getName(), buffer);
					}
				}
			}
			for (EOperation op : eClass.getEOperations()) {
				if (op.getEType() != null) {
					if (! (shouldSuppress(op, null) || shouldSuppress(op.getEType(), "operation"))) {
						Collection<String> parameters = new ArrayList<String>();
						for (EParameter parameter : op.getEParameters()) {
							String paramString = parameter.getName();
							if (parameter.getEType() != null) {
								paramString = parameter.getEType().getName() + " " + paramString;
							}
							parameters.add(paramString);
						}
						appendOperation(null, null, getTypeName(op.getEType()), op.getName(), parameters, buffer);
					}
				}
			}
		}
		appendClassEnd(buffer);
	}

	protected void appendDataType(EDataType dataType, int genFlags, StringBuilder buffer) {
		appendClassStart(null, "class", dataType.getName(), buffer);
		if (dataType.getInstanceClassName() != null) {
			appendAttribute(null, null, null, dataType.getInstanceClassName(), buffer);
		}
		appendClassEnd(buffer);
	}

	protected void appendEnum(EEnum eEnum, int genFlags, StringBuilder buffer) {
		appendClassStart(null, "enum", eEnum.getName(), buffer);
		for (EEnumLiteral literal : eEnum.getELiterals()) {
			appendAttribute(null, null, literal.getName(), literal.getLiteral(), buffer);
		}
		appendClassEnd(buffer);
	}

	protected String getTypeName(EClassifier type) {
		String typeName = null;
		if (type != null) {
			if (type instanceof EDataType) {
				typeName = type.getInstanceClassName();
			}
			if (typeName == null) {
				typeName = type.getName();
			}
		}
		return getSimpleName(typeName);
	}

	protected void appendAssociation(EReference ref, StringBuilder buffer) {
		EReference opposite = ref.getEOpposite();
		String source = ref.getEContainingClass().getName();
		String target = ref.getEType().getName();
		String direction = getAnnotation(ref, "direction", false);
		if (opposite != null) {
			// avoid duplicates
			if (classifiers.indexOf(ref.getEContainingClass()) < classifiers.indexOf(ref.getEType())) {
				appendAssociation(source, ref.isContainment(), opposite.getName(), getMultiplicity(opposite), direction, target, opposite.isContainment(), ref.getName(), getMultiplicity(ref), null, buffer);
			}
		} else {
			appendAssociation(source, ref.isContainment(), null, 0, direction, target, false, ref.getName(), getMultiplicity(ref), null, buffer);
		}
	}

	private boolean suppressSingleMultiplicity = true;
	
	protected int getMultiplicity(EStructuralFeature feature) {
		return feature.isMany() ? -1 : (suppressSingleMultiplicity ? 0 : 1);
	}
}
