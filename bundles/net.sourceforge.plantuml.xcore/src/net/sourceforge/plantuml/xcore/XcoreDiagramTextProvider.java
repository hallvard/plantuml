package net.sourceforge.plantuml.xcore;

import net.sourceforge.plantuml.ecore.AbstractEcoreClassDiagramTextProvider;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

public class XcoreDiagramTextProvider extends AbstractEcoreClassDiagramTextProvider {

	public XcoreDiagramTextProvider() {
		super(XtextEditor.class);
	}

	protected String getDiagramText(IEditorPart editorPart, IEditorInput editorInput, ISelection ignore) {
		return ((XtextEditor) editorPart).getDocument().readOnly(new IUnitOfWork<String, XtextResource>() {
			public String exec(XtextResource state) throws Exception {
				return getDiagramText(((Resource) state).getResourceSet());
			}
		});
	}
}
