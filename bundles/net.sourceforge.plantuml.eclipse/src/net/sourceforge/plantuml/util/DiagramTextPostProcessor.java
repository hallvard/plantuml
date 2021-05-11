package net.sourceforge.plantuml.util;

public interface DiagramTextPostProcessor {

	public String getDiagramText(String diagramText, AbstractDiagramIntent<?> diagramIntent);
}
