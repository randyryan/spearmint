/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.config.app;

import java.util.List;

import org.spearmint.primitive.Ints;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Typesafe Configuration - implementation for com.typesafe.config
 *
 * @author ryan131
 * @since Apr 26, 2015, 3:41:47 PM
 */
public class TypesafeConfiguration extends ConfigurationImpl {

  private Config config;

  private String context;

  public TypesafeConfiguration(ConfigurationContext configurationContext) {
    if (configurationContext.isDefaultContext()) {
      config = ConfigFactory.load();
    } else {
      config = ConfigFactory.load(configurationContext.getConfigName());
      context = configurationContext.getConfigName();
    }
  }

  private String getQualifiedKey(String key) {
    if (context != null) {
      return context + "." + key;
    }
    return key;
  }

  @Override
  public String getString(String key) {
    return config.getString(getQualifiedKey(key));
  }

  @Override
  public List<String> getStringList(String key) {
    return config.getStringList(key);
  }

  @Override
  public boolean getBoolean(String key) {
    return config.getBoolean(getQualifiedKey(key));
  }

  @Override
  public int getInteger(String key) {
    return config.getInt(getQualifiedKey(key));
  }

  @Override
  public int[] getIntArray(String key) {
    return Ints.toIntArray(config.getIntList(key));
  }

  @Override
  public Integer[] getIntegerArray(String key) {
    return Ints.toIntegerArray(config.getIntList(key));
  }

  @Override
  public List<Integer> getIntegerList(String key) {
    return config.getIntList(key);
  }

  @Override
  public long getLong(String key) {
    return config.getLong(getQualifiedKey(key));
  }

}
