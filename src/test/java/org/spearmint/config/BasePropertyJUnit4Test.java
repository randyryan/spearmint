/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.config;

import org.junit.Test;
import org.spearmint.config.BaseProperty;
import org.spearmint.config.Property;

/**
 * BasePropertyJUnit4Test
 *
 * @author ryan131
 * @since May 23, 2011, 4:56:40 PM
 */
public class BasePropertyJUnit4Test {

  @Test(expected=IllegalArgumentException.class)
  public void ddd () {
    Property property = new BaseProperty();
    property.addProperty("", "");
  }

}
