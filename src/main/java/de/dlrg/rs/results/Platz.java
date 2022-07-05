package de.dlrg.rs.results;

import lombok.Value;

@Value
public class Platz {

  private static final double[] Points = new double[] {20, 18, 16};

  private final String startnummer;
  private final int platz;

  public double getPunkte() {
    return Points[platz - 1];
  }
}
