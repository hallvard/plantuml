package net.sourceforge.plantuml.eclipse.views.actions;

import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;

import net.sourceforge.plantuml.eclipse.utils.Diagram;
import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;

/**Manage the copy action.
 * 
 * @author durif_c
 * 
 */
public class CopyAction extends DiagramAction {

    /**
     * 
     * @param diagram Diagram
     */
    public CopyAction(Display display, Diagram diagram) {
		super(display, diagram);
		setText(PlantumlConstants.COPY_MENU);
	}

    /**
     * 
     */
    public void run() {
        Clipboard clipboard = new Clipboard(display);
        clipboard.setContents(new Object[]{getImage()}, new Transfer[]{ImageTransfer.getInstance()});
        clipboard.dispose();
    }
}
