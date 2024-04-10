# net.sourceforge.plantuml.lib

This plugin wraps the EPL-ed version of the plantuml.jar file. The plugin's version is the same as the jar's, to make it easy to track versions.

Once in a while it should be updated with a new version. This isn't currently fully automated, so the following manual steps are needed:

- Update the PlantUML lib version in `releng/net.sourceforge.plantuml.parent/pom.xml`, property `plantuml-lib-version`
- Maven automatically downloads the specified library version and corresponding sources jars to the lib folder
- Search and replace the previous version with the new one throughout the workspace _except_ `compositeArtifacts.xml` and `compositeContent.xml` in the `releng/composite` module, to update meta-data in plug-in and feature projects (`MANIFEST.MF`, `build.properties`, `pom.xml`, `feature.xml`, `category.xml`)
