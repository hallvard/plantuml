package net.sourceforge.plantuml.uml2;

import org.eclipse.uml2.uml.BehavioralFeature;
import org.eclipse.uml2.uml.Feature;

/**
 * Utility function for PUML modifiers on attributes and operations
 */
public class ModifiersUtil {
	
	/**
	 * return the PlantUML modifier string containing {abstract} and {static}, if applied on a feature
	 * 
	 * @param feature a UML feature
	 * @return
	 */
	public static String modifiers(Feature feature) {
		String modifiersPUML = null;
		if (feature.isStatic()) {
			modifiersPUML = "{static}";
		}
		if (feature instanceof BehavioralFeature) {
			boolean isAbstract = ((BehavioralFeature) feature).isAbstract();
			// normally, a method cannot be both abstract and static 
			if (isAbstract) {
				modifiersPUML = "{abstract}";
			}
		}
		if (feature.isLeaf()) {
			// unsupported in pUML
			modifiersPUML = (modifiersPUML == null) ? modifiersPUML : "" + "{const}";
		}
		return modifiersPUML;
	}
}
