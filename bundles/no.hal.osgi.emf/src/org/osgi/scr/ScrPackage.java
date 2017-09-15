/**
 */
package org.osgi.scr;

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
 * <!-- begin-model-doc -->
 * 
 *             This is the XML Schema for component descriptions used by
 *             the Service Component Runtime (SCR). Component description
 *             documents may be embedded in other XML documents. SCR will
 *             process all XML documents listed in the Service-Component
 *             manifest header of a bundle. XML documents containing
 *             component descriptions may contain a single, root component
 *             element or one or more component elements embedded in a
 *             larger document. Use of the namespace for component 
 *             descriptions is mandatory. The attributes and subelements 
 *             of a component element are always unqualified.
 *         
 * <!-- end-model-doc -->
 * @see org.osgi.scr.ScrFactory
 * @model kind="package"
 * @generated
 */
public interface ScrPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "scr";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.osgi.org/xmlns/scr/v1.2.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "scr";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ScrPackage eINSTANCE = org.osgi.scr.impl.ScrPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.osgi.scr.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.osgi.scr.impl.DocumentRootImpl
	 * @see org.osgi.scr.impl.ScrPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 0;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Component</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__COMPONENT = 3;

	/**
	 * The feature id for the '<em><b>Must Understand</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MUST_UNDERSTAND = 4;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.osgi.scr.impl.ComponentImpl <em>Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.osgi.scr.impl.ComponentImpl
	 * @see org.osgi.scr.impl.ScrPackageImpl#getComponent()
	 * @generated
	 */
	int COMPONENT = 1;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__PROPERTY = 1;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__PROPERTIES = 2;

	/**
	 * The feature id for the '<em><b>Service</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__SERVICE = 3;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__REFERENCE = 4;

	/**
	 * The feature id for the '<em><b>Implementation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__IMPLEMENTATION = 5;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__ANY = 6;

	/**
	 * The feature id for the '<em><b>Activate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__ACTIVATE = 7;

	/**
	 * The feature id for the '<em><b>Configuration Pid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__CONFIGURATION_PID = 8;

	/**
	 * The feature id for the '<em><b>Configuration Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__CONFIGURATION_POLICY = 9;

	/**
	 * The feature id for the '<em><b>Deactivate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__DEACTIVATE = 10;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__ENABLED = 11;

	/**
	 * The feature id for the '<em><b>Factory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__FACTORY = 12;

	/**
	 * The feature id for the '<em><b>Immediate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__IMMEDIATE = 13;

	/**
	 * The feature id for the '<em><b>Modified</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__MODIFIED = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__NAME = 15;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__ANY_ATTRIBUTE = 16;

	/**
	 * The number of structural features of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_FEATURE_COUNT = 17;

	/**
	 * The number of operations of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.osgi.scr.impl.ImplementationImpl <em>Implementation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.osgi.scr.impl.ImplementationImpl
	 * @see org.osgi.scr.impl.ScrPackageImpl#getImplementation()
	 * @generated
	 */
	int IMPLEMENTATION = 2;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION__ANY = 0;

	/**
	 * The feature id for the '<em><b>Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION__CLASS = 1;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION__ANY_ATTRIBUTE = 2;

	/**
	 * The number of structural features of the '<em>Implementation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Implementation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.osgi.scr.impl.PropertiesImpl <em>Properties</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.osgi.scr.impl.PropertiesImpl
	 * @see org.osgi.scr.impl.ScrPackageImpl#getProperties()
	 * @generated
	 */
	int PROPERTIES = 3;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTIES__ANY = 0;

	/**
	 * The feature id for the '<em><b>Entry</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTIES__ENTRY = 1;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTIES__ANY_ATTRIBUTE = 2;

	/**
	 * The number of structural features of the '<em>Properties</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTIES_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Properties</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTIES_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.osgi.scr.impl.PropertyImpl <em>Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.osgi.scr.impl.PropertyImpl
	 * @see org.osgi.scr.impl.ScrPackageImpl#getProperty()
	 * @generated
	 */
	int PROPERTY = 4;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__NAME = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__TYPE = 2;

	/**
	 * The feature id for the '<em><b>Value1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__VALUE1 = 3;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__ANY_ATTRIBUTE = 4;

	/**
	 * The number of structural features of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.osgi.scr.impl.ProvideImpl <em>Provide</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.osgi.scr.impl.ProvideImpl
	 * @see org.osgi.scr.impl.ScrPackageImpl#getProvide()
	 * @generated
	 */
	int PROVIDE = 5;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDE__ANY = 0;

	/**
	 * The feature id for the '<em><b>Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDE__INTERFACE = 1;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDE__ANY_ATTRIBUTE = 2;

	/**
	 * The number of structural features of the '<em>Provide</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Provide</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.osgi.scr.impl.ReferenceImpl <em>Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.osgi.scr.impl.ReferenceImpl
	 * @see org.osgi.scr.impl.ScrPackageImpl#getReference()
	 * @generated
	 */
	int REFERENCE = 6;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__ANY = 0;

	/**
	 * The feature id for the '<em><b>Bind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__BIND = 1;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__CARDINALITY = 2;

	/**
	 * The feature id for the '<em><b>Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__INTERFACE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__NAME = 4;

	/**
	 * The feature id for the '<em><b>Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__POLICY = 5;

	/**
	 * The feature id for the '<em><b>Policy Option</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__POLICY_OPTION = 6;

	/**
	 * The feature id for the '<em><b>Target</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__TARGET = 7;

	/**
	 * The feature id for the '<em><b>Unbind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__UNBIND = 8;

	/**
	 * The feature id for the '<em><b>Updated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__UPDATED = 9;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__ANY_ATTRIBUTE = 10;

	/**
	 * The number of structural features of the '<em>Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FEATURE_COUNT = 11;

	/**
	 * The number of operations of the '<em>Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.osgi.scr.impl.ServiceImpl <em>Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.osgi.scr.impl.ServiceImpl
	 * @see org.osgi.scr.impl.ScrPackageImpl#getService()
	 * @generated
	 */
	int SERVICE = 7;

	/**
	 * The feature id for the '<em><b>Provide</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__PROVIDE = 0;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__ANY = 1;

	/**
	 * The feature id for the '<em><b>Servicefactory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__SERVICEFACTORY = 2;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__ANY_ATTRIBUTE = 3;

	/**
	 * The number of structural features of the '<em>Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.osgi.scr.Cardinality <em>Cardinality</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.osgi.scr.Cardinality
	 * @see org.osgi.scr.impl.ScrPackageImpl#getCardinality()
	 * @generated
	 */
	int CARDINALITY = 8;

	/**
	 * The meta object id for the '{@link org.osgi.scr.ConfigurationPolicy <em>Configuration Policy</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.osgi.scr.ConfigurationPolicy
	 * @see org.osgi.scr.impl.ScrPackageImpl#getConfigurationPolicy()
	 * @generated
	 */
	int CONFIGURATION_POLICY = 9;

	/**
	 * The meta object id for the '{@link org.osgi.scr.JavaTypes <em>Java Types</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.osgi.scr.JavaTypes
	 * @see org.osgi.scr.impl.ScrPackageImpl#getJavaTypes()
	 * @generated
	 */
	int JAVA_TYPES = 10;

	/**
	 * The meta object id for the '{@link org.osgi.scr.Policy <em>Policy</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.osgi.scr.Policy
	 * @see org.osgi.scr.impl.ScrPackageImpl#getPolicy()
	 * @generated
	 */
	int POLICY = 11;

	/**
	 * The meta object id for the '{@link org.osgi.scr.PolicyOption <em>Policy Option</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.osgi.scr.PolicyOption
	 * @see org.osgi.scr.impl.ScrPackageImpl#getPolicyOption()
	 * @generated
	 */
	int POLICY_OPTION = 12;

	/**
	 * The meta object id for the '<em>Cardinality Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Enumerator
	 * @see org.osgi.scr.impl.ScrPackageImpl#getCardinalityObject()
	 * @generated
	 */
	int CARDINALITY_OBJECT = 13;

	/**
	 * The meta object id for the '<em>Configuration Policy Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Enumerator
	 * @see org.osgi.scr.impl.ScrPackageImpl#getConfigurationPolicyObject()
	 * @generated
	 */
	int CONFIGURATION_POLICY_OBJECT = 14;

	/**
	 * The meta object id for the '<em>Java Types Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.osgi.scr.JavaTypes
	 * @see org.osgi.scr.impl.ScrPackageImpl#getJavaTypesObject()
	 * @generated
	 */
	int JAVA_TYPES_OBJECT = 15;

	/**
	 * The meta object id for the '<em>Policy Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.osgi.scr.Policy
	 * @see org.osgi.scr.impl.ScrPackageImpl#getPolicyObject()
	 * @generated
	 */
	int POLICY_OBJECT = 16;

	/**
	 * The meta object id for the '<em>Policy Option Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.osgi.scr.PolicyOption
	 * @see org.osgi.scr.impl.ScrPackageImpl#getPolicyOptionObject()
	 * @generated
	 */
	int POLICY_OPTION_OBJECT = 17;


	/**
	 * Returns the meta object for class '{@link org.osgi.scr.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.osgi.scr.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link org.osgi.scr.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.osgi.scr.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link org.osgi.scr.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.osgi.scr.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link org.osgi.scr.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.osgi.scr.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link org.osgi.scr.DocumentRoot#getComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Component</em>'.
	 * @see org.osgi.scr.DocumentRoot#getComponent()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Component();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.DocumentRoot#isMustUnderstand <em>Must Understand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Must Understand</em>'.
	 * @see org.osgi.scr.DocumentRoot#isMustUnderstand()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_MustUnderstand();

	/**
	 * Returns the meta object for class '{@link org.osgi.scr.Component <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component</em>'.
	 * @see org.osgi.scr.Component
	 * @generated
	 */
	EClass getComponent();

	/**
	 * Returns the meta object for the attribute list '{@link org.osgi.scr.Component#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.osgi.scr.Component#getGroup()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.osgi.scr.Component#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Property</em>'.
	 * @see org.osgi.scr.Component#getProperty()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Property();

	/**
	 * Returns the meta object for the containment reference list '{@link org.osgi.scr.Component#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Properties</em>'.
	 * @see org.osgi.scr.Component#getProperties()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Properties();

	/**
	 * Returns the meta object for the containment reference '{@link org.osgi.scr.Component#getService <em>Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Service</em>'.
	 * @see org.osgi.scr.Component#getService()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Service();

	/**
	 * Returns the meta object for the containment reference list '{@link org.osgi.scr.Component#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Reference</em>'.
	 * @see org.osgi.scr.Component#getReference()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Reference();

	/**
	 * Returns the meta object for the containment reference '{@link org.osgi.scr.Component#getImplementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Implementation</em>'.
	 * @see org.osgi.scr.Component#getImplementation()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Implementation();

	/**
	 * Returns the meta object for the attribute list '{@link org.osgi.scr.Component#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.osgi.scr.Component#getAny()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Any();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Component#getActivate <em>Activate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activate</em>'.
	 * @see org.osgi.scr.Component#getActivate()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Activate();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Component#getConfigurationPid <em>Configuration Pid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Configuration Pid</em>'.
	 * @see org.osgi.scr.Component#getConfigurationPid()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_ConfigurationPid();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Component#getConfigurationPolicy <em>Configuration Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Configuration Policy</em>'.
	 * @see org.osgi.scr.Component#getConfigurationPolicy()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_ConfigurationPolicy();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Component#getDeactivate <em>Deactivate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Deactivate</em>'.
	 * @see org.osgi.scr.Component#getDeactivate()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Deactivate();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Component#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see org.osgi.scr.Component#isEnabled()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Enabled();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Component#getFactory <em>Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Factory</em>'.
	 * @see org.osgi.scr.Component#getFactory()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Factory();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Component#isImmediate <em>Immediate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Immediate</em>'.
	 * @see org.osgi.scr.Component#isImmediate()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Immediate();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Component#getModified <em>Modified</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Modified</em>'.
	 * @see org.osgi.scr.Component#getModified()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Modified();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Component#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.osgi.scr.Component#getName()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Name();

	/**
	 * Returns the meta object for the attribute list '{@link org.osgi.scr.Component#getAnyAttribute <em>Any Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any Attribute</em>'.
	 * @see org.osgi.scr.Component#getAnyAttribute()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_AnyAttribute();

	/**
	 * Returns the meta object for class '{@link org.osgi.scr.Implementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Implementation</em>'.
	 * @see org.osgi.scr.Implementation
	 * @generated
	 */
	EClass getImplementation();

	/**
	 * Returns the meta object for the attribute list '{@link org.osgi.scr.Implementation#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.osgi.scr.Implementation#getAny()
	 * @see #getImplementation()
	 * @generated
	 */
	EAttribute getImplementation_Any();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Implementation#getClass_ <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class</em>'.
	 * @see org.osgi.scr.Implementation#getClass_()
	 * @see #getImplementation()
	 * @generated
	 */
	EAttribute getImplementation_Class();

	/**
	 * Returns the meta object for the attribute list '{@link org.osgi.scr.Implementation#getAnyAttribute <em>Any Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any Attribute</em>'.
	 * @see org.osgi.scr.Implementation#getAnyAttribute()
	 * @see #getImplementation()
	 * @generated
	 */
	EAttribute getImplementation_AnyAttribute();

	/**
	 * Returns the meta object for class '{@link org.osgi.scr.Properties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Properties</em>'.
	 * @see org.osgi.scr.Properties
	 * @generated
	 */
	EClass getProperties();

	/**
	 * Returns the meta object for the attribute list '{@link org.osgi.scr.Properties#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.osgi.scr.Properties#getAny()
	 * @see #getProperties()
	 * @generated
	 */
	EAttribute getProperties_Any();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Properties#getEntry <em>Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Entry</em>'.
	 * @see org.osgi.scr.Properties#getEntry()
	 * @see #getProperties()
	 * @generated
	 */
	EAttribute getProperties_Entry();

	/**
	 * Returns the meta object for the attribute list '{@link org.osgi.scr.Properties#getAnyAttribute <em>Any Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any Attribute</em>'.
	 * @see org.osgi.scr.Properties#getAnyAttribute()
	 * @see #getProperties()
	 * @generated
	 */
	EAttribute getProperties_AnyAttribute();

	/**
	 * Returns the meta object for class '{@link org.osgi.scr.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property</em>'.
	 * @see org.osgi.scr.Property
	 * @generated
	 */
	EClass getProperty();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Property#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.osgi.scr.Property#getValue()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Property#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.osgi.scr.Property#getName()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Property#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.osgi.scr.Property#getType()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Property#getValue1 <em>Value1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value1</em>'.
	 * @see org.osgi.scr.Property#getValue1()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Value1();

	/**
	 * Returns the meta object for the attribute list '{@link org.osgi.scr.Property#getAnyAttribute <em>Any Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any Attribute</em>'.
	 * @see org.osgi.scr.Property#getAnyAttribute()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_AnyAttribute();

	/**
	 * Returns the meta object for class '{@link org.osgi.scr.Provide <em>Provide</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Provide</em>'.
	 * @see org.osgi.scr.Provide
	 * @generated
	 */
	EClass getProvide();

	/**
	 * Returns the meta object for the attribute list '{@link org.osgi.scr.Provide#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.osgi.scr.Provide#getAny()
	 * @see #getProvide()
	 * @generated
	 */
	EAttribute getProvide_Any();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Provide#getInterface <em>Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interface</em>'.
	 * @see org.osgi.scr.Provide#getInterface()
	 * @see #getProvide()
	 * @generated
	 */
	EAttribute getProvide_Interface();

	/**
	 * Returns the meta object for the attribute list '{@link org.osgi.scr.Provide#getAnyAttribute <em>Any Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any Attribute</em>'.
	 * @see org.osgi.scr.Provide#getAnyAttribute()
	 * @see #getProvide()
	 * @generated
	 */
	EAttribute getProvide_AnyAttribute();

	/**
	 * Returns the meta object for class '{@link org.osgi.scr.Reference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference</em>'.
	 * @see org.osgi.scr.Reference
	 * @generated
	 */
	EClass getReference();

	/**
	 * Returns the meta object for the attribute list '{@link org.osgi.scr.Reference#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.osgi.scr.Reference#getAny()
	 * @see #getReference()
	 * @generated
	 */
	EAttribute getReference_Any();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Reference#getBind <em>Bind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bind</em>'.
	 * @see org.osgi.scr.Reference#getBind()
	 * @see #getReference()
	 * @generated
	 */
	EAttribute getReference_Bind();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Reference#getCardinality <em>Cardinality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cardinality</em>'.
	 * @see org.osgi.scr.Reference#getCardinality()
	 * @see #getReference()
	 * @generated
	 */
	EAttribute getReference_Cardinality();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Reference#getInterface <em>Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interface</em>'.
	 * @see org.osgi.scr.Reference#getInterface()
	 * @see #getReference()
	 * @generated
	 */
	EAttribute getReference_Interface();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Reference#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.osgi.scr.Reference#getName()
	 * @see #getReference()
	 * @generated
	 */
	EAttribute getReference_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Reference#getPolicy <em>Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Policy</em>'.
	 * @see org.osgi.scr.Reference#getPolicy()
	 * @see #getReference()
	 * @generated
	 */
	EAttribute getReference_Policy();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Reference#getPolicyOption <em>Policy Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Policy Option</em>'.
	 * @see org.osgi.scr.Reference#getPolicyOption()
	 * @see #getReference()
	 * @generated
	 */
	EAttribute getReference_PolicyOption();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Reference#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target</em>'.
	 * @see org.osgi.scr.Reference#getTarget()
	 * @see #getReference()
	 * @generated
	 */
	EAttribute getReference_Target();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Reference#getUnbind <em>Unbind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unbind</em>'.
	 * @see org.osgi.scr.Reference#getUnbind()
	 * @see #getReference()
	 * @generated
	 */
	EAttribute getReference_Unbind();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Reference#getUpdated <em>Updated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Updated</em>'.
	 * @see org.osgi.scr.Reference#getUpdated()
	 * @see #getReference()
	 * @generated
	 */
	EAttribute getReference_Updated();

	/**
	 * Returns the meta object for the attribute list '{@link org.osgi.scr.Reference#getAnyAttribute <em>Any Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any Attribute</em>'.
	 * @see org.osgi.scr.Reference#getAnyAttribute()
	 * @see #getReference()
	 * @generated
	 */
	EAttribute getReference_AnyAttribute();

	/**
	 * Returns the meta object for class '{@link org.osgi.scr.Service <em>Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service</em>'.
	 * @see org.osgi.scr.Service
	 * @generated
	 */
	EClass getService();

	/**
	 * Returns the meta object for the containment reference list '{@link org.osgi.scr.Service#getProvide <em>Provide</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Provide</em>'.
	 * @see org.osgi.scr.Service#getProvide()
	 * @see #getService()
	 * @generated
	 */
	EReference getService_Provide();

	/**
	 * Returns the meta object for the attribute list '{@link org.osgi.scr.Service#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.osgi.scr.Service#getAny()
	 * @see #getService()
	 * @generated
	 */
	EAttribute getService_Any();

	/**
	 * Returns the meta object for the attribute '{@link org.osgi.scr.Service#isServicefactory <em>Servicefactory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Servicefactory</em>'.
	 * @see org.osgi.scr.Service#isServicefactory()
	 * @see #getService()
	 * @generated
	 */
	EAttribute getService_Servicefactory();

	/**
	 * Returns the meta object for the attribute list '{@link org.osgi.scr.Service#getAnyAttribute <em>Any Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any Attribute</em>'.
	 * @see org.osgi.scr.Service#getAnyAttribute()
	 * @see #getService()
	 * @generated
	 */
	EAttribute getService_AnyAttribute();

	/**
	 * Returns the meta object for enum '{@link org.osgi.scr.Cardinality <em>Cardinality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Cardinality</em>'.
	 * @see org.osgi.scr.Cardinality
	 * @generated
	 */
	EEnum getCardinality();

	/**
	 * Returns the meta object for enum '{@link org.osgi.scr.ConfigurationPolicy <em>Configuration Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Configuration Policy</em>'.
	 * @see org.osgi.scr.ConfigurationPolicy
	 * @generated
	 */
	EEnum getConfigurationPolicy();

	/**
	 * Returns the meta object for enum '{@link org.osgi.scr.JavaTypes <em>Java Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Java Types</em>'.
	 * @see org.osgi.scr.JavaTypes
	 * @generated
	 */
	EEnum getJavaTypes();

	/**
	 * Returns the meta object for enum '{@link org.osgi.scr.Policy <em>Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Policy</em>'.
	 * @see org.osgi.scr.Policy
	 * @generated
	 */
	EEnum getPolicy();

	/**
	 * Returns the meta object for enum '{@link org.osgi.scr.PolicyOption <em>Policy Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Policy Option</em>'.
	 * @see org.osgi.scr.PolicyOption
	 * @generated
	 */
	EEnum getPolicyOption();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.emf.common.util.Enumerator <em>Cardinality Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Cardinality Object</em>'.
	 * @see org.eclipse.emf.common.util.Enumerator
	 * @model instanceClass="org.eclipse.emf.common.util.Enumerator"
	 *        extendedMetaData="name='cardinality:Object' baseType='Cardinality'"
	 * @generated
	 */
	EDataType getCardinalityObject();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.emf.common.util.Enumerator <em>Configuration Policy Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Configuration Policy Object</em>'.
	 * @see org.eclipse.emf.common.util.Enumerator
	 * @model instanceClass="org.eclipse.emf.common.util.Enumerator"
	 *        extendedMetaData="name='configuration-policy:Object' baseType='configuration-policy'"
	 * @generated
	 */
	EDataType getConfigurationPolicyObject();

	/**
	 * Returns the meta object for data type '{@link org.osgi.scr.JavaTypes <em>Java Types Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Java Types Object</em>'.
	 * @see org.osgi.scr.JavaTypes
	 * @model instanceClass="org.osgi.scr.JavaTypes"
	 *        extendedMetaData="name='java-types:Object' baseType='java-types'"
	 * @generated
	 */
	EDataType getJavaTypesObject();

	/**
	 * Returns the meta object for data type '{@link org.osgi.scr.Policy <em>Policy Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Policy Object</em>'.
	 * @see org.osgi.scr.Policy
	 * @model instanceClass="org.osgi.scr.Policy"
	 *        extendedMetaData="name='policy:Object' baseType='policy'"
	 * @generated
	 */
	EDataType getPolicyObject();

	/**
	 * Returns the meta object for data type '{@link org.osgi.scr.PolicyOption <em>Policy Option Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Policy Option Object</em>'.
	 * @see org.osgi.scr.PolicyOption
	 * @model instanceClass="org.osgi.scr.PolicyOption"
	 *        extendedMetaData="name='policy-option:Object' baseType='policy-option'"
	 * @generated
	 */
	EDataType getPolicyOptionObject();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ScrFactory getScrFactory();

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
		 * The meta object literal for the '{@link org.osgi.scr.impl.DocumentRootImpl <em>Document Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.osgi.scr.impl.DocumentRootImpl
		 * @see org.osgi.scr.impl.ScrPackageImpl#getDocumentRoot()
		 * @generated
		 */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Component</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__COMPONENT = eINSTANCE.getDocumentRoot_Component();

		/**
		 * The meta object literal for the '<em><b>Must Understand</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__MUST_UNDERSTAND = eINSTANCE.getDocumentRoot_MustUnderstand();

		/**
		 * The meta object literal for the '{@link org.osgi.scr.impl.ComponentImpl <em>Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.osgi.scr.impl.ComponentImpl
		 * @see org.osgi.scr.impl.ScrPackageImpl#getComponent()
		 * @generated
		 */
		EClass COMPONENT = eINSTANCE.getComponent();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__GROUP = eINSTANCE.getComponent_Group();

		/**
		 * The meta object literal for the '<em><b>Property</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__PROPERTY = eINSTANCE.getComponent_Property();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__PROPERTIES = eINSTANCE.getComponent_Properties();

		/**
		 * The meta object literal for the '<em><b>Service</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__SERVICE = eINSTANCE.getComponent_Service();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__REFERENCE = eINSTANCE.getComponent_Reference();

		/**
		 * The meta object literal for the '<em><b>Implementation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__IMPLEMENTATION = eINSTANCE.getComponent_Implementation();

		/**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__ANY = eINSTANCE.getComponent_Any();

		/**
		 * The meta object literal for the '<em><b>Activate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__ACTIVATE = eINSTANCE.getComponent_Activate();

		/**
		 * The meta object literal for the '<em><b>Configuration Pid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__CONFIGURATION_PID = eINSTANCE.getComponent_ConfigurationPid();

		/**
		 * The meta object literal for the '<em><b>Configuration Policy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__CONFIGURATION_POLICY = eINSTANCE.getComponent_ConfigurationPolicy();

		/**
		 * The meta object literal for the '<em><b>Deactivate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__DEACTIVATE = eINSTANCE.getComponent_Deactivate();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__ENABLED = eINSTANCE.getComponent_Enabled();

		/**
		 * The meta object literal for the '<em><b>Factory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__FACTORY = eINSTANCE.getComponent_Factory();

		/**
		 * The meta object literal for the '<em><b>Immediate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__IMMEDIATE = eINSTANCE.getComponent_Immediate();

		/**
		 * The meta object literal for the '<em><b>Modified</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__MODIFIED = eINSTANCE.getComponent_Modified();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__NAME = eINSTANCE.getComponent_Name();

		/**
		 * The meta object literal for the '<em><b>Any Attribute</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__ANY_ATTRIBUTE = eINSTANCE.getComponent_AnyAttribute();

		/**
		 * The meta object literal for the '{@link org.osgi.scr.impl.ImplementationImpl <em>Implementation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.osgi.scr.impl.ImplementationImpl
		 * @see org.osgi.scr.impl.ScrPackageImpl#getImplementation()
		 * @generated
		 */
		EClass IMPLEMENTATION = eINSTANCE.getImplementation();

		/**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMPLEMENTATION__ANY = eINSTANCE.getImplementation_Any();

		/**
		 * The meta object literal for the '<em><b>Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMPLEMENTATION__CLASS = eINSTANCE.getImplementation_Class();

		/**
		 * The meta object literal for the '<em><b>Any Attribute</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMPLEMENTATION__ANY_ATTRIBUTE = eINSTANCE.getImplementation_AnyAttribute();

		/**
		 * The meta object literal for the '{@link org.osgi.scr.impl.PropertiesImpl <em>Properties</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.osgi.scr.impl.PropertiesImpl
		 * @see org.osgi.scr.impl.ScrPackageImpl#getProperties()
		 * @generated
		 */
		EClass PROPERTIES = eINSTANCE.getProperties();

		/**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTIES__ANY = eINSTANCE.getProperties_Any();

		/**
		 * The meta object literal for the '<em><b>Entry</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTIES__ENTRY = eINSTANCE.getProperties_Entry();

		/**
		 * The meta object literal for the '<em><b>Any Attribute</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTIES__ANY_ATTRIBUTE = eINSTANCE.getProperties_AnyAttribute();

		/**
		 * The meta object literal for the '{@link org.osgi.scr.impl.PropertyImpl <em>Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.osgi.scr.impl.PropertyImpl
		 * @see org.osgi.scr.impl.ScrPackageImpl#getProperty()
		 * @generated
		 */
		EClass PROPERTY = eINSTANCE.getProperty();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__VALUE = eINSTANCE.getProperty_Value();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__NAME = eINSTANCE.getProperty_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__TYPE = eINSTANCE.getProperty_Type();

		/**
		 * The meta object literal for the '<em><b>Value1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__VALUE1 = eINSTANCE.getProperty_Value1();

		/**
		 * The meta object literal for the '<em><b>Any Attribute</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__ANY_ATTRIBUTE = eINSTANCE.getProperty_AnyAttribute();

		/**
		 * The meta object literal for the '{@link org.osgi.scr.impl.ProvideImpl <em>Provide</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.osgi.scr.impl.ProvideImpl
		 * @see org.osgi.scr.impl.ScrPackageImpl#getProvide()
		 * @generated
		 */
		EClass PROVIDE = eINSTANCE.getProvide();

		/**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROVIDE__ANY = eINSTANCE.getProvide_Any();

		/**
		 * The meta object literal for the '<em><b>Interface</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROVIDE__INTERFACE = eINSTANCE.getProvide_Interface();

		/**
		 * The meta object literal for the '<em><b>Any Attribute</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROVIDE__ANY_ATTRIBUTE = eINSTANCE.getProvide_AnyAttribute();

		/**
		 * The meta object literal for the '{@link org.osgi.scr.impl.ReferenceImpl <em>Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.osgi.scr.impl.ReferenceImpl
		 * @see org.osgi.scr.impl.ScrPackageImpl#getReference()
		 * @generated
		 */
		EClass REFERENCE = eINSTANCE.getReference();

		/**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE__ANY = eINSTANCE.getReference_Any();

		/**
		 * The meta object literal for the '<em><b>Bind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE__BIND = eINSTANCE.getReference_Bind();

		/**
		 * The meta object literal for the '<em><b>Cardinality</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE__CARDINALITY = eINSTANCE.getReference_Cardinality();

		/**
		 * The meta object literal for the '<em><b>Interface</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE__INTERFACE = eINSTANCE.getReference_Interface();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE__NAME = eINSTANCE.getReference_Name();

		/**
		 * The meta object literal for the '<em><b>Policy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE__POLICY = eINSTANCE.getReference_Policy();

		/**
		 * The meta object literal for the '<em><b>Policy Option</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE__POLICY_OPTION = eINSTANCE.getReference_PolicyOption();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE__TARGET = eINSTANCE.getReference_Target();

		/**
		 * The meta object literal for the '<em><b>Unbind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE__UNBIND = eINSTANCE.getReference_Unbind();

		/**
		 * The meta object literal for the '<em><b>Updated</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE__UPDATED = eINSTANCE.getReference_Updated();

		/**
		 * The meta object literal for the '<em><b>Any Attribute</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE__ANY_ATTRIBUTE = eINSTANCE.getReference_AnyAttribute();

		/**
		 * The meta object literal for the '{@link org.osgi.scr.impl.ServiceImpl <em>Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.osgi.scr.impl.ServiceImpl
		 * @see org.osgi.scr.impl.ScrPackageImpl#getService()
		 * @generated
		 */
		EClass SERVICE = eINSTANCE.getService();

		/**
		 * The meta object literal for the '<em><b>Provide</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE__PROVIDE = eINSTANCE.getService_Provide();

		/**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE__ANY = eINSTANCE.getService_Any();

		/**
		 * The meta object literal for the '<em><b>Servicefactory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE__SERVICEFACTORY = eINSTANCE.getService_Servicefactory();

		/**
		 * The meta object literal for the '<em><b>Any Attribute</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE__ANY_ATTRIBUTE = eINSTANCE.getService_AnyAttribute();

		/**
		 * The meta object literal for the '{@link org.osgi.scr.Cardinality <em>Cardinality</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.osgi.scr.Cardinality
		 * @see org.osgi.scr.impl.ScrPackageImpl#getCardinality()
		 * @generated
		 */
		EEnum CARDINALITY = eINSTANCE.getCardinality();

		/**
		 * The meta object literal for the '{@link org.osgi.scr.ConfigurationPolicy <em>Configuration Policy</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.osgi.scr.ConfigurationPolicy
		 * @see org.osgi.scr.impl.ScrPackageImpl#getConfigurationPolicy()
		 * @generated
		 */
		EEnum CONFIGURATION_POLICY = eINSTANCE.getConfigurationPolicy();

		/**
		 * The meta object literal for the '{@link org.osgi.scr.JavaTypes <em>Java Types</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.osgi.scr.JavaTypes
		 * @see org.osgi.scr.impl.ScrPackageImpl#getJavaTypes()
		 * @generated
		 */
		EEnum JAVA_TYPES = eINSTANCE.getJavaTypes();

		/**
		 * The meta object literal for the '{@link org.osgi.scr.Policy <em>Policy</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.osgi.scr.Policy
		 * @see org.osgi.scr.impl.ScrPackageImpl#getPolicy()
		 * @generated
		 */
		EEnum POLICY = eINSTANCE.getPolicy();

		/**
		 * The meta object literal for the '{@link org.osgi.scr.PolicyOption <em>Policy Option</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.osgi.scr.PolicyOption
		 * @see org.osgi.scr.impl.ScrPackageImpl#getPolicyOption()
		 * @generated
		 */
		EEnum POLICY_OPTION = eINSTANCE.getPolicyOption();

		/**
		 * The meta object literal for the '<em>Cardinality Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.common.util.Enumerator
		 * @see org.osgi.scr.impl.ScrPackageImpl#getCardinalityObject()
		 * @generated
		 */
		EDataType CARDINALITY_OBJECT = eINSTANCE.getCardinalityObject();

		/**
		 * The meta object literal for the '<em>Configuration Policy Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.common.util.Enumerator
		 * @see org.osgi.scr.impl.ScrPackageImpl#getConfigurationPolicyObject()
		 * @generated
		 */
		EDataType CONFIGURATION_POLICY_OBJECT = eINSTANCE.getConfigurationPolicyObject();

		/**
		 * The meta object literal for the '<em>Java Types Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.osgi.scr.JavaTypes
		 * @see org.osgi.scr.impl.ScrPackageImpl#getJavaTypesObject()
		 * @generated
		 */
		EDataType JAVA_TYPES_OBJECT = eINSTANCE.getJavaTypesObject();

		/**
		 * The meta object literal for the '<em>Policy Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.osgi.scr.Policy
		 * @see org.osgi.scr.impl.ScrPackageImpl#getPolicyObject()
		 * @generated
		 */
		EDataType POLICY_OBJECT = eINSTANCE.getPolicyObject();

		/**
		 * The meta object literal for the '<em>Policy Option Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.osgi.scr.PolicyOption
		 * @see org.osgi.scr.impl.ScrPackageImpl#getPolicyOptionObject()
		 * @generated
		 */
		EDataType POLICY_OPTION_OBJECT = eINSTANCE.getPolicyOptionObject();

	}

} //ScrPackage
