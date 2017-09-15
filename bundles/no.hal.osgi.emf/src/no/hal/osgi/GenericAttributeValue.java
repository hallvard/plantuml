/**
 */
package no.hal.osgi;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generic Attribute Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link no.hal.osgi.GenericAttributeValue#getValue <em>Value</em>}</li>
 *   <li>{@link no.hal.osgi.GenericAttributeValue#getExtraAttributes <em>Extra Attributes</em>}</li>
 * </ul>
 *
 * @see no.hal.osgi.OsgiPackage#getGenericAttributeValue()
 * @model
 * @generated
 */
public interface GenericAttributeValue extends EObject {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see no.hal.osgi.OsgiPackage#getGenericAttributeValue_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link no.hal.osgi.GenericAttributeValue#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Extra Attributes</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extra Attributes</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extra Attributes</em>' attribute list.
	 * @see no.hal.osgi.OsgiPackage#getGenericAttributeValue_ExtraAttributes()
	 * @model
	 * @generated
	 */
	EList<String> getExtraAttributes();

} // GenericAttributeValue
