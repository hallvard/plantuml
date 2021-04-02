package net.sourceforge.plantuml.text;

import java.util.regex.Pattern;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import net.sourceforge.plantuml.eclipse.DiagramIntentProviderInfo;
import net.sourceforge.plantuml.eclipse.DiagramTextProviderProcessor;
import net.sourceforge.plantuml.eclipse.DiagramIntentProviderRegistry;

/**
 * The activator class controls the plug-in life cycle
 *
 * @author durif_c
 */
public class Activator extends Plugin implements DiagramTextProviderProcessor {

	private static Activator plugin;

	public static Activator getDefault() {
		return plugin;
	}

	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		final net.sourceforge.plantuml.eclipse.Activator plantumlActivator = net.sourceforge.plantuml.eclipse.Activator.getDefault();
		plantumlActivator.addDiagramTextProviderProcessor(this);
	}

	@Override
	public void stop(final BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	@Override
	public void processDiagramIntentProviders(final DiagramIntentProviderRegistry registry) {
		final IExtensionPoint ep = Platform.getExtensionRegistry().getExtensionPoint("net.sourceforge.plantuml.text.textDiagramProvider");
		final IExtension[] extensions = ep.getExtensions();
		for (int i = 0; i < extensions.length; i++) {
			for (final IConfigurationElement ces: extensions[i].getConfigurationElements()) {
				final String name = ces.getName();
				if ("textDiagramProvider".equals(name)) {
					try {
						final String diagramPrefix = ces.getAttribute("diagramPrefix");
						final String diagramSuffix = ces.getAttribute("diagramSuffix");
						final String fileExtensionsString = ces.getAttribute("fileExtensions");
						final String[] fileExtensions = (fileExtensionsString != null ? fileExtensionsString.split("[, ]") : new String[0]);
						final TextEditorDiagramIntentProvider textDiagramIntentProvider = new TextEditorDiagramIntentProvider(
								diagramPrefix, diagramSuffix,
								fileExtensions
								);
						String diagramPrefixRegex = ces.getAttribute("diagramPrefixRegex");
						if (diagramPrefixRegex == null) {
							diagramPrefixRegex = Pattern.quote(diagramPrefix);
						}
						String diagramSuffixRegex = ces.getAttribute("diagramSuffixRegex");
						if (diagramSuffixRegex == null) {
							diagramSuffixRegex = Pattern.quote(diagramSuffix);
						}
						textDiagramIntentProvider.setDiagramPrefixRegex(diagramPrefixRegex);
						textDiagramIntentProvider.setDiagramSuffixRegex(diagramSuffixRegex);

						final int priority = net.sourceforge.plantuml.eclipse.Activator.NORMAL_PRIORITY;
						final DiagramIntentProviderInfo info = new DiagramIntentProviderInfo();
						info.id = ces.getAttribute("id");
						info.label = ces.getAttribute("label");
						info.priority = priority;
						registry.registerDiagramIntentProvider(textDiagramIntentProvider, info);
					} catch (final InvalidRegistryObjectException e) {
					}
				}
			}
		}
	}
}
