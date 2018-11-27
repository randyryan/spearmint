/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.config.app;

import java.util.List;

/**
 * Property Utility
 *
 * @author ryan131
 * @since Apr 25, 2015, 8:25:52 PM
 */
public class PropertyUtil {

  // Public interface

  public static String getString(String key) {
    return getInstance().getStringInternal(key);
  }

  public static List<String> getStringList(String key) {
    return getInstance().getStringListInternal(key);
  }

  public static boolean getBoolean(String key) {
    return getInstance().getBooleanInternal(key);
  }

  public static int getInteger(String key) {
    return getInstance().getIntegerInternal(key);
  }

  public static int[] getIntArray(String key) {
    return getInstance().getIntArrayInternal(key);
  }

  public static Integer[] getIntegerArray(String key) {
    return getInstance().getIntegerArrayInternal(key);
  }

  public static List<Integer> getIntegerList(String key) {
    return getInstance().getIntegerListInternal(key);
  }

  public static long getLong(String key) {
    return getInstance().getLongInternal(key);
  }

  // Underlying mechanism

  private PropertyUtil() {
    configuration = new TypesafeConfiguration(ConfigurationContext.getDefaultContext());
  }

  private Configuration configuration;

  private Configuration getConfiguration() {
    return configuration;
  }

  private String getStringInternal(String key) {
    return getConfiguration().get(key);
  }

  private List<String> getStringListInternal(String key) {
    return getConfiguration().getStringList(key);
  }

  private boolean getBooleanInternal(String key) {
    return getConfiguration().getBoolean(key);
  }

  private int getIntegerInternal(String key) {
    return getConfiguration().getInteger(key);
  }

  private int[] getIntArrayInternal(String key) {
    return getConfiguration().getIntArray(key);
  }

  private Integer[] getIntegerArrayInternal(String key) {
    return getConfiguration().getIntegerArray(key);
  }

  private List<Integer> getIntegerListInternal(String key) {
    return getConfiguration().getIntegerList(key);
  }

  private long getLongInternal(String key) {
    return getConfiguration().getLong(key);
  }

  // Singleton

  private static PropertyUtil getInstance() {
    return Instance.INSTANCE;
  }

  private interface Instance {
    PropertyUtil INSTANCE = new PropertyUtil();
  }

}
