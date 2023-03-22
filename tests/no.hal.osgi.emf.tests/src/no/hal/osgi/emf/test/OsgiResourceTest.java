package no.hal.osgi.emf.test;

import java.util.Arrays;

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
import org.osgi.scr.util.ScrResourceFactoryImpl;

import no.hal.osgi.ActivationPolicyKind;
import no.hal.osgi.Bundle;
import no.hal.osgi.Manifest;
import no.hal.osgi.OsgiPackage;
import no.hal.osgi.RequiredBundle;
import no.hal.osgi.RequiredExecutionEnvironmentKind;
import no.hal.osgi.ServiceComponent;
import no.hal.osgi.VisibilityKind;
import no.hal.osgi.util.OsgiResourceFactoryImpl;

public class OsgiResourceTest {

	private ScrResourceTest srcResourceTest;
	
	@Before
	public void register() {
		srcResourceTest = new ScrResourceTest();
		EPackage.Registry.INSTANCE.put(OsgiPackage.eNS_URI, OsgiPackage.eINSTANCE);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("MF", new OsgiResourceFactoryImpl());
		srcResourceTest.register();
	}
	
	public Resource loadOsgiResource(String uri) {
		ResourceSet resSet = new ResourceSetImpl();
		resSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new ScrResourceFactoryImpl());
		return resSet.getResource(URI.createURI(uri), true);
	}

	@Test
	public void testOsgiResource() {
		Resource resource = loadOsgiResource(getClass().getResource("MANIFEST1.MF").toString());
		EList<EObject> contents = resource.getContents();
		Assert.assertEquals(2, contents.size());

		Assert.assertTrue(contents.get(0) instanceof Manifest);
		Manifest manifest = (Manifest) contents.get(0);
		Assert.assertEquals("1.0", String.valueOf(manifest.getVersion()));
		
		Assert.assertTrue(contents.get(1) instanceof Bundle);
		Bundle bundle = (Bundle) contents.get(1);
		testManifest1Bundle(bundle);

		EList<ServiceComponent> serviceComponents = bundle.getServiceComponent();
		Assert.assertEquals(2, serviceComponents.size());
		ServiceComponent sc1 = serviceComponents.get(0), sc2 = serviceComponents.get(1);
		Assert.assertEquals("no.hal.pg.runtime.engine.web.GameApp.xml", sc1.getPath().toString());
		Assert.assertEquals("no.hal.pg.runtime.engine.web.PlayerApp.xml", sc2.getPath().toString());
		
		Component comp1 = sc1.getComponent(), comp2 = sc2.getComponent();
		Assert.assertFalse(comp1.eIsProxy());
		Assert.assertFalse(comp2.eIsProxy());

		srcResourceTest.testGameAppComponent(comp1);
		srcResourceTest.testPlayerAppComponent(comp2);
	}

	private void testManifest1Bundle(Bundle bundle) {
		Assert.assertEquals("2", String.valueOf(bundle.getManifestVersion()));
		Assert.assertEquals("%pluginName", bundle.getName());
		Assert.assertEquals("no.hal.pg.runtime.web", bundle.getSymbolicName().toString());
		Assert.assertEquals("0.1.0.qualifier", String.valueOf(bundle.getVersion()));
		Assert.assertEquals(Arrays.asList("."), bundle.getClassPath());
		Assert.assertEquals("%providerName", bundle.getVendor());
		Assert.assertEquals(ActivationPolicyKind.LAZY, bundle.getActivationPolicy());
//		Assert.assertEquals("plugin", bundle.getLocalization());
		Assert.assertEquals(Arrays.asList(RequiredExecutionEnvironmentKind.JAVA8), bundle.getRequiredExecutionEnvironment());
		EList<RequiredBundle> requireBundles = bundle.getRequireBundle();
		Assert.assertEquals(3, requireBundles.size());
		RequiredBundle rb1 = requireBundles.get(0), rb2 = requireBundles.get(1), rb3 = requireBundles.get(2);
		Assert.assertEquals("org.eclipse.emf.ecore", rb1.getName().toString());
		Assert.assertNull(rb1.getBundleVersion());
		Assert.assertEquals(VisibilityKind.REEXPORT, rb1.getVisibility());
		Assert.assertEquals("no.hal.pg.runtime", rb2.getName().toString());
		Assert.assertEquals("0.0.1", String.valueOf(rb2.getBundleVersion().minimum));
		Assert.assertEquals(null, rb2.getBundleVersion().maximum);
		Assert.assertEquals("no.hal.pg.http", rb3.getName().toString());
		Assert.assertEquals("0.0.1", String.valueOf(rb3.getBundleVersion().minimum));
		Assert.assertEquals(null, rb3.getBundleVersion().maximum);
	}
}
