package net.sourceforge.plantuml.eclipse.views;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
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
		createImageControls(0);
		menuSupport = new MenuSupport();
		addListeners();
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

	private void createImageControls(final int count) {
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
		// here imageControls exists, may contain disposed items and may be too short or
		// long
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

	/**
	 * add listeners to the canvas
	 */
	private void addListeners() {
		addCanvasActions();
	}

	private void addCanvasActions() {
		final Display display = composite.getDisplay();
		menuSupport.addMenuAction(new CopyAction(null) {
			@Override
			public ImageControl getControl() {
				return getCurrentImageControl();
			};
		});
		menuSupport.addMenuAction(new CopySourceAction(display, diagramData));
		menuSupport.addMenuAction(new CopyAsciiAction(display, diagramData));
		menuSupport.addMenuAction(new SaveAction(null, diagramData) {
			@Override
			public ImageControl getControl() {
				return getCurrentImageControl();
			};
		});
		menuSupport.addMenuAction(new ExportAction(null, diagramData) {
			@Override
			public ImageControl getControl() {
				return getCurrentImageControl();
			};
		});
		menuSupport.addMenuAction(new PrintAction(null) {
			@Override
			public ImageControl getControl() {
				return getCurrentImageControl();
			};
		});
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
		zoomInAction = new ZoomAction(null, ZOOMIN_RATE) {
			@Override
			public ImageControl getControl() {
				return getCurrentImageControl();
			};
		};
		zoomInAction.setToolTipText(PlantumlConstants.ZOOM_IN_BUTTON);

		zoomOutAction = new ZoomAction(null, ZOOMOUT_RATE) {
			@Override
			public ImageControl getControl() {
				return getCurrentImageControl();
			};
		};
		zoomOutAction.setToolTipText(PlantumlConstants.ZOOM_OUT_BUTTON);

		fitCanvasAction = new ZoomFitAction(null) {
			@Override
			public ImageControl getControl() {
				return getCurrentImageControl();
			};
		};
		fitCanvasAction.setToolTipText(PlantumlConstants.FIT_CANVAS_BUTTON);

		showOriginalAction = new ZoomResetAction(null) {
			@Override
			public ImageControl getControl() {
				return getCurrentImageControl();
			};
		};
		showOriginalAction.setToolTipText(PlantumlConstants.SHOW_ORIGINAL_BUTTON);
	}

	@Override
	protected void contributeToActionBars() {
		final IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
		addZoomActions(toolBarManager);
		addViewActions(toolBarManager);

		final IMenuManager menu = getViewSite().getActionBars().getMenuManager();
		final MenuManager editorSelectionActionMenu = new MenuManager("Diagrams");
		editorSelectionActionMenu.add(new Action() {}); // will be removed, needed for the submenu to actually show
		editorSelectionActionMenu.setRemoveAllWhenShown(true);
		editorSelectionActionMenu.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(final IMenuManager menu) {
				for (final ActionContributionItem actionContributionItem : getEditorSelectionActions(menu)) {
					menu.add(actionContributionItem);
				}
			}
		});
		menu.add(editorSelectionActionMenu);
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
	protected void updateDiagram(final IProgressMonitor monitor) {
		for (int i = 0; i < imageControls.length; i++) {
			if (imageControls.length > i && imageControls[i] != null && (!imageControls[i].isDisposed())) {
				imageControls[i].updateDiagramImage(diagramData, i);
			}
		}
	}

	@Override
	protected void updateDiagram() {
		createImageControls(diagramData.getImageCount());
		super.updateDiagram();
	}

	@Override
	public Object getLink(final int x, final int y) {
		final DiagramImageControl imageControl = getCurrentImageControl();
		if (imageControl != null) {
			for (final LinkData linkData : imageControl.getLinks()) {
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
			for (final LinkData linkData : getCurrentImageControl().getLinks()) {
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
