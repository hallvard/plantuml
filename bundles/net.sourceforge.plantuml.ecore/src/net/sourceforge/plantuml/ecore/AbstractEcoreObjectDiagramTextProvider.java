package net.sourceforge.plantuml.ecore;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import net.sourceforge.plantuml.text.AbstractObjectDiagramTextProvider;

public abstract class AbstractEcoreObjectDiagramTextProvider extends AbstractObjectDiagramTextProvider {

	protected AbstractEcoreObjectDiagramTextProvider(Class<?> editorType) {
		super(editorType);
	}

	public boolean supportsSelection(ISelection selection) {
		return selection instanceof IStructuredSelection && ((IStructuredSelection) selection).getFirstElement() instanceof EPackage;
	}

	private int maxResourceCount = 1, maxObjectCount = 1;

	private List<EClassifier> eObjects;
	
	protected String getDiagramText(ResourceSet resourceSet) {
		int resourceCount = 0, objectCount = 0;
		this.eObjects = new ArrayList<EClassifier>();
		outer: for (Resource resource : resourceSet.getResources()) {
			EObject eObject = null;
			for (EObject root : resource.getContents()) {
				if (! (root instanceof EPackage)) {
					eObject = root;
					init(eObject);
					objectCount++;
					if (objectCount == maxObjectCount) {
						break outer;
					}
				}
			}
			if (eObject != null) {
				resourceCount++;
				if (resourceCount == maxResourceCount) {
					break;
				}
			}
		}
		String result = eObjects.size() > 0 ? getDiagramText(GEN_ATTRIBUTES | GEN_LINKS) : null;
		this.eObjects = null;
		return result;
	}

	protected String getDiagramText(EObject eObject) {
		this.eObjects = new ArrayList<EClassifier>();
		init(eObject);
		String result = eObjects.size() > 0 ? getDiagramText(GEN_ATTRIBUTES | GEN_LINKS) : null;
		this.eObjects = null;
		return result;
	}

	private void init(EObject eObject) {
		addContained(eObject);
	}
	
	protected void addContained(EObject eObject) {
		TreeIterator<EObject> it = eObject.eAllContents();
		while (it.hasNext()) {
			EObject contained = it.next();
			eObjects.add((EClassifier) contained);
		}
	}
	
	protected String getDiagramText(int genFlags) {
		StringBuilder buffer = new StringBuilder();
		for (EObject eObject : eObjects) {
		}
		return buffer.toString();
	}

	protected void appendObject(EObject eObject, int genFlags, StringBuilder buffer) {
		appendObject(eObject, genFlags, buffer);
		if (includes(genFlags, GEN_ATTRIBUTES)) {
		}
		appendObjectEnd(buffer);
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
