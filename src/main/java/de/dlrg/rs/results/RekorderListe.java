package de.dlrg.rs.results;

import java.util.HashMap;
import java.util.Map;

public class RekorderListe {

    private final Map<String, Integer> daten = new HashMap<>();
    private final int offset;
    public RekorderListe(int offset) {

        this.offset = offset;
    }

    public RekorderListe() {
        this(0);
    }


    public void put(Schwimmer schwimmer, int reihenfolge) {
        daten.put(schwimmer.getStartnummer(), reihenfolge);
    }

    public Ergebnis toErgebnis() {
        Platzierung[] platzierungen = daten.entrySet().stream().map(entry-> new Platzierung(entry.getKey(),entry.getValue())).toArray(Platzierung[]::new);
        return new Ergebnis(offset, platzierungen);
    }
}
