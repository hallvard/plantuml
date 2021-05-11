package net.sourceforge.plantuml.eclipse.utils;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.IWorkbenchPart;

public class WorkbenchPartDiagramIntentProviderContext extends LocationDiagramIntentProviderContext {

	private final IWorkbenchPart workbenchPart;
	private final ISelection selection;

	public WorkbenchPartDiagramIntentProviderContext(final IWorkbenchPart workbenchPart, final ISelection selection) {
		this.workbenchPart = workbenchPart;
		this.selection = (selection == StructuredSelection.EMPTY ? null : selection);
		if (workbenchPart instanceof IEditorPart) {
			final IEditorInput editorInput = ((IEditorPart) workbenchPart).getEditorInput();
			if (editorInput instanceof IStorageEditorInput) {
				try {
					setWorkspaceLocation(((IStorageEditorInput) editorInput).getStorage().getFullPath());
				} catch (final CoreException e) {
					throw new RuntimeException(e);
				}
			} else if (editorInput instanceof IPathEditorInput) {
				setFileLocation(((IPathEditorInput) editorInput).getPath());
			} else if (editorInput instanceof IURIEditorInput) {
				setLocation(((IURIEditorInput) editorInput).getURI());
			}
		}
	}

	public IWorkbenchPart getWorkbenchPart() {
		return workbenchPart;
	}

	public ISelection getSelection() {
		return selection;
	}
}
