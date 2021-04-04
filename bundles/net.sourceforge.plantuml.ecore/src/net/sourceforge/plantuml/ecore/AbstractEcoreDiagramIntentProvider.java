package net.sourceforge.plantuml.ecore;

import java.util.Collection;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import net.sourceforge.plantuml.eclipse.utils.WorkbenchEditorPartDiagramIntentProviderContext;
import net.sourceforge.plantuml.eclipse.utils.WorkspaceDiagramIntentProviderContext;
import net.sourceforge.plantuml.text.AbstractDiagramIntentProvider;
import net.sourceforge.plantuml.util.DiagramIntent;

public abstract class AbstractEcoreDiagramIntentProvider extends AbstractDiagramIntentProvider {

	public AbstractEcoreDiagramIntentProvider() {
		super(IEditingDomainProvider.class);
	}

	protected AbstractEcoreDiagramIntentProvider(final Class<?> editorType) {
		super(editorType);
	}

	@Override
	public boolean supportsSelection(final ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			final Object object = ((IStructuredSelection) selection).getFirstElement();
			if (object instanceof EObject) {
				return supportsEObject((EObject) object);
			}
		}
		return false;
	}

	protected abstract boolean supportsEObject(final EObject object);

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
			if (supportsEObject(root)) {
				return getDiagramInfos(root);
			}
		}
		return null;
	}

	protected abstract Collection<DiagramIntent> getDiagramInfos(final EObject root);

	@Override
	protected boolean supportsPath(final IPath path) {
		return "xmi".equals(path.getFileExtension());
	}

	@Override
	protected Collection<DiagramIntent> getDiagramInfos(final WorkspaceDiagramIntentProviderContext context) {
		final URI uri = URI.createPlatformResourceURI(context.getPath().toString(), false);
		final ResourceSet resSet = new ResourceSetImpl();
		resSet.getResource(uri, true);
		return getDiagramInfos(resSet);
	}

	protected Collection<DiagramIntent> getDiagramInfos(final ResourceSet resourceSet) {
		for (final Resource resource : resourceSet.getResources()) {
			for (final EObject eObject : resource.getContents()) {
				if (supportsEObject(eObject)) {
					return getDiagramInfos(eObject);
				}
			}
		}
		return null;
	}
}
