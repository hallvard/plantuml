package net.sourceforge.plantuml.eclipse.listener;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.ByteArrayOutputStream;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;
import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.eclipse.model.Diagram;

/**
 * Manage the copy action.
 * 
 * @author durif_c
 * 
 */
public class CopyAsciiRightClickListener extends RightClickListener {

	/**
	 * 
	 * @param diagram
	 *            Diagram
	 */
	public CopyAsciiRightClickListener(Diagram diagram) {
		super();
		this.diagram = diagram;
	}

	/**
	 * 
	 */
	protected void run() {
		String s = "empty";
		try {
			final String source = StringUtils.uncommentSource(diagram.getTextDiagram());
			final SourceStringReader sourceStringReader = new SourceStringReader(source);
			final ByteArrayOutputStream os = new ByteArrayOutputStream();
			sourceStringReader.generateImage(os, new FileFormatOption(FileFormat.ATXT));
			os.close();
			s = new String(os.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			s = e.toString();
		}
		final StringSelection sel = new StringSelection(s);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel, null);
	}

}
