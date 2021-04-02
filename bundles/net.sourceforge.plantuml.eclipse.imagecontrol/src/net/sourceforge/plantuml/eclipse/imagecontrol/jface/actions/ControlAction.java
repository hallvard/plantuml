package net.sourceforge.plantuml.eclipse.imagecontrol.jface.actions;

import java.util.function.Supplier;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Control;

public abstract class ControlAction<C extends Control> extends Action {

	private final Supplier<C> controlSupplier;

	public ControlAction(final Supplier<C> controlSupplier) {
		this.controlSupplier = controlSupplier;
	}

	public C getControl() {
		return controlSupplier.get();
	}
}
