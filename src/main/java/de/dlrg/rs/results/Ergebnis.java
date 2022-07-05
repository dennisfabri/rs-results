package de.dlrg.rs.results;

import lombok.Value;

import java.util.Arrays;

@Value
public class Ergebnis {

    private final SchwimmerPlatzierung[] platzierungen;

    public Platz[] getPlaetze() {
        return Arrays.stream(platzierungen).map(platzierung -> new Platz(platzierung.getStartnummer(),platzierung.getPlatzierung())).toArray(Platz[]::new);
    }

}
