package net.sourceforge.plantuml.eclipse.views;

import net.sourceforge.plantuml.Activator;
import net.sourceforge.plantuml.eclipse.utils.DiagramTextProvider;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

public abstract class AbstractDiagramSourceView extends ViewPart {

	@Override
	public void createPartControl(Composite parent) {
		registerListeners();
		updateDiagramText(true);
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

	private IPartListener partListener = new IPartListener() {
		
		public void partOpened(IWorkbenchPart part) {
		}
		public void partDeactivated(IWorkbenchPart part) {
		}
		public void partClosed(IWorkbenchPart part) {
		}
		public void partBroughtToTop(IWorkbenchPart part) {
			updateDiagramText(false);
		}
		public void partActivated(IWorkbenchPart part) {
		}
	};
	
	private class DiagramTextChangedListener implements IPropertyListener, ISelectionListener {
		public void propertyChanged(Object source, int propId) {
			if (source == lastEditor && propId == IEditorPart.PROP_DIRTY && (! lastEditor.isDirty())) {
				diagramChanged(lastEditor);
			}
		}		
		public void selectionChanged(IWorkbenchPart part, ISelection selection) {
			if (part == lastEditor) {
				diagramChanged(lastEditor);
			}			
		}
		public void diagramChanged(IEditorPart editor) {
			updateDiagramText(true);
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

	private void updateDiagramText(boolean force) {
		IEditorPart activeEditor = getSite().getPage().getActiveEditor();
		if (force || activeEditor != lastEditor) {
			handleEditorChange(activeEditor);
			if (activeEditor != null) {
				DiagramTextProvider[] diagramTextProviders = Activator.getDefault().getDiagramTextProviders();
				for (int i = 0; i < diagramTextProviders.length; i++) {
					DiagramTextProvider diagramTextProvider = diagramTextProviders[i];
					String diagramText = diagramTextProvider.getDiagramText(activeEditor);
					if (diagramText != null) {
						updateDiagramText(diagramText);
						return;
					}
				}
			}
			updateDiagramText(null);
		}
	}
}
