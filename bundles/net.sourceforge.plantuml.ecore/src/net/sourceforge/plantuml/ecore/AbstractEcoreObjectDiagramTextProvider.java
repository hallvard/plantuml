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
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import net.sourceforge.plantuml.text.AbstractObjectDiagramTextProvider;

public abstract class AbstractEcoreObjectDiagramTextProvider extends AbstractObjectDiagramTextProvider {

	protected AbstractEcoreObjectDiagramTextProvider(Class<?> editorType) {
		super(editorType);
	}

	public boolean supportsSelection(ISelection selection) {
		return selection instanceof IStructuredSelection && (! AbstractEcoreClassDiagramTextProvider.isEcoreClassDiagramObject(((IStructuredSelection) selection).getFirstElement()));
	}

	private List<EObject> eObjects = new ArrayList<EObject>();

	protected String getDiagramText(Iterator<?> it) {
		return getObjectDiagramText(null, it);
	}
	protected String getDiagramText(EObject eObject) {
		return getObjectDiagramText(eObject, eObject.eAllContents());
	}

	protected String getObjectDiagramText(EObject root, Iterator<?> it) {
		this.eObjects.clear();
		if (root != null) {
			this.eObjects.add(root);
		}
		while (it.hasNext()) {
			Object contained = it.next();
			if (contained instanceof EObject) {
				eObjects.add((EObject) contained);
			}
		}
		String result = eObjects.size() > 0 ? getDiagramText(GEN_ATTRIBUTES | GEN_LINKS) : null;
		return result;		
	}
	

	private EcoreDiagramHelper diagramHelper = new EcoreDiagramHelper();

	private Map<EObject, String> idMap = new HashMap<EObject, String>();

	protected String getId(EObject eObject) {
		return idMap.get(eObject);
	}
	
	protected String getOrCreateId(EObject eObject) {
		String id = getId(eObject);
		if (id == null) {
			id = String.valueOf(idMap.keySet().size() + 1);
			idMap.put(eObject, id);
		}
		return id;
	}
	
	private void initIdMap() {
		idMap.clear();
		for (EObject eObject : eObjects) {
			getOrCreateId(eObject);
		}
	}

	protected String getDiagramText(int genFlags) {
		initIdMap();
		StringBuilder buffer = new StringBuilder();
		for (EObject eObject : eObjects) {
			appendObject(eObject, genFlags, buffer);
		}
		if (includes(genFlags, GEN_LINKS)) {
			for (EObject eObject : eObjects) {
				appendLinks(eObject, genFlags, buffer);
			}
		}
		return buffer.toString();
	}

	private static String MANY_VALUE_START = "[", MANY_VALUE_END = "]";

	protected String getName(EObject eObject) {
		return "o" + getId(eObject);
	}
	
	private void appendObject(EObject eObject, int genFlags, StringBuilder buffer) {
		EClass eClass = eObject.eClass();
		appendObjectStart(getOrCreateId(eObject), getName(eObject), getTypeName(eClass), null, buffer);
		if (includes(genFlags, GEN_ATTRIBUTES)) {
			for (EAttribute attr: eClass.getEAllAttributes()) {
				Object value = eObject.eGet(attr);
				String valueString = getValueString(value, attr);
				appendAttribute(attr.getName(), valueString, buffer);
			}
		}
		appendObjectEnd(buffer);
	}
	
	protected int maxAttributeLength = 40, maxAttributeValueLenght = 25;
	
	protected String truncateString = "\u2026"; // ...
	
	private String getValueString(Object value, EAttribute attr) {
		String valueString = "?";
		if (attr.isMany()) {
			valueString = MANY_VALUE_START;
			for (Object element : (Iterable<?>) value) {
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

	private String truncate(String s, int maxLength, String ts) {
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

	protected String convertToString(EAttribute attr, Object value) {
		String converted = EcoreUtil.convertToString(attr.getEAttributeType(), value);
		if (value instanceof String || value instanceof Character) {
			if (replacements != null) {
				Iterator<String> it = replacements.iterator();
				while (it.hasNext()) {
					converted = converted.replace(it.next(), it.next());
				}
			}
			String valueClassName = attr.getEAttributeType().getInstanceClassName();
			String quote = "java.lang.String".equals(valueClassName) ? "\"" : null;
			if (quote != null) {
				converted = quote + converted + quote;
			}
		}
		converted = truncate(converted, maxAttributeValueLenght, truncateString);
		return converted;
	}
	
	protected void appendLinks(EObject eObject, int genFlags, StringBuilder buffer) {
		EClass eClass = eObject.eClass();
		for (EReference ref: eClass.getEAllReferences()) {
			Object value = eObject.eGet(ref);
			if (ref.isContainer()) {
//				if (ref.getEOpposite() == null) {
//					appendLink((EObject) value, eObject, ref, genFlags, buffer);
//				}
			} else if (ref.isMany()) {
				for (EObject element : (Iterable<? extends EObject>) value) {
					appendLink(eObject, element, ref, genFlags, buffer);
				}
			} else if (value != null) {
				appendLink(eObject, (EObject) value, ref, genFlags, buffer);
			}
		}
	}

	protected void appendLink(EObject eObject, EObject other, EReference ref, int genFlags, StringBuilder buffer) {
		String otherId = getId(other);
		if (otherId == null) {
			otherId = getOrCreateId(other);
			appendObject(other, genFlags & ~GEN_ATTRIBUTES, buffer);
		}
		boolean isBi = ref.getEOpposite() != null;
		String startRole = (ref.isContainment() && isBi ? ref.getEOpposite().getName() : (ref.isContainer() ? ref.getName() : null)), endRole = (ref.isContainer() ? null : ref.getName());
		appendRelation(getName(eObject), ref.isContainment(), startRole, RELATION_LINE + (ref.isContainment() || isBi ? "" : ">"), null, getName(other), false, endRole, null, buffer);
	}

	protected String getTypeName(EClassifier type) {
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
