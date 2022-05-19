package org.artiste.sudoku.generator;


import java.util.*;
import java.util.function.IntFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.artiste.sudoku.model.Sudoku;

public class SmartGenerator implements Generator {

  private static final Random                            RANDOM        = new Random();
  private static final UnaryOperator<int[]>              SHIFT_3       = SmartGenerator::shiftValuesBy3;
  private static final UnaryOperator<int[]>              SHIFT_1       = SmartGenerator::shiftValuesBy1;
  private static final IntFunction<UnaryOperator<int[]>> SHIFT_FACTORY = i -> i == 3 || i == 6 ? SHIFT_1 : SHIFT_3;

  public Sudoku generate() {
    Sudoku ret = new Sudoku();
    fillFirstRow(ret);
    fillOtherRows(ret);
    shuffleRowsAndColumns(ret);
    return ret;
  }

  private void shuffleRowsAndColumns(Sudoku sudoku) {
    for (int i = 0; i < 9; i += 3) {
      Map<Integer, Integer> shufflePattern = prepareShufflePattern(i);
      Map<Integer, int[]> rows = sudoku.getRowsInRange(i, 3);
      rows.forEach((k, v) -> sudoku.fillRow(shufflePattern.get(k), v));
    }
    for (int i = 0; i < 9; i += 3) {
      Map<Integer, Integer> shufflePattern = prepareShufflePattern(i);
      Map<Integer, int[]> columns = sudoku.getColumnsInRange(i, 3);
      columns.forEach((k, v) -> sudoku.fillColumn(shufflePattern.get(k), v));
    }
  }

  private Map<Integer, Integer> prepareShufflePattern(int row) {
    List<Integer> range = List.of(row, row + 1, row + 2);
    ArrayList<Integer> newRange = new ArrayList<>(range);
    Collections.shuffle(newRange);
    return IntStream.range(0, 3)
                    .boxed()
                    .collect(Collectors.toMap(range::get, newRange::get, (a, b) -> b));
  }

  private void fillOtherRows(Sudoku sudoku) {
    for (int i = 1; i < 9; i++) {
      int[] row = sudoku.getRow(i - 1);
      row = SHIFT_FACTORY.apply(i)
                         .apply(row);
      sudoku.fillRow(i, row);
    }
  }

  private static int[] shiftValuesBy1(int[] row) {
    int[] newRow = new int[9];
    System.arraycopy(row, 0, newRow, 1, row.length - 1);
    newRow[0] = row[8];
    return newRow;
  }


  private static int[] shiftValuesBy3(int[] row) {
    int[] newRow = new int[9];
    System.arraycopy(row, 0, newRow, 3, row.length - 3);
    newRow[0] = row[6];
    newRow[1] = row[7];
    newRow[2] = row[8];
    return newRow;
  }

  private void fillFirstRow(Sudoku sudoku) {
    int[] values = createRandomRow();
    sudoku.fillRow(0, values);
  }

  private int[] createRandomRow() {
    List<Integer> ret = new ArrayList<>();
    for (int i = 0; i < 9; i++) {
      int value;
      do {
        value = RANDOM.nextInt(9) + 1;
      } while (ret.contains(value));
      ret.add(value);
    }
    return ret.stream()
              .mapToInt(Integer::intValue)
              .toArray();
  }
}
