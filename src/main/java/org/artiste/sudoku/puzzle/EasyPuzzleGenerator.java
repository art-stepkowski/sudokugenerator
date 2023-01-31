package org.artiste.sudoku.puzzle;

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
