package net.sourceforge.plantuml.util;

public abstract class AbstractDiagramIntent<T> implements DiagramIntent {

	private final T source;

	public T getSource() {
		return source;
	}

	private final String label;

	@Override
	public String getLabel() {
		return label;
	}

	public AbstractDiagramIntent(final T source, final String label) {
		this.source = source;
		this.label = label;
	}

	private static String firstLine(final String s) {
		final int pos = s.indexOf('\n');
		return (pos >= 0 ? s.substring(0, pos) : s);
	}

	public AbstractDiagramIntent(final T source) {
		this(source, firstLine(source.toString()));
	}

	@Override
	public String toString() {
		return source.toString();
	}

	//

	@Override
	public String getDiagramText() {
		return toString();
	}

	//

	public static int DEFAULT_PRIORITY = -1, NORMAL_PRIORITY = 0, SELECTED_PRIORITY = 1;

	private int priority = NORMAL_PRIORITY;

	@Override
	public int getPriority() {
		return priority;
	}

	public void setPriority(final int priority) {
		this.priority = priority;
	}

	//

	private ResourceInfo resourceInfo;

	public ResourceInfo getResourceInfo() {
		return resourceInfo;
	}

	public void setResourceInfo(final ResourceInfo resourceInfo) {
		this.resourceInfo = resourceInfo;
	}
}
