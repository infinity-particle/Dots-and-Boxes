package game.dotsandboxes;

import java.io.Serializable;

public class Mark implements Serializable {
  private double x;
  private double y;
  private String text;
  private double fontSize;
  private int colorOfPlayer;

  Mark() {
    x = 0.0;
    y = 0.0;
    text = null;
    fontSize = 0.0;
    colorOfPlayer = 0;
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setFontSize(double fontSize) {
    this.fontSize = fontSize;
  }

  public void setColorOfPlayer(int whichPlayer) {
    this.colorOfPlayer = whichPlayer;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public String getText() {
    return text;
  }

  public double getFontSize() {
    return fontSize;
  }

  public int getColorOfPlayer() {
    return colorOfPlayer;
  }
}
