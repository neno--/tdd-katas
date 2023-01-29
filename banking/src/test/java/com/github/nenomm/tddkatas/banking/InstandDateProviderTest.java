package com.github.nenomm.tddkatas.banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;

public class InstandDateProviderTest {

  private static Supplier<Instant> getProvider(ZonedDateTime zonedDateTime) {
    return () -> zonedDateTime.toInstant();
  }

  @Test
  public void shouldReturnHardcodedDate() {
    final InstantDateProvider dateProvider = new InstantDateProvider(getProvider(ZonedDateTime.of(2015, 12, 24, 0, 0, 0, 0, ZoneId.systemDefault())));
    assertEquals("24.12.2015", dateProvider.dateString());
  }

  @Test
  public void shouldReturnSingleDigitDay() {
    final InstantDateProvider dateProvider = new InstantDateProvider(getProvider(ZonedDateTime.of(2015, 12, 4, 0, 0, 0, 0, ZoneId.systemDefault())));
    assertEquals("4.12.2015", dateProvider.dateString());
  }

}
