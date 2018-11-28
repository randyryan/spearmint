/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint;

import java.io.IOException;
import java.net.URL;
import java.util.Timer;

import org.spearmint.base.Booleans;
import org.spearmint.base.Strings;
import org.spearmint.io.ByteArray;
import org.spearmint.io.Files;
import org.spearmint.io.Streams;
import org.spearmint.io.URLs;
import org.spearmint.util.Reflector;

/**
 * Spearmint
 *
 * @author ryan131
 * @since Aug 13, 2013, 5:04:38 PM
 */
public final class Spearmint {
  private Spearmint () {}

  // Primitive

  public static boolean getBoolean(String string) {
    return Booleans.fromString(string);
  }

  // Time related

  public static void sleep(long millisecond) {
    try {
      Thread.sleep(millisecond);
    } catch (InterruptedException ie) {
    }
  }

  public static Timer startTimer() {
    return new Timer();
  }

  // Reflection

  public static Reflector reflect(Object object) {
    return new Reflector(object);
  }

  // Seed - Give me a String and I'll see what I can do.

  public static Seed $eed(String string) {
    return new Seed(string);
  }

  public static class Seed {

    private final String string;

    private Seed(String string) {
      this.string = string;
    }

    public URL toUrl() {
      return URLs.fromStringSilently(string);
    }

    public boolean isUrl() {
      return toUrl() != null;
    }

    public ByteArray read() {
      byte[] byteArray = null;
      try {
        byteArray = Streams.read(toUrl()).toByteArray();
      } catch (IOException ioe) {
      }
      String name = Files.getName(string);
      if (Strings.isNotEmpty(name)) {
        return new ByteArray(byteArray, name);
      }
      return new ByteArray(byteArray);
    }

  }

}
