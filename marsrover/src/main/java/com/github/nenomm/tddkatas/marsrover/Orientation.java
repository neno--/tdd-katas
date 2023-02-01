package com.github.nenomm.tddkatas.marsrover;

public enum Orientation {
  N(0), S(2), E(3), W(1);
  private int index;

  Orientation(int index) {
    this.index = index;
  }

  public int getIndex() {
    return index;
  }

  public static Orientation[] mapping = {N, W, S, E};
}
