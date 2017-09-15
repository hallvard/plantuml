package no.hal.osgi.emf.util;

public abstract class Segments {
	
	public final String[] segments;

	public Segments(String... segments) {
		for (int i = 0; i < segments.length; i++) {
			checkSegment(segments[i]);
		}
		this.segments = segments;
	}

	protected RuntimeException createIllegalSyntaxException(String s) {
		return new IllegalArgumentException("Illegal syntax: " + s);
	}

	protected void checkSegment(String segment) {
	}
	
	protected abstract String getSeparator();
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < segments.length; i++) {
			if (i > 0) {
				builder.append(getSeparator());
			}
			builder.append(segments[i]);
		}
		return builder.toString();
	}
}
