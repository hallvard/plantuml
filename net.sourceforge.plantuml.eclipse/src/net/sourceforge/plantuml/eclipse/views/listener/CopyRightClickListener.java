package net.sourceforge.plantuml.eclipse.views.listener;

import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;

import net.sourceforge.plantuml.eclipse.utils.Diagram;

/**Manage the copy action.
 * 
 * @author durif_c
 * 
 */
public class CopyRightClickListener extends RightClickListener {

    /**
     * 
     * @param diagram Diagram
     */
    public CopyRightClickListener(Display display, Diagram diagram) {
		super(display, diagram);
	}

    /**
     * 
     */
    public void run() {
        Clipboard clipboard = new Clipboard(display);
        clipboard.setContents(new Object[]{diagram.getImageData()}, new Transfer[]{ImageTransfer.getInstance()});
        clipboard.dispose();
    }
}
