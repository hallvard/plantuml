package net.sourceforge.plantuml.text.test;

import org.junit.Test;

import net.sourceforge.plantuml.eclipse.test.util.AbstractDiagramTextTest;
import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.text.TextEditorDiagramIntentProvider;

public class TextEditorDiagramTextProviderTest {

	private final TextEditorDiagramIntentProvider diagramTextProvider = new TextEditorDiagramIntentProvider(PlantumlConstants.START_UML, PlantumlConstants.END_UML);

	private final String toto = PlantumlConstants.START_UML + "\ntoto\n" + PlantumlConstants.END_UML;

	private String getDiagramText(final String s) {
		return diagramTextProvider.getDiagramText(s);
	}

	@Test
	public void testWithoutPrefix() {
		AbstractDiagramTextTest.assertDiagramText(toto, getDiagramText(toto));
	}

	@Test
	public void testWithWrongPrefix() {
		AbstractDiagramTextTest.assertDiagramText(PlantumlConstants.START_UML + "\n\n" + PlantumlConstants.END_UML, getDiagramText("toto" + toto));
	}

	@Test
	public void testWithSimplePrefix() {
		AbstractDiagramTextTest.assertDiagramText(toto, getDiagramText(" * " + PlantumlConstants.START_UML + "\n * toto\n * " + PlantumlConstants.END_UML + "\n"));
	}

	@Test
	public void testWithMixedWhitespacePrefix() {
		AbstractDiagramTextTest.assertDiagramText(toto, getDiagramText("  *\t" + PlantumlConstants.START_UML + "\n\t*\ttoto\n * " + PlantumlConstants.END_UML + "\n"));
	}
}
