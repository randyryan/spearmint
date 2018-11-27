/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.io;

import java.io.File;
import java.net.URI;
import java.net.URL;

/**
 * Files - Not only a utility class for java.io.File type, but also for other file-related ops.
 *
 * @author ryan131
 * @since Aug 15, 2013, 4:18:34 PM
 */
public final class Files {
  private Files() {}

  public static String getName(String string) {
    int lastSeparatorIndex = string.lastIndexOf('/');
    if (lastSeparatorIndex == -1) {
      lastSeparatorIndex = string.lastIndexOf('\\');
    }
    return string.substring(lastSeparatorIndex + 1); // Exclusive of the separator
  }

  public static String getName(URL url) {
    return getName(url.getPath());
  }

  public static String getName(URI uri) {
    return getName(uri.getPath());
  }

  public static String getName(File file) {
    if (file.isFile()) {
      return file.getName();
    }
    throw new IllegalArgumentException("The File \"" + file + " is not a file.");
  }

  public static File append(File directory, String filename) {
    if (directory.isDirectory()) {
      return new File(directory, filename);
    }
    throw new IllegalArgumentException("The File \"" + directory + " is not a directory.");
  }

  public static File fromString(String string) {
    return new File(string);
  }

}
