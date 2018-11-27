/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.io;

import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Closeable Utility
 *
 * @author ryan131
 * @since May 5, 2011, 3:42:59 PM
 */
public final class Closeables {
  private Closeables() {}

  private static final Logger logger = Logger.getLogger(Closeables.class.getName());

  public static void close(Closeable closeable, boolean swallowIOException) throws IOException {
    try {
      if (closeable != null) {
        closeable.close();
      }
    } catch (IOException ioe) {
      if (swallowIOException) {
        logger.log(Level.WARNING, "An IOException is thrown while closing the Closeable.", ioe);
      } else {
        throw ioe;
      }
    }
  }

  public static void closeSilently(Closeable closeable) {
    try {
      close(closeable, true);
    } catch (IOException ioe) {
      // Impossible
    }
  }

}
