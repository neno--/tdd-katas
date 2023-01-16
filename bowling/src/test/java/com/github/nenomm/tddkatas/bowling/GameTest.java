package com.github.nenomm.tddkatas.bowling;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class GameTest {

  @Test
  public void initiallyTheScoreIsZero() {
    // given
    final Game game = new Game();

    // then
    assertEquals(0, game.score());
  }

  @Test
  public void noHitPinsMeansNoScore() {
    // given
    final Game game = new Game();

    // when
    game.roll(0);

    // then
    assertEquals(0, game.score());
  }

  @Test
  public void eachPinIsOnePointInTheCurrentFrame() {
    // given
    final Game game = new Game();

    // when
    game.roll(5);

    // then
    assertEquals(5, game.score());
  }

  @Test
  public void cannotHitMoreThanTenPinsInTheFirstRoll() {
    // given
    final Game game = new Game();

    // then
    assertThrows(IllegalArgumentException.class, () -> game.roll(11));
  }

  @Test
  public void cannotHitMoreThanTenPinsOverallInTheSecondRoll() {
    // given
    final Game game = new Game();
    game.roll(5);
    // then
    assertThrows(IllegalArgumentException.class, () -> game.roll(6));
  }

  @Test
  public void nextFrameWithoutBonusesCountOnlyCurrentScore() {
    // given
    final Game game = new Game();

    // when
    game.roll(5);
    game.roll(1);
    game.roll(8);

    // then
    assertEquals(14, game.score());
  }

  @Test
  public void nextFrameFirstRollAddsSparePoints() {
    // given
    final Game game = new Game();

    // when
    game.roll(5);
    game.roll(5);
    game.roll(2);

    // then
    assertEquals(14, game.score());
  }
}
