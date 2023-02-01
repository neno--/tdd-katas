package com.github.nenomm.tddkatas.marsrover;

import static com.github.nenomm.tddkatas.marsrover.Orientation.E;
import static com.github.nenomm.tddkatas.marsrover.Orientation.N;
import static com.github.nenomm.tddkatas.marsrover.Orientation.W;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RoverCommandTranslatorTest {

  @Test
  public void translatorAcceptsInitialCoordinates() {
    final RoverCommandTranslator translator = new RoverCommandTranslator(0, 0, N);

    assertEquals(0, translator.getX());
    assertEquals(0, translator.getY());
    assertEquals(N, translator.getOrientation());
  }

  @Test
  public void forwardMovesWhereRoverIsOriented() {
    final RoverCommandTranslator translator = new RoverCommandTranslator(0, 0, N);
    translator.receive('f');

    assertEquals(0, translator.getX());
    assertEquals(1, translator.getY());
    assertEquals(N, translator.getOrientation());
  }

  @Test
  public void backwardMovesWhereRoverIsOriented() {
    final RoverCommandTranslator translator = new RoverCommandTranslator(0, 0, N);
    translator.receive('b');

    assertEquals(0, translator.getX());
    assertEquals(-1, translator.getY());
    assertEquals(N, translator.getOrientation());
  }

  @Test
  public void leftTurnChangesOrientation() {
    final RoverCommandTranslator translator = new RoverCommandTranslator(0, 0, N);
    translator.receive('l');

    assertEquals(0, translator.getX());
    assertEquals(0, translator.getY());
    assertEquals(E, translator.getOrientation());
  }

  @Test
  public void rightTurnChangesOrientation() {
    final RoverCommandTranslator translator = new RoverCommandTranslator(0, 0, N);
    translator.receive('r');

    assertEquals(0, translator.getX());
    assertEquals(0, translator.getY());
    assertEquals(W, translator.getOrientation());
  }

  @Test
  public void turnLeftAndAdvance() {
    final RoverCommandTranslator translator = new RoverCommandTranslator(0, 0, N);

    translator.receive('l', 'f');

    assertEquals(-1, translator.getX());
    assertEquals(0, translator.getY());
    assertEquals(E, translator.getOrientation());
  }

  @Test
  public void moveOutsideOfNorthTakesUsToWest() {
    final RoverCommandTranslator translator = new RoverCommandTranslator(0, 5, N);

    translator.receive('f');

    assertEquals(0, translator.getX());
    assertEquals(-5, translator.getY());
    assertEquals(N, translator.getOrientation());
  }
}
