package net.sourceforge.plantuml.jdt.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.LibraryLocation;
import org.junit.Before;
import org.junit.Test;

import net.sourceforge.plantuml.eclipse.test.util.AbstractDiagramTextTest;
import net.sourceforge.plantuml.eclipse.views.PlantUmlView;

public class JavaEditorDiagramTextProviderTest extends AbstractDiagramTextTest {

	private IProject project;
	private IJavaProject javaProject;
	private IPackageFragmentRoot srcRoot;
	
	@Before
	public void setUpJavaProject() throws Exception {
		project = createProject("javaeditortest");
		addNature(project, JavaCore.NATURE_ID);
		javaProject = JavaCore.create(project);

		List<IClasspathEntry> entries = new ArrayList<IClasspathEntry>();
		entries.add(JavaCore.newContainerEntry(JavaRuntime.newDefaultJREContainerPath().append("org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType").append("JavaSE-1.6")));

		srcRoot = javaProject.getPackageFragmentRoot(createFolder(project, "src"));
		entries.add(JavaCore.newSourceEntry(srcRoot.getPath()));
		//add libs to project class path
		javaProject.setRawClasspath(entries.toArray(new IClasspathEntry[entries.size()]), null);
	}
	
	private ICompilationUnit createJavaSource(String sourceClassName) throws JavaModelException, IOException {
		int pos = sourceClassName.lastIndexOf('.');
		String packName = sourceClassName.substring(0,  pos), simpleName = sourceClassName.substring(pos + 1);
		IPackageFragment pack = srcRoot.createPackageFragment(packName, false, null);
		StringBuilder buffer = new StringBuilder();
		URL url = new URL("platform:/plugin/net.sourceforge.plantuml.jdt.test/src/" + sourceClassName.replace('.', '/') + ".java");
		System.out.println(sourceClassName + " @ " + url);
		Scanner scanner = new Scanner(url.openStream());
		while (scanner.hasNextLine()) {
			buffer.append(scanner.nextLine());
			buffer.append("\n");
		}
		ICompilationUnit cu = pack.createCompilationUnit(simpleName + ".java", buffer.toString(), false, null);
		return cu;
	}

	private void testJavaEditorDiagramText(String sourceClassName, String expected) throws Exception, Exception {
		ICompilationUnit cu = createJavaSource(sourceClassName);
		waitForBuild();
		openEditor((IFile) cu.getResource(), null);
		PlantUmlView view = openView("net.sourceforge.plantuml.eclipse.views.PlantUmlView", PlantUmlView.class);
		AbstractDiagramTextTest.assertDiagramText(expected, view.getDiagramText());
	}

	@Test
	public void testJavaEditorDiagramFromComment() throws Exception, Exception {
		testJavaEditorDiagramText(ClassWithDiagramInComment.class.getName(), "@startuml\nclass ClassWithDiagramInComment\n{\n}\n@enduml");
	}
	
	@Test
	public void testJavaEditorDiagramFromJavaModel() throws Exception, Exception {
		testJavaEditorDiagramText(ClassWithoutDiagram.class.getName(), "@startuml\nclass ClassWithoutDiagram {\n}\n@enduml");
	}
}
