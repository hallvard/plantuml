package net.sourceforge.plantuml.ecore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import net.sourceforge.plantuml.util.AbstractDiagramIntent;
import net.sourceforge.plantuml.util.DiagramIntent;

public class EcoreDiagramIntentProvider extends AbstractEcoreDiagramIntentProvider {

	public EcoreDiagramIntentProvider() {
		super();
	}

	protected EcoreDiagramIntentProvider(final Class<?> editorType) {
		super(editorType);
	}

	@Override
	protected Boolean supportsPath(final IPath path) {
		return "ecore".equals(path.getFileExtension()) || "xmi".equals(path.getFileExtension());
	}

	@Override
	protected boolean supportsEObject(final EObject object) {
		return true;
	}

	protected final EcoreDiagramHelper diagramHelper = new EcoreDiagramHelper();

	protected EPackage getEPackage(final EObject selection) {
		return diagramHelper.getAncestor(selection, EPackage.class);
	}

	public static boolean isEcoreClassDiagramObject(final Object object) {
		return object instanceof EModelElement;
	}

	@Override
	protected Collection<? extends DiagramIntent> getDiagramInfos(final EObject eObject) {
		final Collection<AbstractDiagramIntent<?>> diagrams = new ArrayList<>();
		final boolean isEcoreClassDiagram = isEcoreClassDiagramObject(eObject);
		final EPackage pack = getEPackage(isEcoreClassDiagram ? eObject : eObject.eClass());
		if (! isEcoreClassDiagram) {
			diagrams.add(new EcoreObjectDiagramIntent(eObject));
		}
		if (pack != null) {
			final EcoreClassDiagramIntent classDiagramIntent = new EcoreClassDiagramIntent(Collections.singletonList(pack));
			if (! isEcoreClassDiagram) {
				classDiagramIntent.setPriority(-1);
			}
			diagrams.add(classDiagramIntent);
		}
		return diagrams;
	}
}
