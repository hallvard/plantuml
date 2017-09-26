package no.hal.osgi.emf.test;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.osgi.scr.Component;
import org.osgi.scr.DocumentRoot;
import org.osgi.scr.Property;
import org.osgi.scr.Provide;
import org.osgi.scr.ScrPackage;
import org.osgi.scr.util.ScrResourceFactoryImpl;

public class ScrResourceTest {

	@Before
	public void register() {
		EPackage.Registry.INSTANCE.put(ScrPackage.eNS_URI, ScrPackage.eINSTANCE);
	}
	
	public Resource loadOsgiResource(String uri) {
		ResourceSet resSet = new ResourceSetImpl();
		resSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new ScrResourceFactoryImpl());
		return resSet.getResource(URI.createURI(uri), true);
	}

	@Test
	public void testOsgiResource() {
		Resource resource = loadOsgiResource(getClass().getResource("no.hal.pg.runtime.engine.web.GameApp.xml").toString());
		EList<EObject> contents = resource.getContents();
		Assert.assertEquals(1, contents.size());
		Assert.assertTrue(contents.get(0) instanceof DocumentRoot || contents.get(0) instanceof Component);
		Component comp = contents.get(0) instanceof DocumentRoot ? ((DocumentRoot) contents.get(0)).getComponent() : (Component) contents.get(0);
		testGameAppComponent(comp);
	}

	void testGameAppComponent(Component comp) {
		Assert.assertNotNull(comp.getImplementation());
		Assert.assertEquals("no.hal.pg.runtime.engine.web.GameApp", comp.getName());
		Assert.assertEquals("no.hal.pg.runtime.engine.http.EngineAppComponent", comp.getImplementation().getClass_());
		Assert.assertNotNull(comp.getService());

		EList<Provide> provides = comp.getService().getProvide();
		Assert.assertEquals(1, provides.size());
		Assert.assertEquals("no.hal.pg.runtime.engine.http.IEngineApp", provides.get(0).getInterface());

		EList<Property> propertyList = comp.getProperty();
		Assert.assertEquals(6, propertyList.size());
		
		Property property1 = propertyList.get(0);
		Assert.assertEquals("IEngineApp.name", property1.getName());
		Assert.assertEquals("String", property1.getType().getLiteral());
		Assert.assertEquals("GameApp", property1.getValue1());
		Property property2 = propertyList.get(1);
		Assert.assertEquals("EngineAppComponent.eClass", property2.getName());
		Assert.assertEquals("String", property2.getType().getLiteral());
		Assert.assertEquals("platform:/plugin/no.hal.pg.runtime/model/pg-runtime.ecore#Game", property2.getValue1());
	}

	void testPlayerAppComponent(Component comp) {
	}
}
