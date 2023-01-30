package com.github.nenomm.tddkatas.tictactoe;

import java.util.Arrays;
import java.util.Optional;

public class TicTacToe {

  enum Player {
    X('X'),
    O('O');

    private final char label;

    Player(char label) {
      this.label = label;
    }

    public char label() {
      return this.label;
    }
  }

  private boolean finished = false;
  private final char[][] board = new char[3][3];

  private Player currentPlayer = Player.X;

  private Player winner = null;

  public TicTacToe() {
    initBoard();
  }

  private void initBoard() {
    for (int i = 0; i < board.length; i++) {
      Arrays.fill(board[i], ' ');
    }
  }

  public void put(int row, int column) {
    validateFinished();
    validateIndex(row);
    validateIndex(column);
    validatePosition(row, column);
    board[row][column] = currentPlayer.label();

    if (checkWin(row, column)) {
      finished = true;
      winner = currentPlayer;
    } else {
      finished = !containsEmptyLocations();
    }

    nextPlayer();
  }

  private void validateFinished() {
    if (finished) {
      throw new IllegalStateException("Game is already finished");
    }
  }

  private boolean checkWin(int row, int column) {
    return checkRow(row) || checkColumn(column) || checkDiagonal();
  }

  private boolean checkDiagonal() {
    final boolean first = (board[0][0] == currentPlayer.label()) &&
        (board[1][1] == currentPlayer.label()) &&
        (board[2][2] == currentPlayer.label());

    final boolean second = (board[0][2] == currentPlayer.label()) &&
        (board[1][1] == currentPlayer.label()) &&
        (board[2][0] == currentPlayer.label());

    return first || second;
  }

  private boolean checkColumn(int column) {
    return (board[0][column] == currentPlayer.label()) &&
        (board[1][column] == currentPlayer.label()) &&
        (board[2][column] == currentPlayer.label());
  }

  private boolean checkRow(int row) {
    return (board[row][0] == currentPlayer.label()) &&
        (board[row][1] == currentPlayer.label()) &&
        (board[row][2] == currentPlayer.label());
  }

  private void validateIndex(int row) {
    if ((row < 0) || (row > board.length)) {
      throw new IllegalArgumentException("Invalid position");
    }
  }

  private void validatePosition(int row, int column) {
    if (board[row][column] != ' ') {
      throw new IllegalStateException("Position already taken");
    }
  }

  private void nextPlayer() {
    currentPlayer = currentPlayer == Player.X ? Player.O : Player.X;
  }

  public boolean isFinished() {
    return finished;
  }

  public Optional<Character> getWinner() {
    return Optional.ofNullable(winner).map(Player::label);
  }

  public String getBoard() {
    final StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append(getRow(0)).append(System.lineSeparator());
    stringBuilder.append(" + + ").append(System.lineSeparator());
    stringBuilder.append(getRow(1)).append(System.lineSeparator());
    stringBuilder.append(" + + ").append(System.lineSeparator());
    stringBuilder.append(getRow(2)).append(System.lineSeparator());

    return stringBuilder.toString();
  }

  private String getRow(int index) {
    return String.format("%c|%c|%c", board[index][0], board[index][1], board[index][2]);
  }

  private boolean containsEmptyLocations() {
    return Arrays.stream(board)
        .flatMap(chars -> new String(chars)
            .chars()
            .mapToObj(i -> (char) i))
        .anyMatch(character -> character == ' ');
  }
}

