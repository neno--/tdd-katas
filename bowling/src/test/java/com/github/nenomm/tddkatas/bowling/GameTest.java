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

  @Test
  public void nextTwoRollsAddStrikePoints() {
    // given
    final Game game = new Game();

    // when
    game.roll(10);
    game.roll(5);
    game.roll(2);

    // then
    assertEquals(24, game.score());
  }

  @Test
  public void shouldBe30() {
    // given
    final Game game = new Game();

    // when
    game.roll(10);
    game.roll(10);

    // then
    assertEquals(30, game.score());
  }

  @Test
  public void shouldBe60() {
    // given
    final Game game = new Game();

    // when
    game.roll(10);
    game.roll(10);
    game.roll(10);

    // then
    assertEquals(60, game.score());
  }

  @Test
  public void perfectGameWithoutExtensionsIs270() {
    // given
    final Game game = new Game();

    // when
    game.roll(10);
    game.roll(10);
    game.roll(10);
    game.roll(10);
    game.roll(10);
    game.roll(10);
    game.roll(10);
    game.roll(10);
    game.roll(10);
    game.roll(10);

    // then
    assertEquals(270, game.score());
  }

  @Test
  public void perfectGameWithOneExtensionsIs290() {
    // given
    final Game game = new Game();

    // when
    game.roll(10);
    game.roll(10);
    game.roll(10);
    game.roll(10);
    game.roll(10);
    game.roll(10);
    game.roll(10);
    game.roll(10);
    game.roll(10);
    game.roll(10);
    // extension
    game.roll(10);

    // then
    assertEquals(290, game.score());
  }

  @Test
  public void perfectGameis300() {
    // given
    final Game game = new Game();

    // when
    game.roll(10);
    game.roll(10);
    game.roll(10);
    game.roll(10);
    game.roll(10);
    game.roll(10);
    game.roll(10);
    game.roll(10);
    game.roll(10);
    game.roll(10);

    // additional rolls
    game.roll(10);
    game.roll(10);

    // then
    assertEquals(300, game.score());
  }
}
