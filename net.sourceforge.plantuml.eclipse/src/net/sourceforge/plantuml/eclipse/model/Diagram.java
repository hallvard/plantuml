package net.sourceforge.plantuml.eclipse.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import net.sourceforge.plantuml.FileSystem;
import net.sourceforge.plantuml.OptionFlags;
import net.sourceforge.plantuml.SourceStringReader;
import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.eclipse.Activator;
import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.eclipse.utils.WorkbenchUtil;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IWorkbenchPage;

/**
 * Definition of a Diagram Object.
 * 
 * @author durif_c
 */
public class Diagram {

	/**
	 * Script used to display the diagram (including
	 * 
	 * @startuml and
	 * @enduml)
	 */
	private String textDiagram;

	/**
	 * Current image number (for textDiagram with newpage instruction)
	 */
	private int imageNumber;

	/**
	 * Diagram
	 */
	private DiagramImage image;

	/**
	 * Create a diagram
	 * 
	 */
	public Diagram() {
	}

	public static IPath getActiveEditorPath() {
		IWorkbenchPage activePage = WorkbenchUtil.getCurrentActiveWindows().getActivePage();
		if (activePage != null) {
			IEditorPart part = activePage.getActiveEditor();
			if (part != null) {
				IEditorInput input = part.getEditorInput();
				if (input instanceof IPathEditorInput) {
					return ((IPathEditorInput) input).getPath();
				}
			}
		}
		return null;
	}

	/**
	 * Generate the DiagramImage for textDiagram and imageNumber. Use
	 * extractTextDiagram(int cursorPosition, String contents) to set
	 * textDiagram and imageNumber
	 * 
	 * @return ImageData of the current textDiagram and imageNumber
	 */
	public ImageData getImage(IPath path) {
		setGraphvizPath();

		if (textDiagram != null) {
			// generate the image for textDiagram and imageNumber
			try {
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				if (path != null) {
					FileSystem.getInstance().setCurrentDir(path.toFile().getAbsoluteFile().getParentFile());
				} else {
					FileSystem.getInstance().reset();
				}
				OptionFlags.getInstance().setQuiet(true);

				// image generation.
				SourceStringReader reader = new SourceStringReader(textDiagram);

				// Write the image to "os"
				String desc = reader.generateImage(os, imageNumber);

				// close all the flow.
				os.flush();
				os.close();

				if (StringUtils.isNotEmpty(desc)) {

					InputStream is = new ByteArrayInputStream(os.toByteArray());
					BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(os.toByteArray()));
					ImageData imageData = new ImageData(is);
					image = new DiagramImage(bufferedImage, imageData);

					// close the input stream
					is.close();

				}
			} catch (Throwable e) {
				e.printStackTrace();
				WorkbenchUtil.errorBox("Error during image generation.", e);
			}
		}

		return getImageData();
	}

	/**
	 * Set the Graphviz path with the path in the preference store if it is
	 * filled
	 */
	private static void setGraphvizPath() {
		IPreferenceStore prefStore = Activator.getDefault().getPreferenceStore();
		String dotPath = prefStore.getString(PlantumlConstants.GRAPHVIZ_PATH);
		if (dotPath != null && !"".equals(dotPath)) {
			System.setProperty("GRAPHVIZ_DOT", dotPath);
		}
	}

	/**
	 * Get first ImageData for the textDiagram in parameter
	 * 
	 * @param textDiagram
	 * 
	 * @return ImageData
	 */
	public static ImageData getImage(String textDiagram) {
		setGraphvizPath();

		ImageData imageData = null;
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();

			// image generation.
			SourceStringReader reader = new SourceStringReader(textDiagram);
			// Write the first image to "os"
			String desc = reader.generateImage(os, 0);

			// close all the flow.
			os.flush();
			os.close();

			if (StringUtils.isNotEmpty(desc)) {

				InputStream is = new ByteArrayInputStream(os.toByteArray());
				imageData = new ImageData(is);

				// close the input stream
				is.close();

			}
		} catch (IOException e) {
			WorkbenchUtil.errorBox("Error during image generation.", e);
		}
		return imageData;
	}

	/**
	 * Method to get the startUml script in the contents using regular
	 * expressions. We use the position of the cursor to determine the good
	 * script to keep in the case of there are many plantUml scripts in the
	 * file. This method sets the textDiagram and the imageNumber parameters.
	 * 
	 * @param cursorPosition
	 *            position of the cursor at the end of the current line
	 * @param contents
	 *            {@link String}
	 * 
	 * @return the PlantUml Script {@link String}
	 * 
	 * @author roques_a
	 */
	public String extractTextDiagram(int cursorPosition, String contents) {
		if (cursorPosition < 0) {
			return extractTextDiagram(contents);
		}
		String startingString = contents.substring(0, cursorPosition);

		if (startingString.indexOf("@startuml") == -1) {
			// to optimize, we don't use pattern if @startuml is not found
			return extractTextDiagram(null);
		}

		Pattern patternStart = Pattern.compile("(?s)(?m).*^(\\W*@startuml.*)");
		Matcher matcherStart = patternStart.matcher(startingString);
		if (matcherStart.find() == false) {
			// Not found
			return extractTextDiagram(null);
		}

		// part1 contains the string starting from startuml up to the cursor
		// position
		String part1 = matcherStart.group(1);
		// part1 = part1.replaceAll("@enduml[ */\\t]+", "@enduml");
		String part2 = "";
		if (part1.endsWith("@enduml") == false) {
			if (part1.contains("@enduml")) {
				return extractTextDiagram(null);
			}
			String endingString = contents.substring(cursorPosition);
			if (endingString.indexOf("@enduml") == -1) {
				// to optimize, we don't use pattern if @startuml is not found
				return extractTextDiagram(null);
			}
			Pattern patternEnd = Pattern.compile("(?s)(?m)(.*?^\\W*@enduml)");
			Matcher matcherEnd = patternEnd.matcher(endingString);
			if (matcherEnd.find() == false) {
				// Not found
				return extractTextDiagram(null);
			}
			part2 = matcherEnd.group(1);

			// Remove the * or space in the enduml
			// part2 = part2.replaceAll("(?s)(?m)^\\W+@enduml", "@enduml");
		}
		return extractTextDiagram(part1 + part2); 
	}
	
	private String extractTextDiagram(String diagramText) {
		textDiagram = diagramText;
		if (textDiagram == null) {
			return null;
		}
			
		// We must count the number of "newpage xxx" between startuml and the
		// cursor position, ie in part1
		Matcher matcherNewpage = Pattern.compile("(?i)(?m)^\\W*newpage( .*)?$").matcher(diagramText);

		imageNumber = 0;
		while (matcherNewpage.find()) {
			imageNumber++;
		}

//		String partAll = part1 + part2;
		// System.err.println("partAll="+partAll);
		// partAll = partAll.replaceAll("(?s)(?m)^( \\* |[ *~\\t/]*)", "");
//		textDiagram = partAll;
		return textDiagram;
	}

	/**
	 * @return {@link ImageData}
	 */
	public ImageData getImageData() {
		return (image != null ? image.getImageData() : null);
	}

	/**
	 * @return {@link BufferedImage}
	 */
	public BufferedImage getBufferedImage() {
		return image.getBufferedImage();
	}

	/**
	 * {@link String}
	 */
	public String getTextDiagram() {
		return textDiagram;
	}

	/**
	 * @return int
	 */
	public int getImageNumber() {
		return imageNumber;
	}

}
