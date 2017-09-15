/**
 */
package no.hal.osgi;

import no.hal.osgi.emf.util.QualifiedName;
import no.hal.osgi.emf.util.Version;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bundle</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link no.hal.osgi.Bundle#getManifestVersion <em>Manifest Version</em>}</li>
 *   <li>{@link no.hal.osgi.Bundle#getSymbolicName <em>Symbolic Name</em>}</li>
 *   <li>{@link no.hal.osgi.Bundle#getName <em>Name</em>}</li>
 *   <li>{@link no.hal.osgi.Bundle#isSingleton <em>Singleton</em>}</li>
 *   <li>{@link no.hal.osgi.Bundle#getVersion <em>Version</em>}</li>
 *   <li>{@link no.hal.osgi.Bundle#getVendor <em>Vendor</em>}</li>
 *   <li>{@link no.hal.osgi.Bundle#getActivator <em>Activator</em>}</li>
 *   <li>{@link no.hal.osgi.Bundle#getClassPath <em>Class Path</em>}</li>
 *   <li>{@link no.hal.osgi.Bundle#getRequiredExecutionEnvironment <em>Required Execution Environment</em>}</li>
 *   <li>{@link no.hal.osgi.Bundle#getActivationPolicy <em>Activation Policy</em>}</li>
 *   <li>{@link no.hal.osgi.Bundle#getExportPackage <em>Export Package</em>}</li>
 *   <li>{@link no.hal.osgi.Bundle#getImportPackage <em>Import Package</em>}</li>
 *   <li>{@link no.hal.osgi.Bundle#getRequireBundle <em>Require Bundle</em>}</li>
 *   <li>{@link no.hal.osgi.Bundle#getServiceComponent <em>Service Component</em>}</li>
 * </ul>
 *
 * @see no.hal.osgi.OsgiPackage#getBundle()
 * @model
 * @generated
 */
public interface Bundle extends GenericAttributesContainer {
	/**
	 * Returns the value of the '<em><b>Manifest Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Manifest Version</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Manifest Version</em>' attribute.
	 * @see #setManifestVersion(Version)
	 * @see no.hal.osgi.OsgiPackage#getBundle_ManifestVersion()
	 * @model dataType="no.hal.osgi.Version"
	 * @generated
	 */
	Version getManifestVersion();

	/**
	 * Sets the value of the '{@link no.hal.osgi.Bundle#getManifestVersion <em>Manifest Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Manifest Version</em>' attribute.
	 * @see #getManifestVersion()
	 * @generated
	 */
	void setManifestVersion(Version value);

	/**
	 * Returns the value of the '<em><b>Symbolic Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Symbolic Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Symbolic Name</em>' attribute.
	 * @see #setSymbolicName(QualifiedName)
	 * @see no.hal.osgi.OsgiPackage#getBundle_SymbolicName()
	 * @model dataType="no.hal.osgi.QualifiedName"
	 * @generated
	 */
	QualifiedName getSymbolicName();

	/**
	 * Sets the value of the '{@link no.hal.osgi.Bundle#getSymbolicName <em>Symbolic Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Symbolic Name</em>' attribute.
	 * @see #getSymbolicName()
	 * @generated
	 */
	void setSymbolicName(QualifiedName value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see no.hal.osgi.OsgiPackage#getBundle_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link no.hal.osgi.Bundle#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Singleton</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Singleton</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Singleton</em>' attribute.
	 * @see #setSingleton(boolean)
	 * @see no.hal.osgi.OsgiPackage#getBundle_Singleton()
	 * @model
	 * @generated
	 */
	boolean isSingleton();

	/**
	 * Sets the value of the '{@link no.hal.osgi.Bundle#isSingleton <em>Singleton</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Singleton</em>' attribute.
	 * @see #isSingleton()
	 * @generated
	 */
	void setSingleton(boolean value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(Version)
	 * @see no.hal.osgi.OsgiPackage#getBundle_Version()
	 * @model dataType="no.hal.osgi.Version"
	 * @generated
	 */
	Version getVersion();

	/**
	 * Sets the value of the '{@link no.hal.osgi.Bundle#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(Version value);

	/**
	 * Returns the value of the '<em><b>Vendor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vendor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vendor</em>' attribute.
	 * @see #setVendor(String)
	 * @see no.hal.osgi.OsgiPackage#getBundle_Vendor()
	 * @model
	 * @generated
	 */
	String getVendor();

	/**
	 * Sets the value of the '{@link no.hal.osgi.Bundle#getVendor <em>Vendor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vendor</em>' attribute.
	 * @see #getVendor()
	 * @generated
	 */
	void setVendor(String value);

	/**
	 * Returns the value of the '<em><b>Activator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activator</em>' attribute.
	 * @see #setActivator(QualifiedName)
	 * @see no.hal.osgi.OsgiPackage#getBundle_Activator()
	 * @model dataType="no.hal.osgi.QualifiedName"
	 * @generated
	 */
	QualifiedName getActivator();

	/**
	 * Sets the value of the '{@link no.hal.osgi.Bundle#getActivator <em>Activator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activator</em>' attribute.
	 * @see #getActivator()
	 * @generated
	 */
	void setActivator(QualifiedName value);

	/**
	 * Returns the value of the '<em><b>Class Path</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class Path</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class Path</em>' attribute list.
	 * @see no.hal.osgi.OsgiPackage#getBundle_ClassPath()
	 * @model
	 * @generated
	 */
	EList<String> getClassPath();

	/**
	 * Returns the value of the '<em><b>Required Execution Environment</b></em>' attribute list.
	 * The list contents are of type {@link no.hal.osgi.RequiredExecutionEnvironmentKind}.
	 * The literals are from the enumeration {@link no.hal.osgi.RequiredExecutionEnvironmentKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Required Execution Environment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Required Execution Environment</em>' attribute list.
	 * @see no.hal.osgi.RequiredExecutionEnvironmentKind
	 * @see no.hal.osgi.OsgiPackage#getBundle_RequiredExecutionEnvironment()
	 * @model
	 * @generated
	 */
	EList<RequiredExecutionEnvironmentKind> getRequiredExecutionEnvironment();

	/**
	 * Returns the value of the '<em><b>Activation Policy</b></em>' attribute.
	 * The literals are from the enumeration {@link no.hal.osgi.ActivationPolicyKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activation Policy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activation Policy</em>' attribute.
	 * @see no.hal.osgi.ActivationPolicyKind
	 * @see #setActivationPolicy(ActivationPolicyKind)
	 * @see no.hal.osgi.OsgiPackage#getBundle_ActivationPolicy()
	 * @model
	 * @generated
	 */
	ActivationPolicyKind getActivationPolicy();

	/**
	 * Sets the value of the '{@link no.hal.osgi.Bundle#getActivationPolicy <em>Activation Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activation Policy</em>' attribute.
	 * @see no.hal.osgi.ActivationPolicyKind
	 * @see #getActivationPolicy()
	 * @generated
	 */
	void setActivationPolicy(ActivationPolicyKind value);

	/**
	 * Returns the value of the '<em><b>Export Package</b></em>' containment reference list.
	 * The list contents are of type {@link no.hal.osgi.ExportedPackage}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Export Package</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Export Package</em>' containment reference list.
	 * @see no.hal.osgi.OsgiPackage#getBundle_ExportPackage()
	 * @model containment="true"
	 * @generated
	 */
	EList<ExportedPackage> getExportPackage();

	/**
	 * Returns the value of the '<em><b>Import Package</b></em>' containment reference list.
	 * The list contents are of type {@link no.hal.osgi.ImportedPackage}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Import Package</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Import Package</em>' containment reference list.
	 * @see no.hal.osgi.OsgiPackage#getBundle_ImportPackage()
	 * @model containment="true"
	 * @generated
	 */
	EList<ImportedPackage> getImportPackage();

	/**
	 * Returns the value of the '<em><b>Require Bundle</b></em>' containment reference list.
	 * The list contents are of type {@link no.hal.osgi.RequiredBundle}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Require Bundle</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Require Bundle</em>' containment reference list.
	 * @see no.hal.osgi.OsgiPackage#getBundle_RequireBundle()
	 * @model containment="true"
	 * @generated
	 */
	EList<RequiredBundle> getRequireBundle();

	/**
	 * Returns the value of the '<em><b>Service Component</b></em>' containment reference list.
	 * The list contents are of type {@link no.hal.osgi.ServiceComponent}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service Component</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Service Component</em>' containment reference list.
	 * @see no.hal.osgi.OsgiPackage#getBundle_ServiceComponent()
	 * @model containment="true"
	 * @generated
	 */
	EList<ServiceComponent> getServiceComponent();

} // Bundle
