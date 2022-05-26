package org.artiste.sudoku.puzzle;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.artiste.sudoku.model.CellCoordinates;
import org.artiste.sudoku.model.Sudoku;
import org.artiste.sudoku.neighbours.NeighboursFinder;

public class PuzzleValidator {
  public boolean validate(Sudoku fullSudoku,
                          Sudoku puzzle) {
    int maxEmptyNeighbours = 0;
    CellCoordinates maxEmptyNeighboursCoordinates = new CellCoordinates(0, 0);
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        CellCoordinates coordinates = new CellCoordinates(i, j);
        int cellValue = puzzle.getCellValue(coordinates);
        if (cellValue == 0) {
          int emptyNeighbours = NeighboursFinder.getNumberOfEmptyNeighbours(puzzle, coordinates);
          if (emptyNeighbours > maxEmptyNeighbours && NeighboursFinder.findDistinctNeighbours(puzzle, coordinates)
                                                                      .size() < 9) {
            maxEmptyNeighbours = emptyNeighbours;
            maxEmptyNeighboursCoordinates = coordinates;
          }
        }
      }
    }
    if (maxEmptyNeighbours == 0) {
      return false;
    }
    int cellValue = fullSudoku.getCellValue(maxEmptyNeighboursCoordinates);
    Set<Integer> distinctNeighbours = NeighboursFinder.findDistinctNeighbours(puzzle, maxEmptyNeighboursCoordinates);
    distinctNeighbours.add(cellValue);
    List<Integer> availableNumbers = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                                              .filter(i -> !distinctNeighbours.contains(i))
                                              .boxed()
                                              .collect(Collectors.toList());
    for (Integer availableNumber : availableNumbers) {
      Sudoku testSudoku = new Sudoku(puzzle);
      testSudoku.fillCell(maxEmptyNeighboursCoordinates, availableNumber);
      if (new Solver().solve(testSudoku)) {
        return false;
      }
    }
    return true;
  }
}
