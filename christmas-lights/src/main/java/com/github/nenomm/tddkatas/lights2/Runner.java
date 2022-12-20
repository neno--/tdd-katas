package com.github.nenomm.tddkatas.lights2;

public class Runner {

  public static void main(String[] args) {
    final LightGrid grid = new LightGrid();

    grid.turnOn(887, 9, 959, 629);
    grid.turnOn(454, 398, 844, 448);
    grid.turnOff(539, 243, 559, 965);
    grid.turnOff(370, 819, 676, 868);
    grid.turnOff(145, 40, 370, 997);
    grid.turnOff(301, 3, 808, 453);
    grid.turnOn(351, 678, 951, 908);
    grid.toggle(720, 196, 897, 994);
    grid.toggle(831, 394, 904, 860);

    System.out.println(grid.onLights()); // 280830
  }

}
