package net.sourceforge.plantuml.text;

import java.util.HashMap;

import org.eclipse.jface.text.IDocument;

import net.sourceforge.plantuml.util.AbstractDiagramIntent;

public class TextDiagramIntent extends AbstractDiagramIntent<IDocument> {

	private final int start;

	private final TextDiagramIntentHelper helper;

	public TextDiagramIntent(final IDocument document, final int start, final TextDiagramIntentHelper helper) {
		super(document);
		this.start = start;
		this.helper = helper;
		// should have lower priority than those generated
		setPriority(DEFAULT_PRIORITY);
	}

	@Override
	public String getDiagramText() {
		final HashMap<String, Object> markerAttributes = (getResourceInfo() != null ? new HashMap<String, Object>() : null);
		final StringBuilder lines = helper.getDiagramTextLines(getSource(), start, markerAttributes);
		if (getResourceInfo() != null) {
			getResourceInfo().setMarkerAttributes(markerAttributes);
		}
		return helper.getDiagramText(lines);
	}
}
