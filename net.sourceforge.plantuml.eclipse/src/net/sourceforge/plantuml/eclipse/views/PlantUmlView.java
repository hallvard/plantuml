package net.sourceforge.plantuml.eclipse.views;

import net.sourceforge.plantuml.eclipse.actions.GenerateAction;
import net.sourceforge.plantuml.eclipse.actions.ToggleButtonAction;
import net.sourceforge.plantuml.eclipse.utils.PlantUmlUtils;
import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionBars;

/**
 * 
 * The view permit to generate image diagram of UML modelisation.
 * <P>
 * To launch this view, go to Windows > Show View > Other... > PlantUml >
 * PlantUml. <BR>
 * To use it, it's very easy. you put your text describing the diagram in your
 * file (*.java, *.txt, ...) and you click in the text and automatically, the
 * UML diagram will be generated in the view.
 * <P>
 * To use the image you have the following functionalities on the right-click :
 * <BR>- Copy the image in the buffer. <BR>- Export the image has a file (to
 * be implement). <BR>- Print the image (to be implement).
 * 
 * @author durif_c
 */

public class PlantUmlView extends AbstractDiagramSourceView {

	private SWTImageCanvas canvas;
	/**
	 * The action which manage the image generation.
	 */
	private GenerateAction generateAction;

	private Action zoomInAction;
	private Action zoomOutAction;
	private Action fitCanvasAction;
	private Action showOriginalAction;
	private Action toggleAction;

	/**
	 * The default constructor.
	 */
	public PlantUmlView() {
		super();
	}

	/**
	 * Method in which we construct the view and it contents
	 * 
	 * @author durif_c
	 */
	public void createPartControl(Composite parent) {
		// Display of the view.
		canvas = new SWTImageCanvas(parent);

		// Manage actions
		makeActions(parent.getDisplay());

		// add PlantUmlView in the listener list of the active page to get event
		// of updates.
		contributeToActionBars();

		super.createPartControl(parent);
	}

	/**
	 * Manage the actions.
	 * 
	 * @author durif_c
	 * @param display
	 */
	private void makeActions(Display display) {
		generateAction = new GenerateAction(canvas);

		zoomInAction = new Action() {
			public void run() {
				canvas.zoomIn();
			}
		};
		zoomInAction.setToolTipText(PlantumlConstants.ZOOM_IN_BUTTON);
		zoomInAction.setImageDescriptor(PlantUmlUtils.getImageDescriptor(
				display, "/icons/ZoomIn16.gif"));

		zoomOutAction = new Action() {
			public void run() {
				canvas.zoomOut();
			}
		};
		zoomOutAction.setToolTipText(PlantumlConstants.ZOOM_OUT_BUTTON);
		zoomOutAction.setImageDescriptor(PlantUmlUtils.getImageDescriptor(
				display, "/icons/ZoomOut16.gif"));

		fitCanvasAction = new Action() {
			public void run() {
				canvas.fitCanvas();
			}
		};
		fitCanvasAction.setToolTipText(PlantumlConstants.FIT_CANVAS_BUTTON);
		fitCanvasAction.setImageDescriptor(PlantUmlUtils.getImageDescriptor(
				display, "/icons/Fit16.gif"));

		showOriginalAction = new Action() {
			public void run() {
				canvas.showOriginal();
			}
		};
		showOriginalAction
				.setToolTipText(PlantumlConstants.SHOW_ORIGINAL_BUTTON);
		showOriginalAction.setImageDescriptor(PlantUmlUtils.getImageDescriptor(
				display, "/icons/Original16.gif"));

		// action to start or stop the generation of the actual diagram
		toggleAction = new ToggleButtonAction(this, display);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(toggleAction);
		manager.add(new Separator());
		manager.add(zoomInAction);
		manager.add(zoomOutAction);
		manager.add(fitCanvasAction);
		manager.add(showOriginalAction);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		canvas.setFocus();
	}
	
	@Override
	protected void updateDiagramText(final String text) {
		if (toggleAction != null && toggleAction.isChecked()) {
			generateAction.treatPlantUmlSelected(-1, text);
		}
	}
}
