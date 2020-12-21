package net.sourceforge.plantuml.eclipse.views;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Composite;

import net.sourceforge.plantuml.eclipse.Activator;
import net.sourceforge.plantuml.eclipse.imagecontrol.ILinkSupport;
import net.sourceforge.plantuml.eclipse.utils.ILinkOpener;
import net.sourceforge.plantuml.eclipse.utils.LinkData;
import net.sourceforge.plantuml.eclipse.utils.PlantumlUtil;
import net.sourceforge.plantuml.util.DiagramData;

public abstract class AbstractPlantUmlView extends AbstractDiagramSourceView implements ILinkSupport {

	protected Composite composite;

	@Override
	public void createPartControl(final Composite parent) {
		composite = parent;
		createDiagramControl(parent);
		super.createPartControl(parent);
	}

	protected abstract void createDiagramControl(final Composite parent);

	//

	protected DiagramData diagramData = null;

	@Override
	public String getDiagramText() {
		return diagramData != null ? diagramData.getTextDiagram() : null;
	}

	@Override
	protected void updateDiagramText(final String textDiagram, final IPath original, final Map<String, Object> markerAttributes) {
		if (isVisible() && textDiagram != null && (diagramData == null || (! textDiagram.equals(diagramData.getTextDiagram())))) {
			diagramData = new DiagramData(textDiagram);
			diagramData.setOriginal(original);
			diagramData.setMarkerAttributes(markerAttributes);
			updateDiagram();
		}
	}

	@Override
	public void setVisible(final boolean visible) {
		super.setVisible(visible);
		if (visible && diagramData != null) {
			updateDiagram();
		}
	}

	protected Runnable layoutComposite = () -> {
		if (! composite.isDisposed()) {
			composite.layout();
		}
	};

	protected abstract void updateDiagram(final IProgressMonitor monitor);

	protected void updateDiagram() {
		final Job job = new Job("Generate diagram") {
			@Override
			protected IStatus run(final IProgressMonitor monitor) {
				updateDiagram(monitor);
				if (! composite.isDisposed()) {
					composite.getDisplay().asyncExec(layoutComposite);
				}
				updateDiagramMarkers();
				return Status.OK_STATUS;
			}
		};
		job.schedule();
	}

	protected void updateDiagramMarkers() {
		if (diagramData.getOriginal() != null) {
			final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(diagramData.getOriginal());
			if (file != null && file.exists()) {
				PlantumlUtil.updateMarker(file, diagramData.getTextDiagram(), null, true, diagramData.getMarkerAttributes());
			}
		}
	}
	private Collection<ILinkOpener> linkOpeners = null;

	private Collection<ILinkOpener> getLinkOpeners() {
		if (linkOpeners == null) {
			linkOpeners = Arrays.asList(Activator.getDefault().getLinkOpeners());
		}
		return linkOpeners;
	}

	@Override
	public void openLink(final Object link) {
		LinkData linkData = null;
		if (link instanceof LinkData) {
			linkData = (LinkData) link;
		} else if (link != null) {
			try {
				final URI uri = new URI(link.toString());
				linkData = new LinkData();
				linkData.href = uri.toString();
			} catch (final URISyntaxException e) {
				System.out.println(e);
			}
		}
		if (linkData != null) {
			openLink(linkData);
		}
	}

	protected boolean openLink(final LinkData linkData) {
		final ILinkOpener linkOpener = findBestLinkOpener(linkData, ILinkOpener.DEFAULT_SUPPORT);
		if (linkOpener != null) {
			linkOpener.openLink(linkData);
			return true;
		}
		return false;
	}

	private ILinkOpener findBestLinkOpener(final LinkData link, final int minSupport) {
		int bestSupport = ILinkOpener.NO_SUPPORT;
		ILinkOpener best = null;
		for (final ILinkOpener linkOpener : getLinkOpeners()) {
			int support = ILinkOpener.NO_SUPPORT;
			try {
				support = linkOpener.supportsLink(link);
			} catch (final Exception e) {
			}
			if (support >= bestSupport) {
				bestSupport = support;
				best = linkOpener;
			}
		}
		return (bestSupport >= minSupport ? best : null);
	}
}
