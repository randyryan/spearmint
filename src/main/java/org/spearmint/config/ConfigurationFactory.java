/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.config;

/**
 * Configuration Factory
 *
 * @author ryan131
 * @since May 11, 2011, 9:09:56 PM
 */
public interface ConfigurationFactory {

  Configuration getConfiguration(String configurationName);

}
