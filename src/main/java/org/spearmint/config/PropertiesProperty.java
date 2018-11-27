/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.config;

import java.util.Properties;

import org.spearmint.config.util.PropertiesUtil;

/**
 * Properties Property
 *
 * @author ryan131
 * @since May 12, 2011, 5:08:40 PM
 */
public class PropertiesProperty extends BaseProperty {

  private Properties properties;

  public PropertiesProperty(String resource) {
    properties = PropertiesUtil.load(resource);

    PropertiesUtil.toMap(properties, map);
  }

  @Override
  public Properties toProperties() {
    Properties newProperties = new Properties();
    PropertiesUtil.copy(properties, newProperties);

    return new Properties(newProperties);
  }

}
