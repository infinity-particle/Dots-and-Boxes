package sample;

import javafx.scene.shape.Line;

/**
 * This class implements side of box
 */
public class Side extends Line implements Constants {
  private Point startCoordinates;
  private Point endCoordinates;

  Side(Point startCoordinates, Point endCoordinates) {
    super(startCoordinates.getX(), startCoordinates.getY(), endCoordinates.getX(),
        endCoordinates.getY());
    this.startCoordinates = startCoordinates;
    this.endCoordinates = endCoordinates;
    setStrokeWidth(5);
  }
}
