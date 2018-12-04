/**
 * Copyright (c) 2010-2018 Ryan Li Wan. All rights reserved.
 */

package org.spearmint.collect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * An implementation-agnostic, skeletal TableDriver implementation.
 *
 * @author ryan131
 * @since Jan 11, 2014, 4:46:35 PM
 */
@SuppressWarnings("unchecked")
public abstract class AbstractTableDriver<R, C, V> implements TableDriver<R, C, V> {

  // XXX Row

  @Override
  public Row<C, V> row(int rowIndex) {
    return model().row(rowIndex);
  }

  @Override
  public Row<C, V> row(R rowKey) {
    return model().row(config().rowIndex(rowKey));
  }

  @Override
  public Row<C, V> row(int columnIndex, V value) {
    int rowIndex = 1;
    while (rowIndex <= model().rowCount()) {
      if (column(columnIndex).cell(rowIndex).value().equals(value)) {
        break;
      }
      rowIndex++;
    }
    if (rowIndex > model().rowCount()) {
      throw new TableAccessException.NoSuchValue(
          "The value \"" + value + "\" cannot be found in column \"" + config().columnKey(columnIndex) + "\".");
    }
    return model().row(rowIndex);
  }

  @Override
  public Row<C, V> row(C columnKey, V value) {
    return row(config().columnIndex(columnKey), value);
  }

  @Override
  public Row<C, V> row(int columnIndex1, V value1, int columnIndex2, V value2) {
    return model().row(columnIndex1, value1, columnIndex2, value2);
  }

  @Override
  public Row<C, V> row(C columnKey1, V value1, C columnKey2, V value2) {
    return model().row(model().columnIndex(columnKey1), value1, model().columnIndex(columnKey2), value2);
  }

  @Override
  public List<? extends Row<C, V>> rows(int... rowIndexes) {
    return model().rows(rowIndexes);
  }

  @Override
  public List<? extends Row<C, V>> rows(R... rowKeys) {
    int[] rowIndexes = new int[rowKeys.length];
    int rowIndex = 0;
    for (R rowKey : rowKeys) {
      rowIndexes[rowIndex++] = config().rowIndex(rowKey);
    }
    return rows(rowIndexes);
  }

  // XXX Column

  @Override
  public Column<R, V> column(int columnIndex) {
    return model().column(columnIndex);
  }

  @Override
  public Column<R, V> column(C columnKey) {
    return column(config().columnIndex(columnKey));
  }

  @Override
  public Column<R, V> column(int rowIndex, V value) {
    int columnIndex = 1;
    while (rowIndex <= model().columnCount()) {
      if (row(rowIndex).cell(columnIndex).value().equals(value)) {
        break;
      }
      columnIndex++;
    }
    if (columnIndex > model().columnCount()) {
      throw new TableAccessException.NoSuchValue(
          "The value \"" + value + "\" cannot be found in row \"" + config().rowKey(rowIndex) + "\".");
    }
    return model().column(columnIndex);
  }

  @Override
  public Column<R, V> column(R rowKey, V value) {
    return column(config().rowIndex(rowKey), value);
  }

  @Override
  public List<? extends Column<R, V>> columns(int... columnIndexes) {
    return model().columns(columnIndexes);
  }

  @Override
  public List<? extends Column<R, V>> columns(C... columnKeys) {
    int[] columnIndexes = new int[columnKeys.length];
    int columnIndex = 0;
    for (C columnKey : columnKeys) {
      columnIndexes[columnIndex++] = config().columnIndex(columnKey);
    }
    return columns(columnIndexes);
  }

  // XXX Need update

  @Override
  public abstract AbstractConfig config();

  @Override
  public abstract Snapshot snapshot();

  // XXX TableModel

  public abstract AbstractTableModel model();

  protected abstract class AbstractTableModel implements TableModel<R, C, V> {

    @Override
    public abstract Row<C, V> row(int rowIndex);

    @Override
    public abstract Row<C, V> row(int columnIndex, V value);

    @Override
    public abstract Row<C, V> row(int columnIndex1, V value1, int columnIndex2, V value2);

    @Override
    public ImmutableList<? extends Row<C, V>> rows(int... rowIndexes) {
      List<Row<C, V>> rows = Lists.newArrayList();
      for (int rowIndex : rowIndexes) {
        rows.add(row(rowIndex));
      }
      return Lists.immutableList(rows);
    }

    @Override
    public abstract int rowCount();

    @Override
    public abstract int rowIndex(R rowKey);

    @Override
    public abstract R rowKey(int rowIndex);

    @Override
    public abstract Column<R, V> column(int columnIndex);

    @Override
    public ImmutableList<? extends Column<R, V>> columns(int... columnIndexes) {
      List<Column<R, V>> columns = Lists.newArrayList();
      for (int columnIndex : columnIndexes) {
        columns.add(column(columnIndex));
      }
      return Lists.immutableList(columns);
    }

