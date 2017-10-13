package net.sourceforge.plantuml.eclipse.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringBufferInputStream;
import java.util.HashMap;
import java.util.Map;

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

public class PlantumlUtil {
	
	public static final String PLANTUML_MARKER = "plantumlmarker";
	public static final String ORIGINAL_PATH_ATTRIBUTE = "original";
	public static final String DIAGRAM_SOURCE_ATTRIBUTE = "diagramSource";
	public static final String TARGET_PATH_ATTRIBUTE = "target";

	public static void updateMarker(IFile file, String textDiagram, IPath target, boolean create, Map<String, Object> markerAttributes) {
		IMarker marker = getPlantUmlMarker(file, create);
		if (marker != null) {
			if (target == null) {
				try {
					Object targetAttribute = marker.getAttribute(TARGET_PATH_ATTRIBUTE);
					if (targetAttribute instanceof String) {
						target = new Path(String.valueOf(targetAttribute));
					}
				} catch (CoreException e) {
				}
			}
			Map<String, Object> attributes = new HashMap<String, Object>();
			if (markerAttributes != null) {
				attributes.putAll(markerAttributes);
			}
			attributes.put(ORIGINAL_PATH_ATTRIBUTE, file.getFullPath().toString());
			attributes.put(DIAGRAM_SOURCE_ATTRIBUTE, textDiagram);
			attributes.put(TARGET_PATH_ATTRIBUTE, (target != null ? target.toString() : null));
			try {
//				System.out.println("Updating marker for " + file.getFullPath() + ": " + attributes);
				marker.setAttributes(attributes);
			} catch (CoreException e) {
			}
		}
	}

	public static IMarker getPlantUmlMarker(IFile file, boolean create) {
		IMarker marker = null;
		try {
			IMarker[] markers = file.findMarkers(PLANTUML_MARKER, false, IResource.DEPTH_ZERO);
			if (markers != null && markers.length == 1) {
				marker = markers[0];
			} else if (create) {
				marker = file.createMarker(PLANTUML_MARKER);
			}
		} catch (CoreException e1) {
		}
		return marker;
	}
	
	public static IResourceChangeListener createResourceListener() {
		return new AutoSaveHelper();
	}
	
	private static class AutoSaveHelper implements IResourceChangeListener, IResourceDeltaVisitor {

		@Override
		public void resourceChanged(IResourceChangeEvent changeEvent) {
			try {
				changeEvent.getDelta().accept(this);
			} catch (CoreException e) {
			}
		}

		private Diagram diagram;
		
		@Override
		public boolean visit(IResourceDelta delta) throws CoreException {
			if (delta.getKind() != IResourceDelta.CHANGED || (delta.getFlags() & IResourceDelta.CONTENT) == 0) {
				return true;
			}
			IResource resource = delta.getResource();
			if (resource instanceof IFile) {
				IMarker marker = getPlantUmlMarker((IFile) resource, false);
				if (marker != null) {
					Object target = marker.getAttribute(TARGET_PATH_ATTRIBUTE);
					if (target != null) {
						IPath path = resource.getFullPath();
//						System.out.println("Updating image for " + path + " @ " + target);
						for (DiagramTextProvider diagramTextProvider : Activator.getDefault().getDiagramTextProviders()) {
							if (diagramTextProvider instanceof DiagramTextProvider2) {
								DiagramTextProvider2 diagramTextProvider2 = (DiagramTextProvider2) diagramTextProvider;
								if (diagramTextProvider2.supportsPath(path)) {
									String textDiagram = diagramTextProvider2.getDiagramText(path);
//									System.out.println("Diagram for " + path + ": " + textDiagram);
									if (textDiagram != null) {
										if (diagram == null) {
											diagram = new Diagram();
										}
										diagram.setTextDiagram(textDiagram);
										try {
											ImageData image = diagram.getImage(path, 0, null);
											saveDiagramImage(path, textDiagram, image, new Path(target.toString()), false);
										} catch (Exception e) {
											System.err.println(e);
										}
										break;
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

	public static void saveDiagramImage(IPath sourcePath, String textDiagram, ImageData image, IPath targetPath, boolean create) throws Exception {
	    	IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(targetPath);
	    	if (file != null && (create || file.exists())) {
            String ext = targetPath.getFileExtension();
			if ("svg".equals(ext)) {
			    createSvgFile(file, textDiagram);
			} else if ("puml".equals(ext) || "plantuml".equals(ext)) {
				saveImage(file, textDiagram.getBytes());
			} else {
			    createImageFile(file, ext, image);
			}
			if (sourcePath != null) {
				IFile sourceFile = ResourcesPlugin.getWorkspace().getRoot().getFile(sourcePath);
				if (sourceFile != null && sourceFile.exists()) {
					updateMarker(sourceFile, textDiagram, targetPath, false, null);
				}
			}
	    	}
	}

	private static void createImageFile(IFile file, String format, ImageData imageData) throws Exception {
		ImageLoader loader = new ImageLoader();
	    loader.data = new ImageData[]{imageData};
		int swtFormat = SWT.class.getField("IMAGE_" + format.toUpperCase()).getInt(null);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(32768);
		loader.save(outputStream, swtFormat);
		saveImage(file, outputStream.toByteArray());
	}
	
	private static void saveImage(IFile file, byte[] bytes) throws CoreException {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
		NullProgressMonitor progressMonitor = new NullProgressMonitor();
		if (file.exists()) {
			file.setContents(inputStream, IResource.FORCE, progressMonitor);
		} else {
			file.create(inputStream, IResource.FORCE, progressMonitor);        			
		}
		file.setDerived(true, progressMonitor);
	}
	
	private static void createSvgFile(IFile file, String textDiagram) throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(32768);
		SourceStringReader reader = new SourceStringReader(textDiagram);
		reader.outputImage(outputStream, new FileFormatOption(FileFormat.SVG));
		saveImage(file, outputStream.toByteArray());
	}	
}
