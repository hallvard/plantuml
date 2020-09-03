package net.sourceforge.plantuml.util;

public class SimpleDiagramPromise extends AbstractDiagramPromise {

	private final String diagramText;

	public SimpleDiagramPromise(final String diagramText) {
		this.diagramText = diagramText;
	}

	@Override
	public String getDiagramText() {
		return diagramText;
	}
}
