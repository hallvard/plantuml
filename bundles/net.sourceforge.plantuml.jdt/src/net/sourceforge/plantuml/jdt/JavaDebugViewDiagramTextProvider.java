package net.sourceforge.plantuml.jdt;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewPart;

import net.sourceforge.plantuml.eclipse.utils.DiagramTextProvider;

public class JavaDebugViewDiagramTextProvider implements DiagramTextProvider {

	@Override
	public boolean supportsSelection(final ISelection selection) {
		System.out.println(selection);
		if (selection instanceof IStructuredSelection) {
			final Object object = ((IStructuredSelection) selection).getFirstElement();
			// org.eclipse.jdt.internal.debug.core.model.JDIStackFrame
			// org.eclipse.jdt.internal.debug.core.model.JDIModificationVariable
			//   org.eclipse.jdt.internal.debug.core.model.JDILocalVariable
			//   org.eclipse.jdt.internal.debug.core.model.JDIFieldVariable
			//   org.eclipse.jdt.internal.debug.core.model.JDIArrayEntryVariable
			System.out.println(object + " (" + object.getClass() + ")");
		}
		return true;
	}

	@Override
	public boolean supportsView(final IViewPart viewPart) {
		final String partId = viewPart.getSite().getId();
		if (! partId.startsWith("org.eclipse.debug.ui.")) {
			return false;
		}
		return partId.endsWith("DebugView") || partId.endsWith("VariableView");
	}

	@Override
	public String getDiagramText(final IViewPart viewPart, final ISelection selection) {
		System.out.println(viewPart.getSite().getId() + ": " + selection);
		return null;
	}
}
