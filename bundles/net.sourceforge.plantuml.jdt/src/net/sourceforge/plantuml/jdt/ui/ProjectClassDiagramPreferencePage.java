package net.sourceforge.plantuml.jdt.ui;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import net.sourceforge.plantuml.jdt.Activator;

public class ProjectClassDiagramPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	public final static String
		GENERATE_PACKAGES_KEY 	= "generatePackages",
		PACKAGE_STYLE_KEY 		= "packageStyle"
		;

	private Button generatePackagesButton;
	private Combo packageStyleSelector;
	
	public ProjectClassDiagramPreferencePage() {
		super();
	}

	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite panel = new Composite(parent, SWT.NONE);
		panel.setLayout(new GridLayout(1, false));
		IPreferenceStore preferenceStore = getPreferenceStore();
		
		Group packageGenerationGroup = new Group(panel, SWT.NONE);
		packageGenerationGroup.setText("Package generation");
		packageGenerationGroup.setLayout(new GridLayout(2, false));

		generatePackagesButton = new Button(packageGenerationGroup, SWT.CHECK);
		generatePackagesButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		generatePackagesButton.setText("Generate packages");
		generatePackagesButton.setSelection(preferenceStore.getBoolean(GENERATE_PACKAGES_KEY));
		
		Label packageStyleLabel = new Label(packageGenerationGroup, SWT.NONE);
		packageStyleLabel.setText("Style: ");
		packageStyleSelector = new Combo(packageGenerationGroup, SWT.READ_ONLY);
		packageStyleSelector.setItems(JdtPlantUmlView.ALL_PACKAGE_STYLES);
		String currentPackageStyle = preferenceStore.getString(PACKAGE_STYLE_KEY);
		if (currentPackageStyle == null || currentPackageStyle.length() == 0) {
			currentPackageStyle = JdtPlantUmlView.ALL_PACKAGE_STYLES[0];
		}
		for (int i = 0; i < JdtPlantUmlView.ALL_PACKAGE_STYLES.length; i++) {
			if (currentPackageStyle.equals(JdtPlantUmlView.ALL_PACKAGE_STYLES[i])) {
				packageStyleSelector.select(i);
				break;
			}
		}
		return panel;
	}

	/**
	 * Called when user clicks Restore Defaults
	 */
	protected void performDefaults() {
		// Reset the fields to the defaults
		generatePackagesButton.setSelection(false);
		packageStyleSelector.select(0);
	}

	/**
	 * Called when user clicks Apply or OK
	 * 
	 * @return boolean
	 */
	public boolean performOk() {
		// Get the preference store
		IPreferenceStore preferenceStore = getPreferenceStore();
		preferenceStore.setValue(GENERATE_PACKAGES_KEY, generatePackagesButton.getSelection());
		preferenceStore.setValue(PACKAGE_STYLE_KEY, JdtPlantUmlView.ALL_PACKAGE_STYLES[packageStyleSelector.getSelectionIndex()]);

		// Return true to allow dialog to close
		return true;
	}
}
