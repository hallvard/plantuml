package net.sourceforge.plantuml.jdt;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;

import net.sourceforge.plantuml.eclipse.utils.WorkbenchEditorPartDiagramIntentProviderContext;
import net.sourceforge.plantuml.text.AbstractDiagramIntentProvider;
import net.sourceforge.plantuml.util.DiagramIntent;

public class JavaEditorDiagramIntentProvider extends AbstractDiagramIntentProvider {

	public JavaEditorDiagramIntentProvider() {
		setEditorType(ITextEditor.class);
	}

	@Override
	protected Collection<DiagramIntent> getDiagramInfos(final WorkbenchEditorPartDiagramIntentProviderContext context) {
		final IEditorInput editorInput = context.getEditorPart().getEditorInput();
		if (editorInput instanceof IFileEditorInput) {
			final IFile file = ((IFileEditorInput) editorInput).getFile();
			if (! "java".equals(file.getFileExtension())) {
				return null;
			}
			final ICompilationUnit compUnit = JavaCore.createCompilationUnitFrom(file);
			try {
				compUnit.open(new NullProgressMonitor());
				return Collections.singletonList(new JdtDiagramIntent(Arrays.asList(compUnit.getTypes())));
			} catch (final JavaModelException e) {
			}
		}
		return null;
	}
}
