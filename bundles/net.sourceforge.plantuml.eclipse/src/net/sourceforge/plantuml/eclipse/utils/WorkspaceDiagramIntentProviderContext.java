package net.sourceforge.plantuml.eclipse.utils;

import org.eclipse.core.runtime.IPath;

import net.sourceforge.plantuml.util.DiagramIntentContext;

public class WorkspaceDiagramIntentProviderContext extends DiagramIntentContext {

	private final IPath path;

	public WorkspaceDiagramIntentProviderContext(final IPath path) {
		this.path = path;
	}

	public IPath getPath() {
		return path;
	}
}
