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

We use github pages @ http://hallvard.github.io/plantuml as the update site URL, in the Help > Install New Software... dialog.

# Main plugins (net.sourceforge.plantuml.)
- lib - plantuml.jar packaged as a bundle
- lib.jlatexmath - fragment to the lib bundle that adds the jlatexmath lib
- eclipse - core Eclipse integration, including the extension point for providing diagrams
- eclipse.imagecontrol - the control for viewing the generated images
- text - diagrams based on explicit DSL code, with support for editors based on the standard Eclipse text editor
- jdt - diagrams based on the Eclipse Java model, with support for Java and Class File editors
- ecore - diagrams based on Ecore models, with support for most Ecore editors
- osgi - component diagrams based on OSGi meta-data
- (xcore - diagrams based on Xcore models, with support for the Xtext editor)
- (uml2 - diagrams based on UML2 models)

# Releases

Notable features in recent releases, details are found in [releases](https://github.com/hallvard/plantuml/releases).
Note that the version numbers of the plantuml.lib plugin are a bit special, since they use the version of the included plantuml.jar

## [1.1.25](https://github.com/hallvard/plantuml/releases/tag/1.1.25)
- Updated to use PlantUML library version 1.2021.3 (see [changes](https://plantuml.com/changes)).
- Added new extension point that supports generating several alternative diagrams for the same content ([issue #109](https://github.com/hallvard/plantuml/issues/109)) and delays the computation of the diagram so it can run on a non-UI thread ([issue #82](https://github.com/hallvard/plantuml/issues/82)). Uses of old extension point should still work, but should be updated to use new one.
- Experimental PlantUML SVG view that handles zooming better ([issue #116](https://github.com/hallvard/plantuml/issues/116))
- Association is generated when type is Optional and for methods with a specific signature. (thanks to [dpolivaev](https://github.com/dpolivaev))
- Changes generated diagrams to use `name : type` UML format for attributes instead of java style `type name` ([issue #115](https://github.com/hallvard/plantuml/issues/115))
- Source features ([issue #104](https://github.com/hallvard/plantuml/issues/104))

## [1.1.24](https://github.com/hallvard/plantuml/releases/tag/1.1.24)
- Support for diagrams in more text formats, including markdown ([issue #65](https://github.com/hallvard/plantuml/issues/65) and [issue #91](https://github.com/hallvard/plantuml/issues/91)).
- Support for jlatexmath in separately installable feature ([issue #72](https://github.com/hallvard/plantuml/issues/72))
- Fixed bugs ([issue #93](https://github.com/hallvard/plantuml/issues/93) and ([issue #95](https://github.com/hallvard/plantuml/issues/95))
- Updated to use PlantUML library version 1.2019.11 (which includes support for mindmaps).

## [1.1.23](https://github.com/hallvard/plantuml/releases/tag/1.1.23)
- Support for generating diagrams for views, e.g. Java element selected in Package Explorer ([issue #84](https://github.com/hallvard/plantuml/issues/84)).
- Support for generating diagrams from console output.
- Preference page for enabling/disabling diagram providers.
- Avoid generating diagram (image) when PlantUml view is hidden. 
- Moved list of diagram into sub-menu of view menu, handles [issue #87](https://github.com/hallvard/plantuml/issues/87).
- Now requires Java 1.8.

## [1.1.22](https://github.com/hallvard/plantuml/releases/tag/1.1.22)
- Fixes bug ([issue #77](https://github.com/hallvard/plantuml/issues/77)) concerning incompatibilit with Photon 2018.12.
- The view menu now lists the (explicit) diagrams in a file, so you can select and view it, without navigating inside the file. See ([issue #61](https://github.com/hallvard/plantuml/issues/61))
- Updated to use PlantUML library version 1.2019.0.

## [1.1.21](https://github.com/hallvard/plantuml/releases/tag/1.1.21)
- Fixes include bug ([issue #73](https://github.com/hallvard/plantuml/issues/73)).
- Updated to use PlantUML library version 1.2018.12, which fixes another include bug.

## [1.1.20](https://github.com/hallvard/plantuml/releases/tag/1.1.20)
- Fixes include bug ([issue #35](https://github.com/hallvard/plantuml/issues/35)) and copying, exporting and printing pages of multi-page diagrams.
- Fixes problem with dangling Ecore objects ([issue #36](https://github.com/hallvard/plantuml/issues/36))
- Fixes problem with object attribute values with newlines and braces ([issue #37](https://github.com/hallvard/plantuml/issues/37)) by truncating. Also truncates long attribute value strings, to make object boxes smaller.
- Support for marker:/.../ hyperlinks to specific objects within a file, using the Marker API. Ecore object diagrams now use this kind of marker.
- Fixes problem with missing void operation ([issue #40](https://github.com/hallvard/plantuml/issues/40)). Also appends "[]" to many-valued types.
- Save action, for exporting image or puml file into workspace, and auto-save feature, for automatically updating saved files when the source changes ([issue #41](https://github.com/hallvard/plantuml/issues/41)).
- Updated to use PlantUML library version 1.2018.9

## [1.1.19](https://github.com/hallvard/plantuml/releases/tag/1.1.19)
- Support for the newpage directive. Multi-page diagrams are rendered as numbered tabs ([issue #11](https://github.com/hallvard/plantuml/issues/11)).
- Support generating packages for project class diagram, and a preference to turn on/off ([issue #18](https://github.com/hallvard/plantuml/issues/18)).
- Object diagram generator, currently used as a default for Ecore model instances. To support overriding this behavior, e.g. so an Ecore model for state machines can be rendered as state diagrams, diagram text providers can now have a priority ([issue #33](https://github.com/hallvard/plantuml/issues/33)).
- Refactored project layout, to conform to Eclipse conventions. Prepared for separating PlantUML library and Eclipse plugins update sites, so they can be released separately. The library plugin and feature has changed to same versioning as the jar.

## [1.1.18.8059](https://github.com/hallvard/plantuml/releases/tag/1.1.18.8059)
- Support links to fully qualified java classes with java scheme links ([issue #31](https://github.com/hallvard/plantuml/issues/31)), e.g. java:java.lang.String. Add a fragment to navigate to a specific member. Default diagram generation from java source now uses this kind of link.
- Fix problem with enums in project class diagrams, where associations where generated for the literals ([issue #30](https://github.com/hallvard/plantuml/issues/30)).

## [1.1.17.8059](https://github.com/hallvard/plantuml/releases/tag/1.1.17.8059)
- Hyperlinks that are just paths or use the platform scheme are opened in the default Eclipse editor ([issue #25](https://github.com/hallvard/plantuml/issues/25)). Class diagrams generated from Java code utilize this feature by including navigatable path links.

## [1.1.16.8059](https://github.com/hallvard/plantuml/releases/tag/1.1.16.8059)
- Open several PlantUML views and pin them to a specific resource ([issue #21](https://github.com/hallvard/plantuml/issues/21)). Please comment on the behaviour.
- Support for hyperlinks, see http://plantuml.com/link ([issue #24](https://github.com/hallvard/plantuml/issues/24))

## [1.1.14.8059](https://github.com/hallvard/plantuml/releases/tag/v1.1.14.8059)
- Experimental support for generating a class diagram for a set of Java files ([issue #18](https://github.com/hallvard/plantuml/issues/18)). Open the PlantUML Project Class Diagram and drag a Java project or package into the view (suggest improvements by adding a comment to the issue).
- support for generating class diagram for .class files shown in the Class File editor

## 1.1.13.8054
- Includes EPL-ed version of plantuml.jar
- Fix for [issue #13](https://github.com/hallvard/plantuml/issues/13) - recompute diagram on activate not just broughtOnTop
