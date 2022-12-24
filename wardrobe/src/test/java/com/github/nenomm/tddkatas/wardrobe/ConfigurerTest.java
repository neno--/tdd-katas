package com.github.nenomm.tddkatas.wardrobe;

import static com.github.nenomm.tddkatas.wardrobe.Configurer.Size.L;
import static com.github.nenomm.tddkatas.wardrobe.Configurer.Size.M;
import static com.github.nenomm.tddkatas.wardrobe.Configurer.Size.S;
import static com.github.nenomm.tddkatas.wardrobe.Configurer.Size.X;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;

import com.github.nenomm.tddkatas.wardrobe.Configurer.Size;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ConfigurerTest {

  private Configurer configurer;

  @Test
  public void spaceIsTooTight() {
    configurer = new Configurer(S, M);
    assertThat(configurer.getAllCombinationsForLength(0), empty());
  }

  // I am testing implementation here - is this a smell?
  @Test
  public void returnUniqueCombinations() {
    List<List<Size>> allCombinations = List.of(List.of(S, M), List.of(M, S), List.of(M));
    Set<String> result = Configurer.ignoreDuplicates(allCombinations);
    assertThat(result, hasSize(2));
    assertThat(result, containsInAnyOrder("M", "SM"));
  }

  // not available after refactor to use exact match and price
  @Disabled
  @Test
  public void fitOnlyOne() {
    configurer = new Configurer(S, M);
    assertThat(configurer.getAllCombinationsForLength(M.getLength()), containsInAnyOrder("S", "M"));
  }

  // not available after refactor to use exact match and price
  @Disabled
  @Test
  public void fitSeveral() {
    configurer = new Configurer(S, M, L, X);
    assertThat(configurer.getAllCombinationsForLength(125), containsInAnyOrder("SS", "SM", "L", "XL"));
  }
}
