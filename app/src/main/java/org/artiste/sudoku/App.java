package org.artiste.sudoku;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
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
        generatePuzzles(Integer.parseInt(cmd.getOptionValue("n")));
      }
    } catch (ParseException e) {
      System.out.println(e.getMessage());
      helper.printHelp("Usage:", options);
      System.exit(0);
    }
  }

  private static void generatePuzzles(int numberOfPuzzles) {
    Generator generator = new ClassicGenerator();
    PuzzleGenerator puzzleGenerator = new PuzzleGenerator();
    PuzzleValidator puzzleValidator = new PuzzleValidator();
    List<PuzzleGroup> ret = new ArrayList<>();
    for (int i = 0; i < numberOfPuzzles; i++) {
      Sudoku sudoku = generator.generate();
      Sudoku puzzle = puzzleGenerator.generate(sudoku);
      if (puzzleValidator.validate(sudoku, puzzle)) {
        ret.add(new PuzzleGroup(sudoku, puzzle));
      }
    }
    try (Writer writer = new FileWriter("app\\build\\puzzles.json")) {
      new Gson().toJson(ret, writer);
    } catch (IOException e) {
      System.err.println(e.getMessage());
      System.exit(500);
    }
  }
}
