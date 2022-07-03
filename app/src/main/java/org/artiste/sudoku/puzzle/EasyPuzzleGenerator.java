package org.artiste.sudoku.puzzle;

import java.util.HashSet;
import java.util.Set;
import org.artiste.sudoku.model.CellCoordinates;
import org.artiste.sudoku.model.Sudoku;

public class EasyPuzzleGenerator extends AbstractPuzzleGenerator {
  private static final int MIN_NUMBER_OF_EMPTY_CELLS = 27;
  private static final int MAX_NUMBER_OF_EMPTY_CELLS = 36;

  @Override
  public int getMinNumberOfEmptyCells() {
    return MIN_NUMBER_OF_EMPTY_CELLS;
  }

  @Override
  public int getMaxNumberOfEmptyCells() {
    return MAX_NUMBER_OF_EMPTY_CELLS;
  }
}
