package net.sourceforge.plantuml.eclipse.model;

import java.awt.image.BufferedImage;

import org.eclipse.swt.graphics.ImageData;

/**
 * A image to display
 * @author pecoil_a
 */
public class DiagramImage {

	private ImageData imageData;
	private BufferedImage bufferedImage;
	
	public DiagramImage(BufferedImage bufferedImage, ImageData imageData) {
		this.bufferedImage = bufferedImage;
		this.imageData = imageData;
	}

	public ImageData getImageData() {
		return imageData;
	}

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}
	
}
