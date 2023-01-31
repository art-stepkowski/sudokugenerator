package org.artiste.sudoku.model;

import java.util.HashMap;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BoxManager {
  private static final Map<Integer, CellCoordinates> BOXES_START_CELLS = new HashMap<>();

  static {
    BOXES_START_CELLS.put(0, new CellCoordinates(0, 0));
    BOXES_START_CELLS.put(1, new CellCoordinates(3, 0));
    BOXES_START_CELLS.put(2, new CellCoordinates(6, 0));
    BOXES_START_CELLS.put(3, new CellCoordinates(0, 3));
    BOXES_START_CELLS.put(4, new CellCoordinates(3, 3));
    BOXES_START_CELLS.put(5, new CellCoordinates(6, 3));
    BOXES_START_CELLS.put(6, new CellCoordinates(0, 6));
    BOXES_START_CELLS.put(7, new CellCoordinates(3, 6));
    BOXES_START_CELLS.put(8, new CellCoordinates(6, 6));
  }

  public int findBoxIndex(int row, int column) {
    int r1 = row / 3;
    int c1 = column / 3;
    for (Map.Entry<Integer, CellCoordinates> entry : BOXES_START_CELLS.entrySet()) {
      int startRow = entry.getValue()
                          .getRow();
      int startColumn = entry.getValue()
                             .getColumn();
      if (startRow == r1 * 3 && startColumn == c1 * 3) {
        return entry.getKey();
      }
    }
    throw new IndexOutOfBoundsException();
  }

  public Map<Integer, CellCoordinates> getBoxesStartCells() {
    return new HashMap<>(BOXES_START_CELLS);
  }
}
