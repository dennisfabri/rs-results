package de.dlrg.rs.results;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PunkteNachPlazierungTest {

    @Test
    void leereEingabeErgibtLeeresErgebnis() {
    }

    @ParameterizedTest
    @ValueSource(strings = {"1-1", "1-2"})
    void ersterPlatzBekommt20Punkte(String startnummer) {
    }
}
