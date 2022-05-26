package org.artiste.sudoku;

import org.apache.commons.cli.*;
import org.artiste.sudoku.generator.ClassicGenerator;
import org.artiste.sudoku.generator.Generator;
import org.artiste.sudoku.model.Sudoku;
import org.artiste.sudoku.puzzle.PuzzleGenerator;
import org.artiste.sudoku.puzzle.PuzzleValidator;

public class App {

  public static void main(String[] args) {
    Options options = OptionsManager.get();
    CommandLine cmd;
    CommandLineParser parser = new DefaultParser();
    HelpFormatter helper = new HelpFormatter();
    try {
      cmd = parser.parse(options, args);
      if (cmd.hasOption("n")) {
        generatePuzzles(cmd.getOptionValue("n"));
      }
    } catch (ParseException e) {
      System.out.println(e.getMessage());
      helper.printHelp("Usage:", options);
      System.exit(0);
    }
  }

  private static void generatePuzzles(String numberOfPuzzles) {
    Generator generator = new ClassicGenerator();
    Sudoku sudoku = generator.generate();
    Sudoku puzzle = new PuzzleGenerator().generate(sudoku);
    boolean result = new PuzzleValidator().validate(sudoku, puzzle);
  }
}
