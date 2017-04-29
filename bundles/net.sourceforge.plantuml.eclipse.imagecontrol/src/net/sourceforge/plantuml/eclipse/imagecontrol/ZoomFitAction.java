package net.sourceforge.plantuml.eclipse.imagecontrol;

public class ZoomFitAction extends ImageControlAction {

	public ZoomFitAction(ImageControl imageControl) {
		super(imageControl);
	}

	@Override
	public void run() {
		getControl().fitZoom();
	}
}
