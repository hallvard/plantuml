package net.sourceforge.plantuml.eclipse.utils;

/**
 * Interface with all the constants of PlantUml
 * 
 * @author durif_c
 * 
 */
public interface PlantumlConstants {

	/**
	 * Labels of the view PlantUml
	 */
	public static final String COPY_MENU = "Copy";
	public static final String COPY_SOURCE_MENU = "Copy source";
	public static final String COPY_ASCII_MENU = "Copy as ASCII art";
	public static final String EXPORT_MENU = "Export";
	public static final String PRINT_MENU = "Print";

	public static final String ZOOM_IN_BUTTON = "Zoom in";
	public static final String ZOOM_OUT_BUTTON = "Zoom out";
	public static final String FIT_CANVAS_BUTTON = "Fit to canvas";
	public static final String SHOW_ORIGINAL_BUTTON = "Show original";
	public static final String TOGGLE_GENERATION_BUTTON = "Activate/desactivate generation of diagrams";

	/**
	 * Labels for the Preferences
	 */
	public static final String GRAPHVIZ_PATH = "graphvizPath";
	public static final String GRAPHVIZ_PATH_LABEL = "Path to the dot executable of Graphviz :";
	public static final String GRAPHVIZ_PATH_TEXT_LABEL = "If the Graphviz software is not installed in the default directory, set the path in the field below.";
	public static final String GRAPHVIZ_PATH_GROUP_LABEL = "Graphviz path";

	public static final String AUTHORS_DIAGRAM = "@startuml\nauthor\n@enduml";
	public static final String TEST_DOT_DIAGRAM = "@startuml\ntestdot\n@enduml";
}
