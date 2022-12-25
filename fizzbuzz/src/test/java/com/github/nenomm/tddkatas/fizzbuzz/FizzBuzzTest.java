package com.github.nenomm.tddkatas.fizzbuzz;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FizzBuzzTest {

  @Test
  public void nullIntervalReturnsNull() {
    assertEquals("", FizzBuzz.getString(1, 1));
  }

  @Test
  public void ThreeReturnsFizz() {
    assertEquals("Fizz", FizzBuzz.getString(1, 3));
  }

  @Test
  public void FiveReturnsFizzAndBuzz() {
    assertEquals("Fizz\nBuzz", FizzBuzz.getString(1, 5));
  }

  @Test
  public void FifteenReturnsFizzBuzz() {
    assertEquals("FizzBuzz", FizzBuzz.getString(15, 15));
  }

}
