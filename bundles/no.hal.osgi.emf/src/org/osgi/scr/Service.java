/**
 */
package org.osgi.scr;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.osgi.scr.Service#getProvide <em>Provide</em>}</li>
 *   <li>{@link org.osgi.scr.Service#getAny <em>Any</em>}</li>
 *   <li>{@link org.osgi.scr.Service#isServicefactory <em>Servicefactory</em>}</li>
 *   <li>{@link org.osgi.scr.Service#getAnyAttribute <em>Any Attribute</em>}</li>
 * </ul>
 *
 * @see org.osgi.scr.ScrPackage#getService()
 * @model extendedMetaData="name='service' kind='elementOnly'"
 * @generated
 */
public interface Service extends EObject {
	/**
	 * Returns the value of the '<em><b>Provide</b></em>' containment reference list.
	 * The list contents are of type {@link org.osgi.scr.Provide}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provide</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Provide</em>' containment reference list.
	 * @see org.osgi.scr.ScrPackage#getService_Provide()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='provide'"
	 * @generated
	 */
	EList<Provide> getProvide();

	/**
	 * Returns the value of the '<em><b>Any</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Any</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Any</em>' attribute list.
	 * @see org.osgi.scr.ScrPackage#getService_Any()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='elementWildcard' wildcards='##other' name=':1' processing='lax'"
	 * @generated
	 */
	FeatureMap getAny();

	/**
	 * Returns the value of the '<em><b>Servicefactory</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Servicefactory</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Servicefactory</em>' attribute.
	 * @see #isSetServicefactory()
	 * @see #unsetServicefactory()
	 * @see #setServicefactory(boolean)
	 * @see org.osgi.scr.ScrPackage#getService_Servicefactory()
	 * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='servicefactory'"
	 * @generated
	 */
	boolean isServicefactory();

	/**
	 * Sets the value of the '{@link org.osgi.scr.Service#isServicefactory <em>Servicefactory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Servicefactory</em>' attribute.
	 * @see #isSetServicefactory()
	 * @see #unsetServicefactory()
	 * @see #isServicefactory()
	 * @generated
	 */
	void setServicefactory(boolean value);

	/**
	 * Unsets the value of the '{@link org.osgi.scr.Service#isServicefactory <em>Servicefactory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetServicefactory()
	 * @see #isServicefactory()
	 * @see #setServicefactory(boolean)
	 * @generated
	 */
	void unsetServicefactory();

	/**
	 * Returns whether the value of the '{@link org.osgi.scr.Service#isServicefactory <em>Servicefactory</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Servicefactory</em>' attribute is set.
	 * @see #unsetServicefactory()
	 * @see #isServicefactory()
	 * @see #setServicefactory(boolean)
	 * @generated
	 */
	boolean isSetServicefactory();

	/**
	 * Returns the value of the '<em><b>Any Attribute</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Any Attribute</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Any Attribute</em>' attribute list.
	 * @see org.osgi.scr.ScrPackage#getService_AnyAttribute()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='attributeWildcard' wildcards='##any' name=':3' processing='lax'"
	 * @generated
	 */
	FeatureMap getAnyAttribute();

} // Service
