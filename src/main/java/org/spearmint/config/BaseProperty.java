/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.config;

import java.util.Map;
import java.util.Set;

import org.spearmint.collect.Maps;

/**
 * Base Property
 *
 * @author ryan131
 * @since May 12, 2011, 4:25:11 PM
 */
public class BaseProperty extends AbstractProperty {

  protected Map<String, String> map = Maps.newHashMap();

  @Override
  public String getProperty(String key) {
    return map.get(key);
  }

  private String putProperty(String key, String value) {
    if (key.trim().equals("")) {
      throw new IllegalArgumentException("Cannot store value with empty key.");
    }

    return map.put(key, value);
  }

  @Override
  public String setProperty(String key, String value) {
    return putProperty(key, value);
  }

  @Override
  public void addProperty(String key, String value) {
    if (containsKey(key)) {
      putProperty(key, map.get(key) + ", " + value);
    } else {
      putProperty(key, value);
    }
  }

  @Override
  public void removeProperty(String key) {
    map.remove(key);
  }

  @Override
  public int size() {
    return map.size();
  }

  @Override
  public void clear() {
    map.clear();
  }

  @Override
  public boolean containsKey(String key) {
    return map.containsKey(key);
  }

  @Override
  public boolean containsValue(String value) {
    return map.containsValue(value);
  }

  @Override
  public Set<String> propertyKeySet() {
    return map.keySet();
  }

}
