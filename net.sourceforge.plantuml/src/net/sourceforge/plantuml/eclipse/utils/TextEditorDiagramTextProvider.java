package net.sourceforge.plantuml.eclipse.utils;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.ITextEditor;

public class TextEditorDiagramTextProvider extends AbstractDiagramTextProvider {

	public TextEditorDiagramTextProvider() {
		setEditorType(ITextEditor.class);
	}

	public static String startuml = "@startuml", enduml = "@enduml";
	private boolean startIsRegexp = true, endIsRegexp = true;
	private boolean includeStart = false, includeEnd = false;

	public boolean supportsSelection(ISelection selection) {
		return selection instanceof ITextSelection;
	}

	@Override
	protected String getDiagramText(IEditorPart editorPart, IEditorInput editorInput, ISelection selection) {
		IDocument document = ((ITextEditor) editorPart).getDocumentProvider().getDocument(editorInput);
		int selectionStart = ((ITextSelection) selection).getOffset();
		FindReplaceDocumentAdapter finder = new FindReplaceDocumentAdapter(document);
		try {
			IRegion start = finder.find(selectionStart, startuml, true, true, (! startIsRegexp), startIsRegexp);
			IRegion end = finder.find(selectionStart, enduml, true, true, (! endIsRegexp), endIsRegexp);
			if (start == null || (end != null && end.getOffset() < start.getOffset())) {
				// use a slightly larger selection offset, in case the cursor is within startuml
				start = finder.find(Math.min(selectionStart + startuml.length(), document.getLength()), startuml, false, true, (! startIsRegexp), startIsRegexp);
			}
			if (start != null) {
				end = finder.find(start.getOffset(), enduml, true, true, (! endIsRegexp), endIsRegexp);
				if (end != null) {
					int startOffset = start.getOffset(), endOffset = end.getOffset() + end.getLength();
					int startLine = document.getLineOfOffset(startOffset);
					String linePrefix = document.get(document.getLineOffset(startLine), startOffset - document.getLineOffset(startLine));
					if (linePrefix.trim().length() == 0) {
						linePrefix = null;
					}
					StringBuilder result = new StringBuilder();
					int maxLine = Math.min(document.getLineOfOffset(endOffset) + (includeEnd ? 1 : 0), document.getNumberOfLines());
					for (int lineNum = startLine + (includeStart ? 0 : 1); lineNum < maxLine; lineNum++) {
						String line = document.get(document.getLineOffset(lineNum), document.getLineLength(lineNum));
						if (linePrefix != null && line.startsWith(linePrefix)) {
							line = line.substring(linePrefix.length());
						}
						result.append(line);
					}
					return result.toString();
				}
			}
		} catch (BadLocationException e) {
		}
		return null;
	}
}
