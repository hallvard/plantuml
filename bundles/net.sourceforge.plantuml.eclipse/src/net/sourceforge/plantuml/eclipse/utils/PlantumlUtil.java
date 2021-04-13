package net.sourceforge.plantuml.eclipse.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;
import net.sourceforge.plantuml.eclipse.Activator;
import net.sourceforge.plantuml.util.DiagramData;
import net.sourceforge.plantuml.util.DiagramImageData;
import net.sourceforge.plantuml.util.DiagramIntent;
import net.sourceforge.plantuml.util.DiagramIntentProvider;

public class PlantumlUtil {

	public static final String PLANTUML_MARKER = "plantumlmarker";
	public static final String ORIGINAL_PATH_ATTRIBUTE = "original";
	public static final String DIAGRAM_INTENT_PROVIDER_ID_ATTRIBUTE = "diagramIntentProviderId";
	public static final String DIAGRAM_SOURCE_ATTRIBUTE = "diagramSource";
	public static final String TARGET_PATH_ATTRIBUTE = "target";

	public static void updateMarker(final IFile file, final String textDiagram, IPath target, final boolean create, final Map<String, Object> markerAttributes) {
		final IMarker marker = getPlantUmlMarker(file, create);
		if (marker != null) {
			if (target == null) {
				try {
					final Object targetAttribute = marker.getAttribute(TARGET_PATH_ATTRIBUTE);
					if (targetAttribute instanceof String) {
						target = new Path(String.valueOf(targetAttribute));
					}
				} catch (final CoreException e) {
				}
			}
			final Map<String, Object> attributes = new HashMap<String, Object>();
			if (markerAttributes != null) {
				attributes.putAll(markerAttributes);
			}
			attributes.put(ORIGINAL_PATH_ATTRIBUTE, file.getFullPath().toString());
			attributes.put(DIAGRAM_SOURCE_ATTRIBUTE, textDiagram);
			attributes.put(TARGET_PATH_ATTRIBUTE, (target != null ? target.toString() : null));
			try {
				marker.setAttributes(attributes);
			} catch (final CoreException e) {
			}
		}
	}

	public static IMarker getPlantUmlMarker(final IFile file, final boolean create) {
		IMarker marker = null;
		try {
			final IMarker[] markers = file.findMarkers(PLANTUML_MARKER, false, IResource.DEPTH_ZERO);
			if (markers != null && markers.length == 1) {
				marker = markers[0];
			} else if (create) {
				marker = file.createMarker(PLANTUML_MARKER);
			}
		} catch (final CoreException e1) {
		}
		return marker;
	}

	public static IResourceChangeListener createResourceListener() {
		return new AutoSaveHelper();
	}

	private static class AutoSaveHelper implements IResourceChangeListener, IResourceDeltaVisitor {

		@Override
		public void resourceChanged(final IResourceChangeEvent changeEvent) {
			try {
				changeEvent.getDelta().accept(this);
			} catch (final CoreException e) {
			}
		}

		@Override
		public boolean visit(final IResourceDelta delta) throws CoreException {
			if (delta.getKind() != IResourceDelta.CHANGED || (delta.getFlags() & IResourceDelta.CONTENT) == 0) {
				return true;
			}
			final IResource resource = delta.getResource();
			if (resource instanceof IFile) {
				final IMarker marker = getPlantUmlMarker((IFile) resource, false);
				if (marker != null) {
					final Object target = marker.getAttribute(TARGET_PATH_ATTRIBUTE);
					final Object diagramIntentProviderId = marker.getAttribute(DIAGRAM_INTENT_PROVIDER_ID_ATTRIBUTE);
					if (target != null) {
						final IPath path = resource.getFullPath();
						final WorkspaceDiagramIntentProviderContext intentProviderContext = new WorkspaceDiagramIntentProviderContext(path);
						for (final DiagramIntentProvider diagramIntentProvider : Activator.getDefault().getDiagramIntentProviders(null)) {
							if (diagramIntentProviderId == null || diagramIntentProviderId.toString().equals(Activator.getDefault().getDiagramIntentProviderId(diagramIntentProvider))) {
								final Collection<? extends DiagramIntent> diagramInfos = diagramIntentProvider.getDiagramInfos(intentProviderContext);
								if (diagramInfos != null) {
									for (final DiagramIntent diagramIntent : diagramInfos) {
										final String textDiagram = diagramIntent.getDiagramText();
										if (textDiagram != null) {
											final DiagramData diagram = new DiagramData(textDiagram);
											diagram.setOriginal(path);
											try {
												saveDiagramImage(path, textDiagram, diagram.getImage(), new Path(target.toString()), false);
											} catch (final Exception e) {
												System.err.println(e);
											}
											return false;
										}
									}
								}
							}
						}
					}
				}
			}
			return false;
		}
	}

