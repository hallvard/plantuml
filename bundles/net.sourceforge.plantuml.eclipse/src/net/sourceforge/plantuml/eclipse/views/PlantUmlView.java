package net.sourceforge.plantuml.eclipse.views;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IActionBars;

import net.sourceforge.plantuml.eclipse.imagecontrol.ImageControl;
import net.sourceforge.plantuml.eclipse.imagecontrol.ZoomAction;
import net.sourceforge.plantuml.eclipse.imagecontrol.ZoomFitAction;
import net.sourceforge.plantuml.eclipse.imagecontrol.ZoomResetAction;
import net.sourceforge.plantuml.eclipse.utils.Diagram;
import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.eclipse.views.actions.CopyAction;
import net.sourceforge.plantuml.eclipse.views.actions.CopyAsciiAction;
import net.sourceforge.plantuml.eclipse.views.actions.CopySourceAction;
import net.sourceforge.plantuml.eclipse.views.actions.ExportAction;
import net.sourceforge.plantuml.eclipse.views.actions.PrintAction;

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

	private ImageControl canvas;

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
		canvas = new ImageControl(parent);
		diagram = new Diagram();
		addListeners();

		super.createPartControl(parent);
	}

	/**
	 * add listeners to the canvas
	 */
	private void addListeners() {
		addCanvasActions();

		canvas.addListener(SWT.MouseWheel, new Listener() {
			public void handleEvent(Event e) {
				if ((e.stateMask & SWT.CTRL) != 0) {
					if (e.count > 0) {
						zoomInAction.run();
					} else {
						zoomOutAction.run();
					}
				}
			}
		});

		canvas.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.character) {
				case '+':
					zoomInAction.run();
					break;
				case '-':
					zoomOutAction.run();
					break;
				default:
					break;
				}
			}
		});
	}

	private void addCanvasActions() {
		canvas.addMenuAction(new CopyAction(canvas.getDisplay(), diagram));
		canvas.addMenuAction(new CopySourceAction(canvas.getDisplay(), diagram));
		canvas.addMenuAction(new CopyAsciiAction(canvas.getDisplay(), diagram));
		canvas.addMenuAction(new ExportAction(canvas.getDisplay(), diagram, canvas));
		canvas.addMenuAction(new PrintAction(canvas.getDisplay(), diagram, canvas));
	}

	private IAction zoomInAction, zoomOutAction;
	private IAction fitCanvasAction;
	private IAction showOriginalAction;

	private final float ZOOMIN_RATE = 1.1f; /* zoomin rate */
	private final float ZOOMOUT_RATE = 0.9f; /* zoomout rate */

	@Override
	protected void makeActions() {
		super.makeActions();
		zoomInAction = new ZoomAction(canvas, ZOOMIN_RATE);
		zoomInAction.setToolTipText(PlantumlConstants.ZOOM_IN_BUTTON);
		zoomInAction.setImageDescriptor(ImageDescriptor.createFromFile(PlantumlConstants.class, "/icons/ZoomIn16.gif"));

		zoomOutAction = new ZoomAction(canvas, ZOOMOUT_RATE);
		zoomOutAction.setToolTipText(PlantumlConstants.ZOOM_OUT_BUTTON);
		zoomOutAction.setImageDescriptor(ImageDescriptor.createFromFile(PlantumlConstants.class, "/icons/ZoomOut16.gif"));

		fitCanvasAction = new ZoomFitAction(canvas);
		fitCanvasAction.setToolTipText(PlantumlConstants.FIT_CANVAS_BUTTON);
		fitCanvasAction.setImageDescriptor(ImageDescriptor.createFromFile(PlantumlConstants.class, "/icons/Fit16.gif"));

		showOriginalAction = new ZoomResetAction(canvas);
		showOriginalAction.setToolTipText(PlantumlConstants.SHOW_ORIGINAL_BUTTON);
		showOriginalAction.setImageDescriptor(ImageDescriptor.createFromFile(PlantumlConstants.class, "/icons/Original16.gif"));
	}

	@Override
	protected void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		IToolBarManager toolBarManager = bars.getToolBarManager();
		toolBarManager.add(zoomInAction);
		toolBarManager.add(zoomOutAction);
		toolBarManager.add(fitCanvasAction);
		toolBarManager.add(showOriginalAction);
		toolBarManager.add(new Separator());
		toolBarManager.add(spawnAction);
		toolBarManager.add(pinToAction);
		toolBarManager.add(toggleAction);
		super.contributeToActionBars();
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		canvas.setFocus();
	}
	
	//
	
	@Override
	protected void updateDiagramText(final String text) {
		String textDiagram = diagram.extractTextDiagram(text);
		if (textDiagram != null && (! textDiagram.equals(lastTextDiagram) || lastImageNumber != diagram.getImageNumber())) {
			final IPath path = Diagram.getActiveEditorPath();
			Job job = new Job("Generate diagram") {
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					try {
						final ImageData imageData = diagram.getImage(path);
						if (imageData != null && canvas != null && (! canvas.isDisposed())) {
							canvas.getDisplay().asyncExec(new Runnable() {
								public void run() {
									if (! canvas.isDisposed()) {
										canvas.loadImage(imageData);
										canvas.setLinks(diagram.getLinks());
										lastTextDiagram = diagram.getTextDiagram();
										lastImageNumber = diagram.getImageNumber();
									}
								}
							});
						}
					} catch (final Throwable e) {
						if (canvas != null && (! canvas.isDisposed())) {
							canvas.getDisplay().asyncExec(new Runnable() {
								public void run() {
									if (! canvas.isDisposed()) {
										canvas.showErrorMessage(e);
									}
								}
							});
						}
					}
					return Status.OK_STATUS;
				}
			};
			job.schedule();
		}
	}
	
	@Override
	public String getDiagramText() {
		return diagram.getTextDiagram();
	}
	
	private Diagram diagram;

	private String lastTextDiagram = null;    
	private int lastImageNumber = -1;
}
