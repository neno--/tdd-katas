package com.github.nenomm.tddkatas.fizzbuzz;

public class FizzBuzz {

  public static String getString(int start, int stop) {
    final StringBuilder result = new StringBuilder();
    boolean addNewline = false;
    for (int i = start; i <= stop; i++) {
      final boolean modBy3 = i % 3 == 0;
      final boolean modBy5 = i % 5 == 0;

      if (modBy3 && modBy5) {
        result.append(addNewline ? System.lineSeparator() : "").append("FizzBuzz");
        addNewline = true;
      } else if (modBy3) {
        result.append(addNewline ? System.lineSeparator() : "").append("Fizz");
        addNewline = true;
      } else if (modBy5) {
        result.append(addNewline ? System.lineSeparator() : "").append("Buzz");
        addNewline = true;
      }
    }
    return result.toString();
  }

  // If I create this helper, I won't be able to test it, because it is implementation specific, and
  // I should perhaps only test behaviour?
  private static String getString(int n) {
    throw new UnsupportedOperationException();
  }

  // not really, I could extract this in a private method and I will test it indirectly, by testing the public method.
}
