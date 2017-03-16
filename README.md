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

# Version history

The version number is a bit special, since it includes the version of the included plantuml.jar

## 1.1.14.8059
- updated to 8059 version of plantuml.jar, as part of the fix to [issue #19](../../issues/19) 
- a generic solution for showing exceptions instead of a diagram, if such are thrown during diagram generation, part of the fix to [issue #19](../../issues/19) 
- experimental support for generating a class diagram for a set of Java files, as suggested in [issue #18](../../issues/18). Open the PlantUML Project Class Diagram and drag a Java project or package into the view (suggest improvements by adding a comment to the issue).
- support for generating class diagram for .class files shown in the Class File editor
- fix for [issue #15](../../issues/15)
- improved documentation, including this readme 
See [release 1.1.14.8059](../..releases/tag/v1.1.14.8059)

## 1.1.13.8054
- includes EPL-ed version of plantuml.jar
- Fix for [issue #13](../../issues/13) - recompute diagram on activate not just broughtOnTop
