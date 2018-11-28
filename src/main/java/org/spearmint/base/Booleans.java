/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.base;


/**
 * Boolean utility
 *
 * @author ryan131
 * @since Mar 10, 2012, 3:08:34 PM
 */
public final class Booleans {
  private Booleans() {}

  public static boolean fromString(String s) {
    if (s.equalsIgnoreCase("true") || s.equalsIgnoreCase("s") || s.equalsIgnoreCase("on")) {
      return Boolean.TRUE;
    }
    if (s.equalsIgnoreCase("false") || s.equalsIgnoreCase("f") || s.equalsIgnoreCase("off")) {
      return Boolean.FALSE;
    }
    return Boolean.parseBoolean(s);
  }

}
