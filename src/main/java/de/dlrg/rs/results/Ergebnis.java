package de.dlrg.rs.results;

import lombok.Value;

import java.util.*;

@Value
public class Ergebnis {

    private int offset;
    private final Platzierung[] platzierungen;

    private static int compare(Platz platz1, Platz platz2) {
        return platz1.getPlatz().getAsInt() - platz2.getPlatz().getAsInt();
    }

    private static int compare(Platzierung platz1, Platzierung platz2) {
        return platz1.getPlatzierung() - platz2.getPlatzierung();
    }

    public Platz[] getPlaetze() {
        List<Platz> allePlaetze = new ArrayList<>();
        allePlaetze.addAll(plaetzeFuerKorrekteEingaben());
        allePlaetze.addAll(plaetzeFuerNichtKorrekteEingaben());
        return allePlaetze.toArray(Platz[]::new);
    }

    private List<Platz> plaetzeFuerKorrekteEingaben() {
        Platzierung[] ok = Arrays.stream(platzierungen).filter(platzierung -> platzierung.getStatus() == Status.Ok).sorted(Ergebnis::compare).toArray(Platzierung[]::new);

        GroupedMap<Integer, Platzierung> gruppiertePlatzierungen = new GroupedMap<>();
        for (Platzierung platzierung : ok) {
            gruppiertePlatzierungen.put(platzierung.getPlatzierung(), platzierung);
        }

        List<Platz> plaetzeOk = new ArrayList<>();
        for (Map.Entry<Integer, List<Platzierung>> gruppe : gruppiertePlatzierungen.entrySet().stream().sorted(Map.Entry.comparingByKey()).toList()) {
            int aktuellerPlatz = plaetzeOk.size() + 1;
            for (Platzierung platzierung : gruppe.getValue()) {
                plaetzeOk.add(new Platz(platzierung.getStartnummer(), aktuellerPlatz + offset, platzierung.getStatus()));
            }
        }
        return plaetzeOk;
    }

    private List<Platz> plaetzeFuerNichtKorrekteEingaben() {
        Platzierung[] nichtOk = Arrays.stream(platzierungen).filter(platzierung -> platzierung.getStatus() != Status.Ok).toArray(Platzierung[]::new);
        return Arrays.stream(nichtOk)
                .map(platzierung -> new Platz(platzierung.getStartnummer(), 0, platzierung.getStatus()))
                .toList();
    }
}
