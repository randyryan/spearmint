/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.collect;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * List Utility Tests
 *
 * @author ryan131
 * @since May 4, 2011, 3:30:43 PM
 */
public class ListsTest {

  @Test
  public void indexList() {
    List<Integer> integerList = Lists.integerList(1, 5);

    Assert.assertEquals(5, integerList.size());
    Assert.assertEquals(1, integerList.get(0).intValue());
    Assert.assertEquals(2, integerList.get(1).intValue());
    Assert.assertEquals(3, integerList.get(2).intValue());
    Assert.assertEquals(4, integerList.get(3).intValue());
    Assert.assertEquals(5, integerList.get(4).intValue());
  }

  @Test
  public void keyList() {
    java.util.Map<String, Integer> keyToIndex = Maps.newHashMap();
    keyToIndex.put("One", 0);
    keyToIndex.put("Three", 2);
    keyToIndex.put("Five", 4);
    keyToIndex.put("Two", 1);
    keyToIndex.put("Four", 3);
    List<String> keyList = Lists.keyList(keyToIndex);

    Assert.assertEquals("One", keyList.get(0));
    Assert.assertEquals("Two", keyList.get(1));
    Assert.assertEquals("Three", keyList.get(2));
    Assert.assertEquals("Four", keyList.get(3));
    Assert.assertEquals("Five", keyList.get(4));
    Assert.assertEquals(keyToIndex.size(), keyList.size());
  }

  @Test
  public void shiftIndex() {
    List<Integer> indexList = Lists.shiftIndex(Lists.integerList(1, 5), 2);

    Assert.assertEquals(7, indexList.size());
    Assert.assertEquals(null, indexList.get(0));
    Assert.assertEquals(null, indexList.get(1));
    Assert.assertEquals(1, indexList.get(2).intValue());
    Assert.assertEquals(2, indexList.get(3).intValue());
    Assert.assertEquals(3, indexList.get(4).intValue());
    Assert.assertEquals(4, indexList.get(5).intValue());
    Assert.assertEquals(5, indexList.get(6).intValue());
  }

  @Test(expected=UnsupportedOperationException.class)
  public void testImmutableList() {
    List<String> list = Lists.newArrayList();
    List<String> immutableList = Lists.immutableList(list);
    immutableList.add("");
  }

}
