package com.github.nenomm.tddkatas.ohce2;

import java.io.PrintStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Ohce2 {

  private String name;
  private boolean greeted;
  private boolean canContinue;

  public Ohce2(String name) {
    this.name = name;
    this.greeted = false;
    this.canContinue = true;
  }

  public String greet(Instant now) {
    if (!greeted) {
      greeted = true;
      return String.format("%s %s!", greetingOfTheDay(now), name);
    } else {
      throw new IllegalStateException("Cannot greet more than once.");
    }
  }

  public boolean ohce(String word, PrintStream out) {
    if (!greeted) {
      throw new IllegalStateException("Not greeted yet.");
    }

    if (!canContinue) {
      throw new IllegalStateException("Already stopped.");
    }

    if ("Stop!".equals(word)) {
      out.println(String.format("Adios %s", this.name));
      canContinue = false;
    } else {
      final String reversed = reverse(word);
      out.println(reversed);
      if (reversed.equals(word)) {
        out.println("¡Bonita palabra!");
      }
    }

    return canContinue;
  }

  private String greetingOfTheDay(Instant now) {
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

  private String reverse(String word) {
    String reversed = "";

    for (int i = 0; i < word.length(); i++) {
      reversed = word.charAt(i) + reversed;
    }

    return reversed;
  }

}
