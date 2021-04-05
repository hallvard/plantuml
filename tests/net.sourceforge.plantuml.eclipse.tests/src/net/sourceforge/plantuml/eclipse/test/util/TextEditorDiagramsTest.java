package net.sourceforge.plantuml.eclipse.test.util;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IEditorPart;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import net.sourceforge.plantuml.eclipse.views.AbstractDiagramSourceView;
import net.sourceforge.plantuml.eclipse.views.DiagramViewStatusListener;

public class TextEditorDiagramsTest extends AbstractWorkbenchTest {

	private static final String EXTENSION_POINT_PREFIX = "net.sourceforge.plantuml.eclipse.tests.";
	private static final String TEST_FILE_EXTENSION_POINT = EXTENSION_POINT_PREFIX + "testFile";
	private static final String TEST_PROJECT_EXTENSION_POINT = EXTENSION_POINT_PREFIX + "testProject";

	private AbstractDiagramSourceView diagramView;

	@Before
	public void setUpWorkbench() throws Exception {
		diagramView = openDiagramView(true);
	}

	private final Map<IFile, IConfigurationElement> testFiles = new HashMap<>();

	@Test
	public void testEditorDiagrams() {
		testFiles.clear();
		for (final IExtension extension : Platform.getExtensionRegistry().getExtensionPoint(TEST_PROJECT_EXTENSION_POINT).getExtensions()) {
			for (final IConfigurationElement ces: extension.getConfigurationElements()) {
				if ("testProject".equals(ces.getName())) {
					configureTestProject(ces);
				}
			}
		}
		for (final IExtension extension : Platform.getExtensionRegistry().getExtensionPoint(TEST_FILE_EXTENSION_POINT).getExtensions()) {
			for (final IConfigurationElement ces: extension.getConfigurationElements()) {
				if ("testFile".equals(ces.getName())) {
					final IFile file = createTestFile(ces);
					if (file != null) {
						testFiles.put(file, ces);
					}
				}
			}
		}
		waitForBuild();
		for (final IFile file : testFiles.keySet()) {
			final IConfigurationElement ces = testFiles.get(file);
			testEditorDiagram(file, ces);
		}
		testFiles.clear();
	}

	private void configureTestProject(final IConfigurationElement ces) {
		final String projectName = ces.getAttribute("projectName");
		IProject project = root().getProject(projectName);
		if (project == null || (! projectExists(projectName))) {
			try {
				project = createProject(projectName);

			} catch (final Exception e) {
				Assert.fail("Couldn't create " + projectName + " project" + ": " + e.getMessage());
			}
		}
		final String natures = ces.getAttribute("natures");
		if (natures != null) {
			try {
				addNatures(project, natures.split("[,]"));
			} catch (final CoreException e) {
				Assert.fail("Couldn't add " + natures + " natures to " + projectName + " project" + ": " + e.getMessage());
			}
		}
		final String configurerAttribute = ces.getAttribute("projectConfigurer");
		if (configurerAttribute != null) {
			try {
				final ProjectConfigurer projectConfigurer = (ProjectConfigurer) ces.createExecutableExtension("projectConfigurer");
				projectConfigurer.configureProject(project);
			} catch (final Exception e) {
				Assert.fail("Couldn't configure " + projectName + " project with " + configurerAttribute + ": " + e.getMessage());
			}
		}
	}

	protected IFile createTestFile(final IConfigurationElement ces) {
		final String fileUri = ces.getAttribute("sourceFileUri");
		final String fileName = fileUri.substring(fileUri.lastIndexOf('/') + 1);
		final Path targetPath = new Path(ces.getAttribute("targetPath"));
		if (! projectExists(targetPath.segment(0))) {
			Assert.fail("Missing " + targetPath.segment(0) + " project for " + targetPath + " target file");
		}
		try {
			return createFile(targetPath.append(fileName), new URL(fileUri).openStream());
		} catch (final Exception e) {
			Assert.fail("Couldn't create " + fileName + " in " + targetPath + ": " + e.getMessage());
		}
		return null;
	}

