package de.dlrg.rs.results;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Test1 {

    @Test
    void leereEingabeErgibtLeeresErgebnis() {
        Eingabe eingabe = new Eingabe();

        Ergebnis ergebnis = eingabe.toErgebnis();

        assertEquals(0, ergebnis.getPlaetze().length);
    }


    @ParameterizedTest
    @ValueSource(strings = {"1-1", "1-2"})
    void ersterPlatzBekommt20Punkte(String startnummer) {
        Eingabe eingabe = new Eingabe();
        eingabe.put(new Schwimmer(startnummer), 1);

        Ergebnis ergebnis = eingabe.toErgebnis();
        assertEquals(1, ergebnis.getPlaetze().length);

        Platz platz1 = ergebnis.getPlaetze()[0];

        assertEquals(20.0, platz1.getPunkte(), 0.001);
        assertEquals(startnummer, platz1.getStartnummer());
        assertEquals(1, platz1.getPlatz());
    }

    @ParameterizedTest
    @ValueSource(strings = {"2-1", "2-2"})
    void zweiterPlatzBekommt18Punkte(String startnummer) {
        Eingabe eingabe = new Eingabe();
        eingabe.put(new Schwimmer("1-1"), 1);
        eingabe.put(new Schwimmer(startnummer), 2);

        Ergebnis ergebnis = eingabe.toErgebnis();
        assertEquals(2, ergebnis.getPlaetze().length);

        Platz platz1 = ergebnis.getPlaetze()[0];
        assertEquals("1-1", platz1.getStartnummer());
        assertEquals(1, platz1.getPlatz());
        assertEquals(20.0, platz1.getPunkte(), 0.001);

        Platz platz2 = ergebnis.getPlaetze()[1];
        assertEquals(startnummer, platz2.getStartnummer());
        assertEquals(2, platz2.getPlatz());
        assertEquals(18.0, platz2.getPunkte(), 0.001);
    }
}
