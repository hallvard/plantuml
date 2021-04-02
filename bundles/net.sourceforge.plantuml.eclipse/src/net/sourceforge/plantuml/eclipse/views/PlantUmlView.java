package net.sourceforge.plantuml.eclipse.views;

import java.util.function.Supplier;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import net.sourceforge.plantuml.eclipse.imagecontrol.ILinkSupport;
import net.sourceforge.plantuml.eclipse.imagecontrol.ImageControl;
import net.sourceforge.plantuml.eclipse.imagecontrol.jface.MenuSupport;
import net.sourceforge.plantuml.eclipse.imagecontrol.jface.actions.ZoomAction;
import net.sourceforge.plantuml.eclipse.imagecontrol.jface.actions.ZoomFitAction;
import net.sourceforge.plantuml.eclipse.imagecontrol.jface.actions.ZoomResetAction;
import net.sourceforge.plantuml.eclipse.utils.LinkData;
import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.eclipse.views.actions.CopyAction;
import net.sourceforge.plantuml.eclipse.views.actions.CopyAsciiAction;
import net.sourceforge.plantuml.eclipse.views.actions.CopySourceAction;
import net.sourceforge.plantuml.eclipse.views.actions.ExportAction;
import net.sourceforge.plantuml.eclipse.views.actions.PrintAction;
import net.sourceforge.plantuml.eclipse.views.actions.SaveAction;
import net.sourceforge.plantuml.util.DiagramData;
import net.sourceforge.plantuml.util.DiagramImageData;

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
 * <BR>
 * - Copy the image in the buffer. <BR>
 * - Export the image has a file (to be implement). <BR>
 * - Print the image (to be implement).
 *
 * @author durif_c
 */

public class PlantUmlView extends AbstractPlantUmlView implements ILinkSupport {

	private DiagramImageControl[] imageControls;
	private TabFolder tabFolder = null;
	private MenuSupport menuSupport;

	@Override
	protected void createDiagramControl(final Composite parent) {
		ensureImageControls(0);
		menuSupport = new MenuSupport();
		addCanvasActions();
	}

	private final Listener mouseWheelListener = new Listener() {
		@Override
		public void handleEvent(final Event e) {
			if ((e.stateMask & SWT.CTRL) != 0) {
				if (e.count > 0) {
					zoomInAction.run();
				} else {
					zoomOutAction.run();
				}
			}
		}
	};

