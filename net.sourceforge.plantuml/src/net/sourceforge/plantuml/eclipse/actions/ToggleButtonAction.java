package net.sourceforge.plantuml.eclipse.actions;

import net.sourceforge.plantuml.eclipse.utils.PlantUmlUtils;
import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.eclipse.views.PlantUmlView;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;

/**
 * This action allows to start/stop the generation of diagrams
 * @author pecoil_a
 *
 */
public class ToggleButtonAction extends Action {

	Display display;
	PlantUmlView view;
	
	public ToggleButtonAction(PlantUmlView view, Display display) {
		this.display = display;
		this.view = view;
		setToolTipText(PlantumlConstants.TOGGLE_GENERATION_BUTTON);
		setImageDescriptor(PlantUmlUtils.getImageDescriptor(display, "/icons/feu_vert.gif"));
		setChecked(true);
	}
	
	public void run() {
		if (this.isChecked()) {
//			view.setListener(true);
			setImageDescriptor(PlantUmlUtils.getImageDescriptor(display, "/icons/feu_vert.gif"));
		} else {
//			view.setListener(false);
			setImageDescriptor(PlantUmlUtils.getImageDescriptor(display, "/icons/feu_rouge.gif"));
		}
	}
}
