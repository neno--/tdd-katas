package com.github.nenomm.tddkatas.wardrobe;

import static com.github.nenomm.tddkatas.wardrobe.Configurer.Size.M;
import static com.github.nenomm.tddkatas.wardrobe.Configurer.Size.S;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;

import org.junit.jupiter.api.Test;

public class ConfigurerTest {

  private Configurer configurer;

  @Test
  public void spaceIsTooTight() {
    configurer = new Configurer(S, M);
    assertThat(configurer.getAllCombinationsForLength(0), empty());
  }
}
