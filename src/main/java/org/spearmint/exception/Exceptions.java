/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.exception;


/**
 * Exceptions
 *
 * @author ryan131
 * @since Aug 29, 2012, 3:18:32 PM
 */
public class Exceptions {
  private Exceptions() {}

  public static void unsupportedOperation(String message) {
    throw new UnsupportedOperationException(message);
  }

}
