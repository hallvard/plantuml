package net.sourceforge.plantuml.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

public class BasicProperties extends AbstractProperties {

	private final Properties properties;

	public BasicProperties(final Properties defaults) {
		this.properties = new Properties(defaults);
	}

	@Override
	public String toString() {
		return properties.toString();
	}

	//

	@Override
	protected boolean hasProperty(final String key) {
		return properties.getProperty(key) != null;
	}

	@Override
	protected Object getProperty(final String key) {
		return properties.getProperty(key);
	}

	@Override
	protected void putProperty(final String key, final Object value) {
		properties.put(key, value);
	}

	@Override
	public Iterable<String> propertyNames() {
		final Collection<String> keys = new ArrayList<String>();
		for (final Object key : Collections.list(properties.propertyNames())) {
			if (key instanceof String) {
				keys.add((String) key);
			}
		}
		return keys;
	}
}
