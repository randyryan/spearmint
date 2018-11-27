/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.config.util;

import java.util.Map;
import java.util.Properties;

/**
 * Environment Variables Utility
 *
 * @author ryan131
 * @since May 2, 2016, 10:45:04 PM
 */
public final class EnvironmentVariablesUtil {
  private EnvironmentVariablesUtil() {}

  private static final Map<String, String> environmentVariables;

  static {
    environmentVariables = System.getenv();
  }

  public static String getVariable(String key) {
    return environmentVariables.get(key);
  }

  public static String getVariable(String key, String defaultValue) {
    String environmentVariable = environmentVariables.get(key);
    return environmentVariable == null ? defaultValue : environmentVariable;
  }

  public static Properties toProperties() {
    Properties properties = new Properties();
    properties.putAll(environmentVariables);
    return properties;
  }

  public static void toProperties(Properties properties) {
    properties.putAll(toMap());
  }

  public static Map<String, String> toMap() {
    return System.getenv();
  }

  public static void toMap(Map<String, String> map) {
    map.clear();
    map.putAll(toMap());
  }

}
