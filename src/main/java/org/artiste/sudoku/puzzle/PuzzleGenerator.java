package org.artiste.sudoku.puzzle;

import org.artiste.sudoku.model.Sudoku;

public interface PuzzleGenerator {
  Sudoku generate(Sudoku fullSudoku);

  int getMinNumberOfEmptyCells();

  int getMaxNumberOfEmptyCells();
}
