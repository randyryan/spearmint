/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.collect;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.Set;

/**
 * ImmutableMap
 *
 * @author ryan131
 * @since Jan 6, 2012, 10:14:10 PM
 */
public class ImmutableMap<K, V> implements Map<K, V>, Immutable {

  private final Map<K, V> map;

  public ImmutableMap(Map<K, V> map) {
    if (map == null) {
      throw new NullPointerException();
    }
    this.map = map instanceof Immutable ? ((ImmutableMap<K, V>) map).map : map;
  }

  @Override
  public int size() {
    return map.size();
  }

  @Override
  public boolean isEmpty() {
    return map.isEmpty();
  }

  @Override
  public boolean containsKey(Object key) {
    return map.containsKey(key);
  }

  @Override
  public boolean containsValue(Object value) {
    return map.containsValue(value);
  }

  @Override
  public V get(Object key) {
    return map.get(key);
  }

  @Override
  public final V put(K key, V value) {
    throw new UnsupportedOperationException();
  }

  @Override
  public final V remove(Object key) {
    throw new UnsupportedOperationException();
  }

  @Override
  public final void putAll(Map<? extends K, ? extends V> m) {
    throw new UnsupportedOperationException();
  }

  @Override
  public final void clear() {
    throw new UnsupportedOperationException();
  }

  @Override
  public final ImmutableSet<K> keySet() {
    return new ImmutableSet<K>(map.keySet());
  }

  @Override
  public final ImmutableCollection<V> values() {
    return new ImmutableCollection<V>(map.values());
  }

  @Override
  public final ImmutableSet<Map.Entry<K, V>> entrySet() {
    return new ImmutableMapEntrySet<K, V>(map.entrySet());
  }

  static class ImmutableMapEntry<K, V> implements Map.Entry<K, V>, Immutable {

    private final Map.Entry<K, V> entry;

    ImmutableMapEntry(Map.Entry<K, V> entry) {
      if (entry == null) {
        throw new NullPointerException();
      }
      this.entry = entry instanceof ImmutableMapEntry
          ? ((ImmutableMapEntry<K, V>) entry).entry
          : entry;
    }

    @Override
    public K getKey() {
      return entry.getKey();
    }

    @Override
    public V getValue() {
      return entry.getValue();
    }

    @Override
    public final V setValue(V value) {
      throw new UnsupportedOperationException();
    }

  }

  static class ImmutableMapEntrySet<K, V> extends ImmutableSet<Map.Entry<K, V>> {

    private final Set<Map.Entry<K, V>> entrySet;

    @SuppressWarnings("unchecked")
    ImmutableMapEntrySet(Set<Map.Entry<K, V>> entrySet) {
      super(entrySet);
      if (entrySet == null) {
        throw new NullPointerException();
      }
      this.entrySet = entrySet instanceof ImmutableMapEntrySet
          ? ((ImmutableMapEntrySet<K, V>) entrySet).entrySet
          : entrySet;
    }

    @Override
    public final ImmutableIterator<Map.Entry<K, V>> iterator() {
      return new ImmutableIterator<Map.Entry<K, V>>(entrySet.iterator()) {
        @Override
        public final ImmutableMapEntry<K, V> next() {
          return new ImmutableMapEntry<K, V>(super.next());
        }
      };
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object[] toArray() {
      Object[] array = entrySet.toArray();
      for (int i = 0; i < array.length; i++) {
        array[i] = new ImmutableMapEntry<K, V>((Map.Entry<K, V>) array[i]);
      }
      return array;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] array) {
      if (array == null) {
        throw new NullPointerException();
      }

      if (array.length < entrySet.size()) {
        array = (T[]) Array.newInstance(array.getClass().getComponentType(), entrySet.size());
      }
      if (array.length > entrySet.size()) {
        array[entrySet.size()] = null;
      }

      int index = 0;
      for (Map.Entry<K, V> entry : entrySet) {
        array[index++] = (T) new ImmutableMapEntry<K, V>(entry);
      }

      return array;
    }

  }

}
