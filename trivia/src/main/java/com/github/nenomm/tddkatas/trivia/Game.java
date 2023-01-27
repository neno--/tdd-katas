package com.github.nenomm.tddkatas.trivia;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://kata-log.rocks/ugly-trivia-kata
public class Game {

  enum QuestionCategory {
    POP("Pop"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock");

    private final String name;

    QuestionCategory(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return name;
    }
  }

  private static final short MAX_PLAYERS = 6;
  private static final short NUM_OF_QUESTIONS = 50;
  private final PrintStream out;
  private final List<Player> players = new ArrayList<>(MAX_PLAYERS);
  private final Map<QuestionCategory, List<String>> questions = new HashMap<>(QuestionCategory.values().length * NUM_OF_QUESTIONS);
  Player currentPlayer = null;
  boolean isGettingOutOfPenaltyBox;

  public Game(PrintStream out) {
    this.out = out;
    initializeQuestions();
  }

  private void initializeQuestions() {
    for (QuestionCategory category : QuestionCategory.values()) {
      questions.put(category, new ArrayList<String>(NUM_OF_QUESTIONS));
      for (int i = 0; i < NUM_OF_QUESTIONS; i++) {
        questions.get(category).add(String.format("%s Question %d", category, i));
      }
    }
  }

  public boolean add(String playerName) {
    final Player newPlayer = new Player(playerName);

    if (players.isEmpty()) {
      currentPlayer = newPlayer;
    }

    players.add(newPlayer);

    out.println(playerName + " was added");
    out.println("They are player number " + players.size());
    return true;
  }

  public void roll(int roll) {
    out.println(currentPlayer.getName() + " is the current player");
    out.println("They have rolled a " + roll);

    if (currentPlayer.hasPenalty()) {
      if (roll % 2 != 0) {
        isGettingOutOfPenaltyBox = true;

        out.println(currentPlayer.getName() + " is getting out of the penalty box");
        currentPlayer.goForward(roll);

        displayPosition();
        displayCategory();
        askQuestion();
      } else {
        out.println(currentPlayer.getName() + " is not getting out of the penalty box");
        isGettingOutOfPenaltyBox = false;
      }
    } else {
      currentPlayer.goForward(roll);

      displayPosition();
      displayCategory();
      askQuestion();
    }

  }

  private void askQuestion() {
    out.println(questions.get(currentCategory()).remove(0));
  }

  private QuestionCategory currentCategory() {
    if (currentPlayer.getPosition() == 0) {
      return QuestionCategory.POP;
    }
    if (currentPlayer.getPosition() == 4) {
      return QuestionCategory.POP;
    }
    if (currentPlayer.getPosition() == 8) {
      return QuestionCategory.POP;
    }
    if (currentPlayer.getPosition() == 1) {
      return QuestionCategory.SCIENCE;
    }
    if (currentPlayer.getPosition() == 5) {
      return QuestionCategory.SCIENCE;
    }
    if (currentPlayer.getPosition() == 9) {
      return QuestionCategory.SCIENCE;
    }
    if (currentPlayer.getPosition() == 02) {
      return QuestionCategory.SPORTS;
    }
    if (currentPlayer.getPosition() == 6) {
      return QuestionCategory.SPORTS;
    }
    if (currentPlayer.getPosition() == 10) {
      return QuestionCategory.SPORTS;
    }
    return QuestionCategory.ROCK;
  }

  public boolean wasCorrectlyAnswered() {
    if (currentPlayer.hasPenalty()) {
      if (isGettingOutOfPenaltyBox) {
        out.println("Answer was correct!!!!");
        currentPlayer.addPoints(1);
        displayPoints();

        boolean winner = didPlayerWin();
        currentPlayer = nextPlayer();
        return winner;
      } else {
        currentPlayer = nextPlayer();
        return true;
      }
    } else {
      out.println("Answer was corrent!!!!");
      currentPlayer.addPoints(1);
      displayPoints();

      boolean winner = didPlayerWin();
      currentPlayer = nextPlayer();

      return winner;
    }
  }

  public boolean wrongAnswer() {
    out.println("Question was incorrectly answered");
    out.println(currentPlayer.getName() + " was sent to the penalty box");
    currentPlayer.setPenalty();

    currentPlayer = nextPlayer();
    return true;
  }

  private boolean didPlayerWin() {
    return !(currentPlayer.getPoints() == 6);
  }

  private void displayCategory() {
    out.println("The category is " + currentCategory());
  }

  private void displayPosition() {
    out.println(currentPlayer.getName()
        + "'s new location is "
        + currentPlayer.getPosition());
  }

  private void displayPoints() {
    out.println(currentPlayer.getName()
        + " now has "
        + currentPlayer.getPoints()
        + " Gold Coins.");
  }

  private Player nextPlayer() {
    return players.get((players.indexOf(currentPlayer) + 1) % players.size());
  }
}