package net.sourceforge.plantuml.eclipse.views.actions;

import java.util.function.Supplier;

import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;

import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.util.DiagramImageData;

/**Manage the copy action.
 *
 * @author durif_c
 *
 */
public class CopyAction extends DiagramImageAction<Display> {

	/**
	 *
	 * @param diagram Diagram
	 */
	public CopyAction(final Supplier<DiagramImageData> diagramImageDataSupplier, final Display display) {
		super(diagramImageDataSupplier, display);
		setText(PlantumlConstants.COPY_MENU);
	}

	/**
	 *
	 */
	@Override
	public void run() {
		final Clipboard clipboard = new Clipboard(getContext());
		clipboard.setContents(new Object[]{getImage()}, new Transfer[]{ImageTransfer.getInstance()});
		clipboard.dispose();
	}
}
