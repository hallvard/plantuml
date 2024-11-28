package net.sourceforge.plantuml.eclipse.views;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import net.sourceforge.plantuml.eclipse.Activator;
import net.sourceforge.plantuml.eclipse.utils.LocationDiagramIntentProviderContext;
import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.eclipse.utils.WorkbenchPartDiagramIntentProviderContext;
import net.sourceforge.plantuml.util.AbstractDiagramIntent;
import net.sourceforge.plantuml.util.AbstractProperties;
import net.sourceforge.plantuml.util.DiagramIntent;
import net.sourceforge.plantuml.util.DiagramIntentContext;
import net.sourceforge.plantuml.util.DiagramIntentProvider;
import net.sourceforge.plantuml.util.DiagramTextPostProcessor;
import net.sourceforge.plantuml.util.SimpleDiagramIntent;

public abstract class AbstractDiagramSourceView extends ViewPart {

	private static final String PINNED_TO_ID = "pinnedTo";
	protected static final String INITIAL_DIAGRAM_SOURCE_ID = "initialDiagramSource";
	
	private String pinnedToId = null;
	private IEditorPart pinnedTo = null;
	private String initialDiagramSource = null;

	@Override
	public void saveState(final IMemento memento) {
		super.saveState(memento);
		memento.putString(PINNED_TO_ID, (pinnedTo != null ? getEditorInputId(pinnedTo.getEditorInput()) : null));
		memento.putString(INITIAL_DIAGRAM_SOURCE_ID, getDiagramText());
	}

	@Override
	public void init(final IViewSite site, final IMemento memento) throws PartInitException {
		super.init(site, memento);
		if (memento != null) {
			pinnedToId = memento.getString(PINNED_TO_ID);
			initialDiagramSource = memento.getString(INITIAL_DIAGRAM_SOURCE_ID);
		}
	}

	public boolean isLinkedToActivePart() {
		return true;
	}

	public boolean isLinkingActive() {
		return isLinkedToActivePart() && toggleLinkAction == null || toggleLinkAction.isChecked();
	}

	private Control parent;

	protected void asyncExec(final Runnable runnable) {
		if (parent != null && (! parent.isDisposed())) {
			parent.getDisplay().asyncExec(runnable);
		}
	}

	@Override
	public void createPartControl(final Composite parent) {
		this.parent = parent;
		if (isLinkedToActivePart()) {
			registerListeners();
			// without this it deadlocked during startup
			asyncExec(new Runnable() {
				@Override
				public void run() {
					if (pinnedTo != null || initialDiagramSource == null) {
						updateDiagramText(true, pinnedTo, null);
					} else if (initialDiagramSource != null) {
						updateDiagramText(new SimpleDiagramIntent(initialDiagramSource));
					}
				}
			});
		}
		makeActions();
		contributeToActionBars();
	}

	protected boolean isValidControl(final Control control) {
		return control != null && (! control.isDisposed());
	}

