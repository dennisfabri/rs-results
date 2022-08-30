package de.dlrg.rs.results;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test für ein B Finale der Plätze 9-16
 */
class PunkteFuerBFinaleTest {

    @ParameterizedTest
    @ValueSource(strings = {"1-1", "1-2"})
    void neunterPlatzBekommt8Punkte(String startnummer) {
        RekorderListe rekorderListe = new RekorderListe(8);
        rekorderListe.put(new Schwimmer(startnummer), 1);

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(1, ergebnis.getPlaetze().length);

        Platz platz1 = ergebnis.getPlaetze()[0];

        assertEquals(8.0, platz1.getPunkte(), 0.001);
        assertEquals(startnummer, platz1.getStartnummer());
        assertEquals(9, platz1.getPlatz().getAsInt());
    }

    @Test
    void sechtzehnterPlatzBekommt1Punkte() {
        RekorderListe rekorderListe = new RekorderListe(8);
        for (int i = 1; i <= 8; i++) {
            rekorderListe.put(new Schwimmer("1-" + i), i);
        }

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(8, ergebnis.getPlaetze().length);

        Platz platz16 = ergebnis.getPlaetze()[7];
        assertEquals("1-8", platz16.getStartnummer());
        assertEquals(16, platz16.getPlatz().getAsInt());
        assertEquals(1.0, platz16.getPunkte(), 0.001);
    }
}
