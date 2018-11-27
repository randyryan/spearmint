/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint;


/**
 * SpearmintException
 *
 * @author ryan131
 * @since Aug 14, 2013, 3:03:12 PM
 */
@SuppressWarnings("serial")
public class SpearmintException extends RuntimeException {

  public static SpearmintException from(Throwable cause) {
    return new SpearmintException(cause);
  }

  public static SpearmintException from(Throwable cause, String origin) {
    SpearmintException se = new SpearmintException(cause);
    se.setOrigin(origin);
    return se;
  }

  private String origin;

  public SpearmintException() {
    super();
  }

  public SpearmintException(String message) {
    super(message);
  }

  public SpearmintException(Throwable cause) {
    super(cause);
  }

  public SpearmintException(String message, Throwable cause) {
    super(message, cause);
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getOrigin() {
    return origin;
  }

}
