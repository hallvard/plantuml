package net.sourceforge.plantuml.eclipse.utils;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

import net.sourceforge.plantuml.util.DiagramPromiseContext;

public class WorkbenchDiagramPromiseProviderContext extends DiagramPromiseContext {

	private final IWorkbenchPart workbenchPart;
	private final ISelection selection;

	public WorkbenchDiagramPromiseProviderContext(final IWorkbenchPart workbenchPart, final ISelection selection) {
		this.workbenchPart = workbenchPart;
		this.selection = selection;
	}

	public IWorkbenchPart getWorkbenchPart() {
		return workbenchPart;
	}

	public ISelection getSelection() {
		return selection;
	}
}
