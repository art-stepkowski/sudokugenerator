package org.artiste.sudoku.model;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode
public class CellCoordinates {
  int row;
  int column;
}
