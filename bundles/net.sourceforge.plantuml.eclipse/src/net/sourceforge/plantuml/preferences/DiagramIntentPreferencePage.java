package net.sourceforge.plantuml.preferences;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

import net.sourceforge.plantuml.eclipse.Activator;

public class DiagramIntentPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	@Override
	public void init(final IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	public static String BASE_PROPERTIES_PATH_PREFERENCE = "basePropertiesPath";

	private Text basePropertiesPathText;

	@Override
	protected Control createContents(final Composite parent) {
		final Composite panel = new Composite(parent, SWT.NONE);
		panel.setLayout(new GridLayout(1, false));

		final Group basePropertiesPathGroup = new Group(panel, SWT.NONE);
		basePropertiesPathGroup.setText("Diagram generation settings folder");
		basePropertiesPathGroup.setLayout(new GridLayout(2, false));

		final IPreferenceStore preferenceStore = getPreferenceStore();

		basePropertiesPathText = new Text(basePropertiesPathGroup, SWT.NONE);
		basePropertiesPathText.setText(preferenceStore.getString(BASE_PROPERTIES_PATH_PREFERENCE));

		final Button basePropertiesPathButton = new Button(basePropertiesPathGroup, SWT.NONE);
		basePropertiesPathButton.setText("Browse...");
		basePropertiesPathButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				final ContainerSelectionDialog dialog = new ContainerSelectionDialog(parent.getShell(), ResourcesPlugin.getWorkspace().getRoot(), false, "Select folder");
				if (dialog.open() == Window.OK) {
					final Object[] selection = dialog.getResult();
					if (selection.length > 0) {
						IPath path = null;
						if (selection[0] instanceof IContainer) {
							path = ((IResource) selection[0]).getFullPath();
						} else if (selection[0] instanceof IPath) {
							path = (IPath) selection[0];
						}
						if (path != null) {
							basePropertiesPathText.setText(path.toString());
						}
					}
				}
			}
		});
		return panel;
	}

	/**
	 * Called when user clicks Restore Defaults
	 */
	@Override
	protected void performDefaults() {
		basePropertiesPathText.setText("");
	}

	/**
	 * Called when user clicks Apply or OK
	 *
	 * @return boolean
	 */
	@Override
	public boolean performOk() {
		// Get the preference store
		final IPreferenceStore preferenceStore = getPreferenceStore();
		// Set the values from the fields
		preferenceStore.setValue(BASE_PROPERTIES_PATH_PREFERENCE, basePropertiesPathText.getText());
		// Return true to allow dialog to close
		return true;
	}

}
