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
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

import net.sourceforge.plantuml.text.AbstractClassDiagramTextProvider;

public abstract class AbstractEcoreClassDiagramTextProvider extends AbstractClassDiagramTextProvider {

	private static final String DEFAULT_EXTERNAL_STEREOTYPE = null;

	//

	protected final EcoreDiagramHelper diagramHelper = new EcoreDiagramHelper();

	protected AbstractEcoreClassDiagramTextProvider(final Class<?> editorType) {
		super(editorType);
	}
	protected AbstractEcoreClassDiagramTextProvider() {
		super(IEditingDomainProvider.class);
	}

	@Override
	public boolean supportsSelection(final ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			final Object firstElement = ((IStructuredSelection) selection).getFirstElement();
			if (firstElement instanceof EObject) {
				return supportsEObject((EObject) firstElement);
			}
		}
		return false;
	}

	protected boolean supportsEObject(final EObject selection) {
		return isEcoreClassDiagramObject(selection);
	}

	protected EPackage getEPackage(final EObject selection) {
		return diagramHelper.getAncestor(selection, EPackage.class);
	}

	public static boolean isEcoreClassDiagramObject(final Object object) {
		return object instanceof EModelElement;
	}

	@Override
	protected String getDiagramText(final IEditorPart editorPart, final IEditorInput editorInput, final ISelection selection, final Map<String, Object> markerAttributes) {
		if (selection instanceof IStructuredSelection) {
			final Object sel = ((IStructuredSelection) selection).getFirstElement();
			if (sel instanceof EObject && supportsEObject((EObject) sel)) {
				final EPackage ePack = getEPackage((EObject) sel);
				if (ePack != null) {
					return getDiagramText(getEPackage(ePack));
				}
			}
			return null;
		}
		return getDiagramText(((IEditingDomainProvider) editorPart).getEditingDomain().getResourceSet());
	}

	private final int maxResourceCount = 1, maxPackageCount = 1;
	private final Collection<String> excludePackages = Arrays.asList();

	private Map<String, String> skinParams = null;
	private List<EClassifier> classifiers;

	protected String getDiagramText(final ResourceSet resourceSet) {
		int resourceCount = 0, packageCount = 0;
		this.classifiers = new ArrayList<EClassifier>();
		outer: for (final Resource resource : resourceSet.getResources()) {
			EPackage pack = null;
			for (final EObject root : resource.getContents()) {
				pack = getEPackage(root);
				if ((pack != null) && (! excludePackages.contains(pack.getNsURI()))) {
					init(pack);
					packageCount++;
					if (packageCount == maxPackageCount) {
						break outer;
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
		final String result = classifiers.size() > 0 ? getDiagramText(GEN_MEMBERS | GEN_EXTENDS | GEN_IMPLEMENTS | GEN_ASSOCIATIONS | GEN_CLASS_HYPERLINKS) : null;
		this.classifiers = null;
		return result;
	}

	protected String getDiagramText(final EPackage pack) {
		this.classifiers = new ArrayList<EClassifier>();
		if (! excludePackages.contains(pack.getNsURI())) {
			init(pack);
		}
		final String result = classifiers.size() > 0 ? getDiagramText(GEN_MEMBERS | GEN_EXTENDS | GEN_IMPLEMENTS | GEN_ASSOCIATIONS | GEN_CLASS_HYPERLINKS) : null;
		this.classifiers = null;
		return result;
	}

	private void init(final EPackage pack) {
		addClassifiers(pack);
		skinParams = diagramHelper.getSkinParams(pack, skinParams);
	}

	protected void addClassifiers(final EPackage pack) {
		final TreeIterator<EObject> it = pack.eAllContents();
		while (it.hasNext()) {
			final EObject eObject = it.next();
			if (eObject instanceof EClassifier) {
				classifiers.add((EClassifier) eObject);
				it.prune();
			}
		}
	}

	protected String getDiagramText(final int genFlags) {
		final StringBuilder buffer = new StringBuilder();
		if (skinParams != null) {
			appendSkinParams(skinParams, buffer);
		}
		for (final EClassifier classifier : classifiers) {
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
			for (final EClassifier eClassifier : classifiers) {
				if (eClassifier instanceof EClass) {
					final EClass subClass = (EClass) eClassifier;
					for (final EClass superClass : subClass.getESuperTypes()) {
						if (! diagramHelper.shouldSuppress(superClass, "superType")) {
							final boolean isImplements = superClass.isInterface() && (! subClass.isInterface());
							if (includes(genFlags, isImplements ? GEN_IMPLEMENTS : GEN_EXTENDS)) {
								appendGeneralisation(subClass, superClass, isImplements, buffer);
							}
						}
					}
				}
			}
		}
		if (includes(genFlags, GEN_ASSOCIATIONS)) {
			for (final EClassifier eClassifier : classifiers) {
				if (eClassifier instanceof EClass) {
					for (final EStructuralFeature feature : ((EClass) eClassifier).getEStructuralFeatures()) {
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

	protected void appendGeneralisation(final EClass subClass, final EClass superClass, final boolean isImplements, final StringBuilder buffer) {
		final String otherName = getOtherName(subClass, superClass);
		appendGeneralisation(subClass.getName(), otherName, isImplements, buffer);
	}

	protected String getOtherName(final EClassifier origin, final EClassifier other) {
		String otherName = other.getName();
		if (! classifiers.contains(other)) {
			final String externalStereotype = diagramHelper.getAnnotation(origin, "externalStereotype", true, DEFAULT_EXTERNAL_STEREOTYPE);
			if (externalStereotype != null && externalStereotype.length() > 0) {
				otherName = "<<" + externalStereotype + ">>";
			}
		}
		return otherName;
	}

	protected String getClassifierColor(final EClassifier classifier) {
		final Map<String, String> skinParams = diagramHelper.getSkinParams(classifier, null);
		return (skinParams != null ? skinParams.get("BackgroundColor") : null);
	}

	protected void appendClass(final EClass eClass, final int genFlags, final StringBuilder buffer) {
		final String modifiers = eClass.isAbstract() && (! eClass.isInterface()) ? "abstract" : null;
		String link = null;
		if (includes(genFlags, GEN_CLASS_HYPERLINKS)) {
			link = diagramHelper.getEObjectHyperlink(eClass);
		}
		appendClassStart(modifiers, eClass.isInterface() ? "interface" : "class", eClass.getName(), link, getClassifierColor(eClass), buffer);
		if (includes(genFlags, GEN_MEMBERS)) {
			for (final EStructuralFeature feature : eClass.getEStructuralFeatures()) {
				final EClassifier eType = feature.getEType();
				if (feature instanceof EAttribute) {
					if (! (diagramHelper.shouldSuppress(feature, null) || (eType != null && diagramHelper.shouldSuppress(eType, "attribute")))) {
						appendAttribute(null, null, getTypedName(feature, "?"), feature.getName(), buffer);
					}
				}
			}
			for (final EOperation op : eClass.getEOperations()) {
				final EClassifier eType = op.getEType();
				if (! (diagramHelper.shouldSuppress(op, null) || (eType != null && diagramHelper.shouldSuppress(eType, "operation")))) {
					final Collection<String> parameters = new ArrayList<String>();
					for (final EParameter parameter : op.getEParameters()) {
						String paramString = parameter.getName();
						if (parameter.getEType() != null) {
							paramString = parameter.getEType().getName() + " " + paramString;
						}
						parameters.add(paramString);
					}
					appendOperation(null, null, getTypedName(op, "void"), op.getName(), parameters, buffer);
				}
			}
		}
		appendClassEnd(buffer);
	}

	protected void appendDataType(final EDataType dataType, final int genFlags, final StringBuilder buffer) {
		appendClassStart(null, "class", dataType.getName(), getClassifierColor(dataType), buffer);
		if (dataType.getInstanceClassName() != null) {
			appendAttribute(null, null, null, dataType.getInstanceClassName(), buffer);
		}
		appendClassEnd(buffer);
	}

	protected void appendEnum(final EEnum eEnum, final int genFlags, final StringBuilder buffer) {
		appendClassStart(null, "enum", eEnum.getName(), getClassifierColor(eEnum), buffer);
		for (final EEnumLiteral literal : eEnum.getELiterals()) {
			appendAttribute(null, null, literal.getName(), literal.getLiteral(), buffer);
		}
		appendClassEnd(buffer);
	}

	protected String getTypedName(final ETypedElement typed, final String def) {
		final EClassifier eType = typed.getEType();
		String typeName = getTypeName(eType, def);
		if (typed.isMany()) {
			typeName += "[]";
		}
		return getSimpleName(typeName);
	}

	protected String getTypeName(final EClassifier type, final String def) {
		String typeName = null;
		if (type != null) {
			if (type instanceof EDataType) {
				typeName = type.getInstanceClassName();
			}
			if (typeName == null) {
				typeName = type.getName();
			}
		} else {
			typeName = def;
		}
		return getSimpleName(typeName);
	}

	protected void appendAssociation(final EReference ref, final StringBuilder buffer) {
		final EReference opposite = ref.getEOpposite();
		final EClass sourceClass = ref.getEContainingClass();
		final String source = sourceClass.getName();
		final EClassifier targetType = ref.getEType();
		final String target = getOtherName(sourceClass, targetType);
		final String direction = diagramHelper.getAnnotation(ref, "direction", false, null);
		if (opposite != null) {
			// avoid duplicates
			final int otherIndex = classifiers.indexOf(targetType);
			if (classifiers.indexOf(sourceClass) < otherIndex || otherIndex < 0) {
				appendAssociation(source, ref.isContainment(), opposite.getName(), getMultiplicity(opposite), direction, target, opposite.isContainment(), ref.getName(), getMultiplicity(ref), null, buffer);
			}
		} else {
			appendAssociation(source, ref.isContainment(), null, 0, direction, target, false, ref.getName(), getMultiplicity(ref), null, buffer);
		}
	}

	private final boolean suppressSingleMultiplicity = true;

	protected int getMultiplicity(final EStructuralFeature feature) {
		return feature.isMany() ? -1 : (suppressSingleMultiplicity ? 0 : 1);
	}
}
