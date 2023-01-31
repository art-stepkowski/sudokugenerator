package org.artiste.sudoku.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RandomRowGenerator {
  private static final Random RANDOM = new Random();

  public int[] generate() {
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
