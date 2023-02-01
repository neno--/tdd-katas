package com.github.nenomm.tddkatas.marsrover;

public class RoverCommandTranslator {

  private final static int WORLD_HEIGHT = 10;
  private final static int WORLD_WIDTH = 10;
  private int x;
  private int y;
  private Orientation orientation;

  private final boolean[][] obstacles = new boolean[WORLD_HEIGHT][WORLD_WIDTH];

  public RoverCommandTranslator(int x, int y, Orientation orientation) {
    this.x = x;
    this.y = y;
    this.orientation = orientation;
    initObstacles();
  }

  private void initObstacles() {
    obstacles[3][4] = true;
    obstacles[3][5] = true;
    obstacles[3][6] = true;
  }

  public boolean receive(char... commands) {
    for (char c : commands) {
      final boolean obstacle = execute(c);
      if (obstacle) {
        return false;
      }
    }
    return true;
  }

  private boolean execute(char c) {
    switch (c) {
      case 'f':
        return updatePosition(calculateNextForwardStep(x, y));
      case 'b':
        return updatePosition(calculateNextBackwardStep(x, y));
      case 'l':
        turnLeft();
        return false;
      case 'r':
        turnRight();
        return false;
    }

    throw new IllegalStateException();
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

  private boolean updatePosition(int[] nextPosition) {
    if (checkObstacle(nextPosition[0], nextPosition[1])) {
      return false;
    } else {
      x = nextPosition[0];
      y = nextPosition[1];

      return true;
    }
  }

  private int[] calculateNextForwardStep(int x, int y) {
    switch (orientation) {
      case N:
        return new int[]{x, incY(y)};
      case S:
        return new int[]{x, decY(y)};
      case E:
        return new int[]{decX(x), y};
      case W:
        return new int[]{incX(x), y};
    }

    throw new IllegalStateException();
  }

  private int[] calculateNextBackwardStep(int x, int y) {
    switch (orientation) {
      case N:
        return new int[]{x, decY(y)};
      case S:
        return new int[]{x, incY(y)};
      case E:
        return new int[]{incX(x), y};
      case W:
        return new int[]{decX(x), y};
    }

    throw new IllegalStateException();
  }

  private boolean checkObstacle(int x, int y) {
    return obstacles[obstacleRowIndex(x)][obstacleColumnIndex(y)];
  }

  private static int obstacleRowIndex(int i) {
    return WORLD_HEIGHT / 2 + i;
  }

  private static int obstacleColumnIndex(int i) {
    return WORLD_WIDTH / 2 + i;
  }

  private static int incX(int currentX) {
    int x = currentX + 1;
    if (x > WORLD_WIDTH / 2) {
      x = -WORLD_WIDTH / 2;
    }
    return x;
  }

  private static int decX(int currentX) {
    int x = currentX - 1;
    if (x < -WORLD_WIDTH / 2) {
      x = WORLD_WIDTH / 2;
    }
    return x;
  }

  private static int incY(int currentY) {
    int y = currentY + 1;
    if (y > WORLD_HEIGHT / 2) {
      y = -WORLD_HEIGHT / 2;
    }
    return y;
  }

  private static int decY(int currentY) {
    int y = currentY - 1;
    if (y < -WORLD_HEIGHT / 2) {
      y = WORLD_HEIGHT / 2;
    }
    return y;
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
