package net.sourceforge.plantuml.eclipse.views;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import net.sourceforge.plantuml.util.DiagramIntent;

public class DiagramSourceView extends AbstractDiagramSourceView {

	private String diagramText;
	private Text diagramTextView;

	@Override
	public void createPartControl(final Composite parent) {
		diagramTextView = new Text(parent, SWT.MULTI | SWT.V_SCROLL);
		diagramTextView.setEditable(false);
		super.createPartControl(parent);
	}

	@Override
	protected void updateDiagramText(final String textDiagram, final DiagramIntent diagramIntent, final IProgressMonitor progressMonitor) {
		if (isValidControl(diagramTextView)) {
			setDiagramViewStatus(ViewStatus.DIAGRAM_VIEW_TEXT, textDiagram);
			this.diagramText = textDiagram;
			setDiagramViewStatus(ViewStatus.DIAGRAM_VIEW_DATA, textDiagram);
			asyncExec(() -> {
				if (isValidControl(diagramTextView)) {
					final String text = (textDiagram == null ? "" : textDiagram);
					diagramTextView.setText(text);
					setDiagramViewStatus(ViewStatus.DIAGRAM_VIEW, text);
				}
			});
		}
	}

	@Override
	public String getDiagramText() {
		return diagramText;
	}

	@Override
	public void setFocus() {
		diagramTextView.setFocus();
	}
}
