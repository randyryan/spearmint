/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.config.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.spearmint.collect.Lists;
import org.spearmint.collect.Maps;
import org.spearmint.io.Closeables;

/**
 * Properties Utility (Internal) - No partial class in Java.
 *
 * @author ryan131
 * @since May 5, 2016, 9:47:19 AM
 */
class PropertiesUtilInternal {
  private PropertiesUtilInternal() {}

  private static final Logger logger = Logger.getLogger(PropertiesUtil.class.getName());

  /**
   * Calls the appropriate method according to the parameters.
   */
  static void load(boolean silent, Properties properties, String... resources) {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    String resource = null;
    if (resources.length == 1) {
      resource = resources[0];
    }

    if (silent) {
      if (resource != null) {
        loadSilent(properties, classLoader, resource);
      } else {
        loadSilent(properties, classLoader, resources);
      }
    } else {
      if (resource != null) {
        load(properties, classLoader, resource);
      } else {
        load(properties, classLoader, resources);
      }
    }
  }

  static void load(Properties properties, ClassLoader classLoader, String resource) {
    URL url = classLoader.getResource(resource);
    if (url == null) {
      logger.log(Level.WARNING, "Unable to load \"" + resource + "\"");
      return;
    }

    try {
      InputStream inputStream = null;
      try {
        inputStream = url.openStream();
        properties.load(inputStream);
      } finally {
        Closeables.closeSilently(inputStream);
      }
    } catch (IOException ioe) {
      throw new ExceptionInInitializerError(ioe);
    }

//    System.out.println("Loaded " + url.toString());
    logger.log(Level.INFO, "Loaded " + url.toString());
  }

  static void load(Properties properties, ClassLoader classLoader, String... resources) {
    List<URL> urlList = Lists.newArrayList();

    try {
      for (String resource : resources) {
        Enumeration<URL> urlEnum = classLoader.getResources(resource);
        while(urlEnum.hasMoreElements()) {
          URL url = urlEnum.nextElement();

          InputStream inputStream = null;
          try {
            inputStream = url.openStream();
            properties.load(inputStream);
          } finally {
            if (inputStream != null) {
              inputStream.close();
            }
          }

          urlList.add(url);
        }
      }
    } catch (IOException ioe) {
      throw new ExceptionInInitializerError(ioe);
    }

    for (URL url : urlList) {
//      System.out.println("Loaded " + url.toString());
      logger.log(Level.INFO, "Loaded " + url.toString());
    }
  }

  static void loadSilent(Properties properties, ClassLoader classLoader, String resource) {
    InputStream inputStream = classLoader.getResourceAsStream(resource);
    if (inputStream == null) {
      return; // TODO fails silently
    }

    try {
      try {
        properties.load(inputStream);
      } finally {
        inputStream.close();
      }
    } catch (IOException ioe) {
      throw new ExceptionInInitializerError(ioe);
    }
  }

  static void loadSilent(Properties properties, ClassLoader classLoader, String... resources) {
    try {
      for (String resource : resources) {
        Enumeration<URL> urlEnum = classLoader.getResources(resource);
        while(urlEnum.hasMoreElements()) {
          URL url = urlEnum.nextElement();

          InputStream inputStream = null;
          try {
            inputStream = url.openStream();
            properties.load(inputStream);
          } finally {
            if (inputStream != null) {
              inputStream.close();
            }
          }
        }
      }
    } catch (IOException ioe) {
      throw new ExceptionInInitializerError(ioe);
    }
  }

  /**
   * Enumeration<K> java.util.Hashtable.keys()
   */
  static Map<String, String> toMap_keys(Properties properties) {
    return Maps.fromProperties(properties);
  }

  /**
   * Set<Map.Entry<K,V>> java.util.Hashtable.entrySet()
   */
  static Map<String, String> toMap_entrySet(Properties properties) {
    Map<String, String> map = Maps.newHashMap();

    for (Map.Entry<Object, Object> entry : properties.entrySet()) {
      map.put((String) entry.getKey(), (String) entry.getValue());
    }

    return map;
  }

  /**
   * Enumeration<?> java.util.Properties.propertyNames()
   */
  static Map<String, String> toMap_propertyNames(Properties properties) {
    Map<String, String> map = Maps.newHashMap();

    Enumeration<?> enumeration = properties.propertyNames();
    while (enumeration.hasMoreElements()) {
      String key = (String) enumeration.nextElement();
      String value = properties.getProperty(key);
      map.put(key, value);
    }

    return map;
  }

  /**
   * Set<String> java.util.Properties.stringPropertyNames()
   */
  static Map<String, String> toMap_stringPropertyNames(Properties properties) {
    Map<String, String> map = Maps.newHashMap();

    for (String propertyName : properties.stringPropertyNames()) {
      map.put(propertyName, properties.getProperty(propertyName));
    }

    return map;
  }

}
