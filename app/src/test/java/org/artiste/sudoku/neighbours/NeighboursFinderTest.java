package org.artiste.sudoku.neighbours;

import java.util.List;
import java.util.stream.Stream;
import org.artiste.sudoku.generator.SmartGenerator;
import org.artiste.sudoku.model.CellCoordinates;
import org.artiste.sudoku.model.Sudoku;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class NeighboursFinderTest {
  @ParameterizedTest
  @MethodSource("prepareDataForFindNumberOfEmptyNeighbours")
  void should_find_proper_number_of_empty_neighbours(List<CellCoordinates> emptyCells,
                                                     CellCoordinates targetCell,
                                                     int expectedNumberOfEmptyNeighbours) {
    Sudoku sudoku = new SmartGenerator().generate();
    emptyCells.forEach(c -> sudoku.fillCell(c.getRow(), c.getColumn(), 0));
    int actual = NeighboursFinder.getNumberOfEmptyNeighbours(sudoku, targetCell);
    assertThat(actual).isEqualTo(expectedNumberOfEmptyNeighbours);
  }

  private static Stream<Arguments> prepareDataForFindNumberOfEmptyNeighbours() {
    return Stream.of(Arguments.of(List.of(new CellCoordinates(1, 1),
                                          new CellCoordinates(2, 2),
                                          new CellCoordinates(1, 2),
                                          new CellCoordinates(1, 3),
                                          new CellCoordinates(2, 1),
                                          new CellCoordinates(3, 1)), new CellCoordinates(1, 1), 5),
                     Arguments.of(List.of(new CellCoordinates(1, 1)), new CellCoordinates(1, 1), 0),
                     Arguments.of(List.of(new CellCoordinates(3, 3), new CellCoordinates(3, 4)),
                                  new CellCoordinates(3, 3),
                                  1));
  }
}