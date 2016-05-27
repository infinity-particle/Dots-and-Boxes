package sample;

import javafx.scene.paint.Color;

import java.io.Serializable;

public class Player implements Serializable {
  private String name;
  private String initials;
  private int score;
  transient private PlayerType type;
  transient private Color color;

  Player(String name, PlayerType type, Color color) {
    this.name = name;
    this.initials = String.valueOf(name.charAt(0));
    this.type = type;
    score = 0;
    this.color = color;
  }

  public String getName() {
    return name;
  }

  public int getScore() {
    return score;
  }

  public String getInitials() {
    return initials;
  }

  public Color getColor() {
    return color;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void addScore(int points) {
    score += points;
  }

  public PlayerType getType() {
    return type;
  }
}
