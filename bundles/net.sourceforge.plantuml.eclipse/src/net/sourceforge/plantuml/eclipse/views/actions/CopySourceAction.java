package net.sourceforge.plantuml.eclipse.views.actions;

import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;

import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.util.DiagramData;

/**Manage the copy action.
 * 
 * @author durif_c
 * 
 */
public class CopySourceAction extends DiagramAction {

    /**
     * 
     * @param diagram Diagram
     */
    public CopySourceAction(Display display, DiagramData diagram) {
		super(display, diagram);
		setText(PlantumlConstants.COPY_SOURCE_MENU);
	}

	@Override
	public void run() {
        Clipboard clipboard = new Clipboard(display);
        clipboard.setContents(new Object[]{diagram.getTextDiagram()}, new Transfer[]{TextTransfer.getInstance()});
        clipboard.dispose();
    }
}
