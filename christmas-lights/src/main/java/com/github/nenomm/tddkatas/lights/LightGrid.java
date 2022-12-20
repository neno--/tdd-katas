package com.github.nenomm.tddkatas.lights;

public class LightGrid {

  public enum Operation {
    ON,
    OFF,
    TOGGLE
  }

  private static final int ROWS = 1000;
  private static final int COLUMNS = 1000;

  private final boolean[][] grid = new boolean[ROWS][COLUMNS];

  public int onLights() {
    int turnedOn = 0;

    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLUMNS; j++) {
        if (grid[i][j]) {
          turnedOn++;
        }
      }
    }

    return turnedOn;
  }

  public int offLights() {
    return numOfLights() - this.onLights();
  }

  public int numOfLights() {
    return ROWS * COLUMNS;
  }

  public void turnOn(int firstColumn, int firstRow, int lastColumn, int lastRow) {
    processCoordinate(new int[]{firstColumn, firstRow}, new int[]{lastColumn, lastRow}, Operation.ON);
  }

  public void turnOff(int firstColumn, int firstRow, int lastColumn, int lastRow) {
    processCoordinate(new int[]{firstColumn, firstRow}, new int[]{lastColumn, lastRow}, Operation.OFF);
  }

  public void toggle(int firstColumn, int firstRow, int lastColumn, int lastRow) {
    processCoordinate(new int[]{firstColumn, firstRow}, new int[]{lastColumn, lastRow}, Operation.TOGGLE);
  }

  private void processCoordinate(int[] firstCoordinate, int[] lastCoordinate, Operation operation) {

    for (int i = firstCoordinate[1]; i <= lastCoordinate[1]; i++) {
      for (int j = firstCoordinate[0]; j <= lastCoordinate[0]; j++) {
        switch (operation) {
          case ON:
            this.grid[i][j] = true;
            break;
          case OFF:
            this.grid[i][j] = false;
            break;
          case TOGGLE:
            this.grid[i][j] = !this.grid[i][j];
            break;
        }
      }
    }
  }
}