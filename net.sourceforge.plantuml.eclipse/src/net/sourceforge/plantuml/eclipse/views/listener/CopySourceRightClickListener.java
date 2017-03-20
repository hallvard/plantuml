package net.sourceforge.plantuml.eclipse.views.listener;

import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;

import net.sourceforge.plantuml.eclipse.utils.Diagram;

/**Manage the copy action.
 * 
 * @author durif_c
 * 
 */
public class CopySourceRightClickListener extends RightClickListener {

    /**
     * 
     * @param diagram Diagram
     */
    public CopySourceRightClickListener(Display display, Diagram diagram) {
		super(display, diagram);
	}

	@Override
	public void run() {
        Clipboard clipboard = new Clipboard(display);
        clipboard.setContents(new Object[]{diagram.getTextDiagram()}, new Transfer[]{TextTransfer.getInstance()});
        clipboard.dispose();
    }
}
