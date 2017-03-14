# plantuml - Embed UML diagrams in files and view them in Eclipse

The plantuml library implements a DSL and renderer for many UML diagrams (class, sequence, objects, states, activities, ...).
See http://plantuml.com for more info about the DSL and renderer.

This project integrates plantuml's functionality into Eclipse, by means of a view that shows a diagram for the currently active editor.
The rendered diagram is typically based on explicit DSL code embedded in the editor, or on the content of the editor.

We currently support generating diagrams for
- DSL code embedded in text in any text editor
- the class in the Java and Class File editors, based on Eclipse's Java model
- the EClasses in the Ecore editor

There's also experimental support for generating a diagram for all the Java classes in a Java project (or within one or more Java packages).

Diagram generation is handled by (implementations of) an extension point, so you can customize the process of generating a diagram for other file types or content.
