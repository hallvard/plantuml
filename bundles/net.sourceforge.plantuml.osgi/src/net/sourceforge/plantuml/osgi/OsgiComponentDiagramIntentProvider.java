package net.sourceforge.plantuml.osgi;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPathEditorInput;

import net.sourceforge.plantuml.eclipse.utils.WorkbenchEditorPartDiagramIntentProviderContext;
import net.sourceforge.plantuml.text.AbstractDiagramIntentProvider;
import net.sourceforge.plantuml.util.DiagramIntent;

public class OsgiComponentDiagramIntentProvider extends AbstractDiagramIntentProvider {

	public OsgiComponentDiagramIntentProvider() {
		super(null);
	}

	@Override
	public boolean supportsEditor(final IEditorPart editorPart) {
		if (editorPart.getEditorInput() instanceof IPathEditorInput) {
			final IPathEditorInput editorInput = (IPathEditorInput) editorPart.getEditorInput();
			if ("xml".equals(editorInput.getPath().getFileExtension())) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected Collection<DiagramIntent> getDiagramInfos(final WorkbenchEditorPartDiagramIntentProviderContext context) {
		final IEditorInput editorInput = context.getEditorPart().getEditorInput();
		if (editorInput instanceof IPathEditorInput) {
			final IPath path = ((IPathEditorInput) editorInput).getPath();
			final URI uri = URI.createFileURI(path.toString());
			return Collections.singletonList(new OsgiComponentDiagramIntent(uri));
		}
		return null;
	}
}
