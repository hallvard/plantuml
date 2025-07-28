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

import java.util.List;

import org.eclipse.uml2.uml.Classifier;

public class PlantUmlOptions {
	/**
	 * if true, use simple comments via preceding '
	 */

	public static boolean exportComments = true;

	/**
	 * use qualified names
	 */
	public static boolean useQName = true;

	/**
	 * if true, skip first element of a qualified name
	 */
	public static boolean skipFirst = true;

	/**
	 * 
	 */
	public static List<Classifier> filterList = null;
}
