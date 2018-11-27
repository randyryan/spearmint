/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.collect;

import java.util.Collection;

/**
 * Iterables
 *
 * @author ryan131
 * @since Feb 28, 2013, 9:43:57 PM
 */
public class Iterables {
  private Iterables() {}

  public static boolean isEmpty(Iterable<?> iterable) {
    if (iterable instanceof Collection) {
      return ((Collection<?>) iterable).isEmpty();
    }
    return !iterable.iterator().hasNext();
  }

}
