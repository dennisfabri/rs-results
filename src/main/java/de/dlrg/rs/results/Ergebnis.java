package de.dlrg.rs.results;

import lombok.Value;

import java.util.*;
import java.util.stream.Stream;

@Value
public class Ergebnis {

    private int offset;
    private final Platzierung[] platzierungen;

    private static int compare(Platzierung platz1, Platzierung platz2) {
        if (platz1.getPlatzierung().isEmpty() && platz2.getPlatzierung().isEmpty()) {
            return 0;
        }
        if (platz1.getPlatzierung().isEmpty()) {
            return 1;
        }
        if (platz2.getPlatzierung().isEmpty()) {
            return -1;
        }
        return platz1.getPlatzierung().getAsInt() - platz2.getPlatzierung().getAsInt();
    }

    public Platz[] getPlaetze() {
        List<Platz> allePlaetze = new ArrayList<>();
        allePlaetze.addAll(plaetzeFuerKorrekteEingaben());
        allePlaetze.addAll(plaetzeFuerNichtKorrekteEingaben());
        return allePlaetze.toArray(Platz[]::new);
    }

    private List<Platz> plaetzeFuerKorrekteEingaben() {
        List<Platz> plaetzeOk = new ArrayList<>();
        for (List<Platzierung> gruppe : gruppiereUndSortiereKorrekteEingaben()) {
            int aktuellerPlatz = plaetzeOk.size() + 1;
            for (Platzierung platzierung : gruppe) {
                plaetzeOk.add(new Platz(platzierung.getStartnummer(), aktuellerPlatz + offset));
            }
        }
        return plaetzeOk;
    }

    private List<List<Platzierung>> gruppiereUndSortiereKorrekteEingaben() {
        Platzierung[] ok = Arrays.stream(platzierungen).filter(platzierung -> platzierung.getStatus() == Status.Ok).sorted(Ergebnis::compare).toArray(Platzierung[]::new);

        GroupedMap<Integer, Platzierung> gruppiertePlatzierungen = new GroupedMap<>();
        for (Platzierung platzierung : ok) {
            gruppiertePlatzierungen.put(platzierung.getPlatzierung().getAsInt(), platzierung);
        }
        return gruppiertePlatzierungen.entrySet().stream().sorted(Map.Entry.comparingByKey()).map(entry -> entry.getValue()).toList();
    }

    private List<Platz> plaetzeFuerNichtKorrekteEingaben() {
        return Arrays.stream(platzierungen).filter(platzierung1 -> platzierung1.getStatus() != Status.Ok)
                .map(platzierung -> new Platz(platzierung.getStartnummer(), OptionalInt.empty(), platzierung.getStatus()))
                .toList();
    }
}
