package net.sourceforge.plantuml.uml2;

import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.VisibilityKind;

public class VisibilityUtils {
	public static String visibility(NamedElement ne) {
		if (ne.getVisibility() == VisibilityKind.PRIVATE_LITERAL) {
			return "-";
		}
		else if (ne.getVisibility() == VisibilityKind.PROTECTED_LITERAL) {
			return "#";
		}
		else if (ne.getVisibility() == VisibilityKind.PACKAGE_LITERAL) {
			return "~";
		}
		return null;
	}
}
