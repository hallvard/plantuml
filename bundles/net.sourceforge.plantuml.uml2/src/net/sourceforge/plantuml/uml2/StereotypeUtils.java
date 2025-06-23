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

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Vertex;

/**
 * Utility class around plant-uml stereotypes
 */
public class StereotypeUtils {
	
	/**
	 * Some pseudo states use pseudo stereotypes
	 */
	private static final String ST_FORK = "fork"; //$NON-NLS-1$
	private static final String ST_JOIN = "join"; //$NON-NLS-1$
	private static final String ST_CHOICE = "choice"; //$NON-NLS-1$
	
	/**
	 * @param element a UML element
	 * @param pre     if true, use stereotype names as prefix (i.e. add a space
	 *                afterwards, if there are any, add space before otherwise)
	 * @return a string with the stereotype names (comma separated and in << >>>)
	 */
	public static String stereoNames(Element element, boolean pre) {
		String stNames = "";
		if (element instanceof Vertex) {
			String psStateSt = stateStereo((Vertex) element);
			if (psStateSt != null) {
				stNames = psStateSt;
			}
		}
		for (Stereotype st : element.getAppliedStereotypes()) {
			if (stNames.length() > 0) {
				stNames += ", ";
			}
			stNames += st.getName();
		}
		if (stNames.length() > 0) {
			if (pre) {
				return String.format("<<%s>> ", stNames);
			} else {
				return String.format(" <<%s>>", stNames);
			}
		}
		return stNames;
	}
	
	/**
	 * Some states need a pseudo stereotype in PlantUML, notably Fork, Join and Choice (this in not the case in UML)
	 * 
	 * @param vertex
	 *            a vertex
	 * @return the pseudo stereotype to use
	 */
	public static String stateStereo(Vertex vertex) {
		if (vertex instanceof Pseudostate) {
			PseudostateKind kind = ((Pseudostate) vertex).getKind();
			if (kind == PseudostateKind.FORK_LITERAL) {
				return ST_FORK;
			} else if (kind == PseudostateKind.JOIN_LITERAL) {
				return ST_JOIN;
			} else if (kind == PseudostateKind.CHOICE_LITERAL) {
				return ST_CHOICE;
			}
		}
		return null;
	}
}
