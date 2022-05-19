package org.artiste.sudoku.model;

import java.util.*;

public class Sudoku {
  private static final Map<Integer, List<Integer>> BOXES_START_CELLS = new HashMap<>();
  private final int[][] cells = new int[9][9];

  static {
    BOXES_START_CELLS.put(0, List.of(0, 0));
    BOXES_START_CELLS.put(1, List.of(3, 0));
    BOXES_START_CELLS.put(2, List.of(6, 0));
    BOXES_START_CELLS.put(3, List.of(0, 3));
    BOXES_START_CELLS.put(4, List.of(3, 3));
    BOXES_START_CELLS.put(5, List.of(6, 3));
    BOXES_START_CELLS.put(6, List.of(0, 6));
    BOXES_START_CELLS.put(7, List.of(3, 6));
    BOXES_START_CELLS.put(8, List.of(6, 6));
  }

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

  public int[] getBox(int boxIndex) {
    List<Integer> ret = new ArrayList<>();
    List<Integer> boxStartCells = BOXES_START_CELLS.get(boxIndex);
    int startRow = boxStartCells.get(0);
    int startColumn = boxStartCells.get(1);
    for (int i = startRow; i < startRow + 3; i++) {
      for (int j = startColumn; j < startColumn + 3; j++) {
        ret.add(cells[i][j]);
      }
    }
    return ret.stream()
              .mapToInt(i -> i)
              .toArray();
  }
}
