package net.sourceforge.plantuml.eclipse.svg;

import org.eclipse.core.resources.IFile;
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
import org.eclipse.ui.dialogs.ResourceListSelectionDialog;
import org.eclipse.ui.dialogs.SelectionDialog;

import net.sourceforge.plantuml.eclipse.Activator;

public class SvgPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	@Override
	public void init(final IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	private static String TEMPLATE_URL_DEFAULT = "platform:/plugin/net.sourceforge.plantuml.svg/templates/interactive-svg-template.mustache";

	public static String TEMPLATE_URL_PREFERENCE = "templateUrl";

	private Text templateUrlText;

	public static String getTemplateUrlPreference(final IPreferenceStore preferenceStore) {
		final String templateUrlPreference = preferenceStore.getString(TEMPLATE_URL_PREFERENCE);
		return (templateUrlPreference != null && templateUrlPreference.trim().length() > 0 ? templateUrlPreference.trim() : TEMPLATE_URL_DEFAULT);
	}

	@Override
	protected Control createContents(final Composite parent) {
		final Composite panel = new Composite(parent, SWT.NONE);
		panel.setLayout(new GridLayout(1, false));

		final Group templateUrlGroup = new Group(panel, SWT.NONE);
		templateUrlGroup.setText("Svg template settings");
		templateUrlGroup.setLayout(new GridLayout(2, false));

		final IPreferenceStore preferenceStore = getPreferenceStore();
		final String templateUrlPreference = getTemplateUrlPreference(preferenceStore);

		templateUrlText = new Text(templateUrlGroup, SWT.NONE);
		templateUrlText.setText(templateUrlPreference);

		final Button templateUrlButton = new Button(templateUrlGroup, SWT.NONE);
		templateUrlButton.setText("Browse...");
		templateUrlButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				final SelectionDialog dialog = new ResourceListSelectionDialog(parent.getShell(), ResourcesPlugin.getWorkspace().getRoot(), IResource.FILE);
				dialog.setTitle("Select template");
				if (dialog.open() == Window.OK) {
					final Object[] selection = dialog.getResult();
					if (selection.length > 0) {
						IPath path = null;
						if (selection[0] instanceof IFile) {
							path = ((IResource) selection[0]).getFullPath();
						} else if (selection[0] instanceof IPath) {
							path = (IPath) selection[0];
						}
						if (path != null) {
							templateUrlText.setText("platform:/resource" + path.toString());
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
		// Reset the fields to the defaults
		templateUrlText.setText(TEMPLATE_URL_DEFAULT);
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
		if (templateUrlText != null)
			preferenceStore.setValue(TEMPLATE_URL_PREFERENCE,
					templateUrlText.getText());

		// Return true to allow dialog to close
		return true;
	}
}
