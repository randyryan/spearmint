/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.collect;

import java.util.List;

/**
 * Combinations
 *
 * @author ryan131
 * @since Feb 28, 2013, 9:30:32 PM
 */
public class Combinations {
  private Combinations() {}

  @SuppressWarnings("unchecked")
  public static <E> List<List<E>> getCombinations(Iterable<E> group1, Iterable<E> group2) {
    List<List<E>> combinations = Lists.newArrayList();

    for (E group1Element : group1) {
      for (E group2Element : group2) {
        List<E> combination = Lists.newArrayList();
        combination.add(group1Element);
        combination.add(group2Element);
        combinations.add(combination);
      }
    }

    // if one of the group is empty, then return the other group as-is

    if (combinations.isEmpty()) {
      Iterable<E> groupToReturn =
          Iterables.isEmpty(group1) ? Iterables.isEmpty(group2) ? group2 : group2 : group1;
      for (E groupElement : groupToReturn) {
        combinations.add(Lists.newArrayList(groupElement));
      }
    }

    return combinations;
  }

  private static <E> List<List<E>> getCombinations(List<List<E>> combinations, List<E> group2) {
    List<List<E>> combinationsToReturn = Lists.newArrayList();
    for (List<E> combination : combinations) {
      for (E group2Element : group2) {
        List<E> combinationToAdd = Lists.newArrayList();
        combinationToAdd.addAll(combination);
        combinationToAdd.add(group2Element);
        combinationsToReturn.add(combinationToAdd);
      }
    }
    if (combinationsToReturn.isEmpty()) {
      return combinations;
    }
    return combinationsToReturn;
  }

  @SuppressWarnings("unchecked")
  public static <E> List<List<E>> getCombinations(List<E>... groups) {
    List<E> emptyGroup = Lists.newArrayList();
    List<List<E>> combinations = getCombinations(emptyGroup, groups[0]);

    // The first combination is the first group itself, for loop should starts at the 2nd group.
    for (int groupIndex = 1; groupIndex < groups.length; groupIndex++) {
      combinations = getCombinations(combinations, groups[groupIndex]);
    }

    return combinations;
  }

}
