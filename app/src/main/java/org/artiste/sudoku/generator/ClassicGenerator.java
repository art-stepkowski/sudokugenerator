package org.artiste.sudoku.generator;

import org.artiste.sudoku.model.Sudoku;
import org.artiste.sudoku.puzzle.Solver;

public class ClassicGenerator implements Generator {
  @Override
  public Sudoku generate() {
    Sudoku ret = new Sudoku();
    new Solver().solve(ret);
    return ret;
  }
}
