package net.sourceforge.plantuml.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Rectangle;

import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileSystem;
import net.sourceforge.plantuml.OptionFlags;
import net.sourceforge.plantuml.SourceStringReader;
import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.core.DiagramDescription;
import net.sourceforge.plantuml.eclipse.Activator;
import net.sourceforge.plantuml.eclipse.utils.LinkData;
import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.eclipse.utils.WorkbenchUtil;
import net.sourceforge.plantuml.security.SFile;

/**
 * Definition of a Diagram Object.
 *
 * @author durif_c
 */
public class DiagramData {

	// diagram source
	private final String textDiagram;
	// number of images, derived from textDiagram
	private int imageCount;
	// the file from which the diagram was derived/extracted, used as context for resolving includes
	private IPath original;
	// attributes from file marker
	private Map<String, Object> markerAttributes;

	private static final Pattern pattern = Pattern.compile("(?i)(?m)^\\W*newpage( .*)?$");

	public DiagramData(final String diagramText) {
		textDiagram = diagramText;
		imageCount = 1;
		// We must count the number of "newpage" instructions
		final Matcher matcherNewpage = pattern.matcher(diagramText);
		while (matcherNewpage.find()) {
			imageCount++;
		}
	}

	public String getTextDiagram() {
		return textDiagram;
	}

	public int getImageCount() {
		return imageCount;
	}

	public void setOriginal(final IPath original) {
		this.original = original;
	}

	public IPath getOriginal() {
		return original;
	}

	public Map<String, Object> getMarkerAttributes() {
		return markerAttributes;
	}

	public void setMarkerAttributes(final Map<String, Object> markerAttributes) {
		this.markerAttributes = markerAttributes;
	}

	/**
	 * Generate the DiagramImage for textDiagram and imageNumber. Use
	 * extractTextDiagram(int cursorPosition, String contents) to set
	 * textDiagram and imageNumber
	 *
	 * @return ImageData of the current textDiagram and imageNumber
	 */
	public ImageData getImage(final int imageNum, final Collection<LinkData> links) {
		// generate the image for textDiagram and imageNumber
		if (original != null) {
			// find the real file, which may be linked and thus is not located under the root itself
			final IResource member = ResourcesPlugin.getWorkspace().getRoot().getFile(original);
			final SFile dirPath = SFile.fromFile(member.getLocation().toFile().getAbsoluteFile().getParentFile());
			FileSystem.getInstance().setCurrentDir(dirPath);
		} else {
			FileSystem.getInstance().reset();
		}
		OptionFlags.getInstance().setQuiet(true);
		return getImage(textDiagram, imageNum, links);
	}

	public ImageData getImage() {
		return getImage(0, null);
	}

	/**
	 * Set the Graphviz path with the path in the preference store if it is
	 * filled
	 */
	private static void setGraphvizPath() {
		final IPreferenceStore prefStore = Activator.getDefault().getPreferenceStore();
		final String dotPath = prefStore.getString(PlantumlConstants.GRAPHVIZ_PATH);
		if (dotPath != null && (! "".equals(dotPath))) {
			System.setProperty("GRAPHVIZ_DOT", dotPath);
		}
	}

	private static FileFormatOption layoutFormatOption = new FileFormatOption(FileFormat.PNG);

	private static ImageData getImage(final String textDiagram, final int imageNum, final Collection<LinkData> links) {
		setGraphvizPath();
		ImageData imageData = null;
		try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
			// image generation
			final SourceStringReader reader = new SourceStringReader(textDiagram);
			final DiagramDescription desc = reader.outputImage(os, imageNum);
			if (links != null) {
				final String cMapData = reader.getCMapData(0, layoutFormatOption);
				if (cMapData != null) {
					parseImageMapString(cMapData, links);
				}
			}
			os.flush();
			if (desc != null && StringUtils.isNotEmpty(desc.getDescription())) {
				try (InputStream is = new ByteArrayInputStream(os.toByteArray())) {
					imageData = new ImageData(is);
				}
			}
		} catch (final IOException e) {
			WorkbenchUtil.errorBox("Error during image generation.", e);
		}
		return imageData;
	}

	private static void parseImageMapString(final String cMapData, final Collection<LinkData> links) {
		final String[] areaElements = cMapData.split(Pattern.quote("<area "));
		for (final String areaElement : areaElements) {
			//			int pos = areaElement.indexOf('>');
			//			if (pos >= 0) {
			//				areaElement = areaElement.substring(0, pos);
			//				if (areaElement.endsWith("/")) {
			//					areaElement = areaElement.substring(0, areaElement.length() - 1);
			//				}
			//			}
			final LinkData link = new LinkData();
			link.href = getAttributeValue(areaElement, "href");
			link.title = getAttributeValue(areaElement, "title");
			link.altText = getAttributeValue(areaElement, "alt");
			final String coords = getAttributeValue(areaElement, "coords");
			if (coords != null) {
				final String[] ints = coords.split(",");
				if (ints.length == 4) {
					try {
						final int x1 = Integer.valueOf(ints[0]), y1 = Integer.valueOf(ints[1]), x2 = Integer.valueOf(ints[2]), y2 = Integer.valueOf(ints[3]);
						link.rect = new Rectangle(x1, y1, x2 - x1, y2 - y1);
					} catch (final NumberFormatException e) {
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

	private static String getAttributeValue(final String element, final String attributeName) {
		final String prefix = attributeName + "=\"";
		int start = element.indexOf(prefix);
		if (start >= 0) {
			start += prefix.length();
			final String suffix = "\"";
			final int end = element.indexOf(suffix, start);
			if (end > start) {
				return element.substring(start, end);
			}
		}
		return null;
	}
}
