package net.sourceforge.plantuml.eclipse.utils;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

public class PlatformLinkOpener implements ILinkOpener {
	
	@Override
	public int supportsLink(LinkData link) {
		try {
			URI uri = new URI(link.href);
			if ("platform".equals(uri.getScheme()) && uri.getPath().startsWith("/resource")) {
				return CUSTOM_SUPPORT;
			}
			IPath path = getPath(link);
			// should perhaps check if the file exists and has a default editor
			if (path.getFileExtension() != null) {
				return DEFAULT_SUPPORT;
			}
		} catch (URISyntaxException e) {
		}
		return NO_SUPPORT;
	}

	private IPath getPath(LinkData link) {
		try {
			URI uri = new URI(link.href);
			IPath path = new Path(uri.getPath());
			if ("platform".equals(uri.getScheme())) {
				path = path.removeFirstSegments(1);
			}
			return path;
		} catch (URISyntaxException e) {
			return new Path(link.href);
		}
	}

	@Override
	public void openLink(LinkData link) {
		try {
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(getPath(link));
			IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(file.getName());
			page.openEditor(new FileEditorInput(file), desc.getId());
		} catch (PartInitException e) {
		}
	}
}
