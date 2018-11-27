/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.collect;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Set Utility
 *
 * @author ryan131
 * @since May 4, 2011, 3:56:30 PM
 */
public final class Sets {
  private Sets() {}

  public static <E> ImmutableSet<E> immutableSet(Set<E> set) {
    return set instanceof ImmutableSet ? (ImmutableSet<E>) set : new ImmutableSet<E>(set);
  }

  public static <E> ImmutableSortedSet<E> immutableSortedSet(SortedSet<E> sortedSet) {
    return sortedSet instanceof ImmutableSortedSet
        ? (ImmutableSortedSet<E>) sortedSet
        : new ImmutableSortedSet<E>(sortedSet);
  }

  public static <E> HashSet<E> newHashSet() {
    return new HashSet<E>();
  }

  public static <E> TreeSet<E> newTreeSet() {
    return new TreeSet<E>();
  }

  public static <E> LinkedHashSet<E> newLinkedHashSet() {
    return new LinkedHashSet<E>();
  }

}
