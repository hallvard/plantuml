package net.sourceforge.plantuml.text.console;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleListener;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.IPatternMatchListener;
import org.eclipse.ui.console.PatternMatchEvent;
import org.eclipse.ui.console.TextConsole;

import net.sourceforge.plantuml.text.AbstractDiagramTextProvider;
import net.sourceforge.plantuml.text.TextDiagramHelper;

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

	private final TextDiagramHelper textDiagramHelper  = new TextDiagramHelper(getStartPlantUml(), getStartPlantUml(), getEndPlantUml(), getEndPlantUml());

	private final IPatternMatchListener patternMatchListener = new IPatternMatchListener() {
		@Override
		public void connect(final TextConsole console) {
		}
		@Override
		public void matchFound(final PatternMatchEvent matchEvent) {
			try {
				final IDocument contents = ((TextConsole) matchEvent.getSource()).getDocument();
				final String match = contents.get(matchEvent.getOffset(), matchEvent.getLength());
				setSelection(textDiagramHelper.getDiagramText(match));
			} catch (final BadLocationException e) {
			}
		}
		@Override
		public void disconnect() {
			setSelection(StructuredSelection.EMPTY);
		}

		private final String qualifier = getStartPlantUml();
		private final String pattern = qualifier + "((.|\\n)*)" + getEndPlantUml();

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

	@Override
	public boolean supportsSelection(final ISelection selection) {
		return selection instanceof ITextSelection;
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
