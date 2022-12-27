package com.github.nenomm.tddkatas.ohce;

import java.io.Console;
import java.io.PrintStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Ohce {

  private final String name;
  private final PrintStream out;

  public Ohce(String name, PrintStream out, Instant now) {
    this.name = name;
    this.out = out;

    out.println(String.format("%s %s!", greet(now), name));
  }

  private String greet(Instant now) {
    final ZonedDateTime time = now.atZone(ZoneId.systemDefault());
    String greeting;

    if (time.getHour() >= 6 && time.getHour() < 12) {
      greeting = "¡Buenos días";
    } else if (time.getHour() >= 12 && time.getHour() < 20) {
      greeting = "¡Buenas tardes";
    } else {
      greeting = "¡Buenas noches";
    }

    return greeting;
  }

  public void ohce(Console console) {
    while (true) {
      final String word = console.readLine();
      if ("Stop!".equals(word)) {
        out.println(String.format("Adios %s", this.name));
        break;
      }
      final String reversed = reverse(word);
      out.println(reversed);
      if (reversed.equals(word)) {
        out.println("¡Bonita palabra!");
      }
    }
  }

  private String reverse(String word) {
    String reversed = "";

    for (int i = 0; i < word.length(); i++) {
      reversed = word.charAt(i) + reversed;
    }

    return reversed;
  }
}
