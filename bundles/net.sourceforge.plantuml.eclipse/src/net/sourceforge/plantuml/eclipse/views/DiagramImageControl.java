package net.sourceforge.plantuml.eclipse.views;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IPath;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;

import net.sourceforge.plantuml.eclipse.imagecontrol.ImageControl;
import net.sourceforge.plantuml.eclipse.utils.LinkData;
import net.sourceforge.plantuml.util.DiagramData;

public class DiagramImageControl extends ImageControl {

	public DiagramImageControl(final Composite parent) {
		super(parent);
	}

	public DiagramImageControl(final Composite parent, final int style) {
		super(parent, style);
	}

	private DiagramData diagram;

	public IPath getSourcePath() {
		return diagram.getOriginal();
	}

	private ImageData imageData;

	public ImageData getImageData() {
		return imageData;
	}

	private Collection<LinkData> links = null;

	public Iterable<LinkData> getLinks() {
		return links;
	}

	public void updateDiagramImage(final DiagramData diagram, final int imageNum) {
		try {
			this.diagram = diagram;
			this.imageData = null;
			this.links = new ArrayList<LinkData>();
			final ImageData imageData = diagram.getImage(imageNum, links);
			if (imageData != null && (! isDisposed())) {
				getDisplay().asyncExec(new Runnable() {
					@Override
					public void run() {
						if (! isDisposed()) {
							loadImage(imageData);
							DiagramImageControl.this.imageData = imageData;
						}
					}
				});
			}
		} catch (final Throwable e) {
			if (! isDisposed()) {
				getDisplay().asyncExec(new Runnable() {
					@Override
					public void run() {
						if (! isDisposed()) {
							showErrorMessage(e);
						}
					}
				});
			}
		}
	}
}
