package com.github.nenomm.tddkatas.wardrobe;

import static com.github.nenomm.tddkatas.wardrobe.Configurer.Size.L;
import static com.github.nenomm.tddkatas.wardrobe.Configurer.Size.M;
import static com.github.nenomm.tddkatas.wardrobe.Configurer.Size.S;
import static com.github.nenomm.tddkatas.wardrobe.Configurer.Size.X;


public class Runner {

  public static void main(String[] args) {
    final Configurer configurer = new Configurer(S, M, L, X);
    System.out.println(configurer.getAllCombinationsForLength(250));
    System.out.println(configurer.getCheapestCombination(250));
  }

}
