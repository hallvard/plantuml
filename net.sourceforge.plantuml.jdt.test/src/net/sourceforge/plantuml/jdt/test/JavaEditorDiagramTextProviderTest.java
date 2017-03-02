package net.sourceforge.plantuml.jdt.test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.launching.JavaRuntime;
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
		project = createProject("javaeditortest", JavaCore.NATURE_ID);
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
		PlantUmlView view = openPlantUMLView();
		AbstractDiagramTextTest.assertDiagramText(expected, view.getDiagramText());
	}
	
	private void testJavaEditorClassDiagramText(String fullClassName, String... members) throws Exception, Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("@startuml\nclass ");
		int pos = fullClassName.lastIndexOf('.');
		builder.append(pos < 0 ? fullClassName : fullClassName.substring(pos + 1));
		builder.append(" {\n");
		for (String member : members) {
			builder.append("\t");
			builder.append(member);
			builder.append("\n");
		}
		builder.append("}\n@enduml");
		testJavaEditorDiagramText(fullClassName, builder.toString());
	}
	private void testJavaEditorClassDiagramText(Class<?> clazz, String... members) throws Exception, Exception {
		testJavaEditorClassDiagramText(clazz.getName(), members);
	}

	@Test
	public void testDiagramForClassWithoutMembers() throws Exception, Exception {
		testJavaEditorClassDiagramText(ClassWithoutMembers.class);
	}
	
	// TODO test other variants of class members
}
