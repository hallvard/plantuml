package net.sourceforge.plantuml.eclipse.views.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

import net.sourceforge.plantuml.util.DiagramData;

public abstract class DiagramAction extends Action {

	protected final Display display;
	protected final DiagramData diagram;

	public DiagramAction(final Display display, final DiagramData diagram) {
		this.display = display;
		this.diagram = diagram;
	}

	protected ImageData getImage() {
		return diagram.getImage();
	}
}
