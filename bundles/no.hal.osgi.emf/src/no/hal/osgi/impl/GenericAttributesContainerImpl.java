/**
 */
package no.hal.osgi.impl;

import java.util.Collection;

import no.hal.osgi.GenericAttribute;
import no.hal.osgi.GenericAttributesContainer;
import no.hal.osgi.OsgiPackage;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generic Attributes Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link no.hal.osgi.impl.GenericAttributesContainerImpl#getGenericAttributes <em>Generic Attributes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GenericAttributesContainerImpl extends MinimalEObjectImpl.Container implements GenericAttributesContainer {
	/**
	 * The cached value of the '{@link #getGenericAttributes() <em>Generic Attributes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenericAttributes()
	 * @generated
	 * @ordered
	 */
	protected EList<GenericAttribute> genericAttributes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenericAttributesContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OsgiPackage.Literals.GENERIC_ATTRIBUTES_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GenericAttribute> getGenericAttributes() {
		if (genericAttributes == null) {
			genericAttributes = new EObjectResolvingEList<GenericAttribute>(GenericAttribute.class, this, OsgiPackage.GENERIC_ATTRIBUTES_CONTAINER__GENERIC_ATTRIBUTES);
		}
		return genericAttributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OsgiPackage.GENERIC_ATTRIBUTES_CONTAINER__GENERIC_ATTRIBUTES:
				return getGenericAttributes();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case OsgiPackage.GENERIC_ATTRIBUTES_CONTAINER__GENERIC_ATTRIBUTES:
				getGenericAttributes().clear();
				getGenericAttributes().addAll((Collection<? extends GenericAttribute>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case OsgiPackage.GENERIC_ATTRIBUTES_CONTAINER__GENERIC_ATTRIBUTES:
				getGenericAttributes().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case OsgiPackage.GENERIC_ATTRIBUTES_CONTAINER__GENERIC_ATTRIBUTES:
				return genericAttributes != null && !genericAttributes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //GenericAttributesContainerImpl
