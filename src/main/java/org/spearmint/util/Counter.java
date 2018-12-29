/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.util;

/**
 * Counter
 *
 * @author ryan131
 * @since Dec 29, 2018, 9:36:12 AM
 */
public class Counter implements Comparable<Counter> {

  public static Counter countTo(long countTo) {
    return new Counter("", countTo);
  }

  private final String name;
  private final long countTo;
  private long count;

  protected Counter(String name) {
    this(name, Long.MAX_VALUE, 0);
  }

  protected Counter(String name, long countTo) {
    this(name, countTo, 0);
  }

  protected Counter(String name, long countTo, long countFrom) {
    this.name = name;
    this.count = countFrom;
    this.countTo = countTo;
  }

  public String getName() {
    return name;
  }

  public void increment() {
    count++;
  }

  public void decrement() {
    count--;
  }

  public void count() {
    if (isOnTarget()) {
      throw new IllegalStateException("Count " + countTo + " has already been reached.");
    }
    if (count < countTo) {
      increment();
    } else {
      decrement();
    }
  }

  public long getCount() {
    return count;
  }

  public long getCountTo() {
    return countTo;
  }

  public boolean isOnTarget() {
    return count == countTo;
  }

  @Override
  public int compareTo(Counter o) {
    if (count < o.count) {
      return -1;
    }
    if (count == o.count) {
      return 0;
    }
    return 1;
  }

}
