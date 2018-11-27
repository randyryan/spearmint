/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.io;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * URL utility
 *
 * @author ryan131
 * @since Mar 10, 2012, 2:59:47 PM
 */
public final class URLs {
  private URLs() {}

  private static final Logger logger = Logger.getLogger(URLs.class.getName());

  public static URL fromString(String string) throws MalformedURLException {
    return new URL(string);
  }

  public static URL fromStringSilently(String string) {
    URL url = null;
    try {
      url = new URL(string);
    } catch (MalformedURLException mue) {
      logger.log(Level.WARNING, "A MalformedURLException is thrown while creating the URL.", mue);
    }
    return url;
  }

}
