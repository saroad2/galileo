package org.galileo.test;

import org.galileo.datum.Datums;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class DatumArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(Datums.WGS84),
                Arguments.of(Datums.ED50),
                Arguments.of(Datums.GRS80),
                Arguments.of(Datums.DEFAULT_DATUM)
        );
    }
}
