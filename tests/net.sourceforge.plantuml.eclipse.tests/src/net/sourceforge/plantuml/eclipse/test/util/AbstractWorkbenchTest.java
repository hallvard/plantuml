package net.sourceforge.plantuml.eclipse.test.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

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
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
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

import net.sourceforge.plantuml.eclipse.views.AbstractDiagramSourceView;

public abstract class AbstractWorkbenchTest {

	protected WorkspaceHelper workspaceHelper = new WorkspaceHelper();

	protected IWorkspaceRoot root() {
		return workspaceHelper.root();
	}

	protected boolean projectExists(final String name) {
		return workspaceHelper.projectExists(name);
	}

	protected IProject createProject(final String name, final String... natures) throws CoreException, InvocationTargetException, InterruptedException {
		return workspaceHelper.createProject(name, natures);
	}

	protected IProject createProject(final IProject project) throws CoreException {
		return workspaceHelper.createProject(project);
	}

	protected void addNatures(final IProject project, final String... natures) throws CoreException {
		workspaceHelper.addNatures(project, natures);
	}

	protected void addBuilder(final IProject project, final String builderId) throws CoreException {
		workspaceHelper.addBuilder(project, builderId);
	}

	protected void removeNature(final IProject project, final String nature)
			throws CoreException {
		workspaceHelper.removeNature(project, nature);
	}

	protected void removeBuilder(final IProject project, final String builderId) throws CoreException {
		workspaceHelper.removeBuilder(project, builderId);
	}

