package net.sourceforge.plantuml.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;

import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.eclipse.utils.PlantumlUtil;
import net.sourceforge.plantuml.util.ResourceInfo;

public class TextDiagramIntentHelper {

	private final String prefix, prefixRegex;
	private final String suffix, suffixRegex;

	public TextDiagramIntentHelper(final String prefix, final String prefixRegex, final String suffix, final String suffixRegex) {
		super();
		this.prefix = prefix;
		this.prefixRegex = prefixRegex;
		this.suffix = suffix;
		this.suffixRegex = suffixRegex;
	}

	public Collection<TextDiagramIntent> getDiagramInfos(final IDocument document, final int selectionStart, final ResourceInfo resourceInfo) {
		final List<TextDiagramIntent> intents = new ArrayList<TextDiagramIntent>();
		final FindReplaceDocumentAdapter finder = new FindReplaceDocumentAdapter(document);
		int start = 0;
		while (start < document.getLength()) {
			try {
				final IRegion regionStart = finder.find(start, prefixRegex, true, true, false, true);
				if (regionStart == null) {
					break;
				}
				final IRegion regionEnd = finder.find(regionStart.getOffset() + regionStart.getLength(), suffixRegex, true, true, false, true);
				if (regionEnd == null) {
					break;
				}
				final TextDiagramIntent intent = new TextDiagramIntent(document, regionStart.getOffset() + regionStart.getLength(), this);
				if (resourceInfo != null) {
					intent.setResourceInfo(new ResourceInfo(resourceInfo));
				}
				// indicate a preference for diagram containing selection
				if (selectionStart >= 0 && selectionStart >= regionStart.getOffset() && selectionStart <= regionEnd.getOffset() + regionEnd.getLength()) {
					intent.setPriority(AbstractDiagramIntent.SELECTED_PRIORITY);
				}
				intents.add(intent);
				start = regionEnd.getOffset() + regionEnd.getLength();
			} catch (final BadLocationException e) {
			}
		}
		return intents;
	}

	public StringBuilder getDiagramTextLines(final IDocument document, final int selectionStart, final Map<String, Object> markerAttributes) {
		final boolean includeStart = prefix.startsWith("@"), includeEnd = suffix.startsWith("@");
		final FindReplaceDocumentAdapter finder = new FindReplaceDocumentAdapter(document);
		try {
			// search backward and forward start and end
			IRegion start = finder.find(selectionStart, prefixRegex, false, true, false, true);
			// if not start or end is before start, we must search backward
			if (start == null) {
				// use a slightly larger selection offset, in case the cursor is within startuml
				int altSelectionStart = Math.min(selectionStart + prefix.length(), document.getLength());
				start = finder.find(altSelectionStart, prefixRegex, false, true, false, true);
				if (start == null) {
					altSelectionStart = Math.min(selectionStart + prefixRegex.length(), document.getLength());
					start = finder.find(altSelectionStart, prefixRegex, false, true, false, true);
				}
			}
			if (start != null) {
				final int startOffset = start.getOffset(), startLine = document.getLineOfOffset(startOffset + (includeStart ? 0 : start.getLength()));
				final int startLinePos = document.getLineOffset(startLine);
				final IRegion end = finder.find(startOffset + start.getLength(), suffixRegex, true, true, false, true);
				if (end != null && end.getOffset() >= selectionStart) {
					final int endOffset = end.getOffset() + end.getLength();
					final String linePrefix = document.get(startLinePos, Math.max(0,  startOffset - startLinePos));
					final StringBuilder result = new StringBuilder();
					final int maxLine = Math.min(document.getLineOfOffset(endOffset) + (includeEnd ? 1 : 0), document.getNumberOfLines());
					for (int lineNum = startLine + (includeStart ? 0 : 1); lineNum < maxLine; lineNum++) {
						final String line = document.get(document.getLineOffset(lineNum), document.getLineLength(lineNum));
						// remove prefix
						final boolean startsWithLinePrefix = line.startsWith(linePrefix);
						final int pos1 = (startsWithLinePrefix ? linePrefix.length() : 0);
						// remove platform-specific line-ending
						final String lineEnd = document.getLineDelimiter(lineNum);
						final int pos2 = line.length() - (lineEnd != null ? lineEnd.length() : 0);
						// check specially for the case where the line equals the right-trimmed line prefix, i.e.
						if (startsWithLinePrefix || pos2 - pos1 >= linePrefix.length() || (! equalsRightTrimmed(line, pos2, linePrefix))) {
							result.append(line, pos1, pos2);
						}
						// use standard line-ending
						result.append("\n");
					}
					if (markerAttributes != null) {
						markerAttributes.put(IMarker.CHAR_START, start.getOffset());
					}
					return result;
				}
			}
		} catch (final BadLocationException e) {
		}
		return null;
	}

