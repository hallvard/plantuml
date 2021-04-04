package net.sourceforge.plantuml.ecore;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;

import net.sourceforge.plantuml.util.DiagramIntent;

public class EcoreObjectDiagramIntentProvider extends AbstractEcoreDiagramIntentProvider {

	public EcoreObjectDiagramIntentProvider() {
		super();
	}

	protected EcoreObjectDiagramIntentProvider(final Class<?> editorType) {
		super(editorType);
	}

	@Override
	protected boolean supportsPath(final IPath path) {
		return "xmi".equals(path.getFileExtension());
	}

	@Override
	protected boolean supportsEObject(final EObject object) {
		return true;
	}

	@Override
	protected Collection<DiagramIntent> getDiagramInfos(final EObject root) {
		return Collections.singletonList(new EcoreObjectDiagramIntent(root));
	}
}
