package net.sourceforge.plantuml.ecore;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

public class EcoreObjectDiagramTextProvider extends AbstractEcoreObjectDiagramTextProvider {

	public EcoreObjectDiagramTextProvider() {
		super(IEditingDomainProvider.class);
	}

	@Override
	protected String getDiagramText(final IEditorPart editorPart, final IEditorInput editorInput, final ISelection selection, final Map<String, Object> markerAttributes) {
		if (selection instanceof IStructuredSelection) {
			final Object sel = ((IStructuredSelection) selection).getFirstElement();
			if (sel instanceof EObject && (! AbstractEcoreClassDiagramTextProvider.isEcoreClassDiagramObject(sel))) {
				if (markerAttributes != null) {
					markerAttributes.put("eObjectUri", EcoreUtil.getURI((EObject) sel).toString());
				}
				return getDiagramText((EObject) sel);
			}
			return null;
		}
		return getDiagramText(((IEditingDomainProvider) editorPart).getEditingDomain().getResourceSet().getAllContents());
	}
}
