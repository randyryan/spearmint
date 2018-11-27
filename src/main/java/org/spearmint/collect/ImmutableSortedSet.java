/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.collect;

import java.util.Comparator;
import java.util.SortedSet;

/**
 * ImmutableSortedSet
 *
 * @author ryan131
 * @since Jan 7, 2012, 9:46:07 AM
 */
public class ImmutableSortedSet<E> extends ImmutableSet<E> implements SortedSet<E> {

  private final SortedSet<E> sortedSet;

  public ImmutableSortedSet(SortedSet<E> sortedSet) {
    super(sortedSet);
    if (sortedSet == null) {
      throw new NullPointerException();
    }
    this.sortedSet = sortedSet instanceof Immutable
        ? ((ImmutableSortedSet<E>) sortedSet).sortedSet
        : sortedSet;
  }

  @Override
  public Comparator<? super E> comparator() {
    return sortedSet.comparator();
  }

  @Override
  public final ImmutableSortedSet<E> subSet(E fromElement, E toElement) {
    return new ImmutableSortedSet<E>(sortedSet.subSet(fromElement, toElement));
  }

  @Override
  public final ImmutableSortedSet<E> headSet(E toElement) {
    return new ImmutableSortedSet<E>(sortedSet.headSet(toElement));
  }

  @Override
  public final ImmutableSortedSet<E> tailSet(E fromElement) {
    return new ImmutableSortedSet<E>(sortedSet.tailSet(fromElement));
  }

  @Override
  public E first() {
    return sortedSet.first();
  }

  @Override
  public E last() {
    return sortedSet.last();
  }

}
