package net.sourceforge.plantuml.eclipse.views.actions;

import java.util.function.Supplier;

import org.eclipse.core.runtime.IPath;
import org.eclipse.swt.graphics.ImageData;

import net.sourceforge.plantuml.util.DiagramImageData;

public abstract class DiagramImageAction<C> extends DiagramAction<DiagramImageData, C> {

	public DiagramImageAction(final Supplier<DiagramImageData> diagramImageDataSupplier, final C context) {
		super(diagramImageDataSupplier, context);
	}

	protected DiagramImageData getDiagramImageData() {
		return getDiagramData();
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
