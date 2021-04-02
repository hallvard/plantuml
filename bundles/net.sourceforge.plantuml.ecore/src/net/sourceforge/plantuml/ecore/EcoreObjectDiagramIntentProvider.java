package net.sourceforge.plantuml.ecore;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import net.sourceforge.plantuml.eclipse.utils.WorkbenchEditorPartDiagramIntentProviderContext;
import net.sourceforge.plantuml.text.AbstractDiagramIntentProvider;
import net.sourceforge.plantuml.util.DiagramIntent;

public class EcoreObjectDiagramIntentProvider extends AbstractDiagramIntentProvider {

	public EcoreObjectDiagramIntentProvider() {
		super(IEditingDomainProvider.class);
	}

	protected EcoreObjectDiagramIntentProvider(final Class<?> editorType) {
		super(editorType);
	}

	@Override
	public boolean supportsSelection(final ISelection selection) {
		return selection instanceof IStructuredSelection && (! EcoreClassDiagramIntentProvider.isEcoreClassDiagramObject(((IStructuredSelection) selection).getFirstElement()));
	}

	//

	@Override
	protected Collection<DiagramIntent> getDiagramInfos(final WorkbenchEditorPartDiagramIntentProviderContext context) {
		if (context.getSelection() instanceof IStructuredSelection) {
			final Object selection = ((IStructuredSelection) context.getSelection()).getFirstElement();
			EObject root = null;
			if (selection instanceof Resource) {
				final Resource resource = (Resource) selection;
				if (resource.getContents().size() > 0) {
					root = resource.getContents().get(0);
				}
			} else if (selection instanceof EObject) {
				root = (EObject) selection;
			}
			return Collections.singletonList(new EcoreObjectDiagramIntent(root));
		}
		return null;
	}
}
