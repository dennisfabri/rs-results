package de.dlrg.rs.results;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EinenSchwimmerDisqualifizierenTest {

    @ParameterizedTest
    @ValueSource(strings = {"1-1", "1-2"})
    void disqualifizierterBekommt0Punkte(String startnummer) {
        RekorderListe rekorderListe = new RekorderListe();
        rekorderListe.put(new Schwimmer(startnummer), 1, Status.Disqualifikation);

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(1, ergebnis.getPlaetze().length);

        Platz platz1 = ergebnis.getPlaetze()[0];

        assertEquals(0.0, platz1.getPunkte(), 0.001);
        assertEquals(startnummer, platz1.getStartnummer());
        assertEquals(OptionalInt.empty(), platz1.getPlatz());
    }

    @ParameterizedTest
    @ValueSource(strings = {"2-1", "2-2"})
    void disqualifizierterZweiterBekommt0Punkte(String startnummer) {
        RekorderListe rekorderListe = new RekorderListe();
        rekorderListe.put(new Schwimmer("1-1"), 1);
        rekorderListe.put(new Schwimmer(startnummer), 2, Status.Disqualifikation);

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(2, ergebnis.getPlaetze().length);

        Platz platz1 = ergebnis.getPlaetze()[0];
        assertEquals("1-1", platz1.getStartnummer());
        assertEquals(1, platz1.getPlatz().getAsInt());
        assertEquals(20.0, platz1.getPunkte(), 0.001);

        Platz platz2 = ergebnis.getPlaetze()[1];
        assertEquals(startnummer, platz2.getStartnummer());
        assertEquals(OptionalInt.empty(), platz2.getPlatz());
        assertEquals(0.0, platz2.getPunkte(), 0.001);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2-1", "2-2"})
    void disqualifizierterErsterBekommtLetztenPlatz(String startnummer) {
        RekorderListe rekorderListe = new RekorderListe();
        rekorderListe.put(new Schwimmer("1-1"), 1, Status.Disqualifikation);
        rekorderListe.put(new Schwimmer(startnummer), 2);

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(2, ergebnis.getPlaetze().length);

        Platz platz1 = ergebnis.getPlaetze()[0];
        assertEquals(startnummer, platz1.getStartnummer());
        assertEquals(1, platz1.getPlatz().getAsInt());
        assertEquals(20.0, platz1.getPunkte(), 0.001);

        Platz platz2 = ergebnis.getPlaetze()[1];
        assertEquals("1-1", platz2.getStartnummer());
        assertEquals(OptionalInt.empty(), platz2.getPlatz());
        assertEquals(0.0, platz2.getPunkte(), 0.001);
    }

}
