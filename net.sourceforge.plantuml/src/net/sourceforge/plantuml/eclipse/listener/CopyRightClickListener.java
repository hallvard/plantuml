package net.sourceforge.plantuml.eclipse.listener;

import java.awt.Toolkit;

import net.sourceforge.plantuml.eclipse.model.Diagram;
import net.sourceforge.plantuml.eclipse.utils.ImageSelection;

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
    public CopyRightClickListener(Diagram diagram) {
        super();
        this.diagram = diagram;
    }

    /**
     * 
     */
    protected void run() {
        final ImageSelection imageSelection = new ImageSelection(diagram.getBufferedImage());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                imageSelection, null);
    }

}
