package net.sourceforge.plantuml.eclipse.utils;

import java.util.Iterator;
import java.util.StringTokenizer;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPathEditorInput;

public abstract class AbstractDiagramTextProvider implements DiagramTextProvider {

	private String fileExtensions = null;
	private Class<?> editorType = null;

	public AbstractDiagramTextProvider() {
		
	}
	public AbstractDiagramTextProvider(String fileExtensions, Class<?> editorType) {
		this();
		setFileExtensions(fileExtensions);
		setEditorType(editorType);
	}

	public void setFileExtensions(String fileExtensions) {
		this.fileExtensions = fileExtensions;
	}

	public void setEditorType(Class<?> editorType) {
		this.editorType = editorType;
	}

	public boolean supportsEditor(IEditorPart editorPart) {
		if (editorType != null && (! (editorType.isInstance(editorPart)))) {
			return false;
		}
		if (fileExtensions != null) {
			String fileExtension = null;
			if (fileExtension == null && editorPart.getEditorInput() instanceof IPathEditorInput) {
				fileExtension = ((IPathEditorInput) editorPart.getEditorInput()).getPath().getFileExtension();
			}
			boolean supportsFileExtension = false;
			StringTokenizer tokens = new StringTokenizer(fileExtensions, ",");
			while ((! supportsFileExtension) && tokens.hasMoreTokens()) {
				String ext = tokens.nextToken();
				if ("*".equals(ext) || ext.equals(fileExtension)) {
					supportsFileExtension = true;
				}
			}
			if (! supportsFileExtension) {
				return false;
			}
		}
		return true;
	}
	
	public String getDiagramText(IEditorPart editorPart, ISelection selection) {
		if (supportsEditor(editorPart) && (selection == null || supportsSelection(selection))) {
			String diagramText = getDiagramText(editorPart, editorPart.getEditorInput(), selection);
			if (diagramText != null) {
				diagramText = diagramText.trim();
				if (! diagramText.startsWith(TextEditorDiagramTextProvider.startuml)) {
					diagramText = TextEditorDiagramTextProvider.startuml + "\n" + diagramText;
				}
				if (! diagramText.endsWith(TextEditorDiagramTextProvider.enduml)) {
					diagramText = diagramText + "\n" + TextEditorDiagramTextProvider.enduml;
				}
			}
			return diagramText;
		}
		return null;
	}
	
	protected abstract String getDiagramText(IEditorPart editorPart, IEditorInput editorInput, ISelection selection);

	//
	
	protected static int GEN_MODIFIERS = 1<<0, GEN_MEMBERS = 1<<1, GEN_EXTENDS = 1<<2, GEN_IMPLEMENTS = 1<<3, GEN_ASSOCIATIONS = 1<<4;
	
	protected static boolean includes(int flags, int... bits) {
		for (int i = 0; i < bits.length; i++) {
			if ((flags & bits[i]) == 0) {
				return false;
			}
		}
		return true;
	}
	
	protected static String RELATION_LINE = "--";
	
	protected static String BIDIRECTIONAL_ASSOCIATION_RELATION = RELATION_LINE, ASSOCIATION_RELATION = RELATION_LINE + ">";
	protected static String EXTENDS_RELATION = "<|" + RELATION_LINE, IMPLEMENTS_RELATION = "<|..";

	protected void appendNameDeclaration(String name, StringBuilder buffer) {
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

	protected void appendClassStart(String modifiers, String classType, String name, StringBuilder buffer) {
		if (modifiers != null) {
			buffer.append(modifiers);
			buffer.append(" ");
		}
		buffer.append(classType);
		buffer.append(" ");
		appendNameDeclaration(name, buffer);
		buffer.append(" {\n");
	}

	protected void appendClassEnd(StringBuilder buffer) {
		buffer.append("}\n");
	}

	protected String getSimpleName(String name) {
		int pos = name != null ? name.lastIndexOf('.') : -1;
		return (pos >= 0 ? name.substring(pos + 1) : name);
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
	
	protected String getLogicalName(String name) {
		StringBuilder builder = null;
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (i == 0 ? Character.isJavaIdentifierStart(c) : Character.isJavaIdentifierPart(c)) {
				if (builder != null) {
					builder.append(c);
				}
			} else {
				if (builder == null) {
					builder = new StringBuilder(name.substring(0, i));
				}
				builder.append("_");
			}
		}
		return builder != null ? builder.toString() : name;
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
	
	protected void appendRelation(String start, boolean cont1, String startLabel, String relation, String direction, String end, boolean cont2, String endLabel, String label, StringBuilder buffer) {
		buffer.append(getLogicalName(start));
		if (startLabel != null) {
			buffer.append(" \"");
			buffer.append(startLabel);
			buffer.append("\"");
		}
		buffer.append(" ");
		if (cont1) {
			buffer.append("*");
		}
		// insert direction directive
		if (direction != null) {
			int pos = relation.indexOf(RELATION_LINE);
			if (pos >= 0) {
				pos += RELATION_LINE.length() / 2;
				relation.replace(RELATION_LINE, RELATION_LINE.substring(0, pos) + direction + RELATION_LINE.substring(pos));
			}
		}
		buffer.append(relation);
		if (cont2) {
			buffer.append("*");
		}
		if (endLabel != null) {
			buffer.append(" \"");
			buffer.append(endLabel);
			buffer.append("\"");
		}
		buffer.append(" ");
		buffer.append(getLogicalName(end));
		if (label != null) {
			buffer.append(" : ");
			buffer.append(label);
		}
		buffer.append("\n");
	}
}
