package net.sourceforge.plantuml.jdt;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.core.resources.IFile;
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
	
	@Override
	protected String getDiagramText(IEditorPart editorPart, IEditorInput editorInput, ISelection ignore) {
		if (editorInput instanceof IFileEditorInput) {
			IFile file = ((IFileEditorInput) editorInput).getFile();
			if (! "java".equals(file.getFileExtension())) {
				return null;
			}
			ICompilationUnit compUnit = JavaCore.createCompilationUnitFrom(file);
			try {
				compUnit.open(new NullProgressMonitor());
				StringBuilder result = new StringBuilder();
				Collection<IType> allTypes = Arrays.asList(compUnit.getTypes());
				for (IType type: allTypes) {
					generateForType(type, result, allTypes);
				}
				return (result.length() > 0 ? result.toString() : null);
			} catch (JavaModelException e) {
			}
		}
		return null;
	}
}
