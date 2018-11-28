/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.config.util;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.spearmint.base.Longs;
import org.spearmint.collect.Lists;
import org.spearmint.collect.Maps;
import org.spearmint.config.util.PropertiesUtil;
import org.spearmint.config.util.PropertiesUtilInternal;
import org.spearmint.time.Stopwatch;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Properties Utility Test
 *
 * @author ryan131
 * @since May 3, 2016, 9:50:39 PM
 */
public class PropertiesUtilTest extends TestCase {

  public PropertiesUtilTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(PropertiesUtilTest.class);
  }

  public void testCopy() {
    Properties sourceProperties = new Properties();
    sourceProperties.setProperty("1", "One");
    Properties targetProperties = new Properties();
    targetProperties.setProperty("2", "Two");

    PropertiesUtil.copy(sourceProperties, targetProperties);

    Assert.assertTrue(sourceProperties.getProperty("2") == null);
    Assert.assertTrue(targetProperties.getProperty("1") != null);
  }

  public void testFromMap() {
    Map<String, String> map = Maps.newHashMap();
    map.put("1", "One");
    map.put("2", "Two");

    Properties properties = PropertiesUtil.fromMap(map);

    Assert.assertEquals(map.get("1"), properties.getProperty("1"));
    Assert.assertEquals(map.get("2"), properties.getProperty("2"));

    PropertiesUtil.fromMap(map, properties);

    Assert.assertEquals(map.get("1"), properties.getProperty("1"));
    Assert.assertEquals(map.get("2"), properties.getProperty("2"));
  }

  public void testLoad() {
    Properties properties = PropertiesUtil.load("test.properties");
    Assert.assertTrue(properties.getProperty("test.description") != null);
  }

  public void testToMap() {
    Properties properties = System.getProperties();

    List<Long> keys = Lists.newArrayList();
    List<Long> entrySet = Lists.newArrayList();
    List<Long> propertyNames = Lists.newArrayList();
    List<Long> stringPropsNames = Lists.newArrayList();

    // Warm up

    PropertiesUtilInternal.toMap_keys(properties);
    PropertiesUtilInternal.toMap_propertyNames(properties);
    PropertiesUtilInternal.toMap_entrySet(properties);
    PropertiesUtilInternal.toMap_stringPropertyNames(properties);

    // Collect sample

    final int base = 1000;

    for (int i = 0; i < 50; i++) {
      Stopwatch timerKeys = new Stopwatch();
      PropertiesUtilInternal.toMap_keys(properties);
      keys.add(timerKeys.elapsedNano() / base);

      Stopwatch timerEntrySet = new Stopwatch();
      PropertiesUtilInternal.toMap_entrySet(properties);
      entrySet.add(timerEntrySet.elapsedNano() / base);

      Stopwatch timerPropertyNames = new Stopwatch();
      PropertiesUtilInternal.toMap_propertyNames(properties);
      propertyNames.add(timerPropertyNames.elapsedNano() / base);

      Stopwatch timerStringPropertyNames = new Stopwatch();
      PropertiesUtilInternal.toMap_stringPropertyNames(properties);
      stringPropsNames.add(timerStringPropertyNames.elapsedNano() / base);
    }

    System.out.printf("Enumeration<K> Hashtable.keys(): %d (average)%n",
        Longs.average(Longs.toPrimitiveLongArray(keys)));
    //System.out.println(keys);

    System.out.printf("Set<Map.Entry<K,V>> Hashtable.entrySet(): %d (average)%n",
        Longs.average(Longs.toPrimitiveLongArray(entrySet)));
    //System.out.println(entrySet);

    System.out.printf("Enumeration<?> Properties.propertyNames(): %d (average)%n",
        Longs.average(Longs.toPrimitiveLongArray(propertyNames)));
    //System.out.println(propertyNames);

    System.out.printf("Set<String> Properties.stringPropertyNames(): %d (average)%n",
        Longs.average(Longs.toPrimitiveLongArray(stringPropsNames)));
    //System.out.println(stringPropsNames);
  }

}
