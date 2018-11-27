/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.time;

import java.time.Duration;

/**
 * A Simple Timer
 *
 * @author ryan131
 * @since Apr 13, 2011, 2:52:03 PM
 */
public final class Stopwatch {

  public static Stopwatch create() {
    return new Stopwatch();
  }

  public static Stopwatch createStarted() {
    Stopwatch stopwatch = new Stopwatch();
    stopwatch.start();
    return stopwatch;
  }

  private long startNano;
  private long timedNano; // for stop() only

  public void start() {
    if (!isStarted()) {
      startNano = System.nanoTime();
    } else {
      throw new IllegalStateException("The stopwatch is already started.");
    }
  }

  public void stop() {
    if (isStarted()) {
      timedNano = elapsedNano();
    } else {
      throw new IllegalStateException("The stopwatch is not even started.");
    }
  }

  public void reset() {
    startNano = 0;
    timedNano = 0;
  }

  public void restart() {
    if (isStopped()) {
      startNano = System.nanoTime() - timedNano;
      timedNano = 0;
    } else if (isStarted()) {
      startNano = System.nanoTime();
    } else {
      throw new IllegalStateException("The stopwatch is not even started.");
    }
  }

  public boolean isStarted() {
    return startNano > 0;
  }

  public boolean isStopped() {
    return timedNano > 0;
  }

  public Duration elapsed() {
    return Duration.ofNanos(elapsedNano());
  }

  public long elapsedNano() {
    return isStopped() ? timedNano : isStarted() ? System.nanoTime() - startNano : 0;
  }

  public long elapsedMillis() {
    return elapsed().toMillis();
  }

}
