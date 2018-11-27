/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.config.util;

import java.util.Map;
import java.util.Properties;

import org.spearmint.collect.Maps;

/**
 * System Properties Utility - Manages system properties.
 * 
 * This class will load "system.properties" and "system-override.properties" and set them to
 * the system properties then provide as a ConcurrentMap for high-speed concurrent access.
 * The map also serves as a "backup" when calls on methods from java.lang.System fails.
 *
 * @author ryan131
 * @since Apr 29, 2016, 3:58:28 PM
 */
public final class SystemPropertiesUtil {
  private SystemPropertiesUtil() {}

  public static final String DEFAULT_PROPERTIES = "system.properties";

  public static final String OVERRIDE_PROPERTIES = "system-override.properties";

  private static final Map<String, String> map = Maps.newConcurrentHashMap();

  static {

    // Create a Properties object with the current system properties

    Properties properties = new Properties(System.getProperties());

    // Read system-default.properties to the created Properties object

    PropertiesUtil.load(properties, DEFAULT_PROPERTIES);

    // Read system-override.properties to the created Properties object

    PropertiesUtil.load(properties, OVERRIDE_PROPERTIES);

    // Write properties from the Properties object to system properties

    SystemPropertiesUtil.setProperties(properties);
  }

  public static void clearProperty(String key) {
    System.clearProperty(key);

    map.remove(key);
  }

  public static String getProperty(String key) {
    String value = map.get(key);

    if (value == null) {
      value = System.getProperty(key);
    }

    return value;
  }

  public static String getProperty(String key, String defaultValue) {
    String value = map.get(key);

    if (value == null) {
      value = System.getProperty(key, defaultValue);
    }

    return value;
  }

  public static void setProperty(String key, String value) {
    System.setProperty(key, value);

    map.put(key, value);
  }

  public static Properties getProperties() {
    Properties properties = PropertiesUtil.fromMap(map);

    if (properties.isEmpty()) {
      properties = System.getProperties();
    }

    return properties;
  }

  public static void setProperties(Properties properties) {
    System.setProperties(properties);

    PropertiesUtil.toMap(properties, map);
  }

  @Deprecated
  public static String getEnvironmentVariable(String key) {
    return EnvironmentVariablesUtil.getVariable(key);
  }

  @Deprecated
  public static String getEnvironmentVariable(String key, String defaultValue) {
    return EnvironmentVariablesUtil.getVariable(key, defaultValue);
  }

  @Deprecated
  public static Properties getEnvironmentVariables() {
    return EnvironmentVariablesUtil.toProperties();
  }

}
