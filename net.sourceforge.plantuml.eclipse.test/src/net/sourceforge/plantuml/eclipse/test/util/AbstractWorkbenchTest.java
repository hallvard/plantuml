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
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.intro.IIntroPart;
import org.eclipse.ui.part.FileEditorInput;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import net.sourceforge.plantuml.eclipse.views.PlantUmlView;

public abstract class AbstractWorkbenchTest {

	protected IWorkspaceRoot root() {
		return ResourcesPlugin.getWorkspace().getRoot();
	}

	protected IProject createProject(String name, String... natures) throws CoreException, InvocationTargetException, InterruptedException {
		IProject project = root().getProject(name);
		createProject(project);
		addNatures(project, natures);			
		return project;
	}

	protected IProject createProject(IProject project) throws CoreException {
		if (!project.exists())
			project.create(getDefaultMonitor());
		project.open(getDefaultMonitor());
		return project;
	}

	protected void addNatures(IProject project, String... natures) throws CoreException {
		IProjectDescription description = project.getDescription();
		String[] existingNatures = description.getNatureIds();

		// Add the nature
		String[] newNatures = new String[existingNatures.length + natures.length];
		System.arraycopy(existingNatures, 0, newNatures, 0, existingNatures.length);
		System.arraycopy(natures, 0, newNatures, existingNatures.length, natures.length);
		description.setNatureIds(newNatures);
		project.setDescription(description, null);
	}
	
	protected void addBuilder(IProject project, String builderId) throws CoreException {
		IProjectDescription description = project.getDescription();
		ICommand[] specs = description.getBuildSpec();
		ICommand command = description.newCommand();
		command.setBuilderName(builderId);
		// Add the nature
		ICommand[] specsModified = new ICommand[specs.length + 1];
		System.arraycopy(specs, 0, specsModified, 0, specs.length);
		specsModified[specs.length] = command;
		description.setBuildSpec(specsModified);
		project.setDescription(description, getDefaultMonitor());
	}

	protected void removeNature(IProject project, String nature)
			throws CoreException {
		IProjectDescription description = project.getDescription();
		String[] natures = description.getNatureIds();

		for (int i = 0; i < natures.length; ++i) {
			if (nature.equals(natures[i])) {
				// Remove the nature
				String[] newNatures = new String[natures.length - 1];
				System.arraycopy(natures, 0, newNatures, 0, i);
				System.arraycopy(natures, i + 1, newNatures, i, natures.length
						- i - 1);
				description.setNatureIds(newNatures);
				project.setDescription(description, null);
				return;
			}
		}

	}
	
	protected void removeBuilder(IProject project, String builderId) throws CoreException {
		IProjectDescription description = project.getDescription();
		ICommand[] builderSpecs = description.getBuildSpec();

		for (int i = 0; i < builderSpecs.length; ++i) {
			if (builderId.equals(builderSpecs[i].getBuilderName())) {
				// Remove the builder
				ICommand[] modifiedSpecs = new ICommand[builderSpecs.length - 1];
				System.arraycopy(builderSpecs, 0, modifiedSpecs, 0, i);
				System.arraycopy(builderSpecs, i + 1, modifiedSpecs, i, builderSpecs.length - i - 1);
				description.setBuildSpec(modifiedSpecs);
				project.setDescription(description, null);
				return;
			}
		}

	}

	protected void setReference(final IProject from, final IProject to)
			throws CoreException, InvocationTargetException,
			InterruptedException {
		new WorkspaceModifyOperation() {

			@Override
			protected void execute(IProgressMonitor monitor)
					throws CoreException, InvocationTargetException,
					InterruptedException {
				IProjectDescription projectDescription = from.getDescription();
				IProject[] projects = projectDescription
						.getReferencedProjects();
				IProject[] newProjects = new IProject[projects.length + 1];
				System.arraycopy(projects, 0, newProjects, 0, projects.length);
				newProjects[projects.length] = to;
				projectDescription.setReferencedProjects(newProjects);
				from.setDescription(projectDescription, getDefaultMonitor());
			}
		}.run(getDefaultMonitor());
	}
	
