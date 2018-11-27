/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.collect;

import java.util.Collection;
import java.util.Iterator;

/**
 * ImmutableCollection
 *
 * @author ryan131
 * @since Jan 6, 2012, 10:35:53 PM
 */
public class ImmutableCollection<E> implements Collection<E>, Immutable {

  private final Collection<E> collection;

  public ImmutableCollection(Collection<E> collection) {
    if (collection == null) {
      throw new NullPointerException();
    }
    this.collection = collection instanceof Immutable
        ? ((ImmutableCollection<E>) collection).collection
        : collection;
  }

  @Override
  public int size() {
    return collection.size();
  }

  @Override
  public boolean isEmpty() {
    return collection.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return collection.contains(o);
  }

  @Override
  public ImmutableIterator<E> iterator() {
    return new ImmutableIterator<E>(collection.iterator());
  }

  @Override
  public Object[] toArray() {
    return collection.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return collection.toArray(a);
  }

  @Override
  public final boolean add(E e) {
    throw new UnsupportedOperationException();
  }

  @Override
  public final boolean remove(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return collection.containsAll(c);
  }

  @Override
  public final boolean addAll(Collection<? extends E> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public final boolean removeAll(Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public final boolean retainAll(Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public final void clear() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean equals(Object o) {
    return collection.equals(o);
  }

  @Override
  public int hashCode() {
    return collection.hashCode();
  }

  static class ImmutableIterator<E> implements Iterator<E>, Immutable {

    private final Iterator<E> iterator;

    ImmutableIterator(Iterator<E> iterator) {
      if (iterator == null) {
        throw new NullPointerException();
      }
      this.iterator = iterator instanceof Immutable
          ? ((ImmutableIterator<E>) iterator).iterator
          : iterator;
    }

    @Override
    public boolean hasNext() {
      return iterator.hasNext();
    }

    @Override
    public E next() {
      return iterator.next();
    }

    @Override
    public final void remove() {
      throw new UnsupportedOperationException();
    }

  }

}
