package net.sourceforge.plantuml.eclipse.listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;

import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.eclipse.model.Diagram;
import net.sourceforge.plantuml.eclipse.utils.PlantUmlUtils;

/**Manage the Export of the diagram.
 * 
 * @author durif_c
 * 
 */
public class ExportRightClickListener extends RightClickListener {
    /**
     * 
     */
    private final Composite composite;

    /**
     * 
     * @param diagram Diagram
     * @param container Composite
     */
    public ExportRightClickListener(Diagram diagram, Composite container) {
        super();
        this.diagram = diagram;
        this.composite = container;
    }

    /**
     * 
     */
    protected void run() {
        final FileDialog fDialog = new FileDialog(this.composite.getShell(), SWT.SAVE);
        fDialog.setFilterExtensions(new String[] { "*.png", "*.svg", "*.jpg", "*.gif" });
        fDialog.open();

        // get return from user.
        if (StringUtils.isNotEmpty(fDialog.getFileName())) {
            final String filePathName = fDialog.getFilterPath()
                    + System.getProperty("file.separator")
                    + fDialog.getFileName();

            final String imageFormat = fDialog.getFileName().substring(
                    fDialog.getFileName().lastIndexOf(".") + 1).toUpperCase();
            if ("SVG".equals(imageFormat)) {
                PlantUmlUtils.createImageFileSvg(filePathName, diagram
                        .getTextDiagram());
            } else {
                PlantUmlUtils.createImageFile(filePathName, imageFormat, diagram
                        .getBufferedImage());
            }
        }
    }

}
