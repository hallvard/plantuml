package net.sourceforge.plantuml.eclipse.utils;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IStorageEditorInput;

public class WorkbenchEditorPartDiagramIntentProviderContext extends WorkbenchPartDiagramIntentProviderContext {

	private IPath path;

	public WorkbenchEditorPartDiagramIntentProviderContext(final IEditorPart editorPart, final ISelection selection) {
		super(editorPart, selection);
		final IEditorInput editorInput = editorPart.getEditorInput();
		if (editorInput instanceof IStorageEditorInput) {
			try {
				this.path = ((IStorageEditorInput) editorInput).getStorage().getFullPath();
			} catch (final CoreException e) {
			}
		}
	}

	public IEditorPart getEditorPart() {
		return (IEditorPart) getWorkbenchPart();
	}

	public IPath getPath() {
		return path;
	}
}
