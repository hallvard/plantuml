package net.sourceforge.plantuml.jdt;

import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.javaeditor.IClassFileEditorInput;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.ITextEditor;

public class ClassFileEditorDiagramTextProvider extends JdtDiagramTextProvider {

	public ClassFileEditorDiagramTextProvider() {
		setEditorType(ITextEditor.class);
	}
	
	@Override
	protected String getDiagramText(IEditorPart editorPart, IEditorInput editorInput, ISelection ignore, Map<String, Object> ignore2) {
		if (editorInput instanceof IClassFileEditorInput) {
			IClassFile classFile = ((IClassFileEditorInput) editorInput).getClassFile();
			try {
				classFile.open(new NullProgressMonitor());
				StringBuilder result = new StringBuilder();
				generateForType(classFile.getType(), result, GEN_MEMBERS | GEN_MODIFIERS | GEN_EXTENDS | GEN_IMPLEMENTS, null);
				return (result.length() > 0 ? result.toString() : null);
			} catch (JavaModelException e) {
			}
		}
		return null;
	}
}
