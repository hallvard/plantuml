package net.sourceforge.plantuml.eclipse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import net.sourceforge.plantuml.eclipse.utils.DiagramTextProvider;
import net.sourceforge.plantuml.eclipse.utils.ILinkOpener;
import net.sourceforge.plantuml.eclipse.utils.PlantumlUtil;

/**
 * The activator class controls the plug-in life cycle
 * 
 * @author durif_c
 */
public class Activator extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "net.sourceforge.plantuml.eclipse";

    // The shared instance
    private static Activator plugin;

    /**
     * The constructor
     */
    public Activator() {
        // Empty constructor
    }

    private IResourceChangeListener resourceListener;
    
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        resourceListener = PlantumlUtil.createResourceListener();
        if (resourceListener != null) {
        	ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceListener, IResourceChangeEvent.POST_BUILD);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext context) throws Exception {
        if (resourceListener != null) {
        	ResourcesPlugin.getWorkspace().removeResourceChangeListener(resourceListener);
        }
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static Activator getDefault() {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in
     * relative path
     * 
     * @param path
     *            the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path) {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }
    
	private List<DiagramTextProvider> diagramTextProviders;
	
	public DiagramTextProvider[] getDiagramTextProviders() {
		if (diagramTextProviders == null) {
			diagramTextProviders = new ArrayList<DiagramTextProvider>();
			processDiagramTextProviders();
		}
		return diagramTextProviders.toArray(new DiagramTextProvider[diagramTextProviders.size()]);
	}

	public int DEFAULT_PRIORITY = 0, NORMAL_PRIORITY = 5, CUSTOM_PRIORITY = 10; 

	private void processDiagramTextProviders() {
		IExtensionPoint ep = Platform.getExtensionRegistry().getExtensionPoint(getBundle().getSymbolicName() + ".diagramTextProvider");
		IExtension[] extensions = ep.getExtensions();
		final Map<DiagramTextProvider, Integer> diagramTextProviders = new HashMap<DiagramTextProvider, Integer>();
		for (int i = 0; i < extensions.length; i++) {
			for (IConfigurationElement ces: extensions[i].getConfigurationElements()) {
				String name = ces.getName();
				if ("diagramTextProvider".equals(name)) {
					try {
						DiagramTextProvider diagramTextProvider = (DiagramTextProvider) ces.createExecutableExtension("providerClass");
						String priorityValue = ces.getAttribute("priority");
						int priority = NORMAL_PRIORITY;
						if ("custom".equals(priorityValue)) {
							priority = CUSTOM_PRIORITY;
						} else if ("normal".equals(priorityValue)) {
							priority = NORMAL_PRIORITY;
						} else if ("default".equals(priorityValue)) {
							priority = DEFAULT_PRIORITY;
						} else {
							try {
								priority = Integer.valueOf(priorityValue);
							} catch (NumberFormatException e) {
							}
						}
						diagramTextProviders.put(diagramTextProvider, priority);
					} catch (InvalidRegistryObjectException e) {
					} catch (CoreException e) {
					}
				}
			}
		}
		this.diagramTextProviders.addAll(diagramTextProviders.keySet());
		Collections.sort(this.diagramTextProviders, new Comparator<DiagramTextProvider>() {
			@Override
			public int compare(DiagramTextProvider dtp2, DiagramTextProvider dtp1) {
				return diagramTextProviders.get(dtp1) - diagramTextProviders.get(dtp2);
			}
		});
	}
	
	private List<ILinkOpener> linkOpeners;
	
	public ILinkOpener[] getLinkOpeners() {
		if (linkOpeners == null) {
			linkOpeners = new ArrayList<ILinkOpener>();
			processLinkOpeners();
		}
		return linkOpeners.toArray(new ILinkOpener[linkOpeners.size()]);
	}
	
	private void processLinkOpeners() {
		IExtensionPoint ep = Platform.getExtensionRegistry().getExtensionPoint(getBundle().getSymbolicName() + ".linkOpener");
		IExtension[] extensions = ep.getExtensions();
		for (int i = 0; i < extensions.length; i++) {
			for (IConfigurationElement ces: extensions[i].getConfigurationElements()) {
				String name = ces.getName();
				if ("linkOpener".equals(name)) {
					try {
						ILinkOpener linkOpener = (ILinkOpener) ces.createExecutableExtension("linkOpenerClass");
						linkOpeners.add(linkOpener);
					} catch (InvalidRegistryObjectException e) {
					} catch (CoreException e) {
					}
				}
			}
		}
		
	}
}
