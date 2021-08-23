package net.sourceforge.plantuml.text;

import java.util.Iterator;

import net.sourceforge.plantuml.eclipse.utils.DiagramIntentProperty;

public abstract class AbstractClassDiagramIntent<T> extends AbstractDiagramIntent<T> {

	public AbstractClassDiagramIntent(final T source) {
		super(source);
	}

	public AbstractClassDiagramIntent(final T source, final String label) {
		super(source, label);
	}

	//

	public static int GEN_MODIFIERS = 1<<0, GEN_MEMBERS = 1<<1, GEN_EXTENDS = 1<<2, GEN_IMPLEMENTS = 1<<3, GEN_ASSOCIATIONS = 1<<4, GEN_CLASS_HYPERLINKS = 1<<5;

	protected final static String CLASS_TYPE = "class", INTERFACE_TYPE = "interface", ENUM_TYPE = "enum";
	protected final static String ABSTRACT_MODIFIER = "abstract";

	protected final static String BIDIRECTIONAL_ASSOCIATION_RELATION = RELATION_LINE, ASSOCIATION_RELATION = RELATION_LINE + ">";
	protected final static String EXTENDS_RELATION = "<|" + RELATION_LINE, IMPLEMENTS_RELATION = "<|..";

	protected void appendClassStart(final String modifiers, final String classType, final String name, final String link, final StringBuilder buffer) {
		appendClassStart(modifiers, classType, name, link, null, buffer);
	}

	protected void appendNameDeclaration(final String label, final String logicalName, final StringBuilder buffer) {
		final boolean as = (logicalName != null && (! logicalName.equals(label)));
		if (as) {
			buffer.append("\"");
		}
		buffer.append(label);
		if (as) {
			buffer.append("\"");
			buffer.append(" as ");
			buffer.append(logicalName);
		}
	}

	protected void appendNameDeclaration(String name, final StringBuilder buffer) {
		if (name == null) {
			name = "?";
		}
		appendNameDeclaration(name, getLogicalName(name), buffer);
	}

	protected void appendClassStart(final String modifiers, final String classType, final String name, final String link, final String color, final StringBuilder buffer) {
		if (modifiers != null) {
			buffer.append(modifiers);
			buffer.append(" ");
		}
		buffer.append(classType);
		buffer.append(" ");
		appendNameDeclaration(name, buffer);
		appendLink(link, false, buffer);
		if (color != null) {
			buffer.append(" #");
			buffer.append(color);
		}
		buffer.append(" {\n");
	}

	protected void appendClassEnd(final StringBuilder buffer) {
		buffer.append("}\n");
	}

	private final static String CLASS_DIAGRAM__IS_JAVA_STYLE = "classDiagram.isJavaStyle";

	@DiagramIntentProperty(name = CLASS_DIAGRAM__IS_JAVA_STYLE, type = Boolean.class)
	protected boolean isJavaStyle() {
		return getIntentProperties().getProperty(CLASS_DIAGRAM__IS_JAVA_STYLE, Boolean.class, false);
	}

	private final static String CLASS_DIAGRAM__NAME_TYPE_SEPARATOR = "classDiagram.nameTypeSeparator";

	@DiagramIntentProperty(name = CLASS_DIAGRAM__NAME_TYPE_SEPARATOR)
	protected String getNameTypeSeparator() {
		return getIntentProperties().getProperty(CLASS_DIAGRAM__NAME_TYPE_SEPARATOR, String.class, ": ");
	}

	private void appendMember(final String modifiers, final String visibility, final String type, final String name, final Iterable<String> parameters, final StringBuilder buffer) {
		buffer.append("\t");
		if (visibility != null) {
			buffer.append(visibility);
		}
		if (modifiers != null) {
			buffer.append(modifiers);
			buffer.append(" ");
		}
		if (type != null && isJavaStyle()) {
			buffer.append(type);
			buffer.append(" ");
		}
		if (name != null) {
			buffer.append(name);
		}
		if (parameters != null) {
			buffer.append("(");
			final Iterator<String> it = parameters.iterator();
			while (it.hasNext()) {
				buffer.append(it.next());
				if (it.hasNext()) {
					buffer.append(", ");
				}
			}
			buffer.append(")");
		}
		if (type != null && (! isJavaStyle())) {
			buffer.append(getNameTypeSeparator());
			buffer.append(type);
		}
		buffer.append("\n");
	}

	protected void appendAttribute(final String modifiers, final String visibility, final String type, final String name, final StringBuilder buffer) {
		appendMember(modifiers, visibility, type, name, null, buffer);
	}

	protected void appendOperation(final String modifiers, final String visibility, final String type, final String name, final Iterable<String> parameters, final StringBuilder buffer) {
		appendMember(modifiers, visibility, type, name, parameters, buffer);
	}

	protected void appendGeneralisation(final String subClass, final String superClass, final boolean isImplements, final StringBuilder buffer) {
		buffer.append(getLogicalName(superClass));
		buffer.append(" ");
		buffer.append(isImplements ? IMPLEMENTS_RELATION : EXTENDS_RELATION);
		buffer.append(" ");
		buffer.append(getLogicalName(subClass));
		buffer.append("\n");
	}


	protected void appendAssociation(final String class1, final boolean cont1, final String role1, final int mult1, final String direction, final String class2, final boolean cont2, final String role2, final int mult2, final String name, final StringBuilder buffer) {
		final String relation = (role1 != null ? BIDIRECTIONAL_ASSOCIATION_RELATION : ASSOCIATION_RELATION);
		final String startLabel = getRoleLabel(role1, mult1);
		final String endLabel = getRoleLabel(role2, mult2);
		appendRelation(class1, cont1, startLabel, relation, direction, class2, cont2, endLabel, name, buffer);
	}

	private String getRoleLabel(final String role, final int mult) {
		String label = null;
		if (role != null) {
			label = role;
			if (mult != 0) {
				label += " " + (mult > 0 ? mult : "*");
			}
		}
		return label;
	}
}
