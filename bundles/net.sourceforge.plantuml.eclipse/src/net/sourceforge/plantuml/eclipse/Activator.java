package net.sourceforge.plantuml.eclipse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
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
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import net.sourceforge.plantuml.eclipse.utils.DiagramTextProvider;
import net.sourceforge.plantuml.eclipse.utils.ILinkOpener;
import net.sourceforge.plantuml.eclipse.utils.PlantumlUtil;
import net.sourceforge.plantuml.preferences.DiagramTextProvidersPreferencePage;

/**
 * The activator class controls the plug-in life cycle
 *
 * @author durif_c
 */
public class Activator extends AbstractUIPlugin implements DiagramTextProviderRegistry {

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
	@Override
	public void start(final BundleContext context) throws Exception {
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
	@Override
	public void stop(final BundleContext context) throws Exception {
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
	public static ImageDescriptor getImageDescriptor(final String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	private List<DiagramTextProvider> diagramTextProviders = null;

	private Map<DiagramTextProvider, DiagramTextProviderInfo> diagramTextProviderInfo = null;

	public boolean isEnabled(final DiagramTextProvider diagramTextProvider) {
		final IPreferenceStore preferenceStore = getPreferenceStore();
		final String id = getDiagramTextProviderId(diagramTextProvider);
		return ! preferenceStore.getBoolean(DiagramTextProvidersPreferencePage.getDiagramTextProviderDisablementKey(id));
	}

	private final Collection<DiagramTextProviderProcessor> diagramTextProviderProcessors = new ArrayList<>();

	public void addDiagramTextProviderProcessor(final DiagramTextProviderProcessor diagramTextProviderProcessor) {
		if (diagramTextProviders == null) {
			diagramTextProviderProcessors.add(diagramTextProviderProcessor);
		} else {
			diagramTextProviderProcessor.processDiagramTextProviders(this);
		}
	}

	public DiagramTextProvider[] getDiagramTextProviders(final Boolean enabled) {
		if (diagramTextProviders == null) {
			diagramTextProviders = new ArrayList<DiagramTextProvider>();
			diagramTextProviderInfo = new HashMap<DiagramTextProvider, DiagramTextProviderInfo>();
			processDiagramTextProviders();
			for (final DiagramTextProviderProcessor diagramTextProviderProcessor : diagramTextProviderProcessors) {
				diagramTextProviderProcessor.processDiagramTextProviders(this);
			}
		}
		Collection<DiagramTextProvider> diagramTextProviders = this.diagramTextProviders;
		if (enabled != null) {
			diagramTextProviders = new ArrayList<>(diagramTextProviders);
			final Iterator<DiagramTextProvider> it = diagramTextProviders.iterator();
			while (it.hasNext()) {
				if (enabled != isEnabled(it.next())) {
					it.remove();
				}
			}
		}
		return diagramTextProviders.toArray(new DiagramTextProvider[diagramTextProviders.size()]);
	}

	public String getDiagramTextProviderId(final DiagramTextProvider diagramTextProvider) {
		if (diagramTextProviderInfo != null) {
			final DiagramTextProviderInfo info = diagramTextProviderInfo.get(diagramTextProvider);
			final String id = info != null ? info.id : null;
			return id != null ? id : diagramTextProvider.getClass().getName();
		}
		return null;
	}

	public String getDiagramTextProviderLabel(final DiagramTextProvider diagramTextProvider) {
		if (diagramTextProviderInfo != null) {
			final DiagramTextProviderInfo info = diagramTextProviderInfo.get(diagramTextProvider);
			return info != null ? info.label : null;
		}
		return null;
	}

	public final static int DEFAULT_PRIORITY = 0, NORMAL_PRIORITY = 5, CUSTOM_PRIORITY = 10;

	private void processDiagramTextProviders() {
		final IExtensionPoint ep = Platform.getExtensionRegistry().getExtensionPoint(getBundle().getSymbolicName() + ".diagramTextProvider");
		final IExtension[] extensions = ep.getExtensions();
		for (int i = 0; i < extensions.length; i++) {
			for (final IConfigurationElement ces: extensions[i].getConfigurationElements()) {
				final String name = ces.getName();
				if ("diagramTextProvider".equals(name)) {
					try {
						final DiagramTextProvider diagramTextProvider = (DiagramTextProvider) ces.createExecutableExtension("providerClass");
						final String priorityValue = ces.getAttribute("priority");
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
							} catch (final NumberFormatException e) {
							}
						}
						final DiagramTextProviderInfo info = new DiagramTextProviderInfo();
						info.id = ces.getAttribute("id");
						info.label = ces.getAttribute("label");
						info.priority = priority;
						registerDiagramTextProvider(diagramTextProvider, info);
					} catch (final InvalidRegistryObjectException e) {
					} catch (final CoreException e) {
					}
				}
			}
		}
	}

	private final Comparator<DiagramTextProvider> priorityComparator = new Comparator<DiagramTextProvider>() {
		@Override
		public int compare(final DiagramTextProvider dtp2, final DiagramTextProvider dtp1) {
			return Activator.this.diagramTextProviderInfo.get(dtp1).priority - Activator.this.diagramTextProviderInfo.get(dtp2).priority;
		}
	};

	@Override
	public void registerDiagramTextProvider(final DiagramTextProvider diagramTextProvider, final DiagramTextProviderInfo info) {
		this.diagramTextProviderInfo.put(diagramTextProvider, info);
		int pos = 0;
		while (pos < diagramTextProviders.size()) {
			if (priorityComparator.compare(diagramTextProvider, diagramTextProviders.get(pos)) < 0) {
				break;
			}
			pos++;
		}
		this.diagramTextProviders.add(pos, diagramTextProvider);
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
		final IExtensionPoint ep = Platform.getExtensionRegistry().getExtensionPoint(getBundle().getSymbolicName() + ".linkOpener");
		final IExtension[] extensions = ep.getExtensions();
		for (int i = 0; i < extensions.length; i++) {
			for (final IConfigurationElement ces: extensions[i].getConfigurationElements()) {
				final String name = ces.getName();
				if ("linkOpener".equals(name)) {
					try {
						final ILinkOpener linkOpener = (ILinkOpener) ces.createExecutableExtension("linkOpenerClass");
						linkOpeners.add(linkOpener);
					} catch (final InvalidRegistryObjectException e) {
					} catch (final CoreException e) {
					}
				}
			}
		}
	}
}
