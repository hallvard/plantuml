package net.sourceforge.plantuml.eclipse.imagecontrol.jface;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class MenuSupport {

	public MenuSupport(Control control) {
		addMenuDetectListener(control);
	}

	//

	private Collection<Action> menuActions = new ArrayList<Action>();

	public void addMenuAction(Action action) {
		menuActions.add(action);
	}

	public void removeMenuAction(Action action) {
		menuActions.remove(action);
	}
	
	protected void addMenuDetectListener(final Control control) {
		control.addListener(SWT.MenuDetect, new Listener() {
			public void handleEvent(Event event) {
				Menu menu = control.getMenu();
				if (menu != null) {
					menu.dispose();
				}
				menu = new Menu(control.getDisplay().getFocusControl());
				for (final Action action : menuActions) {
					MenuItem item = new MenuItem(menu, SWT.PUSH);
					item.setText(action.getText());
					item.addListener(SWT.Selection, new Listener() {
						@Override
						public void handleEvent(Event event) {
							action.run();
						}
					});					
				}
				control.setMenu(menu);
			}
		});
	}
}
