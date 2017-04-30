package net.sourceforge.plantuml.eclipse.views;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IActionBars;

import net.sourceforge.plantuml.eclipse.Activator;
import net.sourceforge.plantuml.eclipse.imagecontrol.ILinkSupport;
import net.sourceforge.plantuml.eclipse.imagecontrol.ImageControl;
import net.sourceforge.plantuml.eclipse.imagecontrol.jface.MenuSupport;
import net.sourceforge.plantuml.eclipse.imagecontrol.jface.actions.ZoomAction;
import net.sourceforge.plantuml.eclipse.imagecontrol.jface.actions.ZoomFitAction;
import net.sourceforge.plantuml.eclipse.imagecontrol.jface.actions.ZoomResetAction;
import net.sourceforge.plantuml.eclipse.utils.Diagram;
import net.sourceforge.plantuml.eclipse.utils.ILinkOpener;
import net.sourceforge.plantuml.eclipse.utils.LinkData;
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

public class PlantUmlView extends AbstractDiagramSourceView implements ILinkSupport {

	private ImageControl imageControl;
	private MenuSupport menuSupport;

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
		imageControl = new ImageControl(parent);
		imageControl.addLinkSupport(this);
		menuSupport = new MenuSupport(imageControl);
		diagram = new Diagram();
		addListeners();
		super.createPartControl(parent);
	}

	/**
	 * add listeners to the canvas
	 */
	private void addListeners() {
		addCanvasActions();

		imageControl.addListener(SWT.MouseWheel, new Listener() {
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

		imageControl.addKeyListener(new KeyAdapter() {
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
		Display display = imageControl.getDisplay();
		menuSupport.addMenuAction(new CopyAction(display, diagram));
		menuSupport.addMenuAction(new CopySourceAction(display, diagram));
		menuSupport.addMenuAction(new CopyAsciiAction(display, diagram));
		menuSupport.addMenuAction(new ExportAction(display, diagram, imageControl));
		menuSupport.addMenuAction(new PrintAction(display, diagram, imageControl));
	}

	private IAction zoomInAction, zoomOutAction, fitCanvasAction, showOriginalAction;

	private final float ZOOMIN_RATE = 1.1f; /* zoomin rate */
	private final float ZOOMOUT_RATE = 0.9f; /* zoomout rate */

	@Override
	protected void makeActions() {
		super.makeActions();
		zoomInAction = new ZoomAction(imageControl, ZOOMIN_RATE);
		zoomInAction.setToolTipText(PlantumlConstants.ZOOM_IN_BUTTON);

		zoomOutAction = new ZoomAction(imageControl, ZOOMOUT_RATE);
		zoomOutAction.setToolTipText(PlantumlConstants.ZOOM_OUT_BUTTON);

		fitCanvasAction = new ZoomFitAction(imageControl);
		fitCanvasAction.setToolTipText(PlantumlConstants.FIT_CANVAS_BUTTON);

		showOriginalAction = new ZoomResetAction(imageControl);
		showOriginalAction.setToolTipText(PlantumlConstants.SHOW_ORIGINAL_BUTTON);
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
		imageControl.setFocus();
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
						if (imageData != null && imageControl != null && (! imageControl.isDisposed())) {
							imageControl.getDisplay().asyncExec(new Runnable() {
								public void run() {
									if (! imageControl.isDisposed()) {
										imageControl.loadImage(imageData);
										lastTextDiagram = diagram.getTextDiagram();
										lastImageNumber = diagram.getImageNumber();
									}
								}
							});
						}
					} catch (final Throwable e) {
						if (imageControl != null && (! imageControl.isDisposed())) {
							imageControl.getDisplay().asyncExec(new Runnable() {
								public void run() {
									if (! imageControl.isDisposed()) {
										imageControl.showErrorMessage(e);
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
	
	private Collection<ILinkOpener> linkOpeners = null;
	
	private Collection<ILinkOpener> getLinkOpeners() {
		if (linkOpeners == null) {
			linkOpeners = Arrays.asList(Activator.getDefault().getLinkOpeners());
		}
		return linkOpeners;
	}

	@Override
	public Object getLink(int x, int y) {
		for (LinkData linkData : diagram.getLinks()) {
			if (linkData.rect != null && linkData.rect.contains(x, y)) {
				return linkData.title != null ? linkData.title : linkData.href;
			}
		}
		return null;
	}

	@Override
	public void openLink(Object href) {
		for (LinkData linkData : diagram.getLinks()) {
			// don't use equals, we want the same linkData instances as above
			if (linkData.title == href || linkData.href == href) {
				ILinkOpener linkOpener = findBestLinkOpener(linkData, ILinkOpener.DEFAULT_SUPPORT);
				if (linkOpener != null) {
					linkOpener.openLink(linkData);
					return;
				}
			}
		}
	}

	private ILinkOpener findBestLinkOpener(LinkData link, int minSupport) {
		int bestSupport = ILinkOpener.NO_SUPPORT;
		ILinkOpener best = null;
		for (ILinkOpener linkOpener : getLinkOpeners()) {
			int support = ILinkOpener.NO_SUPPORT;
			try {
				support = linkOpener.supportsLink(link);
			} catch (Exception e) {
			}
			if (support >= bestSupport) {
				bestSupport = support;
				best = linkOpener;
			}
		}
		return (bestSupport >= minSupport ? best : null);
	}

	@Override
	public String getDiagramText() {
		return diagram.getTextDiagram();
	}
	
	private Diagram diagram;

	private String lastTextDiagram = null;    
	private int lastImageNumber = -1;
}
