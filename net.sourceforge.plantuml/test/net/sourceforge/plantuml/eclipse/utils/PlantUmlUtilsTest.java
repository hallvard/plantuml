package net.sourceforge.plantuml.eclipse.utils;

import net.sourceforge.plantuml.eclipse.model.Diagram;
import junit.framework.TestCase;

public class PlantUmlUtilsTest extends TestCase {

	Diagram diagram = new Diagram();
	
	public void testExtractTextDiagram() {
		String s = "@startuml\ntoto\n@enduml";
		assertEquals(s, diagram.extractTextDiagram(11, s));
	}

	public void testExtractTextDiagram01() {
		String s = "toto@startuml\ntoto\n@enduml\n";
		assertEquals(null, diagram.extractTextDiagram(16, s));
	}

	public void testExtractTextDiagram02() {
		String s = "@startuml\ntoto\n@enduml\ntootottootfgkjfgjk";
		assertEquals("@startuml\ntoto\n@enduml", diagram
				.extractTextDiagram(11, s));
	}

	public void testExtractTextDiagram03() {
		String s = "123456789\n@startuml\ntoto\n@enduml\ntootottootfgkjfgjk";
		assertEquals("@startuml\ntoto\n@enduml", diagram
				.extractTextDiagram(11 + 9, s));
	}

	public void testExtractTextDiagram04() {
		String s = "123456789\n@startuml\ntoto\n@enduml\ntootottootfgkjfgjk\n@startuml\ntata\n@enduml";
		assertEquals("@startuml\ntoto\n@enduml", diagram
				.extractTextDiagram(11 + 9, s));
	}

	public void testExtractTextDiagram05() {
		String s = "123456789\n@startuml\ntoto\n@enduml\ntootottootfgkjfgjk\n@startuml\ntata\n@enduml";
		assertEquals("@startuml\ntata\n@enduml", diagram
				.extractTextDiagram(s.length() - 9, s));
	}

	public void testExtractTextDiagram06() {
		String s = "123456789\n\t/* @startuml\ntoto\n  * @enduml\ntootottootfgkjfgjk\n *   @startuml\ntata\n@enduml";
		assertEquals("@startuml\ntata\n@enduml", diagram
				.extractTextDiagram(s.length() - 9, s));
	}

	public void testExtractTextDiagram07() {
		String s = "123456789\n /* @startuml\ntoto\n  * @enduml\ntootottootfgkjfgjk\n *   @startuml\ntata\n@enduml";
		assertEquals("@startuml\ntoto\n@enduml", diagram
				.extractTextDiagram("123456789\n /* @startuml\n".length() + 2,
						s));
	}

	public void testExtractTextDiagram08() {
		String s = "123456789\n /* @startuml\n * toto\n  * @enduml\ntootottootfgkjfgjk\n *   @startuml\ntata\n@enduml";
		assertEquals("@startuml\ntoto\n@enduml", diagram
				.extractTextDiagram("123456789\n /* @startuml\n".length() + 2,
						s));
	}
	
	public void testExtractTextDiagram09() {
		String s = "123456789\n /* @startuml\n /* toto\n  * @enduml\ntootottootfgkjfgjk\n *   @startuml\ntata\n@enduml";
		assertEquals("@startuml\ntoto\n@enduml", diagram
				.extractTextDiagram("123456789\n /* @startuml\n".length() + 2,
						s));
	}

}
