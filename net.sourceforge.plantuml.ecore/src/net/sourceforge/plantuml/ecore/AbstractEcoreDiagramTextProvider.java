package net.sourceforge.plantuml.ecore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.plantuml.text.AbstractDiagramTextProvider;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAnnotation;
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

	public static String PLANTUML_ANNOTATION_KEY = "net.sourceforge.plantuml";
	public static String PLANTUML_SKINPARAMS_ANNOTATION_KEY = PLANTUML_ANNOTATION_KEY + "/skinparams";

	private static final String DEFAULT_EXTERNAL_STEREOTYPE = null;

	//

	protected AbstractEcoreDiagramTextProvider(Class<?> editorType) {
		super(editorType);
	}

	public boolean supportsSelection(ISelection selection) {
		return selection instanceof IStructuredSelection && ((IStructuredSelection) selection).getFirstElement() instanceof EPackage;
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
		String result = classifiers.size() > 0 ? getDiagramText(GEN_MEMBERS | GEN_EXTENDS | GEN_IMPLEMENTS | GEN_ASSOCIATIONS) : null;
		this.classifiers = null;
		return result;
	}

	protected String getDiagramText(EPackage pack) {
		this.classifiers = new ArrayList<EClassifier>();
		if (! excludePackages.contains(pack.getNsURI())) {
			init(pack);
		}
		String result = classifiers.size() > 0 ? getDiagramText(GEN_MEMBERS | GEN_EXTENDS | GEN_IMPLEMENTS | GEN_ASSOCIATIONS) : null;
		this.classifiers = null;
		return result;
	}

	private void init(EPackage pack) {
		addClassifiers(pack);
		skinParams = getSkinParams(pack, skinParams);
	}

	protected Map<String, String> getSkinParams(EModelElement element, Map<String, String> map) {
		for (EAnnotation annotation : element.getEAnnotations()) {
			if (annotation.getSource().startsWith(PLANTUML_SKINPARAMS_ANNOTATION_KEY)) {
				String key = annotation.getSource().substring(PLANTUML_SKINPARAMS_ANNOTATION_KEY.length());
				String qualifier = null;
				if (key.startsWith("/")) {
					qualifier = key.substring(1);
				}
				map = getSkinParams(qualifier, annotation, map);
			}
		}
		return map;
	}

	protected Map<String, String> getSkinParams(String qualifier, EAnnotation annotation, Map<String, String> map) {
		if (annotation != null) {
			EMap<String, String> eMap = annotation.getDetails();
			if (eMap.size() > 0) {
				if (map == null) {
					map = new HashMap<String, String>();
				}
				for (String key : eMap.keySet()) {
					map.put((qualifier != null ? qualifier : "") + key, eMap.get(key));
				}
			}
		}
		return map;
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
	
	protected static String getAnnotation(EObject element, String key, boolean checkContainers, String def) {
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
		return def;
	}
	protected static boolean checkAnnotation(EObject element, String key, boolean checkContainers) {
		return "true".equals(getAnnotation(element, key, checkContainers, null));
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
		if (skinParams != null) {
			appendSkinParams(skinParams, buffer);
		}
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

	protected void appendGeneralisation(EClass subClass, EClass superClass, boolean isImplements, StringBuilder buffer) {
		String otherName = getOtherName(subClass, superClass);
		appendGeneralisation(subClass.getName(), otherName, isImplements, buffer);
	}

	protected String getOtherName(EClassifier origin, EClassifier other) {
		String otherName = other.getName();
		if (! classifiers.contains(other)) {
			String externalStereotype = getAnnotation(origin, "externalStereotype", true, DEFAULT_EXTERNAL_STEREOTYPE);
			if (externalStereotype != null && externalStereotype.length() > 0) {
				otherName = "<<" + externalStereotype + ">>";
			}
		}
		return otherName;
	}

	protected String getClassifierColor(EClassifier classifier) {
		Map<String, String> skinParams = getSkinParams(classifier, null);
		return (skinParams != null ? skinParams.get("BackgroundColor") : null);
	}
	
	protected void appendClass(EClass eClass, int genFlags, StringBuilder buffer) {
		String modifiers = eClass.isAbstract() && (! eClass.isInterface()) ? "abstract" : null;
		appendClassStart(modifiers, eClass.isInterface() ? "interface" : "class", eClass.getName(), getClassifierColor(eClass), buffer);
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
		String direction = getAnnotation(ref, "direction", false, null);
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
