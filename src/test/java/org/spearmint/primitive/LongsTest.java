/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.primitive;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * LongsTest
 *
 * @author ryan131
 * @since Jan 6, 2012, 2:37:56 PM
 */
public class LongsTest extends TestCase {

  public LongsTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(IntsTest.class);
  }

  public void testGetDigits() {
    long sevenDigits = 1234567L;
    Assert.assertEquals(7, Longs.getDigits(sevenDigits));
  }

  public void testConvertLong() {
    long[] primitiveLongArray = new long[] {1L, 2L, 3L, 4L, 5L, 6L, 7L};
    Long[] longArray = new Long[7];
    for (int i = 0; i < longArray.length; i++) {
      longArray[i] = new Long(i + 1);
    }
    List<Long> longList = new ArrayList<Long>();
    for (int i = 1; i <= 7; i++) {
      longList.add(new Long(i));
    }

    // Convert to int[]

    Assert.assertArrayEquals(primitiveLongArray, Longs.toPrimitiveLongArray(longArray));
    Assert.assertArrayEquals(primitiveLongArray, Longs.toPrimitiveLongArray(longList));

    // Convert to Integer[]

    Assert.assertArrayEquals(longArray, Longs.toLongArray(primitiveLongArray));
    Assert.assertArrayEquals(longArray, Longs.toLongArray(longList));

    // Convert to List<Integer>

    List<Long> primitiveLongArrayToList = Longs.toLongList(primitiveLongArray);
    List<Long> longArrayToList = Longs.toLongList(longArray);

    Assert.assertEquals(primitiveLongArrayToList, longArrayToList);
  }

	}
