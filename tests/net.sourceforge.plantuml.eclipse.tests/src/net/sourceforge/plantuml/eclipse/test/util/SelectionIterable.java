package net.sourceforge.plantuml.eclipse.test.util;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

public interface SelectionIterable extends Iterable<ISelection> {
	public void init(IWorkbenchPart part, String spec);
}
