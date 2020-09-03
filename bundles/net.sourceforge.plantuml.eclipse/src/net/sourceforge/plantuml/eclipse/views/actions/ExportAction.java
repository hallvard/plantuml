package net.sourceforge.plantuml.eclipse.views.actions;

import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.FileDialog;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;
import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.eclipse.utils.WorkbenchUtil;
import net.sourceforge.plantuml.eclipse.views.DiagramImageControl;
import net.sourceforge.plantuml.util.DiagramData;

/**Manage the Export of the diagram.
 * 
 * @author durif_c
 * 
 */
public class ExportAction extends DiagramImageAction {

    public ExportAction(DiagramImageControl control, DiagramData diagram) {
		super(control, diagram);
        setText(PlantumlConstants.EXPORT_MENU);
    }

    @Override
    public void run() {
        final FileDialog fDialog = new FileDialog(getControl().getShell(), SWT.SAVE);
        fDialog.setFilterExtensions(new String[] { "*.png", "*.svg", "*.jpg", "*.gif" });
        fDialog.open();

        // get return from user.
        String fileName = fDialog.getFileName();
        if (StringUtils.isNotEmpty(fileName)) {
            String filePathName = fDialog.getFilterPath() + System.getProperty("file.separator") + fileName;
            String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
            if ("svg".equals(ext)) {
                createImageFileSvg(filePathName, diagram.getTextDiagram());
            } else {
                createImageFile(filePathName, ext, getImage());
            }
        }
    }
    
    private void createImageFile(String fileName, String format, ImageData imageData) {
    	ImageLoader loader = new ImageLoader();
        loader.data = new ImageData[]{imageData};
        try {
        	int swtFormat = SWT.class.getField("IMAGE_" + format.toUpperCase()).getInt(null);
        	loader.save(fileName, swtFormat);
		} catch (Exception e) {
		}
    }
    
	private void createImageFileSvg(String filePathName, String textDiagram) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePathName);
    		SourceStringReader reader = new SourceStringReader(textDiagram);
    		reader.outputImage(fos, new FileFormatOption(FileFormat.SVG));
        } catch (IOException e) {
            WorkbenchUtil.errorBox("Error during file generation for export.");
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    WorkbenchUtil.errorBox("Error during file generation.");
                }
            }
        }
	}
}
