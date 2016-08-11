package net.sourceforge.plantuml.eclipse.utils;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;

public interface DiagramTextProvider {
	public boolean supportsEditor(IEditorPart editorPart);
	public boolean supportsSelection(ISelection selection);
	public String getDiagramText(IEditorPart editorPart, ISelection selection);
}
