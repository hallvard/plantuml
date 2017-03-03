package net.sourceforge.plantuml.jdt;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;

public class JavaEditorDiagramTextProvider extends JdtDiagramTextProvider {

	public JavaEditorDiagramTextProvider() {
		setEditorType(ITextEditor.class);
	}

	private class Context {
//		IEditorPart editorPart;
//		IEditorInput editorInput;
		IProject project;
//		IJavaProject javaProject;
		ICompilationUnit compUnit;
	}
	
	private Context currentContext = null;
	
	@Override
	protected String getDiagramText(IEditorPart editorPart, IEditorInput editorInput, ISelection ignore) {
		if (! (editorInput instanceof IFileEditorInput && "java".equals(((IFileEditorInput) editorInput).getFile().getFileExtension()))) {
			return null;
		}
		currentContext = new Context();
		IPath path = ((IFileEditorInput) editorInput).getFile().getFullPath();
		currentContext.project = ResourcesPlugin.getWorkspace().getRoot().getProject(path.segment(0));
//		currentContext.javaProject = JavaCore.create(currentContext.project);
		currentContext.compUnit = JavaCore.createCompilationUnitFrom(currentContext.project.getFile(path.removeFirstSegments(1)));
		StringBuilder result = new StringBuilder();
		try {
			currentContext.compUnit.open(new NullProgressMonitor());
			for (IType type: currentContext.compUnit.getTypes()) {
				generateForType(type, result, GEN_MEMBERS | GEN_MODIFIERS | GEN_EXTENDS | GEN_IMPLEMENTS, null);
			}
		} catch (JavaModelException e) {
			System.err.println(e);
		} finally {
			currentContext = null;
		}
		return (result.length() > 0 ? result.toString() : null);
	}
}
