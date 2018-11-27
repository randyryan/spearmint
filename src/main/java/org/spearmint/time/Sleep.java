/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.time;

import java.time.Duration;

/**
 * Sleep
 *
 * @author ryan131
 * @since Sep 10, 2013, 11:40:47 AM
 */
public final class Sleep {
  private Sleep() {}

  public static void second(long seconds) {
    millis(Duration.ofSeconds(seconds).toMillis());
  }

  public static void millis(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException ie) {
    }
  }

  public static void _50_millis() {
    millis(50);
  }

  public static void _100_millis() {
    millis(100);
  }

  public static void _150_millis() {
    millis(150);
  }

  public static void _200_millis() {
    millis(200);
  }

  public static void _250_millis() {
    millis(250);
  }

}
