/**
 */
package no.hal.osgi.impl;

import java.util.Collection;

import no.hal.osgi.Bundle;
import no.hal.osgi.ImportedPackage;
import no.hal.osgi.OsgiPackage;
import no.hal.osgi.emf.util.VersionRange;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Imported Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link no.hal.osgi.impl.ImportedPackageImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link no.hal.osgi.impl.ImportedPackageImpl#getResolvesTo <em>Resolves To</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ImportedPackageImpl extends BundlePackageImpl implements ImportedPackage {
	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final VersionRange VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected VersionRange version = VERSION_EDEFAULT;

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
	protected ImportedPackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OsgiPackage.Literals.IMPORTED_PACKAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public VersionRange getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVersion(VersionRange newVersion) {
		VersionRange oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsgiPackage.IMPORTED_PACKAGE__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Bundle> getResolvesTo() {
		if (resolvesTo == null) {
			resolvesTo = new EObjectResolvingEList<Bundle>(Bundle.class, this, OsgiPackage.IMPORTED_PACKAGE__RESOLVES_TO);
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
			case OsgiPackage.IMPORTED_PACKAGE__VERSION:
				return getVersion();
			case OsgiPackage.IMPORTED_PACKAGE__RESOLVES_TO:
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
			case OsgiPackage.IMPORTED_PACKAGE__VERSION:
				setVersion((VersionRange)newValue);
				return;
			case OsgiPackage.IMPORTED_PACKAGE__RESOLVES_TO:
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
			case OsgiPackage.IMPORTED_PACKAGE__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case OsgiPackage.IMPORTED_PACKAGE__RESOLVES_TO:
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
			case OsgiPackage.IMPORTED_PACKAGE__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case OsgiPackage.IMPORTED_PACKAGE__RESOLVES_TO:
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
		result.append(" (version: ");
		result.append(version);
		result.append(')');
		return result.toString();
	}

} //ImportedPackageImpl
