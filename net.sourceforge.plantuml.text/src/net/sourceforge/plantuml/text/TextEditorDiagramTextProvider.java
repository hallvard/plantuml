package net.sourceforge.plantuml.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public boolean supportsSelection(ISelection selection) {
		return selection instanceof ITextSelection;
	}

	@Override
	protected String getDiagramText(IEditorPart editorPart, IEditorInput editorInput, ISelection selection) {
		ITextEditor textEditor = (ITextEditor) editorPart;
		IDocument document = textEditor.getDocumentProvider().getDocument(editorInput);
		int cursorPosition = ((ITextSelection) textEditor.getSelectionProvider().getSelection()).getOffset();
		
		String extractedText = extractTextFromStartToEndTag(document, cursorPosition);
		String filteredText = filterCommentsAtBeginningOfEachLine(extractedText);
		
		return filteredText;
	}
	
	private String extractTextFromStartToEndTag(IDocument document, int cursorPosition) {
		String extractedText = null;
		
		try {
			FindReplaceDocumentAdapter finder = new FindReplaceDocumentAdapter(document);
			
			String startSearchString = "@startuml";
			String endSearchString = "@enduml";
			boolean forwardSearch = true;
			boolean wholeWord = true;
			boolean isRegex = true;
			
			IRegion start = finder.find(cursorPosition, startSearchString, !forwardSearch, wholeWord, !isRegex, isRegex);
			IRegion end = finder.find(cursorPosition, endSearchString, forwardSearch, wholeWord, !isRegex, isRegex);

			if (start != null && end != null) {
				if (start.getOffset() < end.getOffset()) {
					int textLength = (end.getOffset() + endSearchString.length()) - start.getOffset();
					extractedText = document.get(start.getOffset(), textLength);
				}
			}
		} catch (Exception e) {
			
		}
		
		return extractedText;
	}
	
	private String filterCommentsAtBeginningOfEachLine(String text) {
		String filteredText = null;

		try {
			Pattern pattern = Pattern.compile("^[\t ]*(\\*|//)+[\t ]*", Pattern.MULTILINE);
			Matcher matcher = pattern.matcher(text);
			filteredText = matcher.replaceAll("");
		} catch (Exception e) {

		}

		return filteredText;
	}
}
