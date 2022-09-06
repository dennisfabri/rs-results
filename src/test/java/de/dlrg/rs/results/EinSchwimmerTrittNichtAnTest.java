package de.dlrg.rs.results;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EinSchwimmerTrittNichtAnTest {

    @ParameterizedTest
    @ValueSource(strings = {"1-1", "1-2"})
    void nichtAngetretenerBekommt0Punkte(String startnummer) throws InputNotValidException{
        RekorderListe rekorderListe = new RekorderListe();
        rekorderListe.put(new Schwimmer(startnummer), Status.NichtAngetreten);

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(1, ergebnis.getPlaetze().length);

        Platz platz1 = ergebnis.getPlaetze()[0];

        assertEquals(0.0, platz1.getPunkte(), 0.001);
        assertEquals(startnummer, platz1.getStartnummer());
        assertEquals(OptionalInt.empty(), platz1.getPlatz());
        assertEquals(Status.NichtAngetreten, platz1.getStatus());
    }

    @ParameterizedTest
    @ValueSource(strings = {"2-1", "2-2"})
    void nichtAngetretenerZweiterBekommt0Punkte(String startnummer) throws InputNotValidException{
        RekorderListe rekorderListe = new RekorderListe();
        rekorderListe.put(new Schwimmer("1-1"), 1);
        rekorderListe.put(new Schwimmer(startnummer), Status.NichtAngetreten);

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(2, ergebnis.getPlaetze().length);

        Platz platz1 = ergebnis.getPlaetze()[0];
        assertEquals("1-1", platz1.getStartnummer());
        assertEquals(1, platz1.getPlatz().getAsInt());
        assertEquals(20.0, platz1.getPunkte(), 0.001);
        assertEquals(Status.Ok, platz1.getStatus());

        Platz platz2 = ergebnis.getPlaetze()[1];
        assertEquals(startnummer, platz2.getStartnummer());
        assertEquals(OptionalInt.empty(), platz2.getPlatz());
        assertEquals(0.0, platz2.getPunkte(), 0.001);
        assertEquals(Status.NichtAngetreten, platz2.getStatus());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1-1", "1-2"})
    void nichtAngetretenerDarfKeinenPlatzHaben(String startnummer) {
        RekorderListe rekorderListe = new RekorderListe();
        assertThrows(InputNotValidException.class, () -> rekorderListe.put(new Schwimmer(startnummer), 1, Status.NichtAngetreten));
    }
}
