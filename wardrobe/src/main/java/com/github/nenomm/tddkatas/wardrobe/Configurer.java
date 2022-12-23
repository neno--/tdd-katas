package com.github.nenomm.tddkatas.wardrobe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Configurer {

  public enum Size implements Comparable<Size> {
    S(50),
    M(75),
    L(100),
    X(120);
    private int length;

    Size(int length) {
      this.length = length;
    }

    public int getLength() {
      return length;
    }
  }

  private final Set<Size> availableSizes = new TreeSet<>();

  public Configurer(Size... sizes) {
    Collections.addAll(availableSizes, sizes);
  }

  public Set<String> getAllCombinationsForLength(int length) {
    final List<List<Size>> allCombinations = new ArrayList<>();

    visitNextCombination(length, allCombinations, new ArrayList<Size>());

    return ignoreDuplicates(allCombinations);
  }

  protected static Set<String> ignoreDuplicates(List<List<Size>> allCombinations) {
    return allCombinations.stream()
        .map(sizes -> {
          final List<Size> copyOfSized = new ArrayList(sizes);
          Collections.sort(copyOfSized);
          return copyOfSized.stream().reduce("", (string, size) -> String.format("%s%s", string, size), (s1, s2) -> s1 + s2);
        }).collect(Collectors.toSet());
  }

  public void visitNextCombination(int leftoverLength, List<List<Size>> allCombinations, List<Size> path) {
    if (leftoverLength < 0) {
      // abort
    } else if (leftoverLength > 0) {
      List<Size> candidates = getCandidates(leftoverLength);
      if (candidates.isEmpty()) {
        if (!path.isEmpty()) {
          allCombinations.add(path);
        }
        // else abort
      } else {
        for (Size size : getCandidates(leftoverLength)) {
          final List<Size> newCombination = new ArrayList<>(path);
          newCombination.add(size);
          visitNextCombination(leftoverLength - size.length, allCombinations, newCombination);
        }
      }

    } else if (!path.isEmpty()) {
      allCombinations.add(path);
    }
  }

  private List<Size> getCandidates(int length) {
    return availableSizes.stream()
        .filter(size -> size.length <= length)
        .collect(Collectors.toList());
  }
}
