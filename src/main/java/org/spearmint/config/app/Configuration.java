/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.config.app;

import java.util.List;

/**
 * Configuration
 *
 * @author ryan131
 * @since Apr 22, 2015, 5:31:20 PM
 */
public interface Configuration {

  String get(String key);

  String getString(String key);

  List<String> getStringList(String key);

  boolean getBoolean(String key);

  int getInteger(String key);

  int[] getIntArray(String key);

  Integer[] getIntegerArray(String key);

  List<Integer> getIntegerList(String key);
 
  long getLong(String key);

}
