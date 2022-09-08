package net.sourceforge.plantuml.postprocessors;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import net.sourceforge.plantuml.util.AbstractProperties;

public class DiagramTextInjectorMatcher {

	private final Map<String, Pattern> contextPropertyPatterns = new HashMap<String, Pattern>();

	public void setPropertyRegex(final String key, final Pattern regex) {
		contextPropertyPatterns.put(key, regex);
	}
	public void setPropertyRegex(final String key, final String regex) {
		setPropertyRegex(key, Pattern.compile(regex));
	}

	public static String startsWithRegex(final String s) {
		return "^" + Pattern.quote(s) + "(?s).*";
	}
	public void setPropertyStartsWith(final String key, final String s) {
		setPropertyRegex(key, startsWithRegex(s));
	}

	public static String containsRegex(final String s) {
		return "^(?s).*" + Pattern.quote(s) + "(?s).*$";
	}
	public void setPropertyContains(final String key, final String s) {
		setPropertyRegex(key, containsRegex(s));
	}

	public void setPropertyEndsWith(final String key, final String s) {
		setPropertyRegex(key, endsWithRegex(s));
	}
	public static String endsWithRegex(final String s) {
		return "(?s).*" + Pattern.quote(s) + "$";
	}

	//

	private boolean doesntMatch(final String key, final String s) {
		return doesntMatch(contextPropertyPatterns.get(key), s);
	}
	private boolean doesntMatch(final Pattern regex, final String s) {
		return (regex != null && (s == null || (! regex.matcher(s).matches())));
	}

	public boolean matches(final String diagramText, final AbstractProperties contextProperties) {
		if (doesntMatch("diagramText", diagramText)) {
			return false;
		}
		for (final String key : contextProperties.propertyNames()) {
			if (doesntMatch(key, contextProperties.getProperty(key, String.class, null))) {
				return false;
			}
		}
		return true;
	}
}
