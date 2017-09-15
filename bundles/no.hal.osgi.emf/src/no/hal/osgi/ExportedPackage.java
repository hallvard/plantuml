/**
 */
package no.hal.osgi;

import no.hal.osgi.emf.util.Version;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exported Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link no.hal.osgi.ExportedPackage#getVersion <em>Version</em>}</li>
 * </ul>
 *
 * @see no.hal.osgi.OsgiPackage#getExportedPackage()
 * @model
 * @generated
 */
public interface ExportedPackage extends BundlePackage {
	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(Version)
	 * @see no.hal.osgi.OsgiPackage#getExportedPackage_Version()
	 * @model dataType="no.hal.osgi.Version"
	 * @generated
	 */
	Version getVersion();

	/**
	 * Sets the value of the '{@link no.hal.osgi.ExportedPackage#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(Version value);

} // ExportedPackage
