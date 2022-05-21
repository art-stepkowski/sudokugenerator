package org.artiste.sudoku.generator;

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
  public Set<Integer> findNeighbours(Sudoku sudoku,
                                     CellCoordinates cellCoordinates) {
    Set<Integer> ret = new HashSet<>();
    ret.addAll(Arrays.stream(sudoku.getRow(cellCoordinates.getRow()))
                     .boxed()
                     .collect(Collectors.toList()));
    ret.addAll(Arrays.stream(sudoku.getColumn(cellCoordinates.getColumn()))
                     .boxed()
                     .collect(Collectors.toList()));
    int boxNumber = BoxManager.findBoxNumber(cellCoordinates.getRow(), cellCoordinates.getColumn());
    ret.addAll(Arrays.stream(sudoku.getBox(boxNumber))
                     .boxed()
                     .collect(Collectors.toList()));
    return ret;
  }
}
