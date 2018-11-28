/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.base;

import java.util.Arrays;
import java.util.List;

/**
 * Longs
 *
 * @author ryan131
 * @since Jan 6, 2012, 1:26:02 PM
 */
public final class Longs {
  private Longs() {}

  public static long[] toPrimitiveLongArray(Long[] objectLongArray) {
    long[] primitiveLongArray = new long[objectLongArray.length];
    for (int i = 0; i < objectLongArray.length; i++) {
      primitiveLongArray[i] = objectLongArray[i]; // unboxing
    }
    return primitiveLongArray;
  }

  public static long[] toPrimitiveLongArray(List<Long> objectLongList) {
    return toPrimitiveLongArray(toLongArray(objectLongList));
  }

  public static Long[] toLongArray(long[] primitiveLongArray) {
    Long[] objectLongArray = new Long[primitiveLongArray.length];
    for (int i = 0; i < primitiveLongArray.length; i++) {
      objectLongArray[i] = primitiveLongArray[i]; // autoboxing
    }
    return objectLongArray;
  }

  public static Long[] toLongArray(List<Long> objectLongList) {
    return objectLongList.toArray(new Long[objectLongList.size()]);
  }

  public static List<Long> toLongList(long[] longArray) {
    return toLongList(toLongArray(longArray));
  }

  public static List<Long> toLongList(Long[] longArray) {
    return Arrays.asList(longArray);
  }

  public static long average(long... numbers) {
    long total = 0;
    for (int i = 0; i < numbers.length; i++) {
      total += numbers[i];
    }
    return total / numbers.length;
  }

  /**
   * http://stackoverflow.com/questions/1306727
   * 
   * @param number
   * @return
   */
  public static int getDigits (long number) {
    // Guessing 4 digit numbers will be more probable.
    // They are set in the first branch.
    if (number < 10000L) { // from 1 to 4
      if (number < 100L) { // 1 or 2
        if (number < 10L) {
          return 1;
        } else {
          return 2;
        }
      } else { // 3 or 4
        if (number < 1000L) {
          return 3;
        } else {
          return 4;
        }
      }
    } else { // from 5 a 20 (albeit longs can't have more than 18 or 19)
      if (number < 1000000000000L) { // from 5 to 12
        if (number < 100000000L) { // from 5 to 8
          if (number < 1000000L) { // 5 or 6
            if (number < 100000L) {
              return 5;
            } else {
              return 6;
            }
          } else { // 7 u 8
            if (number < 10000000L) {
              return 7;
            } else {
              return 8;
            }
          }
        } else { // from 9 to 12
          if (number < 10000000000L) { // 9 or 10
            if (number < 1000000000L) {
              return 9;
            } else {
              return 10;
            }
          } else { // 11 or 12
            if (number < 100000000000L) {
              return 11;
            } else {
              return 12;
            }
          }
        }
      } else { // from 13 to ... (18 or 20)
        if (number < 10000000000000000L) { // from 13 to 16
          if (number < 100000000000000L) { // 13 or 14
            if (number < 10000000000000L) { 
              return 13;
            } else {
              return 14;
            }
          } else { // 15 or 16
            if (number < 1000000000000000L) {
              return 15;
            } else {
              return 16;
            }
          }
        } else { // from 17 to ...¿20?
          if (number < 1000000000000000000L) { // 17 or 18
            if (number < 100000000000000000L) {
              return 17;
            } else {
              return 18;
            }
          } else { // 19? Can it be?
            // 10000000000000000000L is'nt a valid long.
            return 19;
          }
        }
      }
    }
  }

}
