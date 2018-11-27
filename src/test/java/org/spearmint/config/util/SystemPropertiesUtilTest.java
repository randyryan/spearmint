/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.config.util;

import java.util.Properties;

import org.spearmint.config.util.SystemPropertiesUtil;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * System Properties Utility Test
 *
 * @author ryan131
 * @since May 2, 2016, 9:09:38 PM
 */
public class SystemPropertiesUtilTest extends TestCase {

  public SystemPropertiesUtilTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(SystemPropertiesUtilTest.class);
  }

  public void testClearProperty() {
    String key = "webdriver.chrome.driver";
    String value = "D:\\chromedriver.exe";

    System.setProperty(key, value);

    SystemPropertiesUtil.clearProperty(key);
  }

  public void testGetPropertyString() {
    SystemPropertiesUtil.getProperty("webdriver.chrome.driver");
  }

  public void testGetPropertyStringString() {
    SystemPropertiesUtil.getProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
  }

  public void testSetProperty() {
    String key = "webdriver.chrome.driver";
    String value = "D:\\chromedriver.exe";

    SystemPropertiesUtil.setProperty(key, value);
  }

  public void testGetProperties() {
    SystemPropertiesUtil.getProperties();
  }

  public void testSetProperties() {
    Properties properties = new Properties(System.getProperties());

    String key = "webdriver.chrome.driver";
    String value = "D:\\chromedriver.exe";

    properties.setProperty(key, value);

    SystemPropertiesUtil.setProperties(properties);
  }

}
