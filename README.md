# releng/net.sourceforge.plantuml.composite

Module for building and releasing new version.

## Release procedure

### Pre-release

- build and test and build and test...
- commit and push
- swith to new branch named <version>-release

## Release

### Build artifacts and repository

- do a `mvn clean` to clear target folders
- search and replace <version>-SNAPSHOT with <version> in *.*
- search and replace <version>.qualifier with <version> in *.*
- do this also for the plantuml.lib version (see README.md in plantuml.lib bundle
- update `compositeArtifacts.xml` and `compositeContent.xml` files so they list the new version(s)
- commit and push

### Update github pages

- open file explorer on clone of gh-pages branch
- copy contents in the target/repository folder into the proper places
- copy the README.md in the proper place
- commit and push

### Release on github

- add a release and tag with items from README.md
- close issues that (supposedly) are fixed
 
### Post-release

- Merge back into master
- search and replace <version> with <version+1>-SNAPSHOT in pom.xml, and <version> with <version+1>.qualifier in MANIFEST.MF, feature.xml and category.xml (think that's enough)
