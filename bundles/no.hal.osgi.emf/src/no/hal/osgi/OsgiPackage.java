/**
 */
package no.hal.osgi;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see no.hal.osgi.OsgiFactory
 * @model kind="package"
 * @generated
 */
public interface OsgiPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "osgi";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "platform:/plugin/no.hal.osgi.emf/model/osgi.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "osgiEmf";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OsgiPackage eINSTANCE = no.hal.osgi.impl.OsgiPackageImpl.init();

	/**
	 * The meta object id for the '{@link no.hal.osgi.impl.GenericAttributesContainerImpl <em>Generic Attributes Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.osgi.impl.GenericAttributesContainerImpl
	 * @see no.hal.osgi.impl.OsgiPackageImpl#getGenericAttributesContainer()
	 * @generated
	 */
	int GENERIC_ATTRIBUTES_CONTAINER = 1;

	/**
	 * The feature id for the '<em><b>Generic Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_ATTRIBUTES_CONTAINER__GENERIC_ATTRIBUTES = 0;

	/**
	 * The number of structural features of the '<em>Generic Attributes Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_ATTRIBUTES_CONTAINER_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Generic Attributes Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_ATTRIBUTES_CONTAINER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link no.hal.osgi.impl.ManifestImpl <em>Manifest</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.osgi.impl.ManifestImpl
	 * @see no.hal.osgi.impl.OsgiPackageImpl#getManifest()
	 * @generated
	 */
	int MANIFEST = 0;

	/**
	 * The feature id for the '<em><b>Generic Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANIFEST__GENERIC_ATTRIBUTES = GENERIC_ATTRIBUTES_CONTAINER__GENERIC_ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANIFEST__VERSION = GENERIC_ATTRIBUTES_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Manifest</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANIFEST_FEATURE_COUNT = GENERIC_ATTRIBUTES_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Manifest</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANIFEST_OPERATION_COUNT = GENERIC_ATTRIBUTES_CONTAINER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link no.hal.osgi.impl.GenericAttributeImpl <em>Generic Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.osgi.impl.GenericAttributeImpl
	 * @see no.hal.osgi.impl.OsgiPackageImpl#getGenericAttribute()
	 * @generated
	 */
	int GENERIC_ATTRIBUTE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_ATTRIBUTE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_ATTRIBUTE__VALUES = 1;

	/**
	 * The number of structural features of the '<em>Generic Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_ATTRIBUTE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Generic Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_ATTRIBUTE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link no.hal.osgi.impl.GenericAttributeValueImpl <em>Generic Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.osgi.impl.GenericAttributeValueImpl
	 * @see no.hal.osgi.impl.OsgiPackageImpl#getGenericAttributeValue()
	 * @generated
	 */
	int GENERIC_ATTRIBUTE_VALUE = 3;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_ATTRIBUTE_VALUE__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Extra Attributes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_ATTRIBUTE_VALUE__EXTRA_ATTRIBUTES = 1;

	/**
	 * The number of structural features of the '<em>Generic Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Generic Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_ATTRIBUTE_VALUE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '<em>Version</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.osgi.emf.util.Version
	 * @see no.hal.osgi.impl.OsgiPackageImpl#getVersion()
	 * @generated
	 */
	int VERSION = 13;

	/**
	 * The meta object id for the '<em>Version Range</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.osgi.emf.util.VersionRange
	 * @see no.hal.osgi.impl.OsgiPackageImpl#getVersionRange()
	 * @generated
	 */
	int VERSION_RANGE = 14;

	/**
	 * The meta object id for the '{@link no.hal.osgi.impl.BundleImpl <em>Bundle</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.osgi.impl.BundleImpl
	 * @see no.hal.osgi.impl.OsgiPackageImpl#getBundle()
	 * @generated
	 */
	int BUNDLE = 4;

	/**
	 * The feature id for the '<em><b>Generic Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__GENERIC_ATTRIBUTES = GENERIC_ATTRIBUTES_CONTAINER__GENERIC_ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Manifest Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__MANIFEST_VERSION = GENERIC_ATTRIBUTES_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Symbolic Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__SYMBOLIC_NAME = GENERIC_ATTRIBUTES_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__NAME = GENERIC_ATTRIBUTES_CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Singleton</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__SINGLETON = GENERIC_ATTRIBUTES_CONTAINER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__VERSION = GENERIC_ATTRIBUTES_CONTAINER_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Vendor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__VENDOR = GENERIC_ATTRIBUTES_CONTAINER_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Activator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__ACTIVATOR = GENERIC_ATTRIBUTES_CONTAINER_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Class Path</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__CLASS_PATH = GENERIC_ATTRIBUTES_CONTAINER_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Required Execution Environment</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__REQUIRED_EXECUTION_ENVIRONMENT = GENERIC_ATTRIBUTES_CONTAINER_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Activation Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__ACTIVATION_POLICY = GENERIC_ATTRIBUTES_CONTAINER_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Export Package</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__EXPORT_PACKAGE = GENERIC_ATTRIBUTES_CONTAINER_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Import Package</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__IMPORT_PACKAGE = GENERIC_ATTRIBUTES_CONTAINER_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Require Bundle</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__REQUIRE_BUNDLE = GENERIC_ATTRIBUTES_CONTAINER_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Service Component</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE__SERVICE_COMPONENT = GENERIC_ATTRIBUTES_CONTAINER_FEATURE_COUNT + 13;

	/**
	 * The number of structural features of the '<em>Bundle</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE_FEATURE_COUNT = GENERIC_ATTRIBUTES_CONTAINER_FEATURE_COUNT + 14;

	/**
	 * The number of operations of the '<em>Bundle</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE_OPERATION_COUNT = GENERIC_ATTRIBUTES_CONTAINER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link no.hal.osgi.impl.RequiredBundleImpl <em>Required Bundle</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.osgi.impl.RequiredBundleImpl
	 * @see no.hal.osgi.impl.OsgiPackageImpl#getRequiredBundle()
	 * @generated
	 */
	int REQUIRED_BUNDLE = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_BUNDLE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Bundle Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_BUNDLE__BUNDLE_VERSION = 1;

	/**
	 * The feature id for the '<em><b>Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_BUNDLE__OPTIONAL = 2;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_BUNDLE__VISIBILITY = 3;

	/**
	 * The feature id for the '<em><b>Resolves To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_BUNDLE__RESOLVES_TO = 4;

	/**
	 * The number of structural features of the '<em>Required Bundle</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_BUNDLE_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Required Bundle</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_BUNDLE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link no.hal.osgi.impl.BundlePackageImpl <em>Bundle Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.osgi.impl.BundlePackageImpl
	 * @see no.hal.osgi.impl.OsgiPackageImpl#getBundlePackage()
	 * @generated
	 */
	int BUNDLE_PACKAGE = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE_PACKAGE__NAME = 0;

	/**
	 * The number of structural features of the '<em>Bundle Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE_PACKAGE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Bundle Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUNDLE_PACKAGE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link no.hal.osgi.impl.ExportedPackageImpl <em>Exported Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.osgi.impl.ExportedPackageImpl
	 * @see no.hal.osgi.impl.OsgiPackageImpl#getExportedPackage()
	 * @generated
	 */
	int EXPORTED_PACKAGE = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORTED_PACKAGE__NAME = BUNDLE_PACKAGE__NAME;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORTED_PACKAGE__VERSION = BUNDLE_PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Exported Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORTED_PACKAGE_FEATURE_COUNT = BUNDLE_PACKAGE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Exported Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORTED_PACKAGE_OPERATION_COUNT = BUNDLE_PACKAGE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link no.hal.osgi.impl.ImportedPackageImpl <em>Imported Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.osgi.impl.ImportedPackageImpl
	 * @see no.hal.osgi.impl.OsgiPackageImpl#getImportedPackage()
	 * @generated
	 */
	int IMPORTED_PACKAGE = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORTED_PACKAGE__NAME = BUNDLE_PACKAGE__NAME;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORTED_PACKAGE__VERSION = BUNDLE_PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Resolves To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORTED_PACKAGE__RESOLVES_TO = BUNDLE_PACKAGE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Imported Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORTED_PACKAGE_FEATURE_COUNT = BUNDLE_PACKAGE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Imported Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORTED_PACKAGE_OPERATION_COUNT = BUNDLE_PACKAGE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link no.hal.osgi.impl.ServiceComponentImpl <em>Service Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.osgi.impl.ServiceComponentImpl
	 * @see no.hal.osgi.impl.OsgiPackageImpl#getServiceComponent()
	 * @generated
	 */
	int SERVICE_COMPONENT = 9;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_COMPONENT__PATH = 0;

	/**
	 * The feature id for the '<em><b>Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_COMPONENT__COMPONENT = 1;

	/**
	 * The number of structural features of the '<em>Service Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_COMPONENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Service Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_COMPONENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link no.hal.osgi.RequiredExecutionEnvironmentKind <em>Required Execution Environment Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.osgi.RequiredExecutionEnvironmentKind
	 * @see no.hal.osgi.impl.OsgiPackageImpl#getRequiredExecutionEnvironmentKind()
	 * @generated
	 */
	int REQUIRED_EXECUTION_ENVIRONMENT_KIND = 10;

	/**
	 * The meta object id for the '{@link no.hal.osgi.ActivationPolicyKind <em>Activation Policy Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.osgi.ActivationPolicyKind
	 * @see no.hal.osgi.impl.OsgiPackageImpl#getActivationPolicyKind()
	 * @generated
	 */
	int ACTIVATION_POLICY_KIND = 11;

	/**
	 * The meta object id for the '{@link no.hal.osgi.VisibilityKind <em>Visibility Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.osgi.VisibilityKind
	 * @see no.hal.osgi.impl.OsgiPackageImpl#getVisibilityKind()
	 * @generated
	 */
	int VISIBILITY_KIND = 12;

	/**
	 * The meta object id for the '<em>Qualified Name</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.osgi.emf.util.QualifiedName
	 * @see no.hal.osgi.impl.OsgiPackageImpl#getQualifiedName()
	 * @generated
	 */
	int QUALIFIED_NAME = 15;


	/**
	 * The meta object id for the '<em>Path</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see no.hal.osgi.emf.util.Path
	 * @see no.hal.osgi.impl.OsgiPackageImpl#getPath()
	 * @generated
	 */
	int PATH = 16;


	/**
	 * Returns the meta object for class '{@link no.hal.osgi.Manifest <em>Manifest</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Manifest</em>'.
	 * @see no.hal.osgi.Manifest
	 * @generated
	 */
	EClass getManifest();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.osgi.Manifest#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see no.hal.osgi.Manifest#getVersion()
	 * @see #getManifest()
	 * @generated
	 */
	EAttribute getManifest_Version();

	/**
	 * Returns the meta object for class '{@link no.hal.osgi.GenericAttributesContainer <em>Generic Attributes Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Attributes Container</em>'.
	 * @see no.hal.osgi.GenericAttributesContainer
	 * @generated
	 */
	EClass getGenericAttributesContainer();

	/**
	 * Returns the meta object for the reference list '{@link no.hal.osgi.GenericAttributesContainer#getGenericAttributes <em>Generic Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Generic Attributes</em>'.
	 * @see no.hal.osgi.GenericAttributesContainer#getGenericAttributes()
	 * @see #getGenericAttributesContainer()
	 * @generated
	 */
	EReference getGenericAttributesContainer_GenericAttributes();

	/**
	 * Returns the meta object for class '{@link no.hal.osgi.GenericAttribute <em>Generic Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Attribute</em>'.
	 * @see no.hal.osgi.GenericAttribute
	 * @generated
	 */
	EClass getGenericAttribute();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.osgi.GenericAttribute#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see no.hal.osgi.GenericAttribute#getName()
	 * @see #getGenericAttribute()
	 * @generated
	 */
	EAttribute getGenericAttribute_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link no.hal.osgi.GenericAttribute#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Values</em>'.
	 * @see no.hal.osgi.GenericAttribute#getValues()
	 * @see #getGenericAttribute()
	 * @generated
	 */
	EReference getGenericAttribute_Values();

	/**
	 * Returns the meta object for class '{@link no.hal.osgi.GenericAttributeValue <em>Generic Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Attribute Value</em>'.
	 * @see no.hal.osgi.GenericAttributeValue
	 * @generated
	 */
	EClass getGenericAttributeValue();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.osgi.GenericAttributeValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see no.hal.osgi.GenericAttributeValue#getValue()
	 * @see #getGenericAttributeValue()
	 * @generated
	 */
	EAttribute getGenericAttributeValue_Value();

	/**
	 * Returns the meta object for the attribute list '{@link no.hal.osgi.GenericAttributeValue#getExtraAttributes <em>Extra Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Extra Attributes</em>'.
	 * @see no.hal.osgi.GenericAttributeValue#getExtraAttributes()
	 * @see #getGenericAttributeValue()
	 * @generated
	 */
	EAttribute getGenericAttributeValue_ExtraAttributes();

	/**
	 * Returns the meta object for data type '{@link no.hal.osgi.emf.util.Version <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Version</em>'.
	 * @see no.hal.osgi.emf.util.Version
	 * @model instanceClass="no.hal.osgi.emf.util.Version"
	 * @generated
	 */
	EDataType getVersion();

	/**
	 * Returns the meta object for data type '{@link no.hal.osgi.emf.util.VersionRange <em>Version Range</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Version Range</em>'.
	 * @see no.hal.osgi.emf.util.VersionRange
	 * @model instanceClass="no.hal.osgi.emf.util.VersionRange"
	 * @generated
	 */
	EDataType getVersionRange();

	/**
	 * Returns the meta object for class '{@link no.hal.osgi.Bundle <em>Bundle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bundle</em>'.
	 * @see no.hal.osgi.Bundle
	 * @generated
	 */
	EClass getBundle();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.osgi.Bundle#getManifestVersion <em>Manifest Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Manifest Version</em>'.
	 * @see no.hal.osgi.Bundle#getManifestVersion()
	 * @see #getBundle()
	 * @generated
	 */
	EAttribute getBundle_ManifestVersion();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.osgi.Bundle#getSymbolicName <em>Symbolic Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Symbolic Name</em>'.
	 * @see no.hal.osgi.Bundle#getSymbolicName()
	 * @see #getBundle()
	 * @generated
	 */
	EAttribute getBundle_SymbolicName();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.osgi.Bundle#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see no.hal.osgi.Bundle#getName()
	 * @see #getBundle()
	 * @generated
	 */
	EAttribute getBundle_Name();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.osgi.Bundle#isSingleton <em>Singleton</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Singleton</em>'.
	 * @see no.hal.osgi.Bundle#isSingleton()
	 * @see #getBundle()
	 * @generated
	 */
	EAttribute getBundle_Singleton();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.osgi.Bundle#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see no.hal.osgi.Bundle#getVersion()
	 * @see #getBundle()
	 * @generated
	 */
	EAttribute getBundle_Version();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.osgi.Bundle#getVendor <em>Vendor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vendor</em>'.
	 * @see no.hal.osgi.Bundle#getVendor()
	 * @see #getBundle()
	 * @generated
	 */
	EAttribute getBundle_Vendor();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.osgi.Bundle#getActivator <em>Activator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activator</em>'.
	 * @see no.hal.osgi.Bundle#getActivator()
	 * @see #getBundle()
	 * @generated
	 */
	EAttribute getBundle_Activator();

	/**
	 * Returns the meta object for the attribute list '{@link no.hal.osgi.Bundle#getClassPath <em>Class Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Class Path</em>'.
	 * @see no.hal.osgi.Bundle#getClassPath()
	 * @see #getBundle()
	 * @generated
	 */
	EAttribute getBundle_ClassPath();

	/**
	 * Returns the meta object for the attribute list '{@link no.hal.osgi.Bundle#getRequiredExecutionEnvironment <em>Required Execution Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Required Execution Environment</em>'.
	 * @see no.hal.osgi.Bundle#getRequiredExecutionEnvironment()
	 * @see #getBundle()
	 * @generated
	 */
	EAttribute getBundle_RequiredExecutionEnvironment();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.osgi.Bundle#getActivationPolicy <em>Activation Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activation Policy</em>'.
	 * @see no.hal.osgi.Bundle#getActivationPolicy()
	 * @see #getBundle()
	 * @generated
	 */
	EAttribute getBundle_ActivationPolicy();

	/**
	 * Returns the meta object for the containment reference list '{@link no.hal.osgi.Bundle#getExportPackage <em>Export Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Export Package</em>'.
	 * @see no.hal.osgi.Bundle#getExportPackage()
	 * @see #getBundle()
	 * @generated
	 */
	EReference getBundle_ExportPackage();

	/**
	 * Returns the meta object for the containment reference list '{@link no.hal.osgi.Bundle#getImportPackage <em>Import Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Import Package</em>'.
	 * @see no.hal.osgi.Bundle#getImportPackage()
	 * @see #getBundle()
	 * @generated
	 */
	EReference getBundle_ImportPackage();

	/**
	 * Returns the meta object for the containment reference list '{@link no.hal.osgi.Bundle#getRequireBundle <em>Require Bundle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Require Bundle</em>'.
	 * @see no.hal.osgi.Bundle#getRequireBundle()
	 * @see #getBundle()
	 * @generated
	 */
	EReference getBundle_RequireBundle();

	/**
	 * Returns the meta object for the containment reference list '{@link no.hal.osgi.Bundle#getServiceComponent <em>Service Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Service Component</em>'.
	 * @see no.hal.osgi.Bundle#getServiceComponent()
	 * @see #getBundle()
	 * @generated
	 */
	EReference getBundle_ServiceComponent();

	/**
	 * Returns the meta object for class '{@link no.hal.osgi.RequiredBundle <em>Required Bundle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Required Bundle</em>'.
	 * @see no.hal.osgi.RequiredBundle
	 * @generated
	 */
	EClass getRequiredBundle();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.osgi.RequiredBundle#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see no.hal.osgi.RequiredBundle#getName()
	 * @see #getRequiredBundle()
	 * @generated
	 */
	EAttribute getRequiredBundle_Name();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.osgi.RequiredBundle#getBundleVersion <em>Bundle Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bundle Version</em>'.
	 * @see no.hal.osgi.RequiredBundle#getBundleVersion()
	 * @see #getRequiredBundle()
	 * @generated
	 */
	EAttribute getRequiredBundle_BundleVersion();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.osgi.RequiredBundle#isOptional <em>Optional</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Optional</em>'.
	 * @see no.hal.osgi.RequiredBundle#isOptional()
	 * @see #getRequiredBundle()
	 * @generated
	 */
	EAttribute getRequiredBundle_Optional();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.osgi.RequiredBundle#getVisibility <em>Visibility</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Visibility</em>'.
	 * @see no.hal.osgi.RequiredBundle#getVisibility()
	 * @see #getRequiredBundle()
	 * @generated
	 */
	EAttribute getRequiredBundle_Visibility();

	/**
	 * Returns the meta object for the reference list '{@link no.hal.osgi.RequiredBundle#getResolvesTo <em>Resolves To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Resolves To</em>'.
	 * @see no.hal.osgi.RequiredBundle#getResolvesTo()
	 * @see #getRequiredBundle()
	 * @generated
	 */
	EReference getRequiredBundle_ResolvesTo();

	/**
	 * Returns the meta object for class '{@link no.hal.osgi.BundlePackage <em>Bundle Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bundle Package</em>'.
	 * @see no.hal.osgi.BundlePackage
	 * @generated
	 */
	EClass getBundlePackage();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.osgi.BundlePackage#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see no.hal.osgi.BundlePackage#getName()
	 * @see #getBundlePackage()
	 * @generated
	 */
	EAttribute getBundlePackage_Name();

	/**
	 * Returns the meta object for class '{@link no.hal.osgi.ExportedPackage <em>Exported Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exported Package</em>'.
	 * @see no.hal.osgi.ExportedPackage
	 * @generated
	 */
	EClass getExportedPackage();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.osgi.ExportedPackage#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see no.hal.osgi.ExportedPackage#getVersion()
	 * @see #getExportedPackage()
	 * @generated
	 */
	EAttribute getExportedPackage_Version();

	/**
	 * Returns the meta object for class '{@link no.hal.osgi.ImportedPackage <em>Imported Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Imported Package</em>'.
	 * @see no.hal.osgi.ImportedPackage
	 * @generated
	 */
	EClass getImportedPackage();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.osgi.ImportedPackage#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see no.hal.osgi.ImportedPackage#getVersion()
	 * @see #getImportedPackage()
	 * @generated
	 */
	EAttribute getImportedPackage_Version();

	/**
	 * Returns the meta object for the reference list '{@link no.hal.osgi.ImportedPackage#getResolvesTo <em>Resolves To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Resolves To</em>'.
	 * @see no.hal.osgi.ImportedPackage#getResolvesTo()
	 * @see #getImportedPackage()
	 * @generated
	 */
	EReference getImportedPackage_ResolvesTo();

	/**
	 * Returns the meta object for class '{@link no.hal.osgi.ServiceComponent <em>Service Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service Component</em>'.
	 * @see no.hal.osgi.ServiceComponent
	 * @generated
	 */
	EClass getServiceComponent();

	/**
	 * Returns the meta object for the attribute '{@link no.hal.osgi.ServiceComponent#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see no.hal.osgi.ServiceComponent#getPath()
	 * @see #getServiceComponent()
	 * @generated
	 */
	EAttribute getServiceComponent_Path();

	/**
	 * Returns the meta object for the reference '{@link no.hal.osgi.ServiceComponent#getComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Component</em>'.
	 * @see no.hal.osgi.ServiceComponent#getComponent()
	 * @see #getServiceComponent()
	 * @generated
	 */
	EReference getServiceComponent_Component();

	/**
	 * Returns the meta object for enum '{@link no.hal.osgi.RequiredExecutionEnvironmentKind <em>Required Execution Environment Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Required Execution Environment Kind</em>'.
	 * @see no.hal.osgi.RequiredExecutionEnvironmentKind
	 * @generated
	 */
	EEnum getRequiredExecutionEnvironmentKind();

	/**
	 * Returns the meta object for enum '{@link no.hal.osgi.ActivationPolicyKind <em>Activation Policy Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Activation Policy Kind</em>'.
	 * @see no.hal.osgi.ActivationPolicyKind
	 * @generated
	 */
	EEnum getActivationPolicyKind();

	/**
	 * Returns the meta object for enum '{@link no.hal.osgi.VisibilityKind <em>Visibility Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Visibility Kind</em>'.
	 * @see no.hal.osgi.VisibilityKind
	 * @generated
	 */
	EEnum getVisibilityKind();

	/**
	 * Returns the meta object for data type '{@link no.hal.osgi.emf.util.QualifiedName <em>Qualified Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Qualified Name</em>'.
	 * @see no.hal.osgi.emf.util.QualifiedName
	 * @model instanceClass="no.hal.osgi.emf.util.QualifiedName"
	 * @generated
	 */
	EDataType getQualifiedName();

	/**
	 * Returns the meta object for data type '{@link no.hal.osgi.emf.util.Path <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Path</em>'.
	 * @see no.hal.osgi.emf.util.Path
	 * @model instanceClass="no.hal.osgi.emf.util.Path"
	 * @generated
	 */
	EDataType getPath();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	OsgiFactory getOsgiFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link no.hal.osgi.impl.ManifestImpl <em>Manifest</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.osgi.impl.ManifestImpl
		 * @see no.hal.osgi.impl.OsgiPackageImpl#getManifest()
		 * @generated
		 */
		EClass MANIFEST = eINSTANCE.getManifest();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANIFEST__VERSION = eINSTANCE.getManifest_Version();

		/**
		 * The meta object literal for the '{@link no.hal.osgi.impl.GenericAttributesContainerImpl <em>Generic Attributes Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.osgi.impl.GenericAttributesContainerImpl
		 * @see no.hal.osgi.impl.OsgiPackageImpl#getGenericAttributesContainer()
		 * @generated
		 */
		EClass GENERIC_ATTRIBUTES_CONTAINER = eINSTANCE.getGenericAttributesContainer();

		/**
		 * The meta object literal for the '<em><b>Generic Attributes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERIC_ATTRIBUTES_CONTAINER__GENERIC_ATTRIBUTES = eINSTANCE.getGenericAttributesContainer_GenericAttributes();

		/**
		 * The meta object literal for the '{@link no.hal.osgi.impl.GenericAttributeImpl <em>Generic Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.osgi.impl.GenericAttributeImpl
		 * @see no.hal.osgi.impl.OsgiPackageImpl#getGenericAttribute()
		 * @generated
		 */
		EClass GENERIC_ATTRIBUTE = eINSTANCE.getGenericAttribute();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERIC_ATTRIBUTE__NAME = eINSTANCE.getGenericAttribute_Name();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERIC_ATTRIBUTE__VALUES = eINSTANCE.getGenericAttribute_Values();

		/**
		 * The meta object literal for the '{@link no.hal.osgi.impl.GenericAttributeValueImpl <em>Generic Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.osgi.impl.GenericAttributeValueImpl
		 * @see no.hal.osgi.impl.OsgiPackageImpl#getGenericAttributeValue()
		 * @generated
		 */
		EClass GENERIC_ATTRIBUTE_VALUE = eINSTANCE.getGenericAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERIC_ATTRIBUTE_VALUE__VALUE = eINSTANCE.getGenericAttributeValue_Value();

		/**
		 * The meta object literal for the '<em><b>Extra Attributes</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERIC_ATTRIBUTE_VALUE__EXTRA_ATTRIBUTES = eINSTANCE.getGenericAttributeValue_ExtraAttributes();

		/**
		 * The meta object literal for the '<em>Version</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.osgi.emf.util.Version
		 * @see no.hal.osgi.impl.OsgiPackageImpl#getVersion()
		 * @generated
		 */
		EDataType VERSION = eINSTANCE.getVersion();

		/**
		 * The meta object literal for the '<em>Version Range</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.osgi.emf.util.VersionRange
		 * @see no.hal.osgi.impl.OsgiPackageImpl#getVersionRange()
		 * @generated
		 */
		EDataType VERSION_RANGE = eINSTANCE.getVersionRange();

		/**
		 * The meta object literal for the '{@link no.hal.osgi.impl.BundleImpl <em>Bundle</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.osgi.impl.BundleImpl
		 * @see no.hal.osgi.impl.OsgiPackageImpl#getBundle()
		 * @generated
		 */
		EClass BUNDLE = eINSTANCE.getBundle();

		/**
		 * The meta object literal for the '<em><b>Manifest Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUNDLE__MANIFEST_VERSION = eINSTANCE.getBundle_ManifestVersion();

		/**
		 * The meta object literal for the '<em><b>Symbolic Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUNDLE__SYMBOLIC_NAME = eINSTANCE.getBundle_SymbolicName();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUNDLE__NAME = eINSTANCE.getBundle_Name();

		/**
		 * The meta object literal for the '<em><b>Singleton</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUNDLE__SINGLETON = eINSTANCE.getBundle_Singleton();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUNDLE__VERSION = eINSTANCE.getBundle_Version();

		/**
		 * The meta object literal for the '<em><b>Vendor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUNDLE__VENDOR = eINSTANCE.getBundle_Vendor();

		/**
		 * The meta object literal for the '<em><b>Activator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUNDLE__ACTIVATOR = eINSTANCE.getBundle_Activator();

		/**
		 * The meta object literal for the '<em><b>Class Path</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUNDLE__CLASS_PATH = eINSTANCE.getBundle_ClassPath();

		/**
		 * The meta object literal for the '<em><b>Required Execution Environment</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUNDLE__REQUIRED_EXECUTION_ENVIRONMENT = eINSTANCE.getBundle_RequiredExecutionEnvironment();

		/**
		 * The meta object literal for the '<em><b>Activation Policy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUNDLE__ACTIVATION_POLICY = eINSTANCE.getBundle_ActivationPolicy();

		/**
		 * The meta object literal for the '<em><b>Export Package</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BUNDLE__EXPORT_PACKAGE = eINSTANCE.getBundle_ExportPackage();

		/**
		 * The meta object literal for the '<em><b>Import Package</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BUNDLE__IMPORT_PACKAGE = eINSTANCE.getBundle_ImportPackage();

		/**
		 * The meta object literal for the '<em><b>Require Bundle</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BUNDLE__REQUIRE_BUNDLE = eINSTANCE.getBundle_RequireBundle();

		/**
		 * The meta object literal for the '<em><b>Service Component</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BUNDLE__SERVICE_COMPONENT = eINSTANCE.getBundle_ServiceComponent();

		/**
		 * The meta object literal for the '{@link no.hal.osgi.impl.RequiredBundleImpl <em>Required Bundle</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.osgi.impl.RequiredBundleImpl
		 * @see no.hal.osgi.impl.OsgiPackageImpl#getRequiredBundle()
		 * @generated
		 */
		EClass REQUIRED_BUNDLE = eINSTANCE.getRequiredBundle();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIRED_BUNDLE__NAME = eINSTANCE.getRequiredBundle_Name();

		/**
		 * The meta object literal for the '<em><b>Bundle Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIRED_BUNDLE__BUNDLE_VERSION = eINSTANCE.getRequiredBundle_BundleVersion();

		/**
		 * The meta object literal for the '<em><b>Optional</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIRED_BUNDLE__OPTIONAL = eINSTANCE.getRequiredBundle_Optional();

		/**
		 * The meta object literal for the '<em><b>Visibility</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIRED_BUNDLE__VISIBILITY = eINSTANCE.getRequiredBundle_Visibility();

		/**
		 * The meta object literal for the '<em><b>Resolves To</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REQUIRED_BUNDLE__RESOLVES_TO = eINSTANCE.getRequiredBundle_ResolvesTo();

		/**
		 * The meta object literal for the '{@link no.hal.osgi.impl.BundlePackageImpl <em>Bundle Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.osgi.impl.BundlePackageImpl
		 * @see no.hal.osgi.impl.OsgiPackageImpl#getBundlePackage()
		 * @generated
		 */
		EClass BUNDLE_PACKAGE = eINSTANCE.getBundlePackage();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUNDLE_PACKAGE__NAME = eINSTANCE.getBundlePackage_Name();

		/**
		 * The meta object literal for the '{@link no.hal.osgi.impl.ExportedPackageImpl <em>Exported Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.osgi.impl.ExportedPackageImpl
		 * @see no.hal.osgi.impl.OsgiPackageImpl#getExportedPackage()
		 * @generated
		 */
		EClass EXPORTED_PACKAGE = eINSTANCE.getExportedPackage();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPORTED_PACKAGE__VERSION = eINSTANCE.getExportedPackage_Version();

		/**
		 * The meta object literal for the '{@link no.hal.osgi.impl.ImportedPackageImpl <em>Imported Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.osgi.impl.ImportedPackageImpl
		 * @see no.hal.osgi.impl.OsgiPackageImpl#getImportedPackage()
		 * @generated
		 */
		EClass IMPORTED_PACKAGE = eINSTANCE.getImportedPackage();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMPORTED_PACKAGE__VERSION = eINSTANCE.getImportedPackage_Version();

		/**
		 * The meta object literal for the '<em><b>Resolves To</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPORTED_PACKAGE__RESOLVES_TO = eINSTANCE.getImportedPackage_ResolvesTo();

		/**
		 * The meta object literal for the '{@link no.hal.osgi.impl.ServiceComponentImpl <em>Service Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.osgi.impl.ServiceComponentImpl
		 * @see no.hal.osgi.impl.OsgiPackageImpl#getServiceComponent()
		 * @generated
		 */
		EClass SERVICE_COMPONENT = eINSTANCE.getServiceComponent();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE_COMPONENT__PATH = eINSTANCE.getServiceComponent_Path();

		/**
		 * The meta object literal for the '<em><b>Component</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE_COMPONENT__COMPONENT = eINSTANCE.getServiceComponent_Component();

		/**
		 * The meta object literal for the '{@link no.hal.osgi.RequiredExecutionEnvironmentKind <em>Required Execution Environment Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.osgi.RequiredExecutionEnvironmentKind
		 * @see no.hal.osgi.impl.OsgiPackageImpl#getRequiredExecutionEnvironmentKind()
		 * @generated
		 */
		EEnum REQUIRED_EXECUTION_ENVIRONMENT_KIND = eINSTANCE.getRequiredExecutionEnvironmentKind();

		/**
		 * The meta object literal for the '{@link no.hal.osgi.ActivationPolicyKind <em>Activation Policy Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.osgi.ActivationPolicyKind
		 * @see no.hal.osgi.impl.OsgiPackageImpl#getActivationPolicyKind()
		 * @generated
		 */
		EEnum ACTIVATION_POLICY_KIND = eINSTANCE.getActivationPolicyKind();

		/**
		 * The meta object literal for the '{@link no.hal.osgi.VisibilityKind <em>Visibility Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.osgi.VisibilityKind
		 * @see no.hal.osgi.impl.OsgiPackageImpl#getVisibilityKind()
		 * @generated
		 */
		EEnum VISIBILITY_KIND = eINSTANCE.getVisibilityKind();

		/**
		 * The meta object literal for the '<em>Qualified Name</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.osgi.emf.util.QualifiedName
		 * @see no.hal.osgi.impl.OsgiPackageImpl#getQualifiedName()
		 * @generated
		 */
		EDataType QUALIFIED_NAME = eINSTANCE.getQualifiedName();

		/**
		 * The meta object literal for the '<em>Path</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see no.hal.osgi.emf.util.Path
		 * @see no.hal.osgi.impl.OsgiPackageImpl#getPath()
		 * @generated
		 */
		EDataType PATH = eINSTANCE.getPath();

	}

} //OsgiPackage
