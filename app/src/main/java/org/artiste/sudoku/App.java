package org.artiste.sudoku;

import org.artiste.sudoku.generator.ClassicGenerator;
import org.artiste.sudoku.generator.Generator;
import org.artiste.sudoku.model.Sudoku;
import org.artiste.sudoku.puzzle.PuzzleGenerator;
import org.artiste.sudoku.puzzle.PuzzleValidator;

public class App {

  public static void main(String[] args) {
    Generator generator = new ClassicGenerator();
    Sudoku sudoku = generator.generate();
    Sudoku puzzle = new PuzzleGenerator().generate(sudoku);
    boolean result = new PuzzleValidator().validate(sudoku, puzzle);
  }
}
