/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.config;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * <b>{@code Property}</b>
 * <p>
 * Property defines the simplest idea of what a Property should be like. It is used with
 * {@link Configuration}. {@code Configuration} will provide complex abstraction and more
 * methods based on {@code Property}. The {@code Property} is simply used for storing properties.
 * <p>
 * <b>Design principles:</b>
 * <ol>
 * <li><b>Will not extend a collection</b><br>Unlike {@link Properties}, you will use a
 * {@code Property} as is. The methods weren't specifically designed for properties operations
 * will not be exposed on a {@code Property} object.</li>
 * <li><b>Is thread-safe</b><br>Whether this is thread-safe is still to be determined.</li>
 * <li><b>Has a default set of property</b> (as backup)<br>Searches a default {@code Property}
 * when the {@code key} isn't found. This feature will be made in {@link Configuration}.</li>
 * <li><b>Key and value are String-based</b><br>All values are read as String. When a value is
 * needed to be interpreted as another type, A utility program will come in (to convert it).</li>
 * <li><b>Work with plain file</b> (like <i>.properties</i>)<br>But should also has the ability
 * to work with XML or <i>.conf</i>, even HOCON.
 * </ol>
 *
 * @author ryan131
 * @since Apr 29, 2011, 1:58:24 PM
 */
public interface Property {

  /**
   * Returns the value of specified key.
   */
  String getProperty(String key);

  /**
   * Returns the value of the specified key or specified <i>default</i> value when the key
   * is absent.
   */
  String getProperty(String key, String defaultValue);

  /**
   * Sets the property with new value and returns the previous value associated with the key.
   */
  String setProperty(String key, String value);

  /**
   * Always adds a new property, if the given key exists, the value will become a list.
   */
  void addProperty(String key, String value);

  /**
   * Removes the property of specified key.
   */
  void removeProperty(String key);

  // View, these methods will work with PropertyUtil.

  Map<String, String> toMap();

  MapLike toMapLikeProperty();

  Properties toProperties();

  /**
   * Map-like Property provides {@link Map}-like behaviors for {@link Property}.
   *
   * @author ryan131
   * @since May 10, 2016, 9:28:53 PM
   */
  public interface MapLike extends Property {

    int size();

    void clear();

    boolean isEmpty();

    boolean containsKey(String key);

    boolean containsValue(String value);

    Set<String> propertyKeySet();

  }


}
