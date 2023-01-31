package org.artiste.sudoku;

import lombok.Value;
import org.artiste.sudoku.model.Sudoku;

@Value
public class PuzzleGroup {
  Sudoku fullSudoku;
  Sudoku puzzle;
}
