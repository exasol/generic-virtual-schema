# Generic Virtual Schema

[![Build Status](https://api.travis-ci.com/exasol/generic-virtual-schema.svg?branch=main)](https://travis-ci.com/exasol/generic-virtual-schema)

SonarCloud results:

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Ageneric-virtual-schema&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.exasol%3Ageneric-virtual-schema)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Ageneric-virtual-schema&metric=security_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Ageneric-virtual-schema)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Ageneric-virtual-schema&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Ageneric-virtual-schema)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Ageneric-virtual-schema&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Ageneric-virtual-schema)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Ageneric-virtual-schema&metric=sqale_index)](https://sonarcloud.io/dashboard?id=com.exasol%3Ageneric-virtual-schema)

[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Ageneric-virtual-schema&metric=code_smells)](https://sonarcloud.io/dashboard?id=com.exasol%3Ageneric-virtual-schema)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Ageneric-virtual-schema&metric=coverage)](https://sonarcloud.io/dashboard?id=com.exasol%3Ageneric-virtual-schema)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Ageneric-virtual-schema&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=com.exasol%3Ageneric-virtual-schema)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Ageneric-virtual-schema&metric=ncloc)](https://sonarcloud.io/dashboard?id=com.exasol%3Ageneric-virtual-schema)

# Overview

The **Generic Virtual Schema** provides an abstraction layer that makes an external data source accessible from an Exasol database through regular SQL commands. The contents of the external data source are mapped to virtual tables which look like and can be queried as any regular Exasol table.

It's important to notice that in order to set up a Virtual Schema for a specific data source system, the Virtual Schema implementation for that specific data source system (if any) should be used, and not the Generic Virtual Schema.

To see all available Virtual Schema implementations, please head over to the [Virtual Schemas Repository][virtual-schemas].

## Features

* Access a data source in read-only mode from an Exasol database, using a Virtual Schema.

## Table of Contents

### Information for Users

* [Virtual Schemas User Guide][virtual-schemas-user-guide]
* [Changelog](doc/changes/changelog.md)
* [Dependencies](dependencies.md)

Find all the documentation in the [Virtual Schemas project][vs-doc].

## Information for Developers

* [Virtual Schema API Documentation][vs-api]

[virtual-schemas-user-guide]: https://docs.exasol.com/database_concepts/virtual_schemas.htm
[virtual-schemas]: https://github.com/exasol/virtual-schemas
[vs-api]: https://github.com/exasol/virtual-schema-common-java/blob/master/doc/development/api/virtual_schema_api.md
[vs-doc]: https://github.com/exasol/virtual-schemas/tree/master/doc