package net.sourceforge.plantuml.eclipse.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuManager;
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
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import net.sourceforge.plantuml.eclipse.Activator;
import net.sourceforge.plantuml.eclipse.utils.DiagramTextIteratorProvider;
import net.sourceforge.plantuml.eclipse.utils.DiagramTextProvider;
import net.sourceforge.plantuml.eclipse.utils.DiagramTextProvider2;
import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;

public abstract class AbstractDiagramSourceView extends ViewPart {

	private String pinnedToId = null;
	private IEditorPart pinnedTo = null;
	private String initialDiagramSource = null;

	@Override
	public void saveState(final IMemento memento) {
		super.saveState(memento);
		memento.putString("pinnedTo", (pinnedTo != null ? getEditorInputId(pinnedTo.getEditorInput()) : null));
		memento.putString("initialDiagramSource", getDiagramText());
	}

	@Override
	public void init(final IViewSite site, final IMemento memento) throws PartInitException {
		super.init(site, memento);
		if (memento != null) {
			pinnedToId = memento.getString("pinnedTo");
			initialDiagramSource = memento.getString("initialDiagramSource");
		}
	}

	public boolean isLinkedToActivePart() {
		return true;
	}

	public boolean isLinkingActive() {
		return isLinkedToActivePart() && toggleAction == null || toggleAction.isChecked();
	}

	private Control parent;

