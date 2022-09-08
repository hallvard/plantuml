package net.sourceforge.plantuml.eclipse.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

public class PropertiesLoader implements IResourceChangeListener, IResourceDeltaVisitor {

	private final Map<IPath, Properties> pathProperties = new HashMap<IPath, Properties>();

	public void register(final Properties properties, final IPath path) {
		pathProperties.put(path, properties);
	}

	public void load(final Properties properties, final IPath path) {
		loadProperties(properties, ResourcesPlugin.getWorkspace().getRoot().getFile(path));
		register(properties, path);
	}

	@Override
	public void resourceChanged(final IResourceChangeEvent changeEvent) {
		try {
			changeEvent.getDelta().accept(this);
		} catch (final CoreException e) {
		}
	}

	@Override
	public boolean visit(final IResourceDelta delta) throws CoreException {
		final int kind = delta.getKind();
		if (kind == IResourceDelta.ADDED || (kind == IResourceDelta.CHANGED && (delta.getFlags() & IResourceDelta.CONTENT) != 0)) {
			final IResource resource = delta.getResource();
			if (resource instanceof IFile) {
				final Properties properties = pathProperties.get(resource.getFullPath());
				if (properties != null) {
					loadProperties(properties, resource);
				}
				return false;
			}
		}
		return true;
	}

	public void loadProperties(final Properties props, final IResource resource) {
		try (InputStream input = ((IFile) resource).getContents()) {
			loadProperties(props, input);
		} catch (final IOException e) {
		} catch (final CoreException e) {
		}
	}

	public void loadProperties(final Properties props, final InputStream input) {
		try {
			if (input != null) {
				props.load(input);
			}
		} catch (final IOException e) {
		}
	}
}