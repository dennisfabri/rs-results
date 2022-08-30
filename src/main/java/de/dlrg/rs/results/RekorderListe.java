package de.dlrg.rs.results;

import java.util.HashMap;
import java.util.Map;

public class RekorderListe {

    private final Map<String, Eingabe> daten = new HashMap<>();
    private final int offset;

    public RekorderListe(int offset) {
        this.offset = offset;
    }

    public RekorderListe() {
        this(0);
    }

    public void put(Schwimmer schwimmer, int reihenfolge) {
        daten.put(schwimmer.getStartnummer(), new Eingabe(reihenfolge));
    }

    public void put(Schwimmer schwimmer, int reihenfolge, Status status) {
        daten.put(schwimmer.getStartnummer(), new Eingabe(reihenfolge, status));
    }

    public Ergebnis toErgebnis() {
        Platzierung[] platzierungen = daten.entrySet().stream().map(RekorderListe::eingabeToPlatzierung).toArray(Platzierung[]::new);
        return new Ergebnis(offset, platzierungen);
    }

    private static Platzierung eingabeToPlatzierung(Map.Entry<String, Eingabe> entry) {
        return new Platzierung(entry.getKey(), entry.getValue().platzierung(), entry.getValue().status());
    }
}
