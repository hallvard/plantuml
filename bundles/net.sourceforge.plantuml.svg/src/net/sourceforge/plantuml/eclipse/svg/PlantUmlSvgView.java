package net.sourceforge.plantuml.eclipse.svg;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

import net.sourceforge.plantuml.eclipse.Activator;
import net.sourceforge.plantuml.eclipse.imagecontrol.ILinkSupport;
import net.sourceforge.plantuml.eclipse.views.AbstractPlantUmlView;
import net.sourceforge.plantuml.util.DiagramData;

public class PlantUmlSvgView extends AbstractPlantUmlView implements ILinkSupport {

	private Browser browser;

	private Svg2HtmlConverter svg2HtmlConverter;

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
		svg2HtmlConverter = new SvgMustache2HtmlConverter();
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

	protected void updateSvg2HtmlConverter() {
		if (svg2HtmlConverter instanceof SvgMustache2HtmlConverter) {
			final IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
			final String templateUrlPreference = SvgPreferencePage.getTemplateUrlPreference(preferenceStore);
			((SvgMustache2HtmlConverter) svg2HtmlConverter).setTemplateUrl(templateUrlPreference);
		}
	}

	private String diagramHtml = null;

	@Override
	protected void updateDiagram(final DiagramData diagramData, final IProgressMonitor monitor) {
		final String svg = diagramData.getSvg(0);
		updateSvg2HtmlConverter();
		this.diagramHtml = svg2HtmlConverter.convert2Html(svg);
		setDiagramViewStatus(ViewStatus.DIAGRAM_VIEW_DATA, diagramHtml);
		asyncExec(() -> {
			if (isValidControl(browser) && shouldUpdateView(diagramData)) {
				browser.setText(diagramHtml);
				setDiagramViewStatus(ViewStatus.DIAGRAM_VIEW, diagramHtml);
			}
		});
	}

	@Override
	public Object getLink(final int x, final int y) {
		return null;
	}

	//

	private static ImageDescriptor browserImage(final String name) {
		try {
			return ImageDescriptor.createFromURL(new URL("platform:/plugin/org.eclipse.ui.browser/icons/obj16/" + name + ".png"));
		} catch (final MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void addViewActions(final IContributionManager toolBarManager) {
		super.addViewActions(toolBarManager);
		addActions(toolBarManager, new Action("Open external browser", browserImage("external_browser")) {
			@Override
			public void run() {
				openBrowser(true);
			}
		});
	}

	private final int browserStyle = IWorkbenchBrowserSupport.LOCATION_BAR | IWorkbenchBrowserSupport.NAVIGATION_BAR | IWorkbenchBrowserSupport.STATUS;

	protected void openBrowser(final boolean external) {
		if (this.diagramHtml != null) {
			try {
				final IWorkbenchBrowserSupport browserSupport = PlatformUI.getWorkbench().getBrowserSupport();
				final IWebBrowser extBrowser = (external ? browserSupport.getExternalBrowser()
						: browserSupport.createBrowser(browserStyle, toString(), getPartName(), getTitle()));
				final Path tempFile = Files.createTempFile(getClass().getSimpleName(), ".html");
				Files.write(tempFile, this.diagramHtml.getBytes());
				extBrowser.openURL(tempFile.toUri().toURL());
			} catch (final PartInitException e) {
				// couldn't open browser
			} catch (final IOException e) {
				// couldn't write to temp file
			}
		}
	}
}
