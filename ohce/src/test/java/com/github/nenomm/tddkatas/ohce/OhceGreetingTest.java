package com.github.nenomm.tddkatas.ohce;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OhceGreetingTest {

  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  private final PrintStream printStream = (new PrintStream(outputStreamCaptor));

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  public void morningGreeting() {
    final Instant now = ZonedDateTime.parse("2022-12-26T10:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.systemDefault()))
        .toInstant();

    final Ohce ohce = new Ohce("Pedro", printStream, now);
    assertEquals("¡Buenos días Pedro!\n", outputStreamCaptor.toString());
  }

  @Test
  public void afternoonGreeting() {
    final Instant now = ZonedDateTime.parse("2022-12-26T14:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.systemDefault()))
        .toInstant();

    final Ohce ohce = new Ohce("Pedro", printStream, now);
    assertEquals("¡Buenas tardes Pedro!\n", outputStreamCaptor.toString());
  }

  @Test
  public void nightGreeting() {
    final Instant now = ZonedDateTime.parse("2022-12-26T21:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.systemDefault()))
        .toInstant();

    final Ohce ohce = new Ohce("Pedro", printStream, now);
    assertEquals("¡Buenas noches Pedro!\n", outputStreamCaptor.toString());
  }
}
