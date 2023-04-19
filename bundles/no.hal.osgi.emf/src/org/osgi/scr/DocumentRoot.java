/**
 */
package org.osgi.scr;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.osgi.scr.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.osgi.scr.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.osgi.scr.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.osgi.scr.DocumentRoot#getComponent <em>Component</em>}</li>
 *   <li>{@link org.osgi.scr.DocumentRoot#isMustUnderstand <em>Must Understand</em>}</li>
 * </ul>
 *
 * @see org.osgi.scr.ScrPackage#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot extends EObject {
	/**
	 * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mixed</em>' attribute list.
	 * @see org.osgi.scr.ScrPackage#getDocumentRoot_Mixed()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='elementWildcard' name=':mixed'"
	 * @generated
	 */
	FeatureMap getMixed();

	/**
	 * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XMLNS Prefix Map</em>' map.
	 * @see org.osgi.scr.ScrPackage#getDocumentRoot_XMLNSPrefixMap()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;" transient="true"
	 *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
	 * @generated
	 */
	EMap<String, String> getXMLNSPrefixMap();

	/**
	 * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XSI Schema Location</em>' map.
	 * @see org.osgi.scr.ScrPackage#getDocumentRoot_XSISchemaLocation()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;" transient="true"
	 *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
	 * @generated
	 */
	EMap<String, String> getXSISchemaLocation();

	/**
	 * Returns the value of the '<em><b>Component</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component</em>' containment reference.
	 * @see #setComponent(Component)
	 * @see org.osgi.scr.ScrPackage#getDocumentRoot_Component()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='component' namespace='##targetNamespace'"
	 * @generated
	 */
	Component getComponent();

	/**
	 * Sets the value of the '{@link org.osgi.scr.DocumentRoot#getComponent <em>Component</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component</em>' containment reference.
	 * @see #getComponent()
	 * @generated
	 */
	void setComponent(Component value);

	/**
	 * Returns the value of the '<em><b>Must Understand</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                 This attribute should be used by extensions to documents
	 *                 to require that the document consumer understand the
	 *                 extension. This attribute must be qualified when used.
	 *             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Must Understand</em>' attribute.
	 * @see #isSetMustUnderstand()
	 * @see #unsetMustUnderstand()
	 * @see #setMustUnderstand(boolean)
	 * @see org.osgi.scr.ScrPackage#getDocumentRoot_MustUnderstand()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='must-understand' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isMustUnderstand();

	/**
	 * Sets the value of the '{@link org.osgi.scr.DocumentRoot#isMustUnderstand <em>Must Understand</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Must Understand</em>' attribute.
	 * @see #isSetMustUnderstand()
	 * @see #unsetMustUnderstand()
	 * @see #isMustUnderstand()
	 * @generated
	 */
	void setMustUnderstand(boolean value);

	/**
	 * Unsets the value of the '{@link org.osgi.scr.DocumentRoot#isMustUnderstand <em>Must Understand</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMustUnderstand()
	 * @see #isMustUnderstand()
	 * @see #setMustUnderstand(boolean)
	 * @generated
	 */
	void unsetMustUnderstand();

	/**
	 * Returns whether the value of the '{@link org.osgi.scr.DocumentRoot#isMustUnderstand <em>Must Understand</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Must Understand</em>' attribute is set.
	 * @see #unsetMustUnderstand()
	 * @see #isMustUnderstand()
	 * @see #setMustUnderstand(boolean)
	 * @generated
	 */
	boolean isSetMustUnderstand();

} // DocumentRoot
