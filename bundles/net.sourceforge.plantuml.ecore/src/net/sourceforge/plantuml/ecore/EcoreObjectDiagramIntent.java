package net.sourceforge.plantuml.ecore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;

import net.sourceforge.plantuml.text.AbstractObjectDiagramIntent;

public class EcoreObjectDiagramIntent extends AbstractObjectDiagramIntent<EObject> {

	public EcoreObjectDiagramIntent(final EObject source) {
		super(source, "Object diagram");
	}

	@Override
	public String getDiagramText() {
		return getDiagramText(getSource());
	}

	private final List<EObject> eObjects = new ArrayList<EObject>();

	protected String getDiagramText(final Iterator<?> it) {
		return getObjectDiagramText(null, it);
	}
	protected String getDiagramText(final EObject eObject) {
		return getObjectDiagramText(eObject, eObject.eAllContents());
	}

	protected String getObjectDiagramText(final EObject root, final Iterator<?> it) {
		this.eObjects.clear();
		if (root != null) {
			this.eObjects.add(root);
		}
		while (it.hasNext()) {
			final Object contained = it.next();
			if (contained instanceof EObject) {
				eObjects.add((EObject) contained);
			}
		}
		final String result = eObjects.size() > 0 ? getDiagramText(GEN_ATTRIBUTES | GEN_LINKS | GEN_OBJECT_HYPERLINKS) : null;
		return result;
	}

	private final EcoreDiagramHelper diagramHelper = new EcoreDiagramHelper();

	private final Map<EObject, String> idMap = new HashMap<EObject, String>();

	protected String getId(final EObject eObject) {
		return idMap.get(eObject);
	}

	protected String getOrCreateId(final EObject eObject) {
		String id = getId(eObject);
		if (id == null) {
			id = String.valueOf(idMap.keySet().size() + 1);
			idMap.put(eObject, id);
		}
		return id;
	}

	private void initIdMap() {
		idMap.clear();
		for (final EObject eObject : eObjects) {
			getOrCreateId(eObject);
		}
	}

	protected String getDiagramText(final int genFlags) {
		initIdMap();
		final StringBuilder buffer = new StringBuilder();
		for (final EObject eObject : eObjects) {
			appendObject(eObject, genFlags, buffer);
		}
		if (includes(genFlags, GEN_LINKS)) {
			for (final EObject eObject : eObjects) {
				appendLinks(eObject, genFlags, buffer);
			}
		}
		return buffer.toString();
	}

	private static String MANY_VALUE_START = "[", MANY_VALUE_END = "]";

	protected String getName(final EObject eObject) {
		return "o" + getId(eObject);
	}

	protected void appendObject(final EObject eObject, final int genFlags, final StringBuilder buffer) {
		final EClass eClass = eObject.eClass();
		appendObjectStart(getOrCreateId(eObject), getName(eObject), getTypeName(eClass), buffer);
		if (includes(genFlags, GEN_ATTRIBUTES)) {
			for (final EAttribute attr: eClass.getEAllAttributes()) {
				final Object value = eObject.eGet(attr);
				final String valueString = getValueString(value, attr);
				appendAttribute(attr.getName(), valueString, buffer);
			}
		}
		appendObjectEnd(buffer);
		if (includes(genFlags, GEN_OBJECT_HYPERLINKS)) {
			final String link = diagramHelper.getEObjectHyperlink(eObject);
			appendNameLink(getName(eObject), link, buffer);
		}
	}

	protected int maxAttributeLength = 40, maxAttributeValueLenght = 25;

	protected String truncateString = "\u2026"; // ...

	protected String getValueString(final Object value, final EAttribute attr) {
		String valueString = "?";
		if (attr.isMany()) {
			valueString = MANY_VALUE_START;
			for (final Object element : (Iterable<?>) value) {
				if (! valueString.equals(MANY_VALUE_START)) {
					valueString += ", ";
				}
				valueString += convertToString(attr, element);
			}
			valueString += MANY_VALUE_END;
		} else {
			valueString = convertToString(attr, value);
		}
		valueString = truncate(valueString, maxAttributeLength, truncateString);
		return valueString;
	}

	private String truncate(String s, final int maxLength, final String ts) {
		if (s != null && s.length() > maxLength) {
			s = s.substring(0, maxLength - ts.length()) + ts;
		}
		return s;
	}

	protected Collection<String> replacements = Arrays.asList(
			// see https://en.wikipedia.org/wiki/List_of_Unicode_characters#Arrows
			"\n", "\u21B5",
			"\t", "\u21A6",
			"\b", "\u21A4",
			"\r", "\u2190",
			"\f", "\u2193"
			);

	protected String convertToString(final EAttribute attr, final Object value) {
		String converted = EcoreUtil.convertToString(attr.getEAttributeType(), value);
		if (value instanceof String || value instanceof Character) {
			if (replacements != null) {
				final Iterator<String> it = replacements.iterator();
				while (it.hasNext()) {
					converted = converted.replace(it.next(), it.next());
				}
			}
			final String valueClassName = attr.getEAttributeType().getInstanceClassName();
			final String quote = "java.lang.String".equals(valueClassName) ? "\"" : null;
			if (quote != null) {
				converted = quote + converted + quote;
			}
		}
		converted = truncate(converted, maxAttributeValueLenght, truncateString);
		return converted;
	}

	protected void appendLinks(final EObject eObject, final int genFlags, final StringBuilder buffer) {
		final EClass eClass = eObject.eClass();
		for (final EReference ref: eClass.getEAllReferences()) {
			final Object value = eObject.eGet(ref);
			if (ref.isContainer()) {
				//				if (ref.getEOpposite() == null) {
				//					appendLink((EObject) value, eObject, ref, genFlags, buffer);
				//				}
			} else if (ref.isMany()) {
				for (final EObject element : (Iterable<? extends EObject>) value) {
					appendLink(eObject, element, ref, genFlags, buffer);
				}
			} else if (value != null) {
				appendLink(eObject, (EObject) value, ref, genFlags, buffer);
			}
		}
	}

	protected void appendLink(final EObject eObject, final EObject other, final EReference ref, final int genFlags, final StringBuilder buffer) {
		String otherId = getId(other);
		if (otherId == null) {
			otherId = getOrCreateId(other);
			appendObject(other, genFlags & ~GEN_ATTRIBUTES, buffer);
		}
		final boolean isBi = ref.getEOpposite() != null;
		final String startRole = (ref.isContainment() && isBi ? ref.getEOpposite().getName() : (ref.isContainer() ? ref.getName() : null)), endRole = (ref.isContainer() ? null : ref.getName());
		appendRelation(getName(eObject), ref.isContainment(), startRole, RELATION_LINE + (ref.isContainment() || isBi ? "" : ">"), null, getName(other), false, endRole, null, buffer);
	}

	protected String getTypeName(final EClassifier type) {
		String typeName = null;
		if (type != null) {
			if (type instanceof EDataType) {
				typeName = type.getInstanceClassName();
			}
			if (typeName == null) {
				typeName = type.getName();
			}
		}
		return getSimpleName(typeName);
	}
}
