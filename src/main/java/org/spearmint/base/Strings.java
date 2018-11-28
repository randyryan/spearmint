/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.base;

/**
 * String Utility - Yeah, I know java.lang.String isn't a primitive type in Java,
 * but seriously, its vastly use and optimizations in the JVM, it can virtually be
 * seen as a primitive type. Besides, string is primitive type in many other modern
 * languages.
 *
 * @author ryan131
 * @since Apr 29, 2011, 4:09:32 PM
 */
public final class Strings {
  private Strings() {}

  public static String emptyString() {
    return "";
  }

  public static boolean isNotEmpty(String string) {
    return string != null && !string.isEmpty();
  }

  public static boolean isNullOrEmpty(String stringToBeTested) {
    return stringToBeTested == null || stringToBeTested.isEmpty();
  }

  public static String toLowercaseCharArray(String s) {
    char[] charArray = s.toCharArray();

    for (int i = 0; i < charArray.length; i++) {
      char c = charArray[i];
      if (c >= 'A' && c <= 'Z') {
        c += 32;
      }
      charArray[i] = c;
    }

    return new String(charArray);
  }

  public static String toLowercaseStringBuilder(String s) {
    StringBuilder sb = new StringBuilder(s);

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c >= 'A' && c <= 'Z') {
        c += 32;
        sb.setCharAt(i, c);
      }
    }

    return sb.toString();
  }

  public static String toLowerCaseLazyStringBuilder(String s) {
    if (s == null) {
      return null;
    }

    StringBuilder sb = null;

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if ((c >= 'A') && (c <= 'Z')) {
        if (sb == null) {
          sb = new StringBuilder(s);
        }

        sb.setCharAt(i, (char)(c + 32));
      }
    }

    if (sb == null) {
      return s;
    }

    return sb.toString();
  }

}
