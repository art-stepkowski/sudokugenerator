package org.artiste.sudoku.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SudokuTest {

  @Test
  void should_create_empty_sudoku() {
    Sudoku sudoku = new Sudoku();
    for (int i = 0; i < 9; i++) {
      int[] row = sudoku.getRow(i);
      assertThat(row).containsOnly(0);
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
}