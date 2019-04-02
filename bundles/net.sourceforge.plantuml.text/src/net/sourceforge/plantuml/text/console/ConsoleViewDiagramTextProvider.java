package net.sourceforge.plantuml.text.console;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleListener;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.IPatternMatchListener;
import org.eclipse.ui.console.PatternMatchEvent;
import org.eclipse.ui.console.TextConsole;
import org.eclipse.ui.texteditor.ITextEditor;

import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.text.AbstractDiagramTextProvider;

public class ConsoleViewDiagramTextProvider extends AbstractDiagramTextProvider implements ISelectionProvider {

	public ConsoleViewDiagramTextProvider() {
		setEditorType(IConsoleView.class);
		ConsolePlugin.getDefault().getConsoleManager().addConsoleListener(new IConsoleListener() {
			@Override
			public void consolesRemoved(final IConsole[] consoles) {
				for (int i = 0; i < consoles.length; i++) {
					if (consoles[i] instanceof TextConsole) {
						((TextConsole) consoles[i]).removePatternMatchListener(patternMatchListener);
					}
				}
			}
			@Override
			public void consolesAdded(final IConsole[] consoles) {
				for (int i = 0; i < consoles.length; i++) {
					if (consoles[i] instanceof TextConsole) {
						((TextConsole) consoles[i]).addPatternMatchListener(patternMatchListener);
					}
				}
			}
		});
	}

	private final IPatternMatchListener patternMatchListener = new IPatternMatchListener() {
		@Override
		public void connect(final TextConsole console) {
		}
		@Override
		public void matchFound(final PatternMatchEvent matchEvent) {
			try {
				final IDocument contents = ((TextConsole) matchEvent.getSource()).getDocument();
				final String match = contents.get(matchEvent.getOffset(), matchEvent.getLength());
				setSelection(getDiagramText(match));
			} catch (final BadLocationException e) {
			}
		}
		@Override
		public void disconnect() {
			setSelection(StructuredSelection.EMPTY);
		}

		private final String qualifier = (startIsRegexp ? getStartPlantUml() : Pattern.quote(getStartPlantUml()));
		private final String pattern = qualifier + "((.|\\n)*)" + (endIsRegexp ? getEndPlantUml() : Pattern.quote(getEndPlantUml()));

		@Override
		public String getPattern() {
			return pattern;
		}

		@Override
		public String getLineQualifier() {
			return qualifier;
		}

		@Override
		public int getCompilerFlags() {
			return Pattern.MULTILINE;
		}
	};

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
	protected String getStartPlantUml() {
		return PlantumlConstants.START_UML;
	}

	// allows to override suffix
	protected String getEndPlantUml() {
		return PlantumlConstants.END_UML;
	}

	protected StringBuilder getDiagramTextLines(final IEditorPart editorPart, final IEditorInput editorInput, final ISelection selection, final Map<String, Object> markerAttributes) {
		final ITextEditor textEditor = (ITextEditor) editorPart;
		final IDocument document = textEditor.getDocumentProvider().getDocument(editorInput);
		final int selectionStart = ((ITextSelection) (selection != null ? selection : textEditor.getSelectionProvider().getSelection())).getOffset();
		final FindReplaceDocumentAdapter finder = new FindReplaceDocumentAdapter(document);
		try {
			final String startPlantUml = getStartPlantUml(), entPlantUml = getEndPlantUml();
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
		final String startPlantUml = getStartPlantUml(), entPlantUml = getEndPlantUml();
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

	private ISelection selection = StructuredSelection.EMPTY;

	@Override
	public ISelection getSelection() {
		return selection;
	}

	protected void setSelection(final String diagramText) {
		setSelection(new StructuredSelection(diagramText));
	}

	@Override
	public void setSelection(final ISelection selection) {
		this.selection = selection;
		if (selectionListeners != null && (! selectionListeners.isEmpty())) {
			final SelectionChangedEvent event = new SelectionChangedEvent(this, getSelection());
			for (final ISelectionChangedListener listener : selectionListeners) {
				listener.selectionChanged(event);
			}
		}
	}

	private final Collection<ISelectionChangedListener> selectionListeners = new ArrayList<>();

	@Override
	public void addSelectionChangedListener(final ISelectionChangedListener listener) {
		selectionListeners.add(listener);
	}

	@Override
	public void removeSelectionChangedListener(final ISelectionChangedListener listener) {
		selectionListeners.remove(listener);
	}
}
