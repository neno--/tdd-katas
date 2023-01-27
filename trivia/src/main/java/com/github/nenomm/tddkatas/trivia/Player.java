package com.github.nenomm.tddkatas.trivia;

public class Player {

  private final String name;
  private int points;
  private boolean penalty;

  private int position;

  public Player(String name) {
    this.name = name;
    this.points = 0;
    this.penalty = false;
    this.position = 0;
  }

  public String getName() {
    return name;
  }

  public boolean hasPenalty() {
    return penalty;
  }

  public void addPoints(int points) {
    this.points += points;
  }

  public void goForward(int position) {
    this.position += position;

    if (this.position > 11) {
      this.position -= 12;
    }
  }

  public int getPosition() {
    return position;
  }

  public int getPoints() {
    return points;
  }

  public void setPenalty() {
    this.penalty = true;
  }
}
