package net.sourceforge.plantuml.osgi;

import java.io.IOException;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.osgi.scr.Component;
import org.osgi.scr.DocumentRoot;
import org.osgi.scr.Provide;
import org.osgi.scr.ScrPackage;
import org.osgi.scr.util.ScrResourceFactoryImpl;

import net.sourceforge.plantuml.text.AbstractDiagramTextProvider;

public class OsgiComponentDiagramTextProvider extends AbstractDiagramTextProvider {

	public OsgiComponentDiagramTextProvider() {
		super(null);
	}

	@Override
	public boolean supportsSelection(ISelection selection) {
		return false;
	}
	
	@Override
	public boolean supportsPart(IWorkbenchPart part) {
		if (part instanceof IEditorPart) {
			IEditorPart editorPart = (IEditorPart) part;	
			if (editorPart.getEditorInput() instanceof IPathEditorInput) {
				IPathEditorInput editorInput = (IPathEditorInput) editorPart.getEditorInput();
				if ("xml".equals(editorInput.getPath().getFileExtension())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	protected String getDiagramText(IEditorPart editorPart, IEditorInput editorInput, ISelection selection, Map<String, Object> markerAttributes) {
		if (editorInput instanceof IPathEditorInput) {
			IPath path = ((IPathEditorInput) editorInput).getPath();
			URI uri = URI.createFileURI(path.toString());
			ResourceSet resourceSet = new ResourceSetImpl();
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new ScrResourceFactoryImpl());
			Resource res = resourceSet.getResource(uri, true);
			try {
				res.load(null);
				return getDiagramText(res);
			} catch (IOException e) {
				System.err.println(e);
			}
		}
		return null;
	}

	protected String getDiagramText(Resource res) {
		StringBuilder buffer = new StringBuilder();
		DocumentRoot root = (DocumentRoot) EcoreUtil.getObjectByType(res.getContents(), ScrPackage.eINSTANCE.getDocumentRoot());
		Component comp = (root != null ? root.getComponent() : (Component) EcoreUtil.getObjectByType(res.getContents(), ScrPackage.eINSTANCE.getComponent()));
		appendComponent(comp, buffer);
		return buffer.toString();
	}

	void appendComponent(Component comp, StringBuilder buffer) {
		buffer.append("component ");
		buffer.append(comp.getName());
		buffer.append("\n");
		if (comp.getService() != null) {
			for (Provide provides : comp.getService().getProvide()) {
				buffer.append("interface ");
				buffer.append(provides.getInterface());
				buffer.append("\n");
			}
	
			for (Provide provides : comp.getService().getProvide()) {
				buffer.append(comp.getName());
				buffer.append(" -- ");
				buffer.append(provides.getInterface());
				buffer.append("\n");
			}
		}
	}
}
