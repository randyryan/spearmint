/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.util;

import java.time.Duration;

/**
 * Sleep
 *
 * @author ryan131
 * @since Sep 10, 2013, 11:40:47 AM
 */
public final class Sleeper {
  private Sleeper() {}

  public static void sleep(Duration duration, boolean swallowInterruptedException)
      throws InterruptedException {
    sleep(duration.toMillis(), swallowInterruptedException);
  }

  public static void sleepSilently(Duration duration) {
    sleepSilently(duration.toMillis());
  }

  public static void sleep(long millis, boolean swallowInterruptedException)
      throws InterruptedException {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException ie) {
      if (swallowInterruptedException) {
        // log
      } else {
        throw ie;
      }
    }
  }

  public static void sleepSilently(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException ie) {
      // log
    }
  }

  public static void sleep_50_ms() {
    sleepSilently(50);
  }

  public static void sleep_100_ms() {
    sleepSilently(100);
  }

  public static void sleep_125_ms() {
    sleepSilently(150);
  }

  public static void sleep_250_ms() {
    sleepSilently(250);
  }

  public static void sleep_1_s() {
    sleepSilently(Duration.ofSeconds(1));
  }

  public static void sleep_2_s() {
    sleepSilently(Duration.ofSeconds(2));
  }

  public static void sleep_5_s() {
    sleepSilently(Duration.ofSeconds(5));
  }

}
