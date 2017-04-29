package net.sourceforge.plantuml.eclipse.imagecontrol;

public class ZoomResetAction extends ImageControlAction {

	public ZoomResetAction(ImageControl imageControl) {
		super(imageControl);
	}

	@Override
	public void run() {
		getControl().resetZoom();
	}
}
