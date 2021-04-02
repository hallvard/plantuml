package net.sourceforge.plantuml.eclipse;

import net.sourceforge.plantuml.util.DiagramIntentProvider;

public interface DiagramIntentProviderRegistry {
	public void registerDiagramIntentProvider(final DiagramIntentProvider diagramTextProvider, final DiagramIntentProviderInfo info);
}
