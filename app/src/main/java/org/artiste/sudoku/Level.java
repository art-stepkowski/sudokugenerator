package org.artiste.sudoku;

public enum Level {
  EASY,
  MEDIUM,
  HARD;

  public static Level of(String level) {
    return valueOf(level.toUpperCase());
  }
}
