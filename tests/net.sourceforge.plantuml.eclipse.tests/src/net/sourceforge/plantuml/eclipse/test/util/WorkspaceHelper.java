package net.sourceforge.plantuml.eclipse.test.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

public class WorkspaceHelper {

	public IWorkspaceRoot root() {
		return ResourcesPlugin.getWorkspace().getRoot();
	}

	public boolean projectExists(final String name) {
		return root().getProject(name).exists();
	}

	public IProject createProject(final String name, final String... natures) throws CoreException, InvocationTargetException, InterruptedException {
		final IProject project = root().getProject(name);
		createProject(project);
		addNatures(project, natures);
		return project;
	}

	public IProject createProject(final IProject project) throws CoreException {
		if (! project.exists()) {
			project.create(getDefaultMonitor());
		}
		project.open(getDefaultMonitor());
		return project;
	}

	public void addNatures(final IProject project, final String... natures) throws CoreException {
		final IProjectDescription description = project.getDescription();
		final String[] existingNatures = description.getNatureIds();

		// Add the nature
		final String[] newNatures = new String[existingNatures.length + natures.length];
		System.arraycopy(existingNatures, 0, newNatures, 0, existingNatures.length);
		System.arraycopy(natures, 0, newNatures, existingNatures.length, natures.length);
		description.setNatureIds(newNatures);
		project.setDescription(description, null);
	}

	public void addBuilder(final IProject project, final String builderId) throws CoreException {
		final IProjectDescription description = project.getDescription();
		final ICommand[] specs = description.getBuildSpec();
		final ICommand command = description.newCommand();
		command.setBuilderName(builderId);
		// Add the nature
		final ICommand[] specsModified = new ICommand[specs.length + 1];
		System.arraycopy(specs, 0, specsModified, 0, specs.length);
		specsModified[specs.length] = command;
		description.setBuildSpec(specsModified);
		project.setDescription(description, getDefaultMonitor());
	}

	protected void removeNature(final IProject project, final String nature)
			throws CoreException {
		final IProjectDescription description = project.getDescription();
		final String[] natures = description.getNatureIds();

		for (int i = 0; i < natures.length; ++i) {
			if (nature.equals(natures[i])) {
				// Remove the nature
				final String[] newNatures = new String[natures.length - 1];
				System.arraycopy(natures, 0, newNatures, 0, i);
				System.arraycopy(natures, i + 1, newNatures, i, natures.length
						- i - 1);
				description.setNatureIds(newNatures);
				project.setDescription(description, null);
				return;
			}
		}

	}

	protected void removeBuilder(final IProject project, final String builderId) throws CoreException {
		final IProjectDescription description = project.getDescription();
		final ICommand[] builderSpecs = description.getBuildSpec();

		for (int i = 0; i < builderSpecs.length; ++i) {
			if (builderId.equals(builderSpecs[i].getBuilderName())) {
				// Remove the builder
				final ICommand[] modifiedSpecs = new ICommand[builderSpecs.length - 1];
				System.arraycopy(builderSpecs, 0, modifiedSpecs, 0, i);
				System.arraycopy(builderSpecs, i + 1, modifiedSpecs, i, builderSpecs.length - i - 1);
				description.setBuildSpec(modifiedSpecs);
				project.setDescription(description, null);
				return;
			}
		}
	}

	public IFolder createFolder(final IResource resource, final String relativePath) throws InvocationTargetException, InterruptedException {
		return createFolder(resource.getFullPath().append(relativePath));
	}
	public IFolder createFolder(final String wsRelativePath) throws InvocationTargetException, InterruptedException {
		return createFolder(new Path(wsRelativePath));
	}
	public IFolder createFolder(final IPath wsRelativePath) throws InvocationTargetException, InterruptedException {
		final IFolder folder = root().getFolder(wsRelativePath);
		new WorkspaceModifyOperation() {

			@Override
			protected void execute(final IProgressMonitor monitor)
					throws CoreException, InvocationTargetException,
					InterruptedException {
				create(folder.getParent());
				folder.delete(true, getDefaultMonitor());
				folder.create(true, true, getDefaultMonitor());
			}

		}.run(getDefaultMonitor());
		return folder;
	}

	public IFile createFile(final IResource resource, final String relativePath, final String s) throws CoreException, InvocationTargetException, InterruptedException {
		return createFile(resource.getFullPath().append(relativePath), s);
	}
	public IFile createFile(final String wsRelativePath, final String s) throws CoreException, InvocationTargetException, InterruptedException {
		return createFile(new Path(wsRelativePath), s);
	}
	public IFile createFile(final IPath wsRelativePath, final String s) throws CoreException, InvocationTargetException, InterruptedException {
		return createFile(wsRelativePath, new StringBufferInputStream(s));
	}

	public IFile createFile(final IPath wsRelativePath, final InputStream contents)
			throws CoreException, InvocationTargetException,
			InterruptedException {
		final IFile file = root().getFile(wsRelativePath);
		new WorkspaceModifyOperation() {

			@Override
			protected void execute(final IProgressMonitor monitor)
					throws CoreException, InvocationTargetException,
					InterruptedException {
				create(file.getParent());
				file.delete(true, getDefaultMonitor());
				file.create(contents, true, getDefaultMonitor());
			}

		}.run(getDefaultMonitor());
		return file;
	}

	protected InputStream getPluginTestFileContents(final String pluginProject, String path) throws IOException {
		if (! path.startsWith("/")) {
			path = "/" + path;
		}
		final URL url = new URL("platform:/plugin/" + pluginProject + path);
		return url.openStream();
	}

	public IResource file(final String path) {
		return root().findMember(new Path(path));
	}

	private void create(final IContainer container)
			throws CoreException, InvocationTargetException,
			InterruptedException {
		new WorkspaceModifyOperation() {
			@Override
			protected void execute(final IProgressMonitor monitor)
					throws CoreException, InvocationTargetException,
					InterruptedException {
				if (!container.exists()) {
					create(container.getParent());
					if (container instanceof IFolder) {
						((IFolder) container).create(true, true, getDefaultMonitor());
					} else {
						final IProject iProject = (IProject) container;
						createProject(iProject);
					}
				}
			}
		}.run(getDefaultMonitor());
	}

	public IProgressMonitor getDefaultMonitor() {
		return new NullProgressMonitor();
	}

	protected void fullBuild() throws CoreException {
		ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.FULL_BUILD, getDefaultMonitor());
	}

	protected void cleanBuild() throws CoreException {
		ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.CLEAN_BUILD, getDefaultMonitor());
	}
}
