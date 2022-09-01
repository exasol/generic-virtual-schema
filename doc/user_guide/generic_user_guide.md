# Generic SQL Dialect User Guide

The dialect only works with sources that offer a JDBC driver.

Please, only use Generic Virtual Schema if a source you want to connect to is not on [the list of the supported dialects](https://docs.exasol.com/database_concepts/virtual_schema/supported_data_sources.htm).

## Registering the JDBC Driver in EXAOperation

First, download the JDBC driver of the source database.

Now register the driver in EXAOperation:

1. Click "Software"
1. Switch to tab "JDBC Drivers"
1. Click "Browse..."
1. Select JDBC driver file
1. Click "Upload"
1. Click "Add"
1. In dialog "Add EXACluster JDBC driver" configure the JDBC driver

You need to specify the following settings when adding the JDBC driver via EXAOperation.

| Parameter | Value Description                         |
|-----------|-------------------------------------------|
| Name      | Name for the registered JDBC driver       |
| Main      | Path to the main class of the JDBC driver |
| Prefix    | Prefix to use in connections              |
| Files     | Files that belongs to the JDBC driver     |

For some JDBC drivers you might need to disable a security manager.
If you see an error message with some missing certificates when you are testing the connection, that is the case.

## Uploading the JDBC Driver to EXAOperation

1. [Create a bucket in BucketFS](https://docs.exasol.com/administration/on-premise/bucketfs/create_new_bucket_in_bucketfs_service.htm)
1. Upload the driver to BucketFS

## Installing the Adapter Script

Upload the latest available release of [Generic Virtual Schema](https://github.com/exasol/generic-virtual-schema/releases) to BucketFS.

Then create a schema to hold the adapter script.

```sql
CREATE SCHEMA ADAPTER;
```

The SQL statement below creates the adapter script, defines the Java class that serves as entry point and tells the UDF framework where to find the libraries (JAR files) for Virtual Schema and database driver.

```sql
CREATE OR REPLACE JAVA ADAPTER SCRIPT ADAPTER.JDBC_ADAPTER AS
  %scriptclass com.exasol.adapter.RequestDispatcher;
  %jar /buckets/<BFS service>/<bucket>/virtual-schema-dist-9.0.5-generic-2.0.1.jar;
  %jar /buckets/<BFS service>/<bucket>/<source databse jdbc driver>.jar;
/
```

## Defining a Named Connection

Define the connection to the source as shown below.

```sql
CREATE OR REPLACE CONNECTION GENERIC_CONNECTION
TO '<connection string>'
USER '<user>'
IDENTIFIED BY '<password>';
```

## Creating a Virtual Schema

Below you see how a Virtual Schema is created.

```sql
CREATE VIRTUAL SCHEMA <virtual schema name>
	USING ADAPTER.JDBC_ADAPTER
	WITH
	CATALOG_NAME = '<catalog name>'
	CONNECTION_NAME = 'GENERIC_CONNECTION'
	;
```
