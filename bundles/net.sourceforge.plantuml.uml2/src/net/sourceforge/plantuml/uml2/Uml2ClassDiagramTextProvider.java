package net.sourceforge.plantuml.uml2;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;

import net.sourceforge.plantuml.ecore.AbstractEcoreClassDiagramTextProvider;

public class Uml2ClassDiagramTextProvider extends AbstractEcoreClassDiagramTextProvider {

	@Override
	protected boolean supportsEObject(final EObject selection) {
		return selection.eClass().getEPackage() == UMLPackage.eINSTANCE;
	}

	@Override
	protected EPackage getEPackage(final EObject eObject) {
		final org.eclipse.uml2.uml.Package umlPack = diagramHelper.getAncestor(eObject, org.eclipse.uml2.uml.Package.class);
		uml2EcoreMapping.clear();
		return (umlPack != null ? getEPackage(umlPack) : null);
	}

	private final Map<NamedElement, ENamedElement> uml2EcoreMapping = new HashMap<NamedElement, ENamedElement>();

	protected ENamedElement getENamedElement(final NamedElement namedElement, final EClass eClass) {
		ENamedElement eNamedElement = uml2EcoreMapping.get(namedElement);
		if (eNamedElement == null) {
			eNamedElement = (ENamedElement) EcoreUtil.create(eClass);
			uml2EcoreMapping.put(namedElement, eNamedElement);
		}
		eNamedElement.setName(namedElement.getName());
		return eNamedElement;
	}

	protected EPackage getEPackage(final org.eclipse.uml2.uml.Package umlPack) {
		final EPackage ePack = (EPackage) getENamedElement(umlPack, EcorePackage.eINSTANCE.getEPackage());
		for (final NamedElement member : umlPack.getMembers()) {
			if (member instanceof org.eclipse.uml2.uml.Class) {
				final EClass eClass = getEClass((org.eclipse.uml2.uml.Class) member);
				if (eClass != null) {
					ePack.getEClassifiers().add(eClass);
				}
			}
		}
		return ePack;
	}

	private EClass getEClass(final Class umlClass) {
		final EClass eClass = (EClass) getENamedElement(umlClass, EcorePackage.eINSTANCE.getEClass());
		eClass.getEStructuralFeatures().clear();
		for (final NamedElement member : umlClass.getMembers()) {
			if (member instanceof Property) {
				final EAttribute attr = getEAttribute((Property) member);
				if (attr != null) {
					eClass.getEStructuralFeatures().add(attr);
				}
			}
		}
		return eClass;
	}

	private EAttribute getEAttribute(final Property prop) {
		final EAttribute attr = (EAttribute) getENamedElement(prop, EcorePackage.eINSTANCE.getEAttribute());
		final EDataType type = EcoreFactory.eINSTANCE.createEDataType();
		type.setName(prop.getType().getName());
		attr.setEType(type);
		return attr;
	}
}
