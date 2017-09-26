package net.sourceforge.plantuml.eclipse.views.actions;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.ui.dialogs.SaveAsDialog;

import net.sourceforge.plantuml.eclipse.utils.Diagram;
import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.eclipse.utils.PlantumlUtil;
import net.sourceforge.plantuml.eclipse.utils.WorkbenchUtil;
import net.sourceforge.plantuml.eclipse.views.DiagramImageControl;

public class SaveAction extends DiagramImageAction {

    public SaveAction(DiagramImageControl control, Diagram diagram) {
		super(control, diagram);
        setText(PlantumlConstants.SAVE_MENU);
    }

    @Override
    public void run() {
        final SaveAsDialog fileDialog = new SaveAsDialog(getControl().getShell());
//        fileDialog.setFilterExtensions(new String[] { "*.png", "*.svg", "*.jpg", "*.gif" });
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
