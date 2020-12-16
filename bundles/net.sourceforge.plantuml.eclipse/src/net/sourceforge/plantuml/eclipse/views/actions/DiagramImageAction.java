package net.sourceforge.plantuml.eclipse.views.actions;

import org.eclipse.core.runtime.IPath;
import org.eclipse.swt.graphics.ImageData;

import net.sourceforge.plantuml.eclipse.imagecontrol.jface.actions.ImageControlAction;
import net.sourceforge.plantuml.eclipse.views.DiagramImageControl;
import net.sourceforge.plantuml.util.DiagramImageData;

public abstract class DiagramImageAction extends ImageControlAction {

	protected final DiagramImageData diagramImageData;

	public DiagramImageAction(final DiagramImageControl control, final DiagramImageData diagramImageData) {
		super(control);
		this.diagramImageData = diagramImageData;
	}

	public DiagramImageAction(final DiagramImageControl control) {
		this(control, null);
	}

	protected DiagramImageData getDiagramImageData() {
		return ((DiagramImageControl) getControl()).getDiagramImageData();
	}

	protected IPath getSourcePath() {
		return getDiagramImageData().getSourcePath();
	}

	protected ImageData getImage() {
		return getDiagramImageData().getImage();
	}

	protected int getImageNum() {
		return getDiagramImageData().getImageNum();
	}
}
