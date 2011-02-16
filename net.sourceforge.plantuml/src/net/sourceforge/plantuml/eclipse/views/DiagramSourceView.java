package net.sourceforge.plantuml.eclipse.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class DiagramSourceView extends AbstractDiagramSourceView {

	private Text diagramTextView;
	
	@Override
	public void createPartControl(Composite parent) {
		diagramTextView = new Text(parent, SWT.MULTI | SWT.V_SCROLL);
		super.createPartControl(parent);
	}

	@Override
	protected void updateDiagramText(String text) {
		if (diagramTextView != null && (! diagramTextView.isDisposed())) {
			diagramTextView.setText(text == null ? "" : text);
		}
	}
	
	@Override
	public void setFocus() {
		diagramTextView.setFocus();
	}
}
