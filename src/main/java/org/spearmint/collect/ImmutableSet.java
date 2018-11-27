/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.collect;

import java.util.Set;

/**
 * ImmutableSet
 *
 * @author ryan131
 * @since Jan 6, 2012, 10:19:58 PM
 */
public class ImmutableSet<E> extends ImmutableCollection<E> implements Set<E> {

  public ImmutableSet(Set<E> set) {
    super(set);
  }

}
