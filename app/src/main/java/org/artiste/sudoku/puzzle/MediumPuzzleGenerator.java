package org.artiste.sudoku.puzzle;

public class MediumPuzzleGenerator extends AbstractPuzzleGenerator {
  private static final int MIN_NUMBER_OF_EMPTY_CELLS = 37;
  private static final int MAX_NUMBER_OF_EMPTY_CELLS = 50;

  @Override
  public int getMinNumberOfEmptyCells() {
    return MIN_NUMBER_OF_EMPTY_CELLS;
  }

  @Override
  public int getMaxNumberOfEmptyCells() {
    return MAX_NUMBER_OF_EMPTY_CELLS;
  }
}
