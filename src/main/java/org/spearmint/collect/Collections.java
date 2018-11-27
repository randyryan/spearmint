/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.collect;

import java.util.Collection;
import java.util.Iterator;

/**
 * Collection Utility
 *
 * @author ryan131
 * @since May 4, 2011, 4:00:05 PM
 */
public final class Collections {
  private Collections() {}

  public static <T> boolean iterableEquals(Iterable<T> iterable1, Iterable<T> iterable2) {
    Iterator<T> iterator1 = iterable1.iterator();
    Iterator<T> iterator2 = iterable2.iterator();

    while (iterator1.hasNext() && iterator2.hasNext()) {
      if (!iterator1.next().equals(iterator2.next())) {
        return false;
      }
    }

    if (iterator1.hasNext() != iterator2.hasNext()) {
      return false;
    }

    return true;
  }

  public static <E> boolean refSizeEquals(Collection<E> collection1, Collection<E> collection2) {
    // Reference check

    if (collection1 == collection2) {
      return true;
    }

    // Null reference check

    if (collection1 == null) {
      if (collection2 == null) {
        return true;
      } else {
        return false;
      }
    }
    if (collection2 == null) {
      return false;
    }

    // Size check

    if (collection1.size() != collection2.size()) {
      return false;
    }

    // All checks passed

    return true;
  }
}
