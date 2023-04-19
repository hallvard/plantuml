/**
 */
package org.osgi.scr.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.osgi.scr.Cardinality;
import org.osgi.scr.Policy;
import org.osgi.scr.PolicyOption;
import org.osgi.scr.Reference;
import org.osgi.scr.ScrPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.osgi.scr.impl.ReferenceImpl#getAny <em>Any</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ReferenceImpl#getBind <em>Bind</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ReferenceImpl#getCardinality <em>Cardinality</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ReferenceImpl#getInterface <em>Interface</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ReferenceImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ReferenceImpl#getPolicy <em>Policy</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ReferenceImpl#getPolicyOption <em>Policy Option</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ReferenceImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ReferenceImpl#getUnbind <em>Unbind</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ReferenceImpl#getUpdated <em>Updated</em>}</li>
 *   <li>{@link org.osgi.scr.impl.ReferenceImpl#getAnyAttribute <em>Any Attribute</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReferenceImpl extends MinimalEObjectImpl.Container implements Reference {
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
	 * The default value of the '{@link #getBind() <em>Bind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBind()
	 * @generated
	 * @ordered
	 */
	protected static final String BIND_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBind() <em>Bind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBind()
	 * @generated
	 * @ordered
	 */
	protected String bind = BIND_EDEFAULT;

	/**
	 * The default value of the '{@link #getCardinality() <em>Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCardinality()
	 * @generated
	 * @ordered
	 */
	protected static final Cardinality CARDINALITY_EDEFAULT = Cardinality.MANDATORY;

	/**
	 * The cached value of the '{@link #getCardinality() <em>Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCardinality()
	 * @generated
	 * @ordered
	 */
	protected Cardinality cardinality = CARDINALITY_EDEFAULT;

	/**
	 * This is true if the Cardinality attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean cardinalityESet;

	/**
	 * The default value of the '{@link #getInterface() <em>Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterface()
	 * @generated
	 * @ordered
	 */
	protected static final String INTERFACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInterface() <em>Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterface()
	 * @generated
	 * @ordered
	 */
	protected String interface_ = INTERFACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPolicy() <em>Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPolicy()
	 * @generated
	 * @ordered
	 */
	protected static final Policy POLICY_EDEFAULT = Policy.STATIC;

	/**
	 * The cached value of the '{@link #getPolicy() <em>Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPolicy()
	 * @generated
	 * @ordered
	 */
	protected Policy policy = POLICY_EDEFAULT;

	/**
	 * This is true if the Policy attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean policyESet;

	/**
	 * The default value of the '{@link #getPolicyOption() <em>Policy Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPolicyOption()
	 * @generated
	 * @ordered
	 */
	protected static final PolicyOption POLICY_OPTION_EDEFAULT = PolicyOption.RELUCTANT;

	/**
	 * The cached value of the '{@link #getPolicyOption() <em>Policy Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPolicyOption()
	 * @generated
	 * @ordered
	 */
	protected PolicyOption policyOption = POLICY_OPTION_EDEFAULT;

	/**
	 * This is true if the Policy Option attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean policyOptionESet;

	/**
	 * The default value of the '{@link #getTarget() <em>Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected String target = TARGET_EDEFAULT;

	/**
	 * The default value of the '{@link #getUnbind() <em>Unbind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnbind()
	 * @generated
	 * @ordered
	 */
	protected static final String UNBIND_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUnbind() <em>Unbind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnbind()
	 * @generated
	 * @ordered
	 */
	protected String unbind = UNBIND_EDEFAULT;

	/**
	 * The default value of the '{@link #getUpdated() <em>Updated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpdated()
	 * @generated
	 * @ordered
	 */
	protected static final String UPDATED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUpdated() <em>Updated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpdated()
	 * @generated
	 * @ordered
	 */
	protected String updated = UPDATED_EDEFAULT;

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
	protected ReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScrPackage.Literals.REFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FeatureMap getAny() {
		if (any == null) {
			any = new BasicFeatureMap(this, ScrPackage.REFERENCE__ANY);
		}
		return any;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBind() {
		return bind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBind(String newBind) {
		String oldBind = bind;
		bind = newBind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.REFERENCE__BIND, oldBind, bind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Cardinality getCardinality() {
		return cardinality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCardinality(Cardinality newCardinality) {
		Cardinality oldCardinality = cardinality;
		cardinality = newCardinality == null ? CARDINALITY_EDEFAULT : newCardinality;
		boolean oldCardinalityESet = cardinalityESet;
		cardinalityESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.REFERENCE__CARDINALITY, oldCardinality, cardinality, !oldCardinalityESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetCardinality() {
		Cardinality oldCardinality = cardinality;
		boolean oldCardinalityESet = cardinalityESet;
		cardinality = CARDINALITY_EDEFAULT;
		cardinalityESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScrPackage.REFERENCE__CARDINALITY, oldCardinality, CARDINALITY_EDEFAULT, oldCardinalityESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetCardinality() {
		return cardinalityESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getInterface() {
		return interface_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInterface(String newInterface) {
		String oldInterface = interface_;
		interface_ = newInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.REFERENCE__INTERFACE, oldInterface, interface_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.REFERENCE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Policy getPolicy() {
		return policy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPolicy(Policy newPolicy) {
		Policy oldPolicy = policy;
		policy = newPolicy == null ? POLICY_EDEFAULT : newPolicy;
		boolean oldPolicyESet = policyESet;
		policyESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.REFERENCE__POLICY, oldPolicy, policy, !oldPolicyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetPolicy() {
		Policy oldPolicy = policy;
		boolean oldPolicyESet = policyESet;
		policy = POLICY_EDEFAULT;
		policyESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScrPackage.REFERENCE__POLICY, oldPolicy, POLICY_EDEFAULT, oldPolicyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetPolicy() {
		return policyESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PolicyOption getPolicyOption() {
		return policyOption;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPolicyOption(PolicyOption newPolicyOption) {
		PolicyOption oldPolicyOption = policyOption;
		policyOption = newPolicyOption == null ? POLICY_OPTION_EDEFAULT : newPolicyOption;
		boolean oldPolicyOptionESet = policyOptionESet;
		policyOptionESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.REFERENCE__POLICY_OPTION, oldPolicyOption, policyOption, !oldPolicyOptionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetPolicyOption() {
		PolicyOption oldPolicyOption = policyOption;
		boolean oldPolicyOptionESet = policyOptionESet;
		policyOption = POLICY_OPTION_EDEFAULT;
		policyOptionESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScrPackage.REFERENCE__POLICY_OPTION, oldPolicyOption, POLICY_OPTION_EDEFAULT, oldPolicyOptionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetPolicyOption() {
		return policyOptionESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTarget(String newTarget) {
		String oldTarget = target;
		target = newTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.REFERENCE__TARGET, oldTarget, target));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getUnbind() {
		return unbind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUnbind(String newUnbind) {
		String oldUnbind = unbind;
		unbind = newUnbind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.REFERENCE__UNBIND, oldUnbind, unbind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getUpdated() {
		return updated;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUpdated(String newUpdated) {
		String oldUpdated = updated;
		updated = newUpdated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScrPackage.REFERENCE__UPDATED, oldUpdated, updated));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FeatureMap getAnyAttribute() {
		if (anyAttribute == null) {
			anyAttribute = new BasicFeatureMap(this, ScrPackage.REFERENCE__ANY_ATTRIBUTE);
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
			case ScrPackage.REFERENCE__ANY:
				return ((InternalEList<?>)getAny()).basicRemove(otherEnd, msgs);
			case ScrPackage.REFERENCE__ANY_ATTRIBUTE:
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
			case ScrPackage.REFERENCE__ANY:
				if (coreType) return getAny();
				return ((FeatureMap.Internal)getAny()).getWrapper();
			case ScrPackage.REFERENCE__BIND:
				return getBind();
			case ScrPackage.REFERENCE__CARDINALITY:
				return getCardinality();
			case ScrPackage.REFERENCE__INTERFACE:
				return getInterface();
			case ScrPackage.REFERENCE__NAME:
				return getName();
			case ScrPackage.REFERENCE__POLICY:
				return getPolicy();
			case ScrPackage.REFERENCE__POLICY_OPTION:
				return getPolicyOption();
			case ScrPackage.REFERENCE__TARGET:
				return getTarget();
			case ScrPackage.REFERENCE__UNBIND:
				return getUnbind();
			case ScrPackage.REFERENCE__UPDATED:
				return getUpdated();
			case ScrPackage.REFERENCE__ANY_ATTRIBUTE:
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
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ScrPackage.REFERENCE__ANY:
				((FeatureMap.Internal)getAny()).set(newValue);
				return;
			case ScrPackage.REFERENCE__BIND:
				setBind((String)newValue);
				return;
			case ScrPackage.REFERENCE__CARDINALITY:
				setCardinality((Cardinality)newValue);
				return;
			case ScrPackage.REFERENCE__INTERFACE:
				setInterface((String)newValue);
				return;
			case ScrPackage.REFERENCE__NAME:
				setName((String)newValue);
				return;
			case ScrPackage.REFERENCE__POLICY:
				setPolicy((Policy)newValue);
				return;
			case ScrPackage.REFERENCE__POLICY_OPTION:
				setPolicyOption((PolicyOption)newValue);
				return;
			case ScrPackage.REFERENCE__TARGET:
				setTarget((String)newValue);
				return;
			case ScrPackage.REFERENCE__UNBIND:
				setUnbind((String)newValue);
				return;
			case ScrPackage.REFERENCE__UPDATED:
				setUpdated((String)newValue);
				return;
			case ScrPackage.REFERENCE__ANY_ATTRIBUTE:
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
			case ScrPackage.REFERENCE__ANY:
				getAny().clear();
				return;
			case ScrPackage.REFERENCE__BIND:
				setBind(BIND_EDEFAULT);
				return;
			case ScrPackage.REFERENCE__CARDINALITY:
				unsetCardinality();
				return;
			case ScrPackage.REFERENCE__INTERFACE:
				setInterface(INTERFACE_EDEFAULT);
				return;
			case ScrPackage.REFERENCE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ScrPackage.REFERENCE__POLICY:
				unsetPolicy();
				return;
			case ScrPackage.REFERENCE__POLICY_OPTION:
				unsetPolicyOption();
				return;
			case ScrPackage.REFERENCE__TARGET:
				setTarget(TARGET_EDEFAULT);
				return;
			case ScrPackage.REFERENCE__UNBIND:
				setUnbind(UNBIND_EDEFAULT);
				return;
			case ScrPackage.REFERENCE__UPDATED:
				setUpdated(UPDATED_EDEFAULT);
				return;
			case ScrPackage.REFERENCE__ANY_ATTRIBUTE:
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
			case ScrPackage.REFERENCE__ANY:
				return any != null && !any.isEmpty();
			case ScrPackage.REFERENCE__BIND:
				return BIND_EDEFAULT == null ? bind != null : !BIND_EDEFAULT.equals(bind);
			case ScrPackage.REFERENCE__CARDINALITY:
				return isSetCardinality();
			case ScrPackage.REFERENCE__INTERFACE:
				return INTERFACE_EDEFAULT == null ? interface_ != null : !INTERFACE_EDEFAULT.equals(interface_);
			case ScrPackage.REFERENCE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ScrPackage.REFERENCE__POLICY:
				return isSetPolicy();
			case ScrPackage.REFERENCE__POLICY_OPTION:
				return isSetPolicyOption();
			case ScrPackage.REFERENCE__TARGET:
				return TARGET_EDEFAULT == null ? target != null : !TARGET_EDEFAULT.equals(target);
			case ScrPackage.REFERENCE__UNBIND:
				return UNBIND_EDEFAULT == null ? unbind != null : !UNBIND_EDEFAULT.equals(unbind);
			case ScrPackage.REFERENCE__UPDATED:
				return UPDATED_EDEFAULT == null ? updated != null : !UPDATED_EDEFAULT.equals(updated);
			case ScrPackage.REFERENCE__ANY_ATTRIBUTE:
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
		result.append(", bind: ");
		result.append(bind);
		result.append(", cardinality: ");
		if (cardinalityESet) result.append(cardinality); else result.append("<unset>");
		result.append(", interface: ");
		result.append(interface_);
		result.append(", name: ");
		result.append(name);
		result.append(", policy: ");
		if (policyESet) result.append(policy); else result.append("<unset>");
		result.append(", policyOption: ");
		if (policyOptionESet) result.append(policyOption); else result.append("<unset>");
		result.append(", target: ");
		result.append(target);
		result.append(", unbind: ");
		result.append(unbind);
		result.append(", updated: ");
		result.append(updated);
		result.append(", anyAttribute: ");
		result.append(anyAttribute);
		result.append(')');
		return result.toString();
	}

} //ReferenceImpl
