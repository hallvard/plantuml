package net.sourceforge.plantuml.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
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
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.ITextEditor;

import net.sourceforge.plantuml.eclipse.utils.DiagramTextIteratorProvider;
import net.sourceforge.plantuml.eclipse.utils.DiagramTextProvider2;
import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.eclipse.utils.PlantumlUtil;

public class TextEditorDiagramTextProvider extends AbstractDiagramTextProvider implements DiagramTextProvider2, DiagramTextIteratorProvider {

	public TextEditorDiagramTextProvider() {
		setEditorType(ITextEditor.class);
	}

	private final boolean startIsRegexp = true, endIsRegexp = true;
	private final boolean includeStart = true, includeEnd = true;

	@Override
	public boolean supportsSelection(final ISelection selection) {
		return selection instanceof ITextSelection;
	}

	private final static String newline = "\n";

	@Override
	protected String getDiagramText(final IEditorPart editorPart, final IEditorInput editorInput, final ISelection selection, final Map<String, Object> markerAttributes) {
		final StringBuilder lines = getDiagramTextLines(editorPart, editorInput, selection, markerAttributes);
		return (lines != null ? getDiagramText(lines) : null);
	}

	// allows to override prefix
	protected String getEntPlantUml() {
		return PlantumlConstants.END_UML;
	}

	// allows to override suffix
	protected String getStartPlantUml() {
		return PlantumlConstants.START_UML;
	}

	protected StringBuilder getDiagramTextLines(final IEditorPart editorPart, final IEditorInput editorInput, final ISelection selection, final Map<String, Object> markerAttributes) {
		final ITextEditor textEditor = (ITextEditor) editorPart;
		final IDocument document = textEditor.getDocumentProvider().getDocument(editorInput);
		final int selectionStart = ((ITextSelection) (selection != null ? selection : textEditor.getSelectionProvider().getSelection())).getOffset();
		final FindReplaceDocumentAdapter finder = new FindReplaceDocumentAdapter(document);
		try {
			final String startPlantUml = getStartPlantUml(), entPlantUml = getEntPlantUml();
			IRegion start = finder.find(selectionStart, startPlantUml, true, true, (! startIsRegexp), startIsRegexp);
			IRegion end = finder.find(selectionStart, entPlantUml, true, true, (! endIsRegexp), endIsRegexp);
			if (start == null || (end != null && end.getOffset() < start.getOffset())) {
				// use a slightly larger selection offset, in case the cursor is within startuml
				start = finder.find(Math.min(selectionStart + startPlantUml.length(), document.getLength()), startPlantUml, false, true, (! startIsRegexp), startIsRegexp);
			}
			if (start != null) {
				final int startOffset = start.getOffset(), startLine = document.getLineOfOffset(startOffset);
				end = finder.find(startOffset, entPlantUml, true, true, (! endIsRegexp), endIsRegexp);
				if (end != null) {
					final int endOffset = end.getOffset() + end.getLength();
					//					String linePrefix = document.get(startLinePos, startOffset - startLinePos).trim();
					final StringBuilder result = new StringBuilder();
					final int maxLine = Math.min(document.getLineOfOffset(endOffset) + (includeEnd ? 1 : 0), document.getNumberOfLines());
					for (int lineNum = startLine + (includeStart ? 0 : 1); lineNum < maxLine; lineNum++) {
						final String line = document.get(document.getLineOffset(lineNum), document.getLineLength(lineNum)).trim();
						result.append(line);
						if (! line.endsWith(newline)) {
							result.append(newline);
						}
					}
					markerAttributes.put(IMarker.CHAR_START, start.getOffset());
					return result;
				}
			}
		} catch (final BadLocationException e) {
		}
		return null;
	}

	public String getDiagramText(final CharSequence lines) {
		return getDiagramText(new StringBuilder(lines.toString()));
	}

	protected String getDiagramText(final StringBuilder lines) {
		final String startPlantUml = getStartPlantUml(), entPlantUml = getEntPlantUml();
		int start = Math.max(lines.indexOf(startPlantUml), 0);
		final int end = Math.min(lines.lastIndexOf(entPlantUml) + entPlantUml.length(), lines.length());
		final String linePrefix = lines.substring(0, start).trim();
		final StringBuilder result = new StringBuilder(lines.length());
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

	private final Collection<String> supportedExtensions = new ArrayList<String>(Arrays.asList("txt", "text", "puml", "plantuml"));

	@Override
	public boolean supportsPath(final IPath path) {
		return supportedExtensions.contains(path.getFileExtension());
	}

	@Override
	public String getDiagramText(final IPath path) {
		final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
		if (file != null && file.exists()) {
			final IMarker marker = PlantumlUtil.getPlantUmlMarker(file, false);
			int startOffset = marker.getAttribute(IMarker.CHAR_START, 0);
			StringBuilder builder = null;
			try {
				final Scanner scanner = new Scanner(file.getContents());
				while (scanner.hasNextLine()) {
					final String nextLine = scanner.nextLine();
					if (builder == null) {
						if (startOffset <= nextLine.length()) {
							if (nextLine.indexOf(getStartPlantUml(), startOffset) >= 0) {
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
						if (nextLine.contains(getEntPlantUml())) {
							break;
						}
					}
				}
				scanner.close();
			} catch (final CoreException e) {
			}
			if (builder != null) {
				return getDiagramText(builder);
			}
		}
		return null;
	}

	// DiagramTextIteratorProvider

	@Override
	public Iterator<ISelection> getDiagramText(final IWorkbenchPart editorPart) {
		final ITextEditor textEditor = (ITextEditor) editorPart;
		final IDocument document = textEditor.getDocumentProvider().getDocument(textEditor.getEditorInput());
		final FindReplaceDocumentAdapter finder = new FindReplaceDocumentAdapter(document);
		int selectionStart = 0;
		final Collection<ISelection> selections = new ArrayList<ISelection>();
		try {
			while (true) {
				final IRegion start = finder.find(selectionStart, getStartPlantUml(), true, true, (! startIsRegexp), startIsRegexp);
				final IRegion end = finder.find(selectionStart, getEntPlantUml(), true, true, (! endIsRegexp), endIsRegexp);
				if (start == null || end == null) {
					break;
				}
				final int diagramStart = start.getOffset() + start.getLength() + 1, diagramLine = document.getLineOfOffset(diagramStart);
				final String line = document.get(document.getLineOffset(diagramLine), document.getLineLength(diagramLine)).trim();
				final ISelection selection = new TextSelection(start.getOffset() + start.getLength(), 0) {
					@Override
					public String toString() {
						return line;
					}
				};
				selections.add(selection);
				selectionStart = end.getOffset() + end.getLength() + 1;
			}
		} catch (final BadLocationException e) {
		}
		return selections.iterator();
	}
}
