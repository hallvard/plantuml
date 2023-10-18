# net.sourceforge.plantuml.lib

This plugin wraps the EPL-ed version of the plantuml.jar file. The plugin's version is the same as the jar's, to make it easy to track versions.

Once in a while it should be updated with a new version. This isn't currently automated, so the following manual steps are needed:

- Download the EPL-ed version from https://plantuml.com/download and unzip
- Copy the jar into this plugin's lib folder, e.g. plantuml-epl-1.2021.3.jar
- Search and replace the previous version with the new one throughout the workspace _except_ `compositeArtifacts.xml` and `compositeContent.xml` in the `releng/composite` module, to update meta-data in plug-in and feature projects (`MANIFEST.MF`, `build.properties`, `pom.xml`, `feature.xml`, `category.xml`)
