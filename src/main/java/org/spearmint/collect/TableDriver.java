/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.collect;

import java.util.Collection;
import java.util.List;

/**
 * TableDriver
 *
 * @author ryan131
 * @since Jun 28, 2014, 2:49:00 PM
 */
@SuppressWarnings("unchecked")
public interface TableDriver<R, C, V> {

  // XXX Row

  Row<C, V> row(int rowIndex);

  Row<C, V> row(R rowKey);

  Row<C, V> row(int columnIndex, V value);

  Row<C, V> row(C columnKey, V value);

  Row<C, V> row(int columnIndex1, V value1, int columnIndex2, V value2);

  Row<C, V> row(C columnKey1, V value1, C columnKey2, V value2);

  List<? extends Row<C, V>> rows(int... rowIndexes);

  List<? extends Row<C, V>> rows(R... rowKeys);

  // XXX Column

  Column<R, V> column(int columnIndex);

  Column<R, V> column(C columnKey);

  Column<R, V> column(int rowIndex, V value);

  Column<R, V> column(R rowKey, V value);

  List<? extends Column<R, V>> columns(int... columnIndexes);

  List<? extends Column<R, V>> columns(C... columnKeys);

  // XXX Need update

  Config<R, C> config();                                      // XXX

  Snapshot snapshot();                                        // XXX

  // XXX TableModel

  TableModel<R, C, V> model();

  interface TableModel<R, C, V> {

    Row<C, V> row(int rowIndex);

    Row<C, V> row(int columnIndex, V value);

    Row<C, V> row(int columnIndex1, V value1, int columnIndex2, V value2);

    List<? extends Row<C, V>> rows(int... rowIndexes);

    int rowCount();

    int rowIndex(R rowKey);

    R rowKey(int rowIndex);

    Column<R, V> column(int columnIndex);

    List<? extends Column<R, V>> columns(int... columnIndexes);

    int columnCount();

    int columnIndex(C columnKey);

    C columnKey(int columnIndex);

  }

  interface Row<C, V> {

    Cell<V> cell(int columnIndex);

    Cell<V> cell(C columnKey);
 
    List<? extends Cell<V>> cells();

    List<? extends Cell<V>> cells(int... columnIndexes);

    List<? extends Cell<V>> cells(C... columnKeys);

    int index();

    Object key(); // Don't want to declare R

  }

  interface Column<R, V> {

    Cell<V> cell(int rowIndex);

    Cell<V> cell(R rowKey);

    List<? extends Cell<V>> cells();

    List<? extends Cell<V>> cells(int... rowIndexes);

    List<? extends Cell<V>> cells(R... rowKeys);

    int index();

    Object key(); // Don't want to declare C

  }

  interface Cell<V> {

    V value();

    int rowIndex();

    Object rowKey(); // Don't want to declare R

    Row<?, V> row(); // Don't want to declare C

    int columnIndex();

    Object columnKey(); // Don't want to declare C

    Column<?, V> column(); // Don't want to declare R

    String xpath();

  }

  // XXX Need update

  interface Config<R, C> {

    R rowKey(int rowIndex);

    int rowIndex(R rowKey);

    int rowCount();

    C columnKey(int columnIndex);

    int columnIndex(C columnKey);

    int columnCount();

    // XXX Need update

    Management<R, C> manage();

    interface Management<R, C> {

      List<R> rowKeys();

      void setRowKeys(R... rowKeys);

      void setRowKeys(Collection<R> rowKeys);

      List<R> cachedRowKeys();

      void setCachedRowKeys(R... cachedRowKeys);

      void setCachedRowKeys(Collection<R> cachedRowKeys);

      List<C> columnKeys();

      void setColumnKeys(C... columnKeys);

      void setColumnKeys(Collection<C> columnKeys);

      List<C> cachedColumnKeys();

      void setCachedColumnKeys(C... cachedColumnKeys);

      void setCachedColumnKeys(Collection<C> cachedColumnKeys);

      Snapshot.Mode snapshotMode();

      void setSnapshotMode(Snapshot.Mode snapshotMode);

    }

  }

  interface Snapshot {

    public enum Mode {

      DISABLE,
      SPARSE,
      DENSE;

    }

    String get(int rowIndex, int columnIndex);

    void invalidate();

    Snapshot copy();

  }

}
