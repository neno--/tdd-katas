package com.github.nenomm.tddkatas.tictactoe;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TicTacToeTest {

  @Test
  public void inTheBeginningTheGameIsNotFinished() {
    final TicTacToe ticTacToe = new TicTacToe();

    assertFalse(ticTacToe.isFinished());
  }

  @Test
  public void inTheBeginningTheGameIsNotWon() {
    final TicTacToe ticTacToe = new TicTacToe();

    assertTrue(ticTacToe.getWinner().isEmpty());
  }

  @Test
  public void inTheBeginningTheBoardIsEmpty() {
    final TicTacToe ticTacToe = new TicTacToe();

    final String[] rows = ticTacToe.getBoard().split(System.lineSeparator());

    assertThat(rows, arrayWithSize(5));

    assertThat(rows[0], equalTo(" | | "));
    assertThat(rows[1], equalTo(" + + "));
    assertThat(rows[2], equalTo(" | | "));
    assertThat(rows[3], equalTo(" + + "));
    assertThat(rows[4], equalTo(" | | "));
  }

  @Test
  public void firstMoveHasPlayerX() {
    final TicTacToe ticTacToe = new TicTacToe();

    ticTacToe.put(0, 0);

    final String[] rows = ticTacToe.getBoard().split(System.lineSeparator());

    assertThat(rows[0], equalTo("X| | "));
  }

  @Test
  public void secondMoveHasPlayerY() {
    final TicTacToe ticTacToe = new TicTacToe();

    ticTacToe.put(0, 0);
    ticTacToe.put(0, 1);

    final String[] rows = ticTacToe.getBoard().split(System.lineSeparator());

    assertThat(rows[0], equalTo("X|O| "));
  }

  @Test
  public void cannotOccupySamePlace() {
    final TicTacToe ticTacToe = new TicTacToe();

    ticTacToe.put(0, 0);

    assertThrows(IllegalStateException.class, () -> ticTacToe.put(0, 0));
  }

  @Test
  public void cannotUseInvalidPosition() {
    final TicTacToe ticTacToe = new TicTacToe();
    assertThrows(IllegalArgumentException.class, () -> ticTacToe.put(0, 4));
  }

  @Test
  public void fullBoardEndsTheGame() {
    final TicTacToe ticTacToe = new TicTacToe();

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if ((i == 2) && (j == 2)) {
          break;
        }
        ticTacToe.put(i, j);
      }
    }

    assertFalse(ticTacToe.isFinished());
    assertTrue(ticTacToe.getWinner().isEmpty());

    ticTacToe.put(2, 2);

    assertTrue(ticTacToe.isFinished());
    assertTrue(ticTacToe.getWinner().isEmpty());
  }

  @Test
  public void rowWinsTheGame() {
    final TicTacToe ticTacToe = new TicTacToe();

    ticTacToe.put(0, 0);
    ticTacToe.put(1, 0);
    ticTacToe.put(0, 1);
    ticTacToe.put(1, 1);

    assertFalse(ticTacToe.isFinished());
    assertTrue(ticTacToe.getWinner().isEmpty());

    ticTacToe.put(0, 2);

    assertTrue(ticTacToe.isFinished());
    assertTrue(ticTacToe.getWinner().isPresent());
    assertEquals(ticTacToe.getWinner().get(), 'X');
  }

  @Test
  public void columnWinsTheGame() {
    final TicTacToe ticTacToe = new TicTacToe();

    ticTacToe.put(0, 0);
    ticTacToe.put(0, 1);
    ticTacToe.put(1, 0);
    ticTacToe.put(1, 1);

    assertFalse(ticTacToe.isFinished());
    assertTrue(ticTacToe.getWinner().isEmpty());

    ticTacToe.put(2, 0);

    assertTrue(ticTacToe.isFinished());
    assertTrue(ticTacToe.getWinner().isPresent());
    assertEquals(ticTacToe.getWinner().get(), 'X');
  }

  @Test
  public void diagonalWinsTheGame() {
    final TicTacToe ticTacToe = new TicTacToe();

    ticTacToe.put(0, 0);
    ticTacToe.put(0, 1);
    ticTacToe.put(1, 1);
    ticTacToe.put(0, 2);

    assertFalse(ticTacToe.isFinished());
    assertTrue(ticTacToe.getWinner().isEmpty());

    ticTacToe.put(2, 2);

    assertTrue(ticTacToe.isFinished());
    assertTrue(ticTacToe.getWinner().isPresent());
    assertEquals(ticTacToe.getWinner().get(), 'X');
  }

  @Test
  public void noMorePlayAfterWin() {
    final TicTacToe ticTacToe = new TicTacToe();

    ticTacToe.put(0, 0);
    ticTacToe.put(0, 1);
    ticTacToe.put(1, 1);
    ticTacToe.put(0, 2);
    ticTacToe.put(2, 2);

    assertThrows(IllegalStateException.class, () -> ticTacToe.put(2, 0));
  }

}
