/**
 */
package org.osgi.scr;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.osgi.scr.Reference#getAny <em>Any</em>}</li>
 *   <li>{@link org.osgi.scr.Reference#getBind <em>Bind</em>}</li>
 *   <li>{@link org.osgi.scr.Reference#getCardinality <em>Cardinality</em>}</li>
 *   <li>{@link org.osgi.scr.Reference#getInterface <em>Interface</em>}</li>
 *   <li>{@link org.osgi.scr.Reference#getName <em>Name</em>}</li>
 *   <li>{@link org.osgi.scr.Reference#getPolicy <em>Policy</em>}</li>
 *   <li>{@link org.osgi.scr.Reference#getPolicyOption <em>Policy Option</em>}</li>
 *   <li>{@link org.osgi.scr.Reference#getTarget <em>Target</em>}</li>
 *   <li>{@link org.osgi.scr.Reference#getUnbind <em>Unbind</em>}</li>
 *   <li>{@link org.osgi.scr.Reference#getUpdated <em>Updated</em>}</li>
 *   <li>{@link org.osgi.scr.Reference#getAnyAttribute <em>Any Attribute</em>}</li>
 * </ul>
 *
 * @see org.osgi.scr.ScrPackage#getReference()
 * @model extendedMetaData="name='reference' kind='elementOnly'"
 * @generated
 */
public interface Reference extends EObject {
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
	 * @see org.osgi.scr.ScrPackage#getReference_Any()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='elementWildcard' wildcards='##any' name=':0' processing='lax'"
	 * @generated
	 */
	FeatureMap getAny();

	/**
	 * Returns the value of the '<em><b>Bind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bind</em>' attribute.
	 * @see #setBind(String)
	 * @see org.osgi.scr.ScrPackage#getReference_Bind()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Token"
	 *        extendedMetaData="kind='attribute' name='bind'"
	 * @generated
	 */
	String getBind();

	/**
	 * Sets the value of the '{@link org.osgi.scr.Reference#getBind <em>Bind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bind</em>' attribute.
	 * @see #getBind()
	 * @generated
	 */
	void setBind(String value);

	/**
	 * Returns the value of the '<em><b>Cardinality</b></em>' attribute.
	 * The default value is <code>"1..1"</code>.
	 * The literals are from the enumeration {@link org.osgi.scr.Cardinality}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cardinality</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cardinality</em>' attribute.
	 * @see org.osgi.scr.Cardinality
	 * @see #isSetCardinality()
	 * @see #unsetCardinality()
	 * @see #setCardinality(Cardinality)
	 * @see org.osgi.scr.ScrPackage#getReference_Cardinality()
	 * @model default="1..1" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='cardinality'"
	 * @generated
	 */
	Cardinality getCardinality();

	/**
	 * Sets the value of the '{@link org.osgi.scr.Reference#getCardinality <em>Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cardinality</em>' attribute.
	 * @see org.osgi.scr.Cardinality
	 * @see #isSetCardinality()
	 * @see #unsetCardinality()
	 * @see #getCardinality()
	 * @generated
	 */
	void setCardinality(Cardinality value);

	/**
	 * Unsets the value of the '{@link org.osgi.scr.Reference#getCardinality <em>Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCardinality()
	 * @see #getCardinality()
	 * @see #setCardinality(Cardinality)
	 * @generated
	 */
	void unsetCardinality();

	/**
	 * Returns whether the value of the '{@link org.osgi.scr.Reference#getCardinality <em>Cardinality</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Cardinality</em>' attribute is set.
	 * @see #unsetCardinality()
	 * @see #getCardinality()
	 * @see #setCardinality(Cardinality)
	 * @generated
	 */
	boolean isSetCardinality();

	/**
	 * Returns the value of the '<em><b>Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interface</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interface</em>' attribute.
	 * @see #setInterface(String)
	 * @see org.osgi.scr.ScrPackage#getReference_Interface()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Token" required="true"
	 *        extendedMetaData="kind='attribute' name='interface'"
	 * @generated
	 */
	String getInterface();

	/**
	 * Sets the value of the '{@link org.osgi.scr.Reference#getInterface <em>Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interface</em>' attribute.
	 * @see #getInterface()
	 * @generated
	 */
	void setInterface(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                     The default value of this attribute is the value of
	 *                     the interface attribute of this element. If multiple 
	 *                     instances of this element within a component element 
	 *                     use the same value for the interface attribute, then 
	 *                     using the default value for this attribute will result 
	 *                     in duplicate names. In this case, this attribute 
	 *                     must be specified with a unique value.
	 *                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.osgi.scr.ScrPackage#getReference_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Token"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.osgi.scr.Reference#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Policy</b></em>' attribute.
	 * The default value is <code>"static"</code>.
	 * The literals are from the enumeration {@link org.osgi.scr.Policy}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Policy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Policy</em>' attribute.
	 * @see org.osgi.scr.Policy
	 * @see #isSetPolicy()
	 * @see #unsetPolicy()
	 * @see #setPolicy(Policy)
	 * @see org.osgi.scr.ScrPackage#getReference_Policy()
	 * @model default="static" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='policy'"
	 * @generated
	 */
	Policy getPolicy();

	/**
	 * Sets the value of the '{@link org.osgi.scr.Reference#getPolicy <em>Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Policy</em>' attribute.
	 * @see org.osgi.scr.Policy
	 * @see #isSetPolicy()
	 * @see #unsetPolicy()
	 * @see #getPolicy()
	 * @generated
	 */
	void setPolicy(Policy value);

	/**
	 * Unsets the value of the '{@link org.osgi.scr.Reference#getPolicy <em>Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPolicy()
	 * @see #getPolicy()
	 * @see #setPolicy(Policy)
	 * @generated
	 */
	void unsetPolicy();

	/**
	 * Returns whether the value of the '{@link org.osgi.scr.Reference#getPolicy <em>Policy</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Policy</em>' attribute is set.
	 * @see #unsetPolicy()
	 * @see #getPolicy()
	 * @see #setPolicy(Policy)
	 * @generated
	 */
	boolean isSetPolicy();

	/**
	 * Returns the value of the '<em><b>Policy Option</b></em>' attribute.
	 * The default value is <code>"reluctant"</code>.
	 * The literals are from the enumeration {@link org.osgi.scr.PolicyOption}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Policy Option</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Policy Option</em>' attribute.
	 * @see org.osgi.scr.PolicyOption
	 * @see #isSetPolicyOption()
	 * @see #unsetPolicyOption()
	 * @see #setPolicyOption(PolicyOption)
	 * @see org.osgi.scr.ScrPackage#getReference_PolicyOption()
	 * @model default="reluctant" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='policy-option'"
	 * @generated
	 */
	PolicyOption getPolicyOption();

	/**
	 * Sets the value of the '{@link org.osgi.scr.Reference#getPolicyOption <em>Policy Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Policy Option</em>' attribute.
	 * @see org.osgi.scr.PolicyOption
	 * @see #isSetPolicyOption()
	 * @see #unsetPolicyOption()
	 * @see #getPolicyOption()
	 * @generated
	 */
	void setPolicyOption(PolicyOption value);

	/**
	 * Unsets the value of the '{@link org.osgi.scr.Reference#getPolicyOption <em>Policy Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPolicyOption()
	 * @see #getPolicyOption()
	 * @see #setPolicyOption(PolicyOption)
	 * @generated
	 */
	void unsetPolicyOption();

	/**
	 * Returns whether the value of the '{@link org.osgi.scr.Reference#getPolicyOption <em>Policy Option</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Policy Option</em>' attribute is set.
	 * @see #unsetPolicyOption()
	 * @see #getPolicyOption()
	 * @see #setPolicyOption(PolicyOption)
	 * @generated
	 */
	boolean isSetPolicyOption();

	/**
	 * Returns the value of the '<em><b>Target</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' attribute.
	 * @see #setTarget(String)
	 * @see org.osgi.scr.ScrPackage#getReference_Target()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='target'"
	 * @generated
	 */
	String getTarget();

	/**
	 * Sets the value of the '{@link org.osgi.scr.Reference#getTarget <em>Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' attribute.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(String value);

	/**
	 * Returns the value of the '<em><b>Unbind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unbind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unbind</em>' attribute.
	 * @see #setUnbind(String)
	 * @see org.osgi.scr.ScrPackage#getReference_Unbind()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Token"
	 *        extendedMetaData="kind='attribute' name='unbind'"
	 * @generated
	 */
	String getUnbind();

	/**
	 * Sets the value of the '{@link org.osgi.scr.Reference#getUnbind <em>Unbind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unbind</em>' attribute.
	 * @see #getUnbind()
	 * @generated
	 */
	void setUnbind(String value);

	/**
	 * Returns the value of the '<em><b>Updated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Updated</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Updated</em>' attribute.
	 * @see #setUpdated(String)
	 * @see org.osgi.scr.ScrPackage#getReference_Updated()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Token"
	 *        extendedMetaData="kind='attribute' name='updated'"
	 * @generated
	 */
	String getUpdated();

	/**
	 * Sets the value of the '{@link org.osgi.scr.Reference#getUpdated <em>Updated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Updated</em>' attribute.
	 * @see #getUpdated()
	 * @generated
	 */
	void setUpdated(String value);

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
	 * @see org.osgi.scr.ScrPackage#getReference_AnyAttribute()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='attributeWildcard' wildcards='##any' name=':10' processing='lax'"
	 * @generated
	 */
	FeatureMap getAnyAttribute();

} // Reference
