package com.github.nenomm.tddkatas.wardrobe;

import static com.github.nenomm.tddkatas.wardrobe.Configurer.Size.M;
import static com.github.nenomm.tddkatas.wardrobe.Configurer.Size.S;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import org.junit.jupiter.api.Test;

public class ConfigurerPriceTest {

  private Configurer configurer;

  @Test
  public void spaceIsTooTight() {
    configurer = new Configurer(S, M);
    assertThat(configurer.getCheapestCombination(0), nullValue());
  }

  @Test
  public void oneIsCheaper() {
    configurer = new Configurer(S, M);
    assertThat(configurer.getCheapestCombination(3 * S.getLength()), is("MM"));
  }
}
