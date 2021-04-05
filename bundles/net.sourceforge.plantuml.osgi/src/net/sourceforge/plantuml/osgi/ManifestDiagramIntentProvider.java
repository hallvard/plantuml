package net.sourceforge.plantuml.osgi;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPathEditorInput;
import org.osgi.scr.Component;

import net.sourceforge.plantuml.eclipse.utils.WorkbenchEditorPartDiagramIntentProviderContext;
import net.sourceforge.plantuml.text.AbstractDiagramIntentProvider;
import net.sourceforge.plantuml.util.DiagramIntent;
import no.hal.osgi.Bundle;
import no.hal.osgi.ExportedPackage;
import no.hal.osgi.OsgiPackage;
import no.hal.osgi.RequiredBundle;
import no.hal.osgi.ServiceComponent;

public class ManifestDiagramIntentProvider extends AbstractDiagramIntentProvider {

	public ManifestDiagramIntentProvider() {
		super(null);
	}

	@Override
	public boolean supportsEditor(final IEditorPart editorPart) {
		if (editorPart.getEditorInput() instanceof IPathEditorInput) {
			final IPathEditorInput editorInput = (IPathEditorInput) editorPart.getEditorInput();
			if ("MF".equals(editorInput.getPath().getFileExtension())) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected Collection<DiagramIntent> getDiagramInfos(final WorkbenchEditorPartDiagramIntentProviderContext context) {
		final IEditorInput editorInput = context.getEditorPart().getEditorInput();
		if (editorInput instanceof IPathEditorInput) {
			final IPath path = ((IPathEditorInput) editorInput).getPath();
			final URI uri = URI.createFileURI(path.toString());
			return Collections.singletonList(new OsgiComponentDiagramIntent(uri) {

				@Override
				protected String getDiagramText(final Resource res) {
					final StringBuilder buffer = new StringBuilder();
					final Bundle bundle = (Bundle) EcoreUtil.getObjectByType(res.getContents(), OsgiPackage.eINSTANCE.getBundle());
					appendModule(bundle, buffer);
					final Collection<RequiredBundle> requiredBundles = EcoreUtil.getObjectsByType(bundle.getRequireBundle(), OsgiPackage.eINSTANCE.getRequiredBundle());
					for (final RequiredBundle requiredBundle : requiredBundles) {
						appendModule(requiredBundle, buffer);
						appendDependency(bundle, requiredBundle, buffer);
					}
					return buffer.toString();
				}

				protected void appendDependency(final String source, final String target, final String label, final StringBuilder buffer) {
					buffer.append(source);
					buffer.append(" --> ");
					buffer.append(target);
					if (label != null) {
						buffer.append(": ");
						buffer.append(label);
					}
					buffer.append("\n");
				}

				protected String getBundleId(final Bundle bundle) {
					String id = "bundle." + bundle.getSymbolicName();
					if (bundle.getVersion() != null) {
						id += "." + bundle.getVersion();
					}
					return id;
				}

				protected String getBundleId(final RequiredBundle bundle) {
					final String id = "bundle." + bundle.getName();
					//		if (bundle.getVersion() != null) {
					//			id += "." + bundle.getVersion();
					//		}
					return id;
				}

				protected void appendDependency(final Bundle bundle, final RequiredBundle requiredBundle, final StringBuilder buffer) {
					final String versionString = (requiredBundle.getBundleVersion() != null ? String.valueOf(requiredBundle.getBundleVersion()) : null);
					appendDependency(getBundleId(bundle), getBundleId(requiredBundle), versionString, buffer);
				}

				protected void appendModule(final RequiredBundle bundle, final StringBuilder buffer) {
					buffer.append("artifact \"");
					buffer.append(bundle.getName());
					if (bundle.getBundleVersion() != null) {
						buffer.append("\\n");
						buffer.append(bundle.getBundleVersion());
					}
					buffer.append("\" as ");
					buffer.append(getBundleId(bundle));
					buffer.append("\n");
				}

				protected void appendModule(final Bundle bundle, final StringBuilder buffer) {
					buffer.append("artifact \"");
					buffer.append(bundle.getSymbolicName());
					if (bundle.getVersion() != null) {
						buffer.append("\\n");
						buffer.append(bundle.getVersion());
					}
					buffer.append("\" as ");
					buffer.append(getBundleId(bundle));
					buffer.append(" {\n");
					for (final ExportedPackage pack : bundle.getExportPackage()) {
						buffer.append("\tpackage ");
						buffer.append(pack.getName());
						buffer.append("\n");
					}
					for (final ServiceComponent sc : bundle.getServiceComponent()) {
						final Component comp = sc.getComponent();
						super.appendComponent(comp, buffer);
					}
					buffer.append("}\n");
				}
			});
		}
		return null;
	}
}
