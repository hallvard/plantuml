/**
 * Copyright (c) 2025 CEA LIST and others
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package net.sourceforge.plantuml.uml2;

import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.VisibilityKind;

/**
 * Handle visibility of UML elements
 */
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
