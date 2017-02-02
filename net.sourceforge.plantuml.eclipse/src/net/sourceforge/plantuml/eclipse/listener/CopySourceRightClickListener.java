package net.sourceforge.plantuml.eclipse.listener;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import net.sourceforge.plantuml.eclipse.model.Diagram;

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
    public CopySourceRightClickListener(Diagram diagram) {
        super();
        this.diagram = diagram;
    }

    /**
     * 
     */
    protected void run() {
        // final ImageSelection imageSelection = new ImageSelection(diagram.getBufferedImage());
//    	String s = StringUtils.uncommentSource(diagram.getTextDiagram());
    	String s = diagram.getTextDiagram();
    	final StringSelection sel = new StringSelection(s);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel, null);
    }

}
