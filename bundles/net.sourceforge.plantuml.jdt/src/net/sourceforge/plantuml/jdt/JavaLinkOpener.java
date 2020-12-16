package net.sourceforge.plantuml.jdt;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.debug.ui.actions.OpenTypeAction;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.ui.PartInitException;

import net.sourceforge.plantuml.eclipse.utils.ILinkOpener;
import net.sourceforge.plantuml.eclipse.utils.LinkData;

public class JavaLinkOpener implements ILinkOpener {

	public final static String JAVA_LINK_PREFIX = "java:";

	@Override
	public int supportsLink(final LinkData link) {
		try {
			final URI uri = new URI(link.href);
			if ("java".equals(uri.getScheme()) && getJavaElement(link) != null) {
				return CUSTOM_SUPPORT;
			}
		} catch (final URISyntaxException e) {
		}
		return NO_SUPPORT;
	}

	protected IJavaElement getJavaElement(final LinkData link) {
		try {
			final URI uri = new URI(link.href);
			String className = uri.getPath();
			if (className != null) {
				if (className.startsWith("/")) {
					className = className.substring(1);
				}
			} else {
				className = uri.getSchemeSpecificPart();
			}
			final IType type = OpenTypeAction.findTypeInWorkspace(className, false);
			final String fragment = uri.getFragment();
			if (fragment != null) {
				for (final IJavaElement child : type.getChildren()) {
					if (fragment.equals(child.getElementName())) {
						return child;
					}
				}
			}
			return type;
		} catch (final Exception e) {
			return null;
		}
	}

	@Override
	public void openLink(final LinkData link) {
		try {
			final IJavaElement javaElement = getJavaElement(link);
			JavaUI.openInEditor(javaElement);
		} catch (final PartInitException e) {
		} catch (final JavaModelException e) {
		}
	}
}
