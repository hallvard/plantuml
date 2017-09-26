package net.sourceforge.plantuml.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.ITextEditor;

import net.sourceforge.plantuml.eclipse.utils.DiagramTextProvider2;
import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.eclipse.utils.PlantumlUtil;

public class TextEditorDiagramTextProvider extends AbstractDiagramTextProvider implements DiagramTextProvider2 {

	public TextEditorDiagramTextProvider() {
		setEditorType(ITextEditor.class);
	}

	private boolean startIsRegexp = true, endIsRegexp = true;
	private boolean includeStart = true, includeEnd = true;

	public boolean supportsSelection(ISelection selection) {
		return selection instanceof ITextSelection;
	}

	private final static String newline = "\n";

	@Override
	protected String getDiagramText(IEditorPart editorPart, IEditorInput editorInput, ISelection selection, Map<String, Object> markerAttributes) {
		StringBuilder lines = getDiagramTextLines(editorPart, editorInput, selection, markerAttributes);
		return (lines != null ? getDiagramText(lines) : null);
	}

	protected StringBuilder getDiagramTextLines(IEditorPart editorPart, IEditorInput editorInput, ISelection selection, Map<String, Object> markerAttributes) {
		ITextEditor textEditor = (ITextEditor) editorPart;
		IDocument document = textEditor.getDocumentProvider().getDocument(editorInput);
		int selectionStart = ((ITextSelection) textEditor.getSelectionProvider().getSelection()).getOffset();
		FindReplaceDocumentAdapter finder = new FindReplaceDocumentAdapter(document);
		try {
			IRegion start = finder.find(selectionStart, PlantumlConstants.START_UML, true, true, (! startIsRegexp), startIsRegexp);
			IRegion end = finder.find(selectionStart, PlantumlConstants.END_UML, true, true, (! endIsRegexp), endIsRegexp);
			if (start == null || (end != null && end.getOffset() < start.getOffset())) {
				// use a slightly larger selection offset, in case the cursor is within startuml
				start = finder.find(Math.min(selectionStart + PlantumlConstants.START_UML.length(), document.getLength()), PlantumlConstants.START_UML, false, true, (! startIsRegexp), startIsRegexp);
			}
			if (start != null) {
				int startOffset = start.getOffset(), startLine = document.getLineOfOffset(startOffset);
				end = finder.find(startOffset, PlantumlConstants.END_UML, true, true, (! endIsRegexp), endIsRegexp);
				if (end != null) {
					int endOffset = end.getOffset() + end.getLength();
//					String linePrefix = document.get(startLinePos, startOffset - startLinePos).trim();
					StringBuilder result = new StringBuilder();
					int maxLine = Math.min(document.getLineOfOffset(endOffset) + (includeEnd ? 1 : 0), document.getNumberOfLines());
					for (int lineNum = startLine + (includeStart ? 0 : 1); lineNum < maxLine; lineNum++) {
						String line = document.get(document.getLineOffset(lineNum), document.getLineLength(lineNum)).trim();
						result.append(line);
						if (! line.endsWith(newline)) {
							result.append(newline);
						}
					}
					markerAttributes.put(IMarker.CHAR_START, start.getOffset());
					return result;
				}
			}
		} catch (BadLocationException e) {
		}
		return null;
	}

	public String getDiagramText(CharSequence lines) {
		return getDiagramText(new StringBuilder(lines.toString()));
	}

	protected String getDiagramText(StringBuilder lines) {
		int start = Math.max(lines.indexOf(PlantumlConstants.START_UML), 0), end = Math.min(lines.lastIndexOf(PlantumlConstants.END_UML) + PlantumlConstants.END_UML.length(), lines.length());
		String linePrefix = lines.substring(0, start).trim();
		StringBuilder result = new StringBuilder(lines.length());
		while (start < end) {
			int lineEnd = lines.indexOf(newline, start);
			if (lineEnd > end) {
				break;
			} else if (lineEnd < 0) {
				lineEnd = lines.length();
			}
			String line = lines.substring(start, lineEnd).trim();
			if (line.startsWith(linePrefix)) {
				line = line.substring(linePrefix.length()).trim();
			}
			result.append(line);
			result.append(newline);
			start = lineEnd + 1;
		}
		return result.toString().trim();
	}

	private Collection<String> supportedExtensions = new ArrayList<String>(Arrays.asList("txt", "puml", "plantuml"));
	
	@Override
	public boolean supportsPath(IPath path) {
		return supportedExtensions.contains(path.getFileExtension());
	}

	@Override
	public String getDiagramText(IPath path) {
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
		if (file != null && file.exists()) {
			IMarker marker = PlantumlUtil.getPlantUmlMarker(file, false);
			int startOffset = marker.getAttribute(IMarker.CHAR_START, 0);
			StringBuilder builder = null;
			try {
				Scanner scanner = new Scanner(file.getContents());
				while (scanner.hasNextLine()) {
					String nextLine = scanner.nextLine();
					if (builder == null) {
						if (startOffset <= nextLine.length()) {
							if (nextLine.indexOf(PlantumlConstants.START_UML, startOffset) >= 0) {
								builder = new StringBuilder();
							}
							startOffset = 0;
						} else {
							startOffset = startOffset - nextLine.length() - 1;
						}
					}
					if (builder != null) {
						builder.append(nextLine);
						builder.append(newline);
						if (nextLine.contains(PlantumlConstants.END_UML)) {
							break;
						}
					}
				}
				scanner.close();
			} catch (CoreException e) {
			}
			if (builder != null) {
				return getDiagramText(builder);
			}
		}
		return null;
	}
}
