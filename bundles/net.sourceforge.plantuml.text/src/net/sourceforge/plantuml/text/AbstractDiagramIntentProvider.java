package net.sourceforge.plantuml.text;

import java.util.Collection;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;

import net.sourceforge.plantuml.eclipse.utils.PlantumlConstants;
import net.sourceforge.plantuml.eclipse.utils.WorkbenchPartDiagramIntentProviderContext;
import net.sourceforge.plantuml.eclipse.utils.WorkspaceDiagramIntentProviderContext;
import net.sourceforge.plantuml.util.DiagramIntent;
import net.sourceforge.plantuml.util.DiagramIntentContext;
import net.sourceforge.plantuml.util.DiagramIntentProvider;

public abstract class AbstractDiagramIntentProvider implements DiagramIntentProvider {

	private Class<?> partType = null;

	public AbstractDiagramIntentProvider() {
	}

	public AbstractDiagramIntentProvider(final Class<?> partType) {
		if (partType == null);
		else if (IViewPart.class.isAssignableFrom(partType)) {
			setViewType(partType);
		} else {
			setEditorType(partType);
		}
	}

	public void setEditorType(final Class<?> editorType) {
		this.partType = editorType;
	}

	public void setViewType(final Class<?> viewType) {
		this.partType = viewType;
	}

	protected boolean isWorkbenchPart(final IWorkbenchPart part, final Class<?> partType) {
		if (partType != null) {
			if (partType.isInstance(part)) {
				return true;
			} else if (part.getAdapter(partType) != null) {
				return true;
			}
			return false;
		}
		return true;
	}

	protected <T> T getWorkbenchPart(final IWorkbenchPart part, final Class<T> partType) {
		return (T) (partType.isInstance(part) ? part : part.getAdapter(partType));
	}

	/**
	 * Is this DiagramIntentProvider interested in the given editor?
	 * @param editorPart
	 * @return
	 */
	protected boolean supportsEditor(final IEditorPart editorPart) {
		return isWorkbenchPart(editorPart, partType);
	}

	/**
	 * Is this DiagramIntentProvider interested in the given view?
	 * @param viewPart
	 * @return
	 */
	protected boolean supportsView(final IViewPart viewPart) {
		return isWorkbenchPart(viewPart, partType);
	}

	/**
	 * Is the given selection relevant and interesting for this DiagramIntentProvider?
	 * null means irrelevant, while true/false tells if it is interesting
	 * @param selection
	 * @return
	 */
	protected Boolean supportsSelection(final ISelection selection) {
		return null;
	}

	/**
	 * Is the given path relevant and interesting for this DiagramIntentProvider?
	 * null means irrelevant, while true/false tells if it is interesting
	 * @param path
	 * @return
	 */
	protected Boolean supportsPath(final IPath path) {
		return null;
	}

	//

	protected String getStartPlantUml() {
		return PlantumlConstants.START_UML;
	}

	protected String getEndPlantUml() {
		return PlantumlConstants.END_UML;
	}

	//

	@Override
	public Collection<? extends DiagramIntent> getDiagramInfos(final DiagramIntentContext context) {
		if (context instanceof WorkbenchPartDiagramIntentProviderContext) {
			final WorkbenchPartDiagramIntentProviderContext workbenchContext = (WorkbenchPartDiagramIntentProviderContext) context;
			final IWorkbenchPart workbenchPart = workbenchContext.getWorkbenchPart();
			if (workbenchPart instanceof IEditorPart) {
				if (! supportsEditor((IEditorPart) workbenchPart)) {
					return null;
				}
				if (workbenchContext.getSelection() != null && Boolean.FALSE.equals(supportsSelection(workbenchContext.getSelection()))) {
					return null;
				}
				return getDiagramInfos(workbenchContext);
			} else {
				if (! supportsView((IViewPart) workbenchPart)) {
					return null;
				}
				if (workbenchContext.getSelection() != null && Boolean.FALSE.equals(supportsSelection(workbenchContext.getSelection()))) {
					return null;
				}
				return getDiagramInfos(workbenchContext);
			}
		} else if (context instanceof WorkspaceDiagramIntentProviderContext) {
			final WorkspaceDiagramIntentProviderContext workspaceContext = (WorkspaceDiagramIntentProviderContext) context;
			if (! Boolean.FALSE.equals(supportsPath(workspaceContext.getPath()))) {
				return getDiagramInfos(workspaceContext);
			}
		}
		return null;
	}

	protected Collection<? extends DiagramIntent> getDiagramInfos(final WorkbenchPartDiagramIntentProviderContext context) {
		return null;
	}

	protected Collection<? extends DiagramIntent> getDiagramInfos(final WorkspaceDiagramIntentProviderContext context) {
		return null;
	}
}
