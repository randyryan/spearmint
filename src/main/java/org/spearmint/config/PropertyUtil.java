/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.config;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import org.spearmint.collect.Maps;
import org.spearmint.config.util.PropertiesUtil;

/**
 * Property Utility
 *
 * @author ryan131
 * @since May 10, 2011, 9:33:34 PM
 */
public final class PropertyUtil {
  private PropertyUtil() {}

  public static Property fromMap(Property property, Map<String, String> map) {
    load(property, map);

    return property;
  }

  public static Property fromProperties(Property property, Properties properties) {
    load(property, properties);

    return property;
  }

  public static void load(Property targetProperty, Property sourceProperty) {
    load(targetProperty, PropertyUtil.toMap(sourceProperty));
  }

  public static void load(Property targetProperty, Map<String, String> sourceMap) {
    for (Map.Entry<String, String> property : sourceMap.entrySet()) {
      targetProperty.setProperty(property.getKey(), property.getValue());
    }
  }

  public static void load(Property targetProperty, Properties sourceProperties) {
    load(targetProperty, PropertiesUtil.toMap(sourceProperties));
  }

  /**
   * @see Property.toMap
   */
  public static Map<String, String> toMap(Property property) {
    Property.MapLike mapLikeProperty = property.toMapLikeProperty();

    Map<String, String> map = Maps.newHashMap();
    for (String key : mapLikeProperty.propertyKeySet()) {
      map.put(key, property.getProperty(key));
    }

    return Collections.unmodifiableMap(map);
  }

  /**
   * @see Property.toProperties
   */
  public static Properties toProperties(Property property) {
    Map<String, String> map = PropertyUtil.toMap(property);
    return PropertiesUtil.fromMap(map);
  }

}
