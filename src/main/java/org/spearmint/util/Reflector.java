/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.util;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Reflector
 *
 * @author ryan131
 * @since Aug 13, 2013, 5:05:17 PM
 */
public class Reflector {

  private static final Logger logger = Logger.getLogger(Reflector.class.getName());

  private Object object;
  private Class<?> clazz;

  public Reflector(Object object) {
    this.object = object;
    this.clazz = object.getClass();
  }

  private static Field getField(Class<?> clazz, String name) throws NoSuchFieldException {
    Field field = null;
    try {
      field = clazz.getDeclaredField(name);
    } catch (NoSuchFieldException nsfe) {
      return getField(clazz.getSuperclass(), name);
    } catch (SecurityException ignored) {}
    return field;
  }

  public void set(String name, Object value) {
    try {
      try {
        Field field = getField(clazz, name);
        field.setAccessible(true);
        field.set(object, value);
      } catch (NoSuchFieldException nsfe) {
        throw nsfe;
      }
    } catch (SecurityException se) {
      logger.log(Level.WARNING, "A SecurityException is thrown while setting the " +
          "\"" + name + "\" in \"" + object.getClass() + "\"." , se);
    } catch (NoSuchFieldException nsfe) {
      logger.log(Level.WARNING, "A NoSuchFieldException is thrown while setting the " +
          "\"" + name + "\" in \"" + object.getClass() + "\"." , nsfe);
    } catch (IllegalArgumentException iae) {
      logger.log(Level.WARNING, "A IllegalArgumentException is thrown while setting the " +
          "\"" + name + "\" in \"" + object.getClass() + "\"." , iae);
    } catch (IllegalAccessException iae) {
      logger.log(Level.WARNING, "A IllegalAccessException is thrown while setting the " +
          "\"" + name + "\" in \"" + object.getClass() + "\"." , iae);
    }
  }

  @SuppressWarnings("unchecked")
  public <T> T get(String name) {
    T t = null;
    try {
      try {
        Field field = getField(clazz, name);
        field.setAccessible(true);
        t = (T) field.get(object);
      } catch (NoSuchFieldException nsfe) {
        throw nsfe;
      }
    } catch (SecurityException se) {
      logger.log(Level.WARNING, "A SecurityException is thrown while getting the " +
          "\"" + name + "\" in \"" + object.getClass() + "\"." , se);
    } catch (NoSuchFieldException nsfe) {
      logger.log(Level.WARNING, "A NoSuchFieldException is thrown while getting the " +
          "\"" + name + "\" in \"" + object.getClass() + "\"." , nsfe);
    } catch (IllegalArgumentException iae) {
      logger.log(Level.WARNING, "A IllegalArgumentException is thrown while getting the " +
          "\"" + name + "\" in \"" + object.getClass() + "\"." , iae);
    } catch (IllegalAccessException iae) {
      logger.log(Level.WARNING, "A IllegalAccessException is thrown while getting the " +
          "\"" + name + "\" in \"" + object.getClass() + "\"." , iae);
    }
    return t;
  }

}
