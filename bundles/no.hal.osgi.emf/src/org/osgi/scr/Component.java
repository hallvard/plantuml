/**
 */
package org.osgi.scr;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.osgi.scr.Component#getGroup <em>Group</em>}</li>
 *   <li>{@link org.osgi.scr.Component#getProperty <em>Property</em>}</li>
 *   <li>{@link org.osgi.scr.Component#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.osgi.scr.Component#getService <em>Service</em>}</li>
 *   <li>{@link org.osgi.scr.Component#getReference <em>Reference</em>}</li>
 *   <li>{@link org.osgi.scr.Component#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link org.osgi.scr.Component#getAny <em>Any</em>}</li>
 *   <li>{@link org.osgi.scr.Component#getActivate <em>Activate</em>}</li>
 *   <li>{@link org.osgi.scr.Component#getConfigurationPid <em>Configuration Pid</em>}</li>
 *   <li>{@link org.osgi.scr.Component#getConfigurationPolicy <em>Configuration Policy</em>}</li>
 *   <li>{@link org.osgi.scr.Component#getDeactivate <em>Deactivate</em>}</li>
 *   <li>{@link org.osgi.scr.Component#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link org.osgi.scr.Component#getFactory <em>Factory</em>}</li>
 *   <li>{@link org.osgi.scr.Component#isImmediate <em>Immediate</em>}</li>
 *   <li>{@link org.osgi.scr.Component#getModified <em>Modified</em>}</li>
 *   <li>{@link org.osgi.scr.Component#getName <em>Name</em>}</li>
 *   <li>{@link org.osgi.scr.Component#getAnyAttribute <em>Any Attribute</em>}</li>
 * </ul>
 *
 * @see org.osgi.scr.ScrPackage#getComponent()
 * @model extendedMetaData="name='Component' kind='elementOnly'"
 * @generated
 */
public interface Component extends EObject {
	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see org.osgi.scr.ScrPackage#getComponent_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:0'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Property</b></em>' containment reference list.
	 * The list contents are of type {@link org.osgi.scr.Property}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property</em>' containment reference list.
	 * @see org.osgi.scr.ScrPackage#getComponent_Property()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='property' group='#group:0'"
	 * @generated
	 */
	EList<Property> getProperty();

	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.osgi.scr.Properties}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' containment reference list.
	 * @see org.osgi.scr.ScrPackage#getComponent_Properties()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='properties' group='#group:0'"
	 * @generated
	 */
	EList<Properties> getProperties();

	/**
	 * Returns the value of the '<em><b>Service</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Service</em>' containment reference.
	 * @see #setService(Service)
	 * @see org.osgi.scr.ScrPackage#getComponent_Service()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='service'"
	 * @generated
	 */
	Service getService();

	/**
	 * Sets the value of the '{@link org.osgi.scr.Component#getService <em>Service</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service</em>' containment reference.
	 * @see #getService()
	 * @generated
	 */
	void setService(Service value);

	/**
	 * Returns the value of the '<em><b>Reference</b></em>' containment reference list.
	 * The list contents are of type {@link org.osgi.scr.Reference}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference</em>' containment reference list.
	 * @see org.osgi.scr.ScrPackage#getComponent_Reference()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='reference'"
	 * @generated
	 */
	EList<Reference> getReference();

	/**
	 * Returns the value of the '<em><b>Implementation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implementation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implementation</em>' containment reference.
	 * @see #setImplementation(Implementation)
	 * @see org.osgi.scr.ScrPackage#getComponent_Implementation()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='implementation'"
	 * @generated
	 */
	Implementation getImplementation();

	/**
	 * Sets the value of the '{@link org.osgi.scr.Component#getImplementation <em>Implementation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implementation</em>' containment reference.
	 * @see #getImplementation()
	 * @generated
	 */
	void setImplementation(Implementation value);

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
	 * @see org.osgi.scr.ScrPackage#getComponent_Any()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='elementWildcard' wildcards='##any' name=':6' processing='lax'"
	 * @generated
	 */
	FeatureMap getAny();

	/**
	 * Returns the value of the '<em><b>Activate</b></em>' attribute.
	 * The default value is <code>"activate"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activate</em>' attribute.
	 * @see #isSetActivate()
	 * @see #unsetActivate()
	 * @see #setActivate(String)
	 * @see org.osgi.scr.ScrPackage#getComponent_Activate()
	 * @model default="activate" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Token"
	 *        extendedMetaData="kind='attribute' name='activate'"
	 * @generated
	 */
	String getActivate();

	/**
	 * Sets the value of the '{@link org.osgi.scr.Component#getActivate <em>Activate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activate</em>' attribute.
	 * @see #isSetActivate()
	 * @see #unsetActivate()
	 * @see #getActivate()
	 * @generated
	 */
	void setActivate(String value);

	/**
	 * Unsets the value of the '{@link org.osgi.scr.Component#getActivate <em>Activate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetActivate()
	 * @see #getActivate()
	 * @see #setActivate(String)
	 * @generated
	 */
	void unsetActivate();

	/**
	 * Returns whether the value of the '{@link org.osgi.scr.Component#getActivate <em>Activate</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Activate</em>' attribute is set.
	 * @see #unsetActivate()
	 * @see #getActivate()
	 * @see #setActivate(String)
	 * @generated
	 */
	boolean isSetActivate();

	/**
	 * Returns the value of the '<em><b>Configuration Pid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                     The default value of this attribute is the value of
	 *                     the name attribute of this element.
	 *                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Configuration Pid</em>' attribute.
	 * @see #setConfigurationPid(String)
	 * @see org.osgi.scr.ScrPackage#getComponent_ConfigurationPid()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Token"
	 *        extendedMetaData="kind='attribute' name='configuration-pid'"
	 * @generated
	 */
	String getConfigurationPid();

	/**
	 * Sets the value of the '{@link org.osgi.scr.Component#getConfigurationPid <em>Configuration Pid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Configuration Pid</em>' attribute.
	 * @see #getConfigurationPid()
	 * @generated
	 */
	void setConfigurationPid(String value);

	/**
	 * Returns the value of the '<em><b>Configuration Policy</b></em>' attribute.
	 * The default value is <code>"optional"</code>.
	 * The literals are from the enumeration {@link org.osgi.scr.ConfigurationPolicy}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Configuration Policy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Configuration Policy</em>' attribute.
	 * @see org.osgi.scr.ConfigurationPolicy
	 * @see #isSetConfigurationPolicy()
	 * @see #unsetConfigurationPolicy()
	 * @see #setConfigurationPolicy(ConfigurationPolicy)
	 * @see org.osgi.scr.ScrPackage#getComponent_ConfigurationPolicy()
	 * @model default="optional" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='configuration-policy'"
	 * @generated
	 */
	ConfigurationPolicy getConfigurationPolicy();

	/**
	 * Sets the value of the '{@link org.osgi.scr.Component#getConfigurationPolicy <em>Configuration Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Configuration Policy</em>' attribute.
	 * @see org.osgi.scr.ConfigurationPolicy
	 * @see #isSetConfigurationPolicy()
	 * @see #unsetConfigurationPolicy()
	 * @see #getConfigurationPolicy()
	 * @generated
	 */
	void setConfigurationPolicy(ConfigurationPolicy value);

	/**
	 * Unsets the value of the '{@link org.osgi.scr.Component#getConfigurationPolicy <em>Configuration Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetConfigurationPolicy()
	 * @see #getConfigurationPolicy()
	 * @see #setConfigurationPolicy(ConfigurationPolicy)
	 * @generated
	 */
	void unsetConfigurationPolicy();

	/**
	 * Returns whether the value of the '{@link org.osgi.scr.Component#getConfigurationPolicy <em>Configuration Policy</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Configuration Policy</em>' attribute is set.
	 * @see #unsetConfigurationPolicy()
	 * @see #getConfigurationPolicy()
	 * @see #setConfigurationPolicy(ConfigurationPolicy)
	 * @generated
	 */
	boolean isSetConfigurationPolicy();

	/**
	 * Returns the value of the '<em><b>Deactivate</b></em>' attribute.
	 * The default value is <code>"deactivate"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deactivate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deactivate</em>' attribute.
	 * @see #isSetDeactivate()
	 * @see #unsetDeactivate()
	 * @see #setDeactivate(String)
	 * @see org.osgi.scr.ScrPackage#getComponent_Deactivate()
	 * @model default="deactivate" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Token"
	 *        extendedMetaData="kind='attribute' name='deactivate'"
	 * @generated
	 */
	String getDeactivate();

	/**
	 * Sets the value of the '{@link org.osgi.scr.Component#getDeactivate <em>Deactivate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deactivate</em>' attribute.
	 * @see #isSetDeactivate()
	 * @see #unsetDeactivate()
	 * @see #getDeactivate()
	 * @generated
	 */
	void setDeactivate(String value);

	/**
	 * Unsets the value of the '{@link org.osgi.scr.Component#getDeactivate <em>Deactivate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDeactivate()
	 * @see #getDeactivate()
	 * @see #setDeactivate(String)
	 * @generated
	 */
	void unsetDeactivate();

	/**
	 * Returns whether the value of the '{@link org.osgi.scr.Component#getDeactivate <em>Deactivate</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Deactivate</em>' attribute is set.
	 * @see #unsetDeactivate()
	 * @see #getDeactivate()
	 * @see #setDeactivate(String)
	 * @generated
	 */
	boolean isSetDeactivate();

	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #isSetEnabled()
	 * @see #unsetEnabled()
	 * @see #setEnabled(boolean)
	 * @see org.osgi.scr.ScrPackage#getComponent_Enabled()
	 * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='enabled'"
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link org.osgi.scr.Component#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isSetEnabled()
	 * @see #unsetEnabled()
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

	/**
	 * Unsets the value of the '{@link org.osgi.scr.Component#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEnabled()
	 * @see #isEnabled()
	 * @see #setEnabled(boolean)
	 * @generated
	 */
	void unsetEnabled();

	/**
	 * Returns whether the value of the '{@link org.osgi.scr.Component#isEnabled <em>Enabled</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Enabled</em>' attribute is set.
	 * @see #unsetEnabled()
	 * @see #isEnabled()
	 * @see #setEnabled(boolean)
	 * @generated
	 */
	boolean isSetEnabled();

	/**
	 * Returns the value of the '<em><b>Factory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Factory</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Factory</em>' attribute.
	 * @see #setFactory(String)
	 * @see org.osgi.scr.ScrPackage#getComponent_Factory()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='factory'"
	 * @generated
	 */
	String getFactory();

	/**
	 * Sets the value of the '{@link org.osgi.scr.Component#getFactory <em>Factory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Factory</em>' attribute.
	 * @see #getFactory()
	 * @generated
	 */
	void setFactory(String value);

	/**
	 * Returns the value of the '<em><b>Immediate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Immediate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Immediate</em>' attribute.
	 * @see #isSetImmediate()
	 * @see #unsetImmediate()
	 * @see #setImmediate(boolean)
	 * @see org.osgi.scr.ScrPackage#getComponent_Immediate()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='immediate'"
	 * @generated
	 */
	boolean isImmediate();

	/**
	 * Sets the value of the '{@link org.osgi.scr.Component#isImmediate <em>Immediate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Immediate</em>' attribute.
	 * @see #isSetImmediate()
	 * @see #unsetImmediate()
	 * @see #isImmediate()
	 * @generated
	 */
	void setImmediate(boolean value);

	/**
	 * Unsets the value of the '{@link org.osgi.scr.Component#isImmediate <em>Immediate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetImmediate()
	 * @see #isImmediate()
	 * @see #setImmediate(boolean)
	 * @generated
	 */
	void unsetImmediate();

	/**
	 * Returns whether the value of the '{@link org.osgi.scr.Component#isImmediate <em>Immediate</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Immediate</em>' attribute is set.
	 * @see #unsetImmediate()
	 * @see #isImmediate()
	 * @see #setImmediate(boolean)
	 * @generated
	 */
	boolean isSetImmediate();

	/**
	 * Returns the value of the '<em><b>Modified</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modified</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modified</em>' attribute.
	 * @see #setModified(String)
	 * @see org.osgi.scr.ScrPackage#getComponent_Modified()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Token"
	 *        extendedMetaData="kind='attribute' name='modified'"
	 * @generated
	 */
	String getModified();

	/**
	 * Sets the value of the '{@link org.osgi.scr.Component#getModified <em>Modified</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modified</em>' attribute.
	 * @see #getModified()
	 * @generated
	 */
	void setModified(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                     The default value of this attribute is the value of
	 *                     the class attribute of the nested implementation
	 *                     element. If multiple component elements use the same 
	 *                     value for the class attribute of their nested 
	 *                     implementation element, then using the default value 
	 *                     for this attribute will result in duplicate names. 
	 *                     In this case, this attribute must be specified with 
	 *                     a unique value.
	 *                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.osgi.scr.ScrPackage#getComponent_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Token"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.osgi.scr.Component#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

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
	 * @see org.osgi.scr.ScrPackage#getComponent_AnyAttribute()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='attributeWildcard' wildcards='##any' name=':16' processing='lax'"
	 * @generated
	 */
	FeatureMap getAnyAttribute();

} // Component
