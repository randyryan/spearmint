/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.base;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.spearmint.base.Ints;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Int Utility Test
 *
 * @author ryan131
 * @since Apr 14, 2011, 5:47:02 PM
 */
public class IntsTest extends TestCase {

  public IntsTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(IntsTest.class);
  }

  public void testGetDigits() {
    int sevenDigits = 1234567;

    Assert.assertEquals(7, Ints.getIntegerDigits1(sevenDigits));
    Assert.assertEquals(7, Ints.getIntegerDigits2(sevenDigits));
    Assert.assertEquals(7, Ints.getIntegerDigits3(sevenDigits));
  }

  public void testConvertInteger() {
    int[] intArray = new int[] {1, 2, 3, 4, 5, 6, 7};
    Integer[] integerArray = new Integer[7];
    for (int i = 0; i < integerArray.length; i++) {
      integerArray[i] = new Integer(i + 1);
    }
    List<Integer> integerList = new ArrayList<Integer>();
    for (int i = 1; i <= 7; i++) {
      integerList.add(new Integer(i));
    }

    // Convert to int[]

    Assert.assertArrayEquals(intArray, Ints.toIntArray(integerArray));
    Assert.assertArrayEquals(intArray, Ints.toIntArray(integerList));

    // Convert to Integer[]

    Assert.assertArrayEquals(integerArray, Ints.toIntegerArray(intArray));
    Assert.assertArrayEquals(integerArray, Ints.toIntegerArray(integerList));

    // Convert to List<Integer>

    List<Integer> intArrayToList = Ints.toIntegerList(intArray);
    List<Integer> integerArrayToList = Ints.toIntegerList(integerArray);

    Assert.assertEquals(intArrayToList, integerArrayToList);
  }

}
