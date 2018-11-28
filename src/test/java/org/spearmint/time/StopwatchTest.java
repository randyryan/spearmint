/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.time;

import org.spearmint.util.Stopwatch;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Stopwatch Test
 *
 * @author ryan131
 * @since Apr 13, 2011, 2:54:31 PM
 */
public class StopwatchTest extends TestCase {

  public StopwatchTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(StopwatchTest.class);
  }

  public void testCreate() {
    Stopwatch stopwatch = Stopwatch.create();

    assertFalse(stopwatch.isStarted());
    assertFalse(stopwatch.isStopped());

    assertEquals(0, stopwatch.elapsedMillis());
    assertEquals(0, stopwatch.elapsedNano());
  }

  public void testCreateStarted() {
    Stopwatch stopwatch = Stopwatch.createStarted();

    assertTrue(stopwatch.isStarted());
    assertFalse(stopwatch.isStopped());

    try {
      Thread.sleep(1);
    } catch (InterruptedException ie) {
    }

    assertTrue(stopwatch.elapsedMillis() > 0);
    assertTrue(stopwatch.elapsedNano() > 0);
  }

  public void testStart() {
    Stopwatch stopwatch = Stopwatch.create();
    stopwatch.start();

    assertTrue(stopwatch.isStarted());
    assertFalse(stopwatch.isStopped());

    try {
      Thread.sleep(1);
    } catch (InterruptedException ie) {
    }

    assertTrue(stopwatch.elapsedMillis() > 0);
    assertTrue(stopwatch.elapsedNano() > 0);
  }

  public void testStop() {
    Stopwatch stopwatch = Stopwatch.createStarted();
    try {
      Thread.sleep(1);
    } catch (InterruptedException ie) {
    }
    stopwatch.stop();

    assertTrue(stopwatch.isStarted());
    assertTrue(stopwatch.isStopped());
  }

  public void testReset() {
    Stopwatch stopwatch = Stopwatch.createStarted();
    try {
      Thread.sleep(1);
    } catch (InterruptedException ie) {
    }
    stopwatch.reset();

    assertFalse(stopwatch.isStarted());
    assertFalse(stopwatch.isStopped());

    assertEquals(0, stopwatch.elapsedMillis());
    assertEquals(0, stopwatch.elapsedNano());
  }

  public void testRestart() {
    Stopwatch stopwatch = Stopwatch.createStarted();
    try {
      Thread.sleep(10);
    } catch (InterruptedException ie) {
    }
    stopwatch.stop();
    stopwatch.restart();
    try {
      Thread.sleep(10);
    } catch (InterruptedException ie) {
    }

    assertTrue(stopwatch.isStarted());
    assertFalse(stopwatch.isStopped());
    assertTrue(stopwatch.elapsedMillis() > 20);
  }

}
