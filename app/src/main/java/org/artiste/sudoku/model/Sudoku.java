package org.artiste.sudoku.model;

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
}
