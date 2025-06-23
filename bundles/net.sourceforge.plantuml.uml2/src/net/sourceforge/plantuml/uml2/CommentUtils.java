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

import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Element;

public class CommentUtils {
	/**
	 * Convenience function that appends without indent.
	 * 
	 * @see append
	 * 
	 * @param el a UML element containing eventually comments)
	 * @param sb a stringbuilder to append to
	 */
	public static void append(Element el, StringBuilder sb) {
		append(el, sb, false);
	}

	/**
	 * If an element has owned comments, add these to the string buffer. TODO: there
	 * might be comments that are not owned but point via the annotated-element
	 * reference to an element (use reverse reference) *
	 * 
	 * @param el a UML element containing eventually comments)
	 * @param sb a string-builder to append to
	 * @param if true, append a tab
	 */
	public static void append(Element el, StringBuilder sb, boolean indent) {
		if (PlantUmlOptions.exportComments) {
			for (Comment comment : el.getOwnedComments()) {
				if (comment.getBody().length() > 0) {
					for (String cLine : comment.getBody().split("\n")) {
						if (indent) {
							sb.append("\t");
						}
						sb.append("'" + cLine + "\n");
					}
				}
			}
		}
	}
}
