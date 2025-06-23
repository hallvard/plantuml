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

import java.util.Collections;
import java.util.List;

import org.eclipse.uml2.uml.AttributeOwner;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.OperationOwner;
import org.eclipse.uml2.uml.Property;

public class ClassifierUtils {
	public static List<Property> getOwnedAttributes(Classifier cl) {
		if (cl instanceof AttributeOwner) {
			return ((AttributeOwner) cl).getOwnedAttributes();
		}
		return Collections.emptyList();
	}
	
	public static List<Operation> getOwnedOperations(Classifier cl) {
		if (cl instanceof OperationOwner) {
			return ((OperationOwner) cl).getOwnedOperations();
		}
		return Collections.emptyList();
	}
}
