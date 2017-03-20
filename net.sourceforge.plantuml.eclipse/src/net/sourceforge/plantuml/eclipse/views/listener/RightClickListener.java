package net.sourceforge.plantuml.eclipse.views.listener;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import net.sourceforge.plantuml.eclipse.utils.Diagram;

/**Right click listener manager.
 * 
 * @author durif_c
 * 
 */
public abstract class RightClickListener implements Listener, Runnable {

	protected final Display display;
    protected final Diagram diagram;

    public RightClickListener(Display display, Diagram diagram) {
		this.display = display;
		this.diagram = diagram;
	}

    public void handleEvent(Event e) {
        run();
    }
}
