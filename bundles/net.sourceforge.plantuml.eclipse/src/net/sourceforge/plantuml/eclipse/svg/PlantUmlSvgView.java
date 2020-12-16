package net.sourceforge.plantuml.eclipse.views;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.swt.widgets.Composite;

import net.sourceforge.plantuml.eclipse.imagecontrol.ILinkSupport;
import net.sourceforge.plantuml.util.Svg2HtmlConverter;

public class PlantUmlSvgView extends AbstractPlantUmlView implements ILinkSupport {

	private Browser browser;

	@Override
	public void createPartControl(final Composite parent) {
		super.createPartControl(parent);
	}

	@Override
	protected void createDiagramControl(final Composite parent) {
		browser = new Browser(parent, SWT.NONE);
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

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
		if (browser != null) {
			browser.setFocus();
		}
	}

	private final Svg2HtmlConverter svg2HtmlConverter = new Svg2HtmlConverter();

	@Override
	protected void updateDiagram(final IProgressMonitor monitor) {
		final String svg = diagramData.getSvg(0);
		final String html = svg2HtmlConverter.convert2Html(svg);
		if (! browser.isDisposed()) {
			browser.getDisplay().asyncExec(() -> {
				if (! browser.isDisposed()) {
					browser.setText(html);
				}
			});
		}
	}

	@Override
	public Object getLink(final int x, final int y) {
		return null;
	}
}
