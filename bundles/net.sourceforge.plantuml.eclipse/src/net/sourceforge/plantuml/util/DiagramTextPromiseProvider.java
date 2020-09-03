package net.sourceforge.plantuml.util;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;

import net.sourceforge.plantuml.eclipse.utils.DiagramTextProvider;
import net.sourceforge.plantuml.eclipse.utils.WorkbenchDiagramPromiseProviderContext;

public class DiagramTextPromiseProvider implements DiagramPromiseProvider {

	private final DiagramTextProvider diagramTextProvider;

	public DiagramTextPromiseProvider(final DiagramTextProvider diagramTextProvider) {
		this.diagramTextProvider = diagramTextProvider;
	}

	@Override
	public Collection<DiagramPromise> getDiagramInfos(final DiagramPromiseContext context) {
		if (context instanceof WorkbenchDiagramPromiseProviderContext) {
			final WorkbenchDiagramPromiseProviderContext promiseProviderContext = (WorkbenchDiagramPromiseProviderContext) context;
			final IWorkbenchPart workbenchPart = promiseProviderContext.getWorkbenchPart();
			String diagramText = null;
			final ISelection selection = promiseProviderContext.getSelection();
			if (workbenchPart instanceof IEditorPart) {
				diagramText = diagramTextProvider.getDiagramText((IEditorPart) workbenchPart, selection);
			} else if (workbenchPart instanceof IViewPart) {
				diagramText = diagramTextProvider.getDiagramText((IViewPart) workbenchPart, selection);
			}
			if (diagramText != null) {
				return Collections.singletonList(new SimpleDiagramPromise(diagramText));
			}
		}
		return null;
	}
}
