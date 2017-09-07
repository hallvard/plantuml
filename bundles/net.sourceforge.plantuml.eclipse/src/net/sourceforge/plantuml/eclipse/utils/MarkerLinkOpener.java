package net.sourceforge.plantuml.eclipse.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public class MarkerLinkOpener implements ILinkOpener {
	
	@Override
	public int supportsLink(LinkData link) {
		try {
			URI uri = new URI(link.href);
			if ("marker".equals(uri.getScheme())) {
				return CUSTOM_SUPPORT;
			}
		} catch (URISyntaxException e) {
		}
		return NO_SUPPORT;
	}

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

	protected String getMarkerType(LinkData link) {
		try {
			URI uri = new URI(link.href);
			if (uri.getPath() != null) {
				IPath path = new Path(uri.getPath());
				if (path.segmentCount() > 0) {
					return path.segment(0);
				}
			}
		} catch (URISyntaxException e) {
		}
		return null;
	}

	protected Object getMarkerAttributeValue(String name, String valueString) {
		Object value = valueString; // default
		try {
			value = Integer.valueOf(valueString); // try as int
		} catch (NumberFormatException e) {
		}
		return value;
	}
	
	protected void addMarkerAttributes(LinkData link, Map<String, Object> attributes) {
		try {
			URI uri = new URI(link.href);
			String lineNum = uri.getFragment();
			if (lineNum != null) {
				try {
					attributes.put(IMarker.LINE_NUMBER, Integer.valueOf(lineNum));
				} catch (NumberFormatException e) {
				}
			}
			String query = uri.getQuery();
			if (query != null) {
				for (String queryArg : query.split("&")) {
					int pos = queryArg.indexOf('=');
					String attributeName = queryArg.substring(0,  pos), valueString = queryArg.substring(pos + 1);
					Object value = getMarkerAttributeValue(attributeName, valueString); // default
					try {
						value = Integer.valueOf(valueString); // try as int
					} catch (NumberFormatException e) {
					}
					attributes.put(attributeName, value);
				}
			}
		} catch (URISyntaxException e) {
		} catch (NumberFormatException e) {
		}
	}

	public static String createMarkerLink(String markerType, String path, String... args) {
		String link = "marker:/" + markerType + path;
		if (args.length > 0) {
			link = link + "?";
			for (int i = 0; i < args.length; i += 2) {
				if (i > 0) {
					link = link + "&";					
				}
				link = link + args[i] + "=" + args[i + 1];				
			}
		}
		return link;
	}

	protected IMarker createMarker(LinkData link, IFile file) {
		IMarker marker = null;
		try {
			marker = file.createMarker(getMarkerType(link));
			Map<String, Object> attributes = new HashMap<String, Object>();
			addMarkerAttributes(link, attributes);
			marker.setAttributes(attributes);
			return marker;
		} catch (CoreException e) {
		}
		return marker;
	}
	
	@Override
	public void openLink(LinkData link) {
		IMarker marker = null;
		try {
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IPath path = getPath(link);
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			marker = createMarker(link, file);
			IDE.openEditor(page, marker);
		} catch (PartInitException e) {
		} finally {
			if (marker != null) {
				try {
					marker.delete();
				} catch (CoreException e) {
				}
			}
		}
	}
}
