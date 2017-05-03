package net.sourceforge.plantuml.text;

public abstract class AbstractObjectDiagramTextProvider extends AbstractDiagramTextProvider {

	public AbstractObjectDiagramTextProvider() {
		
	}
	public AbstractObjectDiagramTextProvider(Class<?> editorType) {
		super(editorType);
	}
	
	//

	public static int GEN_ATTRIBUTES = 1<<0, GEN_LINKS = 1<<1;
	
	protected final static String OBJECT_TYPE = "object";

	protected final static String LINK_LINE = "--";
	
	protected final static String SIMPLE_LINK = LINK_LINE, AGGREGATION_LINK = "<>" + LINK_LINE;
	
	protected void appendObjectStart(String name, String className, String link, StringBuilder buffer) {
		appendObjectStart(name, className, null, link, buffer);
	}

	protected void appendObjectStart(String name, String className, String color, String link, StringBuilder buffer) {
		buffer.append(OBJECT_TYPE);
		buffer.append(" ");
		appendNameDeclaration(name, buffer);
		appendLink(link, false, buffer);
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

	protected void appendLink(String object1, String name, boolean cont, String object2, StringBuilder buffer) {
		String relation = SIMPLE_LINK;
		appendRelation(object1, cont, null, relation, null, object2, false, null, name, buffer);
	}
}
