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

public class IndentUtils {
	public static String INDENT_CHAR = "\t"; //$NON-NLS-1$

	public static void indentOne(StringBuilder buffer) {
		buffer.append(INDENT_CHAR);
	}

	public static void indentBlock(StringBuilder buffer, String text) {
		for (String line : text.split("\\n")) {
			buffer.append(String.format("%s%s\n", INDENT_CHAR, line));
		}
	}
}
