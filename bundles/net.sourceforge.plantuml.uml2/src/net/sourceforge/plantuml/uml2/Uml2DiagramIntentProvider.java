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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.StateMachine;

import net.sourceforge.plantuml.eclipse.utils.WorkbenchPartDiagramIntentProviderContext;
import net.sourceforge.plantuml.text.AbstractDiagramIntentProvider;
import net.sourceforge.plantuml.util.AbstractDiagramIntent;
import net.sourceforge.plantuml.util.DiagramIntent;

/**
 * Generic UML2 intent provider handling multiple diagrams
 */
public class Uml2DiagramIntentProvider extends AbstractDiagramIntentProvider {

	public Uml2DiagramIntentProvider() {
		super();
	}

	public Collection<? extends DiagramIntent> getDiagramInfos(final Package pack) {
		final Uml2ClassDiagramIntent classDiagramIntent = new Uml2ClassDiagramIntent(Collections.singletonList(pack));
		final Collection<AbstractDiagramIntent<?>> diagrams = new ArrayList<>();
		diagrams.add(classDiagramIntent);
		return diagrams;
	}

	public Collection<? extends DiagramIntent> getDiagramInfos(final Interaction ia) {
		final Uml2SeqDiagramIntent seqDiagramIntent = new Uml2SeqDiagramIntent(Collections.singletonList(ia));
		final Collection<AbstractDiagramIntent<?>> diagrams = new ArrayList<>();
		diagrams.add(seqDiagramIntent);
		return diagrams;
	}

	public Collection<? extends DiagramIntent> getDiagramInfos(final Classifier classifier) {
		AbstractDiagramIntent<?> smDiagramIntent = null;
		;
		if (classifier instanceof Class) {
			Class clazz = (Class) classifier;
			if (clazz.getOwnedConnectors().size() > 0 || clazz.getOwnedPorts().size() > 0) {
				smDiagramIntent = new Uml2ComponentDiagramIntent(Collections.singletonList(clazz));
			}
		}
		if (smDiagramIntent == null) {
			smDiagramIntent = new Uml2ClassDiagramIntent(Collections.singletonList(classifier));
		}
		final Collection<AbstractDiagramIntent<?>> diagrams = new ArrayList<>();
		diagrams.add(smDiagramIntent);
		return diagrams;
	}

	public Collection<? extends DiagramIntent> getDiagramInfos(final StateMachine sm) {
		final Uml2StateDiagramIntent smDiagramIntent = new Uml2StateDiagramIntent(Collections.singletonList(sm));
		final Collection<AbstractDiagramIntent<?>> diagrams = new ArrayList<>();
		diagrams.add(smDiagramIntent);
		return diagrams;
	}

	@Override
	public Boolean supportsSelection(final ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			final Object object = ((IStructuredSelection) selection).getFirstElement();
			if (object instanceof EObject) {
				return supportsEObject((EObject) object);
			} else if (object instanceof IAdaptable) {
				// Try to get an intrinsic adapter
				EObject eObject = ((IAdaptable) object).getAdapter(EObject.class);
				if (eObject instanceof EObject) {
					return supportsEObject((EObject) eObject);
				}
			}
		}
		return super.supportsSelection(selection);
	}

	@Override
	protected Collection<? extends DiagramIntent> getDiagramInfos(
			final WorkbenchPartDiagramIntentProviderContext context) {
		if (context.getSelection() instanceof IStructuredSelection) {
			final Object selection = ((IStructuredSelection) context.getSelection()).getFirstElement();
			EObject eObject = null;
			if (selection instanceof EObject) {
				eObject = (EObject) selection;
			} else if (selection instanceof IAdaptable) {
				// Try to get an intrinsic adapter
				eObject = ((IAdaptable) selection).getAdapter(EObject.class);
			}
			return getDiagramInfos(eObject);
		}
		return super.getDiagramInfos(context);
	}

	protected Collection<? extends DiagramIntent> getDiagramInfos(EObject eObject) {
		if (eObject instanceof Package) {
			Package pkg = getPackage(eObject);
			return getDiagramInfos(pkg);
		} else if (eObject instanceof Interaction) {
			return getDiagramInfos((Interaction) eObject);
		} else if (eObject instanceof StateMachine) {
			return getDiagramInfos((StateMachine) eObject);
		} else if (eObject instanceof Classifier) {
			return getDiagramInfos((Classifier) eObject);
		}
		return null;
	}

	protected boolean supportsEObject(final EObject selection) {
		return selection instanceof Package || selection instanceof StateMachine || selection instanceof Interaction
				|| selection instanceof Classifier;
	}

	protected Package getPackage(final EObject eObject) {
		final org.eclipse.uml2.uml.Package umlPack = (eObject instanceof org.eclipse.uml2.uml.Package)
				? (Package) eObject
				: null;
		uml2EcoreMapping.clear();
		return umlPack;
	}

	private final Map<NamedElement, ENamedElement> uml2EcoreMapping = new HashMap<NamedElement, ENamedElement>();
}
