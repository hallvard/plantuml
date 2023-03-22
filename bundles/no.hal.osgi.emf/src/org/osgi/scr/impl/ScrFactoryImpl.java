/**
 */
package org.osgi.scr.impl;

import org.eclipse.emf.common.util.Enumerator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.osgi.scr.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ScrFactoryImpl extends EFactoryImpl implements ScrFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ScrFactory init() {
		try {
			ScrFactory theScrFactory = (ScrFactory)EPackage.Registry.INSTANCE.getEFactory(ScrPackage.eNS_URI);
			if (theScrFactory != null) {
				return theScrFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ScrFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScrFactoryImpl() {
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
			case ScrPackage.DOCUMENT_ROOT: return createDocumentRoot();
			case ScrPackage.COMPONENT: return createComponent();
			case ScrPackage.IMPLEMENTATION: return createImplementation();
			case ScrPackage.PROPERTIES: return createProperties();
			case ScrPackage.PROPERTY: return createProperty();
			case ScrPackage.PROVIDE: return createProvide();
			case ScrPackage.REFERENCE: return createReference();
			case ScrPackage.SERVICE: return createService();
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
			case ScrPackage.CARDINALITY:
				return createCardinalityFromString(eDataType, initialValue);
			case ScrPackage.CONFIGURATION_POLICY:
				return createConfigurationPolicyFromString(eDataType, initialValue);
			case ScrPackage.JAVA_TYPES:
				return createJavaTypesFromString(eDataType, initialValue);
			case ScrPackage.POLICY:
				return createPolicyFromString(eDataType, initialValue);
			case ScrPackage.POLICY_OPTION:
				return createPolicyOptionFromString(eDataType, initialValue);
			case ScrPackage.CARDINALITY_OBJECT:
				return createCardinalityObjectFromString(eDataType, initialValue);
			case ScrPackage.CONFIGURATION_POLICY_OBJECT:
				return createConfigurationPolicyObjectFromString(eDataType, initialValue);
			case ScrPackage.JAVA_TYPES_OBJECT:
				return createJavaTypesObjectFromString(eDataType, initialValue);
			case ScrPackage.POLICY_OBJECT:
				return createPolicyObjectFromString(eDataType, initialValue);
			case ScrPackage.POLICY_OPTION_OBJECT:
				return createPolicyOptionObjectFromString(eDataType, initialValue);
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
			case ScrPackage.CARDINALITY:
				return convertCardinalityToString(eDataType, instanceValue);
			case ScrPackage.CONFIGURATION_POLICY:
				return convertConfigurationPolicyToString(eDataType, instanceValue);
			case ScrPackage.JAVA_TYPES:
				return convertJavaTypesToString(eDataType, instanceValue);
			case ScrPackage.POLICY:
				return convertPolicyToString(eDataType, instanceValue);
			case ScrPackage.POLICY_OPTION:
				return convertPolicyOptionToString(eDataType, instanceValue);
			case ScrPackage.CARDINALITY_OBJECT:
				return convertCardinalityObjectToString(eDataType, instanceValue);
			case ScrPackage.CONFIGURATION_POLICY_OBJECT:
				return convertConfigurationPolicyObjectToString(eDataType, instanceValue);
			case ScrPackage.JAVA_TYPES_OBJECT:
				return convertJavaTypesObjectToString(eDataType, instanceValue);
			case ScrPackage.POLICY_OBJECT:
				return convertPolicyObjectToString(eDataType, instanceValue);
			case ScrPackage.POLICY_OPTION_OBJECT:
				return convertPolicyOptionObjectToString(eDataType, instanceValue);
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
	public DocumentRoot createDocumentRoot() {
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Component createComponent() {
		ComponentImpl component = new ComponentImpl();
		return component;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Implementation createImplementation() {
		ImplementationImpl implementation = new ImplementationImpl();
		return implementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Properties createProperties() {
		PropertiesImpl properties = new PropertiesImpl();
		return properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Property createProperty() {
		PropertyImpl property = new PropertyImpl();
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Provide createProvide() {
		ProvideImpl provide = new ProvideImpl();
		return provide;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Reference createReference() {
		ReferenceImpl reference = new ReferenceImpl();
		return reference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Service createService() {
		ServiceImpl service = new ServiceImpl();
		return service;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Cardinality createCardinalityFromString(EDataType eDataType, String initialValue) {
		Cardinality result = Cardinality.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCardinalityToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConfigurationPolicy createConfigurationPolicyFromString(EDataType eDataType, String initialValue) {
		ConfigurationPolicy result = ConfigurationPolicy.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertConfigurationPolicyToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaTypes createJavaTypesFromString(EDataType eDataType, String initialValue) {
		JavaTypes result = JavaTypes.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertJavaTypesToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Policy createPolicyFromString(EDataType eDataType, String initialValue) {
		Policy result = Policy.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPolicyToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PolicyOption createPolicyOptionFromString(EDataType eDataType, String initialValue) {
		PolicyOption result = PolicyOption.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPolicyOptionToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Enumerator createCardinalityObjectFromString(EDataType eDataType, String initialValue) {
		return (Enumerator)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCardinalityObjectToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Enumerator createConfigurationPolicyObjectFromString(EDataType eDataType, String initialValue) {
		return (Enumerator)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertConfigurationPolicyObjectToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaTypes createJavaTypesObjectFromString(EDataType eDataType, String initialValue) {
		return createJavaTypesFromString(ScrPackage.Literals.JAVA_TYPES, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertJavaTypesObjectToString(EDataType eDataType, Object instanceValue) {
		return convertJavaTypesToString(ScrPackage.Literals.JAVA_TYPES, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Policy createPolicyObjectFromString(EDataType eDataType, String initialValue) {
		return createPolicyFromString(ScrPackage.Literals.POLICY, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPolicyObjectToString(EDataType eDataType, Object instanceValue) {
		return convertPolicyToString(ScrPackage.Literals.POLICY, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PolicyOption createPolicyOptionObjectFromString(EDataType eDataType, String initialValue) {
		return createPolicyOptionFromString(ScrPackage.Literals.POLICY_OPTION, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPolicyOptionObjectToString(EDataType eDataType, Object instanceValue) {
		return convertPolicyOptionToString(ScrPackage.Literals.POLICY_OPTION, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScrPackage getScrPackage() {
		return (ScrPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ScrPackage getPackage() {
		return ScrPackage.eINSTANCE;
	}

} //ScrFactoryImpl
