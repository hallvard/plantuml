package net.sourceforge.plantuml.eclipse.utils;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * Utils class on plantUml functionalities.
 * 
 * @author durif_c
 */
public class PlantUmlUtils {

    public static void createImageFile(String fileName, String format,
            BufferedImage image) {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(fileName);
            ImageIO.write(image, format, fos);

        } catch (IOException e) {
            WorkbenchUtil
                    .errorBox("Error during file generation for printing.");
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    WorkbenchUtil.errorBox("Error during file generation.");
                }
            }
        }
    }
    
	public static void createImageFileSvg(String filePathName, String textDiagram) {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(filePathName);
    		SourceStringReader reader = new SourceStringReader(textDiagram);
    		reader.generateImage(fos, new FileFormatOption(FileFormat.SVG));
        } catch (IOException e) {
            WorkbenchUtil
                    .errorBox("Error during file generation for printing.");
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    WorkbenchUtil.errorBox("Error during file generation.");
                }
            }
        }
		
	}

    public static ImageDescriptor getImageDescriptor(Display display, String fileName) {
        InputStream is = PlantUmlUtils.class.getResourceAsStream(fileName);   
        Image image = new Image(display, is);  
        return ImageDescriptor.createFromImage(image);    
    }


}
