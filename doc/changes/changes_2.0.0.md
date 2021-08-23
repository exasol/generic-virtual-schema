# Generic Virtual Schema 2.0.0, released 2021-06-11

Code name: Removed `SQL_DIALECT` property

## Summary

The SQL_DIALECT property used when executing a CREATE VIRTUAL SCHEMA from the Exasol database is obsolete from this
version. Please, do not provide this property anymore.

## Documentation

* #5: Added a user guide.

## Refactoring

* #3: Updated generic dialect.

## Dependency Updates

### Compile Dependency Updates

* Updated `com.exasol:error-reporting-java:0.2.0` to `0.4.0`
* Updated `com.exasol:virtual-schema-common-jdbc:8.0.0` to `9.0.3`

### Test Dependency Updates

* Updated `com.exasol:virtual-schema-common-jdbc:8.0.0` to `9.0.3`
* Updated `org.junit.jupiter:junit-jupiter:5.7.0` to `5.7.2`
* Updated `org.mockito:mockito-junit-jupiter:3.6.28` to `3.11.0`

### Plugin Dependency Updates

* Added `com.exasol:error-code-crawler-maven-plugin:0.4.0`
* Updated `com.exasol:project-keeper-maven-plugin:0.4.2` to `0.7.2`
* Added `io.github.zlika:reproducible-build-maven-plugin:0.13`
* Updated `org.apache.maven.plugins:maven-clean-plugin:2.5` to `3.1.0`
* Updated `org.apache.maven.plugins:maven-deploy-plugin:2.7` to `2.8.2`
* Updated `org.apache.maven.plugins:maven-install-plugin:2.4` to `2.5.2`
* Updated `org.apache.maven.plugins:maven-jar-plugin:2.4` to `3.2.0`
* Updated `org.apache.maven.plugins:maven-resources-plugin:2.6` to `3.2.0`
* Updated `org.apache.maven.plugins:maven-site-plugin:3.3` to `3.9.1`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.7` to `2.8.1`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.5` to `0.8.6`
