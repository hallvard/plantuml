package net.sourceforge.plantuml.jdt;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.javaeditor.IClassFileEditorInput;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;

public class JavaEditorDiagramTextProvider extends JdtDiagramTextProvider {

	public JavaEditorDiagramTextProvider() {
		setEditorType(ITextEditor.class);
	}
	
	@Override
	protected String getDiagramText(IEditorPart editorPart, IEditorInput editorInput, ISelection ignore) {
		IType[] types = null;
		if (editorInput instanceof IFileEditorInput) {
			String ext = ((IFileEditorInput) editorInput).getFile().getFileExtension();
			if (! "java".equals(ext)) {
				return null;
			}
			ICompilationUnit compUnit = JavaCore.createCompilationUnitFrom(((IFileEditorInput) editorInput).getFile()); //currentContext.project.getFile(path.removeFirstSegments(1)));
			try {
				compUnit.open(new NullProgressMonitor());
				types = compUnit.getTypes();
			} catch (JavaModelException e) {
			}
		} else if (editorInput instanceof IClassFileEditorInput) {
			IClassFile classFile = ((IClassFileEditorInput) editorInput).getClassFile();
			try {
				classFile.open(new NullProgressMonitor());
				types = new IType[]{classFile.getType()};
			} catch (JavaModelException e) {
			}
		}
		if (types != null) {
			StringBuilder result = new StringBuilder();
			for (IType type: types) {
				generateForType(type, result, GEN_MEMBERS | GEN_MODIFIERS | GEN_EXTENDS | GEN_IMPLEMENTS, null);
			}
			return (result.length() > 0 ? result.toString() : null);
		}
		return null;
	}
}
