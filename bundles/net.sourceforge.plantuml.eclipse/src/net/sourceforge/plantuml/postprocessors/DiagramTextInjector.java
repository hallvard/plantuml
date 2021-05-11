package net.sourceforge.plantuml.postprocessors;

public abstract class DiagramTextInjector {

	private final String text;

	public String getText() {
		return text;
	}

	public DiagramTextInjector(final String text) {
		this.text = text;
	}

	public abstract String getDiagramText(final String diagramText);
}
