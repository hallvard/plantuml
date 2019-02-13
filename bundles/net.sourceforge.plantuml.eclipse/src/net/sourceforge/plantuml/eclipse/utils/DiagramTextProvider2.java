package net.sourceforge.plantuml.eclipse.utils;

import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Extends DiagramTextProvider with support for generating diagrams from file contents.
 * Used for (re)generating diagram directly from a file, instead of an open editor.
 * @author hal
 *
 */
public interface DiagramTextProvider2 extends DiagramTextProvider {

	/**
	 * Tells if the specified path is supported, likely based on the file extension
	 * @param path
	 * @return true if the specified path is supported, likely based on the file extension, false otherwise
	 */
	public boolean supportsPath(IPath path);

	/**
	 * Generates the diagram from a workbench part and its selection,
	 * also considering marker attributes which store info about the previously generated diagram.
	 * @param workbenchPart
	 * @param selection
	 * @param markerAttributes
	 * @return the corresponding diagram text
	 */
	public String getDiagramText(IWorkbenchPart workbenchPart, ISelection selection, Map<String, Object> markerAttributes);

	/**
	 * Generates the diagram from the file contents
	 * @param path
	 * @return the corresponding diagram text
	 */
	public String getDiagramText(IPath path);
}
