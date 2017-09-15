package no.hal.osgi.emf.util;

import java.util.regex.Pattern;

public class QualifiedName extends Segments {
	
	public QualifiedName(String... segments) {
		super(segments);
	}

	protected void checkSegment(String segment) {
		if (segment.length() < 1) {
			throw createIllegalSyntaxException(segment);
		}
		if (! Character.isJavaIdentifierStart(segment.charAt(0))) {
			throw createIllegalSyntaxException(segment);
		}
		for (int i = 1; i < segment.length(); i++) {
			if (! Character.isJavaIdentifierPart(segment.charAt(i))) {
				throw createIllegalSyntaxException(segment);
			}
		}
	}

	private final static String SEPARATOR = ".";

	@Override
	protected String getSeparator() {
		return SEPARATOR;
	}
	
	public static QualifiedName valueOf(String s) {
		return new QualifiedName(s.split(Pattern.quote(SEPARATOR)));
	}
}