	private final KeyAdapter keyListener = new KeyAdapter() {
		@Override
		public void keyPressed(final KeyEvent e) {
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
	};

	private DiagramImageControl createImageControl(final Composite parent) {
		final DiagramImageControl imageControl = new DiagramImageControl(parent);
		imageControl.addLinkSupport(this);
		menuSupport.addImageControl(imageControl);
		imageControl.addListener(SWT.MouseWheel, mouseWheelListener);
		imageControl.addKeyListener(keyListener);
		return imageControl;
	}

	private synchronized void ensureImageControls(final int count) {
		if (imageControls != null && imageControls.length == count) {
			return;
		}
		if (tabFolder != null && count <= 1) {
			tabFolder.dispose();
			tabFolder = null;
		} else if (imageControls != null) {
			// dispose the imageControls that aren't needed
			final int retained = (count > 1 && tabFolder == null ? 0 : count);
			for (int i = imageControls.length - 1; i >= retained; i--) {
				if (tabFolder != null) {
					tabFolder.getItem(i).dispose();
				}
				imageControls[i].dispose();
			}
		} else {
			imageControls = new DiagramImageControl[count];
		}
		// here imageControls exists, may contain disposed items and
		// may be too short or long
		if (imageControls.length != count) {
			final DiagramImageControl[] newImageControls = new DiagramImageControl[count];
			System.arraycopy(imageControls, 0, newImageControls, 0,
					Math.min(imageControls.length, newImageControls.length));
			imageControls = newImageControls;
		}
		if (count > 1 && tabFolder == null) {
			tabFolder = new TabFolder(composite, SWT.BOTTOM);
		}
		// add the extra controls that are needed
		for (int i = 0; i < imageControls.length; i++) {
			if (imageControls[i] == null || imageControls[i].isDisposed()) {
				imageControls[i] = createImageControl(tabFolder != null ? tabFolder : composite);
				if (tabFolder != null) {
					final TabItem tab = new TabItem(tabFolder, SWT.NONE);
					tab.setText(String.valueOf(i + 1));
					tab.setControl(imageControls[i]);
				}
			}
		}
	}

	private void addCanvasActions() {
		final Supplier<DiagramData> diagramDataSupplier = () -> diagramData;
		final Supplier<DiagramImageData> diagramImageDataSupplier = () -> getCurrentImageControl().getDiagramImageData();
		final Display display = composite.getDisplay();
		menuSupport.addMenuAction(new CopyAction(diagramImageDataSupplier, display));
		menuSupport.addMenuAction(new CopySourceAction(diagramDataSupplier, display));
		menuSupport.addMenuAction(new CopyAsciiAction(diagramImageDataSupplier, display));
		final Shell shell = composite.getShell();
		menuSupport.addMenuAction(new SaveAction(diagramImageDataSupplier, shell));
		menuSupport.addMenuAction(new ExportAction(diagramImageDataSupplier, shell));
		menuSupport.addMenuAction(new PrintAction(diagramImageDataSupplier, shell));
	}

	private IAction zoomInAction, zoomOutAction, fitCanvasAction, showOriginalAction;

	private final float ZOOMIN_RATE = 1.1f; //
	private final float ZOOMOUT_RATE = 0.9f; //

	protected DiagramImageControl getCurrentImageControl() {
		int imageNum = 0;
		if (tabFolder != null) {
			imageNum = tabFolder.getSelectionIndex();
		}
		return imageControls.length > imageNum ? imageControls[imageNum] : null;
	}

	@Override
	protected void makeActions() {
		super.makeActions();
		final Supplier<ImageControl> imageControlSupplier = () -> getCurrentImageControl();
		zoomInAction = new ZoomAction(imageControlSupplier, ZOOMIN_RATE);
		zoomInAction.setToolTipText(PlantumlConstants.ZOOM_IN_BUTTON);

		zoomOutAction = new ZoomAction(imageControlSupplier, ZOOMOUT_RATE);
		zoomOutAction.setToolTipText(PlantumlConstants.ZOOM_OUT_BUTTON);

		fitCanvasAction = new ZoomFitAction(imageControlSupplier);
		fitCanvasAction.setToolTipText(PlantumlConstants.FIT_CANVAS_BUTTON);

		showOriginalAction = new ZoomResetAction(imageControlSupplier);
		showOriginalAction.setToolTipText(PlantumlConstants.SHOW_ORIGINAL_BUTTON);
	}

	@Override
	protected void contributeToActionBars() {
		super.contributeToActionBars();
		final IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
		addZoomActions(toolBarManager);
	}

	protected void addZoomActions(final IContributionManager toolBarManager) {
		addActions(toolBarManager, zoomInAction, zoomOutAction, fitCanvasAction, showOriginalAction);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
		final DiagramImageControl imageControl = getCurrentImageControl();
		(imageControl != null ? imageControl : composite).setFocus();
	}

	//

	@Override
	protected void updateDiagram(final DiagramData diagramData, final IProgressMonitor monitor) {
		// load all images before updating controls
		final DiagramImageData[] diagramImageDatas = new DiagramImageData[diagramData.getImageCount()];
		for (int imageNum = 0; imageNum < diagramImageDatas.length; imageNum++) {
			if (monitor != null && monitor.isCanceled()) {
				return;
			}
			final DiagramImageData diagramImageData = new DiagramImageData(diagramData, imageNum, null);
			diagramImageData.getImage();
			diagramImageDatas[imageNum] = diagramImageData;
		}
		if (monitor != null && monitor.isCanceled()) {
			return;
		}
		setDiagramViewStatus(ViewStatus.DIAGRAM_VIEW_DATA, diagramImageDatas);
		asyncExec(() -> {
			// check if the view still matches the model
			if (shouldUpdateView(diagramData)) {
				ensureImageControls(diagramImageDatas.length);
				for (int imageNum = 0; imageNum < imageControls.length; imageNum++) {
					if (imageControls[imageNum] != null && (! imageControls[imageNum].isDisposed())) {
						imageControls[imageNum].updateDiagramImage(diagramImageDatas[imageNum]);
					}
				}
				setDiagramViewStatus(ViewStatus.DIAGRAM_VIEW, diagramImageDatas);
			}
		});
		asyncExec(layoutComposite);
	}

	@Override
	public Object getLink(final int x, final int y) {
		final DiagramImageControl imageControl = getCurrentImageControl();
		if (imageControl != null) {
			for (final LinkData linkData : imageControl.getDiagramImageData().getLinks()) {
				if (linkData.rect != null && linkData.rect.contains(x, y)) {
					return linkData.title != null ? linkData.title : linkData.href;
				}
			}
		}
		return null;
	}

	@Override
	public void openLink(final Object href) {
		final DiagramImageControl imageControl = getCurrentImageControl();
		if (imageControl != null) {
			for (final LinkData linkData : imageControl.getDiagramImageData().getLinks()) {
				// don't use equals, we want the same linkData instances as above
				if (linkData.title == href || linkData.href == href) {
					if (openLink(linkData)) {
						return;
					}
				}
			}
		}
	}
}
