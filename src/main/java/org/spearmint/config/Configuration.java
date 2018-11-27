/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.config;

import java.util.List;

/**
 * <b>{@code Configuration}</b>
 * <p>
 * While {@link Property} represents a single set of properties, a {@code Configuration}
 * contains settings of a software component, which may contains multiple {@code Property}s.
 *
 * @author ryan131
 * @since May 11, 2011, 3:39:44 PM
 */
public interface Configuration {

  String getProperty(String key);

  String[] getArray(String key);

  List<String> getList(String key);

  List<Property> getPropertyList();

  Property getEffectiveProperty();

}
