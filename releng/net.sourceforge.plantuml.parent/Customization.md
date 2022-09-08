# Customization

Each of the so-called *diagram intent providers* supports a certain way of generating a diagram from a source. E.g. the **JavaEditorDiagramIntentProvider** generates PlantUML class diagrams from class declarations in java files open in the Java editor.

Several diagram intent providers may generate diagrams from the same source, e.g. the **TextEditorDiagramIntentProvider** generates diagram from text embedded (in comments) in java files, so there may be several diagrams available for open java files.

Each diagram intent provider may support customization properties that affect details of how diagrams are generated. In addition, other diagram generation behavior may also be affected by similar properties. These properties are specified in corresponding **.properties** files, in a specific folder in the workspace. The workspace folder is specified in a property in the PlantUML property sheet.

Below the various diagram generation behaviors and corresponding properties are detailed. Note that since this mechanism is fairly new, there are pretty few customization properties, but more are easily supported.

## Customizing diagram generation

The following sections details the diagram intent providers and the customization properties they support. The type of each property and the default value is shown in parenthesis behind each property name).

### Class diagra generation

Class diagrams are generated from both Java files and Ecore files may be customized by the following properties in the **AbstractClassDiagramIntent.properties** file:

- **classDiagram.isJavaStyle** (boolean, false): controls the order of name and type in attribute labels, if true the type is followed by the name.
- **classDiagram.nameTypeSeparator** (string, :): the string used for separating the type and name in attribute labels

Class diagrams generated from Java files may be customized by the following properties in the **JdtDiagramIntent.properties** file:

- **javaClassDiagram.useJavaLinks** (boolean, true): controls if class diagrams include links that opens the corresponding java file

Class diagrams generated from Ecore files may be customized by the following properties in the **EcoreClassDiagramIntent.properties** file:

- **ecoreClassDiagram.useDataTypeInstanceClassName** (boolean, false): controls if the type of data types are shown as the data type names or the corresponding instance class name

## Customizing diagram post processing

The PlantUML plug-in support post-processing the generated diagram text, independently of which diagram intent provider generated the diagram in the first place.

The following sections details the supported post-processing and corresponding (syntax of the) customization properties.

### Injecting diagram text

How extra diagram text is injected is controlled by properties in the **DiagramTextInjectionPostProcessor.properties** file. A specific injection is identified by a property having a simple name, with the text to inject as the property value. Other properties prefixed by the injection name and a dot (.) indicate *when* to use that injection, i.e. they act as guards.

The suffix of the property refer to a property of the generated diagram. Currently the following properties are supported:

- **diagramText**: the diagram text itself
- **path**: the path of the source
- **workbenchPartId**: identifier for the kind of editor or view that shows the source
- **diagramIntentProviderId**: identifier for the diagram generator

As an example, the following properties will make all diagrams for files ending in **.java** render in black and white:

```
java.path = ....java
java = skinparam monochrome true
```

The value of a guard properties, e.g. **java.path** above, is a regular expression, and when all guards are matched the corresponding inject, is added as new lines after the **@startuml** tag (or other start tag). Only injections at the top of the diagram text is currently supported.

Regular expressions corresponding to starts-with-prefix contains-infix and ends-with-suffix may be written as **prefix...**, **...infix...** and **...suffix**, respectively. E.g. to inject a certain style in any uml diagram, use **@startuml...** as the regular expression.