	protected void removeReference(final IProject from, final IProject to)
	throws CoreException, InvocationTargetException,
	InterruptedException {
		new WorkspaceModifyOperation() {
			
			@Override
			protected void execute(IProgressMonitor monitor)
			throws CoreException, InvocationTargetException,
			InterruptedException {
				IProjectDescription projectDescription = from.getDescription();
				IProject[] projects = projectDescription
				.getReferencedProjects();
				for (int i = 0; i < projects.length; ++i) {
					if (to.equals(projects[i])) {
						// Remove the nature
						IProject[] newProjects = new IProject[projects.length - 1];
						System.arraycopy(projects, 0, newProjects, 0, i);
						System.arraycopy(projects, i + 1, newProjects, i, projects.length
								- i - 1);
						projectDescription.setReferencedProjects(newProjects);
						from.setDescription(projectDescription, null);
						return;
					}
				}
			}
		}.run(getDefaultMonitor());
	}

	protected IFolder createFolder(IResource resource, String relativePath) throws InvocationTargetException, InterruptedException {
		return createFolder(resource.getFullPath().append(relativePath));
	}
	protected IFolder createFolder(String wsRelativePath) throws InvocationTargetException, InterruptedException {
		return createFolder(new Path(wsRelativePath));
	}
	protected IFolder createFolder(IPath wsRelativePath) throws InvocationTargetException, InterruptedException {
		final IFolder folder = root().getFolder(wsRelativePath);
		new WorkspaceModifyOperation() {

			@Override
			protected void execute(IProgressMonitor monitor)
					throws CoreException, InvocationTargetException,
					InterruptedException {
				create(folder.getParent());
				folder.delete(true, getDefaultMonitor());
				folder.create(true, true, getDefaultMonitor());
			}

		}.run(getDefaultMonitor());
		return folder;
	}

	protected IFile createFile(IResource resource, String relativePath, String s) throws CoreException, InvocationTargetException, InterruptedException {
		return createFile(resource.getFullPath().append(relativePath), s);
	}
	protected IFile createFile(String wsRelativePath, String s) throws CoreException, InvocationTargetException, InterruptedException {
		return createFile(new Path(wsRelativePath), s);
	}
	protected IFile createFile(IPath wsRelativePath, String s) throws CoreException, InvocationTargetException, InterruptedException {
		return createFile(wsRelativePath, new StringBufferInputStream(s));
	}

	protected IFile createFile(IPath wsRelativePath, final InputStream contents)
			throws CoreException, InvocationTargetException,
			InterruptedException {
		final IFile file = root().getFile(wsRelativePath);
		new WorkspaceModifyOperation() {

			@Override
			protected void execute(IProgressMonitor monitor)
					throws CoreException, InvocationTargetException,
					InterruptedException {
				create(file.getParent());
				file.delete(true, getDefaultMonitor());
				file.create(contents, true, getDefaultMonitor());
			}

		}.run(getDefaultMonitor());
		return file;
	}

	protected InputStream getPluginTestFileContents(String pluginProject, String path) throws IOException {
		if (! path.startsWith("/")) {
			path = "/" + path;
		}
		URL url = new URL("platform:/plugin/" + pluginProject + path);
		return url.openStream();
	}
	
	protected IResource file(String path) {
		return root().findMember(new Path(path));
	}
	
	private void create(final IContainer container)
			throws CoreException, InvocationTargetException,
			InterruptedException {
		new WorkspaceModifyOperation() {

			@Override
			protected void execute(IProgressMonitor monitor)
					throws CoreException, InvocationTargetException,
					InterruptedException {
				if (!container.exists()) {
					create(container.getParent());
					if (container instanceof IFolder) {
						((IFolder) container).create(true, true, getDefaultMonitor());
					} else {
						IProject iProject = (IProject) container;
						createProject(iProject);
					}
				}
			}
		}.run(getDefaultMonitor());
	}

	protected IProgressMonitor getDefaultMonitor() {
		return new NullProgressMonitor();
	}

