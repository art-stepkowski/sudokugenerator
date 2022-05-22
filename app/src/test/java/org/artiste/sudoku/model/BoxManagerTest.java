package org.artiste.sudoku.model;


import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BoxManagerTest {
  @ParameterizedTest
  @MethodSource("prepareDataForCheckingBoxNumber")
  void should_return_valid_box_numbers(int row, int column, int expectedBoxNumber) {
    int boxNumber = BoxManager.findBoxIndex(row, column);
    assertThat(boxNumber).isEqualTo(expectedBoxNumber);
  }

  @Test
  void should_throw_exception() {
    assertThrows(IndexOutOfBoundsException.class, () -> BoxManager.findBoxIndex(9, 1));
  }

  private static Stream<Arguments> prepareDataForCheckingBoxNumber() {
    return Stream.of(Arguments.of(1, 1, 0),
                     Arguments.of(2, 2, 0),
                     Arguments.of(3, 1, 1),
                     Arguments.of(4, 2, 1),
                     Arguments.of(7, 1, 2),
                     Arguments.of(0, 4, 3),
                     Arguments.of(3, 4, 4),
                     Arguments.of(7, 7, 8),
                     Arguments.of(8, 8, 8));
  }
}