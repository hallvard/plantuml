package net.sourceforge.plantuml.eclipse.utils;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

public class PlatformLinkOpener extends EditorLinkOpener {
	
	@Override
	public int supportsLink(LinkData link) {
		try {
			URI uri = new URI(link.href);
			if ("platform".equals(uri.getScheme()) && uri.getPath().startsWith("/resource")) {
				return CUSTOM_SUPPORT;
			}
			IPath path = getPath(link);
			// should perhaps check if the file exists and has a default editor
			if (path != null && path.getFileExtension() != null) {
				return DEFAULT_SUPPORT;
			}
		} catch (URISyntaxException e) {
		}
		return NO_SUPPORT;
	}
	
	@Override
	protected IPath getPath(LinkData link) {
		try {
			URI uri = new URI(link.href);
			if (uri.getPath() != null) {
				IPath path = new Path(uri.getPath());
				return path.removeFirstSegments(1);
			}
		} catch (URISyntaxException e) {
			return new Path(link.href);
		}
		return null;
	}
}
