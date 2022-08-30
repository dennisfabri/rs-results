package de.dlrg.rs.results;

import lombok.Value;

import java.util.Optional;
import java.util.OptionalInt;

@Value
public class Platz {

  private static final double[] Points =
      new double[] {20, 18, 16, 14, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

  private final String startnummer;
  private final int platz;
  private final Status status;

  public double getPunkte() {
    if (status == Status.Disqualifikation) {
      return 0;
    }

    if (Points.length >= platz) {
      return Points[platz - 1];
    } else {
      return 0;
    }
  }

  public OptionalInt getPlatz() {
    if (status == Status.Disqualifikation) {
      return OptionalInt.empty();
    }
    return OptionalInt.of(platz);
  }
}
