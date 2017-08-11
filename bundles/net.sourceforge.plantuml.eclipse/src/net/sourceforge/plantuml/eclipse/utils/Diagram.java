package net.sourceforge.plantuml.eclipse.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IWorkbenchPage;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.FileSystem;
import net.sourceforge.plantuml.OptionFlags;
import net.sourceforge.plantuml.SourceStringReader;
import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.core.DiagramDescription;
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

	private int imageCount;

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
	public ImageData getImage(IPath path, int imageNum, Collection<LinkData> links) throws IOException {
		if (textDiagram != null) {
			// generate the image for textDiagram and imageNumber
			if (path != null) {
				FileSystem.getInstance().setCurrentDir(path.toFile().getAbsoluteFile().getParentFile());
			} else {
				FileSystem.getInstance().reset();
			}
			OptionFlags.getInstance().setQuiet(true);
			return getImage(textDiagram, imageNum, links);
		}
		return null;
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

	public static ImageData getImage(String textDiagram) {
		return getImage(textDiagram, 0, null);
	}

	private static FileFormatOption layoutFormatOption = new FileFormatOption(FileFormat.PNG);

	private static ImageData getImage(String textDiagram, int imageNum, Collection<LinkData> links) {
		setGraphvizPath();
		ImageData imageData = null;
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			// image generation
			SourceStringReader reader = new SourceStringReader(textDiagram);
			DiagramDescription desc = reader.outputImage(os, imageNum);
			if (links != null) {
				String cMapData = reader.getCMapData(0, layoutFormatOption);
				if (cMapData != null) {
					parseImageMapString(cMapData, links);
				}
			}
			os.close();

			if (StringUtils.isNotEmpty(desc.getDescription())) {
				InputStream is = new ByteArrayInputStream(os.toByteArray());
				imageData = new ImageData(is);
				is.close();
			}
		} catch (IOException e) {
			WorkbenchUtil.errorBox("Error during image generation.", e);
		}
		return imageData;
	}

	private static void parseImageMapString(String cMapData, Collection<LinkData> links) {
		String[] areaElements = cMapData.split(Pattern.quote("<area "));
		for (String areaElement : areaElements) {
//			int pos = areaElement.indexOf('>');
//			if (pos >= 0) {
//				areaElement = areaElement.substring(0, pos);
//				if (areaElement.endsWith("/")) {
//					areaElement = areaElement.substring(0, areaElement.length() - 1);
//				}
//			}
			LinkData link = new LinkData();
			link.href = getAttributeValue(areaElement, "href");
			link.title = getAttributeValue(areaElement, "title");
			link.altText = getAttributeValue(areaElement, "alt");
			String coords = getAttributeValue(areaElement, "coords");
			if (coords != null) {
				String[] ints = coords.split(",");
				if (ints.length == 4) {
					try {
						int x1 = Integer.valueOf(ints[0]), y1 = Integer.valueOf(ints[1]), x2 = Integer.valueOf(ints[2]), y2 = Integer.valueOf(ints[3]);
						link.rect = new Rectangle(x1, y1, x2 - x1, y2 - y1);
					} catch (NumberFormatException e) {
					}
				}
			}
			links.add(link);
		}
	}
	/*
	    @startuml
		actor Bob [[http://plantuml.com/sequence]]
		actor "This is [[http://plantuml.com/sequence Alice]] actor" as Alice
		Bob -> Alice [[http://plantuml.com/start]] : hello
		note left [[http://plantuml.com/start]]
		a note with a link
		end note
		Alice -> Bob : hello with [[http://plantuml.com/start{Tooltip for message} some link]]
		note right [[http://plantuml.com/start]] : another note
		note left of Bob
		You can use [[http://plantuml.com/start links in notes]] also.
		end note
		@enduml

		<map id="plantuml_map" name="plantuml_map">
		<area shape="rect" id="id1" href="http://plantuml.com/sequence" title="http://plantuml.com/sequence" alt="" coords="375,221,408,238"/>
		<area shape="rect" id="id2" href="http://plantuml.com/sequence" title="http://plantuml.com/sequence" alt="" coords="375,68,408,85"/>
		<area shape="rect" id="id3" href="http://plantuml.com/start" title="Tooltip for message" alt="" coords="321,143,383,158"/>
		<area shape="rect" id="id4" href="http://plantuml.com/start" title="http://plantuml.com/start" alt="" coords="94,183,180,199"/>
		<area shape="rect" id="id5" href="http://plantuml.com/start" title="http://plantuml.com/start" alt="" coords="245,104,388,126"/>
		<area shape="rect" id="id6" href="http://plantuml.com/start" title="http://plantuml.com/start" alt="" coords="99,101,229,126"/>
		<area shape="rect" id="id7" href="http://plantuml.com/start" title="http://plantuml.com/start" alt="" coords="239,140,496,165"/>
		<area shape="rect" id="id8" href="http://plantuml.com/sequence" title="http://plantuml.com/sequence" alt="" coords="222,5,248,257"/>
		</map>
	 */
	
	private static String getAttributeValue(String element, String attributeName) {
		String prefix = attributeName + "=\"";
		int start = element.indexOf(prefix);
		if (start >= 0) {
			start += prefix.length();
			String suffix = "\"";
			int end = element.indexOf(suffix, start);
			if (end > start) {
				return element.substring(start, end);
			}
		}
		return null;
	}

	private Pattern pattern = Pattern.compile("(?i)(?m)^\\W*newpage( .*)?$");

	public void setTextDiagram(String diagramText) {
		textDiagram = diagramText;
		if (textDiagram != null) {
			imageCount = 1;
			// We must count the number of "newpage" instructions
			Matcher matcherNewpage = pattern.matcher(diagramText);
			while (matcherNewpage.find()) {
				imageCount++;
			}
		}
	}

	public String getTextDiagram() {
		return textDiagram;
	}

	public int getImageCount() {
		return imageCount;
	}
}
