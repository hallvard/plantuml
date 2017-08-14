package net.sourceforge.plantuml.eclipse.views.actions;

import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.Transfer;

import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.eclipse.views.DiagramImageControl;

/**Manage the copy action.
 * 
 * @author durif_c
 * 
 */
public class CopyAction extends DiagramImageAction {

    /**
     * 
     * @param diagram Diagram
     */
    public CopyAction(DiagramImageControl control) {
		super(control);
		setText(PlantumlConstants.COPY_MENU);
	}

    /**
     * 
     */
    public void run() {
        Clipboard clipboard = new Clipboard(getControl().getDisplay());
        clipboard.setContents(new Object[]{getImage()}, new Transfer[]{ImageTransfer.getInstance()});
        clipboard.dispose();
    }
}
