package net.sourceforge.plantuml.ecore;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import net.sourceforge.plantuml.eclipse.utils.WorkbenchEditorPartDiagramIntentProviderContext;
import net.sourceforge.plantuml.text.AbstractDiagramIntentProvider;
import net.sourceforge.plantuml.util.DiagramIntent;

public class EcoreClassDiagramIntentProvider extends AbstractDiagramIntentProvider {

	public EcoreClassDiagramIntentProvider() {
		this(IEditingDomainProvider.class);
	}

	protected EcoreClassDiagramIntentProvider(final Class<?> editorType) {
		super(editorType);
	}

	@Override
	public boolean supportsSelection(final ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			final Object firstElement = getSelectedElement(selection);
			if (firstElement instanceof EObject) {
				return supportsEObject((EObject) firstElement);
			}
		}
		return false;
	}

	private Object getSelectedElement(final ISelection selection) {
		Object firstElement = ((IStructuredSelection) selection).getFirstElement();
		if (firstElement instanceof Resource) {
			final Resource resource = (Resource) firstElement;
			if (resource.getContents().size() > 0) {
				firstElement = resource.getContents().get(0);
			}
		}
		return firstElement;
	}

	protected boolean supportsEObject(final EObject selection) {
		return isEcoreClassDiagramObject(selection);
	}

	protected final EcoreDiagramHelper diagramHelper = new EcoreDiagramHelper();

	protected EPackage getEPackage(final EObject selection) {
		return diagramHelper.getAncestor(selection, EPackage.class);
	}

	public static boolean isEcoreClassDiagramObject(final Object object) {
		return object instanceof EModelElement;
	}

	@Override
	protected Collection<DiagramIntent> getDiagramInfos(final WorkbenchEditorPartDiagramIntentProviderContext context) {
		if (context.getSelection() instanceof IStructuredSelection) {
			final EObject selection = (EObject) getSelectedElement(context.getSelection());
			final EPackage pack = getEPackage(selection);
			if (pack != null) {
				return Collections.singletonList(new EcoreClassDiagramIntent(Collections.singletonList(pack)));
			}
		}
		return null;
	}
}
