package org.artiste.sudoku;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import me.tongfei.progressbar.ProgressBar;
import org.apache.commons.cli.*;
import org.artiste.sudoku.generator.ClassicGenerator;
import org.artiste.sudoku.generator.Generator;
import org.artiste.sudoku.model.Sudoku;
import org.artiste.sudoku.puzzle.EasyPuzzleGenerator;
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
    EasyPuzzleGenerator puzzleGenerator = new EasyPuzzleGenerator();
    PuzzleValidator puzzleValidator = new PuzzleValidator();
    List<PuzzleGroup> ret = new ArrayList<>();
    try (ProgressBar pb = new ProgressBar("Progress", 100)) {
      for (int i = 0; i < numberOfPuzzles; i++) {
        Sudoku sudoku = generator.generate();
        Sudoku puzzle = puzzleGenerator.generate(sudoku);
        if (puzzleValidator.validate(sudoku, puzzle)) {
          ret.add(new PuzzleGroup(sudoku, puzzle));
          pb.step();
        } else {
          // TODO Refactor the code in order to not assign to this loop counter from within the loop body.
          i--;
        }
      }
    }
    if (null == outputFileName) {
      outputFileName = "puzzles.json";
    }
    try (Writer writer = new FileWriter(outputFileName)) {
      new Gson().toJson(ret, writer);
    } catch (IOException e) {
      System.err.println(e.getMessage());
      System.exit(500);
    }
  }
}
