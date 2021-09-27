package net.sourceforge.plantuml.util;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;

import net.sourceforge.plantuml.eclipse.utils.DiagramTextProvider;
import net.sourceforge.plantuml.eclipse.utils.DiagramTextProvider2;
import net.sourceforge.plantuml.eclipse.utils.WorkbenchPartDiagramIntentProviderContext;
import net.sourceforge.plantuml.eclipse.utils.WorkspaceDiagramIntentProviderContext;

public class DiagramTextIntentProvider implements DiagramIntentProvider {

	private final DiagramTextProvider diagramTextProvider;

	public DiagramTextIntentProvider(final DiagramTextProvider diagramTextProvider) {
		this.diagramTextProvider = diagramTextProvider;
	}

	public DiagramTextProvider getDiagramTextProvider() {
		return diagramTextProvider;
	}

	@Override
	public Collection<DiagramIntent> getDiagramInfos(final DiagramIntentContext context) {
		String diagramText = null;
		if (context instanceof WorkbenchPartDiagramIntentProviderContext) {
			final WorkbenchPartDiagramIntentProviderContext intentProviderContext = (WorkbenchPartDiagramIntentProviderContext) context;
			final IWorkbenchPart workbenchPart = intentProviderContext.getWorkbenchPart();
			final ISelection selection = intentProviderContext.getSelection();
			if (workbenchPart instanceof IEditorPart) {
				if (diagramTextProvider instanceof DiagramTextProvider2) {
					final DiagramTextProvider2 diagramTextProvider2 = (DiagramTextProvider2) diagramTextProvider;
					// TODO
					diagramText = diagramTextProvider2.getDiagramText((IEditorPart) workbenchPart, selection);
				} else {
					if (diagramTextProvider.supportsEditor((IEditorPart) workbenchPart)
							&& diagramTextProvider.supportsSelection(selection)) {
						diagramText = diagramTextProvider.getDiagramText((IEditorPart) workbenchPart, selection);
					}
				}
			} else if (workbenchPart instanceof IViewPart) {
				if (diagramTextProvider.supportsView((IViewPart) workbenchPart)
						&& diagramTextProvider.supportsSelection(selection)) {
					diagramText = diagramTextProvider.getDiagramText((IViewPart) workbenchPart, selection);
				}
			}
		} else if (context instanceof WorkspaceDiagramIntentProviderContext
				&& diagramTextProvider instanceof DiagramTextProvider2) {
			final WorkspaceDiagramIntentProviderContext intentProviderContext = (WorkspaceDiagramIntentProviderContext) context;
			final DiagramTextProvider2 diagramTextProvider2 = (DiagramTextProvider2) diagramTextProvider;
			if (diagramTextProvider2.supportsPath(intentProviderContext.getPath())) {
				diagramText = diagramTextProvider2.getDiagramText(intentProviderContext.getPath());
			}
		}
		if (diagramText != null) {
			return Collections.singletonList(new SimpleDiagramIntent(diagramText));
		}
		return null;
	}
}
