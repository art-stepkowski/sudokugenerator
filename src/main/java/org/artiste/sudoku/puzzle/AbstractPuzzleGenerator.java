package org.artiste.sudoku.puzzle;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.artiste.sudoku.model.CellCoordinates;
import org.artiste.sudoku.model.Sudoku;

public abstract class AbstractPuzzleGenerator implements PuzzleGenerator {
  private static final Random RANDOM = new Random();

  @Override
  public Sudoku generate(Sudoku fullSudoku) {
    int numberOfEmptyFields = getNumberFromRange(getMinNumberOfEmptyCells(), getMaxNumberOfEmptyCells());
    Sudoku puzzle = new Sudoku(fullSudoku);
    Set<CellCoordinates> emptyCells = getRandomCoordinates(numberOfEmptyFields);
    emptyCells.forEach(c -> puzzle.fillCell(c, 0));
    return puzzle;
  }

  private Set<CellCoordinates> getRandomCoordinates(int numberOfEmptyFields) {
    Set<CellCoordinates> ret = new HashSet<>();
    do {
      ret.add(new CellCoordinates(RANDOM.nextInt(9), RANDOM.nextInt(9)));
    } while (ret.size() != numberOfEmptyFields);
    return ret;
  }

  private int getNumberFromRange(int min,
                                 int max) {
    return RANDOM.nextInt(max - min) + min;
  }

}
