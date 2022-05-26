package org.artiste.sudoku;

import lombok.experimental.UtilityClass;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

@UtilityClass
public class OptionsManager {

  public static Options get() {
    Options options = new Options();
    Option numberOfPuzzles = Option.builder("n")
                                   .longOpt("number")
                                   .argName("numberOfPuzzles")
                                   .hasArg()
                                   .required(true)
                                   .desc("set number of generated puzzles ")
                                   .build();
    options.addOption(numberOfPuzzles);
    return options;
  }
}
