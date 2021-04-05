package net.sourceforge.plantuml.jdt.test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.launching.JavaRuntime;

import net.sourceforge.plantuml.eclipse.test.util.ProjectConfigurer;
import net.sourceforge.plantuml.eclipse.test.util.WorkspaceHelper;

public class JavaProjectConfigurer implements ProjectConfigurer {

	private final WorkspaceHelper workspaceHelper = new WorkspaceHelper();

	@Override
	public void configureProject(final IProject project) throws InvocationTargetException, InterruptedException, JavaModelException {
		final IJavaProject javaProject = JavaCore.create(project);

		final List<IClasspathEntry> entries = new ArrayList<IClasspathEntry>();
		entries.add(JavaCore.newContainerEntry(JavaRuntime.newDefaultJREContainerPath().append("org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType").append("JavaSE-1.8")));

		final IPackageFragmentRoot srcRoot = javaProject.getPackageFragmentRoot(workspaceHelper.createFolder(project, "src"));
		entries.add(JavaCore.newSourceEntry(srcRoot.getPath()));
		//add libs to project class path
		javaProject.setRawClasspath(entries.toArray(new IClasspathEntry[entries.size()]), null);
	}
}
