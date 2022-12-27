package com.github.nenomm.tddkatas.ohce2.ohce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.nenomm.tddkatas.ohce2.Ohce2;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OhceGreetingTest {

  Ohce2 ohce;

  @BeforeEach
  public void setUp() {
    ohce = new Ohce2("Pedro");
  }

  @Test
  public void morningGreeting() {
    final Instant now = ZonedDateTime.parse("2022-12-26T10:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.systemDefault()))
        .toInstant();

    assertEquals("¡Buenos días Pedro!", ohce.greet(now));
  }

  @Test
  public void afternoonGreeting() {
    final Instant now = ZonedDateTime.parse("2022-12-26T14:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.systemDefault()))
        .toInstant();

    assertEquals("¡Buenas tardes Pedro!", ohce.greet(now));
  }

  @Test
  public void nightGreeting() {
    final Instant now = ZonedDateTime.parse("2022-12-26T21:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.systemDefault()))
        .toInstant();

    assertEquals("¡Buenas noches Pedro!", ohce.greet(now));
  }

  @Test
  public void cannotGreetMoreThanOnce() {
    final Instant now = ZonedDateTime.parse("2022-12-26T21:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.systemDefault()))
        .toInstant();

    ohce.greet(now);
    assertThrows(IllegalStateException.class, () -> ohce.greet(now));
  }
}
