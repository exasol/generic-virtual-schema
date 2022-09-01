package com.exasol.adapter.dialects.generic;

import static com.exasol.adapter.AdapterProperties.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.*;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.adapter.AdapterProperties;
import com.exasol.adapter.capabilities.Capabilities;
import com.exasol.adapter.dialects.*;
import com.exasol.adapter.jdbc.ConnectionFactory;
import com.exasol.adapter.jdbc.RemoteMetadataReaderException;

@ExtendWith(MockitoExtension.class)
class GenericSqlDialectTest {
    @Mock
    private ConnectionFactory connectionFactoryMock;

    @BeforeEach
    void beforeEach(@Mock final DatabaseMetaData metadataMock, @Mock final Connection connectionMock)
            throws SQLException {
        lenient().when(metadataMock.supportsMixedCaseIdentifiers()).thenReturn(true);
        lenient().when(metadataMock.supportsMixedCaseQuotedIdentifiers()).thenReturn(true);
        lenient().when(metadataMock.getIdentifierQuoteString()).thenReturn("'");
        lenient().when(connectionMock.getMetaData()).thenReturn(metadataMock);
        when(this.connectionFactoryMock.getConnection()).thenReturn(connectionMock);
    }

    @Test
    void testValidateCatalogProperty() throws PropertyValidationException {
        final Map<String, String> rawProperties = Map.of( //
                CATALOG_NAME_PROPERTY, "MY_CATALOG", //
                CONNECTION_NAME_PROPERTY, "MY_CONN" //
        );
        assertDialectCreatedWithValidProperties(rawProperties);
    }

    private void assertDialectCreatedWithValidProperties(final Map<String, String> rawProperties)
            throws PropertyValidationException {
        final SqlDialect sqlDialect = createDialect(rawProperties);
        assertDoesNotThrow(sqlDialect::validateProperties);
    }

    @Test
    void testValidateSchemaProperty() throws PropertyValidationException {
        final Map<String, String> rawProperties = Map.of( //
                SCHEMA_NAME_PROPERTY, "MY_SCHEMA", //
                CONNECTION_NAME_PROPERTY, "MY_CONN" //
        );
        assertDialectCreatedWithValidProperties(rawProperties);
    }

    @Test
    void testGetName() {
        final SqlDialect dialect = createDialect(Map.of());
        assertThat(dialect.getName(), equalTo("GENERIC"));
    }

    @Test
    void testRequiresCatalogQualifiedTableNames() {
        final SqlDialect dialect = createDialect(Map.of());
        assertThat(dialect.requiresCatalogQualifiedTableNames(null), is(true));
    }

    @Test
    void testRequiresSchemaQualifiedTableNames() {
        final SqlDialect dialect = createDialect(Map.of());
        assertThat(dialect.requiresSchemaQualifiedTableNames(null), is(true));
    }

    @Test
    void testGetStringLiteral() {
        final SqlDialect dialect = createDialect(Map.of());
        assertThat(dialect.getStringLiteral("string"), equalTo("'string'"));
    }

    @Test
    void testGetCapabilitiesReturnsNoCapabilities() {
        final SqlDialect dialect = createDialect(Map.of());
        final Capabilities capabilities = dialect.getCapabilities();
        assertThat(capabilities.getAggregateFunctionCapabilities(), empty());
        assertThat(capabilities.getLiteralCapabilities(), empty());
        assertThat(capabilities.getMainCapabilities(), empty());
        assertThat(capabilities.getPredicateCapabilities(), empty());
        assertThat(capabilities.getScalarFunctionCapabilities(), empty());
    }

    @Test
    void testApplyQuote() {
        final SqlDialect dialect = createDialect(Map.of());
        assertThat(dialect.applyQuote("identifier"), equalTo("'identifier'"));
    }

    @Test
    void testApplyQuoteFailsWhenIdentifierContainsQuote() {
        final SqlDialect dialect = createDialect(Map.of());
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> dialect.applyQuote("identi'fier"));
        assertThat(exception.getMessage(), equalTo(
                "E-VSGEN-3: An identifier 'identi'fier' contains illegal substring: '''. Please remove it to use the generic dialect."));
    }

    @Test
    void testCreatingMetadataReaderFails() throws SQLException {
        when(this.connectionFactoryMock.getConnection()).thenThrow(new SQLException("mock"));
        final Map<String, String> properties = Map.of();
        final RemoteMetadataReaderException exception = assertThrows(RemoteMetadataReaderException.class,
                () -> createDialect(properties));
        assertThat(exception.getMessage(), equalTo(
                "E-VSGEN-4: Unable to create remote metadata reader for the generic SQL dialect. Caused by: 'mock'"));
    }

    private SqlDialect createDialect(final Map<String, String> rawProperties) {
        final AdapterProperties adapterProperties = new AdapterProperties(rawProperties);
        final SqlDialectFactory factory = new GenericSqlDialectFactory();
        return factory.createSqlDialect(connectionFactoryMock, adapterProperties);
    }
}