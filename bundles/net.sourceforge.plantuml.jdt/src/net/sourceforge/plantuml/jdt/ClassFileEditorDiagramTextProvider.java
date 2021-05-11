package net.sourceforge.plantuml.jdt;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.javaeditor.IClassFileEditorInput;
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
			if (editorInput instanceof IClassFileEditorInput) {
				final IClassFile classFile = ((IClassFileEditorInput) editorInput).getClassFile();
				try {
					classFile.open(new NullProgressMonitor());
					return Collections.singletonList(new JdtDiagramIntent(Arrays.asList(classFile.getType())));
				} catch (final JavaModelException e) {
				}
			}
		}
		return null;
	}
}
