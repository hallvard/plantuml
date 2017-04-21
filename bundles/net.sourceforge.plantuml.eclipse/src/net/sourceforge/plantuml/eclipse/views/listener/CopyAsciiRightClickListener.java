package net.sourceforge.plantuml.eclipse.views.listener;

import java.io.ByteArrayOutputStream;

import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;
import net.sourceforge.plantuml.eclipse.utils.Diagram;

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
	public CopyAsciiRightClickListener(Display display, Diagram diagram) {
		super(display, diagram);
	}

	@Override
	public void run() {
		String s = "empty";
		try {
			String source = diagram.getTextDiagram();
			SourceStringReader sourceStringReader = new SourceStringReader(source);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			sourceStringReader.generateImage(os, new FileFormatOption(FileFormat.ATXT));
			os.close();
			s = new String(os.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			s = e.toString();
		}
        Clipboard clipboard = new Clipboard(display);
        clipboard.setContents(new Object[]{s}, new Transfer[]{TextTransfer.getInstance()});
        clipboard.dispose();
	}
}
