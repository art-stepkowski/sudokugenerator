package org.artiste.sudoku.generator;

import java.util.*;
import org.artiste.sudoku.model.CellCoordinates;
import org.artiste.sudoku.model.Sudoku;
import org.artiste.sudoku.neighbours.NeighboursFinder;

public class ClassicGenerator implements Generator {
  private static final Random RANDOM = new Random();
  private static final Map<CellCoordinates, Set<Integer>> USED_NUMBERS = new HashMap<>();
  private static final List<Integer> ALL_NUMBERS = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

  @Override
  public Sudoku generate() {
    Sudoku ret = new Sudoku();
    fillSudoku(ret);
    return ret;
  }

  private boolean fillSudoku(Sudoku sudoku) {
    CellCoordinates cellCoordinates = sudoku.findFirstEmptyCell();
    if (null == cellCoordinates) {
      return true;
    }
    Set<Integer> neighbours = NeighboursFinder.findDistinctNeighbours(sudoku, cellCoordinates);
    while (true) {
      Set<Integer> usedNumbers = USED_NUMBERS.getOrDefault(cellCoordinates, Collections.emptySet());
      ArrayList<Integer> availableNumbers = new ArrayList<>(ALL_NUMBERS);
      availableNumbers.removeAll(neighbours);
      availableNumbers.removeAll(usedNumbers);
      if (availableNumbers.isEmpty()) {
        usedNumbers.clear();
        return false;
      }
      int i = availableNumbers.get(RANDOM.nextInt(availableNumbers.size()));
      USED_NUMBERS.putIfAbsent(cellCoordinates, new HashSet<>());
      USED_NUMBERS.get(cellCoordinates)
                  .add(i);
      sudoku.fillCell(cellCoordinates.getRow(), cellCoordinates.getColumn(), i);
      if (fillSudoku(sudoku)) {
        return true;
      }
      sudoku.fillCell(cellCoordinates.getRow(), cellCoordinates.getColumn(), 0);
    }
  }

}
