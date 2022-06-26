package org.artiste.sudoku;

import lombok.experimental.UtilityClass;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

@UtilityClass
public class OptionsManager {

  public static Options get() {
    Options options = new Options();
    options.addOption(Option.builder("n")
                            .longOpt("number")
                            .argName("numberOfPuzzles")
                            .hasArg()
                            .required()
                            .desc("set number of generated puzzles [required]")
                            .build());
    options.addOption(Option.builder("o")
                            .longOpt("output")
                            .argName("outputFileName")
                            .hasArg()
                            .required(false)
                            .desc("set output file name")
                            .build());
    options.addOption(Option.builder("l")
                            .longOpt("level")
                            .argName("level")
                            .hasArg()
                            .required()
                            .desc("set level of generated sudokus (easy, medium, hard) [required]")
                            .build());
    return options;
  }
}
