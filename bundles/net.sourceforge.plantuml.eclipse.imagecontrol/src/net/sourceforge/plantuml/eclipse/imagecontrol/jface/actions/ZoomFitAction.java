package net.sourceforge.plantuml.eclipse.imagecontrol.jface.actions;

import org.eclipse.jface.resource.ImageDescriptor;

import net.sourceforge.plantuml.eclipse.imagecontrol.ImageControl;

public class ZoomFitAction extends ImageControlAction {

	public ZoomFitAction(ImageControl imageControl) {
		super(imageControl);
		setImageDescriptor(ImageDescriptor.createFromFile(ImageControlAction.class, "/icons/ZoomFit16.gif"));
	}

	@Override
	public void run() {
		getControl().fitZoom();
	}
}
