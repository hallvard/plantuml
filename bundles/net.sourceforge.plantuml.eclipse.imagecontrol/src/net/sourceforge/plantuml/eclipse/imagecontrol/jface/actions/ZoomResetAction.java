package net.sourceforge.plantuml.eclipse.imagecontrol.jface.actions;

import org.eclipse.jface.resource.ImageDescriptor;

import net.sourceforge.plantuml.eclipse.imagecontrol.ImageControl;

public class ZoomResetAction extends ImageControlAction {

	public ZoomResetAction(ImageControl imageControl) {
		super(imageControl);
		setImageDescriptor(ImageDescriptor.createFromFile(ImageControlAction.class, "/icons/ZoomReset16.gif"));
	}

	@Override
	public void run() {
		getControl().resetZoom();
	}
}