	public static void saveDiagramImage(final DiagramImageData diagramImageData, final IPath path, final boolean b) throws Exception {
		final Function<Integer, String> fileNameProvider = PlantumlUtil.getImageFileNameProvider(path.lastSegment());
		final DiagramData diagram = diagramImageData.getDiagramData();
		if (fileNameProvider != null) {
			final IPath folderPath = path.removeLastSegments(1);
			final int imageCount = diagram.getImageCount();
			for (int i = 0; i < imageCount; i++) {
				final IPath imagePath = folderPath.append(fileNameProvider.apply(i));
				saveDiagramImage(diagramImageData.getSourcePath(), diagram.getTextDiagram(), i, diagram.getImage(i, null), imagePath, true);
			}
		} else {
			saveDiagramImage(diagramImageData.getSourcePath(), diagram.getTextDiagram(), diagramImageData.getImageNum(), diagramImageData.getImage(), path, true);
		}
	}

	public static void saveDiagramImage(final IPath sourcePath, final String textDiagram, final ImageData image, final IPath targetPath, final boolean create) throws Exception {
		saveDiagramImage(sourcePath, textDiagram, 0, image, targetPath, create);
	}

	private static void saveDiagramImage(final IPath sourcePath, final String textDiagram, final int imageNum, final ImageData image, final IPath targetPath, final boolean create) throws Exception {
		final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(targetPath);
		if (file != null && (create || file.exists())) {
			final String ext = targetPath.getFileExtension();
			if ("svg".equals(ext)) {
				createSvgFile(file, textDiagram, imageNum);
			} else if ("puml".equals(ext) || "plantuml".equals(ext)) {
				saveImage(file, textDiagram.getBytes());
			} else {
				createImageFile(file, ext, image);
			}
			if (sourcePath != null) {
				final IFile sourceFile = ResourcesPlugin.getWorkspace().getRoot().getFile(sourcePath);
				if (sourceFile != null && sourceFile.exists()) {
					updateMarker(sourceFile, textDiagram, targetPath, false, null);
				}
			}
		}
	}

	private static void createImageFile(final IFile file, final String format, final ImageData imageData) throws Exception {
		final ImageLoader loader = new ImageLoader();
		loader.data = new ImageData[]{imageData};
		final int swtFormat = SWT.class.getField("IMAGE_" + format.toUpperCase()).getInt(null);
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(32768);
		loader.save(outputStream, swtFormat);
		saveImage(file, outputStream.toByteArray());
	}

	private static void saveImage(final IFile file, final byte[] bytes) throws CoreException {
		final ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
		final NullProgressMonitor progressMonitor = new NullProgressMonitor();
		if (file.exists()) {
			file.setContents(inputStream, IResource.FORCE, progressMonitor);
		} else {
			file.create(inputStream, IResource.FORCE, progressMonitor);
		}
		file.setDerived(true, progressMonitor);
	}

	private static void createSvgFile(final IFile file, final String textDiagram, final int imageNum) throws Exception {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(32768);
		final SourceStringReader reader = new SourceStringReader(textDiagram);
		reader.outputImage(outputStream, imageNum, new FileFormatOption(FileFormat.SVG));
		saveImage(file, outputStream.toByteArray());
	}

	private final static String imageNumMarker = "#";

	public static Function<Integer, String> getImageFileNameProvider(final String filePathName) {
		final int pos = filePathName.indexOf(imageNumMarker);
		if (pos < 0) {
			return null;
		} else {
			final String filePathNamePrefix = filePathName.substring(0, pos);
			final String filePathNameSuffix = filePathName.substring(pos + imageNumMarker.length());
			return imageNum -> filePathNamePrefix + (imageNum + 1) + filePathNameSuffix;
		}
	}
}
