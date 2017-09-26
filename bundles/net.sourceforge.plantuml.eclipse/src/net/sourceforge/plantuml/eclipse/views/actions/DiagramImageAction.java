package net.sourceforge.plantuml.eclipse.views.actions;

import org.eclipse.core.runtime.IPath;
import org.eclipse.swt.graphics.ImageData;

import net.sourceforge.plantuml.eclipse.imagecontrol.jface.actions.ImageControlAction;
import net.sourceforge.plantuml.eclipse.utils.Diagram;
import net.sourceforge.plantuml.eclipse.views.DiagramImageControl;

public abstract class DiagramImageAction extends ImageControlAction {

	protected final Diagram diagram;
	
	public DiagramImageAction(DiagramImageControl control, Diagram diagram) {
		super(control);
		this.diagram = diagram;
	}
	
    public DiagramImageAction(DiagramImageControl control) {
    	this(control, null);
	}
    
    protected IPath getSourcePath() {
    	return ((DiagramImageControl) getControl()).getSourcePath();
    }

    protected ImageData getImage() {
    	return ((DiagramImageControl) getControl()).getImageData();
    }
}
