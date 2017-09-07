package net.sourceforge.plantuml.ecore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import net.sourceforge.plantuml.text.AbstractClassDiagramTextProvider;

public abstract class AbstractEcoreClassDiagramTextProvider extends AbstractClassDiagramTextProvider {

	private static final String DEFAULT_EXTERNAL_STEREOTYPE = null;

	//

	protected AbstractEcoreClassDiagramTextProvider(Class<?> editorType) {
		super(editorType);
	}

	public boolean supportsSelection(ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			return isEcoreClassDiagramObject(((IStructuredSelection) selection).getFirstElement());
		}
		return false;
	}
	
	public static boolean isEcoreClassDiagramObject(Object object) {
		return object instanceof EModelElement;
	}

	private int maxResourceCount = 1, maxPackageCount = 1;
	private Collection<String> excludePackages = Arrays.asList();

	private Map<String, String> skinParams = null;
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
						init(pack);
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
		String result = classifiers.size() > 0 ? getDiagramText(GEN_MEMBERS | GEN_EXTENDS | GEN_IMPLEMENTS | GEN_ASSOCIATIONS | GEN_CLASS_HYPERLINKS) : null;
		this.classifiers = null;
		return result;
	}

	protected String getDiagramText(EPackage pack) {
		this.classifiers = new ArrayList<EClassifier>();
		if (! excludePackages.contains(pack.getNsURI())) {
			init(pack);
		}
		String result = classifiers.size() > 0 ? getDiagramText(GEN_MEMBERS | GEN_EXTENDS | GEN_IMPLEMENTS | GEN_ASSOCIATIONS | GEN_CLASS_HYPERLINKS) : null;
		this.classifiers = null;
		return result;
	}
	
	private EcoreDiagramHelper diagramHelper = new EcoreDiagramHelper();

	private void init(EPackage pack) {
		addClassifiers(pack);
		skinParams = diagramHelper.getSkinParams(pack, skinParams);
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
	
	protected String getDiagramText(int genFlags) {
		StringBuilder buffer = new StringBuilder();
		if (skinParams != null) {
			appendSkinParams(skinParams, buffer);
		}
		for (EClassifier classifier : classifiers) {
			if (! diagramHelper.shouldSuppress(classifier, null)) {
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
						if (! diagramHelper.shouldSuppress(superClass, "superType")) {
							boolean isImplements = superClass.isInterface() && (! subClass.isInterface());
							if (includes(genFlags, isImplements ? GEN_IMPLEMENTS : GEN_EXTENDS)) {
								appendGeneralisation(subClass, superClass, isImplements, buffer);
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
							if (! (diagramHelper.shouldSuppress(feature, null) || diagramHelper.shouldSuppress(feature.getEType(), "reference"))) {
								appendAssociation((EReference) feature, buffer);
							}
						}
					}
				}
			}
		}
		return buffer.toString();
	}

	protected void appendGeneralisation(EClass subClass, EClass superClass, boolean isImplements, StringBuilder buffer) {
		String otherName = getOtherName(subClass, superClass);
		appendGeneralisation(subClass.getName(), otherName, isImplements, buffer);
	}

	protected String getOtherName(EClassifier origin, EClassifier other) {
		String otherName = other.getName();
		if (! classifiers.contains(other)) {
			String externalStereotype = diagramHelper.getAnnotation(origin, "externalStereotype", true, DEFAULT_EXTERNAL_STEREOTYPE);
			if (externalStereotype != null && externalStereotype.length() > 0) {
				otherName = "<<" + externalStereotype + ">>";
			}
		}
		return otherName;
	}

	protected String getClassifierColor(EClassifier classifier) {
		Map<String, String> skinParams = diagramHelper.getSkinParams(classifier, null);
		return (skinParams != null ? skinParams.get("BackgroundColor") : null);
	}
	
	protected void appendClass(EClass eClass, int genFlags, StringBuilder buffer) {
		String modifiers = eClass.isAbstract() && (! eClass.isInterface()) ? "abstract" : null;
		String link = null;
		if (includes(genFlags, GEN_CLASS_HYPERLINKS)) {
			link = diagramHelper.getEObjectHyperlink(eClass);
		}
		appendClassStart(modifiers, eClass.isInterface() ? "interface" : "class", eClass.getName(), link, getClassifierColor(eClass), buffer);
		if (includes(genFlags, GEN_MEMBERS)) {
			for (EStructuralFeature feature : eClass.getEStructuralFeatures()) {
				EClassifier eType = feature.getEType();
				if (eType != null && feature instanceof EAttribute) {
					if (! (diagramHelper.shouldSuppress(feature, null) || diagramHelper.shouldSuppress(eType, "attribute"))) {
						appendAttribute(null, null, getTypeName(eType), feature.getName(), buffer);
					}
				}
			}
			for (EOperation op : eClass.getEOperations()) {
				if (op.getEType() != null) {
					if (! (diagramHelper.shouldSuppress(op, null) || diagramHelper.shouldSuppress(op.getEType(), "operation"))) {
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
		appendClassStart(null, "class", dataType.getName(), getClassifierColor(dataType), buffer);
		if (dataType.getInstanceClassName() != null) {
			appendAttribute(null, null, null, dataType.getInstanceClassName(), buffer);
		}
		appendClassEnd(buffer);
	}

	protected void appendEnum(EEnum eEnum, int genFlags, StringBuilder buffer) {
		appendClassStart(null, "enum", eEnum.getName(), getClassifierColor(eEnum), buffer);
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
		EClass sourceClass = ref.getEContainingClass();
		String source = sourceClass.getName();
		EClassifier targetType = ref.getEType();
		String target = getOtherName(sourceClass, targetType);
		String direction = diagramHelper.getAnnotation(ref, "direction", false, null);
		if (opposite != null) {
			// avoid duplicates
			int otherIndex = classifiers.indexOf(targetType);
			if (classifiers.indexOf(sourceClass) < otherIndex || otherIndex < 0) {
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
