/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.collect;

import java.util.List;

import org.junit.Test;

/**
 * CombinationsTest
 *
 * @author ryan131
 * @since Feb 28, 2013, 9:30:59 PM
 */
@SuppressWarnings({"unused", "unchecked"})
public class CombinationsTest {

  @Test
  public void test() {
    List<String> group0 = Lists.newArrayList();
    List<String> group1 = Lists.newArrayList("A", "B", "C");
    List<String> group2 = Lists.newArrayList("1", "2", "3");
    List<String> group3 = Lists.newArrayList("+", "-");

    List<List<String>> group0And1 = Combinations.getCombinations(group1, group2, group3);
    for (List<String> combination : group0And1) {
      System.out.println(combination.toString());
    }
  }

}
