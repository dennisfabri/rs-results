package de.dlrg.rs.results;

public record Eingabe(int platzierung, Status status) {
    public Eingabe(int platzierung) {
        this(platzierung, Status.Ok);
    }
}
