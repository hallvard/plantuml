package net.sourceforge.plantuml.ecore;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;

import net.sourceforge.plantuml.eclipse.utils.MarkerLinkOpener;

public class EcoreDiagramHelper {

	public static String PLANTUML_ANNOTATION_KEY = "net.sourceforge.plantuml";
	public static String PLANTUML_SKINPARAMS_ANNOTATION_KEY = PLANTUML_ANNOTATION_KEY + "/skinparams";

	protected Map<String, String> getSkinParams(final EModelElement element, Map<String, String> map) {
		for (final EAnnotation annotation : element.getEAnnotations()) {
			final String source = annotation.getSource();
			if (source != null && source.startsWith(PLANTUML_SKINPARAMS_ANNOTATION_KEY)) {
				final String key = source.substring(PLANTUML_SKINPARAMS_ANNOTATION_KEY.length());
				String qualifier = null;
				if (key.startsWith("/")) {
					qualifier = key.substring(1);
				}
				map = getSkinParams(qualifier, annotation, map);
			}
		}
		return map;
	}

	protected Map<String, String> getSkinParams(final String qualifier, final EAnnotation annotation, Map<String, String> map) {
		if (annotation != null) {
			final EMap<String, String> eMap = annotation.getDetails();
			if (eMap.size() > 0) {
				if (map == null) {
					map = new HashMap<String, String>();
				}
				for (final String key : eMap.keySet()) {
					map.put((qualifier != null ? qualifier : "") + key, eMap.get(key));
				}
			}
		}
		return map;
	}

	protected String getAnnotation(EObject element, final String key, final boolean checkContainers, final String def) {
		while (element instanceof EModelElement) {
			final String value = EcoreUtil.getAnnotation((EModelElement) element, PLANTUML_ANNOTATION_KEY, key);
			if (value != null) {
				return value;
			}
			if (! checkContainers) {
				break;
			}
			element = element.eContainer();
		}
		return def;
	}
	protected boolean checkAnnotation(final EObject element, final String key, final boolean checkContainers) {
		return "true".equals(getAnnotation(element, key, checkContainers, null));
	}

	protected boolean shouldSuppress(final EModelElement element, final String name, final String role) {
		String key = "suppress", containerKey = key + name;
		if (role != null) {
			key = key + "As" + role;
			containerKey = containerKey + "As" + role;
		}
		return checkAnnotation(element, key, false) || checkAnnotation(element.eContainer(), containerKey, true);
	}
	protected boolean shouldSuppress(final ENamedElement element, final String role) {
		return shouldSuppress(element, element.getName(), role);
	}

	//

	public <T> T getAncestor(EObject eObject, final Class<T> clazz) {
		while (eObject != null) {
			if (clazz.isInstance(eObject)) {
				return (T) eObject;
			}
			eObject = eObject.eContainer();
		}
		return null;
	}

	public String getEObjectHyperlink(final EObject eObject) {
		final URI uri = EcoreUtil.getURI(eObject);
		if (uri.isPlatformResource()) {
			String path = uri.path();
			final String prefix = "/resource";
			if (path.startsWith(prefix)) {
				path = path.substring(prefix.length());
			}
			return MarkerLinkOpener.createMarkerLink(EValidator.MARKER, path, EValidator.URI_ATTRIBUTE, URI.encodeQuery(uri.toString(), false));
		}
		return uri.toString();
	}
}