	private AbstractDiagramSourceView.ViewStatus expectedStatus = null;
	private AbstractDiagramSourceView.ViewStatus actualStatus = null;

	final DiagramViewStatusListener statusListener = (view, status, diagram) -> {
		if (view == diagramView) {
			//			System.out.println(status + " == " + expectedStatus + "?");
			//			Thread.dumpStack();
			if (actualStatus == null && (expectedStatus == null || status == expectedStatus)) {
				actualStatus = status;
			}
		}
	};

	protected void testEditorDiagram(final IFile file, final IConfigurationElement ces) {
		final String diagramTextUri = getDiagramTextUri(ces, ces.getAttribute("sourceFileUri"));
		try {
			diagramView.addDiagramViewListener(statusListener);
			this.expectedStatus = AbstractDiagramSourceView.ViewStatus.DIAGRAM_VIEW_DATA;
			this.actualStatus = null;
			final IEditorPart editor = openEditor(file, getEditorId(ces));
			if (ces.getAttribute("selectionsProvider") != null) {
				final SelectionIterable selections = (SelectionIterable) ces.createExecutableExtension("selectionsProvider");
				selections.init(editor, ces.getAttribute("selectionSpec"));
				final ISelectionProvider selectionProvider = editor.getSite().getSelectionProvider();
				int counter = 0;
				for (final ISelection selection : selections) {
					this.actualStatus = null;
					selectionProvider.setSelection(selection);
					diagramView.updateDiagramText();
					Assert.assertTrue("Timeout for diagram #" + (counter + 1) + " in " + file.getFullPath(), waitForDiagramView(5));
					final String diagramText = diagramView.getDiagramText();
					counter++;
					final int pos = diagramTextUri.lastIndexOf('.');
					final String diagramSelectionTextUri = diagramTextUri.substring(0, pos) + "." + counter + diagramTextUri.substring(pos);
					checkDiagramText(diagramSelectionTextUri, diagramText);
				};
			} else {
				Assert.assertTrue("Timeout for diagram in " + file.getFullPath(), waitForDiagramView(5));
				final String diagramText = diagramView.getDiagramText();
				checkDiagramText(diagramTextUri, diagramText);
			}
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail("Couldn't open " + file.getFullPath() + " in editor and test diagram: " + e);
		} finally {
			diagramView.removeDiagramViewListener(statusListener);
		}
	}

	private boolean waitForDiagramView(final float timeout) {
		float timeLeft = timeout;
		while (true) {
			try {
				Thread.sleep(500);
			} catch (final InterruptedException e) {
			}
			timeLeft -= 0.5;
			if (actualStatus != null) {
				return true;
			}
			if (timeLeft <= 0.0) {
				break;
			}
		}
		return false;
	}

	protected String getDiagramTextUri(final IConfigurationElement ces, final String fileUri) {
		final String diagramTextUriAttr = ces.getAttribute("diagramTextUri");
		final String diagramTextUri = (diagramTextUriAttr != null ? diagramTextUriAttr : fileUri + ".plantuml");
		return diagramTextUri;
	}

	protected String getEditorId(final IConfigurationElement ces) {
		return ces.getAttribute("editorId");
	}

	private void checkDiagramText(final String expected, final String actual) throws IOException {
		try (Scanner scanner = new Scanner(new URL(expected).openStream())) {
			final StringTokenizer tokenizer = new StringTokenizer(actual, "\n");
			int lineNum = 1;
			while (scanner.hasNextLine()) {
				Assert.assertTrue("Missing lines (>=" + lineNum + ") in actual diagram text of " + expected, tokenizer.hasMoreTokens());
				final String expectedLine = scanner.nextLine().trim();
				final String actualLine = tokenizer.nextToken().trim();
				Assert.assertEquals("Mismatch at line " + lineNum + " in actual diagram text of " + expected, expectedLine, actualLine);
				lineNum++;
			}
			Assert.assertFalse("Too many lines in actual diagram text of " + expected, tokenizer.hasMoreTokens());
		}
	}
}