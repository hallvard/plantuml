/**
 */
package no.hal.osgi.impl;

import java.util.Collection;

import no.hal.osgi.GenericAttributeValue;
import no.hal.osgi.OsgiPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generic Attribute Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link no.hal.osgi.impl.GenericAttributeValueImpl#getValue <em>Value</em>}</li>
 *   <li>{@link no.hal.osgi.impl.GenericAttributeValueImpl#getExtraAttributes <em>Extra Attributes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GenericAttributeValueImpl extends MinimalEObjectImpl.Container implements GenericAttributeValue {
	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExtraAttributes() <em>Extra Attributes</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtraAttributes()
	 * @generated
	 * @ordered
	 */
	protected EList<String> extraAttributes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenericAttributeValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OsgiPackage.Literals.GENERIC_ATTRIBUTE_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(String newValue) {
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OsgiPackage.GENERIC_ATTRIBUTE_VALUE__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getExtraAttributes() {
		if (extraAttributes == null) {
			extraAttributes = new EDataTypeUniqueEList<String>(String.class, this, OsgiPackage.GENERIC_ATTRIBUTE_VALUE__EXTRA_ATTRIBUTES);
		}
		return extraAttributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OsgiPackage.GENERIC_ATTRIBUTE_VALUE__VALUE:
				return getValue();
			case OsgiPackage.GENERIC_ATTRIBUTE_VALUE__EXTRA_ATTRIBUTES:
				return getExtraAttributes();
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
			case OsgiPackage.GENERIC_ATTRIBUTE_VALUE__VALUE:
				setValue((String)newValue);
				return;
			case OsgiPackage.GENERIC_ATTRIBUTE_VALUE__EXTRA_ATTRIBUTES:
				getExtraAttributes().clear();
				getExtraAttributes().addAll((Collection<? extends String>)newValue);
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
			case OsgiPackage.GENERIC_ATTRIBUTE_VALUE__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case OsgiPackage.GENERIC_ATTRIBUTE_VALUE__EXTRA_ATTRIBUTES:
				getExtraAttributes().clear();
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
			case OsgiPackage.GENERIC_ATTRIBUTE_VALUE__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case OsgiPackage.GENERIC_ATTRIBUTE_VALUE__EXTRA_ATTRIBUTES:
				return extraAttributes != null && !extraAttributes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (value: ");
		result.append(value);
		result.append(", extraAttributes: ");
		result.append(extraAttributes);
		result.append(')');
		return result.toString();
	}

} //GenericAttributeValueImpl
