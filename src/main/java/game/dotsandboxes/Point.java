package game.dotsandboxes;

import java.io.Serializable;

import static java.lang.Math.*;

/**
 * Implements point with x and y coordinates.
 */
public class Point implements Serializable {
  private int x;
  private int y;

  Point() {
    x = 0;
    y = 0;
  }

  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  /**
   * @return Distance to second point
   */
  public int distanceTo(Point secondPoint) {
    return (int) sqrt(pow(this.x - secondPoint.getX(), 2) + pow(this.y - secondPoint.getY(), 2));
  }

  /**
   * Compares two points on equality.
   *
   * @param point Point to compare.
   * @return true, if point equal to each other,and false,if not.
   */
  public boolean compareTo(Point point) {
    if ((this.x == point.getX()) && (this.y == point.getY())) {
      return true;
    } else {
      return false;
    }
  }
}