	protected void fullBuild() throws CoreException {
		ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.FULL_BUILD, getDefaultMonitor());
	}
	
	protected void cleanBuild() throws CoreException {
		ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.CLEAN_BUILD, getDefaultMonitor());
	}

	/**
	 * @deprecated clients should use {@link #waitForBuild()} since it is much faster. Clients that really depend
	 * on the delay before the build can use {@link #reallyWaitForAutoBuild()}.
	 */
	@Deprecated
	protected void waitForAutoBuild() {
		reallyWaitForAutoBuild();
	}
	
	/**
	 * A test that really should test the mechanism including the delay
	 * after the resource change event, could wait for the auto build.
	 */
	protected void reallyWaitForAutoBuild() {
		boolean wasInterrupted = false;
		do {
			try {
				Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD,
						null);
				wasInterrupted = false;
			} catch (OperationCanceledException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				wasInterrupted = true;
			}
		} while (wasInterrupted);
	}
	
	protected void waitForBuild() {
		waitForBuild(null);
	}
	
	protected void waitForBuild(IProgressMonitor monitor) {
		try {
			ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.INCREMENTAL_BUILD, monitor);
		} catch (CoreException e) {
			throw new OperationCanceledException(e.getMessage());
		}
	}

	protected void cleanWorkspace() throws CoreException {
		try {
			new WorkspaceModifyOperation() {
	
				@Override
				protected void execute(IProgressMonitor monitor)
						throws CoreException, InvocationTargetException,
						InterruptedException {
					IProject[] visibleProjects = root().getProjects();
					deleteProjects(visibleProjects);
					IProject[] hiddenProjects = root().getProjects(IContainer.INCLUDE_HIDDEN);
					deleteProjects(hiddenProjects);
				}
			}.run(getDefaultMonitor());
		} catch(Exception e) {
			throw new RuntimeException();
		}
	}

	protected void deleteProjects(IProject[] projects) throws CoreException {
		for (IProject iProject : projects) {
			if (iProject.exists()) {
				iProject.delete(true,true, getDefaultMonitor());
			}
		}
	}

	@Before
	public void setUp() throws Exception {
		closeWelcomePage();
		closeEditors();
//		cleanWorkspace();
		waitForBuild();
	}
	
	@After
	public void tearDown() throws Exception {
		closeEditors();
//		cleanWorkspace();
		waitForBuild();
	}
	
	protected void closeEditors() {
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(false);
	}

	protected void closeWelcomePage() throws InterruptedException {
		IIntroPart intro = PlatformUI.getWorkbench().getIntroManager().getIntro();
		if (intro != null) {
			PlatformUI.getWorkbench().getIntroManager().closeIntro(intro);
		}
	}

	protected void sleep(long i) throws InterruptedException {
		Display displ = Display.getCurrent();
		if (displ != null) {
			long timeToGo = System.currentTimeMillis() + i;
			while (System.currentTimeMillis() < timeToGo) {
				if (!displ.readAndDispatch()) {
					displ.sleep();
				}
			}
			displ.update();
		}
		else {
			Thread.sleep(i);
		}
	}

	//
	
	protected IWorkbenchPage getActivePage() {
		return getWorkbenchWindow().getActivePage();
	}

	protected IWorkbenchWindow getWorkbenchWindow() {
		return getWorkbench().getActiveWorkbenchWindow();
	}

	protected IWorkbench getWorkbench() {
		return PlatformUI.getWorkbench();
	}
	
	protected <T extends IViewPart> T openView(String viewId, Class<T> clazz) throws Exception {
		IViewPart view = openView(viewId);
		if (clazz != null && (! clazz.isInstance(view))) {
			Assert.fail("Couldn't open view of class:" + clazz);
		}
		return (T) view;
	}
	protected IViewPart openView(String viewId) throws PartInitException {
		IViewPart view = getActivePage().showView(viewId);
		if (view == null) {
			Assert.fail("Couldn't open view with id:" + viewId);
		}
		return view;
	}
	
	protected PlantUmlView openPlantUMLView() throws Exception {
		return openView("net.sourceforge.plantuml.eclipse.views.PlantUmlView", PlantUmlView.class);
	}

	protected <T extends IEditorPart> T openEditor(IFile file, String editorId, Class<T> clazz) throws Exception {
		IEditorPart editor = openEditor(file, editorId);
		if (clazz != null && (! clazz.isInstance(editor))) {
			Assert.fail("Couldn't open editor of class:" + clazz);
		}
		return (T) editor;
	}

	protected IEditorPart openEditor(IFile file, String editorId) throws PartInitException {
		if (editorId == null) {
			IEditorDescriptor desc = getWorkbench().getEditorRegistry().getDefaultEditor(file.getName());
			editorId = desc.getId();
		}
		IEditorPart editor = getActivePage().openEditor(new FileEditorInput(file), editorId);
		if (editor == null) {
			Assert.fail("Couldn't open editor with id:" + editorId);
		}
		return editor;
	}
}
