/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.collect;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * ImmutableList
 *
 * @author ryan131
 * @since Jan 6, 2012, 10:58:34 PM
 */
public class ImmutableList<E> extends ImmutableCollection<E> implements List<E> {

  private final List<E> list;

  public ImmutableList(List<E> list) {
    super(list);
    if (list == null) {
      throw new NullPointerException();
    }
    this.list = list instanceof Immutable ? ((ImmutableList<E>) list).list: list;
  }

  @Override
  public final boolean addAll(int index, Collection<? extends E> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public E get(int index) {
    return list.get(index);
  }

  @Override
  public final E set(int index, E element) {
    throw new UnsupportedOperationException();
  }

  @Override
  public final void add(int index, E element) {
    throw new UnsupportedOperationException();
  }

  @Override
  public final E remove(int index) {
    throw new UnsupportedOperationException();
  }

  @Override
  public int indexOf(Object o) {
    return list.indexOf(o);
  }

  @Override
  public int lastIndexOf(Object o) {
    return list.lastIndexOf(o);
  }

  @Override
  public final ImmutableListIterator<E> listIterator() {
    return new ImmutableListIterator<E>(list.listIterator());
  }

  @Override
  public final ImmutableListIterator<E> listIterator(int index) {
    return new ImmutableListIterator<E>(list.listIterator(index));
  }

  @Override
  public final ImmutableList<E> subList(int fromIndex, int toIndex) {
    return new ImmutableList<E>(list.subList(fromIndex, toIndex));
  }

  static class ImmutableListIterator<E> extends ImmutableCollection.ImmutableIterator<E> implements ListIterator<E> {

    private final ListIterator<E> listIterator;

    ImmutableListIterator(ListIterator<E> listIterator) {
      super(listIterator);
      if (listIterator == null) {
        throw new NullPointerException();
      }
      this.listIterator = listIterator instanceof ImmutableListIterator
          ? ((ImmutableListIterator<E>) listIterator).listIterator
          : listIterator;
    }

    @Override
    public boolean hasPrevious() {
      return listIterator.hasPrevious();
    }

    @Override
    public E previous() {
      return listIterator.previous();
    }

    @Override
    public int nextIndex() {
      return listIterator.nextIndex();
    }

    @Override
    public int previousIndex() {
      return listIterator.previousIndex();
    }

    @Override
    public final void set(E e) {
      throw new UnsupportedOperationException();
    }

    @Override
    public final void add(E e) {
      throw new UnsupportedOperationException();
    }

  }

}
