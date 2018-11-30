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

  public static RuntimeException wrapInRuntimeException(Throwable throwable) {
    if (throwable instanceof RuntimeException) {
      Throw.IllegalArgument(throwable.getClass().getName() + " is already a RuntimeException.");
    }
    return new RuntimeException(throwable);
  }

  public static RuntimeException wrapInRuntimeException(Throwable throwable, String message) {
    return new RuntimeException(message, throwable);
  }

  public static void propagateInRuntime(Exception checkedException) throws RuntimeException {
    throw wrapInRuntimeException(checkedException, "\n" + checkedException.getClass().getName() +
        " is thrown at " + checkedException.getStackTrace()[0]);
  }

  public static class Throw {

    public static void IllegalArgument() throws IllegalArgumentException {
      throw new IllegalArgumentException();
    }

    public static void IllegalArgument(String message) throws IllegalArgumentException {
      throw new IllegalArgumentException(message);
    }

    public static void IllegalState() throws IllegalStateException {
      throw new IllegalStateException();
    }

    public static void IllegalState(String message) throws IllegalStateException {
      throw new IllegalStateException(message);
    }

    public static void NullPointer() throws NullPointerException {
      throw new NullPointerException();
    }

    public static void NullPointer(String message) throws NullPointerException {
      throw new NullPointerException(message);
    }

    public static void UnsupportedOperation() throws UnsupportedOperationException {
      throw new UnsupportedOperationException();
    }

    public static void UnsupportedOperation(String message) throws UnsupportedOperationException {
      throw new UnsupportedOperationException(message);
    }

  }

}
