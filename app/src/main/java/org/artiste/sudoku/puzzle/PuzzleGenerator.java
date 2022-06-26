package org.artiste.sudoku.puzzle;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.artiste.sudoku.Level;
import org.artiste.sudoku.model.CellCoordinates;
import org.artiste.sudoku.model.Sudoku;
import org.artiste.sudoku.neighbours.NeighboursFinder;

public class PuzzleGenerator {
  private static final Random RANDOM = new Random();

  public Sudoku generate(Sudoku fullSudoku,
                         Level level) {
    Sudoku puzzle = new Sudoku(fullSudoku);
    for (int i = 0; i < 9; i++) {
      Set<Integer> indexes = new HashSet<>();
      int numberOfTries = 0;
      do {
        randomIndexes(indexes);
        numberOfTries++;
      } while (checkIndexes(puzzle, i, indexes) && numberOfTries < 100);
      for (Integer index : indexes) {
        puzzle.fillCell(i, index, 0);
      }
    }
    RANDOM.nextInt(9);
    return puzzle;
  }

  private boolean checkIndexes(Sudoku puzzle,
                               int row,
                               Set<Integer> indexes) {
    indexes.removeIf(i -> NeighboursFinder.getNumberOfEmptyNeighbours(puzzle, new CellCoordinates(row, i)) > 3);
    return indexes.size() < 3;
  }

  private void randomIndexes(Set<Integer> indexes) {
    while (true) {
      indexes.add(RANDOM.nextInt(9));
      if (indexes.size() >= 3) {
        return;
      }
    }
  }
}
