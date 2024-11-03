package sample;

import javafx.scene.text.Text;

import java.io.Serializable;
import java.util.ArrayList;

public class Move implements Serializable, Constants {
  private Side move;
  private ArrayList<Mark> marks;
  private int firstPlayerScore;
  private int secondPlayerScore;
  private int currentPlayer;

  Move() {
    move = null;
    marks = new ArrayList<>();
    firstPlayerScore = 0;
    secondPlayerScore = 0;
    currentPlayer = 0;
  }

  public void setSide(Side move) {
    this.move = move;
  }

  public void addMark(Mark mark) {
    marks.add(mark);
  }

  public void setScores(ArrayList<Text> scores) {
    this.firstPlayerScore = Integer.valueOf(scores.get(FIRST).getText(), 10);
    this.secondPlayerScore = Integer.valueOf(scores.get(SECOND).getText(), 10);
  }

  public Side getMove() {
    return move;
  }

  public void setCurrentPlayer(int currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  public ArrayList<Mark> getMarks() {
    return marks;
  }

  public int getFirstPlayerScore() {
    return firstPlayerScore;
  }

  public int getSecondPlayerScore() {
    return secondPlayerScore;
  }

  public int getCurrentPlayer() {
    return currentPlayer;
  }

}
