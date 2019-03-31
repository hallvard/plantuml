package net.sourceforge.plantuml.eclipse.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
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

	public boolean isLinkedToActiveEditor() {
		return true;
	}

	public boolean isLinkingActive() {
		return isLinkedToActiveEditor() && toggleAction == null || toggleAction.isChecked();
	}

	private Control parent;

	protected void asyncExec(final Runnable runnable) {
		parent.getDisplay().asyncExec(runnable);
	}

	@Override
	public void createPartControl(final Composite parent) {
		this.parent = parent;
		if (isLinkedToActiveEditor()) {
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
				return isLinkedToActiveEditor();
			}
			@Override
			public void run() {
				pinnedTo = (isChecked() ? currentEditor : null);
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

	protected boolean acceptEditor(final IEditorPart editor) {
		if (pinnedTo == null && pinnedToId == null) {
			return true;
		}
		if (pinnedTo != null && pinnedTo == editor) {
			return true;
		}
		if (pinnedToId != null && acceptEditorInput(pinnedToId, editor.getEditorInput())) {
			pinnedTo = editor;
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
		if (currentEditor != null) {
			currentEditor.removePropertyListener(diagramTextChangedListener);
		}
		getSite().getPage().removePartListener(partListener);
		getSite().getPage().removePostSelectionListener(diagramTextChangedListener);
	}

	protected abstract void updateDiagramText(String text, IPath path, Map<String, Object> markerAttributes);
	public abstract String getDiagramText();

	private final IPartListener partListener = new IPartListener() {
		@Override
		public void partActivated(final IWorkbenchPart part) {
			updateDiagramText(part);
		}
		protected void updateDiagramText(final IWorkbenchPart part) {
			if (isLinkingActive()) {
				AbstractDiagramSourceView.this.updateDiagramText(false, part, null);
			}
		}
		@Override
		public void partOpened(final IWorkbenchPart part) {}
		@Override
		public void partDeactivated(final IWorkbenchPart part) {}
		@Override
		public void partClosed(final IWorkbenchPart part) {}
		@Override
		public void partBroughtToTop(final IWorkbenchPart part) {}
	};

	private class DiagramTextChangedListener implements IPropertyListener, ISelectionListener {

		@Override
		public void propertyChanged(final Object source, final int propId) {
			if (source == currentEditor && propId == IEditorPart.PROP_DIRTY && (! currentEditor.isDirty())) {
				diagramChanged(currentEditor, null);
			}
		}
		@Override
		public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
			if (part == currentEditor) {
				diagramChanged(currentEditor, selection);
			}
		}
		protected void diagramChanged(final IEditorPart editor, final ISelection selection) {
			if (isLinkingActive()) {
				updateDiagramText(true, editor, selection);
			}
		}
	}

	private final DiagramTextChangedListener diagramTextChangedListener = new DiagramTextChangedListener();
	private IEditorPart currentEditor;

	private void handleEditorChange(final IEditorPart editor) {
		if (currentEditor != null) {
			currentEditor.removePropertyListener(diagramTextChangedListener);
		}
		currentEditor = editor;
		if (currentEditor != null) {
			currentEditor.addPropertyListener(diagramTextChangedListener);
		}
	}

	protected List<ActionContributionItem> addEditorSelectionActions(final IMenuManager menu) {
		List<ActionContributionItem> actions = new ArrayList<ActionContributionItem>();
		final DiagramTextProvider[] diagramTextProviders = Activator.getDefault().getDiagramTextProviders();
		final IEditorPart editor = (pinnedTo != null ? pinnedTo : getSite().getPage().getActiveEditor());
		final ISelectionProvider selectionProvider = editor.getSite().getSelectionProvider();
		if (selectionProvider != null) {
			for (final DiagramTextProvider diagramTextProvider : diagramTextProviders) {
				if (diagramTextProvider instanceof DiagramTextIteratorProvider && diagramTextProvider.supportsEditor(editor)) {
					final Iterator<ISelection> selections = ((DiagramTextIteratorProvider) diagramTextProvider).getDiagramText(editor);
					while (selections.hasNext()) {
						final ISelection selection = selections.next();
						final ActionContributionItem action = new ActionContributionItem(createEditorSelectionAction(editor, selectionProvider, selection));
						menu.add(action);
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

	protected void updateDiagramText(final boolean force, final IWorkbenchPart part, ISelection selection) {
		final IEditorPart activeEditor = (part instanceof IEditorPart ? (IEditorPart) part : (isLinkedToActiveEditor() ? getSite().getPage().getActiveEditor() : null));
		if (force || activeEditor != currentEditor) {
			if (activeEditor == null || acceptEditor(activeEditor)) {
				IPath path = null;
				handleEditorChange(activeEditor);
				if (activeEditor != null) {
					if (activeEditor.getEditorInput() instanceof IFileEditorInput) {
						path = ((IFileEditorInput) activeEditor.getEditorInput()).getFile().getFullPath();
					}
					if (selection == null) {
						final ISelectionProvider selectionProvider = activeEditor.getSite().getSelectionProvider();
						if (selectionProvider != null) {
							selection = selectionProvider.getSelection();
						}
					}
					if (updateDiagramText(activeEditor, selection, path)) {
						return;
					}
					if (selection != null && updateDiagramText(activeEditor, null, path)) {
						return;
					}
				}
				updateDiagramText((String) null, (IPath) null, (Map<String, Object>) null);
			}
		}
	}

	private boolean updateDiagramText(final IEditorPart activeEditor, final ISelection selection, final IPath path) {
		if (activeEditor != null) {
			final DiagramTextProvider[] diagramTextProviders = Activator.getDefault().getDiagramTextProviders();
			final Map<String, Object> markerAttributes = new HashMap<String, Object>();
			for (int i = 0; i < diagramTextProviders.length; i++) {
				final DiagramTextProvider diagramTextProvider = diagramTextProviders[i];
				if (diagramTextProvider.supportsEditor(activeEditor) && (selection == null || diagramTextProvider.supportsSelection(selection))) {
					String diagramText = null;
					if (diagramTextProvider instanceof DiagramTextProvider2) {
						markerAttributes.clear();
						diagramText = ((DiagramTextProvider2) diagramTextProvider).getDiagramText(activeEditor, selection, markerAttributes);
					} else {
						diagramText = diagramTextProvider.getDiagramText(activeEditor, selection);
					}
					if (diagramText != null) {
						updateDiagramText(diagramText, path, markerAttributes);
						return true;
					}
				}
			}
		}
		return false;
	}
}
