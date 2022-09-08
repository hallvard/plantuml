package net.sourceforge.plantuml.postprocessors;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import net.sourceforge.plantuml.eclipse.Activator;
import net.sourceforge.plantuml.util.AbstractDiagramIntent;
import net.sourceforge.plantuml.util.AbstractProperties;
import net.sourceforge.plantuml.util.BasicProperties;
import net.sourceforge.plantuml.util.DiagramTextPostProcessor;

public class DiagramTextInjectionPostProcessor implements DiagramTextPostProcessor {

	private Map<String, Map.Entry<DiagramTextInjectorMatcher, DiagramTextInjector>> diagramTextInjections;

	public void setDdiagramTextInjection(final String key, final DiagramTextInjectorMatcher matcher, final DiagramTextInjector injector) {
		setDiagramTextInjection(key, new AbstractMap.SimpleEntry<DiagramTextInjectorMatcher, DiagramTextInjector>(matcher,injector));
	}

	public void setDiagramTextInjection(final String key, final Map.Entry<DiagramTextInjectorMatcher, DiagramTextInjector> injection) {
		if (diagramTextInjections == null) {
			diagramTextInjections = new HashMap<String, Map.Entry<DiagramTextInjectorMatcher, DiagramTextInjector>>();
		}
		diagramTextInjections.put(key, injection);
	}

	private String getStringProperty(String value) {
		if (value.startsWith("\"")) {
			value = value.substring(1);
		}
		if (value.endsWith("\"")) {
			value = value.substring(0, value.length() - 1);
		}
		return value;
	}

	private Pattern getRegexProperty(final String value) {
		String regexString = getStringProperty(value);
		final String whatever = "...";
		if (regexString.startsWith(whatever) && regexString.endsWith(whatever)) {
			regexString = DiagramTextInjectorMatcher.containsRegex(regexString.substring(whatever.length(), regexString.length() - whatever.length()));
		} else if (regexString.endsWith(whatever)) {
			regexString = DiagramTextInjectorMatcher.startsWithRegex(regexString.substring(0, regexString.length() - whatever.length()));
		} else if (regexString.startsWith(whatever)) {
			regexString = DiagramTextInjectorMatcher.endsWithRegex(regexString.substring(whatever.length()));
		}
		return Pattern.compile(regexString);
	}

	@Override
	public String getDiagramText(final String diagramText, final AbstractDiagramIntent<?> diagramIntent) {
		if (diagramTextInjections == null) {
			initDiagramTextInjections();
		}
		if (diagramTextInjections != null) {
			for (final Map.Entry<DiagramTextInjectorMatcher, DiagramTextInjector> injection : diagramTextInjections.values()) {
				if (injection.getKey().matches(diagramText, diagramIntent.getContextProperties())) {
					return injection.getValue().getDiagramText(diagramText);
				}
			}
		}
		return null;
	}

	// init from Properties files

	private final BasicProperties textInjectionsProperties = new BasicProperties(Activator.getDefault().getProperties(getClass(), props -> {
		// invalidate when underlying properties are updated
		diagramTextInjections = null;
	}));

	private void initDiagramTextInjections() {
		initDiagramTextInjections(textInjectionsProperties);
	}

	private void initDiagramTextInjections(final AbstractProperties props) {
		diagramTextInjections = new HashMap<String, Map.Entry<DiagramTextInjectorMatcher, DiagramTextInjector>>();
		for (String key : textInjectionsProperties.propertyNames()) {
			String prop = null;
			final String value = props.getProperty(key, String.class, null);
			final int pos = key.indexOf('.');
			if (pos > 0) {
				prop = key.substring(pos + 1);
				key = key.substring(0, pos);
			}
			DiagramTextInjectorMatcher matcher = null;
			Entry<DiagramTextInjectorMatcher, DiagramTextInjector> injection = diagramTextInjections.get(key);
			if (injection == null) {
				injection = new AbstractMap.SimpleEntry<DiagramTextInjectorMatcher, DiagramTextInjector>(new DiagramTextInjectorMatcher(),null);
				setDiagramTextInjection(key, injection);
			}
			matcher = injection.getKey();
			if (prop == null) {
				final DiagramTextAfterFirstLineInjector injector = new DiagramTextAfterFirstLineInjector(getStringProperty(value));
				injection = new AbstractMap.SimpleEntry<DiagramTextInjectorMatcher, DiagramTextInjector>(matcher,injector);
				setDiagramTextInjection(key, injection);
			} else {
				matcher.setPropertyRegex(prop, getRegexProperty(value));
			}
		}
	}
}
