package org.artiste.sudoku.puzzle;

public class HardPuzzleGenerator extends AbstractPuzzleGenerator {
  private static final int MIN_NUMBER_OF_EMPTY_CELLS = 51;
  private static final int MAX_NUMBER_OF_EMPTY_CELLS = 60;

  @Override
  public int getMinNumberOfEmptyCells() {
    return MIN_NUMBER_OF_EMPTY_CELLS;
  }

  @Override
  public int getMaxNumberOfEmptyCells() {
    return MAX_NUMBER_OF_EMPTY_CELLS;
  }
}
