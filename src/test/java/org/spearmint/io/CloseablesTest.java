/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

import org.spearmint.io.Closeables;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Closeable Utility Test
 *
 * @author ryan131
 * @since May 5, 2011, 8:17:41 PM
 */
public class CloseablesTest extends TestCase {

  public CloseablesTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(CloseablesTest.class);
  }

  public void test() {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test");

    Closeables.closeSilently(inputStream);

    Closeable closeable = new Closeable() {
      @Override
      public void close() throws IOException {
        throw new IOException();
      }
    };

    Closeables.closeSilently(closeable);
  }

}
