package net.sourceforge.plantuml.eclipse.svg;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import net.sourceforge.plantuml.eclipse.imagecontrol.ILinkSupport;
import net.sourceforge.plantuml.eclipse.views.AbstractPlantUmlView;
import net.sourceforge.plantuml.util.DiagramData;

public class PlantUmlSvgView extends AbstractPlantUmlView implements ILinkSupport {

	private Browser browser;

	@Override
	protected void createDiagramControl(final Composite parent) {
		final GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		parent.setLayout(layout);
		browser = new Browser(parent, SWT.NONE);
		browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		browser.setJavascriptEnabled(true);
		browser.addLocationListener(new LocationListener() {
			@Override
			public void changing(final LocationEvent event) {
				openLink(event.location);
			}
			@Override
			public void changed(final LocationEvent event) {
			}
		});
	}

	@Override
	public void dispose() {
		browser = null;
		super.dispose();
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
		if (isValidControl(browser)) {
			browser.setFocus();
		}
	}

	private final Svg2HtmlConverter svg2HtmlConverter = new Svg2InteractiveHtmlConverter();

	@Override
	protected void updateDiagram(final DiagramData diagramData, final IProgressMonitor monitor) {
		final String svg = diagramData.getSvg(0);
		final String html = svg2HtmlConverter.convert2Html(svg);
		setDiagramViewStatus(ViewStatus.DIAGRAM_VIEW_DATA, html);
		asyncExec(() -> {
			if (isValidControl(browser) && shouldUpdateView(diagramData)) {
				browser.setText(html);
				setDiagramViewStatus(ViewStatus.DIAGRAM_VIEW, html);
			}
		});
	}

	@Override
	public Object getLink(final int x, final int y) {
		return null;
	}
}
