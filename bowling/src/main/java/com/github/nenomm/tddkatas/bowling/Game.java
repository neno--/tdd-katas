package com.github.nenomm.tddkatas.bowling;

import java.util.Arrays;

public class Game {
  private static final int NUMBER_OF_FRAMES = 10;
  private static final int NUMBER_OF_PINS = 10;
  private int[] bonuses = new int[NUMBER_OF_FRAMES - 1];
  private int score = 0;
  private int roll = 1;

  private int frame = 1;
  private int hitPinsInFrame = 0;

  private boolean spare = false;

  public void roll(int n) {
    validateNumberOfPins(n);
    updateHitPins(n);
    updateSpare(n);
    updateScore(n);
    updateBonus(n);
    updateRolls();
  }

  public int score() {
    return Arrays.stream(bonuses).sum() + score;
  }

  private void validateNumberOfPins(int n) {
    assert n >= 0;

    if ((roll == 1) && (n > NUMBER_OF_PINS)) {
      throw new IllegalArgumentException("Cannot hit more than 10 pins in one roll");
    }

    if ((roll == 2) && ((hitPinsInFrame + n) > 10)) {
      throw new IllegalArgumentException("Cannot hit more than 10 pins in one roll");
    }
  }

  private void updateScore(int n) {
      score += n;
  }

  private void updateBonus(int n) {
    if (frame != 1) {
      if ((roll == 1) && spare) {
        bonuses[frame - 2] = hitPinsInFrame;
        spare = false;
      }
    }
  }

  private void updateRolls() {
    if (roll == 1 && (hitPinsInFrame != NUMBER_OF_PINS)) {
      roll = 2;
    } else {
      roll = 1;
      frame++;
      hitPinsInFrame = 0;
    }
  }

  private void updateHitPins(int n) {
    hitPinsInFrame += n;
  }

  private void updateSpare(int n) {
    if ((roll == 2) && (hitPinsInFrame == NUMBER_OF_PINS)) {
      spare = true;
    }
  }

}
