package net.sourceforge.plantuml.eclipse.views.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;

import net.sourceforge.plantuml.eclipse.utils.Diagram;

public abstract class DiagramAction extends Action {

	protected final Display display;
    protected final Diagram diagram;

    public DiagramAction(Display display, Diagram diagram) {
		this.display = display;
		this.diagram = diagram;
	}
}
