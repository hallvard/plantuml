package net.sourceforge.plantuml.eclipse.views;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import net.sourceforge.plantuml.eclipse.Activator;
import net.sourceforge.plantuml.eclipse.utils.DiagramTextProvider;

public abstract class AbstractDiagramSourceView extends ViewPart {
	
	public boolean isLinkedToActiveEditor() {
		return true;
	}

	@Override
	public void createPartControl(Composite parent) {
		if (isLinkedToActiveEditor()) {
			registerListeners();
			// without this it deadlocked during startup
			parent.getDisplay().asyncExec(new Runnable() {
				@Override
				public void run() {
					updateDiagramText(true, null, null);
				}
			});
		}
	}

	protected void registerListeners() {
		getSite().getPage().addPartListener(partListener);
		getSite().getPage().addPostSelectionListener(diagramTextChangedListener);
	}
	
	@Override
	public void dispose() {
		if (lastEditor != null) {
			lastEditor.removePropertyListener(diagramTextChangedListener);
		}
		getSite().getPage().removePartListener(partListener);
		getSite().getPage().removePostSelectionListener(diagramTextChangedListener);
	}
	
	protected abstract void updateDiagramText(String text);
	public abstract String getDiagramText();

	private IPartListener partListener = new IPartListener() {
		
		public void partActivated(IWorkbenchPart part) {
			if (isLinkedToActiveEditor()) {
				updateDiagramText(false, part, null);
			}
		}
		public void partOpened(IWorkbenchPart part) {}
		public void partDeactivated(IWorkbenchPart part) {}
		public void partClosed(IWorkbenchPart part) {}
		public void partBroughtToTop(IWorkbenchPart part) {}
	};
	
	private class DiagramTextChangedListener implements IPropertyListener, ISelectionListener {

		public void propertyChanged(Object source, int propId) {
			if (source == lastEditor && propId == IEditorPart.PROP_DIRTY && (! lastEditor.isDirty())) {
				diagramChanged(lastEditor, null);
			}
		}		
		public void selectionChanged(IWorkbenchPart part, ISelection selection) {
			if (part == lastEditor) {
				diagramChanged(lastEditor, selection);
			}
		}
		public void diagramChanged(IEditorPart editor, ISelection selection) {
			updateDiagramText(true, editor, selection);
		}
	}
	
	private DiagramTextChangedListener diagramTextChangedListener = new DiagramTextChangedListener();	
	private IEditorPart lastEditor;
	
	private void handleEditorChange(IEditorPart editor) {
		if (lastEditor != null) {
			lastEditor.removePropertyListener(diagramTextChangedListener);
		}
		lastEditor = editor;
		if (lastEditor != null) {
			lastEditor.addPropertyListener(diagramTextChangedListener);
		}
	}

	protected void updateDiagramText(boolean force, IWorkbenchPart part, ISelection selection) {
		IEditorPart activeEditor = (part instanceof IEditorPart ? (IEditorPart) part : (isLinkedToActiveEditor() ? getSite().getPage().getActiveEditor() : null));
		if (force || activeEditor != lastEditor) {
			handleEditorChange(activeEditor);
			if (activeEditor != null) {
				if (selection == null) {
					selection = activeEditor.getSite().getSelectionProvider().getSelection(); // getWorkbenchWindow().getSelectionService().getSelection();
				}
				if (updateDiagramText(activeEditor, selection)) {
					return;
				}
				if (selection != null && updateDiagramText(activeEditor, null)) {
					return;
				}
			}
			updateDiagramText(null);
		}
	}
	
	private boolean updateDiagramText(IEditorPart activeEditor, ISelection selection) {
		if (activeEditor != null) {
			DiagramTextProvider[] diagramTextProviders = Activator.getDefault().getDiagramTextProviders();
			for (int i = 0; i < diagramTextProviders.length; i++) {
				DiagramTextProvider diagramTextProvider = diagramTextProviders[i];
				if (diagramTextProvider.supportsEditor(activeEditor) && (selection == null || diagramTextProvider.supportsSelection(selection))) {
					String diagramText = diagramTextProvider.getDiagramText(activeEditor, selection);
					if (diagramText != null) {
						updateDiagramText(diagramText);
						return true;
					}
				}
			}
		}
		return false;
	}
}
