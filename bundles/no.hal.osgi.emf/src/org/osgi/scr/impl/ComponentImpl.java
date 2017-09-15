/**
 */
package org.osgi.scr.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.osgi.scr.Component;
import org.osgi.scr.ConfigurationPolicy;
import org.osgi.scr.Implementation;
import org.osgi.scr.Properties;
import org.osgi.scr.Property;
import org.osgi.scr.Reference;
import org.osgi.scr.ScrPackage;
import org.osgi.scr.Service;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.osgi.scr.impl.ComponentImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ComponentImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ComponentImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ComponentImpl#getService <em>Service</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ComponentImpl#getReference <em>Reference</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ComponentImpl#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ComponentImpl#getAny <em>Any</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ComponentImpl#getActivate <em>Activate</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ComponentImpl#getConfigurationPid <em>Configuration Pid</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ComponentImpl#getConfigurationPolicy <em>Configuration Policy</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ComponentImpl#getDeactivate <em>Deactivate</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ComponentImpl#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ComponentImpl#getFactory <em>Factory</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ComponentImpl#isImmediate <em>Immediate</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ComponentImpl#getModified <em>Modified</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ComponentImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ComponentImpl#getAnyAttribute <em>Any Attribute</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ComponentImpl extends MinimalEObjectImpl.Container implements Component {
	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

	/**
	 * The cached value of the '{@link #getService() <em>Service</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getService()
	 * @generated
	 * @ordered
	 */
	protected Service service;

	/**
	 * The cached value of the '{@link #getReference() <em>Reference</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReference()
	 * @generated
	 * @ordered
	 */
	protected EList<Reference> reference;

	/**
	 * The cached value of the '{@link #getImplementation() <em>Implementation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementation()
	 * @generated
	 * @ordered
	 */
	protected Implementation implementation;

	/**
	 * The cached value of the '{@link #getAny() <em>Any</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAny()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap any;

	/**
	 * The default value of the '{@link #getActivate() <em>Activate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivate()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTIVATE_EDEFAULT = "activate";

	/**
	 * The cached value of the '{@link #getActivate() <em>Activate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivate()
	 * @generated
	 * @ordered
	 */
	protected String activate = ACTIVATE_EDEFAULT;

	/**
	 * This is true if the Activate attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean activateESet;

	/**
	 * The default value of the '{@link #getConfigurationPid() <em>Configuration Pid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigurationPid()
	 * @generated
	 * @ordered
	 */
	protected static final String CONFIGURATION_PID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConfigurationPid() <em>Configuration Pid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigurationPid()
	 * @generated
	 * @ordered
	 */
	protected String configurationPid = CONFIGURATION_PID_EDEFAULT;

	/**
	 * The default value of the '{@link #getConfigurationPolicy() <em>Configuration Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigurationPolicy()
	 * @generated
	 * @ordered
	 */
	protected static final ConfigurationPolicy CONFIGURATION_POLICY_EDEFAULT = ConfigurationPolicy.OPTIONAL;

	/**
	 * The cached value of the '{@link #getConfigurationPolicy() <em>Configuration Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigurationPolicy()
	 * @generated
	 * @ordered
	 */
	protected ConfigurationPolicy configurationPolicy = CONFIGURATION_POLICY_EDEFAULT;

	/**
	 * This is true if the Configuration Policy attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean configurationPolicyESet;

	/**
	 * The default value of the '{@link #getDeactivate() <em>Deactivate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeactivate()
	 * @generated
	 * @ordered
	 */
	protected static final String DEACTIVATE_EDEFAULT = "deactivate";

	/**
	 * The cached value of the '{@link #getDeactivate() <em>Deactivate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeactivate()
	 * @generated
	 * @ordered
	 */
	protected String deactivate = DEACTIVATE_EDEFAULT;

	/**
	 * This is true if the Deactivate attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean deactivateESet;

	/**
	 * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected boolean enabled = ENABLED_EDEFAULT;

	/**
	 * This is true if the Enabled attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean enabledESet;

	/**
	 * The default value of the '{@link #getFactory() <em>Factory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFactory()
	 * @generated
	 * @ordered
	 */
	protected static final String FACTORY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFactory() <em>Factory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFactory()
	 * @generated
	 * @ordered
	 */
	protected String factory = FACTORY_EDEFAULT;

	/**
	 * The default value of the '{@link #isImmediate() <em>Immediate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isImmediate()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IMMEDIATE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isImmediate() <em>Immediate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isImmediate()
	 * @generated
	 * @ordered
	 */
	protected boolean immediate = IMMEDIATE_EDEFAULT;

	/**
	 * This is true if the Immediate attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean immediateESet;

	/**
	 * The default value of the '{@link #getModified() <em>Modified</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModified()
	 * @generated
	 * @ordered
	 */
	protected static final String MODIFIED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getModified() <em>Modified</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModified()
	 * @generated
	 * @ordered
	 */
	protected String modified = MODIFIED_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAnyAttribute() <em>Any Attribute</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnyAttribute()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap anyAttribute;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScrPackage.Literals.COMPONENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, ScrPackage.COMPONENT__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Property> getProperty() {
		return getGroup().list(ScrPackage.Literals.COMPONENT__PROPERTY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Properties> getProperties() {
		return getGroup().list(ScrPackage.Literals.COMPONENT__PROPERTIES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Service getService() {
		return service;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetService(Service newService, NotificationChain msgs) {
		Service oldService = service;
		service = newService;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScrPackage.COMPONENT__SERVICE, oldService, newService);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setService(Service newService) {
		if (newService != service) {
			NotificationChain msgs = null;
			if (service != null)
				msgs = ((InternalEObject)service).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScrPackage.COMPONENT__SERVICE, null, msgs);
			if (newService != null)
				msgs = ((InternalEObject)newService).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScrPackage.COMPONENT__SERVICE, null, msgs);
			msgs = basicSetService(newService, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.COMPONENT__SERVICE, newService, newService));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Reference> getReference() {
		if (reference == null) {
			reference = new EObjectContainmentEList<Reference>(Reference.class, this, ScrPackage.COMPONENT__REFERENCE);
		}
		return reference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Implementation getImplementation() {
		return implementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetImplementation(Implementation newImplementation, NotificationChain msgs) {
		Implementation oldImplementation = implementation;
		implementation = newImplementation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScrPackage.COMPONENT__IMPLEMENTATION, oldImplementation, newImplementation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImplementation(Implementation newImplementation) {
		if (newImplementation != implementation) {
			NotificationChain msgs = null;
			if (implementation != null)
				msgs = ((InternalEObject)implementation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScrPackage.COMPONENT__IMPLEMENTATION, null, msgs);
			if (newImplementation != null)
				msgs = ((InternalEObject)newImplementation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScrPackage.COMPONENT__IMPLEMENTATION, null, msgs);
			msgs = basicSetImplementation(newImplementation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.COMPONENT__IMPLEMENTATION, newImplementation, newImplementation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getAny() {
		if (any == null) {
			any = new BasicFeatureMap(this, ScrPackage.COMPONENT__ANY);
		}
		return any;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getActivate() {
		return activate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivate(String newActivate) {
		String oldActivate = activate;
		activate = newActivate;
		boolean oldActivateESet = activateESet;
		activateESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.COMPONENT__ACTIVATE, oldActivate, activate, !oldActivateESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetActivate() {
		String oldActivate = activate;
		boolean oldActivateESet = activateESet;
		activate = ACTIVATE_EDEFAULT;
		activateESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScrPackage.COMPONENT__ACTIVATE, oldActivate, ACTIVATE_EDEFAULT, oldActivateESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetActivate() {
		return activateESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getConfigurationPid() {
		return configurationPid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConfigurationPid(String newConfigurationPid) {
		String oldConfigurationPid = configurationPid;
		configurationPid = newConfigurationPid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.COMPONENT__CONFIGURATION_PID, oldConfigurationPid, configurationPid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConfigurationPolicy getConfigurationPolicy() {
		return configurationPolicy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConfigurationPolicy(ConfigurationPolicy newConfigurationPolicy) {
		ConfigurationPolicy oldConfigurationPolicy = configurationPolicy;
		configurationPolicy = newConfigurationPolicy == null ? CONFIGURATION_POLICY_EDEFAULT : newConfigurationPolicy;
		boolean oldConfigurationPolicyESet = configurationPolicyESet;
		configurationPolicyESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.COMPONENT__CONFIGURATION_POLICY, oldConfigurationPolicy, configurationPolicy, !oldConfigurationPolicyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetConfigurationPolicy() {
		ConfigurationPolicy oldConfigurationPolicy = configurationPolicy;
		boolean oldConfigurationPolicyESet = configurationPolicyESet;
		configurationPolicy = CONFIGURATION_POLICY_EDEFAULT;
		configurationPolicyESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScrPackage.COMPONENT__CONFIGURATION_POLICY, oldConfigurationPolicy, CONFIGURATION_POLICY_EDEFAULT, oldConfigurationPolicyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetConfigurationPolicy() {
		return configurationPolicyESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDeactivate() {
		return deactivate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeactivate(String newDeactivate) {
		String oldDeactivate = deactivate;
		deactivate = newDeactivate;
		boolean oldDeactivateESet = deactivateESet;
		deactivateESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.COMPONENT__DEACTIVATE, oldDeactivate, deactivate, !oldDeactivateESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetDeactivate() {
		String oldDeactivate = deactivate;
		boolean oldDeactivateESet = deactivateESet;
		deactivate = DEACTIVATE_EDEFAULT;
		deactivateESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScrPackage.COMPONENT__DEACTIVATE, oldDeactivate, DEACTIVATE_EDEFAULT, oldDeactivateESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetDeactivate() {
		return deactivateESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnabled(boolean newEnabled) {
		boolean oldEnabled = enabled;
		enabled = newEnabled;
		boolean oldEnabledESet = enabledESet;
		enabledESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.COMPONENT__ENABLED, oldEnabled, enabled, !oldEnabledESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetEnabled() {
		boolean oldEnabled = enabled;
		boolean oldEnabledESet = enabledESet;
		enabled = ENABLED_EDEFAULT;
		enabledESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScrPackage.COMPONENT__ENABLED, oldEnabled, ENABLED_EDEFAULT, oldEnabledESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetEnabled() {
		return enabledESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFactory() {
		return factory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFactory(String newFactory) {
		String oldFactory = factory;
		factory = newFactory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.COMPONENT__FACTORY, oldFactory, factory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isImmediate() {
		return immediate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImmediate(boolean newImmediate) {
		boolean oldImmediate = immediate;
		immediate = newImmediate;
		boolean oldImmediateESet = immediateESet;
		immediateESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.COMPONENT__IMMEDIATE, oldImmediate, immediate, !oldImmediateESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetImmediate() {
		boolean oldImmediate = immediate;
		boolean oldImmediateESet = immediateESet;
		immediate = IMMEDIATE_EDEFAULT;
		immediateESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScrPackage.COMPONENT__IMMEDIATE, oldImmediate, IMMEDIATE_EDEFAULT, oldImmediateESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetImmediate() {
		return immediateESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getModified() {
		return modified;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModified(String newModified) {
		String oldModified = modified;
		modified = newModified;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.COMPONENT__MODIFIED, oldModified, modified));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.COMPONENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getAnyAttribute() {
		if (anyAttribute == null) {
			anyAttribute = new BasicFeatureMap(this, ScrPackage.COMPONENT__ANY_ATTRIBUTE);
		}
		return anyAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ScrPackage.COMPONENT__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case ScrPackage.COMPONENT__PROPERTY:
				return ((InternalEList<?>)getProperty()).basicRemove(otherEnd, msgs);
			case ScrPackage.COMPONENT__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
			case ScrPackage.COMPONENT__SERVICE:
				return basicSetService(null, msgs);
			case ScrPackage.COMPONENT__REFERENCE:
				return ((InternalEList<?>)getReference()).basicRemove(otherEnd, msgs);
			case ScrPackage.COMPONENT__IMPLEMENTATION:
				return basicSetImplementation(null, msgs);
			case ScrPackage.COMPONENT__ANY:
				return ((InternalEList<?>)getAny()).basicRemove(otherEnd, msgs);
			case ScrPackage.COMPONENT__ANY_ATTRIBUTE:
				return ((InternalEList<?>)getAnyAttribute()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ScrPackage.COMPONENT__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case ScrPackage.COMPONENT__PROPERTY:
				return getProperty();
			case ScrPackage.COMPONENT__PROPERTIES:
				return getProperties();
			case ScrPackage.COMPONENT__SERVICE:
				return getService();
			case ScrPackage.COMPONENT__REFERENCE:
				return getReference();
			case ScrPackage.COMPONENT__IMPLEMENTATION:
				return getImplementation();
			case ScrPackage.COMPONENT__ANY:
				if (coreType) return getAny();
				return ((FeatureMap.Internal)getAny()).getWrapper();
			case ScrPackage.COMPONENT__ACTIVATE:
				return getActivate();
			case ScrPackage.COMPONENT__CONFIGURATION_PID:
				return getConfigurationPid();
			case ScrPackage.COMPONENT__CONFIGURATION_POLICY:
				return getConfigurationPolicy();
			case ScrPackage.COMPONENT__DEACTIVATE:
				return getDeactivate();
			case ScrPackage.COMPONENT__ENABLED:
				return isEnabled();
			case ScrPackage.COMPONENT__FACTORY:
				return getFactory();
			case ScrPackage.COMPONENT__IMMEDIATE:
				return isImmediate();
			case ScrPackage.COMPONENT__MODIFIED:
				return getModified();
			case ScrPackage.COMPONENT__NAME:
				return getName();
			case ScrPackage.COMPONENT__ANY_ATTRIBUTE:
				if (coreType) return getAnyAttribute();
				return ((FeatureMap.Internal)getAnyAttribute()).getWrapper();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ScrPackage.COMPONENT__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case ScrPackage.COMPONENT__PROPERTY:
				getProperty().clear();
				getProperty().addAll((Collection<? extends Property>)newValue);
				return;
			case ScrPackage.COMPONENT__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends Properties>)newValue);
				return;
			case ScrPackage.COMPONENT__SERVICE:
				setService((Service)newValue);
				return;
			case ScrPackage.COMPONENT__REFERENCE:
				getReference().clear();
				getReference().addAll((Collection<? extends Reference>)newValue);
				return;
			case ScrPackage.COMPONENT__IMPLEMENTATION:
				setImplementation((Implementation)newValue);
				return;
			case ScrPackage.COMPONENT__ANY:
				((FeatureMap.Internal)getAny()).set(newValue);
				return;
			case ScrPackage.COMPONENT__ACTIVATE:
				setActivate((String)newValue);
				return;
			case ScrPackage.COMPONENT__CONFIGURATION_PID:
				setConfigurationPid((String)newValue);
				return;
			case ScrPackage.COMPONENT__CONFIGURATION_POLICY:
				setConfigurationPolicy((ConfigurationPolicy)newValue);
				return;
			case ScrPackage.COMPONENT__DEACTIVATE:
				setDeactivate((String)newValue);
				return;
			case ScrPackage.COMPONENT__ENABLED:
				setEnabled((Boolean)newValue);
				return;
			case ScrPackage.COMPONENT__FACTORY:
				setFactory((String)newValue);
				return;
			case ScrPackage.COMPONENT__IMMEDIATE:
				setImmediate((Boolean)newValue);
				return;
			case ScrPackage.COMPONENT__MODIFIED:
				setModified((String)newValue);
				return;
			case ScrPackage.COMPONENT__NAME:
				setName((String)newValue);
				return;
			case ScrPackage.COMPONENT__ANY_ATTRIBUTE:
				((FeatureMap.Internal)getAnyAttribute()).set(newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ScrPackage.COMPONENT__GROUP:
				getGroup().clear();
				return;
			case ScrPackage.COMPONENT__PROPERTY:
				getProperty().clear();
				return;
			case ScrPackage.COMPONENT__PROPERTIES:
				getProperties().clear();
				return;
			case ScrPackage.COMPONENT__SERVICE:
				setService((Service)null);
				return;
			case ScrPackage.COMPONENT__REFERENCE:
				getReference().clear();
				return;
			case ScrPackage.COMPONENT__IMPLEMENTATION:
				setImplementation((Implementation)null);
				return;
			case ScrPackage.COMPONENT__ANY:
				getAny().clear();
				return;
			case ScrPackage.COMPONENT__ACTIVATE:
				unsetActivate();
				return;
			case ScrPackage.COMPONENT__CONFIGURATION_PID:
				setConfigurationPid(CONFIGURATION_PID_EDEFAULT);
				return;
			case ScrPackage.COMPONENT__CONFIGURATION_POLICY:
				unsetConfigurationPolicy();
				return;
			case ScrPackage.COMPONENT__DEACTIVATE:
				unsetDeactivate();
				return;
			case ScrPackage.COMPONENT__ENABLED:
				unsetEnabled();
				return;
			case ScrPackage.COMPONENT__FACTORY:
				setFactory(FACTORY_EDEFAULT);
				return;
			case ScrPackage.COMPONENT__IMMEDIATE:
				unsetImmediate();
				return;
			case ScrPackage.COMPONENT__MODIFIED:
				setModified(MODIFIED_EDEFAULT);
				return;
			case ScrPackage.COMPONENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ScrPackage.COMPONENT__ANY_ATTRIBUTE:
				getAnyAttribute().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ScrPackage.COMPONENT__GROUP:
				return group != null && !group.isEmpty();
			case ScrPackage.COMPONENT__PROPERTY:
				return !getProperty().isEmpty();
			case ScrPackage.COMPONENT__PROPERTIES:
				return !getProperties().isEmpty();
			case ScrPackage.COMPONENT__SERVICE:
				return service != null;
			case ScrPackage.COMPONENT__REFERENCE:
				return reference != null && !reference.isEmpty();
			case ScrPackage.COMPONENT__IMPLEMENTATION:
				return implementation != null;
			case ScrPackage.COMPONENT__ANY:
				return any != null && !any.isEmpty();
			case ScrPackage.COMPONENT__ACTIVATE:
				return isSetActivate();
			case ScrPackage.COMPONENT__CONFIGURATION_PID:
				return CONFIGURATION_PID_EDEFAULT == null ? configurationPid != null : !CONFIGURATION_PID_EDEFAULT.equals(configurationPid);
			case ScrPackage.COMPONENT__CONFIGURATION_POLICY:
				return isSetConfigurationPolicy();
			case ScrPackage.COMPONENT__DEACTIVATE:
				return isSetDeactivate();
			case ScrPackage.COMPONENT__ENABLED:
				return isSetEnabled();
			case ScrPackage.COMPONENT__FACTORY:
				return FACTORY_EDEFAULT == null ? factory != null : !FACTORY_EDEFAULT.equals(factory);
			case ScrPackage.COMPONENT__IMMEDIATE:
				return isSetImmediate();
			case ScrPackage.COMPONENT__MODIFIED:
				return MODIFIED_EDEFAULT == null ? modified != null : !MODIFIED_EDEFAULT.equals(modified);
			case ScrPackage.COMPONENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ScrPackage.COMPONENT__ANY_ATTRIBUTE:
				return anyAttribute != null && !anyAttribute.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (group: ");
		result.append(group);
		result.append(", any: ");
		result.append(any);
		result.append(", activate: ");
		if (activateESet) result.append(activate); else result.append("<unset>");
		result.append(", configurationPid: ");
		result.append(configurationPid);
		result.append(", configurationPolicy: ");
		if (configurationPolicyESet) result.append(configurationPolicy); else result.append("<unset>");
		result.append(", deactivate: ");
		if (deactivateESet) result.append(deactivate); else result.append("<unset>");
		result.append(", enabled: ");
		if (enabledESet) result.append(enabled); else result.append("<unset>");
		result.append(", factory: ");
		result.append(factory);
		result.append(", immediate: ");
		if (immediateESet) result.append(immediate); else result.append("<unset>");
		result.append(", modified: ");
		result.append(modified);
		result.append(", name: ");
		result.append(name);
		result.append(", anyAttribute: ");
		result.append(anyAttribute);
		result.append(')');
		return result.toString();
	}

} //ComponentImpl
