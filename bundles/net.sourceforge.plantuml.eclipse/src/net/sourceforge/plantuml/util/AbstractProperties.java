package net.sourceforge.plantuml.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public abstract class AbstractProperties {

	protected abstract boolean hasProperty(String key);

	protected abstract Object getProperty(String key);

	protected abstract void putProperty(String key, Object value);

	public abstract Iterable<String> propertyNames();

	//

	public void setProperty(final String key, final Object value) {
		if (value != null) {
			checkValueType(value);
		}
		putProperty(key, value);
	}

	private final Class<?>[] valueOfParamTypes = { String.class };

	private void checkValueType(final Object value) throws IllegalArgumentException {
		checkValueType(value.getClass());
	}
	private void checkValueType(final Class<?> valueClass) throws IllegalArgumentException {
		if (valueClass == String.class) {
			return;
		}
		// we only allow simple types that support valueOf(String)
		try {
			final Method m = valueClass.getMethod("valueOf", valueOfParamTypes);
			if (m.getReturnType() == valueClass && Modifier.isStatic(m.getModifiers())) {
				return;
			}
		} catch (final Exception e) {
		}
		throw new IllegalArgumentException(valueClass + " doesn't have a static valueOf(String) method");
	}

	public <T> T getProperty(final String key, final Class<T> valueClass, final T def) {
		checkValueType(valueClass);
		if (! hasProperty(key)) {
			return def;
		}
		final Object value = getProperty(key);
		if (value == null) {
			return null;
		} else if (valueClass == String.class) {
			return (T) String.valueOf(value);
		}
		try {
			return (T) valueClass.getMethod("valueOf", valueOfParamTypes).invoke(null, String.valueOf(value));
		} catch (final Exception e) {
			throw new IllegalArgumentException(value + " couldnt't be converted to " + valueClass);
		}
	}
}
