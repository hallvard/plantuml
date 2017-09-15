/**
 */
package no.hal.osgi;

import no.hal.osgi.emf.util.QualifiedName;
import no.hal.osgi.emf.util.VersionRange;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Required Bundle</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link no.hal.osgi.RequiredBundle#getName <em>Name</em>}</li>
 *   <li>{@link no.hal.osgi.RequiredBundle#getBundleVersion <em>Bundle Version</em>}</li>
 *   <li>{@link no.hal.osgi.RequiredBundle#isOptional <em>Optional</em>}</li>
 *   <li>{@link no.hal.osgi.RequiredBundle#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link no.hal.osgi.RequiredBundle#getResolvesTo <em>Resolves To</em>}</li>
 * </ul>
 *
 * @see no.hal.osgi.OsgiPackage#getRequiredBundle()
 * @model
 * @generated
 */
public interface RequiredBundle extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(QualifiedName)
	 * @see no.hal.osgi.OsgiPackage#getRequiredBundle_Name()
	 * @model dataType="no.hal.osgi.QualifiedName"
	 * @generated
	 */
	QualifiedName getName();

	/**
	 * Sets the value of the '{@link no.hal.osgi.RequiredBundle#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(QualifiedName value);

	/**
	 * Returns the value of the '<em><b>Bundle Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bundle Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bundle Version</em>' attribute.
	 * @see #setBundleVersion(VersionRange)
	 * @see no.hal.osgi.OsgiPackage#getRequiredBundle_BundleVersion()
	 * @model dataType="no.hal.osgi.VersionRange"
	 * @generated
	 */
	VersionRange getBundleVersion();

	/**
	 * Sets the value of the '{@link no.hal.osgi.RequiredBundle#getBundleVersion <em>Bundle Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bundle Version</em>' attribute.
	 * @see #getBundleVersion()
	 * @generated
	 */
	void setBundleVersion(VersionRange value);

	/**
	 * Returns the value of the '<em><b>Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Optional</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Optional</em>' attribute.
	 * @see #setOptional(boolean)
	 * @see no.hal.osgi.OsgiPackage#getRequiredBundle_Optional()
	 * @model
	 * @generated
	 */
	boolean isOptional();

	/**
	 * Sets the value of the '{@link no.hal.osgi.RequiredBundle#isOptional <em>Optional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Optional</em>' attribute.
	 * @see #isOptional()
	 * @generated
	 */
	void setOptional(boolean value);

	/**
	 * Returns the value of the '<em><b>Visibility</b></em>' attribute.
	 * The literals are from the enumeration {@link no.hal.osgi.VisibilityKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visibility</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visibility</em>' attribute.
	 * @see no.hal.osgi.VisibilityKind
	 * @see #setVisibility(VisibilityKind)
	 * @see no.hal.osgi.OsgiPackage#getRequiredBundle_Visibility()
	 * @model
	 * @generated
	 */
	VisibilityKind getVisibility();

	/**
	 * Sets the value of the '{@link no.hal.osgi.RequiredBundle#getVisibility <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visibility</em>' attribute.
	 * @see no.hal.osgi.VisibilityKind
	 * @see #getVisibility()
	 * @generated
	 */
	void setVisibility(VisibilityKind value);

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
	 * @see no.hal.osgi.OsgiPackage#getRequiredBundle_ResolvesTo()
	 * @model
	 * @generated
	 */
	EList<Bundle> getResolvesTo();

} // RequiredBundle
