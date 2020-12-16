package net.sourceforge.plantuml.eclipse.views.actions;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.ui.dialogs.SaveAsDialog;

import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.eclipse.utils.PlantumlUtil;
import net.sourceforge.plantuml.eclipse.utils.WorkbenchUtil;
import net.sourceforge.plantuml.eclipse.views.DiagramImageControl;
import net.sourceforge.plantuml.util.DiagramData;
import net.sourceforge.plantuml.util.DiagramImageData;

public class SaveAction extends DiagramImageAction {

	public SaveAction(final DiagramImageControl control, final DiagramData diagram) {
		super(control, new DiagramImageData(diagram));
		setText(PlantumlConstants.SAVE_MENU);
	}

	@Override
	public void run() {
		final SaveAsDialog fileDialog = new SaveAsDialog(getControl().getShell());
		if (fileDialog.open() == Dialog.OK) {
			final IPath path = fileDialog.getResult();
			if (path != null) {
				try {
					PlantumlUtil.saveDiagramImage(getDiagramImageData(), path, true);
				} catch (final Exception e) {
					WorkbenchUtil.errorBox("Error during file generation: " + e);
				}
			}
		}
	}
}
