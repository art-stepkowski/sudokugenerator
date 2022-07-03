package org.artiste.sudoku;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class SudokuWriter {
  public void write(String outputFileName,
                    List<PuzzleGroup> ret) {
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
