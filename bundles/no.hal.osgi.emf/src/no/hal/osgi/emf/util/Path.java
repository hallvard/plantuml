package no.hal.osgi.emf.util;

import java.util.regex.Pattern;

public class Path extends Segments {
	
	public Path(String... segments) {
		super(segments);
	}

	private final static String SEPARATOR = "/";

	@Override
	protected String getSeparator() {
		return SEPARATOR;
	}
	
	public static Path valueOf(String s) {
		return new Path(s.split(Pattern.quote(SEPARATOR)));
	}
}
