package net.sourceforge.plantuml.uml2;

import java.util.List;

import org.eclipse.uml2.uml.Classifier;

public class PlantUmlOptions {
	/**
	 * if true, use simple comments via preceding '
	 */

	public static boolean exportComments = true;

	/**
	 * use qualified names
	 */
	public static boolean useQName = true;

	/**
	 * if true, skip first element of a qualified name
	 */
	public static boolean skipFirst = true;

	/**
	 * 
	 */
	public static List<Classifier> filterList = null;
}
