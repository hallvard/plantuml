package net.sourceforge.plantuml.eclipse.utils;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public abstract class EditorLinkOpener implements ILinkOpener {
	
	@Override
	public int supportsLink(LinkData link) {
		if (getPath(link) != null) {
			return CUSTOM_SUPPORT;
		}
		return NO_SUPPORT;
	}

	protected IPath getPath(LinkData link) {
		try {
			URI uri = new URI(link.href);
			if (uri.getPath() != null) {
				IPath path = new Path(uri.getPath());
				return path;
			}
		} catch (URISyntaxException e) {
			return new Path(link.href);
		}
		return null;
	}

	@Override
	public void openLink(LinkData link) {
		try {
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IPath path = getPath(link);
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			IDE.openEditor(page, file);
		} catch (PartInitException e) {
		}
	}
}
