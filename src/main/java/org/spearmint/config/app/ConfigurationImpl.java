/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.config.app;

import java.util.List;

/**
 * Configuration (implementation)
 *
 * @author ryan131
 * @since Apr 26, 2015, 3:36:54 PM
 */
abstract class ConfigurationImpl implements Configuration {

  @Override
  public String get(String key) {
    return getString(key);
  }

  @Override
  public abstract String getString(String key);

  @Override
  public abstract List<String> getStringList(String key);

  @Override
  public abstract boolean getBoolean(String key);

  @Override
  public abstract int getInteger(String key);

  @Override
  public abstract int[] getIntArray(String key);

  @Override
  public abstract Integer[] getIntegerArray(String key);

  @Override
  public abstract List<Integer> getIntegerList(String key);

  @Override
  public abstract long getLong(String key);

}
