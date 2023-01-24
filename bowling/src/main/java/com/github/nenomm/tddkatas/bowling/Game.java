package com.github.nenomm.tddkatas.bowling;

import java.util.Arrays;

public class Game {

  private static final int NUMBER_OF_FRAMES = 10;
  private static final int NUMBER_OF_PINS = 10;

  // which roles should be included as spare bonuses
  private final int[] spareBonusRollsForFrame = new int[NUMBER_OF_FRAMES];
  private final int[][] strikeBonusRollsForFrame = new int[NUMBER_OF_FRAMES][2];

  // there can be at most 9*2 + 1(strike) + 2 additional rolls
  private final int[] scoreForRoll = new int[(NUMBER_OF_FRAMES - 1) * 2 + 1 + 2];

  // first or second throw
  private int step = 1;
  private int roll = 0;
  private int frame = 0;
  private int hitPinsInFrame = 0;

  private boolean end = false;
  private boolean extended = false;
  private int extraRolls = 0;

  public Game() {
    Arrays.fill(spareBonusRollsForFrame, -1);
    for (int i = 0; i < strikeBonusRollsForFrame.length; i++) {
      Arrays.fill(strikeBonusRollsForFrame[i], -1);
    }
  }

  public void roll(int n) {
    if (!end) {
      if (!extended) {
        validateNumberOfPins(n);
        updateHitPins(n);
        updateSpare();
        updateStrike();
        updateScore(n);
        updateStep();
      } else {
        extraRolls--;
        if (extraRolls == 0) {
          end = true;
        }
      }
      roll++;
    }
  }

  public int score() {
    return spareBonuses() + strikeBonuses() + Arrays.stream(scoreForRoll).sum();
  }

  private void validateNumberOfPins(int n) {
    assert n >= 0;

    if ((step == 1) && (n > NUMBER_OF_PINS)) {
      throw new IllegalArgumentException("Cannot hit more than 10 pins in one roll");
    }

    if ((step == 2) && ((hitPinsInFrame + n) > 10)) {
      throw new IllegalArgumentException("Cannot hit more than 10 pins in one roll");
    }
  }

  private void updateScore(int n) {
    scoreForRoll[roll] = n;
  }

  private void updateStep() {
    if (step == 1 && (hitPinsInFrame != NUMBER_OF_PINS)) {
      step = 2;
    } else {
      step = 1;
      frame++;
      hitPinsInFrame = 0;
    }

    if (frame > (NUMBER_OF_FRAMES - 1)) {
      extended = true;
    }
  }

  private void updateHitPins(int n) {
    hitPinsInFrame += n;
  }

  private void updateSpare() {
    if ((step == 2) && (hitPinsInFrame == NUMBER_OF_PINS)) {
      spareBonusRollsForFrame[frame] = roll + 1;
      if ((frame == (NUMBER_OF_FRAMES - 1))) {
        extraRolls = 1;
      }
    }
  }

  private void updateStrike() {
    if ((step == 1) && (hitPinsInFrame == NUMBER_OF_PINS)) {
      strikeBonusRollsForFrame[frame][0] = roll + 1;
      strikeBonusRollsForFrame[frame][1] = roll + 2;
      if ((frame == (NUMBER_OF_FRAMES - 1))) {
        extraRolls = 2;
      }
    }
  }

  private int spareBonuses() {
    return Arrays.stream(spareBonusRollsForFrame)
        .filter(roll -> roll != -1)
        .map(roll -> scoreForRoll[roll])
        .sum();
  }

  private int strikeBonuses() {
    return Arrays.stream(strikeBonusRollsForFrame)
        .filter(ints -> ints[0] != -1)
        .mapToInt(rolls -> scoreForRoll[rolls[0]] + scoreForRoll[rolls[1]])
        .sum();
  }
}
