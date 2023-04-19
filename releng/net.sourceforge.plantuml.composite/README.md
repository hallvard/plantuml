# releng/net.sourceforge.plantuml.composite

Module for building and releasing new version.

## Release procedure

### Pre-release

- build and test and build and test... add, commit and push
- switch to new branch named <version>-release

## Release

### Build artifacts and repository

- do a `mvn clean` to clear target folders
- search and replace <version>-SNAPSHOT with <version> in *.*
- search and replace <version>.qualifier with <version> in *.*
- do this also for the plantuml.lib version (see README.md in plantuml.lib bundle
- update `compositeArtifacts.xml` and `compositeContent.xml` files so they list the new version(s)
- build with `mvn install` and test
- add, commit and push

### Update github pages

- open file explorer on clone of gh-pages branch
- copy contents in the target/repository folder into the proper places (should have been done by the build)
-- cp -r releng/net.sourceforge.plantuml.composite/target/repository/{compositeArtifacts.xml,compositeContent.xml} gh-pages/
-- cp -r releng/net.sourceforge.plantuml.composite/target/repository/plantuml.lib/* gh-pages/plantuml.lib/
-- cp -r releng/net.sourceforge.plantuml.composite/target/repository/plantuml.eclipse/* gh-pages/plantuml.eclipse/
- copy the README.md in the proper place
-- cp README.md gh-pages/
- add, commit (should have been done by the build) and push

### Release on github

- add a release and tag with items from README.md
- close issues that (supposedly) are fixed

### Post-release

- merge back into master
- search and replace <version> with <version+1>-SNAPSHOT in pom.xml, and <version> with <version+1>.qualifier in MANIFEST.MF, feature.xml and category.xml (think that's enough)
