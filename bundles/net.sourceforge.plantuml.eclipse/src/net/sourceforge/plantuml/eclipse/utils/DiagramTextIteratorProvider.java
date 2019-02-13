package net.sourceforge.plantuml.eclipse.utils;

import java.util.Iterator;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Used for generating editor selections, from editor contents
 * @author hal
 *
 */
public interface DiagramTextIteratorProvider extends DiagramTextProvider {

	/**
	 * Computes an iterator of workbench part selections suitable for
	 * getDiagramText(IWorkbenchPart, ISelection)
	 * @param workbenchPart
	 * @return an iterator of workbench part selections
	 */
	public Iterator<ISelection> getDiagramText(IWorkbenchPart workbenchPart);
}
