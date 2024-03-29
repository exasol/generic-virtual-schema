package com.exasol.adapter.dialects.generic;

import static com.exasol.adapter.dialects.IdentifierCaseHandling.*;

import com.exasol.adapter.adapternotes.SchemaAdapterNotes;
import com.exasol.adapter.dialects.BaseIdentifierConverter;
import com.exasol.adapter.dialects.IdentifierCaseHandling;
import com.exasol.errorreporting.ExaError;

/**
 * The {@link GenericIdentifierConverter} tries to determine identifier handling semi-automatically.
 * <p>
 * It is intended as fallback for cases where no dedicated SQL dialect adapter is available and identifier handling is
 * therefore unknown.
 */
public class GenericIdentifierConverter extends BaseIdentifierConverter {
    /**
     * Create a new instance of the {@link GenericIdentifierConverter}.
     *
     * @param adapterNotes cached metadata of the remote data source
     */
    public GenericIdentifierConverter(final SchemaAdapterNotes adapterNotes) {
        super(readUnquotedIdentifierHandlingFromAdapterNotes(adapterNotes),
                readQuotedIdentifierHandlingFromAdapterNotes(adapterNotes));
    }

    private static IdentifierCaseHandling readUnquotedIdentifierHandlingFromAdapterNotes(
            final SchemaAdapterNotes adapterNotes) {
        if (adapterNotes.supportsMixedCaseIdentifiers()) {
            return INTERPRET_CASE_SENSITIVE;
        } else {
            if (adapterNotes.storesLowerCaseIdentifiers()) {
                return INTERPRET_AS_LOWER;
            } else if (adapterNotes.storesUpperCaseIdentifiers()) {
                return INTERPRET_AS_UPPER;
            } else if (adapterNotes.storesMixedCaseIdentifiers()) {
                return INTERPRET_CASE_SENSITIVE;
            } else {
                throw new UnsupportedOperationException(ExaError.messageBuilder("E-VSGEN-1") //
                        .message("Unexpected behavior for unquoted identifiers. Adapter notes:\n {{adapterNotes}}",
                                adapterNotes)
                        .toString());
            }
        }
    }

    private static IdentifierCaseHandling readQuotedIdentifierHandlingFromAdapterNotes(
            final SchemaAdapterNotes adapterNotes) {
        if (adapterNotes.supportsMixedCaseQuotedIdentifiers()) {
            return INTERPRET_CASE_SENSITIVE;
        } else {
            if (adapterNotes.storesLowerCaseQuotedIdentifiers()) {
                return INTERPRET_AS_LOWER;
            } else if (adapterNotes.storesUpperCaseQuotedIdentifiers()) {
                return INTERPRET_AS_UPPER;
            } else if (adapterNotes.storesMixedCaseQuotedIdentifiers()) {
                return INTERPRET_CASE_SENSITIVE;
            } else {
                throw new UnsupportedOperationException(ExaError.messageBuilder("E-VSGEN-2") //
                        .message("Unexpected behavior for quoted identifiers. Adapter notes:\n {{adapterNotes}}",
                                adapterNotes)
                        .toString());
            }
        }
    }
}