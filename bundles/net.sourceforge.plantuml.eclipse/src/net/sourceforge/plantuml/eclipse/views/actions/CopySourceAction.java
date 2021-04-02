package net.sourceforge.plantuml.eclipse.views.actions;

import java.util.function.Supplier;

import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;

import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.util.DiagramData;

/**Manage the copy action.
 *
 * @author durif_c
 *
 */
public class CopySourceAction extends DiagramAction<DiagramData, Display> {

	/**
	 *
	 * @param diagram Diagram
	 */
	public CopySourceAction(final Supplier<DiagramData> diagramDataSupplier, final Display display) {
		super(diagramDataSupplier, display);
		setText(PlantumlConstants.COPY_SOURCE_MENU);
	}

	@Override
	public void run() {
		final Clipboard clipboard = new Clipboard(getContext());
		clipboard.setContents(new Object[]{getDiagramData().getTextDiagram()}, new Transfer[]{TextTransfer.getInstance()});
		clipboard.dispose();
	}
}
