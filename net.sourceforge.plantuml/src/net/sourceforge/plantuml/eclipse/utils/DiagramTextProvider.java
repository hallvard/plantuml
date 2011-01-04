package net.sourceforge.plantuml.eclipse.utils;

import org.eclipse.ui.IEditorPart;

public interface DiagramTextProvider {

	public boolean supportsEditor(IEditorPart editorPart);
	public String getDiagramText(IEditorPart editorPart);

}
