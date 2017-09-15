/**
 */
package no.hal.osgi;

import no.hal.osgi.emf.util.VersionRange;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Imported Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link no.hal.osgi.ImportedPackage#getVersion <em>Version</em>}</li>
 *   <li>{@link no.hal.osgi.ImportedPackage#getResolvesTo <em>Resolves To</em>}</li>
 * </ul>
 *
 * @see no.hal.osgi.OsgiPackage#getImportedPackage()
 * @model
 * @generated
 */
public interface ImportedPackage extends BundlePackage {
	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(VersionRange)
	 * @see no.hal.osgi.OsgiPackage#getImportedPackage_Version()
	 * @model dataType="no.hal.osgi.VersionRange"
	 * @generated
	 */
	VersionRange getVersion();

	/**
	 * Sets the value of the '{@link no.hal.osgi.ImportedPackage#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(VersionRange value);

	/**
	 * Returns the value of the '<em><b>Resolves To</b></em>' reference list.
	 * The list contents are of type {@link no.hal.osgi.Bundle}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resolves To</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resolves To</em>' reference list.
	 * @see no.hal.osgi.OsgiPackage#getImportedPackage_ResolvesTo()
	 * @model
	 * @generated
	 */
	EList<Bundle> getResolvesTo();

} // ImportedPackage
