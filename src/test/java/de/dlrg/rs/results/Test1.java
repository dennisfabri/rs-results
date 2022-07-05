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

  @Test
  void dritterPlatzBekommt16Punkte() {
    Eingabe eingabe = new Eingabe();
    eingabe.put(new Schwimmer("1-3"), 1);
    eingabe.put(new Schwimmer("1-5"), 2);
    eingabe.put(new Schwimmer("1-6"), 3);

    Ergebnis ergebnis = eingabe.toErgebnis();
    assertEquals(3, ergebnis.getPlaetze().length);

    Platz platz3 = ergebnis.getPlaetze()[2];
    assertEquals("1-6", platz3.getStartnummer());
    assertEquals(3, platz3.getPlatz());
    assertEquals(16.0, platz3.getPunkte(), 0.001);
  }

  @Test
  void sechtzehnPlatzBekommt1Punkte() {
    Eingabe eingabe = new Eingabe();
    for (int i = 1; i <= 16; i++) {
      eingabe.put(new Schwimmer("1-" + i), i);
    }

    Ergebnis ergebnis = eingabe.toErgebnis();
    assertEquals(16, ergebnis.getPlaetze().length);

    Platz platz16 = ergebnis.getPlaetze()[15];
    assertEquals("1-16", platz16.getStartnummer());
    assertEquals(16, platz16.getPlatz());
    assertEquals(1.0, platz16.getPunkte(), 0.001);
  }

  @Test
  void sibzehnPlatzBekommt1Punkte() {
    Eingabe eingabe = new Eingabe();
    for (int i = 1; i <= 17; i++) {
      eingabe.put(new Schwimmer("1-" + i), i);
    }

    Ergebnis ergebnis = eingabe.toErgebnis();
    assertEquals(17, ergebnis.getPlaetze().length);

    Platz platz17 = ergebnis.getPlaetze()[16];
    assertEquals("1-17", platz17.getStartnummer());
    assertEquals(17, platz17.getPlatz());
    assertEquals(0.0, platz17.getPunkte(), 0.001);
  }
}
