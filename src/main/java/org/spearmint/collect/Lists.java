/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.collect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * List Utility
 *
 * @author ryan131
 * @since May 4, 2011, 2:57:45 PM
 */
public final class Lists {
  private Lists() {}

  public static ImmutableList<Integer> integerList(int startInteger, int endInteger) {
    if (startInteger < 0 || endInteger < 0 || startInteger == endInteger) {
      String message =
          String.format("Illegal arguments startKey: %d, endKey: %d.%n", startInteger, endInteger);
      throw new IllegalArgumentException(message);
    }

    ArrayList<Integer> integerList = newArrayList();

    if (startInteger > endInteger) {
      int swap = startInteger;
      startInteger = endInteger;
      endInteger = swap;
    }
    for (int i = startInteger; i <= endInteger; i++) {
      integerList.add(i);
    }
    if (startInteger > endInteger) {
      java.util.Collections.reverse(integerList);
    }

    return immutableList(integerList);
  }

  /**
   * @see Maps.indexMap
   */
  public static <K> ImmutableList<K> keyList(Map<K, Integer> indexMap) {
    List<K> keyList = newArrayList(indexMap.size());

    for (int index = 0; index <= indexMap.size(); index++) {
      for (Map.Entry<K, Integer> entry : indexMap.entrySet()) {
        if (entry.getValue() == index) {
          keyList.add(entry.getKey());
        }
      }
    }

    return immutableList(keyList);
  }

  public static <V> ImmutableList<V> valueList(Map<Integer, V> valueMap) {
    List<V> valueList = newArrayList(valueMap.size());
    for (int index = 0; index < valueMap.size(); index++) {
      for (Map.Entry<Integer, V> entry : valueMap.entrySet()) {
        if (entry.getKey() == index) {
          valueList.add(entry.getValue());
        }
      }
    }

    return immutableList(valueList);
  }

  public static <E> ArrayList<E> shiftIndex(List<E> list, int offset) {
    if (offset < 1) {
      throw new IllegalArgumentException("Offset must be greater than 0.");
    }

    ArrayList<E> newList = newArrayList(list.size() + offset);
    for (int index = 0; index < list.size() + offset; index++) {
      if (index - offset < 0) {
        newList.add(null);
      } else {
        newList.add(list.get(index - offset));
      }
    }

    return newList;
  }

  public static <E> ImmutableList<E> immutableList(List<E> list) {
    return list instanceof ImmutableList ? (ImmutableList<E>) list : new ImmutableList<E>(list);
  }

  public static <E> ArrayList<E> newArrayList() {
    return new ArrayList<E>();
  }

  public static <E> ArrayList<E> newArrayList(int initialCapacity) {
    return new ArrayList<E>(initialCapacity);
  }

  @SuppressWarnings("unchecked")
  public static <E> ArrayList<E> newArrayList(E... array) {
    return new ArrayList<E>(Arrays.asList(array));
  }

  public static <E> ArrayList<E> newArrayList(Iterable<? extends E> iterable) {
    ArrayList<E> arrayList = newArrayList();
    for (E element : iterable) {
      arrayList.add(element);
    }
    return arrayList;
  }

  public static <E> LinkedList<E> newLinkedList() {
    return new LinkedList<E>();
  }

  @SuppressWarnings("unchecked")
  public static <E> LinkedList<E> newLinkedList(E... array) {
    return new LinkedList<E>(Arrays.asList(array));
  }

}
