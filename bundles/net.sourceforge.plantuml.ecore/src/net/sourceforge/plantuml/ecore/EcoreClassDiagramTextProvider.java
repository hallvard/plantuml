package net.sourceforge.plantuml.ecore;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import net.sourceforge.plantuml.eclipse.utils.DiagramTextProvider2;

public class EcoreClassDiagramTextProvider extends AbstractEcoreClassDiagramTextProvider implements DiagramTextProvider2 {

	@Override
	public boolean supportsPath(IPath path) {
		return "ecore".equals(path.getFileExtension());
	}

	@Override
	public String getDiagramText(IPath path) {
		URI uri = URI.createPlatformResourceURI(path.toString(), true);
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResource(uri, true);
		String diagramText = getDiagramText(resourceSet);
		return ensureDiagramText(diagramText);
	}
}
