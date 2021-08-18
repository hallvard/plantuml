package net.sourceforge.plantuml.eclipse.svg;

public abstract class AbstractSvg2HtmlConverter implements Svg2HtmlConverter {

	protected AbstractSvg2HtmlConverter() {
	}

	protected final String idAttrPrefix = "<svg", idAttr = " id='plantuml'";

	protected String cleanSvg(String svg) {
		if (svg.startsWith("<?xml")) {
			svg = svg.substring(svg.indexOf("?>") + 2);
		}
		final int pos = svg.indexOf(idAttrPrefix);
		if (pos >= 0) {
			svg = svg.substring(0, pos) + idAttrPrefix + idAttr + svg.substring(pos + idAttrPrefix.length());
		}
		return svg;
	}
}
