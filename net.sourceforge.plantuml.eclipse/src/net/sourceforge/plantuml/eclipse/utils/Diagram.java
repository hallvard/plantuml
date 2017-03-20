package net.sourceforge.plantuml.eclipse.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IWorkbenchPage;

import net.sourceforge.plantuml.FileSystem;
import net.sourceforge.plantuml.OptionFlags;
import net.sourceforge.plantuml.SourceStringReader;
import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.eclipse.Activator;

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
	private ImageData imageData;

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
	public ImageData getImage(IPath path) throws IOException {
		if (textDiagram != null) {
			// generate the image for textDiagram and imageNumber
			if (path != null) {
				FileSystem.getInstance().setCurrentDir(path.toFile().getAbsoluteFile().getParentFile());
			} else {
				FileSystem.getInstance().reset();
			}
			OptionFlags.getInstance().setQuiet(true);

			imageData = getImage(textDiagram, imageNumber);
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
		if (dotPath != null && (! "".equals(dotPath))) {
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
		return getImage(textDiagram, 0);
	}

	private static ImageData getImage(String textDiagram, int imageNum) {
		setGraphvizPath();

		ImageData imageData = null;
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();

			// image generation.
			SourceStringReader reader = new SourceStringReader(textDiagram);
			String desc = reader.generateImage(os, imageNum);
			os.flush();
			os.close();

			if (StringUtils.isNotEmpty(desc)) {
				InputStream is = new ByteArrayInputStream(os.toByteArray());
				imageData = new ImageData(is);
				is.close();
			}
		} catch (IOException e) {
			WorkbenchUtil.errorBox("Error during image generation.", e);
		}
		return imageData;
	}

	public String extractTextDiagram(String diagramText) {
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
		return textDiagram;
	}

	/**
	 * @return {@link ImageData}
	 */
	public ImageData getImageData() {
		return imageData;
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
