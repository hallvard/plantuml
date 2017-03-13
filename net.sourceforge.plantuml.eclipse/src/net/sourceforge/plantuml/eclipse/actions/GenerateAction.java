package net.sourceforge.plantuml.eclipse.actions;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import net.sourceforge.plantuml.eclipse.listener.CopyAsciiRightClickListener;
import net.sourceforge.plantuml.eclipse.listener.CopyRightClickListener;
import net.sourceforge.plantuml.eclipse.listener.CopySourceRightClickListener;
import net.sourceforge.plantuml.eclipse.listener.ExportRightClickListener;
import net.sourceforge.plantuml.eclipse.listener.PrintRightClickListener;
import net.sourceforge.plantuml.eclipse.model.Diagram;
import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.eclipse.views.SWTImageCanvas;

/**
 * 
 * @author durif_c
 *
 */
public class GenerateAction extends Action {
	/**
	 * diagram to display 
	 */
	private Diagram diagram;

	/**
	 * attribute to keep the last text diagram.
	 */
	private String lastTextDiagram = null;    

	/**
	 * Last image number (for textDiagram with newpage instruction)
	 */
	private int lastImageNumber = -1;

	/**
	 * composite which contains the label of the generated image.
	 */
	protected SWTImageCanvas canvas = null;

	/**
	 * create a GenerateAction
	 * @param canvas
	 */
	public GenerateAction(SWTImageCanvas canvas) {
		super();
		this.canvas = canvas;
		this.diagram = new Diagram();

		addListeners();
	}

	/**
	 * add listeners to the canvas
	 */
	private void addListeners() {
		canvas.addListener(SWT.MenuDetect, new Listener() {
			public void handleEvent(Event event) {
				Menu menu = canvas.getMenu();

				if (menu != null)
					menu.dispose();

				menu = new Menu(canvas.getDisplay().getFocusControl());
				MenuItem item = new MenuItem(menu, SWT.PUSH);
				item.setText(PlantumlConstants.COPY_MENU);
				item.addListener(SWT.Selection, new CopyRightClickListener(
						diagram));

				item = new MenuItem(menu, SWT.PUSH);
				item.setText(PlantumlConstants.COPY_SOURCE_MENU);
				item.addListener(SWT.Selection, new CopySourceRightClickListener(diagram));

				item = new MenuItem(menu, SWT.PUSH);
				item.setText(PlantumlConstants.COPY_ASCII_MENU);
				item.addListener(SWT.Selection, new CopyAsciiRightClickListener(diagram));


				item = new MenuItem(menu, SWT.PUSH);
				item.setText(PlantumlConstants.EXPORT_MENU);
				item.setEnabled(true);
				item.addListener(SWT.Selection,
						new ExportRightClickListener(diagram, canvas));

				item = new MenuItem(menu, SWT.PUSH);
				item.setText(PlantumlConstants.PRINT_MENU);
				item.setEnabled(true);
				item.addListener(SWT.Selection,
						new PrintRightClickListener(diagram, canvas));

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

	/**
	 * Get the current diagram as text
	 * @return the current diagram as text
	 */
	public String getDiagramText() {
		return diagram.getTextDiagram();
	}
	
	/**
	 * display diagram corresponding to the UML script where the cursor is
	 * 
	 * @param cursorPosition -
	 *            position of the cursor at the end of the current line
	 * @param contents
	 * @author durif_c
	 */
	public void treatPlantUmlSelected(int cursorPosition, String contents) {
		String textDiagram = diagram.extractTextDiagram(cursorPosition, contents);
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
}