	protected void asyncExec(final Runnable runnable) {
		parent.getDisplay().asyncExec(runnable);
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
						updateDiagramText(initialDiagramSource, null, null);
					}
				}
			});
		}
		makeActions();
		contributeToActionBars();
	}

	protected void contributeToActionBars() {
	}

	private IAction toggleAction, pinToAction, spawnAction;

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
		toggleAction = 	new Action() {
			@Override
			public void run() {
				if (isChecked()) {
					updateDiagramText(true, null, null);
				}
			}
		};
		toggleAction.setToolTipText(PlantumlConstants.TOGGLE_GENERATION_BUTTON);
		toggleAction.setImageDescriptor(ImageDescriptor.createFromFile(PlantumlConstants.class, "/icons/link.gif"));
		toggleAction.setChecked(true);
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
		addActions(toolBarManager, spawnAction, pinToAction, toggleAction);
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
		for (final DiagramTextProvider diagramTextProvider : Activator.getDefault().getDiagramTextProviders(null)) {
			if (diagramTextProvider instanceof ISelectionProvider) {
				((ISelectionProvider) diagramTextProvider).addSelectionChangedListener(diagramTextChangedListener);
			}
		}
	}

	@Override
	public void dispose() {
		if (currentPart != null) {
			currentPart.removePropertyListener(diagramTextChangedListener);
		}
		getSite().getPage().removePartListener(partListener);
		getSite().getPage().removePostSelectionListener(diagramTextChangedListener);
		for (final DiagramTextProvider diagramTextProvider : Activator.getDefault().getDiagramTextProviders(null)) {
			if (diagramTextProvider instanceof ISelectionProvider) {
				((ISelectionProvider) diagramTextProvider).removeSelectionChangedListener(diagramTextChangedListener);
			}
		}
	}

	protected abstract void updateDiagramText(String text, IPath path, Map<String, Object> markerAttributes);
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
			if ((! (event.getSource() instanceof DiagramTextProvider)) || Activator.getDefault().isEnabled((DiagramTextProvider) event.getSource())) {
				final ISelection selection = event.getSelection();
				if (selection instanceof IStructuredSelection) {
					final Object o = ((IStructuredSelection) selection).getFirstElement();
					asyncExec(new Runnable() {
						@Override
						public void run() {
							updateDiagramText(String.valueOf(o), null, null);
						}
					});
				}
			}
		}
	}

	private final DiagramTextChangedListener diagramTextChangedListener = new DiagramTextChangedListener();
	private IWorkbenchPart currentPart;

	private void handlePartChange(final IWorkbenchPart part) {
		if (part.equals(this)) {
			return;
		}
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
		final DiagramTextProvider[] diagramTextProviders = Activator.getDefault().getDiagramTextProviders(true);
		final IEditorPart editor = (pinnedTo != null ? pinnedTo : getSite().getPage().getActiveEditor());
		final ISelectionProvider selectionProvider = editor.getSite().getSelectionProvider();
		if (selectionProvider != null) {
			for (final DiagramTextProvider diagramTextProvider : diagramTextProviders) {
				if (diagramTextProvider instanceof DiagramTextIteratorProvider && diagramTextProvider.supportsEditor(editor)) {
					final Iterator<ISelection> selections = ((DiagramTextIteratorProvider) diagramTextProvider).getDiagramText(editor);
					while (selections.hasNext()) {
						final ISelection selection = selections.next();
						final ActionContributionItem action = new ActionContributionItem(createEditorSelectionAction(editor, selectionProvider, selection));
						actions.add(action);
					}
				}
			}
		}
		return actions;
	}

	private Action createEditorSelectionAction(final IEditorPart editor, final ISelectionProvider selectionProvider, final ISelection selection) {
		return new Action(selection.toString()) {
			@Override
			public void run() {
				asyncExec(new Runnable() {
					@Override
					public void run() {
						diagramTextChangedListener.diagramChanged(editor, selection);
						selectionProvider.setSelection(selection);
					}
				});
			}
		};
	}
	
	/**
	 * Updates the diagram text using the part that was last used by PlantUML.
	 * That makes it possible to programmatically update the current diagram.
	 */
	public void updateDiagramTextFromCurrentPart() {
		updateDiagramText(true, currentPart, null);
	}

	/**
	 * Updates the diagram text from the part that is currently active in the IDE (if linked to it) and the current selection.
	 */
	public void updateDiagramText() {
		updateDiagramText(true, null, null);
	}

	protected void updateDiagramText(final boolean force, final IWorkbenchPart part, ISelection selection) {
		final IWorkbenchPart activePart = (part != null ? part : (isLinkedToActivePart() ? getSite().getPage().getActivePart() : null));
		if (force || activePart != currentPart) {
			if (activePart == null || acceptPart(activePart)) {
				IPath path = null;
				handlePartChange(activePart);
				if (activePart != null) {
					if (activePart instanceof IEditorPart && ((IEditorPart) activePart).getEditorInput() instanceof IFileEditorInput) {
						path = ((IFileEditorInput) ((IEditorPart) activePart).getEditorInput()).getFile().getFullPath();
					}
					if (selection == null) {
						final ISelectionProvider selectionProvider = activePart.getSite().getSelectionProvider();
						if (selectionProvider != null) {
							selection = selectionProvider.getSelection();
						}
					}
					if (updateDiagramText(activePart, selection, path)) {
						return;
					}
					if (selection != null && updateDiagramText(activePart, null, path)) {
						return;
					}
				}
				updateDiagramText((String) null, (IPath) null, (Map<String, Object>) null);
			}
		}
	}

	protected boolean supportsPart(final DiagramTextProvider diagramTextProvider, final IWorkbenchPart part) {
		if (part instanceof IViewPart) {
			return diagramTextProvider.supportsView((IViewPart) part);
		} else if (part instanceof IEditorPart) {
			return diagramTextProvider.supportsEditor((IEditorPart) part);
		}
		return false;
	}

	protected String getDiagramText(final DiagramTextProvider diagramTextProvider, final IWorkbenchPart part, final ISelection selection) {
		if (part instanceof IViewPart) {
			return diagramTextProvider.getDiagramText((IViewPart) part, selection);
		} else if (part instanceof IEditorPart) {
			return diagramTextProvider.getDiagramText((IEditorPart) part, selection);
		}
		return null;
	}

	private boolean updateDiagramText(final IWorkbenchPart activePart, final ISelection selection, final IPath path) {
		if (activePart != null) {
			// TODO: do this in a Job
			final DiagramTextProvider[] diagramTextProviders = Activator.getDefault().getDiagramTextProviders(true);
			final Map<String, Object> markerAttributes = new HashMap<String, Object>();
			for (int i = 0; i < diagramTextProviders.length; i++) {
				final DiagramTextProvider diagramTextProvider = diagramTextProviders[i];
				if (supportsPart(diagramTextProvider, activePart) && (selection == null || diagramTextProvider.supportsSelection(selection))) {
					String diagramText = null;
					if (activePart instanceof IEditorPart && diagramTextProvider instanceof DiagramTextProvider2) {
						markerAttributes.clear();
						diagramText = ((DiagramTextProvider2) diagramTextProvider).getDiagramText((IEditorPart) activePart, selection, markerAttributes);
					} else {
						diagramText = getDiagramText(diagramTextProvider, activePart, selection);
					}
					if (diagramText != null) {
						// default to @startuml ... @enduml diagram
						diagramText = ensureDiagram(diagramText);
						updateDiagramText(diagramText, path, markerAttributes);
						return true;
					}
				}
			}
		}
		return false;
	}

	protected String ensureDiagram(String diagramText) {
		if (! diagramText.startsWith("@")) {
			diagramText = PlantumlConstants.START_UML + "\n" + diagramText + "\n" + PlantumlConstants.END_UML;
		}
		return diagramText;
	}
}
