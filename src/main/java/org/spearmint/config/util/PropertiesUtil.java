/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.config.util;

import java.util.Map;
import java.util.Properties;

import org.spearmint.collect.Maps;

/**
 * Properties Utility
 *
 * @author ryan131
 * @since May 3, 2016, 9:32:43 PM
 */
public final class PropertiesUtil {
  private PropertiesUtil() {}

  public static void copy(Properties sourceProperties, Properties targetProperties) {
    targetProperties.putAll(toMap(sourceProperties));
  }

  public static Properties fromMap(Map<String, String> map) {
    Properties properties = new Properties();
    properties.putAll(map);

    return properties;
  }

  public static void fromMap(Map<String, String> sourceMap, Properties targetProperties) {
    targetProperties.clear();
    targetProperties.putAll(sourceMap);
  }

  public static Properties load(String resource) {
    Properties properties = new Properties();

    PropertiesUtilInternal.load(false, properties, resource);

    return properties;
  }

  public static void load(Properties properties, String resource) {
    PropertiesUtilInternal.load(false, properties, resource);
  }

  public static Properties load(String resource, String defaultResource) {
    Properties defaultProperties = load(defaultResource);

    Properties properties = new Properties(defaultProperties);
    PropertiesUtilInternal.load(false, properties, resource);

    return properties;
  }

  public static Map<String, String> toMap(Properties properties) {
    // toMap_keys is selected for being the best performance algorithm
    return Maps.immutableMap(PropertiesUtilInternal.toMap_keys(properties));
  }

  public static void toMap(Properties sourceProperties, Map<String, String> targetMap) {
    targetMap.clear();
    targetMap.putAll(toMap(sourceProperties));
  }

}