	private static boolean equalsRightTrimmed(final String s1, final int end, final String s2) {
		for (int i = 0; i < end; i++) {
			if (i >= s2.length() || s1.charAt(i) != s2.charAt(i)) {
				return false;
			}
		}
		// common prefix, now s2 should have just blanks
		for (int i = end; i < s2.length(); i++) {
			if (! Character.isWhitespace(s2.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public String getDiagramText(final CharSequence lines) {
		return getDiagramText(new StringBuilder(lines.toString()));
	}

	public String getDiagramText(final StringBuilder lines) {
		final int prefixPos = lines.indexOf(prefix);
		int start = Math.max(prefixPos, 0);
		final int suffixPos = lines.lastIndexOf(suffix);
		final int end = (suffixPos < 0 ? lines.length() : Math.min(suffixPos + suffix.length(), lines.length()));
		final String linePrefix = lines.substring(0, start).trim();
		final StringBuilder result = new StringBuilder(lines.length());
		if (prefixPos < 0) {
			result.append(PlantumlConstants.START_UML + "\n");
		}
		while (start < end) {
			int lineEnd = lines.indexOf("\n", start);
			if (lineEnd > end) {
				break;
			} else if (lineEnd < 0) {
				lineEnd = lines.length();
			}
			String line = lines.substring(start, lineEnd);
			if (line.startsWith(linePrefix)) {
				line = line.substring(linePrefix.length());
			} else if (line.matches("\\s+" + Pattern.quote(linePrefix) + ".*")) {
				int indexOfPrefix = line.indexOf(linePrefix);
				line = line.substring(indexOfPrefix + linePrefix.length());
				line = line.trim();
			}
			result.append(line);
			if (!line.equals(suffix)) {
				result.append("\n");
			}
			start = lineEnd + 1;
		}
		if (suffixPos < 0) {
			result.append(PlantumlConstants.END_UML);
		}
		return result.toString();
	}

	public String getDiagramText(final IFile file) {
		final IMarker marker = PlantumlUtil.getPlantUmlMarker(file, false);
		int startOffset = marker.getAttribute(IMarker.CHAR_START, 0);
		StringBuilder builder = null;
		try {
			final Scanner scanner = new Scanner(file.getContents());
			while (scanner.hasNextLine()) {
				final String nextLine = scanner.nextLine();
				if (builder == null) {
					if (startOffset <= nextLine.length()) {
						if (nextLine.indexOf(prefix, startOffset) >= 0) {
							builder = new StringBuilder();
						}
						startOffset = 0;
					} else {
						startOffset = startOffset - nextLine.length() - 1;
					}
				}
				if (builder != null) {
					builder.append(nextLine);
					builder.append("\n");
					if (nextLine.contains(suffix)) {
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
		return null;
	}

	public Iterator<ISelection> getDiagramText(final IDocument document) {
		final FindReplaceDocumentAdapter finder = new FindReplaceDocumentAdapter(document);
		int selectionStart = 0;
		final Collection<ISelection> selections = new ArrayList<ISelection>();
		try {
			while (true) {
				final IRegion start = finder.find(selectionStart, prefixRegex, true, true, false, true);
				final IRegion end = finder.find(selectionStart, suffixRegex, true, true, false, true);
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
