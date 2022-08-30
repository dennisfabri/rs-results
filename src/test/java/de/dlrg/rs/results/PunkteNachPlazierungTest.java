package de.dlrg.rs.results;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PunkteNachPlazierungTest {

    @Test
    void leereEingabeErgibtLeeresErgebnis() {
        RekorderListe rekorderListe = new RekorderListe();

        Ergebnis ergebnis = rekorderListe.toErgebnis();

        assertEquals(0, ergebnis.getPlaetze().length);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1-1", "1-2"})
    void ersterPlatzBekommt20Punkte(String startnummer) {
        RekorderListe rekorderListe = new RekorderListe();
        rekorderListe.put(new Schwimmer(startnummer), 1);

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(1, ergebnis.getPlaetze().length);

        Platz platz1 = ergebnis.getPlaetze()[0];

        assertEquals(20.0, platz1.getPunkte(), 0.001);
        assertEquals(startnummer, platz1.getStartnummer());
        assertEquals(1, platz1.getPlatz().getAsInt());
    }

    @ParameterizedTest
    @ValueSource(strings = {"2-1", "2-2"})
    void zweiterPlatzBekommt18Punkte(String startnummer) {
        RekorderListe rekorderListe = new RekorderListe();
        rekorderListe.put(new Schwimmer("1-1"), 1);
        rekorderListe.put(new Schwimmer(startnummer), 2);

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(2, ergebnis.getPlaetze().length);

        Platz platz1 = ergebnis.getPlaetze()[0];
        assertEquals("1-1", platz1.getStartnummer());
        assertEquals(1, platz1.getPlatz().getAsInt());
        assertEquals(20.0, platz1.getPunkte(), 0.001);

        Platz platz2 = ergebnis.getPlaetze()[1];
        assertEquals(startnummer, platz2.getStartnummer());
        assertEquals(2, platz2.getPlatz().getAsInt());
        assertEquals(18.0, platz2.getPunkte(), 0.001);
    }

    @Test
    void dritterPlatzBekommt16Punkte() {
        RekorderListe rekorderListe = new RekorderListe();
        rekorderListe.put(new Schwimmer("1-3"), 1);
        rekorderListe.put(new Schwimmer("1-5"), 2);
        rekorderListe.put(new Schwimmer("1-6"), 3);

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(3, ergebnis.getPlaetze().length);

        Platz platz3 = ergebnis.getPlaetze()[2];
        assertEquals("1-6", platz3.getStartnummer());
        assertEquals(3, platz3.getPlatz().getAsInt());
        assertEquals(16.0, platz3.getPunkte(), 0.001);
    }

    @Test
    void vierterPlatzBekommt14Punkte() {
        RekorderListe rekorderListe = new RekorderListe();
        for (int i = 1; i <= 4; i++) {
            rekorderListe.put(new Schwimmer("1-" + i), i);
        }

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(4, ergebnis.getPlaetze().length);

        Platz platz4 = ergebnis.getPlaetze()[3];
        assertEquals("1-4", platz4.getStartnummer());
        assertEquals(4, platz4.getPlatz().getAsInt());
        assertEquals(14.0, platz4.getPunkte(), 0.001);
    }

    @Test
    void fuenfterPlatzBekommt12Punkte() {
        RekorderListe rekorderListe = new RekorderListe();
        for (int i = 1; i <= 5; i++) {
            rekorderListe.put(new Schwimmer("1-" + i), i);
        }

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(5, ergebnis.getPlaetze().length);

        Platz platz5 = ergebnis.getPlaetze()[4];
        assertEquals("1-5", platz5.getStartnummer());
        assertEquals(5, platz5.getPlatz().getAsInt());
        assertEquals(12.0, platz5.getPunkte(), 0.001);
    }

    
    @Test
    void sesterPlatzBekommt10Punkte() {
        RekorderListe rekorderListe = new RekorderListe();
        for (int i = 1; i <= 6; i++) {
            rekorderListe.put(new Schwimmer("1-" + i), i);
        }

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(6, ergebnis.getPlaetze().length);

        Platz platz6 = ergebnis.getPlaetze()[5];
        assertEquals("1-6", platz6.getStartnummer());
        assertEquals(6, platz6.getPlatz().getAsInt());
        assertEquals(11.0, platz6.getPunkte(), 0.001);
    }

    @Test
    void sibtnerPlatzBekommt10Punkte() {
        RekorderListe rekorderListe = new RekorderListe();
        for (int i = 1; i <= 7; i++) {
            rekorderListe.put(new Schwimmer("1-" + i), i);
        }

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(7, ergebnis.getPlaetze().length);

        Platz platz7 = ergebnis.getPlaetze()[6];
        assertEquals("1-7", platz7.getStartnummer());
        assertEquals(7, platz7.getPlatz().getAsInt());
        assertEquals(10.0, platz7.getPunkte(), 0.001);
    }
    @Test
    void achterPlatzBekommt9Punkte() {
        RekorderListe rekorderListe = new RekorderListe();
        for (int i = 1; i <= 8; i++) {
            rekorderListe.put(new Schwimmer("1-" + i), i);
        }

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(8, ergebnis.getPlaetze().length);

        Platz platz8 = ergebnis.getPlaetze()[7];
        assertEquals("1-8", platz8.getStartnummer());
        assertEquals(8, platz8.getPlatz().getAsInt());
        assertEquals(9.0, platz8.getPunkte(), 0.001);
    }
    @Test
    void neunterPlatzBekommt8Punkte() {
        RekorderListe rekorderListe = new RekorderListe();
        for (int i = 1; i <= 9; i++) {
            rekorderListe.put(new Schwimmer("1-" + i), i);
        }

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(9, ergebnis.getPlaetze().length);

        Platz platz9 = ergebnis.getPlaetze()[8];
        assertEquals("1-9", platz9.getStartnummer());
        assertEquals(9, platz9.getPlatz().getAsInt());
        assertEquals(8.0, platz9.getPunkte(), 0.001);
    }
    @Test
    void zehnterPlatzBekommt7Punkte() {
        RekorderListe rekorderListe = new RekorderListe();
        for (int i = 1; i <= 10; i++) {
            rekorderListe.put(new Schwimmer("1-" + i), i);
        }

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(10, ergebnis.getPlaetze().length);

        Platz platz10 = ergebnis.getPlaetze()[9];
        assertEquals("1-10", platz10.getStartnummer());
        assertEquals(10, platz10.getPlatz().getAsInt());
        assertEquals(7.0, platz10.getPunkte(), 0.001);
    }
    @Test
    void elfterPlatzBekommt6Punkte() {
        RekorderListe rekorderListe = new RekorderListe();
        for (int i = 1; i <= 11; i++) {
            rekorderListe.put(new Schwimmer("1-" + i), i);
        }

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(11, ergebnis.getPlaetze().length);

        Platz platz11 = ergebnis.getPlaetze()[10];
        assertEquals("1-11", platz11.getStartnummer());
        assertEquals(11, platz11.getPlatz().getAsInt());
        assertEquals(6.0, platz11.getPunkte(), 0.001);
    }
    @Test
    void zfoelfPlatzBekommt5Punkte() {
        RekorderListe rekorderListe = new RekorderListe();
        for (int i = 1; i <= 12; i++) {
            rekorderListe.put(new Schwimmer("1-" + i), i);
        }

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(12, ergebnis.getPlaetze().length);

        Platz platz12 = ergebnis.getPlaetze()[11];
        assertEquals("1-12", platz12.getStartnummer());
        assertEquals(12, platz12.getPlatz().getAsInt());
        assertEquals(5.0, platz12.getPunkte(), 0.001);
    }
    @Test
    void dreizehnPlatzBekommt4Punkte() {
        RekorderListe rekorderListe = new RekorderListe();
        for (int i = 1; i <= 13; i++) {
            rekorderListe.put(new Schwimmer("1-" + i), i);
        }

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(13, ergebnis.getPlaetze().length);

        Platz platz13 = ergebnis.getPlaetze()[12];
        assertEquals("1-13", platz13.getStartnummer());
        assertEquals(13, platz13.getPlatz().getAsInt());
        assertEquals(4.0, platz13.getPunkte(), 0.001);
    }
    @Test
    void vierzehnterPlatzBekommt3Punkte() {
        RekorderListe rekorderListe = new RekorderListe();
        for (int i = 1; i <= 14; i++) {
            rekorderListe.put(new Schwimmer("1-" + i), i);
        }

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(14, ergebnis.getPlaetze().length);

        Platz platz14 = ergebnis.getPlaetze()[13];
        assertEquals("1-14", platz14.getStartnummer());
        assertEquals(14, platz14.getPlatz().getAsInt());
        assertEquals(3.0, platz14.getPunkte(), 0.001);
    }
    @Test
    void fuenfzenterPlatzBekommt2Punkte() {
        RekorderListe rekorderListe = new RekorderListe();
        for (int i = 1; i <= 15; i++) {
            rekorderListe.put(new Schwimmer("1-" + i), i);
        }

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(15, ergebnis.getPlaetze().length);

        Platz platz15 = ergebnis.getPlaetze()[14];
        assertEquals("1-15", platz15.getStartnummer());
        assertEquals(15, platz15.getPlatz().getAsInt());
        assertEquals(2.0, platz15.getPunkte(), 0.001);
    }
    @Test
    void sechtzehnterPlatzBekommt1Punkte() {
        RekorderListe rekorderListe = new RekorderListe();
        for (int i = 1; i <= 16; i++) {
            rekorderListe.put(new Schwimmer("1-" + i), i);
        }

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(16, ergebnis.getPlaetze().length);

        Platz platz16 = ergebnis.getPlaetze()[15];
        assertEquals("1-16", platz16.getStartnummer());
        assertEquals(16, platz16.getPlatz().getAsInt());
        assertEquals(1.0, platz16.getPunkte(), 0.001);
    }


    @Test
    void siebzenterPlatzBekommt1Punkte() {
        RekorderListe rekorderListe = new RekorderListe();
        for (int i = 1; i <= 17; i++) {
            rekorderListe.put(new Schwimmer("1-" + i), i);
        }

        Ergebnis ergebnis = rekorderListe.toErgebnis();
        assertEquals(17, ergebnis.getPlaetze().length);

        Platz platz17 = ergebnis.getPlaetze()[16];
        assertEquals("1-17", platz17.getStartnummer());
        assertEquals(17, platz17.getPlatz().getAsInt());
        assertEquals(0.0, platz17.getPunkte(), 0.001);
    }


}
