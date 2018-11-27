/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.config;

import org.junit.Assert;
import org.spearmint.config.BaseProperty;
import org.spearmint.config.Property;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * BaseProperty Test
 *
 * @author ryan131
 * @since May 16, 2011, 11:00:47 AM
 */
public class BasePropertyTest extends TestCase {

  public BasePropertyTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(BasePropertyTest.class);
  }

  public void testGetSetProperty() {
    Property property = new BaseProperty();

    // Set

    property.setProperty("test.key.1", "test.value.1");

    // Get

    Assert.assertEquals("test.value.1", property.getProperty("test.key.1"));
    Assert.assertEquals(null, property.getProperty("test.key.2"));
    Assert.assertEquals("test.value.2", property.getProperty("test.key.2", "test.value.2"));
  }

  public void testAddProperty() {
    Property property = new BaseProperty();

    property.addProperty("add", "1");
    property.addProperty("add", "2");

    Assert.assertEquals("1, 2", property.getProperty("add"));

    property.addProperty("add", "3");

    Assert.assertEquals("1, 2, 3", property.getProperty("add"));
  }

  public void testRemoveProperty() {
    Property property = new BaseProperty();

    property.addProperty("a.key.to.be.removed", "You don't really care");
    property.removeProperty("a.key.to.be.removed");

    Assert.assertEquals(null, property.getProperty("a.key.to.be.removed"));
  }

  public void testToMap() {
    Property property = new BaseProperty();
    property.addProperty("1", "One");
    Property.MapLike mapLikeProperty = property.toMapLikeProperty();

    Assert.assertEquals(mapLikeProperty.size(), 1);
    Assert.assertTrue(mapLikeProperty.containsKey("1"));
    Assert.assertTrue(mapLikeProperty.containsValue("One"));
    Assert.assertTrue(mapLikeProperty.propertyKeySet().contains("1"));

    mapLikeProperty.clear();

    Assert.assertTrue(mapLikeProperty.isEmpty());
    Assert.assertEquals(0, mapLikeProperty.size());
  }

}
