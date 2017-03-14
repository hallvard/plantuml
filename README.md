# plantuml - Embed UML diagrams in files and view them in Eclipse

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

# main plugins
- net.sourceforge.plantuml.lib - plantuml.lib packaged as a bundle
- net.sourceforge.plantuml.eclipse - core Eclipse integration, including the extension point for providing diagrams
- net.sourceforge.plantuml.text - diagrams based on explicit DSL code, with support for editors based on the standard Eclipse text editor
- net.sourceforge.plantuml.jdt - diagrams based on the Eclipse Java model, with support for Java and Class File editors
- net.sourceforge.plantuml.ecore - diagrams based on Ecore models, with support for most Ecore editors
- net.sourceforge.plantuml.xcore - diagrams based on Xcore models, with support for the Xtext editor

# Versions

The version number is a bit special, since it includes the version of the included plantuml.jar

## 1.1.13.8054
- includes EPL-ed version of plantuml.jar
