package sample;

import javafx.scene.shape.Line;

import java.io.Serializable;

/**
 * This class implements side of box
 */
public class Side extends Line implements Constants, Serializable {
  private Point startCoordinates;
  private Point endCoordinates;

  Side() {
    startCoordinates = null;
    endCoordinates = null;
  }

  Side(Point startCoordinates, Point endCoordinates) {
    super(startCoordinates.getX(), startCoordinates.getY(), endCoordinates.getX(),
        endCoordinates.getY());
    this.startCoordinates = startCoordinates;
    this.endCoordinates = endCoordinates;
    setStrokeWidth(5);
  }

  public Point getStartCoordinates() {
    return startCoordinates;
  }

  public Point getEndCoordinates() {
    return endCoordinates;
  }
}
