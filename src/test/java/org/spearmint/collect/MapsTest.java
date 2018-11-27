/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.collect;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.spearmint.collect.ImmutableMap.ImmutableMapEntry;

/**
 * Map utility Test
 *
 * @author ryan131
 * @since May 4, 2011, 4:29:35 PM
 */
public class MapsTest {

  @Test
  public void testNewHashMap() {
    Map<String, String> map1 = Maps.newHashMap();
    Map<String, String> map2 = Maps.newHashMap();

    Assert.assertEquals(map1, map2);
    Assert.assertFalse(map1 == map2);
 
    map1 = Maps.newHashMap();
    map1.put("1", "Name");
    map1.put("2", "Age");
    map1.put("3", "Gender");
    map2 = Maps.newHashMap(map1);

    Assert.assertEquals(map1, map2);

    map2.put("3", "Sex");

    Assert.assertNotEquals(map1, map2);
  }

  @Test
  public void testClone() {
    Map<String, String> map1 = Maps.newHashMap();
    Map<String, String> map2 = Maps.clone(map1);

    Assert.assertEquals(map1, map2);
    Assert.assertFalse(map1 == map2);
  }

  @Test
  public void testPutAll() {
    Map<String, String> map1 = Maps.newHashMap();
    map1.put("1", "Name");
    map1.put("2", "Age");
    map1.put("3", "Gender");
    Map<String, String> map2 = Maps.newHashMap();
    map2.put("4", "Height");
    map2.put("5", "Weight");
    Map<String, String> map3 = Maps.putAll(map1, map2);

    Assert.assertNotEquals(map1, map3);
  }

  @Test
  public void testClear() {
    Map<String, String> map1 = Maps.newHashMap();
    map1.put("1", "Name");
    map1.put("2", "Age");
    map1.put("3", "Gender");
    Map<String, String> map2 = Maps.clone(map1);
    Map<String, String> map3 = Maps.clear(map1);

    Assert.assertTrue(map1.isEmpty());
    Assert.assertFalse(map2.isEmpty());
    Assert.assertFalse(map3.isEmpty());
    Assert.assertEquals(map2, map3);
  }

  private void printArray(Object[] array) {
    for (int index = 0; index < array.length; index++) {
      @SuppressWarnings({ "unchecked", "rawtypes" })
      ImmutableMapEntry<Integer, Boolean> entry = (ImmutableMapEntry) array[index];
      try {
        System.out.println(entry.getKey() + " -> " + entry.getValue());
      } catch (NullPointerException npe) {
        if (array[index] == null) {
          System.out.println(index + 1 + " -> null");
        }
      }
    }
  }

  @Test
  public void testImmutableMapToArray1() {
    System.out.println("When collection.size() = array.length:\n");

    Map<Integer, String> map1 = Maps.newHashMap();
    map1.put(1, "1");
    map1.put(2, "2");
    map1.put(3, "3");
    Map<Integer, String> immutableMap1 = Maps.immutableMap(map1);

    Map<Integer, String> mapForArray1 = Maps.newHashMap();
    mapForArray1.put(1, "One");
    mapForArray1.put(2, "Two");
    mapForArray1.put(3, "Three");
    Object[] arrayOriginal1 = Maps.immutableMap(mapForArray1).entrySet().toArray();

    System.out.println("collection:");
    for (Map.Entry<Integer, String> entry : map1.entrySet()) {
      System.out.println(entry.getKey() + " -> " + entry.getValue());
    }

    System.out.println("array (before):");
    printArray(arrayOriginal1);

    Object[] arrayReturn1 = immutableMap1.entrySet().toArray(arrayOriginal1);

    System.out.println("array (after):");
    printArray(arrayOriginal1);

    System.out.println("array (return):");
    printArray(arrayReturn1);

    System.out.println("\nWhen collection.size() < array.length:\n");

    Map<Integer, String> map2 = Maps.newHashMap();
    map2.put(1, "1");
    map2.put(2, "2");
    map2.put(3, "3");
    Map<Integer, String> immutableMap2 = Maps.immutableMap(map1);

    Map<Integer, String> mapForArray2 = Maps.newHashMap();
    mapForArray2.put(1, "One");
    mapForArray2.put(2, "Two");
    mapForArray2.put(3, "Three");
    mapForArray2.put(4, "Four");
    mapForArray2.put(5, "Five");
    Object[] arrayOriginal2 = Maps.immutableMap(mapForArray2).entrySet().toArray();

    System.out.println("collection:");
    for (Map.Entry<Integer, String> entry : map2.entrySet()) {
      System.out.println(entry.getKey() + " -> " + entry.getValue());
    }

    System.out.println("array (before):");
    printArray(arrayOriginal2);

    Object[] arrayReturn2 = immutableMap2.entrySet().toArray(arrayOriginal2);

    System.out.println("array (after):");
    printArray(arrayOriginal2);

    System.out.println("array (return):");
    printArray(arrayReturn2);

    System.out.println("\nWhen collection.size() > array.length:\n");

    Map<Integer, String> map3 = Maps.newHashMap();
    map3.put(1, "1");
    map3.put(2, "2");
    map3.put(3, "3");
    Map<Integer, String> immutableMap3 = Maps.immutableMap(map3);

    Map<Integer, String> mapForArray3 = Maps.newHashMap();
    mapForArray3.put(1, "One");
    mapForArray3.put(2, "Two");
    Object[] arrayOriginal3 = Maps.immutableMap(mapForArray3).entrySet().toArray();

    System.out.println("collection:");
    for (Map.Entry<Integer, String> entry : map3.entrySet()) {
      System.out.println(entry.getKey() + " -> " + entry.getValue());
    }

    System.out.println("array (before):");
    printArray(arrayOriginal3);

    Object[] arrayReturn3 = immutableMap3.entrySet().toArray(arrayOriginal3);

    System.out.println("array (after):");
    printArray(arrayOriginal3);

    System.out.println("array (return):");
    printArray(arrayReturn3);
  }

  @Test
  public void indexMap() {
    Assert.assertEquals(7, Maps.indexMap(Lists.integerList(1, 7)).size());
  }

  @Test
  public void testImmutableMapEntrySetIterator() {
    System.out.println("\nImmutableMapEntrySet.iterator() test:\n");

    Map<Integer, Boolean> map = Maps.newHashMap();
    map.put(1, true);
    map.put(2, false);
    map.put(3, true);

    for (Map.Entry<Integer, Boolean> entry : Maps.immutableMap(map).entrySet()) {
      System.out.println(entry.getKey() + " -> " + entry.getValue());
    }
  }

}
