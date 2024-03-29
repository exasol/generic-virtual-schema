package com.exasol.adapter.dialects.generic;

import static com.exasol.adapter.dialects.IdentifierCaseHandling.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.exasol.adapter.adapternotes.SchemaAdapterNotes;
import com.exasol.adapter.dialects.IdentifierCaseHandling;

class GenericIdentifierConverterTest {
    @Test
    void testGetUnquotedIdentifierHandlingUpperIfSupportsMixedCase() {
        final SchemaAdapterNotes.Builder adapterNotesBuilder = SchemaAdapterNotes.builder()
                .supportsMixedCaseIdentifiers(true);
        assertUnquotedIdentifierHandling(INTERPRET_CASE_SENSITIVE, adapterNotesBuilder);
    }

    private void assertUnquotedIdentifierHandling(final IdentifierCaseHandling expectedUnquotedIdentifierHandling,
            final SchemaAdapterNotes.Builder adapterNotesBuilder) {
        adapterNotesBuilder.supportsMixedCaseQuotedIdentifiers(true);
        final GenericIdentifierConverter converter = new GenericIdentifierConverter(adapterNotesBuilder.build());
        assertThat(converter.getUnquotedIdentifierHandling(), equalTo(expectedUnquotedIdentifierHandling));
    }

    @Test
    void testGetUnquotedIdentifierHandlingLower() {
        final SchemaAdapterNotes.Builder adapterNotesBuilder = SchemaAdapterNotes.builder() //
                .supportsMixedCaseIdentifiers(false) //
                .storesLowerCaseIdentifiers(true);
        assertUnquotedIdentifierHandling(INTERPRET_AS_LOWER, adapterNotesBuilder);
    }

    @Test
    void testGetUnquotedIdentifierHandlingUpper() {
        final SchemaAdapterNotes.Builder adapterNotesBuilder = SchemaAdapterNotes.builder() //
                .supportsMixedCaseIdentifiers(false) //
                .storesLowerCaseIdentifiers(false) //
                .storesUpperCaseIdentifiers(true);
        assertUnquotedIdentifierHandling(INTERPRET_AS_UPPER, adapterNotesBuilder);
    }

    @Test
    void testGetUnquotedIdentifierHandlingUpperIfStoresMixedCase() {
        final SchemaAdapterNotes.Builder adapterNotesBuilder = SchemaAdapterNotes.builder() //
                .supportsMixedCaseIdentifiers(false) //
                .storesLowerCaseIdentifiers(false) //
                .storesUpperCaseIdentifiers(false) //
                .storesMixedCaseIdentifiers(true);
        assertUnquotedIdentifierHandling(INTERPRET_CASE_SENSITIVE, adapterNotesBuilder);
    }

    @Test
    void testUnknownCaseIdentifierStorage() {
        final SchemaAdapterNotes adapterNotes = SchemaAdapterNotes.builder() //
                .supportsMixedCaseIdentifiers(false) //
                .storesLowerCaseIdentifiers(false) //
                .storesUpperCaseIdentifiers(false) //
                .storesMixedCaseIdentifiers(false) //
                .supportsMixedCaseQuotedIdentifiers(true) //
                .build();
        final UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class,
                () -> new GenericIdentifierConverter(adapterNotes));
        assertThat(exception.getMessage(), startsWith("E-VSGEN-1: Unexpected behavior for unquoted identifiers."));
    }

    @Test
    void testUnknownQuotedIdentifierHandling() {
        final SchemaAdapterNotes adapterNotes = SchemaAdapterNotes.builder() //
                .supportsMixedCaseIdentifiers(true) //
                .supportsMixedCaseQuotedIdentifiers(false) //
                .storesLowerCaseQuotedIdentifiers(false) //
                .storesUpperCaseQuotedIdentifiers(false) //
                .storesMixedCaseQuotedIdentifiers(false) //
                .build();
        final UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class,
                () -> new GenericIdentifierConverter(adapterNotes));
        assertThat(exception.getMessage(), startsWith("E-VSGEN-2: Unexpected behavior for quoted identifiers."));
    }

    @Test
    void testGetQuotedIdentifierHandlingUpperIfSupportsMixedCase() {
        final SchemaAdapterNotes.Builder adapterNotesBuilder = SchemaAdapterNotes.builder()
                .supportsMixedCaseQuotedIdentifiers(true);
        assertQuotedIdentifierHandling(INTERPRET_CASE_SENSITIVE, adapterNotesBuilder);
    }

    private void assertQuotedIdentifierHandling(final IdentifierCaseHandling expectedUnquotedIdentifierHandling,
            final SchemaAdapterNotes.Builder adapterNotesBuilder) {
        adapterNotesBuilder.supportsMixedCaseIdentifiers(true);
        final GenericIdentifierConverter converter = new GenericIdentifierConverter(adapterNotesBuilder.build());
        assertThat(converter.getQuotedIdentifierHandling(), equalTo(expectedUnquotedIdentifierHandling));
    }

    @Test
    void testGetQuotedIdentifierHandlingLower() {
        final SchemaAdapterNotes.Builder adapterNotesBuilder = SchemaAdapterNotes.builder() //
                .supportsMixedCaseQuotedIdentifiers(false) //
                .storesLowerCaseQuotedIdentifiers(true);
        assertQuotedIdentifierHandling(INTERPRET_AS_LOWER, adapterNotesBuilder);
    }

    @Test
    void testGetQuotedIdentifierHandlingUpper() {
        final SchemaAdapterNotes.Builder adapterNotesBuilder = SchemaAdapterNotes.builder() //
                .supportsMixedCaseQuotedIdentifiers(false) //
                .storesLowerCaseQuotedIdentifiers(false) //
                .storesUpperCaseQuotedIdentifiers(true);
        assertQuotedIdentifierHandling(INTERPRET_AS_UPPER, adapterNotesBuilder);
    }

    @Test
    void testGetQuotedIdentifierHandlingUpperIfStoresMixedCase() {
        final SchemaAdapterNotes.Builder adapterNotesBuilder = SchemaAdapterNotes.builder() //
                .supportsMixedCaseQuotedIdentifiers(false) //
                .storesLowerCaseQuotedIdentifiers(false) //
                .storesUpperCaseQuotedIdentifiers(false) //
                .storesMixedCaseQuotedIdentifiers(true);
        assertQuotedIdentifierHandling(INTERPRET_CASE_SENSITIVE, adapterNotesBuilder);
    }
}