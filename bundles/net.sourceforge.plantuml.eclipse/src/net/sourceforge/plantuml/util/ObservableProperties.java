package net.sourceforge.plantuml.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

@SuppressWarnings("serial")
public class ObservableProperties extends Properties {

	public interface Listener {

		public void propertiesChanged(String... propertyNames);
	}

	public ObservableProperties(final Properties properties) {
		super(properties);
		if (properties instanceof ObservableProperties) {
			((ObservableProperties) properties).addListener(this::firePropertiesChanged);
		}
	}
	public ObservableProperties() {
		super();
	}

	//

	private final Collection<Listener> listeners = new ArrayList<>();

	public void addListener(final Listener listener) {
		listeners.add(listener);
	}

	public void removeListener(final Listener listener) {
		listeners.remove(listener);
	}

	public void fireUnknownPropertiesChanged() {
		firePropertiesChanged((String[]) null);
	}

	public void firePropertiesChanged(final String... propertyNames) {
		for (final Listener listener : listeners) {
			listener.propertiesChanged(propertyNames);
		}
	}

	//

	@Override
	public synchronized void clear() {
		super.clear();
		fireUnknownPropertiesChanged();
	}

	@Override
	public synchronized Object setProperty(final String key, final String value) {
		final Object oldValue = super.setProperty(key, value);
		firePropertiesChanged(key);
		return oldValue;
	}

	@Override
	public synchronized void load(final InputStream input) throws IOException {
		super.load(input);
		fireUnknownPropertiesChanged();
	}

	@Override
	public synchronized void load(final Reader reader) throws IOException {
		super.load(reader);
		fireUnknownPropertiesChanged();
	}

	@Override
	public synchronized void loadFromXML(final InputStream in) throws IOException, InvalidPropertiesFormatException {
		super.loadFromXML(in);
		fireUnknownPropertiesChanged();
	}
}
