/**
 */
package no.hal.osgi;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generic Attributes Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link no.hal.osgi.GenericAttributesContainer#getGenericAttributes <em>Generic Attributes</em>}</li>
 * </ul>
 *
 * @see no.hal.osgi.OsgiPackage#getGenericAttributesContainer()
 * @model
 * @generated
 */
public interface GenericAttributesContainer extends EObject {
	/**
	 * Returns the value of the '<em><b>Generic Attributes</b></em>' reference list.
	 * The list contents are of type {@link no.hal.osgi.GenericAttribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generic Attributes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generic Attributes</em>' reference list.
	 * @see no.hal.osgi.OsgiPackage#getGenericAttributesContainer_GenericAttributes()
	 * @model
	 * @generated
	 */
	EList<GenericAttribute> getGenericAttributes();

} // GenericAttributesContainer
