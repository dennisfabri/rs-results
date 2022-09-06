package de.dlrg.rs.results;

import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;

public class RekorderListe {

    private final Map<String, Eingabe> daten = new HashMap<>();
    private final int offset;

    public RekorderListe(int offset) {
        this.offset = offset;
    }

    public RekorderListe() {
        this(0);
    }

    public void put(Schwimmer schwimmer, int reihenfolge) throws InputNotValidException{
        put(schwimmer, OptionalInt.of(reihenfolge), Status.Ok);
    }

    public void put(Schwimmer schwimmer, Status status) throws InputNotValidException{
        put(schwimmer, OptionalInt.empty(), status);
    }

    public void put(Schwimmer schwimmer, int reihenfolge, Status status) throws InputNotValidException {
        put(schwimmer, OptionalInt.of(reihenfolge), status);
    }

    private void put(Schwimmer schwimmer, OptionalInt reihenfolge, Status status) throws InputNotValidException {
        assertValideKombination(reihenfolge, status);
        daten.put(schwimmer.getStartnummer(), new Eingabe(reihenfolge, status));
    }

    private void assertValideKombination(OptionalInt reihenfolge, Status status) throws InputNotValidException {
        if (status == Status.NichtAngetreten && reihenfolge.isPresent()) {
            throw new ReihenfolgeTrotzNichtAngetretenException();
        }
    }

    public Ergebnis toErgebnis() {
        Platzierung[] platzierungen = daten.entrySet().stream().map(RekorderListe::eingabeToPlatzierung).toArray(Platzierung[]::new);
        return new Ergebnis(offset, platzierungen);
    }

    private static Platzierung eingabeToPlatzierung(Map.Entry<String, Eingabe> entry) {
        return new Platzierung(entry.getKey(), entry.getValue().platzierung(), entry.getValue().status());
    }
}
