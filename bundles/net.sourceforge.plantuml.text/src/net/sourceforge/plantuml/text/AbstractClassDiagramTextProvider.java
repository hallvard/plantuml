package net.sourceforge.plantuml.text;

import java.util.Iterator;

public abstract class AbstractClassDiagramTextProvider extends AbstractDiagramTextProvider {

	public AbstractClassDiagramTextProvider() {
		
	}
	public AbstractClassDiagramTextProvider(Class<?> editorType) {
		super(editorType);
	}
	
	//

	public static int GEN_MODIFIERS = 1<<0, GEN_MEMBERS = 1<<1, GEN_EXTENDS = 1<<2, GEN_IMPLEMENTS = 1<<3, GEN_ASSOCIATIONS = 1<<4, GEN_CLASS_HYPERLINKS = 1<<5;
	
	protected final static String CLASS_TYPE = "class", INTERFACE_TYPE = "interface", ENUM_TYPE = "enum";

	protected final static String BIDIRECTIONAL_ASSOCIATION_RELATION = RELATION_LINE, ASSOCIATION_RELATION = RELATION_LINE + ">";
	protected final static String EXTENDS_RELATION = "<|" + RELATION_LINE, IMPLEMENTS_RELATION = "<|..";
	
	protected void appendClassStart(String modifiers, String classType, String name, String link, StringBuilder buffer) {
		appendClassStart(modifiers, classType, name, link, null, buffer);
	}

	protected void appendNameDeclaration(String name, StringBuilder buffer) {
		if (name == null) {
			name = "?";
		}
		String logicalName = getLogicalName(name);
		if (name.equals(logicalName)) {
			buffer.append(name);
		} else {
			buffer.append("\"");
			buffer.append(name);
			buffer.append("\"");
			buffer.append(" as ");
			buffer.append(logicalName);
		}
	}

	protected void appendClassStart(String modifiers, String classType, String name, String link, String color, StringBuilder buffer) {
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

	protected void appendClassEnd(StringBuilder buffer) {
		buffer.append("}\n");
	}

	private void appendMember(String modifiers, String visibility, String type, String name, Iterable<String> parameters, StringBuilder buffer) {
		buffer.append("\t");
		if (visibility != null) {
			buffer.append(visibility);
		}
		if (modifiers != null) {
			buffer.append(modifiers);
			buffer.append(" ");
		}
		if (type != null) {
			buffer.append(type);
			buffer.append(" ");
		}
		if (name != null) {
			buffer.append(name);
		}
		if (parameters != null) {
			buffer.append("(");
			Iterator<String> it = parameters.iterator();
			while (it.hasNext()) {
				buffer.append(it.next());
				if (it.hasNext()) {
					buffer.append(", ");
				}
			}
			buffer.append(")");
		}
		buffer.append("\n");
	}
	
	protected void appendAttribute(String modifiers, String visibility, String type, String name, StringBuilder buffer) {
		appendMember(modifiers, visibility, type, name, null, buffer);
	}

	protected void appendOperation(String modifiers, String visibility, String type, String name, Iterable<String> parameters, StringBuilder buffer) {
		appendMember(modifiers, visibility, type, name, parameters, buffer);
	}
	
	protected void appendGeneralisation(String subClass, String superClass, boolean isImplements, StringBuilder buffer) {
		buffer.append(getLogicalName(superClass));
		buffer.append(" ");
		buffer.append(isImplements ? IMPLEMENTS_RELATION : EXTENDS_RELATION);
		buffer.append(" ");
		buffer.append(getLogicalName(subClass));
		buffer.append("\n");
	}
	

	protected void appendAssociation(String class1, boolean cont1, String role1, int mult1, String direction, String class2, boolean cont2, String role2, int mult2, String name, StringBuilder buffer) {
		String relation = (role1 != null ? BIDIRECTIONAL_ASSOCIATION_RELATION : ASSOCIATION_RELATION);
		String startLabel = getRoleLabel(role1, mult1);
		String endLabel = getRoleLabel(role2, mult2);
		appendRelation(class1, cont1, startLabel, relation, direction, class2, cont2, endLabel, name, buffer);
	}

	private String getRoleLabel(String role, int mult) {
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
