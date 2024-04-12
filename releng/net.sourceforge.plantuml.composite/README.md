# releng/net.sourceforge.plantuml.composite

Module for building and releasing new version.

## Release procedure

### Pre-release

- update all pom.xml, MANIFEST.MF, feature.xml, etc. to new version, e.g. 1.1.31-SNAPSHOT or 1.1.31.qualifier,
  also update dependencies and other version-dependant configurations,
  do this also for the `pom.xml` files in folders `bundles`, `features`, `releng`, and `tests`,
  do this also for the plantuml.lib version (see README.md in plantuml.lib bundle)
- update `compositeArtifacts.xml` and `compositeContent.xml` files so they list the new version(s)
- re-calculate / update all features' dependencies (seems to be obsolete now, see https://github.com/eclipse-pde/eclipse.pde/issues/26)
- build and test and build and test...
- run `mvn clean install` on project `net.sourceforge.plantuml.composite` or just run the launch configuration *Build all with Maven*
  (that builds and runs all non-UI unit tests)
- run the plug-ins tests, too (see `net.sourceforge.plantuml.*.tests` and `no.hal.osgi.emf.tests` projects)
- git add, commit and push
- update the README.md in root folder, e.g. add release notes
- switch to new branch named <version>-release

## Release

### Build artifacts and repository

- build with `mvn clean install` on project `net.sourceforge.plantuml.composite` 
  or just run the launch configuration *Build all with Maven*
  (that builds and runs all non-UI unit tests)
- test the release candidate
- check the PlantUML lib update site / repository in target/gh-pages/plantuml.lib/<version>
  and the PlantUML Eclipse update site / repository in target/gh-pages/plantuml.eclipse/<version>,
  ensure that you have only the latest plug-in / feature versions there and only one version per plug-in / feature
- add, commit and push

### Update GitHub pages

That is done by the Maven build automatically, when you run `mvn clean install`
on project `net.sourceforge.plantuml.composite` or the launch configuration *Build all with Maven*.
Just check if the following steps were successfully done by Maven.
If everything is as expected, push the changes to GitHub pages.

- open file explorer on clone of gh-pages branch
- copy contents in the target/repository folder into the proper places (should have been done by the build) 
    - cp -r releng/net.sourceforge.plantuml.composite/target/repository/{compositeArtifacts.xml,compositeContent.xml} gh-pages/
    - cp -r releng/net.sourceforge.plantuml.composite/target/repository/plantuml.lib/* gh-pages/plantuml.lib/
    - cp -r releng/net.sourceforge.plantuml.composite/target/repository/plantuml.eclipse/* gh-pages/plantuml.eclipse/
- copy the README.md in the proper place
    - cp README.md gh-pages/
- add, commit (should have been done by the build)
- push to gh-pages

### Release on GitHub

- add a release (create a draft first, fill all the details)
- add release notes from README.md in root folder
- merge the release branch back into master
- tag the new version on master branch with e.g. 1.1.28
- choose a git tag (e.g. 1.1.28) and the branch `master`
- publish the release
- close issues that (supposedly) are fixed

### Post-release

- search and replace <version> with <version+1>-SNAPSHOT in pom.xml, and <version> with <version+1>.qualifier in MANIFEST.MF, feature.xml and category.xml
- add the new version to `compositeArtifacts.xml` and `compositeContent.xml` and update the number of files
- build, commit and push

(think that's enough)
