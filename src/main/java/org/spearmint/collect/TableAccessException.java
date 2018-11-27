/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.collect;

/**
 * TableAccessorException
 *
 * @author ryan131
 * @since Jan 11, 2014, 3:51:10 PM
 */
public class TableAccessException extends RuntimeException {

  private static final long serialVersionUID = -128882881663391993L;

  public TableAccessException() {
    super();
  }

  public TableAccessException(String message) {
    super(message);
  }

  public TableAccessException(String message, Throwable cause) {
    super(message, cause);
  }

  public TableAccessException(Throwable cause) {
    super(cause);
  }

  public static class KeysNotSet extends TableAccessException {

    private static final long serialVersionUID = -4962558752514424844L;

    public KeysNotSet() {
      super();
    }

    public KeysNotSet(String message) {
      super(message);
    }

    public KeysNotSet(String message, Throwable cause) {
      super(message, cause);
    }

    public KeysNotSet(Throwable cause) {
      super(cause);
    }

  }

  public static class NoSuchKey extends TableAccessException {

    private static final long serialVersionUID = 3976188807879108186L;

    public NoSuchKey() {
      super();
    }

    public NoSuchKey(String message) {
      super(message);
    }

    public NoSuchKey(String message, Throwable cause) {
      super(message, cause);
    }

    public NoSuchKey(Throwable cause) {
      super(cause);
    }

  }

  public static class NoSuchIndex extends TableAccessException {

    private static final long serialVersionUID = 2741283576486409906L;

    public NoSuchIndex() {
      super();
    }

    public NoSuchIndex(String message) {
      super(message);
    }

    public NoSuchIndex(String message, Throwable cause) {
      super(message, cause);
    }

    public NoSuchIndex(Throwable cause) {
      super(cause);
    }

  }

  public static class NoSuchValue extends TableAccessException {

    private static final long serialVersionUID = 643646579435732303L;

    public NoSuchValue() {
      super();
    }

    public NoSuchValue(String message) {
      super(message);
    }

    public NoSuchValue(String message, Throwable cause) {
      super(message, cause);
    }

    public NoSuchValue(Throwable cause) {
      super(cause);
    }

  }

}
