package com.github.nenomm.tddkatas.ohce2.ohce;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.hamcrest.text.StringContainsInOrder.stringContainsInOrder;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.nenomm.tddkatas.ohce2.Ohce2;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OhceChatTest {

  private ByteArrayOutputStream outputStreamCaptor;
  private PrintStream printStream;

  private Ohce2 ohce;
  final Instant now = ZonedDateTime.parse("2022-12-26T10:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.systemDefault()))
      .toInstant();

  @BeforeEach
  public void setUp() {
    outputStreamCaptor = new ByteArrayOutputStream();
    printStream = (new PrintStream(outputStreamCaptor));

    ohce = new Ohce2("Pedro");
    ohce.greet(now);
  }

  @Test
  public void cannotChatWithoutGreeting() {
    final Ohce2 ohce = new Ohce2("Pedro");

    assertThrows(IllegalStateException.class, () -> ohce.ohce("boom", printStream));
  }

  @Test
  public void shouldSayAdios() {
    assertFalse(ohce.ohce("Stop!", printStream));
    assertThat(outputStreamCaptor.toString(), endsWith("Adios Pedro\n"));
  }

  @Test
  public void cannotChatAfterAdios() {
    ohce.ohce("Stop!", printStream);

    assertThrows(IllegalStateException.class, () -> ohce.ohce("boom", printStream));
  }

  @Test
  public void shouldReverse() {
    assertTrue(ohce.ohce("hola", printStream));
    assertThat(outputStreamCaptor.toString(), endsWith("aloh\n"));
  }

  @Test
  public void shouldDetectPalindrome() {
    assertTrue(ohce.ohce("oto", printStream));
    assertThat(outputStreamCaptor.toString(), stringContainsInOrder("oto\n", "¡Bonita palabra!\n"));
  }
}
