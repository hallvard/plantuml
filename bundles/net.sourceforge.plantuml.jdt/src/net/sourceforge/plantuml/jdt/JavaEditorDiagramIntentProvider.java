package net.sourceforge.plantuml.jdt;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;

import net.sourceforge.plantuml.eclipse.utils.WorkbenchEditorPartDiagramIntentProviderContext;
import net.sourceforge.plantuml.eclipse.utils.WorkspaceDiagramIntentProviderContext;
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
			return getDiagramInfos(((IFileEditorInput) editorInput).getFile());
		}
		return null;
	}

	@Override
	protected Boolean supportsPath(final IPath path) {
		return "java".equals(path.getFileExtension());
	}

	private Collection<DiagramIntent> getDiagramInfos(final IFile file) {
		if (file != null && supportsPath(file.getFullPath())) {
			final ICompilationUnit compUnit = JavaCore.createCompilationUnitFrom(file);
			try {
				compUnit.open(new NullProgressMonitor());
				return Collections.singletonList(new JdtDiagramIntent(Arrays.asList(compUnit.getTypes())));
			} catch (final JavaModelException e) {
			}
		}
		return null;
	}

	@Override
	protected Collection<DiagramIntent> getDiagramInfos(final WorkspaceDiagramIntentProviderContext context) {
		final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(context.getPath());
		return getDiagramInfos(file);
	}
}
