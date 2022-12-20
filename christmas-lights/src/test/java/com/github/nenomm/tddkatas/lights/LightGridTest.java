package com.github.nenomm.tddkatas.lights;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LightGridTest {

  private LightGrid grid;

  @BeforeEach
  public void setup() {
    grid = new LightGrid();
  }


  @Test
  public void initiallyLightsAreOff() {
    assertEquals(grid.numOfLights(), grid.offLights());
    assertEquals(0, grid.onLights());
  }

  @Test
  public void turnOnAllTheLights() {
    grid.turnOn(0, 0, 999, 999);
    assertEquals(0, grid.offLights());
    assertEquals(grid.numOfLights(), grid.onLights());
  }

  @Test
  public void toggleFirstRow() {
    grid.turnOn(0, 0, 999, 999);
    grid.toggle(0, 0, 999, 0);
    assertEquals(1000, grid.offLights());
    assertEquals(grid.numOfLights() - 1000, grid.onLights());
  }

  @Test
  public void turnOffFourMiddleLights() {
    grid.turnOn(0, 0, 999, 999);
    grid.turnOff(499, 499, 500, 500);
    assertEquals(4, grid.offLights());
    assertEquals(grid.numOfLights() - 4, grid.onLights());
  }
}