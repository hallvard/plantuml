package net.sourceforge.plantuml.text.test;

import org.junit.Test;

import net.sourceforge.plantuml.eclipse.test.util.AbstractDiagramTextTest;
import net.sourceforge.plantuml.text.TextEditorDiagramTextProvider;

public class TextEditorDiagramTextProviderTest {

	private TextEditorDiagramTextProvider diagramTextProvider = new TextEditorDiagramTextProvider();

	private String toto = TextEditorDiagramTextProvider.startuml + "\ntoto\n" + TextEditorDiagramTextProvider.enduml;
	
	private String getDiagramText(String s) {
		return diagramTextProvider.getDiagramText(s);
	}

	@Test
	public void testWithoutPrefix() {
		AbstractDiagramTextTest.assertDiagramText(toto, getDiagramText(toto));
	}

	@Test
	public void testWithWrongPrefix() {
		AbstractDiagramTextTest.assertDiagramText(TextEditorDiagramTextProvider.startuml + "\n\n" + TextEditorDiagramTextProvider.enduml, getDiagramText("toto" + toto));
	}

	@Test
	public void testWithSimplePrefix() {
		AbstractDiagramTextTest.assertDiagramText(toto, getDiagramText(" * " + TextEditorDiagramTextProvider.startuml + "\n * toto\n * " + TextEditorDiagramTextProvider.enduml + "\n"));
	}
	
	@Test
	public void testWithMixedWhitespacePrefix() {
		AbstractDiagramTextTest.assertDiagramText(toto, getDiagramText("  *\t" + TextEditorDiagramTextProvider.startuml + "\n\t*\ttoto\n * " + TextEditorDiagramTextProvider.enduml + "\n"));
	}
}
