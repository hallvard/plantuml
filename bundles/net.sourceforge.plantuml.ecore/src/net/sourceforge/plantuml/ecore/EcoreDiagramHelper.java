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
import net.sourceforge.plantuml.text.AbstractDiagramTextProvider;

public class EcoreDiagramHelper {

	public static String PLANTUML_ANNOTATION_KEY = "net.sourceforge.plantuml";
	public static String PLANTUML_SKINPARAMS_ANNOTATION_KEY = PLANTUML_ANNOTATION_KEY + "/skinparams";

	protected Map<String, String> getSkinParams(EModelElement element, Map<String, String> map) {
		for (EAnnotation annotation : element.getEAnnotations()) {
			if (annotation.getSource().startsWith(PLANTUML_SKINPARAMS_ANNOTATION_KEY)) {
				String key = annotation.getSource().substring(PLANTUML_SKINPARAMS_ANNOTATION_KEY.length());
				String qualifier = null;
				if (key.startsWith("/")) {
					qualifier = key.substring(1);
				}
				map = getSkinParams(qualifier, annotation, map);
			}
		}
		return map;
	}
	
	protected Map<String, String> getSkinParams(String qualifier, EAnnotation annotation, Map<String, String> map) {
		if (annotation != null) {
			EMap<String, String> eMap = annotation.getDetails();
			if (eMap.size() > 0) {
				if (map == null) {
					map = new HashMap<String, String>();
				}
				for (String key : eMap.keySet()) {
					map.put((qualifier != null ? qualifier : "") + key, eMap.get(key));
				}
			}
		}
		return map;
	}

	protected String getAnnotation(EObject element, String key, boolean checkContainers, String def) {
		while (element instanceof EModelElement) {
			String value = EcoreUtil.getAnnotation((EModelElement) element, PLANTUML_ANNOTATION_KEY, key);
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
	protected boolean checkAnnotation(EObject element, String key, boolean checkContainers) {
		return "true".equals(getAnnotation(element, key, checkContainers, null));
	}
	
	protected boolean shouldSuppress(EModelElement element, String name, String role) {
		String key = "suppress", containerKey = key + name;
		if (role != null) {
			key = key + "As" + role;
			containerKey = containerKey + "As" + role;
		}
		return checkAnnotation(element, key, false) || checkAnnotation(element.eContainer(), containerKey, true);
	}
	protected boolean shouldSuppress(ENamedElement element, String role) {
		return shouldSuppress(element, element.getName(), role);
	}
	
	//
	
	protected String getEObjectHyperlink(EObject eObject) {
		URI uri = EcoreUtil.getURI(eObject);
		if (uri.isPlatformResource()) {
			String path = uri.path();
			String prefix = "/resource";
			if (path.startsWith(prefix)) {
				path = path.substring(prefix.length());
			}
			return MarkerLinkOpener.createMarkerLink(EValidator.MARKER, path, EValidator.URI_ATTRIBUTE, URI.encodeQuery(uri.toString(), false));
		}
		return uri.toString();
	}
}
