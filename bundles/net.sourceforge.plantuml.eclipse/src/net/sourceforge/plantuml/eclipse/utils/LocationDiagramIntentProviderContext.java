package net.sourceforge.plantuml.eclipse.utils;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import net.sourceforge.plantuml.util.DiagramIntentContext;

public abstract class LocationDiagramIntentProviderContext extends DiagramIntentContext {

	private URI location;

	public URI getLocation() {
		return location;
	}

	public void setLocation(final URI location) {
		this.location = location;
	}

	public void setWorkspaceLocation(final IPath path) {
		setLocation(path, "ws");
	}

	public void setFileLocation(final IPath path) {
		setLocation(path, "file");
	}

	private void setLocation(final IPath path, final String scheme) {
		try {
			setLocation(new URI("ws", null, path.toString(), null));
		} catch (final URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	public IPath getPath() {
		return (location != null ? new Path(location.getPath()) : null);
	}
}
