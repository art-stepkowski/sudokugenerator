package org.artiste.sudoku.generator;

import java.util.stream.Stream;
import org.artiste.sudoku.model.Sudoku;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class GeneratorTest {
  private static final int[] EXPECTED_VALUES = {1, 2, 3, 4, 5, 6, 7, 8, 9};

  static Stream<Generator> generatorsStream() {
    return Stream.of(new SmartGenerator());
  }

  @ParameterizedTest
  @MethodSource("generatorsStream")
  void should_prepare_proper_sudoku(SmartGenerator generator) {
    Sudoku actual = generator.generate();
    for (int i = 0; i < 9; i++) {
      assertThat(actual.getRow(i)).containsExactlyInAnyOrder(EXPECTED_VALUES);
      assertThat(actual.getColumn(i)).containsExactlyInAnyOrder(EXPECTED_VALUES);
      assertThat(actual.getBox(i)).containsExactlyInAnyOrder(EXPECTED_VALUES);
    }
  }

}