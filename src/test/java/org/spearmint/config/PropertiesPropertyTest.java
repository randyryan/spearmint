/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.config;

import java.util.Properties;

import org.junit.Assert;
import org.spearmint.config.PropertiesProperty;
import org.spearmint.config.Property;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * PropertiesProperty Test
 *
 * @author ryan131
 * @since May 16, 2011, 1:04:43 PM
 */
public class PropertiesPropertyTest extends TestCase {

  public PropertiesPropertyTest(String testName) {
    super(testName);
  }

  public static TestSuite suite() {
    return new TestSuite(PropertiesPropertyTest.class);
  }

  public void testPropertiesProperty() {
    Property property = new PropertiesProperty("test.properties");

    Assert.assertNotNull(property.getProperty("test.description"));
    Assert.assertNotNull(property.getProperty("test.information"));

    Properties properties = property.toProperties();

    Assert.assertEquals(
        property.getProperty("test.description"), properties.getProperty("test.description"));
    Assert.assertEquals(
        property.getProperty("test.information"), properties.getProperty("test.information"));
  }

}
