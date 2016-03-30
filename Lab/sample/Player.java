package sample;

import javafx.scene.paint.Color;

public class Player {
  private String name;
  private String initials;
  private int score;
  private PlayerType type;
  private Color color;

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

  public void addScore(int points) {
    score += points;
  }

  public PlayerType getType() {
    return type;
  }
}
