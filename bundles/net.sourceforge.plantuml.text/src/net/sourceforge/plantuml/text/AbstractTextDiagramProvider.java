package net.sourceforge.plantuml.text;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.ITextEditor;

import net.sourceforge.plantuml.eclipse.utils.DiagramTextIteratorProvider;
import net.sourceforge.plantuml.eclipse.utils.DiagramTextProvider2;
import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;

public abstract class AbstractTextDiagramProvider extends AbstractDiagramTextProvider implements DiagramTextProvider2, DiagramTextIteratorProvider {

	public AbstractTextDiagramProvider() {
		setEditorType(ITextEditor.class);
	}

	@Override
	public boolean supportsSelection(final ISelection selection) {
		return selection instanceof ITextSelection;
	}

	@Override
	protected String getDiagramText(final IEditorPart editorPart, final IEditorInput editorInput, final ISelection selection, final Map<String, Object> markerAttributes) {
		final StringBuilder lines = getDiagramTextLines(editorPart, editorInput, selection, markerAttributes);
		return (lines != null ? getDiagramText(lines) : null);
	}

	protected String getStartPlantUmlRegex() {
		return PlantumlConstants.START_UML;
	}

	protected String getEndPlantUmlRegex() {
		return PlantumlConstants.END_UML;
	}

	private TextDiagramHelper textDiagramHelper = null;

	public TextDiagramHelper getTextDiagramHelper() {
		if (textDiagramHelper == null) {
			textDiagramHelper = new TextDiagramHelper(getStartPlantUml(), getStartPlantUmlRegex(), getEndPlantUml(), getEndPlantUmlRegex());
		}
		return textDiagramHelper;
	}

	protected StringBuilder getDiagramTextLines(final IEditorPart editorPart, final IEditorInput editorInput, final ISelection selection, final Map<String, Object> markerAttributes) {
		final ITextEditor textEditor = (ITextEditor) editorPart;
		final IDocument document = textEditor.getDocumentProvider().getDocument(editorInput);
		final int selectionStart = ((ITextSelection) (selection != null ? selection : textEditor.getSelectionProvider().getSelection())).getOffset();
		return getTextDiagramHelper().getDiagramTextLines(document, selectionStart, markerAttributes);
	}

	public String getDiagramText(final CharSequence lines) {
		return getDiagramText(new StringBuilder(lines.toString()));
	}

	protected String getDiagramText(final StringBuilder lines) {
		return getTextDiagramHelper().getDiagramText(lines);
	}

	@Override
	public String getDiagramText(final IPath path) {
		final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
		if (file != null && file.exists()) {
			return getTextDiagramHelper().getDiagramText(file);
		}
		return null;
	}

	// DiagramTextIteratorProvider

	@Override
	public Iterator<ISelection> getDiagramText(final IEditorPart editorPart) {
		final IDocument document = ((ITextEditor) editorPart).getDocumentProvider().getDocument(editorPart.getEditorInput());
		return getTextDiagramHelper().getDiagramText(document);
	}
}
