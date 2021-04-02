package net.sourceforge.plantuml.eclipse.views;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;

import net.sourceforge.plantuml.eclipse.imagecontrol.ImageControl;
import net.sourceforge.plantuml.util.DiagramImageData;

public class DiagramImageControl extends ImageControl {

	public DiagramImageControl(final Composite parent) {
		super(parent);
	}

	public DiagramImageControl(final Composite parent, final int style) {
		super(parent, style);
	}

	private DiagramImageData diagramImageData;

	public DiagramImageData getDiagramImageData() {
		return diagramImageData;
	}

	public void updateDiagramImage(final DiagramImageData diagramImageData) {
		this.diagramImageData = diagramImageData;
		try {
			final ImageData imageData = diagramImageData.getImage();
			if (imageData != null && (! isDisposed())) {
				loadImage(imageData);
			}
		} catch (final Throwable e) {
			if (! isDisposed()) {
				showErrorMessage(e);
			}
		}
	}
}
