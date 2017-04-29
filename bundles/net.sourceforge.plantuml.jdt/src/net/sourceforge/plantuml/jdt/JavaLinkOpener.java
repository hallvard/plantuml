package net.sourceforge.plantuml.jdt;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.debug.ui.actions.OpenTypeAction;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.ui.PartInitException;

import net.sourceforge.plantuml.eclipse.imagecontrol.ILinkOpener;
import net.sourceforge.plantuml.eclipse.imagecontrol.LinkData;

public class JavaLinkOpener implements ILinkOpener {

	public final static String JAVA_LINK_PREFIX = "java:";
	
	@Override
	public int supportsLink(LinkData link) {
		try {
			URI uri = new URI(link.href);
			if ("java".equals(uri.getScheme()) && getJavaElement(link) != null) {
				return CUSTOM_SUPPORT;
			}
		} catch (URISyntaxException e) {
		}
		return NO_SUPPORT;
	}

	protected IJavaElement getJavaElement(LinkData link) {
		try {
			URI uri = new URI(link.href);
			String className = uri.getPath();
			if (className != null) {
				if (className.startsWith("/")) {
					className = className.substring(1);
				}
			} else {
				className = uri.getSchemeSpecificPart();
			}
			IType type = OpenTypeAction.findTypeInWorkspace(className, false);
			String fragment = uri.getFragment();
			if (fragment != null) {
				for (IJavaElement child : type.getChildren()) {
					if (fragment.equals(child.getElementName())) {
						return child;
					}
				}
			}
			return type;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void openLink(LinkData link) {
		try {
			IJavaElement javaElement = getJavaElement(link);
			JavaUI.openInEditor(javaElement);
		} catch (PartInitException e) {
		} catch (JavaModelException e) {
		}
	}
}
