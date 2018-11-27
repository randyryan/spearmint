/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.config.util;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.spearmint.config.util.EnvironmentVariablesUtil;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Environment Variables Utility Test
 *
 * @author ryan131
 * @since May 3, 2016, 11:10:40 AM
 */
public class EnvironmentVariablesTest extends TestCase {

  public EnvironmentVariablesTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(EnvironmentVariablesTest.class);
  }

  public void testEnvironmentVariablesUtil() {

    // Test of environment variables

    Map<String, String> envVarMap = System.getenv();
    for (Entry<String, String> entry : envVarMap.entrySet()) {
      Assert.assertEquals(entry.getValue(), EnvironmentVariablesUtil.getVariable(entry.getKey()));
    }

    // Test of properties

    Assert.assertTrue(EnvironmentVariablesUtil.toProperties().size() > 0);
  }
}
