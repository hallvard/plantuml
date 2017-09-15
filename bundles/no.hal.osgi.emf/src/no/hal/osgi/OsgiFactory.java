/**
 */
package no.hal.osgi;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see no.hal.osgi.OsgiPackage
 * @generated
 */
public interface OsgiFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OsgiFactory eINSTANCE = no.hal.osgi.impl.OsgiFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Manifest</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Manifest</em>'.
	 * @generated
	 */
	Manifest createManifest();

	/**
	 * Returns a new object of class '<em>Generic Attributes Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Generic Attributes Container</em>'.
	 * @generated
	 */
	GenericAttributesContainer createGenericAttributesContainer();

	/**
	 * Returns a new object of class '<em>Generic Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Generic Attribute</em>'.
	 * @generated
	 */
	GenericAttribute createGenericAttribute();

	/**
	 * Returns a new object of class '<em>Generic Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Generic Attribute Value</em>'.
	 * @generated
	 */
	GenericAttributeValue createGenericAttributeValue();

	/**
	 * Returns a new object of class '<em>Bundle</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Bundle</em>'.
	 * @generated
	 */
	Bundle createBundle();

	/**
	 * Returns a new object of class '<em>Required Bundle</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Required Bundle</em>'.
	 * @generated
	 */
	RequiredBundle createRequiredBundle();

	/**
	 * Returns a new object of class '<em>Exported Package</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Exported Package</em>'.
	 * @generated
	 */
	ExportedPackage createExportedPackage();

	/**
	 * Returns a new object of class '<em>Imported Package</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Imported Package</em>'.
	 * @generated
	 */
	ImportedPackage createImportedPackage();

	/**
	 * Returns a new object of class '<em>Service Component</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Service Component</em>'.
	 * @generated
	 */
	ServiceComponent createServiceComponent();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	OsgiPackage getOsgiPackage();

} //OsgiFactory
