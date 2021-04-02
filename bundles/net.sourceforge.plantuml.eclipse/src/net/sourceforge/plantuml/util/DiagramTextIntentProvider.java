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
		if (context instanceof WorkbenchPartDiagramIntentProviderContext) {
			final WorkbenchPartDiagramIntentProviderContext intentProviderContext = (WorkbenchPartDiagramIntentProviderContext) context;
			final IWorkbenchPart workbenchPart = intentProviderContext.getWorkbenchPart();
			String diagramText = null;
			final ISelection selection = intentProviderContext.getSelection();
			if (workbenchPart instanceof IEditorPart) {
				if (diagramTextProvider instanceof DiagramTextProvider2) {
					// TODO
					diagramText = ((DiagramTextProvider2) diagramTextProvider).getDiagramText((IEditorPart) workbenchPart, selection);
				} else {
					diagramText = diagramTextProvider.getDiagramText((IEditorPart) workbenchPart, selection);
				}
			} else if (workbenchPart instanceof IViewPart) {
				diagramText = diagramTextProvider.getDiagramText((IViewPart) workbenchPart, selection);
			}
			if (diagramText != null) {
				return Collections.singletonList(new SimpleDiagramIntent(diagramText));
			}
		}
		return null;
	}
}
