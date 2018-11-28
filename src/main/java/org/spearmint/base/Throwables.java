/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.base;

/**
 * Throwables
 *
 * @author ryan131
 * @since Aug 29, 2012, 3:18:32 PM
 */
public final class Throwables {
  private Throwables() {}

  public static void illegalArgument() throws IllegalArgumentException {
    throw new IllegalArgumentException();
  }

  public static void illegalArgument(String message) throws IllegalArgumentException {
    throw new IllegalArgumentException(message);
  }

  public static void illegalState() throws IllegalStateException {
    throw new IllegalStateException();
  }

  public static void illegalState(String message) throws IllegalStateException {
    throw new IllegalStateException(message);
  }

  public static void nullPointer() throws NullPointerException {
    throw new NullPointerException();
  }

  public static void nullPointer(String message) throws NullPointerException {
    throw new NullPointerException(message);
  }

  public static void unsupportedOperation() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  public static void unsupportedOperation(String message) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(message);
  }

}
