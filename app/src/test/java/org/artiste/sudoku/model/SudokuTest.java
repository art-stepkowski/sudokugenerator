package org.artiste.sudoku.model;

import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SudokuTest {

  @Test
  void should_create_empty_sudoku() {
    Sudoku sudoku = new Sudoku();
    for (int i = 0; i < 9; i++) {
      int[] row = sudoku.getRow(i);
      assertThat(row).containsOnly(0);
      assertThat(row).hasSize(9);
    }
  }

  @Test
  void should_fill_row() {
    Sudoku sudoku = new Sudoku();
    sudoku.fillRow(0, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    assertThat(sudoku.getRow(0)).containsSequence(1, 2, 3, 4, 5, 6, 7, 8, 9);
  }

  @Test
  void should_fill_column() {
    Sudoku sudoku = new Sudoku();
    sudoku.fillColumn(0, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    assertThat(sudoku.getColumn(0)).containsSequence(1, 2, 3, 4, 5, 6, 7, 8, 9);
  }

  @Test
  void should_get_columns_in_range() {
    Sudoku sudoku = new Sudoku();
    sudoku.fillColumn(2, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    sudoku.fillColumn(3, new int[]{2, 3, 4, 5, 6, 7, 8, 9, 1});
    sudoku.fillColumn(4, new int[]{3, 4, 5, 6, 7, 8, 9, 1, 2});
    Map<Integer, int[]> columnsInRange = sudoku.getColumnsInRange(2, 3);
    assertThat(columnsInRange).hasSize(3);
    assertThat(columnsInRange.keySet()).containsSequence(2, 3, 4);
    assertThat(columnsInRange.get(2)).containsSequence(1, 2, 3, 4, 5, 6, 7, 8, 9);
    assertThat(columnsInRange.get(3)).containsSequence(2, 3, 4, 5, 6, 7, 8, 9, 1);
    assertThat(columnsInRange.get(4)).containsSequence(3, 4, 5, 6, 7, 8, 9, 1, 2);
  }

  @Test
  void should_get_rows_in_range() {
    Sudoku sudoku = new Sudoku();
    sudoku.fillRow(2, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    sudoku.fillRow(3, new int[]{2, 3, 4, 5, 6, 7, 8, 9, 1});
    sudoku.fillRow(4, new int[]{3, 4, 5, 6, 7, 8, 9, 1, 2});
    Map<Integer, int[]> rowsInRange = sudoku.getRowsInRange(2, 3);
    assertThat(rowsInRange).hasSize(3);
    assertThat(rowsInRange.keySet()).containsSequence(2, 3, 4);
    assertThat(rowsInRange.get(2)).containsSequence(1, 2, 3, 4, 5, 6, 7, 8, 9);
    assertThat(rowsInRange.get(3)).containsSequence(2, 3, 4, 5, 6, 7, 8, 9, 1);
    assertThat(rowsInRange.get(4)).containsSequence(3, 4, 5, 6, 7, 8, 9, 1, 2);
  }

  @Test
  void should_get_box() {
    Sudoku sudoku = new Sudoku();
    sudoku.fillRow(0, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    sudoku.fillRow(1, new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3});
    sudoku.fillRow(2, new int[]{7, 8, 9, 1, 2, 3, 4, 5, 6});
    int[] box = sudoku.getBox(0);
    assertThat(box).containsSequence(1, 2, 3, 4, 5, 6, 7, 8, 9);
  }

  @Test
  void should_return_proper_string() {
    Sudoku sudoku = new Sudoku();
    String s = sudoku.toString();
    assertThat(s).isEqualTo("[0, 0, 0, 0, 0, 0, 0, 0, 0]\n" + "[0, 0, 0, 0, 0, 0, 0, 0, 0]\n" + "[0, 0, 0, 0, 0, 0, 0, 0, 0]\n" + "[0, 0, 0, 0, 0, 0, 0, 0, 0]\n" + "[0, 0, 0, 0, 0, 0, 0, 0, 0]\n" + "[0, 0, 0, 0, 0, 0, 0, 0, 0]\n" + "[0, 0, 0, 0, 0, 0, 0, 0, 0]\n" + "[0, 0, 0, 0, 0, 0, 0, 0, 0]\n" + "[0, 0, 0, 0, 0, 0, 0, 0, 0]");
  }

  @Test
  void should_find_first_empty_cell() {
    Sudoku sudoku = new Sudoku();
    sudoku.fillRow(0, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    sudoku.fillRow(1, new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3});
    sudoku.fillRow(2, new int[]{7, 8, 9, 1, 0, 3, 4, 5, 6});
    CellCoordinates firstEmptyCell = sudoku.findFirstEmptyCell();
    assertThat(firstEmptyCell).hasFieldOrPropertyWithValue("row", 2)
                              .hasFieldOrPropertyWithValue("column", 4);
  }

  @Test
  void should_prepare_sudoku_copy() {
    Sudoku sudoku = new Sudoku();
    sudoku.fillRow(0, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    Sudoku newSudoku = new Sudoku(sudoku);
    sudoku.fillRow(0, new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1});
    assertThat(newSudoku.getRow(0)).containsSequence(1, 2, 3, 4, 5, 6, 7, 8, 9);
  }
}