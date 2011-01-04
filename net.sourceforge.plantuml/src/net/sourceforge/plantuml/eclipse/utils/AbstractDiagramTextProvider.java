package net.sourceforge.plantuml.eclipse.utils;

import java.util.StringTokenizer;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPathEditorInput;

public abstract class AbstractDiagramTextProvider implements DiagramTextProvider {

	private String fileExtensions = null;
	private Class editorType = null;

	public AbstractDiagramTextProvider() {
		
	}
	public AbstractDiagramTextProvider(String fileExtensions, Class editorType) {
		this();
		this.fileExtensions = fileExtensions;
		this.editorType = editorType;
	}

	public void setFileExtensions(String fileExtensions) {
		this.fileExtensions = fileExtensions;
	}

	public void setEditorType(Class editorType) {
		this.editorType = editorType;
	}

	public boolean supportsEditor(IEditorPart editorPart) {
		if (editorType != null && (! (editorType.isInstance(editorPart)))) {
			return false;
		}
		if (fileExtensions != null) {
			String fileExtension = null;
			if (fileExtension == null && editorPart.getEditorInput() instanceof IPathEditorInput) {
				fileExtension = ((IPathEditorInput) editorPart.getEditorInput()).getPath().getFileExtension();
			}
			if (fileExtension != null) {
				boolean supportsFileExtension = false;
				StringTokenizer tokens = new StringTokenizer(fileExtensions, ",");
				while ((! supportsFileExtension) && tokens.hasMoreTokens()) {
					supportsFileExtension |= fileExtension.equals(tokens.nextToken());
				}
				if (! supportsFileExtension) {
					return false;
				}
			}
		}
		return true;
	}
	
	protected String startuml = "@startuml", enduml = "@enduml";

	public String getDiagramText(IEditorPart editorPart) {
		if (supportsEditor(editorPart)) {
			String diagramText = getDiagramText(editorPart, editorPart.getEditorInput());
			if (diagramText != null) {
				diagramText = diagramText.trim();
				if (! diagramText.startsWith(startuml)) {
					diagramText = startuml + "\n" + diagramText;
				}
				if (! diagramText.endsWith(enduml)) {
					diagramText = diagramText + "\n" + enduml;
				}
			}
			return diagramText;
		}
		return null;
	}
	
	protected abstract String getDiagramText(IEditorPart editorPart, IEditorInput editorInput);
}
