/**
 */
package no.hal.osgi.impl;

import no.hal.osgi.*;
import no.hal.osgi.emf.util.Path;
import no.hal.osgi.emf.util.QualifiedName;
import no.hal.osgi.emf.util.Version;
import no.hal.osgi.emf.util.VersionRange;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OsgiFactoryImpl extends EFactoryImpl implements OsgiFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OsgiFactory init() {
		try {
			OsgiFactory theOsgiFactory = (OsgiFactory)EPackage.Registry.INSTANCE.getEFactory(OsgiPackage.eNS_URI);
			if (theOsgiFactory != null) {
				return theOsgiFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new OsgiFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OsgiFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case OsgiPackage.MANIFEST: return createManifest();
			case OsgiPackage.GENERIC_ATTRIBUTES_CONTAINER: return createGenericAttributesContainer();
			case OsgiPackage.GENERIC_ATTRIBUTE: return createGenericAttribute();
			case OsgiPackage.GENERIC_ATTRIBUTE_VALUE: return createGenericAttributeValue();
			case OsgiPackage.BUNDLE: return createBundle();
			case OsgiPackage.REQUIRED_BUNDLE: return createRequiredBundle();
			case OsgiPackage.EXPORTED_PACKAGE: return createExportedPackage();
			case OsgiPackage.IMPORTED_PACKAGE: return createImportedPackage();
			case OsgiPackage.SERVICE_COMPONENT: return createServiceComponent();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case OsgiPackage.REQUIRED_EXECUTION_ENVIRONMENT_KIND:
				return createRequiredExecutionEnvironmentKindFromString(eDataType, initialValue);
			case OsgiPackage.ACTIVATION_POLICY_KIND:
				return createActivationPolicyKindFromString(eDataType, initialValue);
			case OsgiPackage.VISIBILITY_KIND:
				return createVisibilityKindFromString(eDataType, initialValue);
			case OsgiPackage.VERSION:
				return createVersionFromString(eDataType, initialValue);
			case OsgiPackage.VERSION_RANGE:
				return createVersionRangeFromString(eDataType, initialValue);
			case OsgiPackage.QUALIFIED_NAME:
				return createQualifiedNameFromString(eDataType, initialValue);
			case OsgiPackage.PATH:
				return createPathFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case OsgiPackage.REQUIRED_EXECUTION_ENVIRONMENT_KIND:
				return convertRequiredExecutionEnvironmentKindToString(eDataType, instanceValue);
			case OsgiPackage.ACTIVATION_POLICY_KIND:
				return convertActivationPolicyKindToString(eDataType, instanceValue);
			case OsgiPackage.VISIBILITY_KIND:
				return convertVisibilityKindToString(eDataType, instanceValue);
			case OsgiPackage.VERSION:
				return convertVersionToString(eDataType, instanceValue);
			case OsgiPackage.VERSION_RANGE:
				return convertVersionRangeToString(eDataType, instanceValue);
			case OsgiPackage.QUALIFIED_NAME:
				return convertQualifiedNameToString(eDataType, instanceValue);
			case OsgiPackage.PATH:
				return convertPathToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Manifest createManifest() {
		ManifestImpl manifest = new ManifestImpl();
		return manifest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericAttributesContainer createGenericAttributesContainer() {
		GenericAttributesContainerImpl genericAttributesContainer = new GenericAttributesContainerImpl();
		return genericAttributesContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericAttribute createGenericAttribute() {
		GenericAttributeImpl genericAttribute = new GenericAttributeImpl();
		return genericAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericAttributeValue createGenericAttributeValue() {
		GenericAttributeValueImpl genericAttributeValue = new GenericAttributeValueImpl();
		return genericAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Bundle createBundle() {
		BundleImpl bundle = new BundleImpl();
		return bundle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequiredBundle createRequiredBundle() {
		RequiredBundleImpl requiredBundle = new RequiredBundleImpl();
		return requiredBundle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExportedPackage createExportedPackage() {
		ExportedPackageImpl exportedPackage = new ExportedPackageImpl();
		return exportedPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImportedPackage createImportedPackage() {
		ImportedPackageImpl importedPackage = new ImportedPackageImpl();
		return importedPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceComponent createServiceComponent() {
		ServiceComponentImpl serviceComponent = new ServiceComponentImpl();
		return serviceComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequiredExecutionEnvironmentKind createRequiredExecutionEnvironmentKindFromString(EDataType eDataType, String initialValue) {
		RequiredExecutionEnvironmentKind result = RequiredExecutionEnvironmentKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRequiredExecutionEnvironmentKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivationPolicyKind createActivationPolicyKindFromString(EDataType eDataType, String initialValue) {
		ActivationPolicyKind result = ActivationPolicyKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertActivationPolicyKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VisibilityKind createVisibilityKindFromString(EDataType eDataType, String initialValue) {
		VisibilityKind result = VisibilityKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertVisibilityKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Version createVersionFromString(EDataType eDataType, String initialValue) {
		return (Version)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertVersionToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VersionRange createVersionRangeFromString(EDataType eDataType, String initialValue) {
		return (VersionRange)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertVersionRangeToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QualifiedName createQualifiedNameFromString(EDataType eDataType, String initialValue) {
		return (QualifiedName)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertQualifiedNameToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Path createPathFromString(EDataType eDataType, String initialValue) {
		return (Path)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPathToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OsgiPackage getOsgiPackage() {
		return (OsgiPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static OsgiPackage getPackage() {
		return OsgiPackage.eINSTANCE;
	}

} //OsgiFactoryImpl
