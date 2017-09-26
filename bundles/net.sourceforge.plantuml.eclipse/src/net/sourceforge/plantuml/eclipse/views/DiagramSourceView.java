package net.sourceforge.plantuml.eclipse.views;

import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class DiagramSourceView extends AbstractDiagramSourceView {

	private Text diagramTextView;
	
	@Override
	public void createPartControl(Composite parent) {
		diagramTextView = new Text(parent, SWT.MULTI | SWT.V_SCROLL);
		diagramTextView.setEditable(false);
		super.createPartControl(parent);
	}

	@Override
	protected void updateDiagramText(String text, IPath original, Map<String, Object> markerAttributes) {
		if (diagramTextView != null && (! diagramTextView.isDisposed())) {
			diagramTextView.setText(text == null ? "" : text);
		}
	}
	
	@Override
	public String getDiagramText() {
		return (diagramTextView != null && (! diagramTextView.isDisposed()) ? diagramTextView.getText() : null);
	}
	
	@Override
	public void setFocus() {
		diagramTextView.setFocus();
	}
}
