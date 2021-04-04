package net.sourceforge.plantuml.ecore;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import net.sourceforge.plantuml.util.DiagramIntent;

public class EcoreClassDiagramIntentProvider extends AbstractEcoreDiagramIntentProvider {

	public EcoreClassDiagramIntentProvider() {
		super();
	}

	protected EcoreClassDiagramIntentProvider(final Class<?> editorType) {
		super(editorType);
	}

	@Override
	protected boolean supportsPath(final IPath path) {
		return "ecore".equals(path.getFileExtension());
	}

	@Override
	protected boolean supportsEObject(final EObject object) {
		return isEcoreClassDiagramObject(object);
	}

	protected final EcoreDiagramHelper diagramHelper = new EcoreDiagramHelper();

	protected EPackage getEPackage(final EObject selection) {
		return diagramHelper.getAncestor(selection, EPackage.class);
	}

	public static boolean isEcoreClassDiagramObject(final Object object) {
		return object instanceof EModelElement;
	}

	@Override
	protected Collection<DiagramIntent> getDiagramInfos(final EObject eObject) {
		final EPackage pack = getEPackage(eObject);
		if (pack != null) {
			return Collections.singletonList(new EcoreClassDiagramIntent(Collections.singletonList(pack)));
		}
		return null;
	}
}
