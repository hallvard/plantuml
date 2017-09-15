package net.sourceforge.plantuml.text;

public abstract class AbstractObjectDiagramTextProvider extends AbstractDiagramTextProvider {

	public AbstractObjectDiagramTextProvider() {
	}
	public AbstractObjectDiagramTextProvider(Class<?> editorType) {
		super(editorType);
	}
	
	//

	public static int GEN_ATTRIBUTES = 1<<0, GEN_LINKS = 1<<1, GEN_OBJECT_HYPERLINKS = 1<<2;
	
	protected final static String OBJECT_TYPE = "object";

	protected final static String LINK_LINE = "--";
	
	protected final static String SIMPLE_LINK = LINK_LINE, AGGREGATION_LINK = "<>" + LINK_LINE;
	
	protected void appendObjectStart(String name, String id, String className, StringBuilder buffer) {
		appendObjectStart(name, id, className, null, buffer);
	}

	protected void appendObjectStart(String name, String id, String className, String color, StringBuilder buffer) {
		buffer.append(OBJECT_TYPE);
		buffer.append(" ");
		buffer.append("\"~#" + name + ": " + className + "\"");
		if (id != null) {
			buffer.append(" as " + id);
		}
//		appendNameDeclaration(name, buffer);
//		appendLink(link, false, buffer);
		if (color != null) {
			buffer.append(" #");
			buffer.append(color);
		}
		buffer.append(" {\n");
	}

	protected void appendObjectEnd(StringBuilder buffer) {
		buffer.append("}\n");
	}

	protected void appendAttribute(String name, String value, StringBuilder buffer) {
		buffer.append("\t");
		if (name != null) {
			buffer.append(name);
		}
		buffer.append(" = ");
		buffer.append(value);
		buffer.append("\n");
	}
}
