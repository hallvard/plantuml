package net.sourceforge.plantuml.util;

public interface DiagramIntent {
	/**
	 * Returns the label to show for this intent,
	 * without necessarily having computed the diagram text.
	 * @return the label to show for this intent
	 */
	public String getLabel();

	/**
	 * Computes and returns the diagram text.
	 * @return the diagram text
	 */
	public String getDiagramText();

	/**
	 * Returns the priority,
	 * to select the appropriate intent.
	 * @return the intent's priority
	 */
	public int getPriority();
}
