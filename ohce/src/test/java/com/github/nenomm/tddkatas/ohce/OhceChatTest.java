package com.github.nenomm.tddkatas.ohce;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.hamcrest.text.StringContainsInOrder.stringContainsInOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.PrintStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OhceChatTest {

  private ByteArrayOutputStream outputStreamCaptor;

  private Ohce ohce;

  private Console console;
  final Instant now = ZonedDateTime.parse("2022-12-26T10:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.systemDefault()))
      .toInstant();

  @BeforeEach
  public void setUp() {
    outputStreamCaptor = new ByteArrayOutputStream();
    PrintStream printStream = (new PrintStream(outputStreamCaptor));

    ohce = new Ohce("Pedro", printStream, now);
    console = mock(Console.class);
  }

  @Test
  public void shouldSayAdios() {
    when(console.readLine()).thenReturn("Stop!");

    ohce.ohce(console);
    assertThat(outputStreamCaptor.toString(), endsWith("Adios Pedro\n"));
  }

  @Test
  public void shouldReverse() {
    when(console.readLine()).thenReturn("hola").thenReturn("Stop!");

    ohce.ohce(console);
    assertThat(outputStreamCaptor.toString(), containsString("aloh"));
  }

  @Test
  public void shouldDetectPalindrome() {
    when(console.readLine()).thenReturn("oto").thenReturn("Stop!");

    ohce.ohce(console);
    assertThat(outputStreamCaptor.toString(), stringContainsInOrder("oto", "¡Bonita palabra!"));
  }
}
