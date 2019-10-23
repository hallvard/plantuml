package net.sourceforge.plantuml.eclipse;

import net.sourceforge.plantuml.eclipse.utils.DiagramTextProvider;

public interface DiagramTextProviderRegistry {
	public void registerDiagramTextProvider(final DiagramTextProvider diagramTextProvider, final DiagramTextProviderInfo info);
}
