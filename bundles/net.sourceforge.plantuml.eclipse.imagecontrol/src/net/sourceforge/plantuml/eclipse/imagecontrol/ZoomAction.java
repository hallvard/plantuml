package net.sourceforge.plantuml.eclipse.imagecontrol;

public class ZoomAction extends ImageControlAction {

	private float zoomRate;
	
	public ZoomAction(ImageControl imageControl, float zoomRate) {
		super(imageControl);
		this.zoomRate = zoomRate;
	}

	public void run() {
		getControl().zoomCentered(zoomRate);
	}
}
