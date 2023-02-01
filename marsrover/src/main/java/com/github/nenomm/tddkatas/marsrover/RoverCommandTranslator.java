package com.github.nenomm.tddkatas.marsrover;

public class RoverCommandTranslator {

  private int x;
  private int y;
  private Orientation orientation;

  public RoverCommandTranslator(int x, int y, Orientation orientation) {
    this.x = x;
    this.y = y;
    this.orientation = orientation;
  }

  public void receive(char... commands) {
    for (char c : commands) {
      switch (c) {
        case 'f':
          goForward();
          break;
        case 'b':
          goBackward();
          break;
        case 'l':
          turnLeft();
          break;
        case 'r':
          turnRight();
          break;
      }

    }

  }

  private void turnRight() {
    orientation = Orientation.mapping[(orientation.getIndex() + 1) % 4];
  }

  private void turnLeft() {
    int index = orientation.getIndex();
    if (index == 0) {
      index = 3;
    } else {
      index--;
    }

    orientation = Orientation.mapping[index];
  }

  private void goBackward() {
    switch (orientation) {
      case N:
        y -= 1;
        break;
      case S:
        y += 1;
        break;
      case E:
        x += 1;
        break;
      case W:
        x -= 1;
        break;
    }
  }

  private void goForward() {
    switch (orientation) {
      case N:
        y += 1;
        break;
      case S:
        y -= 1;
        break;
      case E:
        x -= 1;
        break;
      case W:
        x += 1;
        break;
    }
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Orientation getOrientation() {
    return orientation;
  }
}
