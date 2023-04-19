/**
 */
package org.osgi.scr.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.osgi.scr.Provide;
import org.osgi.scr.ScrPackage;
import org.osgi.scr.Service;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.osgi.scr.impl.ServiceImpl#getProvide <em>Provide</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ServiceImpl#getAny <em>Any</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ServiceImpl#isServicefactory <em>Servicefactory</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ServiceImpl#getAnyAttribute <em>Any Attribute</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ServiceImpl extends MinimalEObjectImpl.Container implements Service {
	/**
	 * The cached value of the '{@link #getProvide() <em>Provide</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProvide()
	 * @generated
	 * @ordered
	 */
	protected EList<Provide> provide;

	/**
	 * The cached value of the '{@link #getAny() <em>Any</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAny()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap any;

	/**
	 * The default value of the '{@link #isServicefactory() <em>Servicefactory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isServicefactory()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SERVICEFACTORY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isServicefactory() <em>Servicefactory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isServicefactory()
	 * @generated
	 * @ordered
	 */
	protected boolean servicefactory = SERVICEFACTORY_EDEFAULT;

	/**
	 * This is true if the Servicefactory attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean servicefactoryESet;

	/**
	 * The cached value of the '{@link #getAnyAttribute() <em>Any Attribute</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnyAttribute()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap anyAttribute;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScrPackage.Literals.SERVICE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Provide> getProvide() {
		if (provide == null) {
			provide = new EObjectContainmentEList<Provide>(Provide.class, this, ScrPackage.SERVICE__PROVIDE);
		}
		return provide;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FeatureMap getAny() {
		if (any == null) {
			any = new BasicFeatureMap(this, ScrPackage.SERVICE__ANY);
		}
		return any;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isServicefactory() {
		return servicefactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setServicefactory(boolean newServicefactory) {
		boolean oldServicefactory = servicefactory;
		servicefactory = newServicefactory;
		boolean oldServicefactoryESet = servicefactoryESet;
		servicefactoryESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.SERVICE__SERVICEFACTORY, oldServicefactory, servicefactory, !oldServicefactoryESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetServicefactory() {
		boolean oldServicefactory = servicefactory;
		boolean oldServicefactoryESet = servicefactoryESet;
		servicefactory = SERVICEFACTORY_EDEFAULT;
		servicefactoryESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScrPackage.SERVICE__SERVICEFACTORY, oldServicefactory, SERVICEFACTORY_EDEFAULT, oldServicefactoryESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetServicefactory() {
		return servicefactoryESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FeatureMap getAnyAttribute() {
		if (anyAttribute == null) {
			anyAttribute = new BasicFeatureMap(this, ScrPackage.SERVICE__ANY_ATTRIBUTE);
		}
		return anyAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ScrPackage.SERVICE__PROVIDE:
				return ((InternalEList<?>)getProvide()).basicRemove(otherEnd, msgs);
			case ScrPackage.SERVICE__ANY:
				return ((InternalEList<?>)getAny()).basicRemove(otherEnd, msgs);
			case ScrPackage.SERVICE__ANY_ATTRIBUTE:
				return ((InternalEList<?>)getAnyAttribute()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ScrPackage.SERVICE__PROVIDE:
				return getProvide();
			case ScrPackage.SERVICE__ANY:
				if (coreType) return getAny();
				return ((FeatureMap.Internal)getAny()).getWrapper();
			case ScrPackage.SERVICE__SERVICEFACTORY:
				return isServicefactory();
			case ScrPackage.SERVICE__ANY_ATTRIBUTE:
				if (coreType) return getAnyAttribute();
				return ((FeatureMap.Internal)getAnyAttribute()).getWrapper();
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
			case ScrPackage.SERVICE__PROVIDE:
				getProvide().clear();
				getProvide().addAll((Collection<? extends Provide>)newValue);
				return;
			case ScrPackage.SERVICE__ANY:
				((FeatureMap.Internal)getAny()).set(newValue);
				return;
			case ScrPackage.SERVICE__SERVICEFACTORY:
				setServicefactory((Boolean)newValue);
				return;
			case ScrPackage.SERVICE__ANY_ATTRIBUTE:
				((FeatureMap.Internal)getAnyAttribute()).set(newValue);
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
			case ScrPackage.SERVICE__PROVIDE:
				getProvide().clear();
				return;
			case ScrPackage.SERVICE__ANY:
				getAny().clear();
				return;
			case ScrPackage.SERVICE__SERVICEFACTORY:
				unsetServicefactory();
				return;
			case ScrPackage.SERVICE__ANY_ATTRIBUTE:
				getAnyAttribute().clear();
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
			case ScrPackage.SERVICE__PROVIDE:
				return provide != null && !provide.isEmpty();
			case ScrPackage.SERVICE__ANY:
				return any != null && !any.isEmpty();
			case ScrPackage.SERVICE__SERVICEFACTORY:
				return isSetServicefactory();
			case ScrPackage.SERVICE__ANY_ATTRIBUTE:
				return anyAttribute != null && !anyAttribute.isEmpty();
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

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (any: ");
		result.append(any);
		result.append(", servicefactory: ");
		if (servicefactoryESet) result.append(servicefactory); else result.append("<unset>");
		result.append(", anyAttribute: ");
		result.append(anyAttribute);
		result.append(')');
		return result.toString();
	}

} //ServiceImpl
