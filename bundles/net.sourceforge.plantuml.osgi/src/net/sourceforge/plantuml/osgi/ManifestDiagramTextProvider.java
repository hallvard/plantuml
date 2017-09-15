package net.sourceforge.plantuml.osgi;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPathEditorInput;
import org.osgi.scr.Component;
import org.osgi.scr.util.ScrResourceFactoryImpl;

import net.sourceforge.plantuml.text.AbstractDiagramTextProvider;
import no.hal.osgi.Bundle;
import no.hal.osgi.ExportedPackage;
import no.hal.osgi.OsgiPackage;
import no.hal.osgi.RequiredBundle;
import no.hal.osgi.ServiceComponent;
import no.hal.osgi.util.OsgiResourceImpl;

public class ManifestDiagramTextProvider extends AbstractDiagramTextProvider {

	public ManifestDiagramTextProvider() {
		super(null);
	}

	@Override
	public boolean supportsSelection(ISelection selection) {
		return false;
	}
	
	@Override
	public boolean supportsEditor(IEditorPart editorPart) {
		if (editorPart.getEditorInput() instanceof IPathEditorInput) {
			IPathEditorInput editorInput = (IPathEditorInput) editorPart.getEditorInput();
			if ("MF".equals(editorInput.getPath().getFileExtension())) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected String getDiagramText(IEditorPart editorPart, IEditorInput editorInput, ISelection selection) {
		if (editorInput instanceof IPathEditorInput) {
			IPath path = ((IPathEditorInput) editorInput).getPath();
			URI uri = URI.createFileURI(path.toString());
			ResourceSet resSet = new ResourceSetImpl();
			resSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new ScrResourceFactoryImpl());
			Resource res = resSet.getResource(uri, true);
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
		Bundle bundle = (Bundle) EcoreUtil.getObjectByType(res.getContents(), OsgiPackage.eINSTANCE.getBundle());
		appendModule(bundle, buffer);
		Collection<RequiredBundle> requiredBundles = EcoreUtil.getObjectsByType(bundle.getRequireBundle(), OsgiPackage.eINSTANCE.getRequiredBundle());
		for (RequiredBundle requiredBundle : requiredBundles) {
			appendModule(requiredBundle, buffer);
			appendDependency(bundle, requiredBundle, buffer);
		}
		return buffer.toString();
	}

	protected void appendDependency(String source, String target, String label, StringBuilder buffer) {
		buffer.append(source);
		buffer.append(" --> ");
		buffer.append(target);
		if (label != null) {
			buffer.append(": ");
			buffer.append(label);			
		}
		buffer.append("\n");
	}

	protected String getBundleId(Bundle bundle) {
		String id = "bundle." + bundle.getSymbolicName();
		if (bundle.getVersion() != null) {
			id += "." + bundle.getVersion();
		}
		return id;
	}

	protected String getBundleId(RequiredBundle bundle) {
		String id = "bundle." + bundle.getName();
//		if (bundle.getVersion() != null) {
//			id += "." + bundle.getVersion();
//		}
		return id;
	}
	
	protected void appendDependency(Bundle bundle, RequiredBundle requiredBundle, StringBuilder buffer) {
		String versionString = (requiredBundle.getBundleVersion() != null ? String.valueOf(requiredBundle.getBundleVersion()) : null);
		appendDependency(getBundleId(bundle), getBundleId(requiredBundle), versionString, buffer);
	}

	protected void appendModule(RequiredBundle bundle, StringBuilder buffer) {
		buffer.append("artifact \"");
		buffer.append(bundle.getName());
		if (bundle.getBundleVersion() != null) {
			buffer.append("\\n");
			buffer.append(bundle.getBundleVersion());
		}
		buffer.append("\" as ");
		buffer.append(getBundleId(bundle));
		buffer.append("\n");
	}

	private OsgiComponentDiagramTextProvider componentDiagramTextProvider = new OsgiComponentDiagramTextProvider();
	
	protected void appendModule(Bundle bundle, StringBuilder buffer) {
		buffer.append("artifact \"");
		buffer.append(bundle.getSymbolicName());
		if (bundle.getVersion() != null) {
			buffer.append("\\n");
			buffer.append(bundle.getVersion());
		}
		buffer.append("\" as ");
		buffer.append(getBundleId(bundle));
		buffer.append(" {\n");
		for (ExportedPackage pack : bundle.getExportPackage()) {
			buffer.append("\tpackage ");
			buffer.append(pack.getName());
			buffer.append("\n");
		}
		for (ServiceComponent sc : bundle.getServiceComponent()) {
			Component comp = sc.getComponent();
			componentDiagramTextProvider.appendComponent(comp, buffer);
		}
		buffer.append("}\n");
	}
}
