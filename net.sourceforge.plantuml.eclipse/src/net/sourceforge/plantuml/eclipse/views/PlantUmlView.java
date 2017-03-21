package net.sourceforge.plantuml.eclipse.views;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
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
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IActionBars;

import net.sourceforge.plantuml.eclipse.utils.Diagram;
import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.eclipse.views.listener.CopyAsciiRightClickListener;
import net.sourceforge.plantuml.eclipse.views.listener.CopyRightClickListener;
import net.sourceforge.plantuml.eclipse.views.listener.CopySourceRightClickListener;
import net.sourceforge.plantuml.eclipse.views.listener.ExportRightClickListener;
import net.sourceforge.plantuml.eclipse.views.listener.PrintRightClickListener;

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
		diagram = new Diagram();
		addListeners();

		super.createPartControl(parent);
	}

	/**
	 * add listeners to the canvas
	 */
	private void addListeners() {
		canvas.addListener(SWT.MenuDetect, new Listener() {
			public void handleEvent(Event event) {
				Menu menu = canvas.getMenu();
				if (menu != null) {
					menu.dispose();
				}
				menu = new Menu(canvas.getDisplay().getFocusControl());
				MenuItem item = new MenuItem(menu, SWT.PUSH);
				item.setText(PlantumlConstants.COPY_MENU);
				item.addListener(SWT.Selection, new CopyRightClickListener(canvas.getDisplay(), diagram));

				item = new MenuItem(menu, SWT.PUSH);
				item.setText(PlantumlConstants.COPY_SOURCE_MENU);
				item.addListener(SWT.Selection, new CopySourceRightClickListener(canvas.getDisplay(), diagram));

				item = new MenuItem(menu, SWT.PUSH);
				item.setText(PlantumlConstants.COPY_ASCII_MENU);
				item.addListener(SWT.Selection, new CopyAsciiRightClickListener(canvas.getDisplay(), diagram));

				item = new MenuItem(menu, SWT.PUSH);
				item.setText(PlantumlConstants.EXPORT_MENU);
				item.setEnabled(true);
				item.addListener(SWT.Selection,
						new ExportRightClickListener(canvas.getDisplay(), diagram, canvas));

				item = new MenuItem(menu, SWT.PUSH);
				item.setText(PlantumlConstants.PRINT_MENU);
				item.setEnabled(true);
				item.addListener(SWT.Selection,
						new PrintRightClickListener(canvas.getDisplay(), diagram, canvas));
				canvas.setMenu(menu);
			}
		});

		canvas.addListener(SWT.MouseWheel, new Listener() {
			public void handleEvent(Event e) {
				if ((e.stateMask & SWT.CTRL) != 0) {
					if (e.count > 0)
						canvas.zoomIn();
					else
						canvas.zoomOut();
				}
			}
		});

		canvas.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.character) {
				case '+':
					canvas.zoomIn();
					break;
				case '-':
					canvas.zoomOut();
					break;
				default:
					break;
				}
			}
		});
	}

	private IAction zoomInAction, zoomOutAction;
	private IAction fitCanvasAction;
	private IAction showOriginalAction;

	@Override
	protected void makeActions() {
		super.makeActions();
		zoomInAction = new Action() {
			public void run() {
				canvas.zoomIn();
			}
		};
		zoomInAction.setToolTipText(PlantumlConstants.ZOOM_IN_BUTTON);
		zoomInAction.setImageDescriptor(ImageDescriptor.createFromFile(PlantumlConstants.class, "/icons/ZoomIn16.gif"));

		zoomOutAction = new Action() {
			public void run() {
				canvas.zoomOut();
			}
		};
		zoomOutAction.setToolTipText(PlantumlConstants.ZOOM_OUT_BUTTON);
		zoomOutAction.setImageDescriptor(ImageDescriptor.createFromFile(PlantumlConstants.class, "/icons/ZoomOut16.gif"));

		fitCanvasAction = new Action() {
			public void run() {
				canvas.fitCanvas();
			}
		};
		fitCanvasAction.setToolTipText(PlantumlConstants.FIT_CANVAS_BUTTON);
		fitCanvasAction.setImageDescriptor(ImageDescriptor.createFromFile(PlantumlConstants.class, "/icons/Fit16.gif"));

		showOriginalAction = new Action() {
			public void run() {
				canvas.showOriginal();
			}
		};
		showOriginalAction
				.setToolTipText(PlantumlConstants.SHOW_ORIGINAL_BUTTON);
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
