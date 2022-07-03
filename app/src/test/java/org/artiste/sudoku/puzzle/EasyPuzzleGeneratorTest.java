package org.artiste.sudoku.puzzle;


import org.artiste.sudoku.generator.SmartGenerator;
import org.artiste.sudoku.model.Sudoku;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EasyPuzzleGeneratorTest {
  @Test
  void should_prepare_puzzle_with_proper_number_of_empty_cells() {
    Sudoku sudoku = new SmartGenerator().generate();
    EasyPuzzleGenerator generator = new EasyPuzzleGenerator();
    Sudoku puzzle = generator.generate(sudoku);
    assertThat(puzzle.getNumberOfEmptyCells()).isGreaterThanOrEqualTo(generator.getMinNumberOfEmptyCells())
                                              .isLessThanOrEqualTo(generator.getMaxNumberOfEmptyCells());
  }
}