	protected void contributeToActionBars() {
		final IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
		addViewActions(toolBarManager);

		final IMenuManager menu = getViewSite().getActionBars().getMenuManager();
		final MenuManager editorSelectionActionMenu = new MenuManager("Diagrams");
		editorSelectionActionMenu.add(new Action() {}); // will be removed, needed for the submenu to actually show
		editorSelectionActionMenu.setRemoveAllWhenShown(true);
		editorSelectionActionMenu.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(final IMenuManager menu) {
				for (final ActionContributionItem actionContributionItem : getEditorSelectionActions(menu)) {
					menu.add(actionContributionItem);
				}
			}
		});
		menu.add(editorSelectionActionMenu);
	}

	private IAction toggleLinkAction, pinToAction, spawnAction;

	protected void makeActions() {
		pinToAction = new Action() {
			@Override
			public boolean isEnabled() {
				return isLinkedToActivePart();
			}
			@Override
			public void run() {
				pinnedTo = (isChecked() && currentPart instanceof IEditorPart ? (IEditorPart) currentPart : null);
				if (pinnedTo != null) {
					setToolTipText("Pinned to " + getEditorInputId(pinnedTo.getEditorInput()));
				} else {
					updateDiagramText(true, null, null);
					setToolTipText(PlantumlConstants.PIN_TO_BUTTON);
				}
			}
		};
		pinToAction.setToolTipText(PlantumlConstants.PIN_TO_BUTTON);
		pinToAction.setImageDescriptor(ImageDescriptor.createFromFile(PlantumlConstants.class, "/icons/pin.png"));
		pinToAction.setChecked(pinnedTo != null || pinnedToId != null);

		spawnAction = new Action() {
			@Override
			public void run() {
				final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				final String id = AbstractDiagramSourceView.this.getViewSite().getId();
				try {
					page.showView(id, id + "-" + System.currentTimeMillis(), IWorkbenchPage.VIEW_ACTIVATE);
				} catch (final PartInitException e) {
				}
			}
		};
		spawnAction.setToolTipText(PlantumlConstants.SPAWN_BUTTON);
		spawnAction.setImageDescriptor(ImageDescriptor.createFromFile(PlantumlConstants.class, "/icons/spawn.png"));

		// action to start or stop the generation of the actual diagram
		toggleLinkAction = 	new Action() {
			@Override
			public void run() {
				if (isChecked()) {
					updateDiagramText(true, null, null);
				}
			}
		};
		toggleLinkAction.setToolTipText(PlantumlConstants.TOGGLE_GENERATION_BUTTON);
		toggleLinkAction.setImageDescriptor(ImageDescriptor.createFromFile(PlantumlConstants.class, "/icons/link.gif"));
		toggleLinkAction.setChecked(true);
	}

	protected void addActions(final IContributionManager manager, final IAction... actions) {
		if (! manager.isEmpty()) {
			manager.add(new Separator());
		}
		for (final IAction action : actions) {
			manager.add(action);
		}
	}

	protected void addViewActions(final IContributionManager toolBarManager) {
		addActions(toolBarManager, spawnAction, pinToAction, toggleLinkAction);
	}

	protected String getEditorInputId(final IEditorInput editorInput) {
		if (editorInput instanceof IStorageEditorInput) {
			IPath path = null;
			try {
				path = ((IStorageEditorInput) editorInput).getStorage().getFullPath();
			} catch (final CoreException e) {
			}
			if (path != null) {
				return path.toString();
			}
		}
		if (editorInput instanceof IPathEditorInput) {
			return ((IPathEditorInput) editorInput).getPath().toString();
		}
		if (editorInput instanceof IURIEditorInput) {
			return ((IURIEditorInput) editorInput).getURI().toString();
		}
		return editorInput.getName();
	}

	protected boolean acceptPart(final IWorkbenchPart part) {
		if (pinnedTo == null && pinnedToId == null) {
			return true;
		}
		if (pinnedTo != null && pinnedTo == part) {
			return true;
		}
		if (part instanceof IEditorPart && pinnedToId != null && acceptEditorInput(pinnedToId, ((IEditorPart) part).getEditorInput())) {
			pinnedTo = (IEditorPart) part;
			pinnedToId = null;
			return true;
		}
		return false;
	}

	protected boolean acceptEditorInput(final String inputId, final IEditorInput editorInput) {
		return pinnedToId.equals(getEditorInputId(editorInput));
	}

	protected void registerListeners() {
		getSite().getPage().addPartListener(partListener);
		getSite().getPage().addPostSelectionListener(diagramTextChangedListener);
	}

	@Override
	public void dispose() {
		if (currentPart != null) {
			currentPart.removePropertyListener(diagramTextChangedListener);
		}
		getSite().getPage().removePartListener(partListener);
		getSite().getPage().removePostSelectionListener(diagramTextChangedListener);
	}

	/**
	 * Update view with the provided text derived from the (optional) diagramIntent
	 * @param text diagram text
	 * @param diagramIntent original (or equivalent) diagram intent from which the diagram text is derived
	 * @param monitor a monitor that indicates the method is called outside the UI thread
	 */
	protected abstract void updateDiagramText(String text, DiagramIntent diagramIntent, final IProgressMonitor monitor);

	public abstract String getDiagramText();

	private boolean visible = false;

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(final boolean visible) {
		this.visible = visible;
	}

	private final IPartListener2 partListener = new PartListener();

	protected boolean isPlantUmlView(final String partId) {
		return partId.startsWith("net.sourceforge.plantuml.eclipse.views.");
	}

	protected boolean isPlantUmlView(final IWorkbenchPartReference partRef) {
		return isPlantUmlView(partRef.getId());
	}

	protected boolean isThisView(final IWorkbenchPartReference partRef) {
		return partRef.getId().equals(getSite().getId());
	}

	private class PartListener implements IPartListener2 {

		protected void updateDiagramText(final IWorkbenchPart part) {
			if (isLinkingActive()) {
				AbstractDiagramSourceView.this.updateDiagramText(false, part, null);
			}
		}

		@Override
		public void partOpened(final IWorkbenchPartReference partRef) {}

		@Override
		public void partActivated(final IWorkbenchPartReference partRef) {
			if (! isPlantUmlView(partRef)) {
				updateDiagramText(partRef.getPart(true));
			}
		}

		@Override
		public void partVisible(final IWorkbenchPartReference partRef) {
			if (isThisView(partRef)) {
				setVisible(true);
			}
		}

		@Override
		public void partBroughtToTop(final IWorkbenchPartReference partRef) {}

		@Override
		public void partInputChanged(final IWorkbenchPartReference partRef) {}

		@Override
		public void partHidden(final IWorkbenchPartReference partRef) {
			if (isThisView(partRef)) {
				setVisible(false);
			}
		}

		@Override
		public void partDeactivated(final IWorkbenchPartReference partRef) {}

		@Override
		public void partClosed(final IWorkbenchPartReference partRef) {}
	};

	private class DiagramTextChangedListener implements IPropertyListener, ISelectionListener, ISelectionChangedListener {

		@Override
		public void propertyChanged(final Object source, final int propId) {
			if (source == currentPart && propId == IEditorPart.PROP_DIRTY && currentPart instanceof IEditorPart && (! ((IEditorPart) currentPart).isDirty())) {
				diagramChanged(currentPart, null);
			}
		}
		@Override
		public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
			if (part == currentPart) {
				diagramChanged(currentPart, selection);
			}
		}
		protected void diagramChanged(final IWorkbenchPart editor, final ISelection selection) {
			if (isLinkingActive()) {
				updateDiagramText(true, editor, selection);
			}
		}

		@Override
		public void selectionChanged(final SelectionChangedEvent event) {
			if ((! (event.getSource() instanceof DiagramIntentProvider)) || Activator.getDefault().isEnabled((DiagramIntentProvider) event.getSource())) {
				final ISelection selection = event.getSelection();
				if (selection instanceof IStructuredSelection) {
					final Object o = ((IStructuredSelection) selection).getFirstElement();
					asyncExec(new Runnable() {
						@Override
						public void run() {
							updateDiagramText(new SimpleDiagramIntent(String.valueOf(o)));
						}
					});
				}
			}
		}
	}

	private final DiagramTextChangedListener diagramTextChangedListener = new DiagramTextChangedListener();
	private IWorkbenchPart currentPart;

	private void handlePartChange(final IWorkbenchPart part) {
		if (currentPart != null) {
			currentPart.removePropertyListener(diagramTextChangedListener);
		}
		currentPart = part;
		if (currentPart != null) {
			currentPart.addPropertyListener(diagramTextChangedListener);
		}
	}

	protected Collection<ActionContributionItem> getEditorSelectionActions(final IMenuManager menu) {
		final Collection<ActionContributionItem> actions = new ArrayList<ActionContributionItem>();
		final DiagramIntentProvider[] diagramIntentProviders = Activator.getDefault().getDiagramIntentProviders(true);
		// must use activeEditorPart instead of activePart, since the latter will be the plantuml view
		final IEditorPart part = (pinnedTo != null ? pinnedTo : getSite().getPage().getActiveEditor());
		final ISelectionProvider selectionProvider = part.getSite().getSelectionProvider();
		final DiagramIntentContext partContext = new WorkbenchPartDiagramIntentProviderContext(part, selectionProvider != null ? selectionProvider.getSelection() : null);
		for (final DiagramIntentProvider diagramIntentProvider : diagramIntentProviders) {
			final Collection<? extends DiagramIntent> diagramInfos = diagramIntentProvider.getDiagramInfos(partContext);
			if (diagramInfos != null) {
				final String diagramIntentProviderLabel = Activator.getDefault().getDiagramIntentProviderLabel(diagramIntentProvider);
				int count = 0;
				for (final DiagramIntent diagramIntent : diagramInfos) {
					String label = diagramIntentProviderLabel;
					if (diagramInfos.size() > 1) {
						label += " " + (count + 1);
					}
					final ActionContributionItem action = new ActionContributionItem(createEditorSelectionAction(diagramIntent, label));
					actions.add(action);
					count++;
				}
			}
		}
		return actions;
	}

	private Action createEditorSelectionAction(final DiagramIntent diagramIntent, final String label) {
		return new Action(label) {
			@Override
			public void run() {
				asyncExec(new Runnable() {
					@Override
					public void run() {
						updateDiagramText(diagramIntent);
					}
				});
			}
		};
	}

	/**
	 * Updates the diagram text from the current part (if linked to it) and the current selection.
	 */
	public void updateDiagramText() {
		updateDiagramText(true, null, null);
	}

	protected void updateDiagramText(final boolean force, final IWorkbenchPart part, ISelection selection) {
		final IWorkbenchPart activePart = (part != null ? part : (isLinkedToActivePart() ? getSite().getPage().getActivePart() : null));
		if (force || activePart != currentPart) {
			if (activePart == null || acceptPart(activePart)) {
				handlePartChange(activePart);
				if (activePart != null) {
					if (selection == null) {
						final ISelectionProvider selectionProvider = activePart.getSite().getSelectionProvider();
						if (selectionProvider != null) {
							selection = selectionProvider.getSelection();
						}
					}
					if (updateDiagramText(activePart, selection)) {
						return;
					}
				}
				updateDiagramText(null, null, null);
			}
		}
	}

	// listener support, partly to support testing

	private Collection<DiagramViewStatusListener> diagramViewListeners = new ArrayList<DiagramViewStatusListener>();

	public void addDiagramViewListener(final DiagramViewStatusListener listener) {
		if (diagramViewListeners == null) {
			diagramViewListeners = new ArrayList<DiagramViewStatusListener>();
		}
		diagramViewListeners.add(listener);
	}

	public void removeDiagramViewListener(final DiagramViewStatusListener listener) {
		if (diagramViewListeners != null) {
			diagramViewListeners.remove(listener);
		}
	}

	public enum ViewStatus {
		DIAGRAM_INTENT, DIAGRAM_TEXT, DIAGRAM_VIEW_TEXT, DIAGRAM_VIEW_DATA, DIAGRAM_VIEW
	}

	private ViewStatus viewStatus;

	protected ViewStatus getViewStatus() {
		return viewStatus;
	}

	protected void setDiagramViewStatus(final ViewStatus status, final Object diagram) {
		fireDiagramViewStatusChanged(status, diagram);
		//		String diagramText = diagram.toString();
		//		final int pos = diagramText.indexOf('\n');
		//		if (pos >= 0) {
		//			diagramText = diagramText.substring(0, pos);
		//		}
		//		System.out.println(this + " -> " + status + " (" + diagramText + ")");
	}

	private void fireDiagramViewStatusChanged(final ViewStatus status, final Object diagram) {
		if (diagramViewListeners != null) {
			for (final DiagramViewStatusListener listener : diagramViewListeners) {
				listener.diagramViewStatusChanged(this, status, diagram);
			}
		}
	}

	//

	private boolean updateDiagramText(final IWorkbenchPart activePart, final ISelection selection) {
		if (activePart != null) {
			final DiagramIntentContext context = new WorkbenchPartDiagramIntentProviderContext(activePart, selection);

			// intent providers are sorted on priority
			// within the same provider priority, the intent's priority is used
			DiagramIntent bestIntent = null;
			int bestPriority = AbstractDiagramIntent.DEFAULT_PRIORITY - 1;

			int lastProviderPriority = Activator.DEFAULT_PRIORITY;
			for (final DiagramIntentProvider diagramIntentProvider : Activator.getDefault().getDiagramIntentProviders(true)) {
				final int providerPriority = Activator.getDefault().getDiagramIntentProviderPriority(diagramIntentProvider);
				if (providerPriority < lastProviderPriority && bestIntent != null) {
					break;
				}
				final Collection<? extends DiagramIntent> diagramInfos = diagramIntentProvider.getDiagramInfos(context);
				if (diagramInfos != null && (! diagramInfos.isEmpty())) {
					for (final DiagramIntent diagramIntent : diagramInfos) {
						if (diagramIntent.getPriority() > bestPriority) {
							bestIntent = diagramIntent;
							bestPriority = diagramIntent.getPriority();
							if (bestIntent instanceof AbstractDiagramIntent<?>) {
								setDiagramContextProperties(((AbstractDiagramIntent<?>) bestIntent).getContextProperties(), diagramIntentProvider);
							}
						}
					}
				}
				lastProviderPriority = providerPriority;
			}
			if (bestIntent != null) {
				setDiagramViewStatus(ViewStatus.DIAGRAM_INTENT, bestIntent);
				if (bestIntent instanceof AbstractDiagramIntent<?>) {
					setDiagramContextProperties(((AbstractDiagramIntent<?>) bestIntent).getContextProperties(), context);
				}
				updateDiagramText(bestIntent);
				return true;
			}
		}
		return false;
	}

	private void setDiagramContextProperties(final AbstractProperties diagramContextProperties, final DiagramIntentProvider diagramIntentProvider) {
		diagramContextProperties.setProperty("diagramIntentProviderId", Activator.getDefault().getDiagramIntentProviderId(diagramIntentProvider));
	}

	private void setDiagramContextProperties(final AbstractProperties diagramContextProperties, final DiagramIntentContext context) {
		if (context instanceof WorkbenchPartDiagramIntentProviderContext) {
			diagramContextProperties.setProperty("workbenchPartId", ((WorkbenchPartDiagramIntentProviderContext) context).getWorkbenchPart().getSite().getId());
		}
		if (context instanceof LocationDiagramIntentProviderContext) {
			final IPath path = ((LocationDiagramIntentProviderContext) context).getPath();
			if (path != null) {
				diagramContextProperties.setProperty("path", path.toString());
			}
		}
	}

	private Job currentJob = null;

	protected synchronized void updateDiagramText(final DiagramIntent diagramIntent) {
		if (currentJob != null) {
			currentJob.cancel();
		}
		currentJob = new Job("Generate diagram") {
			@Override
			protected IStatus run(final IProgressMonitor monitor) {
				try {
					String diagramText = diagramIntent.getDiagramText();
					if (monitor == null || (! monitor.isCanceled())) {
						if (diagramText != null) {
							// default to @startuml ... @enduml diagram
							diagramText = ensureDiagram(diagramText);
							if (diagramIntent instanceof AbstractDiagramIntent<?>) {
								diagramText = postProcessDiagramText(diagramText, (AbstractDiagramIntent<?>) diagramIntent);
							}
							setDiagramViewStatus(ViewStatus.DIAGRAM_TEXT, diagramText);
							updateDiagramText(diagramText, diagramIntent, monitor);
						}
					}
				} catch (final Exception e) {
					System.err.println(e);
				}
				currentJob = null;
				return monitor != null && monitor.isCanceled() ? Status.CANCEL_STATUS : Status.OK_STATUS;
			}
		};
		//		Thread.dumpStack();
		currentJob.schedule();
	}

	private String postProcessDiagramText(String diagramText, final AbstractDiagramIntent<?> diagramIntent) {
		for (final DiagramTextPostProcessor postProcessor : Activator.getDefault().getDiagramTextPostProcessors()) {
			final String altDiagramText = postProcessor.getDiagramText(diagramText, diagramIntent);
			if (altDiagramText != null) {
				diagramText = altDiagramText;
			}
		}
		return diagramText;
	}

	protected String ensureDiagram(String diagramText) {
		if (! diagramText.startsWith("@")) {
			diagramText = PlantumlConstants.START_UML + "\n" + diagramText;
			String suffix = PlantumlConstants.END_UML;
			if (! diagramText.endsWith("\n")) {
				suffix = "\n" + suffix;
			}
			diagramText += suffix;
		}
		return diagramText;
	}
}
