package net.sourceforge.plantuml.eclipse.svg;

public class SimpleSvg2HtmlConverter extends AbstractSvg2HtmlConverter {

	private final String prefix;
	private final String suffix;

	protected SimpleSvg2HtmlConverter(final String prefix, final String suffix) {
		this.prefix = prefix;
		this.suffix = suffix;
	}

	public SimpleSvg2HtmlConverter() {
		this("<html>\n  <body>\n", "\n  </body>\n</html>");
	}

	@Override
	public String convert2Html(String svg) {
		svg = cleanSvg(svg);
		final StringBuilder builder = new StringBuilder(prefix.length() + svg.length() + suffix.length());
		builder.append(prefix);
		builder.append(idAttrPrefix);
		builder.append(idAttr);
		builder.append(svg.substring(idAttrPrefix.length()));
		builder.append(suffix);
		return builder.toString();
	}
}
