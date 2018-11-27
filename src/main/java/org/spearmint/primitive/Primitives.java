/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.primitive;

/**
 * Primitive Utility
 *
 * @author ryan131
 * @since May 18, 2011, 9:29:56 PM
 */
public final class Primitives {
  public Primitives() {}

  @Deprecated
  public static boolean getBoolean(String string) {
    return Boolean.parseBoolean(string);
  }

}
