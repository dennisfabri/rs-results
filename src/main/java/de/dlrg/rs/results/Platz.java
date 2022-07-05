package de.dlrg.rs.results;

import lombok.Value;

@Value
public class Platz {

  private static final double[] Points =
      new double[] {20, 18, 16, 14, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

  private final String startnummer;
  private final int platz;

  public double getPunkte() {
    if (Points.length >= platz) {
      return Points[platz - 1];
    } else {
      return 0;
    }
  }
}
