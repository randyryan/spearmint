/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.collect;

import java.util.Comparator;
import java.util.SortedMap;

/**
 * ImmutableSortedMap
 *
 * @author ryan131
 * @since Jan 7, 2012, 9:56:50 AM
 */
public class ImmutableSortedMap<K, V> extends ImmutableMap<K, V> implements SortedMap<K, V> {

  private final SortedMap<K, V> sortedMap;

  public ImmutableSortedMap(SortedMap<K, V> sortedMap) {
    super(sortedMap);
    if (sortedMap == null) {
      throw new NullPointerException();
    }
    this.sortedMap = sortedMap instanceof Immutable
        ? ((ImmutableSortedMap<K, V>) sortedMap).sortedMap
        : sortedMap;
  }

  @Override
  public Comparator<? super K> comparator() {
    return sortedMap.comparator();
  }

  @Override
  public ImmutableSortedMap<K, V> subMap(K fromKey, K toKey) {
    return new ImmutableSortedMap<K, V>(sortedMap.subMap(fromKey, toKey));
  }

  @Override
  public ImmutableSortedMap<K, V> headMap(K toKey) {
    return new ImmutableSortedMap<K, V>(sortedMap.headMap(toKey));
  }

  @Override
  public ImmutableSortedMap<K, V> tailMap(K fromKey) {
    return new ImmutableSortedMap<K, V>(sortedMap.tailMap(fromKey));
  }

  @Override
  public K firstKey() {
    return sortedMap.firstKey();
  }

  @Override
  public K lastKey() {
    return sortedMap.lastKey();
  }

}
