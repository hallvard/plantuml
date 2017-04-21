package net.sourceforge.plantuml.eclipse.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

public class DefaultLinkOpener implements ILinkOpener {
	
	@Override
	public int supportsLink(LinkData link) {
		try {
			URI uri = new URI(link.href);
			if (uri.getScheme() != null) {
				return DEFAULT_SUPPORT;
			}
		} catch (URISyntaxException e) {
		}
		return NO_SUPPORT;
	}

	private boolean preferInternal = false;
	
	@Override
	public void openLink(LinkData link) {
		try {
			URL url = new URL(link.href);
			IWorkbenchBrowserSupport browserSupport = PlatformUI.getWorkbench().getBrowserSupport();
			IWebBrowser browser = (preferInternal ? browserSupport.createBrowser(IWorkbenchBrowserSupport.AS_VIEW, "plantuml", "PlantUML Browser", null) : browserSupport.getExternalBrowser());
			browser.openURL(url);
		} catch (Exception e) {
		}
	}
}
