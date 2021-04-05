package net.sourceforge.plantuml.xcore;

import java.util.Collection;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import net.sourceforge.plantuml.eclipse.utils.WorkbenchEditorPartDiagramIntentProviderContext;
import net.sourceforge.plantuml.ecore.EcoreDiagramIntentProvider;
import net.sourceforge.plantuml.util.DiagramIntent;

public class XcoreDiagramIntentProvider extends EcoreDiagramIntentProvider {

	public XcoreDiagramIntentProvider() {
		super(XtextEditor.class);
	}

	@Override
	protected Collection<? extends DiagramIntent> getDiagramInfos(final WorkbenchEditorPartDiagramIntentProviderContext context) {
		return ((XtextEditor) context.getEditorPart()).getDocument().readOnly(new IUnitOfWork<Collection<? extends DiagramIntent>, XtextResource>() {
			@Override
			public Collection<? extends DiagramIntent> exec(final XtextResource state) throws Exception {
				return getDiagramInfos(((Resource) state).getResourceSet());
			}
		});
	}
}
