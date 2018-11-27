/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.config.app;

/**
 * Configuration Context
 *
 * @author ryan131
 * @since Apr 22, 2015, 10:33:31 PM
 */
public class ConfigurationContext {

  private Class<?> className;

  private String configName;

  public boolean isDefaultContext() {
    return className == null && configName == null;
  }

  public Class<?> getClassName() {
    if (className == null) {
      throw new UnsupportedOperationException();
    }
    return className;
  }

  public String getConfigName() {
    if (configName == null) {
      throw new UnsupportedOperationException();
    }
    return configName;
  }

  // Get instance

  private ConfigurationContext() {}

  private ConfigurationContext(Class<?> className) {
    this.className = className;
  }

  private ConfigurationContext(String configName) {
    this.configName = configName;
  }

  public static ConfigurationContext getDefaultContext() {
    return new ConfigurationContext();
  }

  public static ConfigurationContext getContext(Class<?> className) {
    return new ConfigurationContext(className);
  }

  public static ConfigurationContext getContext(String configName) {
    return new ConfigurationContext(configName);
  }

}
