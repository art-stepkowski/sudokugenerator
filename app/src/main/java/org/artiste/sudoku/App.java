package org.artiste.sudoku;

import java.util.ArrayList;
import java.util.List;
import me.tongfei.progressbar.ProgressBar;
import org.apache.commons.cli.*;
import org.artiste.sudoku.generator.ClassicGenerator;
import org.artiste.sudoku.generator.Generator;
import org.artiste.sudoku.model.Sudoku;
import org.artiste.sudoku.puzzle.PuzzleGenerator;
import org.artiste.sudoku.puzzle.PuzzleGeneratorFactory;
import org.artiste.sudoku.puzzle.PuzzleValidator;

public class App {

  public static void main(String[] args) {
    Options options = OptionsManager.get();
    CommandLine cmd;
    CommandLineParser parser = new DefaultParser();
    HelpFormatter helper = new HelpFormatter();
    try {
      cmd = parser.parse(options, args);
      if (cmd.hasOption("n") && cmd.hasOption('l')) {
        generatePuzzles(Integer.parseInt(cmd.getOptionValue("n")),
                        cmd.getOptionValue("o"),
                        Level.of(cmd.getOptionValue('l')));
      }
    } catch (ParseException e) {
      System.out.println(e.getMessage());
      helper.printHelp("Options for sudoku generator", options);
      System.exit(0);
    }
  }

  private static void generatePuzzles(int numberOfPuzzles,
                                      String outputFileName,
                                      Level level) {
    Generator generator = new ClassicGenerator();
    PuzzleGenerator puzzleGenerator = PuzzleGeneratorFactory.get(level);
    if (null == puzzleGenerator) {
      throw new IllegalArgumentException("Wrong level");
    }
    PuzzleValidator puzzleValidator = new PuzzleValidator();
    List<PuzzleGroup> ret = new ArrayList<>();
    try (ProgressBar pb = new ProgressBar("Progress", 100)) {
      int i = 0;
      while (i < numberOfPuzzles) {
        Sudoku sudoku = generator.generate();
        Sudoku puzzle = puzzleGenerator.generate(sudoku);
        if (puzzleValidator.validate(sudoku, puzzle)) {
          ret.add(new PuzzleGroup(sudoku, puzzle));
          pb.step();
        } else {
          i--;
        }
        i++;
      }
    }
    new SudokuWriter().write(outputFileName, ret);
  }
}
