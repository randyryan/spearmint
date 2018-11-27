/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.config.app;

import java.util.List;

/**
 * Property (implementation)
 *
 * @author ryan131
 * @since Apr 25, 2015, 8:24:48 PM
 */
public class PropertyImpl implements Property {

  @Override
  public String get(String key) {
    return PropertyUtil.getString(key);
  }

  @Override
  public String getString(String key) {
    return PropertyUtil.getString(key);
  }

  @Override
  public boolean getBoolean(String key) {
    return PropertyUtil.getBoolean(key);
  }

  @Override
  public int getInteger(String key) {
    return PropertyUtil.getInteger(key);
  }

  @Override
  public int[] getIntArray(String key) {
    return PropertyUtil.getIntArray(key);
  }

  @Override
  public Integer[] getIntegerArray(String key) {
    return PropertyUtil.getIntegerArray(key);
  }

  @Override
  public List<Integer> getIntegerList(String key) {
    return PropertyUtil.getIntegerList(key);
  }

  @Override
  public long getLong(String key) {
    return PropertyUtil.getLong(key);
  }

}
