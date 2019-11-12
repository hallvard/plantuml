package net.sourceforge.plantuml.jdt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IDebugElement;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewPart;

import net.sourceforge.plantuml.eclipse.utils.DiagramTextProvider;

public class DebugViewDiagramTextProvider implements DiagramTextProvider {

	@Override
	public boolean supportsSelection(final ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			return ((IStructuredSelection) selection).getFirstElement() instanceof IDebugElement;
		}
		return true;
	}

	@Override
	public boolean supportsView(final IViewPart viewPart) {
		final String partId = viewPart.getSite().getId();
		if (! partId.startsWith("org.eclipse.debug.ui.")) {
			return false;
		}
		return partId.endsWith("DebugView") || partId.endsWith("VariableView");
	}

	private <T> Collection<T> getSelection(final ISelection selection, final Class<T> clazz) {
		final Collection<T> result = new ArrayList<T>();
		if (selection instanceof IStructuredSelection) {
			final Iterator<Object> it = ((IStructuredSelection) selection).iterator();
			while (it.hasNext()) {
				final Object o = it.next();
				if (clazz.isInstance(o)) {
					result.add((T) o);
				}
			}
		}
		return result;
	}

	@Override
	public String getDiagramText(final IViewPart viewPart, final ISelection selection) {
		final Collection<IStackFrame> stackFrames = getSelection(selection, IStackFrame.class);
		if (! stackFrames.isEmpty()) {
			return generateStackDiagram(stackFrames);
		} else {
			final Collection<IVariable> variables = getSelection(selection, IVariable.class);
			if (! variables.isEmpty()) {
				return generateVariablesDiagram(variables);
			}
		}
		return null;
	}

	protected String generateStackDiagram(final Collection<IStackFrame> stackFrames) {
		final StringBuilder builder = new StringBuilder();
		for (final IStackFrame stackFrame : stackFrames) {
			generateStackDiagram(stackFrame, builder);
		}
		return builder.toString();
	}

	protected void generateStackDiagram(final IStackFrame stackFrame, final StringBuilder builder) {
		try {
			appendStackFrameStart(stackFrame, builder);
			if (stackFrame.hasVariables()) {
				final Iterator<IVariable> variables = Arrays.asList(stackFrame.getVariables()).iterator();
				final Map<IVariable, Iterator<IValue>> variablesValues = new HashMap<IVariable, Iterator<IValue>>();
				while (variables.hasNext()) {
					final IVariable variable = variables.next();
					final Iterator<IValue> variableValues = getVariableElements(variable);
					if (variableValues == null) {
						appendDiagramVariable(variable, builder);
					} else {
						variablesValues.put(variable, variableValues);
					}
				}
				appendStackFrameEnd(stackFrame, builder);
				final String stackFrameId = getDiagramElementId(stackFrame);
				for (final IVariable variable : variablesValues.keySet()) {
					final Iterator<IValue> variableValues = variablesValues.get(variable);
					while (variableValues.hasNext()) {
						final IValue value = variableValues.next();
						generateVariableObject(stackFrame, variable, value, 1, builder);
						builder.append(stackFrameId);
						builder.append("-->");
						builder.append(getDiagramElementId(variable));
						builder.append(":");
						builder.append(variable.getName());
						builder.append("\n");
					}
				}
			}
		} catch (final DebugException e) {
		}
	}

	protected Iterator<IValue> getVariableElements(final IVariable variable) {
		return null;
	}

	protected String getDiagramElementId(final IStackFrame stackFrame) {
		String prefix = "diagramElement";
		try {
			prefix = stackFrame.getName();
		} catch (final DebugException e) {
		}
		return prefix + stackFrame.hashCode();
	}

	protected String getDiagramElementId(final IVariable variable) {
		String prefix = "diagramElement";
		try {
			prefix = variable.getName();
		} catch (final DebugException e) {
		}
		return prefix + variable.hashCode();
	}

	protected void appendStackFrameStart(final IStackFrame stackFrame, final StringBuilder builder) {
		builder.append("object \"");
		try {
			appendStackFrameName(stackFrame, builder);
		} catch (final DebugException e) {
			builder.append("stackFrame");
		}
		builder.append("\" as " + getDiagramElementId(stackFrame));
		builder.append(" {\n");
	}
	protected void appendStackFrameEnd(final IStackFrame stackFrame, final StringBuilder builder) {
		builder.append("}\n");
	}

	protected void appendStackFrameName(final IStackFrame stackFrame, final StringBuilder builder)
			throws DebugException {
		builder.append(stackFrame.getName());
	}

	protected void appendDiagramVariable(final IVariable variable, final StringBuilder builder) throws DebugException {
		builder.append("\t");
		//		appendVariableType(variable, builder);
		//		builder.append(" ");
		builder.append(variable.getName());
		builder.append(" = ");
		appendVariableValue(variable, builder);
		builder.append("\n");
	}

	protected void appendVariableValue(final IVariable variable, final StringBuilder builder) {
		try {
			builder.append(variable.getValue().getValueString());
		} catch (final DebugException e) {
			builder.append("?");
		}
	}

	protected void appendVariableType(final IVariable variable, final StringBuilder builder) throws DebugException {
		appendType(variable.getReferenceTypeName(), builder);
	}

	protected void appendType(final String type, final StringBuilder builder) {
		builder.append(type);
	}

	//

	protected String generateVariablesDiagram(final Collection<IVariable> variables) {
		final StringBuilder builder = new StringBuilder();
		for (final IVariable variable : variables) {
			try {
				generateVariableObject(null, variable, variable.getValue(), 1, builder);
			} catch (final DebugException e) {
			}
		}
		return builder.toString();
	}

	protected void generateVariableObject(final IStackFrame context, final IVariable variable, final IValue value, final int width, final StringBuilder builder) {
		try {
			appendVariableObjectStart(variable, value, builder);
			final Map<IVariable, Iterator<IValue>> variablesValues = new HashMap<IVariable, Iterator<IValue>>();
			if (width > 0 && value.hasVariables()) {
				for (final IVariable childVariable : value.getVariables()) {
					if (shouldIncludeVariable(context, value, childVariable)) {
						final Iterator<IValue> variableValues = getVariableElements(childVariable);
						if (variableValues == null) {
							appendDiagramVariable(childVariable, builder);
						} else {
							variablesValues.put(childVariable, variableValues);
						}
					}
				}
			}
			appendVariableObjectEnd(variable, value, builder);
			final String variableId = getDiagramElementId(variable);
			for (final IVariable childVariable : variablesValues.keySet()) {
				final Iterator<IValue> variableValues = variablesValues.get(childVariable);
				while (variableValues.hasNext()) {
					final IValue childValue = variableValues.next();
					generateVariableObject(context, childVariable, childValue, width - 1, builder);
					builder.append(variableId);
					builder.append("-->");
					builder.append(getDiagramElementId(childVariable));
					builder.append(":");
					builder.append(childVariable.getName());
					builder.append("\n");
				}
			}
		} catch (final DebugException e) {
		}
	}

	protected boolean shouldIncludeVariable(final IStackFrame context, final IValue value, final IVariable variable) {
		return true;
	}

	protected String getDiagramElementId(final IValue value) {
		String id = String.valueOf(value.hashCode());
		try {
			final String valueString = value.getValueString();
			if (valueString.startsWith("(id=") && valueString.endsWith(")")) {
				id = valueString.substring(4, valueString.length() - 1);
			}
		} catch (final DebugException e) {
		}
		return id;
	}

	protected void appendVariableObjectName(final IVariable variable, final StringBuilder builder)
			throws DebugException {
		builder.append("~#");
		builder.append(getDiagramElementId(variable.getValue()));
		builder.append(":");
		appendVariableType(variable, builder);
	}

	protected void appendVariableObjectStart(final IVariable variable, final IValue value, final StringBuilder builder) {
		builder.append("object \"");
		try {
			appendVariableObjectName(variable, builder);
			builder.append("\" as " + variable.getName() + variable.hashCode());
		} catch (final Exception e) {
			builder.append("variable");
		}
		builder.append(" {\n");
	}
	protected void appendVariableObjectEnd(final IVariable variable, final IValue value, final StringBuilder builder) {
		builder.append("}\n");
	}
}
