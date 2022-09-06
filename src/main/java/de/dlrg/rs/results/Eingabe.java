package de.dlrg.rs.results;

import java.util.OptionalInt;

public record Eingabe(OptionalInt platzierung, Status status) {
    public Eingabe(OptionalInt platzierung) {
        this(platzierung, Status.Ok);
    }
}
