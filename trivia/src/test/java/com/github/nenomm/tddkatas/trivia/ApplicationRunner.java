package com.github.nenomm.tddkatas.trivia;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class ApplicationRunner {

  public static final Random RANDOM = new Random(357);

  public static void main(String[] args) {
    final ApplicationRunner runner = new ApplicationRunner();
    runner.dumpGoldenMaster();
  }

  private void run(Random random, PrintStream output) {
    final Game game = new Game(output);
    boolean keepPlaying;

    game.add("Chet");
    game.add("Pat");
    game.add("Sue");

    do {
      game.roll(random.nextInt(5) + 1);
      if (random.nextInt(9) == 7) {
        keepPlaying = game.wrongAnswer();
      } else {
        keepPlaying = game.wasCorrectlyAnswered();
      }
    } while (keepPlaying);
  }

  public String run(Random random) {
    final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    final PrintStream output = new PrintStream(byteStream);

    run(random, output);

    return byteStream.toString(StandardCharsets.UTF_8);
  }

  private void dumpGoldenMaster() {
    try (final BufferedWriter writer = new BufferedWriter(new FileWriter("golden-master.txt"))) {
      writer.write(run(RANDOM));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
