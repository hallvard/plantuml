package net.sourceforge.plantuml.text;

import java.util.Map;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

import net.sourceforge.plantuml.eclipse.utils.DiagramTextProvider;
import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;

public abstract class AbstractDiagramTextProvider implements DiagramTextProvider {

	private Class<?> editorType = null;

	public AbstractDiagramTextProvider() {
		
	}
	public AbstractDiagramTextProvider(Class<?> editorType) {
		this();
		setEditorType(editorType);
	}

	public void setEditorType(Class<?> editorType) {
		this.editorType = editorType;
	}

	public boolean supportsEditor(IEditorPart editorPart) {
		if (editorType != null && (! (editorType.isInstance(editorPart)))) {
			return false;
		}
		return true;
	}
	
	public String getDiagramText(IEditorPart editorPart, ISelection selection) {
		if (supportsEditor(editorPart) && (selection == null || supportsSelection(selection))) {
			String diagramText = getDiagramText(editorPart, editorPart.getEditorInput(), selection);
			if (diagramText != null) {
				diagramText = diagramText.trim();
				if (! diagramText.startsWith(PlantumlConstants.START_UML)) {
					diagramText = PlantumlConstants.START_UML + "\n" + diagramText;
				}
				if (! diagramText.endsWith(PlantumlConstants.END_UML)) {
					diagramText = diagramText + "\n" + PlantumlConstants.END_UML;
				}
			}
			return diagramText;
		}
		return null;
	}
	
	protected abstract String getDiagramText(IEditorPart editorPart, IEditorInput editorInput, ISelection selection);

	//
	
	protected static boolean includes(int flags, int... bits) {
		for (int i = 0; i < bits.length; i++) {
			if ((flags & bits[i]) == 0) {
				return false;
			}
		}
		return true;
	}

	protected void appendSkinParams(Map<String, String> skinParams, StringBuilder buffer) {
		appendSkinParams(null, skinParams, buffer);
	}

	protected void appendSkinParams(String qualifier, Map<String, String> skinParams, StringBuilder buffer) {
		if (qualifier != null) {
			buffer.append("skinparam");
			buffer.append(" ");
			buffer.append(qualifier);
			buffer.append(" {\n");
		}
		for (String param : skinParams.keySet()) {
			if (qualifier != null) {
				buffer.append("\t");
				if (param.startsWith(qualifier)) {
					param = param.substring(qualifier.length());
				}
			} else {
				buffer.append("skinparam ");
			}
			buffer.append(param);
			buffer.append(" ");
			buffer.append(skinParams.get(param));
			buffer.append("\n");
		}
		if (qualifier != null) {
			buffer.append("}\n");
		}
	}

	protected void appendLink(String link, boolean isMember, StringBuilder result) {
		if (link != null) {
			result.append(" [[");
			if (isMember) {
				result.append("[");				
			}
			result.append(link);
			if (isMember) {
				result.append("]");				
			}
			result.append("]]");
		}
	}

	protected String getSimpleName(String name) {
		int pos = name != null ? name.lastIndexOf('.') : -1;
		return (pos >= 0 ? name.substring(pos + 1) : name);
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

	protected final static String RELATION_LINE = "--";

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
				pos = RELATION_LINE.length() / 2;
				relation = relation.replace(RELATION_LINE, RELATION_LINE.substring(0, pos) + direction + RELATION_LINE.substring(pos));
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
	
	protected void appendNameLink(String name, String link, StringBuilder buffer) {
		if (link != null) {
			buffer.append("url of ");
			buffer.append(name);
			buffer.append(" is ");
			appendLink(link, false, buffer);
			buffer.append("\n");
		}
	}
}
