package net.sourceforge.plantuml.eclipse.views;

public interface DiagramViewStatusListener {

	public void diagramViewStatusChanged(AbstractDiagramSourceView view, AbstractDiagramSourceView.ViewStatus status, Object diagram);
}
