package net.sourceforge.plantuml.eclipse.imagecontrol.jface.actions;

import java.util.function.Supplier;

import org.eclipse.jface.resource.ImageDescriptor;

import net.sourceforge.plantuml.eclipse.imagecontrol.ImageControl;

public class ZoomAction extends ControlAction<ImageControl> {

	private final float zoomRate;

	public ZoomAction(final Supplier<ImageControl> imageControlSupplier, final float zoomRate) {
		super(imageControlSupplier);
		if (zoomRate == 1.0 || zoomRate <= 0.0) {
			throw new IllegalArgumentException("Illegal zoom rate value: " + zoomRate);
		}
		setImageDescriptor(ImageDescriptor.createFromFile(ControlAction.class, (zoomRate > 1.0 ? "/icons/ZoomIn16.gif" : "/icons/ZoomOut16.gif")));
		this.zoomRate = zoomRate;
	}

	@Override
	public void run() {
		getControl().zoomCentered(zoomRate);
	}
}
