package de.dlrg.rs.results;

import lombok.Value;

import java.util.OptionalInt;

@Value
public class Platzierung {
  private final String startnummer;
  private final OptionalInt platzierung;
  private final Status status;
}
