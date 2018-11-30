/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.util;

import java.lang.reflect.Field;

import org.spearmint.base.Throwables;
import org.spearmint.base.Throwables.Throw;

/**
 * Reflector
 *
 * @author ryan131
 * @since Aug 13, 2013, 5:05:17 PM
 */
public class Reflector {

  public static Field getField(Class<?> clazz, String name) throws NoSuchFieldException {
    Field field = null;
    try {
      field = clazz.getDeclaredField(name);
    } catch (NoSuchFieldException nsfe) {
      Class<?> superclass = clazz.getSuperclass();
      if (superclass == null) {
        throw nsfe;
      }
      return getField(superclass, name);
    }
    if (field == null) {
      throw new NullPointerException("Unable to get the field \"" + name + "\"" +
          "\" in class \"" + clazz);
    }
    return field;
  }

  private Object object;
  private Class<?> clazz;

  public Reflector(Object object) {
    this.object = object;
    this.clazz = object.getClass();
  }

  public ReflectedField field(String name) {
    return new ReflectedField(name).makeAccessible();
  }

  public class ReflectedField {

    private String name;
    private Field field;

    public ReflectedField(String name) {
      try {
        this.name = name;
        this.field = getField(clazz, name);
      } catch (NoSuchFieldException nsfe) {
        Throwables.propagateInRuntime(nsfe);
      }
    }

    public ReflectedField makeAccessible() throws SecurityException {
      if (!isAccessible()) {
        field.setAccessible(true);
      }
      return this;
    }

    public boolean isAccessible() {
      return field.isAccessible();
    }

    public void set(Object value) {
      try {
        field.set(object, value);
      } catch (IllegalAccessException iae) {
        Throwables.propagateInRuntime(iae);
      }
    }

    @SuppressWarnings("unchecked")
    public <T> T get() {
      Object value = null;
      try {
        value = field.get(object);
      } catch (IllegalAccessException iae) {
        Throwables.propagateInRuntime(iae);
      }
      if (value == null) {
        Throw.NullPointer("Unable to get the field \"" + name + "\".");
      }
      return (T) value;
    }

  }

}
