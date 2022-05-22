package org.artiste.sudoku.neighbours;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import org.artiste.sudoku.model.BoxManager;
import org.artiste.sudoku.model.CellCoordinates;
import org.artiste.sudoku.model.Sudoku;

@UtilityClass
public class NeighboursFinder {
  public Set<Integer> findDistinctNeighbours(Sudoku sudoku, CellCoordinates cellCoordinates) {
    Set<Integer> ret = new HashSet<>();
    ret.addAll(Arrays.stream(sudoku.getRow(cellCoordinates.getRow()))
                     .boxed()
                     .collect(Collectors.toList()));
    ret.addAll(Arrays.stream(sudoku.getColumn(cellCoordinates.getColumn()))
                     .boxed()
                     .collect(Collectors.toList()));
    int boxIndex = BoxManager.findBoxIndex(cellCoordinates.getRow(), cellCoordinates.getColumn());
    ret.addAll(Arrays.stream(sudoku.getBox(boxIndex))
                     .boxed()
                     .collect(Collectors.toList()));
    return ret;
  }

  public int getNumberOfEmptyNeighbours(Sudoku sudoku, CellCoordinates cellCoordinates) {
    int[] row = sudoku.getRow(cellCoordinates.getRow());
    int[] column = sudoku.getColumn(cellCoordinates.getColumn());
    int boxIndex = BoxManager.findBoxIndex(cellCoordinates.getRow(), cellCoordinates.getColumn());
    int ret = 0;
    ret += Arrays.stream(row)
                 .filter(i -> i == 0)
                 .toArray().length;
    ret += Arrays.stream(column)
                 .filter(i -> i == 0)
                 .toArray().length;
    ret += getNumberOfEmptyCellsInBox(sudoku.getBox(boxIndex),
                                      BoxManager.getBoxesStartCells()
                                                .get(boxIndex),
                                      cellCoordinates);
    if (sudoku.getCellValue(cellCoordinates) == 0) {
      ret -= 2;
    }
    return ret;
  }

  private int getNumberOfEmptyCellsInBox(int[] box, CellCoordinates boxStartCells, CellCoordinates cellCoordinates) {
    int ret = 0;
    int excludedRow = cellCoordinates.getRow() - boxStartCells.getRow();
    int excludedCol = cellCoordinates.getColumn() - boxStartCells.getColumn();
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (i != excludedRow && j != excludedCol && box[i * 3 + j] == 0) {
          ret++;
        }
      }
    }
    return ret;
  }
}
