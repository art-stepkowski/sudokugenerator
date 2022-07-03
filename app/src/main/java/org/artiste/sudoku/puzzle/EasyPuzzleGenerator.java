package org.artiste.sudoku.puzzle;

import com.google.common.annotations.VisibleForTesting;
import java.util.HashSet;
import java.util.Set;
import org.artiste.sudoku.model.CellCoordinates;
import org.artiste.sudoku.model.Sudoku;

public class EasyPuzzleGenerator extends AbstractPuzzleGenerator {
  @VisibleForTesting
  protected static final int MIN_NUMBER_OF_EMPTY_CELLS = 27;
  @VisibleForTesting
  protected static final int MAX_NUMBER_OF_EMPTY_CELLS = 36;

  @Override
  public Sudoku generate(Sudoku fullSudoku) {
    int numberOfEmptyFields = getNumberFromRange(MIN_NUMBER_OF_EMPTY_CELLS, MAX_NUMBER_OF_EMPTY_CELLS);
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
}
