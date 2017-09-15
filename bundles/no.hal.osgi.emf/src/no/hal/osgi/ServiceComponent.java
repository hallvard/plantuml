/**
 */
package no.hal.osgi;

import no.hal.osgi.emf.util.Path;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link no.hal.osgi.ServiceComponent#getPath <em>Path</em>}</li>
 *   <li>{@link no.hal.osgi.ServiceComponent#getComponent <em>Component</em>}</li>
 * </ul>
 *
 * @see no.hal.osgi.OsgiPackage#getServiceComponent()
 * @model
 * @generated
 */
public interface ServiceComponent extends EObject {

	/**
	 * Returns the value of the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path</em>' attribute.
	 * @see #setPath(Path)
	 * @see no.hal.osgi.OsgiPackage#getServiceComponent_Path()
	 * @model dataType="no.hal.osgi.Path"
	 * @generated
	 */
	Path getPath();

	/**
	 * Sets the value of the '{@link no.hal.osgi.ServiceComponent#getPath <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(Path value);

	/**
	 * Returns the value of the '<em><b>Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component</em>' reference.
	 * @see #setComponent(org.osgi.scr.Component)
	 * @see no.hal.osgi.OsgiPackage#getServiceComponent_Component()
	 * @model
	 * @generated
	 */
	org.osgi.scr.Component getComponent();

	/**
	 * Sets the value of the '{@link no.hal.osgi.ServiceComponent#getComponent <em>Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component</em>' reference.
	 * @see #getComponent()
	 * @generated
	 */
	void setComponent(org.osgi.scr.Component value);
} // ServiceComponent
