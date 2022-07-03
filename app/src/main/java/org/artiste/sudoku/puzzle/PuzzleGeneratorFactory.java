package org.artiste.sudoku.puzzle;

import lombok.experimental.UtilityClass;
import org.artiste.sudoku.Level;

@UtilityClass
public class PuzzleGeneratorFactory {
  public static PuzzleGenerator get(Level level) {
    return switch (level) {
      case EASY -> new EasyPuzzleGenerator();
      case MEDIUM -> new MediumPuzzleGenerator();
      case HARD -> new HardPuzzleGenerator();
    };
  }
}
