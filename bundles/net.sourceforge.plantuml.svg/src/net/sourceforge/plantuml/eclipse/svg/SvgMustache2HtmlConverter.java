package net.sourceforge.plantuml.eclipse.svg;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

public class SvgMustache2HtmlConverter extends AbstractSvg2HtmlConverter {

	private String templateUrl;

	protected URL getTemplateUrl() throws MalformedURLException {
		return new URL(templateUrl);
	}

	public void setTemplateUrl(final String templateUrl) {
		this.templateUrl = templateUrl;
	}

	public void setTemplateUrl(final URL templateUrl) {
		setTemplateUrl(templateUrl.toString());
	}

	protected InputStream getTemplateInput() throws IOException {
		return getTemplateUrl().openStream();
	}

	protected Template getTemplate() throws IOException {
		try (InputStream input = getTemplateInput();
				Reader reader = new InputStreamReader(input)) {
			return Mustache.compiler().compile(reader);
		}
	}

	protected Map<String, Object> getTemplateContext(final String svg) {
		final Map<String, Object> context = new HashMap<String, Object>();
		final String cleaned = cleanSvg(svg);
		context.put("svgSource", cleaned);
		context.put("svgSelector", "#plantuml");
		return context;
	}

	@Override
	public String convert2Html(final String svg) {
		try {
			final Template template = getTemplate();
			final Map<String, Object> context = getTemplateContext(svg);
			final String converted = template.execute(context);
			return converted;
		} catch (final Exception e) {
			return "<verbatim>" + e.getMessage() + "</verbatim>";
		}
	}
}
