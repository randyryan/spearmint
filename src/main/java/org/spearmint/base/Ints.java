/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.base;

import java.util.Arrays;
import java.util.List;

/**
 * Int Utility
 *
 * @author ryan131
 * @since Apr 14, 2011, 5:25:47 PM
 */
public final class Ints {
  private Ints() {}

  public static int[] toIntArray(Integer[] integerArray) {
    int[] intArray = new int[integerArray.length];
    for (int i = 0; i < integerArray.length; i++) {
      intArray[i] = integerArray[i]; // unboxing
    }
    return intArray;
  }

  public static int[] toIntArray(List<Integer> integerList) {
    return toIntArray(toIntegerArray(integerList));
  }

  public static Integer[] toIntegerArray(int[] intArray) {
    Integer[] integerArray = new Integer[intArray.length];
    for (int i = 0; i < intArray.length; i++) {
      integerArray[i] = intArray[i]; // autoboxing
    }
    return integerArray;
  }

  public static Integer[] toIntegerArray(List<Integer> integerList) {
    return integerList.toArray(new Integer[integerList.size()]);
  }

  public static List<Integer> toIntegerList(int[] intArray) {
    return toIntegerList(toIntegerArray(intArray));
  }

  public static List<Integer> toIntegerList(Integer[] integerArray) {
    return Arrays.asList(integerArray);
  }

  public static int average(int... numbers) {
    int total = 0;
    for (int i = 0; i < numbers.length; i++) {
      total += numbers[i];
    }
    return total / numbers.length;
  }

  public static int getIntegerDigits1(int number) {
    return String.valueOf(number).length();
  }

  public static int getIntegerDigits2(int number) {
    return (int) Math.log10(number) + 1;
  }

  /**
   * http://stackoverflow.com/questions/1306727
   * 
   * @param number
   * @return
   */
  public static int getIntegerDigits3(int number) {
    if (number < 100000) {
      // 5 or less
      if (number < 100) {
        // 1 or 2
        if (number < 10) {
          return 1;
        } else {
          return 2;
        }
      } else {
        // 3 or 4 or 5
        if (number < 1000) {
          return 3;
        } else {
          // 4 or 5
          if (number < 10000) {
            return 4;
          } else {
            return 5;
          }
        }
      }
    } else {
      // 6 or more
      if (number < 10000000) {
        // 6 or 7
        if (number < 1000000) {
          return 6;
        } else {
          return 7;
        }
      } else {
        // 8 to 10
        if (number < 100000000) {
          return 8;
        } else {
          // 9 or 10
          if (number < 1000000000) {
            return 9;
          } else {
            return 10;
          }
        }
      }
    }
  }



}
