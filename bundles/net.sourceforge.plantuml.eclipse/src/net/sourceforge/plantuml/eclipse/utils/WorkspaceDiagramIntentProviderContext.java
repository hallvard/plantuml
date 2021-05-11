package net.sourceforge.plantuml.eclipse.utils;

import org.eclipse.core.runtime.IPath;

public class WorkspaceDiagramIntentProviderContext extends LocationDiagramIntentProviderContext {

	public WorkspaceDiagramIntentProviderContext(final IPath path) {
		setWorkspaceLocation(path);
	}
}
