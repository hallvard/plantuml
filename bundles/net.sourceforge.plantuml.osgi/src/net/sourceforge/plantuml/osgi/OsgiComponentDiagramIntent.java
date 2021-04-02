package net.sourceforge.plantuml.osgi;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.osgi.scr.Component;
import org.osgi.scr.DocumentRoot;
import org.osgi.scr.Provide;
import org.osgi.scr.ScrPackage;
import org.osgi.scr.util.ScrResourceFactoryImpl;

import net.sourceforge.plantuml.text.AbstractDiagramIntent;

public class OsgiComponentDiagramIntent extends AbstractDiagramIntent<URI> {

	public OsgiComponentDiagramIntent(final URI source) {
		super(source, "Component diagram");
	}

	@Override
	public String getDiagramText() {
		final ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new ScrResourceFactoryImpl());
		final Resource res = resourceSet.getResource(getSource(), true);
		try {
			res.load(null);
			return getDiagramText(res);
		} catch (final IOException e) {
		}
		return null;
	}

	protected String getDiagramText(final Resource res) {
		final StringBuilder buffer = new StringBuilder();
		final DocumentRoot root = (DocumentRoot) EcoreUtil.getObjectByType(res.getContents(), ScrPackage.eINSTANCE.getDocumentRoot());
		final Component comp = (root != null ? root.getComponent() : (Component) EcoreUtil.getObjectByType(res.getContents(), ScrPackage.eINSTANCE.getComponent()));
		appendComponent(comp, buffer);
		return buffer.toString();
	}

	void appendComponent(final Component comp, final StringBuilder buffer) {
		buffer.append("component ");
		buffer.append(comp.getName());
		buffer.append("\n");
		if (comp.getService() != null) {
			for (final Provide provides : comp.getService().getProvide()) {
				buffer.append("interface ");
				buffer.append(provides.getInterface());
				buffer.append("\n");
			}

			for (final Provide provides : comp.getService().getProvide()) {
				buffer.append(comp.getName());
				buffer.append(" -- ");
				buffer.append(provides.getInterface());
				buffer.append("\n");
			}
		}
	}
}
