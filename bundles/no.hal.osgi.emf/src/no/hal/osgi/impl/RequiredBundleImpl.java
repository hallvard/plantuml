/**
 */
package no.hal.osgi.impl;

import java.util.Collection;

import no.hal.osgi.Bundle;
import no.hal.osgi.OsgiPackage;
import no.hal.osgi.RequiredBundle;
import no.hal.osgi.VisibilityKind;
import no.hal.osgi.emf.util.QualifiedName;
import no.hal.osgi.emf.util.VersionRange;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Required Bundle</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link no.hal.osgi.impl.RequiredBundleImpl#getName <em>Name</em>}</li>
 *   <li>{@link no.hal.osgi.impl.RequiredBundleImpl#getBundleVersion <em>Bundle Version</em>}</li>
 *   <li>{@link no.hal.osgi.impl.RequiredBundleImpl#isOptional <em>Optional</em>}</li>
 *   <li>{@link no.hal.osgi.impl.RequiredBundleImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link no.hal.osgi.impl.RequiredBundleImpl#getResolvesTo <em>Resolves To</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RequiredBundleImpl extends MinimalEObjectImpl.Container implements RequiredBundle {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final QualifiedName NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected QualifiedName name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getBundleVersion() <em>Bundle Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBundleVersion()
	 * @generated
	 * @ordered
	 */
	protected static final VersionRange BUNDLE_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBundleVersion() <em>Bundle Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBundleVersion()
	 * @generated
	 * @ordered
	 */
	protected VersionRange bundleVersion = BUNDLE_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #isOptional() <em>Optional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOptional()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OPTIONAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOptional() <em>Optional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOptional()
	 * @generated
	 * @ordered
	 */
	protected boolean optional = OPTIONAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected static final VisibilityKind VISIBILITY_EDEFAULT = VisibilityKind.DEFAULT;

	/**
	 * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected VisibilityKind visibility = VISIBILITY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getResolvesTo() <em>Resolves To</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResolvesTo()
	 * @generated
	 * @ordered
	 */
	protected EList<Bundle> resolvesTo;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RequiredBundleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OsgiPackage.Literals.REQUIRED_BUNDLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public QualifiedName getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(QualifiedName newName) {
		QualifiedName oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsgiPackage.REQUIRED_BUNDLE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public VersionRange getBundleVersion() {
		return bundleVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBundleVersion(VersionRange newBundleVersion) {
		VersionRange oldBundleVersion = bundleVersion;
		bundleVersion = newBundleVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsgiPackage.REQUIRED_BUNDLE__BUNDLE_VERSION, oldBundleVersion, bundleVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isOptional() {
		return optional;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOptional(boolean newOptional) {
		boolean oldOptional = optional;
		optional = newOptional;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsgiPackage.REQUIRED_BUNDLE__OPTIONAL, oldOptional, optional));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public VisibilityKind getVisibility() {
		return visibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVisibility(VisibilityKind newVisibility) {
		VisibilityKind oldVisibility = visibility;
		visibility = newVisibility == null ? VISIBILITY_EDEFAULT : newVisibility;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsgiPackage.REQUIRED_BUNDLE__VISIBILITY, oldVisibility, visibility));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Bundle> getResolvesTo() {
		if (resolvesTo == null) {
			resolvesTo = new EObjectResolvingEList<Bundle>(Bundle.class, this, OsgiPackage.REQUIRED_BUNDLE__RESOLVES_TO);
		}
		return resolvesTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OsgiPackage.REQUIRED_BUNDLE__NAME:
				return getName();
			case OsgiPackage.REQUIRED_BUNDLE__BUNDLE_VERSION:
				return getBundleVersion();
			case OsgiPackage.REQUIRED_BUNDLE__OPTIONAL:
				return isOptional();
			case OsgiPackage.REQUIRED_BUNDLE__VISIBILITY:
				return getVisibility();
			case OsgiPackage.REQUIRED_BUNDLE__RESOLVES_TO:
				return getResolvesTo();
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
			case OsgiPackage.REQUIRED_BUNDLE__NAME:
				setName((QualifiedName)newValue);
				return;
			case OsgiPackage.REQUIRED_BUNDLE__BUNDLE_VERSION:
				setBundleVersion((VersionRange)newValue);
				return;
			case OsgiPackage.REQUIRED_BUNDLE__OPTIONAL:
				setOptional((Boolean)newValue);
				return;
			case OsgiPackage.REQUIRED_BUNDLE__VISIBILITY:
				setVisibility((VisibilityKind)newValue);
				return;
			case OsgiPackage.REQUIRED_BUNDLE__RESOLVES_TO:
				getResolvesTo().clear();
				getResolvesTo().addAll((Collection<? extends Bundle>)newValue);
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
			case OsgiPackage.REQUIRED_BUNDLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case OsgiPackage.REQUIRED_BUNDLE__BUNDLE_VERSION:
				setBundleVersion(BUNDLE_VERSION_EDEFAULT);
				return;
			case OsgiPackage.REQUIRED_BUNDLE__OPTIONAL:
				setOptional(OPTIONAL_EDEFAULT);
				return;
			case OsgiPackage.REQUIRED_BUNDLE__VISIBILITY:
				setVisibility(VISIBILITY_EDEFAULT);
				return;
			case OsgiPackage.REQUIRED_BUNDLE__RESOLVES_TO:
				getResolvesTo().clear();
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
			case OsgiPackage.REQUIRED_BUNDLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case OsgiPackage.REQUIRED_BUNDLE__BUNDLE_VERSION:
				return BUNDLE_VERSION_EDEFAULT == null ? bundleVersion != null : !BUNDLE_VERSION_EDEFAULT.equals(bundleVersion);
			case OsgiPackage.REQUIRED_BUNDLE__OPTIONAL:
				return optional != OPTIONAL_EDEFAULT;
			case OsgiPackage.REQUIRED_BUNDLE__VISIBILITY:
				return visibility != VISIBILITY_EDEFAULT;
			case OsgiPackage.REQUIRED_BUNDLE__RESOLVES_TO:
				return resolvesTo != null && !resolvesTo.isEmpty();
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
		result.append(" (name: ");
		result.append(name);
		result.append(", bundleVersion: ");
		result.append(bundleVersion);
		result.append(", optional: ");
		result.append(optional);
		result.append(", visibility: ");
		result.append(visibility);
		result.append(')');
		return result.toString();
	}

} //RequiredBundleImpl
