package net.sourceforge.plantuml.text.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.ITextEditor;

import net.sourceforge.plantuml.eclipse.test.util.SelectionIterable;

public class TextEditorSelectionIterable implements SelectionIterable {

	private Collection<Integer> offsets;

	@Override
	public void init(final IWorkbenchPart part, final String spec) {
		if (! (part instanceof ITextEditor)) {
			throw new IllegalArgumentException(part + " is not instanceof " + ITextEditor.class);
		}
		final ITextEditor textEditor = (ITextEditor) part;
		final IDocument document = textEditor.getDocumentProvider().getDocument(textEditor.getEditorInput());
		if (spec != null) {
			// try spec as line numbers
			final String[] lineNums = spec.split("[,; ]");
			offsets = new ArrayList<Integer>();
			try {
				for (final String lineNum : lineNums) {
					offsets.add(document.getLineOfOffset(Integer.valueOf(lineNum)));
				}
			} catch (final NumberFormatException | BadLocationException e) {
				offsets = null;
			}
		}
		if (offsets == null) {
			// try spec as prefixes to search for
			offsets = new ArrayList<Integer>();
			final String prefix = (spec != null ? spec : "@start");
			final FindReplaceDocumentAdapter finder = new FindReplaceDocumentAdapter(document);
			int offset = 0;
			while (true) {
				try {
					final IRegion pos = finder.find(offset, prefix, true, true, false, true);
					if (pos == null) {
						break;
					}
					final int line = document.getLineOfOffset(pos.getOffset()) + 1;
					offset = document.getLineOffset(line);
					offsets.add(offset);
				} catch (final BadLocationException e) {
					break;
				}
			}
		}
	}

	@Override
	public Iterator<ISelection> iterator() {
		return new Iterator<ISelection>() {
			private final Integer[] offsets = TextEditorSelectionIterable.this.offsets.toArray(new Integer[0]);
			int pos = 0;
			@Override
			public boolean hasNext() {
				return pos < offsets.length;
			}

			@Override
			public ISelection next() {
				return new TextSelection(offsets[pos++], 0);
			}
		};
	}
}
