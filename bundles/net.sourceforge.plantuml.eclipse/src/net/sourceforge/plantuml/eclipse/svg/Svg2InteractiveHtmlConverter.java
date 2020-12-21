package net.sourceforge.plantuml.eclipse.svg;

public class Svg2InteractiveHtmlConverter extends SimpleSvg2HtmlConverter {

	public Svg2InteractiveHtmlConverter() {
		super("<html>\n" +
				"  <head>\n" +
				"    <script src=\"https://ariutta.github.io/svg-pan-zoom/dist/svg-pan-zoom.min.js\"></script>\n" +
				"  </head>\n" +
				"  <body>\n",
				"\n    <script>\n" +
						"      window.onload = function() {\n" +
						"        window.zoomPlantuml = svgPanZoom('#plantuml', {\n" +
						"          zoomEnabled: true,\n" +
						"          controlIconsEnabled: false,\n" +
						"          fit: true,\n" +
						"          center: true,\n" +
						"        });\n" +
						"      };\n" +
						"    </script>\n" +
						"  </body>\n" +
				"</html>");
	}
}