    @Override
    public abstract int columnCount();

    @Override
    public abstract int columnIndex(C columnKey);

    @Override
    public abstract C columnKey(int columnIndex);

  }

  protected abstract class AbstractRow implements Row<C, V> {

    @Override
    public abstract Cell<V> cell(int columnIndex);

    @Override
    public Cell<V> cell(C columnKey) {
      return cell(config().columnIndex(columnKey));
    }

    @Override
    public ImmutableList<Cell<V>> cells() {
      // TODO code a utility like org.spearmint.collect.Lists.integerList() for array
      int[] columnIndexes = new int[config().columnCount()];
      for (int index = 0; index < config().columnCount(); index++) {
        columnIndexes[index] = index + 1;
      }
      return cells(columnIndexes);
    }

    @Override
    public ImmutableList<Cell<V>> cells(int... columnIndexes) {
      List<Cell<V>> cells = Lists.newArrayList();
      for (int columnIndex : columnIndexes) {
        cells.add(cell(columnIndex));
      }
      return Lists.immutableList(cells);
    }

    @Override
    public abstract List<Cell<V>> cells(C... columnKeys);

    @Override
    public abstract int index();

    @Override
    public abstract R key();

  }

  protected abstract class AbstractColumn implements Column<R, V> {

    @Override
    public abstract Cell<V> cell(int rowIndex);

    @Override
    public Cell<V> cell(R rowKey) {
      return cell(config().rowIndex(rowKey));
    }

    @Override
    public List<Cell<V>> cells() {
      // TODO code a utility like org.spearmint.collect.Lists.integerList() for array
      int[] rowIndexes = new int[config().rowCount()];
      for (int index = 0; index < config().rowCount(); index++) {
        rowIndexes[index] = index + 1;
      }
      return cells(rowIndexes);
    }

    @Override
    public List<Cell<V>> cells(int... rowIndexes) {
      List<Cell<V>> cells = Lists.newArrayList();
      for (int rowIndex : rowIndexes) {
        cells.add(cell(rowIndex));
      }
      return Lists.immutableList(cells);
    }

    @Override
    public abstract List<Cell<V>> cells(R... rowKeys);

    @Override
    public abstract int index();

    @Override
    public abstract C key();

  }

  protected abstract class AbstractCell implements Cell<V> {

    protected Row<C, V> row;       // By specifying a row and a column index
    protected int columnIndex;

    protected Column<R, V> column; // By specifying a column and a row index
    protected int rowIndex;

    protected AbstractCell(Row<C, V> row, int columnIndex) {
      if (columnIndex < 1) {
        throw new IllegalArgumentException("The column index cannot be less than 1.");
      }
      this.row = row;
      this.columnIndex = columnIndex;
    }

    protected AbstractCell(Column<R, V> column, int rowIndex) {
      if (rowIndex < 1) {
        throw new IllegalArgumentException("The row index cannot be less than 1.");
      }
      this.column = column;
      this.rowIndex = rowIndex;
    }

    @Override
    public abstract V value();

    @Override
    public int rowIndex() {
      return row != null ? row.index() : rowIndex;
    }

    @Override
    public abstract R rowKey();

    @Override
    public abstract AbstractRow row();

    @Override
    public int columnIndex() {
      return column != null ? column.index() : columnIndex;
    }

    @Override
    public abstract C columnKey();

    @Override
    public abstract AbstractColumn column();

    @Override
    public abstract String xpath();

  }

  // XXX Need update

  protected abstract class AbstractConfig implements Config<R, C>, Config.Management<R, C> {

    private int rowCount;
    private int columnCount;

    @Override
    public abstract int rowIndex(R rowKey);

    @Override
    public abstract R rowKey(int rowIndex);

    @Override
    public int rowCount() {
      return rowCount;
    }

    @Internal
    protected void setRowCount(int rowCount) {
      if (rowCount < -1) {
        throw new IllegalArgumentException();
      }
      this.rowCount = rowCount;
    }

    @Override
    public abstract int columnIndex(C columnKey);

    @Override
    public abstract C columnKey(int columnIndex);

    @Override
    public int columnCount() {
      return columnCount;
    }

    @Internal
    protected void setColumnCount(int columnCount) {
      if (columnCount < -1) {
        throw new IllegalArgumentException();
      }
      this.columnCount = columnCount;
    }

    // XXX Need update

    @Override
    public Management<R, C> manage() {
      return this;
    }

    // Management

    // rowKeys is left to sub-classes to determine
    // columnKeys is left to sub-classes to determine
    private List<R> cachedRowKeys;
    private List<C> cachedColumnKeys;

    private Snapshot.Mode snapshotMode = Snapshot.Mode.DISABLE; // Disable by default

    protected AbstractConfig() {
      cachedRowKeys = Lists.newArrayList();
      cachedColumnKeys = Lists.newArrayList();
    }

