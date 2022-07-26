package de.dlrg.rs.results;

import lombok.Value;

import java.util.Arrays;

@Value
public class Ergebnis {

    private int offset;
    private final Platzierung[] platzierungen;


    public Platz[] getPlaetze() {
        return Arrays.stream(platzierungen)
                .map(platzierung -> new Platz(platzierung.getStartnummer(), platzierung.getPlatzierung() + offset))
                .sorted((platz1, platz2) -> platz1.getPlatz() - platz2.getPlatz())
                .toArray(Platz[]::new);
    }

}
