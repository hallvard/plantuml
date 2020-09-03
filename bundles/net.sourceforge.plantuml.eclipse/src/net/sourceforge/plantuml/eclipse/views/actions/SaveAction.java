package net.sourceforge.plantuml.eclipse.views.actions;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.ui.dialogs.SaveAsDialog;

import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.eclipse.utils.PlantumlUtil;
import net.sourceforge.plantuml.eclipse.utils.WorkbenchUtil;
import net.sourceforge.plantuml.eclipse.views.DiagramImageControl;
import net.sourceforge.plantuml.util.DiagramData;

public class SaveAction extends DiagramImageAction {

    public SaveAction(DiagramImageControl control, DiagramData diagram) {
		super(control, diagram);
        setText(PlantumlConstants.SAVE_MENU);
    }

    @Override
    public void run() {
        final SaveAsDialog fileDialog = new SaveAsDialog(getControl().getShell());
        if (fileDialog.open() == Dialog.OK) {
	        IPath path = fileDialog.getResult();
	        if (path != null) {
	        	try {
		        	PlantumlUtil.saveDiagramImage(getSourcePath(), diagram.getTextDiagram(), getImage(), path, true);
				} catch (Exception e) {
		            WorkbenchUtil.errorBox("Error during file generation: " + e);
				}
	        }
        }
    }
}
