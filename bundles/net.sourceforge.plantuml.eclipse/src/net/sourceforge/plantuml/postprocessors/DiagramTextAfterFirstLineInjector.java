package net.sourceforge.plantuml.postprocessors;

public class DiagramTextAfterFirstLineInjector extends DiagramTextInjector {

	public DiagramTextAfterFirstLineInjector(final String text) {
		super(text);
	}

	@Override
	public String getDiagramText(final String diagramText) {
		final int pos = diagramText.indexOf('\n');
		if (pos < 0) {
			return null;
		}
		final String prefix = diagramText.substring(0, pos + 1);
		final String suffix = diagramText.substring(pos);
		return prefix + getText() + suffix;
	}
}
