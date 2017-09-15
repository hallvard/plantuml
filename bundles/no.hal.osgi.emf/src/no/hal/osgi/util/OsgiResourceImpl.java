/**
 */
package no.hal.osgi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.osgi.scr.Component;
import org.osgi.scr.ScrFactory;

import no.hal.osgi.GenericAttribute;
import no.hal.osgi.GenericAttributeValue;
import no.hal.osgi.GenericAttributesContainer;
import no.hal.osgi.OsgiFactory;
import no.hal.osgi.OsgiPackage;
import no.hal.osgi.ServiceComponent;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see no.hal.osgi.util.OsgiResourceFactoryImpl
 * @generated
 */
public class OsgiResourceImpl extends ResourceImpl {
	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param uri the URI of the new resource.
	 * @generated
	 */
	public OsgiResourceImpl(URI uri) {
		super(uri);
	}
	
	
	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		getContents().add(OsgiFactory.eINSTANCE.createManifest());
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String attributeString = "", line = null;
		while ((line = reader.readLine()) != null) {
			if (attributeString.length() == 0) {
				attributeString = line;
			} else if (line.length() > 0 && line.charAt(0) == ' ') {
				attributeString += line.substring(1);
			} else {
				handleAttributeData(parseAttribute(attributeString));
				attributeString = line;
			}
		}
		if (attributeString.length() > 0) {
			handleAttributeData(parseAttribute(attributeString));
		}
		reader.close();
		TreeIterator<EObject> allContents = getAllContents();
		while (allContents.hasNext()) {
			if (! postProcess(allContents.next())) {
				allContents.prune();
			}
		}
	}

	protected boolean postProcess(EObject eObject) {
		if (eObject instanceof ServiceComponent) {
			ServiceComponent sc = (ServiceComponent) eObject;
			if (sc.getComponent() == null && sc.getPath() != null) {
				URI resUri = URI.createURI(sc.getPath().toString()).resolve(getURI());
				URI compUri = resUri.appendFragment("//@component");
				Component component = ScrFactory.eINSTANCE.createComponent();
				((InternalEObject) component).eSetProxyURI(compUri);
				sc.setComponent(component);
			}
			return false;
		}
		return true;
	}

	private static class AttributeData {
		String name;
		AttributeValueData[] values;

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append(name);
			builder.append(": ");
			for (int i = 0; i < values.length; i++) {
				if (i > 0) {
					builder.append(",\n ");
				}
				builder.append(values[i].value);
				String[] extraAttributes = values[i].extraAttributes;
				if (extraAttributes != null) {
					for (int j = 0; j < extraAttributes.length; j += 2) {
						builder.append(";");
						builder.append(extraAttributes[(j * 2) + 0]);
						builder.append(":=");
						builder.append(extraAttributes[(j * 2) + 1]);
					}
				}
			}
			builder.append("\n");
			return builder.toString();
		}
	}

	private static class AttributeValueData {
		String value;
		String[] extraAttributes;
	}

	private AttributeData parseAttribute(String attributeString) {
		int pos = attributeString.indexOf(':');
		if (pos < 0) {
			return null;
		}
		AttributeData attributeData = new AttributeData();
		attributeData.name = attributeString.substring(0, pos);
		String[] attributeValueStrings = attributeString.substring(pos + 1).trim().split(",");
		attributeData.values = new AttributeValueData[attributeValueStrings.length];
		for (int i = 0; i < attributeValueStrings.length; i++) {
			AttributeValueData attributeValueData = new AttributeValueData();
			String[] attributeValues = attributeValueStrings[i].split(";");
			attributeValueData.value = attributeValues[0];
			if (attributeValues.length > 1) {
				String[] extraAttributes = new String[(attributeValues.length - 1) * 2];
				for (int j = 1; j < attributeValues.length; j++) {
					String s = attributeValues[j];
					int pos2 = s.indexOf('=');
					extraAttributes[((j - 1) * 2) + 0] = s.substring(0, pos2 - (s.charAt(pos2 - 1) == ':' ? 1 : 0));
					String valueString = s.substring(pos2 + 1);
					if (valueString.charAt(0) == '\"' && valueString.charAt(valueString.length() - 1) == '\"') {
						valueString = valueString.substring(1, valueString.length() - 1);
					}
					extraAttributes[((j - 1) * 2) + 1] = valueString;
				}
				attributeValueData.extraAttributes = extraAttributes;
			}
			attributeData.values[i] = attributeValueData;
		}
		return attributeData;
	}

	private void handleAttributeData(AttributeData attributeData) {
		String attributeName = attributeData.name;
		int pos = attributeName.indexOf('-');
		EObject eObject = null;
		EStructuralFeature feature = null;
		EClass eClass = (EClass) findClassifier(attributeName.substring(0, pos), true);
		if (eClass != null) {
			String featureName = toFirstLower(attributeName.substring(pos + 1));
			feature = eClass.getEStructuralFeature(featureName);
			List<? extends EObject> eObjects = getContents();
			for (int i = eObjects.size() - 1; i >= 0; i--) {
				eObject = eObjects.get(i);
				if (eClass.isInstance(eObject)) {
					break;
				}
				eObject = null;
			}
			if (eObject == null) {
				eObject = EcoreUtil.create(eClass);
				getContents().add(eObject);
			}
		} else {
			List<? extends EObject> eObjects = getContents();
			String featureName = camelCasify(attributeName, false);
			for (int i = eObjects.size() - 1; i >= 0; i--) {
				eObject = eObjects.get(i);
				feature = eObject.eClass().getEStructuralFeature(featureName);
				if (feature != null) {
					break;
				}
				eObject = null;
			}
		}
		if (feature != null) {
			setFeatures(eObject, feature, attributeData.values);
		} else {
			GenericAttribute attribute = OsgiFactory.eINSTANCE.createGenericAttribute();
			attribute.setName(attributeData.name);
			for (int i = 0; i < attributeData.values.length; i++) {
				GenericAttributeValue attributeValue = OsgiFactory.eINSTANCE.createGenericAttributeValue();
				AttributeValueData attributeValueData = attributeData.values[i];
				attributeValue.setValue(attributeValueData.value);
				if (attributeValueData.extraAttributes != null) {
					attributeValue.getExtraAttributes().addAll(Arrays.asList(attributeValueData.extraAttributes));
				}
				attribute.getValues().add(attributeValue);
			}
			if (! (eObject instanceof GenericAttributesContainer)) {
				eObject = getContents().get(0);
			}
			if (eObject instanceof GenericAttributesContainer) {
				((GenericAttributesContainer) eObject).getGenericAttributes().add(attribute);
			}
		}
	}

	private void setFeatures(EObject eObject, EStructuralFeature feature, AttributeValueData[] values) {
		EClassifier featureType = feature.getEType();
		for (AttributeValueData value : values) {
			String[] extraAttributes = value.extraAttributes;
			if (featureType instanceof EDataType) {
				setAttribute(eObject, feature, value.value);
				Object featureValue = EcoreUtil.createFromString((EDataType) featureType, value.value);
				setFeature(eObject, feature, featureValue);
				if (extraAttributes != null) {
					for (int i = 0; i < extraAttributes.length; i += 2) {
						setAttribute(eObject, extraAttributes[i], extraAttributes[i + 1]);
					}
				}
			} else {
				EObject valueObject = EcoreUtil.create((EClass) featureType);
				setFeature(eObject, feature, valueObject);
				for (EAttribute attribute : valueObject.eClass().getEAllAttributes()) {
					try {
						setAttribute(valueObject, attribute, value.value);
						break;
					} catch (Exception e) {
					}
				}
				if (extraAttributes != null) {
					for (int i = 0; i < extraAttributes.length; i += 2) {
						setAttribute(valueObject, extraAttributes[i], extraAttributes[i + 1]);
					}
				}
			}
		}
	}

	private void setAttribute(EObject eObject, String featureName, String valueString) {
		featureName = camelCasify(featureName, false);
		EStructuralFeature feature = eObject.eClass().getEStructuralFeature(featureName);
		if (feature != null) {
			setAttribute(eObject, feature, valueString);			
		}
	}

	private String camelCasify(String s, Boolean initial) {
		int start = 0;
		if (initial != null) {
			s = casify(s, 0, initial);
		}
		while (start < s.length()) {
			int end = s.indexOf('-', start);
			if (end > start && end < s.length() + 2) {
				s = s.substring(0, end) + casify(s, end + 1, true);
				start = end;
			} else {
				start = s.length();
			}
		}
		return s;
	}

	private String casify(String s, int pos, Boolean firstCase) {
		String sub = (pos < s.length() ? s.substring(pos) : "");
		if (sub.length() > 0 && firstCase != null) {
			char first = sub.charAt(0);
			if (! (firstCase ? Character.isUpperCase(first) : Character.isLowerCase(first))) {
				sub = (firstCase ? Character.toUpperCase(first) : Character.toLowerCase(first)) + sub.substring(1);
			}
		}
		return sub;
	}
	
	private void setAttribute(EObject eObject, EStructuralFeature feature, String valueString) {
		EClassifier featureType = feature.getEType();
		if (featureType instanceof EDataType) {
			Object featureValue = EcoreUtil.createFromString((EDataType) featureType, valueString);
			setFeature(eObject, feature, featureValue);
		}
	}

	private void setFeature(EObject eObject, EStructuralFeature feature, Object featureValue) {
		if (feature.isMany()) {
			((EList<Object>) eObject.eGet(feature)).add(featureValue);
		} else {
			eObject.eSet(feature, featureValue);
		}
	}

	private String toFirstLower(String s) {
		char firstChar = s.charAt(0);
		if (Character.isUpperCase(firstChar)) {
			return Character.toLowerCase(firstChar) + s.substring(1);
		}
		return s;
	}
	
	private EClassifier findClassifier(String className, Boolean eClass) {
		EClassifier eClassifier = OsgiPackage.eINSTANCE.getEClassifier(className);
		return (eClass != null && (eClass ? eClassifier instanceof EClass : eClassifier instanceof EDataType) ? eClassifier : null);
	}
	
	@Override
	protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
		throw new UnsupportedOperationException();
	}

} //OsgiResourceImpl
