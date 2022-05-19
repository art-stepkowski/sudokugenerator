package org.artiste.sudoku.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Sudoku {
  private final int[][] cells = new int[9][9];

  public Sudoku() {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        cells[i][j] = 0;
      }
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        sb.append(cells[i][j]);
        if (j != 8) {
          sb.append(",");
        }
      }
      if (i != 8) {
        sb.append("\n");
      }
    }
    return sb.toString();
  }

  public void fillRow(int rowIndex,
                      int[] values) {
    cells[rowIndex] = values;
  }

  public int[] getRow(int rowIndex) {
    return cells[rowIndex];
  }

  public int[] getColumn(int colIndex) {
    return Arrays.stream(cells)
                 .mapToInt(arr -> arr[colIndex])
                 .toArray();
  }

  public Map<Integer, int[]> getRowsInRange(int firstRow,
                                            int numberOfRows) {
    Map<Integer, int[]> ret = new HashMap<>();
    int rowIndex = firstRow;
    for (int i = 0; i < numberOfRows; i++) {
      ret.put(rowIndex, cells[rowIndex++]);
    }
    return ret;
  }

  public Map<Integer, int[]> getColumnsInRange(int firstColumn,
                                               int numberOfColumns) {
    Map<Integer, int[]> ret = new HashMap<>();
    int colIndex = firstColumn;
    for (int i = 0; i < numberOfColumns; i++) {
      ret.put(colIndex, getColumn(colIndex++));
    }
    return ret;
  }

  public void fillColumn(int colIndex,
                         int[] values) {
    for (int i = 0; i < 9; i++) {
      cells[i][colIndex] = values[i];
    }
  }
}
