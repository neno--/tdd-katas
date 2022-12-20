package com.github.nenomm.tddkatas.lights2;

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
  public void toggleIncreasesBy2() {
    grid.toggle(0, 0, 999, 999);
    assertEquals(0, grid.offLights());
    assertEquals(grid.numOfLights(), grid.onLights());
    assertEquals(2 * grid.numOfLights(), grid.getBrightness());
  }
}