	protected void setReference(final IProject from, final IProject to)
			throws CoreException, InvocationTargetException,
			InterruptedException {
		new WorkspaceModifyOperation() {

			@Override
			protected void execute(final IProgressMonitor monitor)
					throws CoreException, InvocationTargetException,
					InterruptedException {
				final IProjectDescription projectDescription = from.getDescription();
				final IProject[] projects = projectDescription
						.getReferencedProjects();
				final IProject[] newProjects = new IProject[projects.length + 1];
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
			protected void execute(final IProgressMonitor monitor)
					throws CoreException, InvocationTargetException,
					InterruptedException {
				final IProjectDescription projectDescription = from.getDescription();
				final IProject[] projects = projectDescription
						.getReferencedProjects();
				for (int i = 0; i < projects.length; ++i) {
					if (to.equals(projects[i])) {
						// Remove the nature
						final IProject[] newProjects = new IProject[projects.length - 1];
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

	protected IFolder createFolder(final IResource resource, final String relativePath) throws InvocationTargetException, InterruptedException {
		return workspaceHelper.createFolder(resource, relativePath);
	}
	protected IFolder createFolder(final String wsRelativePath) throws InvocationTargetException, InterruptedException {
		return workspaceHelper.createFolder(wsRelativePath);
	}
	protected IFolder createFolder(final IPath wsRelativePath) throws InvocationTargetException, InterruptedException {
		return workspaceHelper.createFolder(wsRelativePath);
	}

	protected IFile createFile(final IResource resource, final String relativePath, final String s) throws CoreException, InvocationTargetException, InterruptedException {
		return workspaceHelper.createFile(resource, relativePath, s);
	}
	protected IFile createFile(final String wsRelativePath, final String s) throws CoreException, InvocationTargetException, InterruptedException {
		return workspaceHelper.createFile(wsRelativePath, s);
	}
	protected IFile createFile(final IPath wsRelativePath, final String s) throws CoreException, InvocationTargetException, InterruptedException {
		return workspaceHelper.createFile(wsRelativePath, s);
	}

	protected IFile createFile(final IPath wsRelativePath, final InputStream contents)
			throws CoreException, InvocationTargetException,
			InterruptedException {
		return workspaceHelper.createFile(wsRelativePath, contents);
	}

	protected InputStream getPluginTestFileContents(final String pluginProject, String path) throws IOException {
		if (! path.startsWith("/")) {
			path = "/" + path;
		}
		final URL url = new URL("platform:/plugin/" + pluginProject + path);
		return url.openStream();
	}

	protected IResource file(final String path) {
		return root().findMember(new Path(path));
	}

	protected IProgressMonitor getDefaultMonitor() {
		return workspaceHelper.getDefaultMonitor();
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
			} catch (final OperationCanceledException e) {
				e.printStackTrace();
			} catch (final InterruptedException e) {
				wasInterrupted = true;
			}
		} while (wasInterrupted);
	}

	protected void waitForBuild() {
		waitForBuild(null);
	}

	protected void waitForBuild(final IProgressMonitor monitor) {
		try {
			ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.INCREMENTAL_BUILD, monitor);
		} catch (final CoreException e) {
			throw new OperationCanceledException(e.getMessage());
		}
	}

	protected void cleanWorkspace() throws CoreException {
		try {
			new WorkspaceModifyOperation() {

				@Override
				protected void execute(final IProgressMonitor monitor)
						throws CoreException, InvocationTargetException,
						InterruptedException {
					final IProject[] visibleProjects = root().getProjects();
					deleteProjects(visibleProjects);
					final IProject[] hiddenProjects = root().getProjects(IContainer.INCLUDE_HIDDEN);
					deleteProjects(hiddenProjects);
				}
			}.run(getDefaultMonitor());
		} catch(final Exception e) {
			throw new RuntimeException();
		}
	}

	protected void deleteProjects(final IProject[] projects) throws CoreException {
		for (final IProject iProject : projects) {
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
		final IIntroPart intro = PlatformUI.getWorkbench().getIntroManager().getIntro();
		if (intro != null) {
			PlatformUI.getWorkbench().getIntroManager().closeIntro(intro);
		}
	}

	protected void sleep(final long i) throws InterruptedException {
		final Display displ = Display.getCurrent();
		if (displ != null) {
			final long timeToGo = System.currentTimeMillis() + i;
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

	protected <T extends IViewPart> T openView(final String viewId, final Class<T> clazz) throws Exception {
		final IViewPart view = openView(viewId);
		if (clazz != null && (! clazz.isInstance(view))) {
			Assert.fail("Couldn't open view of class:" + clazz);
		}
		return (T) view;
	}
	protected IViewPart openView(final String viewId) throws PartInitException {
		final IViewPart view = getActivePage().showView(viewId);
		if (view == null) {
			Assert.fail("Couldn't open view with id:" + viewId);
		}
		return view;
	}

	protected AbstractDiagramSourceView openDiagramView(final boolean source) throws Exception {
		final String viewId = (source ? "net.sourceforge.plantuml.eclipse.views.DiagramSourceView" : "net.sourceforge.plantuml.eclipse.views.PlantUmlView");
		return openView(viewId, AbstractDiagramSourceView.class);
	}

	protected AbstractDiagramSourceView openPlantUMLView() throws Exception {
		return openDiagramView(false);
	}

	protected <T extends IEditorPart> T openEditor(final IFile file, final String editorId, final Class<T> clazz) throws Exception {
		final IEditorPart editor = openEditor(file, editorId);
		if (clazz != null && (! clazz.isInstance(editor))) {
			Assert.fail("Couldn't open editor of class:" + clazz);
		}
		return (T) editor;
	}

	protected IEditorPart openEditor(final IEditorInput editorInput, String editorId) throws PartInitException {
		if (editorId == null) {
			final IEditorDescriptor desc = getWorkbench().getEditorRegistry().getDefaultEditor(editorInput.getName());
			if (desc != null) {
				editorId = desc.getId();
			} else {
				editorId = "org.eclipse.ui.DefaultTextEditor";
			}
		}
		final IEditorPart editor = getActivePage().openEditor(editorInput, editorId);
		if (editor == null) {
			Assert.fail("Couldn't open editor with id:" + editorId);
		}
		return editor;
	}

	protected IEditorPart openEditor(final IFile file, final String editorId) throws PartInitException {
		return openEditor(new FileEditorInput(file), editorId);
	}
}
