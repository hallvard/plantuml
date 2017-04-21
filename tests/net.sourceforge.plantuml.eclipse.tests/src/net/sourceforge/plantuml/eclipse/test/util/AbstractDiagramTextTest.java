package net.sourceforge.plantuml.eclipse.test.util;

import org.junit.Assert;

public class AbstractDiagramTextTest extends AbstractWorkbenchTest {

	public static void assertDiagramText(String expected, String actual) {
		Assert.assertEquals(expected, actual);
	}
}
