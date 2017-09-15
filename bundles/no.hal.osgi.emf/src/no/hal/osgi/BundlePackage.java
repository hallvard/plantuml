/**
 */
package no.hal.osgi;

import no.hal.osgi.emf.util.QualifiedName;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bundle Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link no.hal.osgi.BundlePackage#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see no.hal.osgi.OsgiPackage#getBundlePackage()
 * @model abstract="true"
 * @generated
 */
public interface BundlePackage extends EObject {
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
	 * @see no.hal.osgi.OsgiPackage#getBundlePackage_Name()
	 * @model dataType="no.hal.osgi.QualifiedName"
	 * @generated
	 */
	QualifiedName getName();

	/**
	 * Sets the value of the '{@link no.hal.osgi.BundlePackage#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(QualifiedName value);

} // BundlePackage
