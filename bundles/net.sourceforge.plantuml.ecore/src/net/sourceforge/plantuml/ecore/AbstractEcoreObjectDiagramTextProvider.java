package net.sourceforge.plantuml.ecore;

import java.util.ArrayList;
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

	private void initIdMap() {
		idMap.clear();
		for (int i = 0; i < eObjects.size(); i++) {
			EObject eObject = eObjects.get(i);
			idMap.put(eObject, String.valueOf(i + 1));
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

	private void appendObject(EObject eObject, int genFlags, StringBuilder buffer) {
		EClass eClass = eObject.eClass();
		String id = idMap.get(eObject);
		appendObjectStart(id, "o" + id, getTypeName(eClass), null, buffer);
		if (includes(genFlags, GEN_ATTRIBUTES)) {
			for (EAttribute attr: eClass.getEAllAttributes()) {
				Object value = eObject.eGet(attr);
				String valueString = getValueString(value, attr);
				appendAttribute(attr.getName(), valueString, buffer);
			}
		}
		appendObjectEnd(buffer);
	}

	private String getValueString(Object value, EAttribute attr) {
		String valueString = "?";
		if (attr.isMany()) {
			valueString = MANY_VALUE_START;
			for (Object element : (Iterable<?>) value) {
				if (! valueString.equals(MANY_VALUE_START)) {
					valueString += ", ";
				}
				valueString += EcoreUtil.convertToString(attr.getEAttributeType(), element);
			}
			valueString += MANY_VALUE_END;
		} else {
			valueString = EcoreUtil.convertToString(attr.getEAttributeType(), value);					
		}
		return valueString;
	}
	
	protected void appendLinks(EObject eObject, int genFlags, StringBuilder buffer) {
		EClass eClass = eObject.eClass();
		for (EReference ref: eClass.getEAllReferences()) {
			Object value = eObject.eGet(ref);
			if (ref.isContainer()) {
				// ???
			} else if (ref.isMany()) {
				for (EObject element : (Iterable<? extends EObject>) value) {
					appendLink(eObject, element, ref, buffer);
				}
			} else {
				appendLink(eObject, (EObject) value, ref, buffer);
			}
		}
	}

	protected void appendLink(EObject eObject, EObject other, EReference ref, StringBuilder buffer) {
		appendRelation("o" + idMap.get(eObject), ref.isContainment(), null, RELATION_LINE, null, "o" + idMap.get(other), false, ref.getName(), null, buffer);
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
