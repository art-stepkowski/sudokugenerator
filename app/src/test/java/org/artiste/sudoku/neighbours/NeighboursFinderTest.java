package org.artiste.sudoku.neighbours;

import org.artiste.sudoku.generator.SmartGenerator;
import org.artiste.sudoku.model.CellCoordinates;
import org.artiste.sudoku.model.Sudoku;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NeighboursFinderTest {
  @Test
  void should_find_proper_number_of_empty_neighbours() {
    Sudoku sudoku = new SmartGenerator().generate();
    sudoku.fillCell(0, 0, 0);
    sudoku.fillCell(1, 1, 0);
    sudoku.fillCell(2, 2, 0);
    sudoku.fillCell(0, 2, 0);
    sudoku.fillCell(0, 3, 0);
    sudoku.fillCell(2, 0, 0);
    sudoku.fillCell(3, 0, 0);
    int actual = NeighboursFinder.getNumberOfEmptyNeighbours(sudoku, new CellCoordinates(0, 0));
    assertThat(actual).isEqualTo(6);
  }
}