package org.artiste.sudoku.puzzle;

import java.util.Random;

public abstract class AbstractPuzzleGenerator implements PuzzleGenerator {
  protected static final Random RANDOM = new Random();

  protected int getNumberFromRange(int min, int max) {
    return RANDOM.nextInt(max - min) + min;
  }

}
