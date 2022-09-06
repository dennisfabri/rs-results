package de.dlrg.rs.results;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Optional;
import java.util.OptionalInt;

@Value
@AllArgsConstructor
public class Platz {

  private static final double[] Points =
      new double[] {20, 18, 16, 14, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

  private final String startnummer;
  private final OptionalInt platz;
  private final Status status;

  public Platz(String startnummer, int platz) {
    this(startnummer, OptionalInt.of(platz), Status.Ok);
  }

  public double getPunkte() {
    if (status == Status.Disqualifikation) {
      return 0;
    }
    if (platz.isEmpty()) {
      return 0;
    }

    if (Points.length >= platz.getAsInt()) {
      return Points[platz.getAsInt() - 1];
    } else {
      return 0;
    }
  }
}
