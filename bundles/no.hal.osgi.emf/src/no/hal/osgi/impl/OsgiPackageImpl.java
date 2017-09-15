/**
 */
package no.hal.osgi.impl;

import no.hal.osgi.ActivationPolicyKind;
import no.hal.osgi.Bundle;
import no.hal.osgi.BundlePackage;
import no.hal.osgi.ExportedPackage;
import no.hal.osgi.GenericAttribute;
import no.hal.osgi.GenericAttributeValue;
import no.hal.osgi.GenericAttributesContainer;
import no.hal.osgi.ImportedPackage;
import no.hal.osgi.Manifest;
import no.hal.osgi.OsgiFactory;
import no.hal.osgi.OsgiPackage;
import no.hal.osgi.RequiredBundle;
import no.hal.osgi.RequiredExecutionEnvironmentKind;
import no.hal.osgi.ServiceComponent;
import no.hal.osgi.VisibilityKind;
import no.hal.osgi.emf.util.Path;
import no.hal.osgi.emf.util.QualifiedName;
import no.hal.osgi.emf.util.Version;
import no.hal.osgi.emf.util.VersionRange;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OsgiPackageImpl extends EPackageImpl implements OsgiPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass manifestEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass genericAttributesContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass genericAttributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass genericAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bundleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass requiredBundleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bundlePackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exportedPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass importedPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass serviceComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum requiredExecutionEnvironmentKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum activationPolicyKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum visibilityKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType versionEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType versionRangeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType qualifiedNameEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType pathEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see no.hal.osgi.OsgiPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private OsgiPackageImpl() {
		super(eNS_URI, OsgiFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link OsgiPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static OsgiPackage init() {
		if (isInited) return (OsgiPackage)EPackage.Registry.INSTANCE.getEPackage(OsgiPackage.eNS_URI);

		// Obtain or create and register package
		OsgiPackageImpl theOsgiPackage = (OsgiPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof OsgiPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new OsgiPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		org.osgi.scr.ScrPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theOsgiPackage.createPackageContents();

		// Initialize created meta-data
		theOsgiPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theOsgiPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(OsgiPackage.eNS_URI, theOsgiPackage);
		return theOsgiPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getManifest() {
		return manifestEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getManifest_Version() {
		return (EAttribute)manifestEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGenericAttributesContainer() {
		return genericAttributesContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGenericAttributesContainer_GenericAttributes() {
		return (EReference)genericAttributesContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGenericAttribute() {
		return genericAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGenericAttribute_Name() {
		return (EAttribute)genericAttributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGenericAttribute_Values() {
		return (EReference)genericAttributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGenericAttributeValue() {
		return genericAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGenericAttributeValue_Value() {
		return (EAttribute)genericAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGenericAttributeValue_ExtraAttributes() {
		return (EAttribute)genericAttributeValueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getVersion() {
		return versionEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getVersionRange() {
		return versionRangeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBundle() {
		return bundleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBundle_ManifestVersion() {
		return (EAttribute)bundleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBundle_SymbolicName() {
		return (EAttribute)bundleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBundle_Name() {
		return (EAttribute)bundleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBundle_Singleton() {
		return (EAttribute)bundleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBundle_Version() {
		return (EAttribute)bundleEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBundle_Vendor() {
		return (EAttribute)bundleEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBundle_Activator() {
		return (EAttribute)bundleEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBundle_ClassPath() {
		return (EAttribute)bundleEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBundle_RequiredExecutionEnvironment() {
		return (EAttribute)bundleEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBundle_ActivationPolicy() {
		return (EAttribute)bundleEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBundle_ExportPackage() {
		return (EReference)bundleEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBundle_ImportPackage() {
		return (EReference)bundleEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBundle_RequireBundle() {
		return (EReference)bundleEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBundle_ServiceComponent() {
		return (EReference)bundleEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRequiredBundle() {
		return requiredBundleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRequiredBundle_Name() {
		return (EAttribute)requiredBundleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRequiredBundle_BundleVersion() {
		return (EAttribute)requiredBundleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRequiredBundle_Optional() {
		return (EAttribute)requiredBundleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRequiredBundle_Visibility() {
		return (EAttribute)requiredBundleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRequiredBundle_ResolvesTo() {
		return (EReference)requiredBundleEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBundlePackage() {
		return bundlePackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBundlePackage_Name() {
		return (EAttribute)bundlePackageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExportedPackage() {
		return exportedPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExportedPackage_Version() {
		return (EAttribute)exportedPackageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImportedPackage() {
		return importedPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImportedPackage_Version() {
		return (EAttribute)importedPackageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImportedPackage_ResolvesTo() {
		return (EReference)importedPackageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getServiceComponent() {
		return serviceComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceComponent_Path() {
		return (EAttribute)serviceComponentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceComponent_Component() {
		return (EReference)serviceComponentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getRequiredExecutionEnvironmentKind() {
		return requiredExecutionEnvironmentKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getActivationPolicyKind() {
		return activationPolicyKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getVisibilityKind() {
		return visibilityKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getQualifiedName() {
		return qualifiedNameEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getPath() {
		return pathEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OsgiFactory getOsgiFactory() {
		return (OsgiFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		manifestEClass = createEClass(MANIFEST);
		createEAttribute(manifestEClass, MANIFEST__VERSION);

		genericAttributesContainerEClass = createEClass(GENERIC_ATTRIBUTES_CONTAINER);
		createEReference(genericAttributesContainerEClass, GENERIC_ATTRIBUTES_CONTAINER__GENERIC_ATTRIBUTES);

		genericAttributeEClass = createEClass(GENERIC_ATTRIBUTE);
		createEAttribute(genericAttributeEClass, GENERIC_ATTRIBUTE__NAME);
		createEReference(genericAttributeEClass, GENERIC_ATTRIBUTE__VALUES);

		genericAttributeValueEClass = createEClass(GENERIC_ATTRIBUTE_VALUE);
		createEAttribute(genericAttributeValueEClass, GENERIC_ATTRIBUTE_VALUE__VALUE);
		createEAttribute(genericAttributeValueEClass, GENERIC_ATTRIBUTE_VALUE__EXTRA_ATTRIBUTES);

		bundleEClass = createEClass(BUNDLE);
		createEAttribute(bundleEClass, BUNDLE__MANIFEST_VERSION);
		createEAttribute(bundleEClass, BUNDLE__SYMBOLIC_NAME);
		createEAttribute(bundleEClass, BUNDLE__NAME);
		createEAttribute(bundleEClass, BUNDLE__SINGLETON);
		createEAttribute(bundleEClass, BUNDLE__VERSION);
		createEAttribute(bundleEClass, BUNDLE__VENDOR);
		createEAttribute(bundleEClass, BUNDLE__ACTIVATOR);
		createEAttribute(bundleEClass, BUNDLE__CLASS_PATH);
		createEAttribute(bundleEClass, BUNDLE__REQUIRED_EXECUTION_ENVIRONMENT);
		createEAttribute(bundleEClass, BUNDLE__ACTIVATION_POLICY);
		createEReference(bundleEClass, BUNDLE__EXPORT_PACKAGE);
		createEReference(bundleEClass, BUNDLE__IMPORT_PACKAGE);
		createEReference(bundleEClass, BUNDLE__REQUIRE_BUNDLE);
		createEReference(bundleEClass, BUNDLE__SERVICE_COMPONENT);

		requiredBundleEClass = createEClass(REQUIRED_BUNDLE);
		createEAttribute(requiredBundleEClass, REQUIRED_BUNDLE__NAME);
		createEAttribute(requiredBundleEClass, REQUIRED_BUNDLE__BUNDLE_VERSION);
		createEAttribute(requiredBundleEClass, REQUIRED_BUNDLE__OPTIONAL);
		createEAttribute(requiredBundleEClass, REQUIRED_BUNDLE__VISIBILITY);
		createEReference(requiredBundleEClass, REQUIRED_BUNDLE__RESOLVES_TO);

		bundlePackageEClass = createEClass(BUNDLE_PACKAGE);
		createEAttribute(bundlePackageEClass, BUNDLE_PACKAGE__NAME);

		exportedPackageEClass = createEClass(EXPORTED_PACKAGE);
		createEAttribute(exportedPackageEClass, EXPORTED_PACKAGE__VERSION);

		importedPackageEClass = createEClass(IMPORTED_PACKAGE);
		createEAttribute(importedPackageEClass, IMPORTED_PACKAGE__VERSION);
		createEReference(importedPackageEClass, IMPORTED_PACKAGE__RESOLVES_TO);

		serviceComponentEClass = createEClass(SERVICE_COMPONENT);
		createEAttribute(serviceComponentEClass, SERVICE_COMPONENT__PATH);
		createEReference(serviceComponentEClass, SERVICE_COMPONENT__COMPONENT);

		// Create enums
		requiredExecutionEnvironmentKindEEnum = createEEnum(REQUIRED_EXECUTION_ENVIRONMENT_KIND);
		activationPolicyKindEEnum = createEEnum(ACTIVATION_POLICY_KIND);
		visibilityKindEEnum = createEEnum(VISIBILITY_KIND);

		// Create data types
		versionEDataType = createEDataType(VERSION);
		versionRangeEDataType = createEDataType(VERSION_RANGE);
		qualifiedNameEDataType = createEDataType(QUALIFIED_NAME);
		pathEDataType = createEDataType(PATH);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		org.osgi.scr.ScrPackage theScrPackage = (org.osgi.scr.ScrPackage)EPackage.Registry.INSTANCE.getEPackage(org.osgi.scr.ScrPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		manifestEClass.getESuperTypes().add(this.getGenericAttributesContainer());
		bundleEClass.getESuperTypes().add(this.getGenericAttributesContainer());
		exportedPackageEClass.getESuperTypes().add(this.getBundlePackage());
		importedPackageEClass.getESuperTypes().add(this.getBundlePackage());

		// Initialize classes, features, and operations; add parameters
		initEClass(manifestEClass, Manifest.class, "Manifest", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getManifest_Version(), this.getVersion(), "version", null, 0, 1, Manifest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(genericAttributesContainerEClass, GenericAttributesContainer.class, "GenericAttributesContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGenericAttributesContainer_GenericAttributes(), this.getGenericAttribute(), null, "genericAttributes", null, 0, -1, GenericAttributesContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(genericAttributeEClass, GenericAttribute.class, "GenericAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGenericAttribute_Name(), ecorePackage.getEString(), "name", null, 0, 1, GenericAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGenericAttribute_Values(), this.getGenericAttributeValue(), null, "values", null, 0, -1, GenericAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(genericAttributeValueEClass, GenericAttributeValue.class, "GenericAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGenericAttributeValue_Value(), ecorePackage.getEString(), "value", null, 0, 1, GenericAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGenericAttributeValue_ExtraAttributes(), ecorePackage.getEString(), "extraAttributes", null, 0, -1, GenericAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(bundleEClass, Bundle.class, "Bundle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBundle_ManifestVersion(), this.getVersion(), "manifestVersion", null, 0, 1, Bundle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBundle_SymbolicName(), this.getQualifiedName(), "symbolicName", null, 0, 1, Bundle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBundle_Name(), ecorePackage.getEString(), "name", null, 0, 1, Bundle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBundle_Singleton(), ecorePackage.getEBoolean(), "singleton", null, 0, 1, Bundle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBundle_Version(), this.getVersion(), "version", null, 0, 1, Bundle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBundle_Vendor(), ecorePackage.getEString(), "vendor", null, 0, 1, Bundle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBundle_Activator(), this.getQualifiedName(), "activator", null, 0, 1, Bundle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBundle_ClassPath(), ecorePackage.getEString(), "classPath", null, 0, -1, Bundle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBundle_RequiredExecutionEnvironment(), this.getRequiredExecutionEnvironmentKind(), "requiredExecutionEnvironment", null, 0, -1, Bundle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBundle_ActivationPolicy(), this.getActivationPolicyKind(), "activationPolicy", null, 0, 1, Bundle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBundle_ExportPackage(), this.getExportedPackage(), null, "exportPackage", null, 0, -1, Bundle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBundle_ImportPackage(), this.getImportedPackage(), null, "importPackage", null, 0, -1, Bundle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBundle_RequireBundle(), this.getRequiredBundle(), null, "requireBundle", null, 0, -1, Bundle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBundle_ServiceComponent(), this.getServiceComponent(), null, "serviceComponent", null, 0, -1, Bundle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(requiredBundleEClass, RequiredBundle.class, "RequiredBundle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRequiredBundle_Name(), this.getQualifiedName(), "name", null, 0, 1, RequiredBundle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRequiredBundle_BundleVersion(), this.getVersionRange(), "bundleVersion", null, 0, 1, RequiredBundle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRequiredBundle_Optional(), ecorePackage.getEBoolean(), "optional", null, 0, 1, RequiredBundle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRequiredBundle_Visibility(), this.getVisibilityKind(), "visibility", null, 0, 1, RequiredBundle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRequiredBundle_ResolvesTo(), this.getBundle(), null, "resolvesTo", null, 0, -1, RequiredBundle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(bundlePackageEClass, BundlePackage.class, "BundlePackage", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBundlePackage_Name(), this.getQualifiedName(), "name", null, 0, 1, BundlePackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(exportedPackageEClass, ExportedPackage.class, "ExportedPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExportedPackage_Version(), this.getVersion(), "version", null, 0, 1, ExportedPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(importedPackageEClass, ImportedPackage.class, "ImportedPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getImportedPackage_Version(), this.getVersionRange(), "version", null, 0, 1, ImportedPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getImportedPackage_ResolvesTo(), this.getBundle(), null, "resolvesTo", null, 0, -1, ImportedPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(serviceComponentEClass, ServiceComponent.class, "ServiceComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getServiceComponent_Path(), this.getPath(), "path", null, 0, 1, ServiceComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getServiceComponent_Component(), theScrPackage.getComponent(), null, "component", null, 0, 1, ServiceComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(requiredExecutionEnvironmentKindEEnum, RequiredExecutionEnvironmentKind.class, "RequiredExecutionEnvironmentKind");
		addEEnumLiteral(requiredExecutionEnvironmentKindEEnum, RequiredExecutionEnvironmentKind.DEFAULT);
		addEEnumLiteral(requiredExecutionEnvironmentKindEEnum, RequiredExecutionEnvironmentKind.JAVA4);
		addEEnumLiteral(requiredExecutionEnvironmentKindEEnum, RequiredExecutionEnvironmentKind.JAVA5);
		addEEnumLiteral(requiredExecutionEnvironmentKindEEnum, RequiredExecutionEnvironmentKind.JAVA6);
		addEEnumLiteral(requiredExecutionEnvironmentKindEEnum, RequiredExecutionEnvironmentKind.JAVA7);
		addEEnumLiteral(requiredExecutionEnvironmentKindEEnum, RequiredExecutionEnvironmentKind.JAVA8);

		initEEnum(activationPolicyKindEEnum, ActivationPolicyKind.class, "ActivationPolicyKind");
		addEEnumLiteral(activationPolicyKindEEnum, ActivationPolicyKind.DEFAULT);
		addEEnumLiteral(activationPolicyKindEEnum, ActivationPolicyKind.LAZY);

		initEEnum(visibilityKindEEnum, VisibilityKind.class, "VisibilityKind");
		addEEnumLiteral(visibilityKindEEnum, VisibilityKind.DEFAULT);
		addEEnumLiteral(visibilityKindEEnum, VisibilityKind.REEXPORT);

		// Initialize data types
		initEDataType(versionEDataType, Version.class, "Version", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(versionRangeEDataType, VersionRange.class, "VersionRange", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(qualifiedNameEDataType, QualifiedName.class, "QualifiedName", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(pathEDataType, Path.class, "Path", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //OsgiPackageImpl
