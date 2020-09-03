package net.sourceforge.plantuml.eclipse.views.actions;

import java.io.ByteArrayOutputStream;

import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;
import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.util.DiagramData;

/**
 * Manage the copy action.
 * 
 * @author durif_c
 * 
 */
public class CopyAsciiAction extends DiagramAction {

	/**
	 * 
	 * @param diagram
	 *            Diagram
	 */
	public CopyAsciiAction(Display display, DiagramData diagram) {
		super(display, diagram);
		setText(PlantumlConstants.COPY_ASCII_MENU);
	}

	@Override
	public void run() {
		String s = "empty";
		try {
			String source = diagram.getTextDiagram();
			SourceStringReader sourceStringReader = new SourceStringReader(source);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			sourceStringReader.outputImage(os, new FileFormatOption(FileFormat.ATXT));
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
