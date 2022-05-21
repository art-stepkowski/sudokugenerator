package org.artiste.sudoku.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BoxManager {
  static final Map<Integer, List<Integer>> BOXES_START_CELLS = new HashMap<>();

  static {
    BOXES_START_CELLS.put(0, List.of(0, 0));
    BOXES_START_CELLS.put(1, List.of(3, 0));
    BOXES_START_CELLS.put(2, List.of(6, 0));
    BOXES_START_CELLS.put(3, List.of(0, 3));
    BOXES_START_CELLS.put(4, List.of(3, 3));
    BOXES_START_CELLS.put(5, List.of(6, 3));
    BOXES_START_CELLS.put(6, List.of(0, 6));
    BOXES_START_CELLS.put(7, List.of(3, 6));
    BOXES_START_CELLS.put(8, List.of(6, 6));
  }

  public int findBoxNumber(int row,
                           int column) {
    int r1 = row / 3;
    int c1 = column / 3;
    for (Map.Entry<Integer, List<Integer>> entry : BOXES_START_CELLS.entrySet()) {
      Integer startRow = entry.getValue()
                              .get(0);
      Integer startColumn = entry.getValue()
                                 .get(1);
      if (startRow == r1 * 3 && startColumn == c1 * 3) {
        return entry.getKey();
      }
    }
    throw new IndexOutOfBoundsException();
  }
}
