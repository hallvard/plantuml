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
	public Boolean supportsSelection(final ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			final Object object = ((IStructuredSelection) selection).getFirstElement();
			if (object instanceof EObject) {
				return supportsEObject((EObject) object);
			} else if (object instanceof Resource) {
				for (final EObject eObject : ((Resource) object).getContents()) {
					if (supportsEObject(eObject)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	protected abstract boolean supportsEObject(final EObject object);

	//

	@Override
	protected Collection<? extends DiagramIntent> getDiagramInfos(final WorkbenchEditorPartDiagramIntentProviderContext context) {
		if (context.getSelection() instanceof IStructuredSelection) {
			final Object selection = ((IStructuredSelection) context.getSelection()).getFirstElement();
			if (selection instanceof Resource) {
				for (final EObject eObject : ((Resource) selection).getContents()) {
					if (supportsEObject(eObject)) {
						final Collection<? extends DiagramIntent> diagramInfos = getDiagramInfos(eObject);
						if (diagramInfos != null) {
							return diagramInfos;
						}
					}
				}
			} else if (selection instanceof EObject && supportsEObject((EObject) selection)) {
				return getDiagramInfos((EObject) selection);
			}
		}
		return null;
	}

	protected abstract Collection<? extends DiagramIntent> getDiagramInfos(final EObject root);

	@Override
	protected Boolean supportsPath(final IPath path) {
		return "xmi".equals(path.getFileExtension());
	}

	@Override
	protected Collection<? extends DiagramIntent> getDiagramInfos(final WorkspaceDiagramIntentProviderContext context) {
		final URI uri = URI.createPlatformResourceURI(context.getPath().toString(), false);
		final ResourceSet resSet = new ResourceSetImpl();
		resSet.getResource(uri, true);
		return getDiagramInfos(resSet);
	}

	protected Collection<? extends DiagramIntent> getDiagramInfos(final ResourceSet resourceSet) {
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
