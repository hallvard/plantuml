# plantuml - generate UML diagrams from files and view them in Eclipse

The plantuml library implements a DSL and renderer for many UML diagrams (class, sequence, objects, states, activities, ...).
See http://plantuml.com for more info about the DSL and renderer.

This project integrates plantuml's functionality into Eclipse, by means of a view that shows a diagram for the currently active editor.
The rendered diagram is typically based on explicit DSL code embedded in the editor, or generated from the content of the editor.

We currently support generating diagrams for
- DSL code embedded in text in any text editor
- the class in the Java and Class File editors, based on the Eclipse Java model
- the EClasses in Ecore models in the Ecore editor

There's also experimental support for generating a diagram for all the Java classes in a Java project (or within one or more Java packages).

Diagram generation is handled by (implementations of) an extension point, so you can customize the process of generating a diagram for other file types or editor content.

# License

This repo uses the [EPL license](net.sourceforge.plantuml.feature/epl-v10.html). 

# Installation

Use http://files.idi.ntnu.no/publish/plantuml/repository/ as the update site URL, in the Help > Install New Software... dialog.

# Main plugins
- net.sourceforge.plantuml.lib - plantuml.jar packaged as a bundle
- net.sourceforge.plantuml.eclipse - core Eclipse integration, including the extension point for providing diagrams
- net.sourceforge.plantuml.text - diagrams based on explicit DSL code, with support for editors based on the standard Eclipse text editor
- net.sourceforge.plantuml.jdt - diagrams based on the Eclipse Java model, with support for Java and Class File editors
- net.sourceforge.plantuml.ecore - diagrams based on Ecore models, with support for most Ecore editors
- net.sourceforge.plantuml.xcore - diagrams based on Xcore models, with support for the Xtext editor

# Releases

Notable features in recent releases, details are found in [../../releases].
Note that the version numbers are a bit special, since they include the version of the included plantuml.jar

## [1.1.20]
- Fixes include bug ([issue #35](../../issues/35)) and copying, exporting and printing pages of multi-page diagrams.
- Fixes problem with dangling Ecore objects ([issue #36](../../issues/36))
- Fixes problem with object attribute values with newlines and braces ([issue #37](../../issues/37)) by truncating. Also truncates long attribute value strings, to make object boxes smaller.
- Support for marker:/.../ hyperlinks to specific objects within a file, using the Marker API. Ecore object diagrams now use this kind of marker.
- Fixes problem with missing void operation ([issue #40](../../issues/40)). Also appends "[]" to many-valued types.
- Save action, for exporting image or puml file into workspace, and auto-save feature, for automatically updating saved files when the source changes ([issue #41](../../issues/41)).
- Signed release artifacts to remove a security warning ([issue #41](../../issues/54)).

## [1.1.19](../../releases/tag/1.1.19)
- Support for the newpage directive. Multi-page diagrams are rendered as numbered tabs ([issue #11](../../issues/11)).
- Support generating packages for project class diagram, and a preference to turn on/off ([issue #18](../../issues/18)).
- Object diagram generator, currently used as a default for Ecore model instances. To support overriding this behavior, e.g. so an Ecore model for state machines can be rendered as state diagrams, diagram text providers can now have a priority ([issue #33](../../issues/33)).
- Refactored project layout, to conform to Eclipse conventions. Prepared for separating PlantUML library and Eclipse plugins update sites, so they can be released separately. The library plugin and feature has changed to same versioning as the jar.

## [1.1.18.8059](../../releases/tag/1.1.18.8059)
- Support links to fully qualified java classes with java scheme links ([issue #31](../../issues/31)), e.g. java:java.lang.String. Add a fragment to navigate to a specific member. Default diagram generation from java source now uses this kind of link.
- Fix problem with enums in project class diagrams, where associations where generated for the literals ([issue #30](../../issues/30)).

## [1.1.17.8059](../../releases/tag/1.1.17.8059)
- Hyperlinks that are just paths or use the platform scheme are opened in the default Eclipse editor ([issue #25](../../issues/25)). Class diagrams generated from Java code utilize this feature by including navigatable path links.

## [1.1.16.8059](../../releases/tag/1.1.16.8059)
- Open several PlantUML views and pin them to a specific resource ([issue #21](../../issues/21)). Please comment on the behaviour.
- Support for hyperlinks, see http://plantuml.com/link ([issue #24](../../issues/24))

## [1.1.14.8059](../../releases/tag/v1.1.14.8059)
- Experimental support for generating a class diagram for a set of Java files ([issue #18](../../issues/18)). Open the PlantUML Project Class Diagram and drag a Java project or package into the view (suggest improvements by adding a comment to the issue).
- support for generating class diagram for .class files shown in the Class File editor

## 1.1.13.8054
- Includes EPL-ed version of plantuml.jar
- Fix for [issue #13](../../issues/13) - recompute diagram on activate not just broughtOnTop
