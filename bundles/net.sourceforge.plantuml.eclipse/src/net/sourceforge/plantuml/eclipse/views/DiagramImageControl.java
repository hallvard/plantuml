package net.sourceforge.plantuml.eclipse.views;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IPath;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;

import net.sourceforge.plantuml.eclipse.imagecontrol.ImageControl;
import net.sourceforge.plantuml.eclipse.utils.Diagram;
import net.sourceforge.plantuml.eclipse.utils.LinkData;

public class DiagramImageControl extends ImageControl {

	public DiagramImageControl(Composite parent) {
		super(parent);
	}
	
	public DiagramImageControl(Composite parent, int style) {
		super(parent, style);
	}

	private ImageData imageData;
	
	public ImageData getImageData() {
		return imageData;
	}
	
	private Collection<LinkData> links = null;
	
	public Iterable<LinkData> getLinks() {
		return links;
	}
	
	public void updateDiagramImage(IPath path, Diagram diagram, int imageNum) {
		try {
			this.imageData = null;
			this.links = new ArrayList<LinkData>();
			final ImageData imageData = diagram.getImage(path, imageNum, links);
			if (imageData != null && (! isDisposed())) {
				getDisplay().asyncExec(new Runnable() {
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
