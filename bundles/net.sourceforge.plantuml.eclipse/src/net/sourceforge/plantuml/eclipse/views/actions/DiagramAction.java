package net.sourceforge.plantuml.eclipse.views.actions;

import java.util.function.Supplier;

import org.eclipse.jface.action.Action;

public abstract class DiagramAction<D, C> extends Action {

	protected final Supplier<D> diagramDataSupplier;
	protected final C context;

	public DiagramAction(final Supplier<D> diagramDataSupplier, final C context) {
		this.diagramDataSupplier = diagramDataSupplier;
		this.context = context;
	}

	public C getContext() {
		return context;
	}

	protected D getDiagramData() {
		return diagramDataSupplier.get();
	}
}
