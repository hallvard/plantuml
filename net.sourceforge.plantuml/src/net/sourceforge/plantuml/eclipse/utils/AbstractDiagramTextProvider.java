package net.sourceforge.plantuml.eclipse.utils;

import java.util.StringTokenizer;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPathEditorInput;

public abstract class AbstractDiagramTextProvider implements DiagramTextProvider {

	private String fileExtensions = null;
	private Class<?> editorType = null;

	public AbstractDiagramTextProvider() {
		
	}
	public AbstractDiagramTextProvider(String fileExtensions, Class<?> editorType) {
		this();
		setFileExtensions(fileExtensions);
		setEditorType(editorType);
	}

	public void setFileExtensions(String fileExtensions) {
		this.fileExtensions = fileExtensions;
	}

	public void setEditorType(Class<?> editorType) {
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
			boolean supportsFileExtension = false;
			StringTokenizer tokens = new StringTokenizer(fileExtensions, ",");
			while ((! supportsFileExtension) && tokens.hasMoreTokens()) {
				String ext = tokens.nextToken();
				if ("*".equals(ext) || ext.equals(fileExtension)) {
					supportsFileExtension = true;
				}
			}
			if (! supportsFileExtension) {
				return false;
			}
		}
		return true;
	}
	
	public String getDiagramText(IEditorPart editorPart) {
		if (supportsEditor(editorPart)) {
			String diagramText = getDiagramText(editorPart, editorPart.getEditorInput());
			if (diagramText != null) {
				diagramText = diagramText.trim();
				if (! diagramText.startsWith(TextEditorDiagramTextProvider.startuml)) {
					diagramText = TextEditorDiagramTextProvider.startuml + "\n" + diagramText;
				}
				if (! diagramText.endsWith(TextEditorDiagramTextProvider.enduml)) {
					diagramText = diagramText + "\n" + TextEditorDiagramTextProvider.enduml;
				}
			}
			return diagramText;
		}
		return null;
	}
	
	protected abstract String getDiagramText(IEditorPart editorPart, IEditorInput editorInput);
}
