package net.sourceforge.plantuml.eclipse.imagecontrol.actions;

import org.eclipse.jface.resource.ImageDescriptor;

import net.sourceforge.plantuml.eclipse.imagecontrol.ImageControl;

public class ZoomAction extends ImageControlAction {

	private float zoomRate;
	
	public ZoomAction(ImageControl imageControl, float zoomRate) {
		super(imageControl);
		if (zoomRate == 1.0 || zoomRate <= 0.0) {
			throw new IllegalArgumentException("Illegal zoom rate value: " + zoomRate);
		}
		setImageDescriptor(ImageDescriptor.createFromFile(ImageControlAction.class, (zoomRate > 1.0 ? "/icons/ZoomIn16.gif" : "/icons/ZoomOut16.gif")));
		this.zoomRate = zoomRate;
	}

	public void run() {
		getControl().zoomCentered(zoomRate);
	}
}
