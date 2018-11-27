/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.collect;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.spearmint.collect.ImmutableMap;

/**
 * Map Utility
 *
 * @author ryan131
 * @since May 4, 2011, 2:49:08 PM
 */
public final class Maps {
  private Maps() {}

  public static <K> ImmutableMap<K, Integer> indexMap(Iterable<K> keys) {
    Map<K, Integer> indexMap = newHashMap();
    int index = 0;
    for (K key : keys) {
      indexMap.put(key, index++);
    }
    return immutableMap(indexMap);
  }

  public static <K> ImmutableMap<Integer, K> indexToKeyMap(Iterable<K> keys) {
    Map<Integer, K> indexToKeyMap = newHashMap();
    int index = 0;
    for (K key : keys) {
      indexToKeyMap.put(index++, key);
    }
    return immutableMap(indexToKeyMap);
  }

  public static <K, V> Map<K, V> putAll(Map<K, V> map1, Map<K, V> map2) {
    Map<K, V> oldMap = clone(map1);
    map1.putAll(map2);
    return oldMap;
  }

  public static <K, V> Map<K, V> clear(Map<K, V> map) {
    Map<K, V> oldMap = clone(map);
    map.clear();
    return oldMap;
  }

  @SuppressWarnings("unchecked") // safe
  public static <K, V> Map<K, V> clone(Map<K, V> map) {
    Map<K, V> clonedMap = null;
    try {
      clonedMap = map.getClass().newInstance();
      clonedMap.putAll(map);
    } catch (InstantiationException ie) {
    } catch (IllegalAccessException iae) {
    }
    return clonedMap;
  }

  public static <K, V> ImmutableMap<K, V> immutableMap(Map<K, V> map) {
    return map instanceof ImmutableMap ? (ImmutableMap<K, V>) map : new ImmutableMap<K, V>(map);
  }

  public static <K, V> ImmutableSortedMap<K, V> immutableSortedMap(SortedMap<K, V> sortedMap) {
    return sortedMap instanceof ImmutableSortedMap
        ? (ImmutableSortedMap<K, V>) sortedMap
        : new ImmutableSortedMap<K, V>(sortedMap);
  }

  public static ImmutableMap<String, String> fromProperties(Properties properties) {
    Map<String, String> map = Maps.newHashMap();

    Enumeration<?> enumeration = properties.keys();
    while (enumeration.hasMoreElements()) {
      String key = (String) enumeration.nextElement();
      String value = properties.getProperty(key);
      map.put(key, value);
    }

    return immutableMap(map);
  }

  public static <K, V> HashMap<K, V> newHashMap() {
    return new HashMap<K, V>();
  }

  public static <K, V> HashMap<K, V> newHashMap(Map<? extends K, ? extends V> map) {
    return new HashMap<K, V>(map);
  }

  public static <K, V> LinkedHashMap<K, V> newLinkedHashMap() {
    return new LinkedHashMap<K, V>();
  }

  public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(Map<? extends K, ? extends V> map) {
    return new LinkedHashMap<K, V>(map);
  }

  public static <K, V> TreeMap<K, V> newTreeMap() {
    return new TreeMap<K, V>();
  }

  public static <K, V> TreeMap<K, V> newTreeMap(SortedMap<K, ? extends V> map) {
    return new TreeMap<K, V>(map);
  }

  public static <K, V> ConcurrentMap<K, V> newConcurrentHashMap() {
    return new ConcurrentHashMap<K, V>();
  }

}
