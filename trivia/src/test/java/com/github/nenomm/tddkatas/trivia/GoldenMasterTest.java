package com.github.nenomm.tddkatas.trivia;

import static com.github.nenomm.tddkatas.trivia.ApplicationRunner.RANDOM;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

public class GoldenMasterTest {

  @Test
  public void isStillInLineWithTheGoldenMaster() throws Exception {
    final ApplicationRunner runner = new ApplicationRunner();
    final String actual = runner.run(RANDOM);
    final InputStream inputStream = this.getClass().getResourceAsStream("/golden-master.txt");
    final String expected = inputStreamToString(inputStream);

    assertEquals(expected, actual);
  }

  private String inputStreamToString(InputStream inputStream) throws IOException {
    final StringBuilder textBuilder = new StringBuilder();

    try (Reader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
      int c = 0;
      while ((c = reader.read()) != -1) {
        textBuilder.append((char) c);
      }
    }

    return textBuilder.toString();
  }
}