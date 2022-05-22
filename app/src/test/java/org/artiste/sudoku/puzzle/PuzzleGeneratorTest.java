package org.artiste.sudoku.puzzle;


import java.util.Arrays;
import org.artiste.sudoku.generator.SmartGenerator;
import org.artiste.sudoku.model.Sudoku;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PuzzleGeneratorTest {
  @Test
  void should_prepare_puzzle_with_proper_number_of_empty_cells() {
    Sudoku sudoku = new SmartGenerator().generate();
    Sudoku puzzle = new PuzzleGenerator().generate(sudoku);
    for (int i = 0; i < 9; i++) {
      int[] row = puzzle.getRow(i);
      assertThat(Arrays.stream(row)
                       .filter(x -> x == 0)).hasSizeLessThan(4);
    }
    for (int i = 0; i < 9; i++) {
      int[] col = puzzle.getColumn(i);
      assertThat(Arrays.stream(col)
                       .filter(x -> x == 0)).hasSizeLessThan(5);
    }
  }
}