package com.github.nenomm.tddkatas.marsrover;

public class RoverCommandTranslator {

  private final int WORLD_HEIGHT = 10;
  private final int WORLD_WIDTH = 10;
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
        decY();
        break;
      case S:
        incY();
        break;
      case E:
        incX();
        break;
      case W:
        decX();
        break;
    }
  }

  private void goForward() {
    switch (orientation) {
      case N:
        incY();
        break;
      case S:
        decY();
        break;
      case E:
        decX();
        break;
      case W:
        incX();
        break;
    }
  }

  private void incX() {
    x++;
    if (x > WORLD_WIDTH / 2) {
      x = -WORLD_WIDTH / 2;
    }
  }

  private void decX() {
    x--;
    if (x < -WORLD_WIDTH / 2) {
      x = WORLD_WIDTH / 2;
    }
  }

  private void incY() {
    y++;
    if (y > WORLD_HEIGHT / 2) {
      y = -WORLD_HEIGHT / 2;
    }
  }

  private void decY() {
    y--;
    if (y < -WORLD_HEIGHT / 2) {
      y = WORLD_HEIGHT / 2;
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
