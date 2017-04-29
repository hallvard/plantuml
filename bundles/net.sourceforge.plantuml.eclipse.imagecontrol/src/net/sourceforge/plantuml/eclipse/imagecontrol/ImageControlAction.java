package net.sourceforge.plantuml.eclipse.imagecontrol;

import org.eclipse.jface.action.Action;

public abstract class ImageControlAction extends Action {

	private final ImageControl control;

	public ImageControlAction(ImageControl imageControl) {
		this.control = imageControl;
	}
	
	public ImageControl getControl() {
		return control;
	}
}
