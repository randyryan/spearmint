/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.config;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.spearmint.config.util.PropertiesUtil;

/**
 * Abstract Property
 *
 * @author ryan131
 * @since May 11, 2011, 9:33:04 PM
 */
public abstract class AbstractProperty implements Property.MapLike {

  // Property

  @Override
  public abstract String getProperty(String key);

  @Override
  public String getProperty(String key, String defaultValue) {
    String value = getProperty(key);
    return value != null ? value : defaultValue;
  }

  @Override
  public abstract String setProperty(String key, String value);

  @Override
  public abstract void addProperty(String key, String value);

  @Override
  public abstract void removeProperty(String key);

  // View

  @Override
  public Map<String, String> toMap() {
    return PropertyUtil.toMap(this);
  }

  @Override
  public Property.MapLike toMapLikeProperty() {
    return (Property.MapLike) this;
  }

  @Override
  public Properties toProperties() {
    return PropertiesUtil.fromMap(toMap());
  }

  // Map-like

  public abstract int size();

  @Override
  public boolean isEmpty() {
    return size() <= 0 ? true : false;
  }

  @Override
  public abstract void clear();

  @Override
  public abstract boolean containsKey(String key);

  @Override
  public abstract boolean containsValue(String value);

  @Override
  public abstract Set<String> propertyKeySet();

}
