package com.github.nenomm.tddkatas.banking;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

public class InstantDateProvider implements DateProvider {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.M.yyyy");
  private final Supplier<Instant> instantSupplier;

  public InstantDateProvider(Supplier<Instant> instantSupplier) {
    this.instantSupplier = instantSupplier;
  }

  @Override
  public String dateString() {
    return instantSupplier.get().atZone(ZoneId.systemDefault()).format(FORMATTER);
  }
}
