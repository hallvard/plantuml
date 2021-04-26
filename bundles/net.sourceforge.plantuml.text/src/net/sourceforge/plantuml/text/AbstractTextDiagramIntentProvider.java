package net.sourceforge.plantuml.text;

import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.texteditor.ITextEditor;

import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.eclipse.utils.WorkbenchEditorPartDiagramIntentProviderContext;
import net.sourceforge.plantuml.util.DiagramIntent;
import net.sourceforge.plantuml.util.ResourceInfo;

public abstract class AbstractTextDiagramIntentProvider extends AbstractDiagramIntentProvider {

	public AbstractTextDiagramIntentProvider() {
		setEditorType(ITextEditor.class);
	}

	@Override
	public Boolean supportsSelection(final ISelection selection) {
		return selection instanceof ITextSelection;
	}

	protected String getStartPlantUmlRegex() {
		return PlantumlConstants.START_UML;
	}

	protected String getEndPlantUmlRegex() {
		return PlantumlConstants.END_UML;
	}

	private TextDiagramIntentHelper textDiagramIntentHelper = null;

	public TextDiagramIntentHelper getTextDiagramIntentHelper() {
		if (textDiagramIntentHelper == null) {
			textDiagramIntentHelper = new TextDiagramIntentHelper(getStartPlantUml(), getStartPlantUmlRegex(), getEndPlantUml(), getEndPlantUmlRegex());
		}
		return textDiagramIntentHelper;
	}

	@Override
	protected Collection<? extends DiagramIntent> getDiagramInfos(final WorkbenchEditorPartDiagramIntentProviderContext context) {
		final ITextEditor textEditor = getWorkbenchPart(context.getEditorPart(), ITextEditor.class);
		final IDocument document = textEditor.getDocumentProvider().getDocument(context.getEditorPart().getEditorInput());
		final ISelection selection = context.getSelection();
		final int selectionStart = ((ITextSelection) (selection != null ? selection : textEditor.getSelectionProvider().getSelection())).getOffset();
		final ResourceInfo resourceInfo = new ResourceInfo();
		if (context.getPath() != null) {
			resourceInfo.setOriginalPath(context.getPath().toString());
		}
		return getTextDiagramIntentHelper().getDiagramInfos(document, selectionStart, resourceInfo);
	}

	public String getDiagramText(final CharSequence lines) {
		return getDiagramText(new StringBuilder(lines.toString()));
	}

	protected String getDiagramText(final StringBuilder lines) {
		return getTextDiagramIntentHelper().getDiagramText(lines);
	}

	public String getDiagramText(final IPath path) {
		final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
		if (file != null && file.exists()) {
			return getTextDiagramIntentHelper().getDiagramText(file);
		}
		return null;
	}
}
