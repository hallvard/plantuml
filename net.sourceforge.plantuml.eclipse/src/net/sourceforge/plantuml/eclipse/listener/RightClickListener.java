package net.sourceforge.plantuml.eclipse.listener;

import net.sourceforge.plantuml.eclipse.model.Diagram;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**Right click listener manager.
 * 
 * @author durif_c
 * 
 */
public abstract class RightClickListener implements Listener {

    protected Diagram diagram;

    
    /**
     * @param e Event
     */
    public void handleEvent(Event e) {
        run();
    }

    /**
     * 
     */
    protected abstract void run();

}
