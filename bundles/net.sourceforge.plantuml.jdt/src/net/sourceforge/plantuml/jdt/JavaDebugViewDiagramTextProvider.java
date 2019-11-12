package net.sourceforge.plantuml.jdt;

import java.util.Collections;
import java.util.Iterator;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.jdt.debug.core.IJavaStackFrame;
import org.eclipse.jdt.debug.core.IJavaType;
import org.eclipse.jdt.debug.core.IJavaValue;
import org.eclipse.jdt.debug.core.IJavaVariable;

public class JavaDebugViewDiagramTextProvider extends DebugViewDiagramTextProvider {

	@Override
	protected void appendStackFrameName(final IStackFrame stackFrame, final StringBuilder builder) throws DebugException {
		if (stackFrame instanceof IJavaStackFrame) {
			final IJavaStackFrame javaStackFrame = (IJavaStackFrame) stackFrame;
			if (javaStackFrame.isStatic()) {
				appendType(javaStackFrame.getDeclaringTypeName(), builder);
				builder.append(".");
			}
			builder.append(javaStackFrame.getMethodName());
			builder.append("(");
			final int pos = builder.length();
			for (final String argType : javaStackFrame.getArgumentTypeNames()) {
				if (builder.length() > pos) {
					builder.append(", ");
				}
				appendType(argType, builder);
			}
			builder.append(")");
		} else {
			super.appendStackFrameName(stackFrame, builder);
		}
	}

	@Override
	protected void appendDiagramVariable(final IVariable variable, final StringBuilder builder) throws DebugException {
		if (! "this".equals(variable.getName())) {
			super.appendDiagramVariable(variable, builder);
		}
	}

	@Override
	protected void appendType(final String type, final StringBuilder builder) {
		final int pos = type.lastIndexOf(".");
		builder.append(pos > 0 ? type.substring(pos + 1) : type);
	}

	//

	@Override
	protected boolean shouldIncludeVariable(final IStackFrame context, final IValue value, final IVariable variable) {
		try {
			if (variable instanceof IJavaVariable) {
				final IJavaVariable javaVariable = (IJavaVariable) variable;
				if (javaVariable.isStatic() || javaVariable.isSynthetic()) {
					return false;
				}
				if (context instanceof IJavaStackFrame && value instanceof IJavaValue) {
					if (! isVisible((IJavaStackFrame) context, value, javaVariable)) {
						return false;
					}
				}
			}
		} catch (final DebugException e) {
		}
		return super.shouldIncludeVariable(context, value, variable);
	}

	protected boolean isVisible(final IJavaStackFrame context, final IValue value, final IJavaVariable childVariable) {
		try {
			return childVariable.isPublic();
		} catch (final DebugException e) {
			return false;
		}
	}

	@Override
	protected Iterator<IValue> getVariableElements(final IVariable variable) {
		if (variable instanceof IJavaVariable) {
			final IJavaVariable javaVariable = (IJavaVariable) variable;
			try {
				final IJavaType type = javaVariable.getJavaType();
				if (isValueType(type)) {
					return super.getVariableElements(variable);
				}
				return getVariableElements(variable.getValue());
			} catch (final DebugException e) {
			}
		}
		return super.getVariableElements(variable);
	}

	protected Iterator<IValue> getVariableElements(final IValue value) {
		try {
			final String type = value.getReferenceTypeName();
			if (type.endsWith("[]")) {
				// TODO: array type
			} else if (type.startsWith("java.util.List") || type.startsWith("java.util.Collection") || type.startsWith("java.util.Iterable")) {
				// TODO: iterable
			}
		} catch (final DebugException e) {
		}
		return Collections.singletonList(value).iterator();
	}

	private boolean isValueType(final IJavaType type) {
		try {
			final String name = type.getName();
			return name.indexOf('.') < 0 || name.startsWith("java.lang.");
		} catch (final DebugException e) {
			return true;
		}
	}
}
