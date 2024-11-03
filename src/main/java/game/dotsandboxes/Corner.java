package game.dotsandboxes;

import javafx.scene.effect.GaussianBlur;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * This class implements a corner of the cell on the playing field.
 */
public class Corner extends Circle implements Constants {
  private Point location;
  private boolean clicked;

  /**
   * Make corner by given the coordinates
   *
   * @param x Coordinate x
   * @param y Coordinate y
   */
  Corner(int x, int y) {
    super(x, y, DEFAULT_RADIUS);
    location = new Point(x, y);
    clicked = false;
    setFill(Color.WHITE);

    setOnMouseEntered(event -> {
      GaussianBlur blur = new GaussianBlur(DEFAULT_RADIUS * 1.75);
      setEffect(blur);
    });
    setOnMouseExited(event -> {
      setEffect(null);
    });
    setOnMousePressed(event -> {
      clicked = !clicked;
      if (clicked)
        setFill(Color.LIGHTGREEN);
      else
        setFill(Color.WHITE);
    });
  }

  public Point getLocation() {
    return location;
  }

  public boolean isClicked() {
    return clicked;
  }

  public void unclick() {
    clicked = false;
    setFill(Color.WHITE);
  }

  /**
   * Checks whether two adjacent corners
   */
  public boolean isNearbyTo(Corner cornerToCompare) {
    if (this.location.distanceTo(cornerToCompare.location) == DEFAULT_OFFSET)
      return true;
    return false;
  }

  /**
   * Checks the same corner
   */
  public boolean sameTo(Corner corner) {
    if (this.getLocation().compareTo(corner.getLocation())) {
      return true;
    } else {
      return false;
    }
  }
}

