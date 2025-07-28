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

package net.sourceforge.plantuml.text.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.StateMachine;
import org.junit.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import net.sourceforge.plantuml.eclipse.test.util.AbstractDiagramTextTest;
import net.sourceforge.plantuml.text.AbstractDiagramIntent;
import net.sourceforge.plantuml.uml2.Uml2DiagramIntentProvider;
import net.sourceforge.plantuml.util.DiagramIntent;

public class UMLDiagramsTests extends AbstractDiagramTextTest {

	/**
	 * Path for diagram folder (UML models)
	 */
	public static final String MODELS_FOLDER = "/net.sourceforge.plantuml.uml2.tests/testModels/";

	/**
	 * Path for plant UML files with expected output
	 */
	public static final String EXP_FOLDER = "expectedOutput/";

	@Test
	public void testClassDiag() throws Exception {
		Model model = getModel("TestClassDiag.uml");

		Uml2DiagramIntentProvider provider = new Uml2DiagramIntentProvider();
		Collection<? extends DiagramIntent> diagramIntent = provider.getDiagramInfos(model);
		assert (!diagramIntent.isEmpty());
		assert (diagramIntent.toArray()[0] instanceof AbstractDiagramIntent);
		AbstractDiagramIntent<?> firstDiag = (AbstractDiagramIntent<?>) diagramIntent.toArray()[0];
		assertFileEquality("TestClassDiag.puml", firstDiag.getDiagramText());
	}

	@Test
	public void testUseCaseDiag() throws Exception {
		Model model = getModel("TestUseCaseDiag.uml");

		Uml2DiagramIntentProvider provider = new Uml2DiagramIntentProvider();
		Collection<? extends DiagramIntent> diagramIntent = provider.getDiagramInfos(model);
		assert (!diagramIntent.isEmpty());
		assert (diagramIntent.toArray()[0] instanceof AbstractDiagramIntent);
		AbstractDiagramIntent<?> firstDiag = (AbstractDiagramIntent<?>) diagramIntent.toArray()[0];
		assertFileEquality("TestUseCaseDiag.puml", firstDiag.getDiagramText());
	}

	@Test
	public void testCompositeDiag() throws Exception {
		Model model = getModel("TestCompositeDiag.uml");
		PackageableElement system = model.getPackagedElement("System");
		assert (system instanceof org.eclipse.uml2.uml.Class);

		Uml2DiagramIntentProvider provider = new Uml2DiagramIntentProvider();
		Collection<? extends DiagramIntent> diagramIntent = provider
				.getDiagramInfos((org.eclipse.uml2.uml.Class) system);
		assert (!diagramIntent.isEmpty());
		assert (diagramIntent.toArray()[0] instanceof AbstractDiagramIntent);
		AbstractDiagramIntent<?> firstDiag = (AbstractDiagramIntent<?>) diagramIntent.toArray()[0];
		assertFileEquality("TestCompositeDiag.puml", firstDiag.getDiagramText());
	}

	@Test
	public void testStateMachineDiag() throws Exception {
		Model model = getModel("TestStateMachineDiag.uml");
		PackageableElement classWithSM = model.getPackagedElement("ClassWithSM");
		assert (classWithSM instanceof org.eclipse.uml2.uml.Class);
		Behavior sm = ((org.eclipse.uml2.uml.Class) classWithSM).getClassifierBehavior();
		assert (sm instanceof StateMachine);

		Uml2DiagramIntentProvider provider = new Uml2DiagramIntentProvider();
		Collection<? extends DiagramIntent> diagramIntent = provider.getDiagramInfos(sm);
		assert (!diagramIntent.isEmpty());
		assert (diagramIntent.toArray()[0] instanceof AbstractDiagramIntent);
		AbstractDiagramIntent<?> firstDiag = (AbstractDiagramIntent<?>) diagramIntent.toArray()[0];
		assertFileEquality("TestStateMachineDiag.puml", firstDiag.getDiagramText());
	}

	/**
	 * Get a UML model from the given filename, assuming that it is stored in the
	 * plugin's MODELS_FOLDER
	 * 
	 * @param fileName the model file (UML file)
	 * @return the root model
	 */
	private Model getModel(String fileName) {
		ResourceSet rs = new ResourceSetImpl();
		URI uri = URI.createPlatformPluginURI(MODELS_FOLDER + fileName, true);
		Resource r = rs.getResource(uri, true);
		EObject root = r.getContents().get(0);
		assert (root instanceof Model);
		return (Model) root;
	}

	/**
	 * Check whether the computed diagram text is equal to the expected folder
	 * 
	 * @param expFileName the filename of the expected file
	 * @param diagText    the calculated file contents
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private void assertFileEquality(String expFileName, String diagText) throws URISyntaxException, IOException {
		Bundle srcBundle = FrameworkUtil.getBundle(UMLDiagramsTests.class);
		URL fileURL = srcBundle.getEntry(EXP_FOLDER + expFileName);

		File file = new File(FileLocator.resolve(fileURL).toURI());
		assert (file.isFile());

		BufferedReader br = new BufferedReader(new FileReader(file));
		String expectedContents = "";
		while (br.ready()) {
			expectedContents += br.readLine() + "\n";
		}
		br.close();
		assert (expectedContents.equals(diagText));
	}
}
