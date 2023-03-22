/**
 */
package no.hal.osgi.impl;

import java.util.Collection;

import no.hal.osgi.ActivationPolicyKind;
import no.hal.osgi.Bundle;
import no.hal.osgi.ExportedPackage;
import no.hal.osgi.ImportedPackage;
import no.hal.osgi.OsgiPackage;
import no.hal.osgi.RequiredBundle;
import no.hal.osgi.RequiredExecutionEnvironmentKind;
import no.hal.osgi.ServiceComponent;
import no.hal.osgi.emf.util.QualifiedName;
import no.hal.osgi.emf.util.Version;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bundle</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link no.hal.osgi.impl.BundleImpl#getManifestVersion <em>Manifest Version</em>}</li>
 *   <li>{@link no.hal.osgi.impl.BundleImpl#getSymbolicName <em>Symbolic Name</em>}</li>
 *   <li>{@link no.hal.osgi.impl.BundleImpl#getName <em>Name</em>}</li>
 *   <li>{@link no.hal.osgi.impl.BundleImpl#isSingleton <em>Singleton</em>}</li>
 *   <li>{@link no.hal.osgi.impl.BundleImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link no.hal.osgi.impl.BundleImpl#getVendor <em>Vendor</em>}</li>
 *   <li>{@link no.hal.osgi.impl.BundleImpl#getActivator <em>Activator</em>}</li>
 *   <li>{@link no.hal.osgi.impl.BundleImpl#getClassPath <em>Class Path</em>}</li>
 *   <li>{@link no.hal.osgi.impl.BundleImpl#getRequiredExecutionEnvironment <em>Required Execution Environment</em>}</li>
 *   <li>{@link no.hal.osgi.impl.BundleImpl#getActivationPolicy <em>Activation Policy</em>}</li>
 *   <li>{@link no.hal.osgi.impl.BundleImpl#getExportPackage <em>Export Package</em>}</li>
 *   <li>{@link no.hal.osgi.impl.BundleImpl#getImportPackage <em>Import Package</em>}</li>
 *   <li>{@link no.hal.osgi.impl.BundleImpl#getRequireBundle <em>Require Bundle</em>}</li>
 *   <li>{@link no.hal.osgi.impl.BundleImpl#getServiceComponent <em>Service Component</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BundleImpl extends GenericAttributesContainerImpl implements Bundle {
	/**
	 * The default value of the '{@link #getManifestVersion() <em>Manifest Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManifestVersion()
	 * @generated
	 * @ordered
	 */
	protected static final Version MANIFEST_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getManifestVersion() <em>Manifest Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManifestVersion()
	 * @generated
	 * @ordered
	 */
	protected Version manifestVersion = MANIFEST_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getSymbolicName() <em>Symbolic Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSymbolicName()
	 * @generated
	 * @ordered
	 */
	protected static final QualifiedName SYMBOLIC_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSymbolicName() <em>Symbolic Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSymbolicName()
	 * @generated
	 * @ordered
	 */
	protected QualifiedName symbolicName = SYMBOLIC_NAME_EDEFAULT;

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
	 * The default value of the '{@link #isSingleton() <em>Singleton</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSingleton()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SINGLETON_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSingleton() <em>Singleton</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSingleton()
	 * @generated
	 * @ordered
	 */
	protected boolean singleton = SINGLETON_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final Version VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected Version version = VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getVendor() <em>Vendor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVendor()
	 * @generated
	 * @ordered
	 */
	protected static final String VENDOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVendor() <em>Vendor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVendor()
	 * @generated
	 * @ordered
	 */
	protected String vendor = VENDOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getActivator() <em>Activator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivator()
	 * @generated
	 * @ordered
	 */
	protected static final QualifiedName ACTIVATOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActivator() <em>Activator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivator()
	 * @generated
	 * @ordered
	 */
	protected QualifiedName activator = ACTIVATOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getClassPath() <em>Class Path</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassPath()
	 * @generated
	 * @ordered
	 */
	protected EList<String> classPath;

	/**
	 * The cached value of the '{@link #getRequiredExecutionEnvironment() <em>Required Execution Environment</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiredExecutionEnvironment()
	 * @generated
	 * @ordered
	 */
	protected EList<RequiredExecutionEnvironmentKind> requiredExecutionEnvironment;

	/**
	 * The default value of the '{@link #getActivationPolicy() <em>Activation Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivationPolicy()
	 * @generated
	 * @ordered
	 */
	protected static final ActivationPolicyKind ACTIVATION_POLICY_EDEFAULT = ActivationPolicyKind.DEFAULT;

	/**
	 * The cached value of the '{@link #getActivationPolicy() <em>Activation Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivationPolicy()
	 * @generated
	 * @ordered
	 */
	protected ActivationPolicyKind activationPolicy = ACTIVATION_POLICY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExportPackage() <em>Export Package</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExportPackage()
	 * @generated
	 * @ordered
	 */
	protected EList<ExportedPackage> exportPackage;

	/**
	 * The cached value of the '{@link #getImportPackage() <em>Import Package</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImportPackage()
	 * @generated
	 * @ordered
	 */
	protected EList<ImportedPackage> importPackage;

	/**
	 * The cached value of the '{@link #getRequireBundle() <em>Require Bundle</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequireBundle()
	 * @generated
	 * @ordered
	 */
	protected EList<RequiredBundle> requireBundle;

	/**
	 * The cached value of the '{@link #getServiceComponent() <em>Service Component</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceComponent()
	 * @generated
	 * @ordered
	 */
	protected EList<ServiceComponent> serviceComponent;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BundleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OsgiPackage.Literals.BUNDLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Version getManifestVersion() {
		return manifestVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setManifestVersion(Version newManifestVersion) {
		Version oldManifestVersion = manifestVersion;
		manifestVersion = newManifestVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsgiPackage.BUNDLE__MANIFEST_VERSION, oldManifestVersion, manifestVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public QualifiedName getSymbolicName() {
		return symbolicName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSymbolicName(QualifiedName newSymbolicName) {
		QualifiedName oldSymbolicName = symbolicName;
		symbolicName = newSymbolicName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsgiPackage.BUNDLE__SYMBOLIC_NAME, oldSymbolicName, symbolicName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsgiPackage.BUNDLE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSingleton() {
		return singleton;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSingleton(boolean newSingleton) {
		boolean oldSingleton = singleton;
		singleton = newSingleton;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsgiPackage.BUNDLE__SINGLETON, oldSingleton, singleton));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Version getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVersion(Version newVersion) {
		Version oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsgiPackage.BUNDLE__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getVendor() {
		return vendor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVendor(String newVendor) {
		String oldVendor = vendor;
		vendor = newVendor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsgiPackage.BUNDLE__VENDOR, oldVendor, vendor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public QualifiedName getActivator() {
		return activator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setActivator(QualifiedName newActivator) {
		QualifiedName oldActivator = activator;
		activator = newActivator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsgiPackage.BUNDLE__ACTIVATOR, oldActivator, activator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getClassPath() {
		if (classPath == null) {
			classPath = new EDataTypeUniqueEList<String>(String.class, this, OsgiPackage.BUNDLE__CLASS_PATH);
		}
		return classPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<RequiredExecutionEnvironmentKind> getRequiredExecutionEnvironment() {
		if (requiredExecutionEnvironment == null) {
			requiredExecutionEnvironment = new EDataTypeUniqueEList<RequiredExecutionEnvironmentKind>(RequiredExecutionEnvironmentKind.class, this, OsgiPackage.BUNDLE__REQUIRED_EXECUTION_ENVIRONMENT);
		}
		return requiredExecutionEnvironment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ActivationPolicyKind getActivationPolicy() {
		return activationPolicy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setActivationPolicy(ActivationPolicyKind newActivationPolicy) {
		ActivationPolicyKind oldActivationPolicy = activationPolicy;
		activationPolicy = newActivationPolicy == null ? ACTIVATION_POLICY_EDEFAULT : newActivationPolicy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsgiPackage.BUNDLE__ACTIVATION_POLICY, oldActivationPolicy, activationPolicy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ExportedPackage> getExportPackage() {
		if (exportPackage == null) {
			exportPackage = new EObjectContainmentEList<ExportedPackage>(ExportedPackage.class, this, OsgiPackage.BUNDLE__EXPORT_PACKAGE);
		}
		return exportPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ImportedPackage> getImportPackage() {
		if (importPackage == null) {
			importPackage = new EObjectContainmentEList<ImportedPackage>(ImportedPackage.class, this, OsgiPackage.BUNDLE__IMPORT_PACKAGE);
		}
		return importPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<RequiredBundle> getRequireBundle() {
		if (requireBundle == null) {
			requireBundle = new EObjectContainmentEList<RequiredBundle>(RequiredBundle.class, this, OsgiPackage.BUNDLE__REQUIRE_BUNDLE);
		}
		return requireBundle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ServiceComponent> getServiceComponent() {
		if (serviceComponent == null) {
			serviceComponent = new EObjectContainmentEList<ServiceComponent>(ServiceComponent.class, this, OsgiPackage.BUNDLE__SERVICE_COMPONENT);
		}
		return serviceComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OsgiPackage.BUNDLE__EXPORT_PACKAGE:
				return ((InternalEList<?>)getExportPackage()).basicRemove(otherEnd, msgs);
			case OsgiPackage.BUNDLE__IMPORT_PACKAGE:
				return ((InternalEList<?>)getImportPackage()).basicRemove(otherEnd, msgs);
			case OsgiPackage.BUNDLE__REQUIRE_BUNDLE:
				return ((InternalEList<?>)getRequireBundle()).basicRemove(otherEnd, msgs);
			case OsgiPackage.BUNDLE__SERVICE_COMPONENT:
				return ((InternalEList<?>)getServiceComponent()).basicRemove(otherEnd, msgs);
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
			case OsgiPackage.BUNDLE__MANIFEST_VERSION:
				return getManifestVersion();
			case OsgiPackage.BUNDLE__SYMBOLIC_NAME:
				return getSymbolicName();
			case OsgiPackage.BUNDLE__NAME:
				return getName();
			case OsgiPackage.BUNDLE__SINGLETON:
				return isSingleton();
			case OsgiPackage.BUNDLE__VERSION:
				return getVersion();
			case OsgiPackage.BUNDLE__VENDOR:
				return getVendor();
			case OsgiPackage.BUNDLE__ACTIVATOR:
				return getActivator();
			case OsgiPackage.BUNDLE__CLASS_PATH:
				return getClassPath();
			case OsgiPackage.BUNDLE__REQUIRED_EXECUTION_ENVIRONMENT:
				return getRequiredExecutionEnvironment();
			case OsgiPackage.BUNDLE__ACTIVATION_POLICY:
				return getActivationPolicy();
			case OsgiPackage.BUNDLE__EXPORT_PACKAGE:
				return getExportPackage();
			case OsgiPackage.BUNDLE__IMPORT_PACKAGE:
				return getImportPackage();
			case OsgiPackage.BUNDLE__REQUIRE_BUNDLE:
				return getRequireBundle();
			case OsgiPackage.BUNDLE__SERVICE_COMPONENT:
				return getServiceComponent();
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
			case OsgiPackage.BUNDLE__MANIFEST_VERSION:
				setManifestVersion((Version)newValue);
				return;
			case OsgiPackage.BUNDLE__SYMBOLIC_NAME:
				setSymbolicName((QualifiedName)newValue);
				return;
			case OsgiPackage.BUNDLE__NAME:
				setName((String)newValue);
				return;
			case OsgiPackage.BUNDLE__SINGLETON:
				setSingleton((Boolean)newValue);
				return;
			case OsgiPackage.BUNDLE__VERSION:
				setVersion((Version)newValue);
				return;
			case OsgiPackage.BUNDLE__VENDOR:
				setVendor((String)newValue);
				return;
			case OsgiPackage.BUNDLE__ACTIVATOR:
				setActivator((QualifiedName)newValue);
				return;
			case OsgiPackage.BUNDLE__CLASS_PATH:
				getClassPath().clear();
				getClassPath().addAll((Collection<? extends String>)newValue);
				return;
			case OsgiPackage.BUNDLE__REQUIRED_EXECUTION_ENVIRONMENT:
				getRequiredExecutionEnvironment().clear();
				getRequiredExecutionEnvironment().addAll((Collection<? extends RequiredExecutionEnvironmentKind>)newValue);
				return;
			case OsgiPackage.BUNDLE__ACTIVATION_POLICY:
				setActivationPolicy((ActivationPolicyKind)newValue);
				return;
			case OsgiPackage.BUNDLE__EXPORT_PACKAGE:
				getExportPackage().clear();
				getExportPackage().addAll((Collection<? extends ExportedPackage>)newValue);
				return;
			case OsgiPackage.BUNDLE__IMPORT_PACKAGE:
				getImportPackage().clear();
				getImportPackage().addAll((Collection<? extends ImportedPackage>)newValue);
				return;
			case OsgiPackage.BUNDLE__REQUIRE_BUNDLE:
				getRequireBundle().clear();
				getRequireBundle().addAll((Collection<? extends RequiredBundle>)newValue);
				return;
			case OsgiPackage.BUNDLE__SERVICE_COMPONENT:
				getServiceComponent().clear();
				getServiceComponent().addAll((Collection<? extends ServiceComponent>)newValue);
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
			case OsgiPackage.BUNDLE__MANIFEST_VERSION:
				setManifestVersion(MANIFEST_VERSION_EDEFAULT);
				return;
			case OsgiPackage.BUNDLE__SYMBOLIC_NAME:
				setSymbolicName(SYMBOLIC_NAME_EDEFAULT);
				return;
			case OsgiPackage.BUNDLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case OsgiPackage.BUNDLE__SINGLETON:
				setSingleton(SINGLETON_EDEFAULT);
				return;
			case OsgiPackage.BUNDLE__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case OsgiPackage.BUNDLE__VENDOR:
				setVendor(VENDOR_EDEFAULT);
				return;
			case OsgiPackage.BUNDLE__ACTIVATOR:
				setActivator(ACTIVATOR_EDEFAULT);
				return;
			case OsgiPackage.BUNDLE__CLASS_PATH:
				getClassPath().clear();
				return;
			case OsgiPackage.BUNDLE__REQUIRED_EXECUTION_ENVIRONMENT:
				getRequiredExecutionEnvironment().clear();
				return;
			case OsgiPackage.BUNDLE__ACTIVATION_POLICY:
				setActivationPolicy(ACTIVATION_POLICY_EDEFAULT);
				return;
			case OsgiPackage.BUNDLE__EXPORT_PACKAGE:
				getExportPackage().clear();
				return;
			case OsgiPackage.BUNDLE__IMPORT_PACKAGE:
				getImportPackage().clear();
				return;
			case OsgiPackage.BUNDLE__REQUIRE_BUNDLE:
				getRequireBundle().clear();
				return;
			case OsgiPackage.BUNDLE__SERVICE_COMPONENT:
				getServiceComponent().clear();
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
			case OsgiPackage.BUNDLE__MANIFEST_VERSION:
				return MANIFEST_VERSION_EDEFAULT == null ? manifestVersion != null : !MANIFEST_VERSION_EDEFAULT.equals(manifestVersion);
			case OsgiPackage.BUNDLE__SYMBOLIC_NAME:
				return SYMBOLIC_NAME_EDEFAULT == null ? symbolicName != null : !SYMBOLIC_NAME_EDEFAULT.equals(symbolicName);
			case OsgiPackage.BUNDLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case OsgiPackage.BUNDLE__SINGLETON:
				return singleton != SINGLETON_EDEFAULT;
			case OsgiPackage.BUNDLE__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case OsgiPackage.BUNDLE__VENDOR:
				return VENDOR_EDEFAULT == null ? vendor != null : !VENDOR_EDEFAULT.equals(vendor);
			case OsgiPackage.BUNDLE__ACTIVATOR:
				return ACTIVATOR_EDEFAULT == null ? activator != null : !ACTIVATOR_EDEFAULT.equals(activator);
			case OsgiPackage.BUNDLE__CLASS_PATH:
				return classPath != null && !classPath.isEmpty();
			case OsgiPackage.BUNDLE__REQUIRED_EXECUTION_ENVIRONMENT:
				return requiredExecutionEnvironment != null && !requiredExecutionEnvironment.isEmpty();
			case OsgiPackage.BUNDLE__ACTIVATION_POLICY:
				return activationPolicy != ACTIVATION_POLICY_EDEFAULT;
			case OsgiPackage.BUNDLE__EXPORT_PACKAGE:
				return exportPackage != null && !exportPackage.isEmpty();
			case OsgiPackage.BUNDLE__IMPORT_PACKAGE:
				return importPackage != null && !importPackage.isEmpty();
			case OsgiPackage.BUNDLE__REQUIRE_BUNDLE:
				return requireBundle != null && !requireBundle.isEmpty();
			case OsgiPackage.BUNDLE__SERVICE_COMPONENT:
				return serviceComponent != null && !serviceComponent.isEmpty();
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

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (manifestVersion: ");
		result.append(manifestVersion);
		result.append(", symbolicName: ");
		result.append(symbolicName);
		result.append(", name: ");
		result.append(name);
		result.append(", singleton: ");
		result.append(singleton);
		result.append(", version: ");
		result.append(version);
		result.append(", vendor: ");
		result.append(vendor);
		result.append(", activator: ");
		result.append(activator);
		result.append(", classPath: ");
		result.append(classPath);
		result.append(", requiredExecutionEnvironment: ");
		result.append(requiredExecutionEnvironment);
		result.append(", activationPolicy: ");
		result.append(activationPolicy);
		result.append(')');
		return result.toString();
	}

} //BundleImpl