    @Internal
    protected abstract void setRowKey(R rowKey, int rowIndex);

    @Override
    public void setRowKeys(R... rowKeys) {
      setRowKeys(Arrays.asList(rowKeys));
    }

    @Override
    public void setRowKeys(Collection<R> rowKeys) {
      int index = 1;
      for (R rowKey : rowKeys) {
        setRowKey(rowKey, index++);
      }
      setRowCount(rowKeys.size());
    }

    @Override
    public abstract List<R> rowKeys();

    @Internal
    void setCachedRowKey(R rowKey) {
      if (rowKeys().indexOf(rowKey) == -1) {
        throw new TableAccessException.NoSuchKey(
            "The specified row key does not exist in the current table.");
      }
      cachedRowKeys.add(rowKey);
    }

    @Override
    public void setCachedRowKeys(R... cachedRowKeys) {
      setCachedRowKeys(Arrays.asList(cachedRowKeys));
    }

    @Override
    public void setCachedRowKeys(Collection<R> cachedRowKeys) {
      if (!this.cachedRowKeys.isEmpty()) {
        this.cachedRowKeys.clear();
      }
      for (R rowKey : cachedRowKeys) {
        setCachedRowKey(rowKey);
      }
    }

    @Override
    public List<R> cachedRowKeys() {
      return Lists.immutableList(cachedRowKeys);
    }

    @Internal
    protected abstract void setColumnKey(C columnKey, int columnIndex);

    @Override
    public void setColumnKeys(C... columnKeys) {
      setColumnKeys(Arrays.asList(columnKeys));
    }

    @Override
    public void setColumnKeys(Collection<C> columnKeys) {
      int index = 1;
      for (C columnKey : columnKeys) {
        setColumnKey(columnKey, index++);
      }
      setColumnCount(columnKeys.size());
    }

    @Override
    public abstract List<C> columnKeys();

    @Internal
    void setCachedColumnKey(C columnKey) {
      if (columnKeys().indexOf(columnKey) == -1) {
        throw new TableAccessException.NoSuchKey(
            "The specified column key does not exist in the current table.");
      }
      cachedColumnKeys.add(columnKey);
    }

    @Override
    public void setCachedColumnKeys(C... cachedColumnKeys) {
      setCachedColumnKeys(Arrays.asList(cachedColumnKeys));
    }

    @Override
    public void setCachedColumnKeys(Collection<C> cachedColumnKeys) {
      if (!this.cachedColumnKeys.isEmpty()) {
        this.cachedColumnKeys.clear();
      }
      for (C columnKey : cachedColumnKeys) {
        setCachedColumnKey(columnKey);
      }
    }

    @Override
    public List<C> cachedColumnKeys() {
      return Lists.immutableList(cachedColumnKeys);
    }

    @Override
    public Snapshot.Mode snapshotMode() {
      return snapshotMode;
    }

    @Override
    public void setSnapshotMode(Snapshot.Mode snapshotMode) {
      if (snapshotMode == null) {
        throw new NullPointerException();
      }
      this.snapshotMode = snapshotMode;
    }

    // java.lang.Object

    @Override
    public boolean equals(Object object) {
      if (this == object) {
        return true;
      }
      if (object instanceof AbstractTableDriver.AbstractConfig) {
        AbstractConfig other = (AbstractConfig) object;
        return rowCount == other.rowCount && columnCount == other.columnCount &&
            rowKeys().equals(other.rowKeys()) && columnKeys().equals(other.columnKeys()) &&
            cachedRowKeys.equals(other.cachedRowKeys) && cachedColumnKeys.equals(other.cachedColumnKeys);
      }
      return false;
    }
 
    @Override
    public String toString() {
      String rows = "{";
      for (R row : rowKeys()) {
        rows += "[" + rowIndex(row) + "=" + row + "], ";
      }
      rows += "}";
      rows = rows.replace("], }", "]}");
      String columns = "{";
      for (C column : columnKeys()) {
        columns += "[" + columnIndex(column) + "=" + column + "], ";
      }
      columns += "}";
      columns = columns.replace("], }", "]}");
      return "Rows: " + rows + "\n" + "Columns: " + columns;
    }

  }

  protected abstract class AbstractSnapshot implements Snapshot {

    @Override
    public abstract String get(int rowIndex, int columnIndex);

    protected abstract void set(int rowIndex, int columnIndex, String value);

    protected abstract List<String> getRow(int rowIndex);

    protected abstract void setRow(int rowIndex, List<String> row);

    protected abstract List<String> getColumn(int columnIndex);

    protected abstract void setColumn(int columnIndex, List<String> column);

    @Override
    public abstract void invalidate();

    @Override
    public abstract boolean equals(Object object);

  }

  @Documented
  @Target({ElementType.METHOD, ElementType.TYPE})
  @Retention(RetentionPolicy.CLASS)
  public @interface Internal {}

}
