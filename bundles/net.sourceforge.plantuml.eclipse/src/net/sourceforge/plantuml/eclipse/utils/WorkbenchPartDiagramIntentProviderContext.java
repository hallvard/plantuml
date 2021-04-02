package net.sourceforge.plantuml.eclipse.utils;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

import net.sourceforge.plantuml.util.DiagramIntentContext;

public class WorkbenchPartDiagramIntentProviderContext extends DiagramIntentContext {

	private final IWorkbenchPart workbenchPart;
	private final ISelection selection;

	public WorkbenchPartDiagramIntentProviderContext(final IWorkbenchPart workbenchPart, final ISelection selection) {
		this.workbenchPart = workbenchPart;
		this.selection = (selection == StructuredSelection.EMPTY ? null : selection);
	}

	public IWorkbenchPart getWorkbenchPart() {
		return workbenchPart;
	}

	public ISelection getSelection() {
		return selection;
	}
}
