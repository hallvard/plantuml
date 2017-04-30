package net.sourceforge.plantuml.eclipse.imagecontrol.jface.actions;

import org.eclipse.jface.action.Action;

import net.sourceforge.plantuml.eclipse.imagecontrol.ImageControl;

public abstract class ImageControlAction extends Action {

	private final ImageControl control;

	public ImageControlAction(ImageControl imageControl) {
		this.control = imageControl;
	}
	
	public ImageControl getControl() {
		return control;
	}
}
