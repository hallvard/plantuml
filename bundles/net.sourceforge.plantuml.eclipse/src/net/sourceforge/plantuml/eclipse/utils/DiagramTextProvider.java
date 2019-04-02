package net.sourceforge.plantuml.eclipse.utils;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;

/**
 * Functionality for generating diagrams from contents of active editors,
 * optionally based on editor selection
 * @author hal
 *
 */
public interface DiagramTextProvider {

	/**
	 * Tells if the specified editor (or its input) is supported
	 * @param editorPart
	 * @return true if the specified editor (or its input) is supported, false otherwise
	 */
	public default boolean supportsEditor(final IEditorPart editorPart) {
		return false;
	}

	/**
	 * Tells if the specified view is supported
	 * @param viewPart
	 * @return true if the specified view is supported, false otherwise
	 */
	public default boolean supportsView(final IViewPart viewPart) {
		return false;
	}

	/**
	 * Tells whether the specified editor or view selection is supported
	 * @param selection
	 * @return true if the specified editor or view selection is supported, false otherwise
	 */
	public boolean supportsSelection(ISelection selection);

	/**
	 * Computes the diagram text for the specific editor part and selection
	 * @param editorPart
	 * @param selection
	 * @return the corresponding diagram text
	 */
	public default String getDiagramText(final IEditorPart editorPart, final ISelection selection) {
		return null;
	}

	/**
	 * Computes the diagram text for the specific view part and selection
	 * @param viewPart
	 * @param selection
	 * @return the corresponding diagram text
	 */
	public default String getDiagramText(final IViewPart viewPart, final ISelection selection) {
		return null;
	}
}
