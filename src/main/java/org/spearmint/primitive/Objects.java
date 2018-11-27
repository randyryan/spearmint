/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.primitive;


/**
 * Object utility
 *
 * @author ryan131
 * @since Aug 17, 2012, 1:55:16 PM
 */
public final class Objects {
  private Objects() {}

  @Deprecated
  public static boolean equals(Object a, Object b) {
    // return a == null ? b == null : a.equals(b);
    return java.util.Objects.equals(a, b);
  }

}
