/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.config.app;

import java.util.List;

/**
 * Property
 *
 * @author ryan131
 * @since Apr 25, 2015, 8:15:30 PM
 */
public interface Property {

  String get(String key);

  String getString(String key);

  boolean getBoolean(String key);

  int getInteger(String key);

  int[] getIntArray(String key);

  Integer[] getIntegerArray(String key);

  List<Integer> getIntegerList(String key);
 
  long getLong(String key);

}
