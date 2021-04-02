package net.sourceforge.plantuml.util;

import java.util.HashMap;
import java.util.Map;

public class ResourceInfo {

	public ResourceInfo() {
	}

	public ResourceInfo(final ResourceInfo resourceInfo) {
		this.originalPath = resourceInfo.originalPath;
		if (resourceInfo.markerAttributes != null) {
			this.markerAttributes = new HashMap<String, Object>(resourceInfo.markerAttributes);
		}
	}

	private String originalPath;

	public String getOriginalPath() {
		return originalPath;
	}

	public void setOriginalPath(final String originalPath) {
		this.originalPath = originalPath;
	}

	private Map<String, Object> markerAttributes;

	public Map<String, Object> getMarkerAttributes() {
		return markerAttributes;
	}

	public void setMarkerAttributes(final Map<String, Object> markerAttributes) {
		this.markerAttributes = markerAttributes;
	}
}
