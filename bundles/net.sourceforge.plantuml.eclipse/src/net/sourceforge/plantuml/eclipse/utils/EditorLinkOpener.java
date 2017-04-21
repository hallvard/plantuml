package net.sourceforge.plantuml.eclipse.utils;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

public abstract class EditorLinkOpener implements ILinkOpener {
	
	@Override
	public int supportsLink(LinkData link) {
		if (getPath(link) != null) {
			return CUSTOM_SUPPORT;
		}
		return NO_SUPPORT;
	}

	protected abstract IPath getPath(LinkData link);

	@Override
	public void openLink(LinkData link) {
		try {
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IPath path = getPath(link);
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(file.getName());
			page.openEditor(new FileEditorInput(file), desc.getId());
		} catch (PartInitException e) {
		}
	}
}
