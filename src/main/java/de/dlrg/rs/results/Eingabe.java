package de.dlrg.rs.results;

import java.util.HashMap;
import java.util.Map;

public class Eingabe {

    private final Map<String, Integer> daten = new HashMap<>();


    public void put(Schwimmer schwimmer, int reihenfolge) {
        daten.put(schwimmer.getStartnummer(), reihenfolge);
    }

    public Ergebnis toErgebnis() {
        SchwimmerPlatzierung[] platzierungen = daten.entrySet().stream().map(entry-> new SchwimmerPlatzierung(entry.getKey(),entry.getValue())).toArray(SchwimmerPlatzierung[]::new);
        return new Ergebnis(platzierungen);
    }
}
