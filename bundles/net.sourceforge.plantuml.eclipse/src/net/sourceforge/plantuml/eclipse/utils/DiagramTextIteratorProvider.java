package net.sourceforge.plantuml.eclipse.utils;

import java.util.Iterator;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;

/**
 * Used for generating editor selections, from editor contents
 * @author hal
 *
 */
public interface DiagramTextIteratorProvider extends DiagramTextProvider {

	/**
	 * Computes an iterator of editor selections suitable for
	 * getDiagramText(IEditorPart, ISelection)
	 * @param editorPart
	 * @return an iterator of editor selections
	 */
	public Iterator<ISelection> getDiagramText(IEditorPart editorPart);
}
