package net.sourceforge.plantuml.jdt;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.ITextEditor;

import net.sourceforge.plantuml.eclipse.utils.WorkbenchPartDiagramIntentProviderContext;
import net.sourceforge.plantuml.text.AbstractDiagramIntentProvider;
import net.sourceforge.plantuml.util.DiagramIntent;

public class ClassFileEditorDiagramTextProvider extends AbstractDiagramIntentProvider {

	public ClassFileEditorDiagramTextProvider() {
		setEditorType(ITextEditor.class);
	}

	@Override
	protected Collection<DiagramIntent> getDiagramInfos(final WorkbenchPartDiagramIntentProviderContext context) {
		if (context.getWorkbenchPart() instanceof IEditorPart) {
			final IEditorInput editorInput = ((IEditorPart) context.getWorkbenchPart()).getEditorInput();
			IClassFile classFile = (IClassFile) editorInput.getAdapter(IClassFile.class);
			try {
				classFile.open(new NullProgressMonitor());
				return Collections.singletonList(new JdtDiagramIntent(Arrays.asList(classFile.getType())));
			} catch (final JavaModelException e) {
			}
		}
		return null;
	}
}
