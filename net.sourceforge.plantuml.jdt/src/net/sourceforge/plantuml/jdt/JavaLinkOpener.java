package net.sourceforge.plantuml.jdt;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.internal.debug.ui.actions.OpenTypeAction;

import net.sourceforge.plantuml.eclipse.utils.EditorLinkOpener;
import net.sourceforge.plantuml.eclipse.utils.LinkData;

public class JavaLinkOpener extends EditorLinkOpener {
	
	@Override
	public int supportsLink(LinkData link) {
		try {
			URI uri = new URI(link.href);
			if ("java".equals(uri.getScheme())) {
				return super.supportsLink(link);
			}
		} catch (URISyntaxException e) {
		}
		return NO_SUPPORT;
	}

	@Override
	protected IPath getPath(LinkData link) {
		try {
			URI uri = new URI(link.href);
			IType type = OpenTypeAction.findTypeInWorkspace(uri.getRawSchemeSpecificPart(), false);
			return type.getPath();
		} catch (Exception e) {
			return null;
		}
	}
}
