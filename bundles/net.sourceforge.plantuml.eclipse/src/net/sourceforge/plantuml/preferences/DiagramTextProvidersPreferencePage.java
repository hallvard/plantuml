package net.sourceforge.plantuml.preferences;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import net.sourceforge.plantuml.eclipse.Activator;
import net.sourceforge.plantuml.eclipse.utils.DiagramTextProvider;

public class DiagramTextProvidersPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	@Override
	public void init(final IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	final Map<DiagramTextProvider, Button> diagramTextProviderButtons = new HashMap<>();

	@Override
	protected Control createContents(final Composite parent) {
		final Composite panel = new Composite(parent, SWT.NONE);
		panel.setLayout(new GridLayout(1, false));

		final Group diagramTextProvidersGroup = new Group(panel, SWT.NONE);
		diagramTextProvidersGroup.setText("Diagram provider enablement");
		diagramTextProvidersGroup.setLayout(new GridLayout(1, false));

		final IPreferenceStore preferenceStore = getPreferenceStore();
		for (final DiagramTextProvider diagramTextProvider : Activator.getDefault().getDiagramTextProviders(null)) {
			final String[] diagramTextProviderInfo = getDiagramTextProviderInfo(diagramTextProvider);
			final Button diagramTextProviderButton = new Button(diagramTextProvidersGroup, SWT.CHECK);
			diagramTextProviderButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
			diagramTextProviderButton.setText(diagramTextProviderInfo[1]);
			diagramTextProviderButton.setSelection(! preferenceStore.getBoolean(diagramTextProviderInfo[2]));
			diagramTextProviderButtons.put(diagramTextProvider, diagramTextProviderButton);
		}
		return panel;
	}

	public static String getDiagramTextProviderDisablementKey(final String id) {
		return id + ".disablement";
	}

	private static String[] getDiagramTextProviderInfo(final DiagramTextProvider diagramTextProvider) {
		final String id = Activator.getDefault().getDiagramTextProviderId(diagramTextProvider);
		String label = Activator.getDefault().getDiagramTextProviderLabel(diagramTextProvider);
		if (label == null) {
			label = id;
		}
		final String key = getDiagramTextProviderDisablementKey(id);
		return new String[] { id, label, key };
	}

	/**
	 * Called when user clicks Restore Defaults
	 */
	@Override
	protected void performDefaults() {
		for (final Button diagramTextProviderButton : diagramTextProviderButtons.values()) {
			diagramTextProviderButton.setSelection(true);
		}
	}

	private boolean hasChanged() {
		final IPreferenceStore preferenceStore = getPreferenceStore();
		for (final DiagramTextProvider diagramTextProvider : Activator.getDefault().getDiagramTextProviders(null)) {
			final String[] diagramTextProviderInfo = getDiagramTextProviderInfo(diagramTextProvider);
			final Button button = diagramTextProviderButtons.get(diagramTextProvider);
			final String key = diagramTextProviderInfo[2];
			if (preferenceStore.getBoolean(key) != (! button.getSelection())) {
				return false;
			}
		}
		return true;
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
		for (final DiagramTextProvider diagramTextProvider : Activator.getDefault().getDiagramTextProviders(null)) {
			final String[] diagramTextProviderInfo = getDiagramTextProviderInfo(diagramTextProvider);
			final Button button = diagramTextProviderButtons.get(diagramTextProvider);
			preferenceStore.setValue(diagramTextProviderInfo[2], ! button.getSelection());
		}
		// Return true to allow dialog to close
		return true;
	}

}
