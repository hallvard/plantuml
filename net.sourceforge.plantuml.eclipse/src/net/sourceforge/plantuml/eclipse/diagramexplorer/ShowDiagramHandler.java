package net.sourceforge.plantuml.eclipse.diagramexplorer;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

public class ShowDiagramHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {

		IStructuredSelection selection = (IStructuredSelection) HandlerUtil
				.getActiveMenuSelection(event);
		System.out.println(selection);
		Object o = selection.getFirstElement();
		System.err.println(o);	
		/*
		Window window = new ApplicationWindow(null);						
		window.open();
		
		Shell shell = window.getShell();		
		shell.setText("Diagrams explorer");

		RowLayout layout = new RowLayout();
		shell.setLayout(layout);

		Label l1 = new Label(shell, SWT.LEFT);
		l1.setText("Pr�nom : ");
		new Text(shell, SWT.BORDER);
		Button b1 = new Button(shell, SWT.PUSH);
		b1.setText("Valider");

		shell.pack();*/

		Display display = new Display ();
		Shell shell = new Shell (display);
		shell.setText ("Shell");
		shell.setSize (200, 200);
		shell.open ();
		Shell dialog = new Shell (shell);
		dialog.setText ("Dialog");
		dialog.setSize (200, 200);
		dialog.open ();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose();
		return null;

	}
}
