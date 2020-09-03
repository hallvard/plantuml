package net.sourceforge.plantuml.util;

import java.util.Collection;

public interface DiagramPromiseProvider {
	public Collection<DiagramPromise> getDiagramInfos(DiagramPromiseContext context);
}
