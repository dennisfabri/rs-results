package de.dlrg.rs.results;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ZweiSchwimmerSindZeitgleichTest {

    @ParameterizedTest
    @ValueSource(strings = {"2-1", "2-2"})
    void zweiSchwimmerBekommen20Punkte(String startnummer) {
        RekorderListe rekorderListe = new RekorderListe();
        rekorderListe.put(new Schwimmer("1-1"), 1);
        rekorderListe.put(new Schwimmer(startnummer), 1);

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(2, ergebnis.getPlaetze().length);

        Platz platz1 = ergebnis.getPlaetze()[0];
        assertEquals("1-1", platz1.getStartnummer());
        assertEquals(1, platz1.getPlatz().getAsInt());
        assertEquals(20.0, platz1.getPunkte(), 0.001);

        Platz platz2 = ergebnis.getPlaetze()[1];
        assertEquals(startnummer, platz2.getStartnummer());
        assertEquals(1, platz2.getPlatz().getAsInt());
        assertEquals(20.0, platz2.getPunkte(), 0.001);
    }

    @Test
    void zweiSchwimmerBekommen18Punkte() {
        RekorderListe rekorderListe = new RekorderListe();
        rekorderListe.put(new Schwimmer("1-1"), 1);
        rekorderListe.put(new Schwimmer("2-1"), 2);
        rekorderListe.put(new Schwimmer("2-2"), 2);
        rekorderListe.put(new Schwimmer("4-1"), 3);

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(4, ergebnis.getPlaetze().length);

        Platz platz1 = ergebnis.getPlaetze()[0];
        assertEquals("1-1", platz1.getStartnummer());
        assertEquals(1, platz1.getPlatz().getAsInt());
        assertEquals(20.0, platz1.getPunkte(), 0.001);

        Platz platz2a = ergebnis.getPlaetze()[1];
        assertEquals("2-1", platz2a.getStartnummer());
        assertEquals(2, platz2a.getPlatz().getAsInt());
        assertEquals(18.0, platz2a.getPunkte(), 0.001);

        Platz platz2b = ergebnis.getPlaetze()[2];
        assertEquals("2-2", platz2b.getStartnummer());
        assertEquals(2, platz2b.getPlatz().getAsInt());
        assertEquals(18.0, platz2b.getPunkte(), 0.001);

        Platz platz4 = ergebnis.getPlaetze()[3];
        assertEquals("4-1", platz4.getStartnummer());
        assertEquals(4, platz4.getPlatz().getAsInt());
        assertEquals(14.0, platz4.getPunkte(), 0.001);
    }
}
