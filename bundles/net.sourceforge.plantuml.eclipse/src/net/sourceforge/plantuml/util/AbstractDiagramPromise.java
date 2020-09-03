package net.sourceforge.plantuml.util;

public abstract class AbstractDiagramPromise implements DiagramPromise {

	private String label;

	@Override
	public String getLabel() {
		return label;
	}
}
