package sample;

/**
 * This class implements cell on game board
 */
public class Box {
  private Corner upperLeft;
  private Corner upperRight;
  private Corner lowerRight;
  private Corner lowerLeft;
  private boolean top;
  private boolean right;
  private boolean bottom;
  private boolean left;
  private String owner;

  Box(Corner upperLeft, Corner upperRight, Corner lowerRight, Corner lowerLeft) {
    this.upperLeft = upperLeft;
    this.upperRight = upperRight;
    this.lowerRight = lowerRight;
    this.lowerLeft = lowerLeft;

    top = false;
    right = false;
    bottom = false;
    left = false;

    owner = null;
  }

  public Corner getUpperLeft() {
    return upperLeft;
  }

  public Corner getUpperRight() {
    return upperRight;
  }

  public Corner getLowerRight() {
    return lowerRight;
  }

  public Corner getLowerLeft() {
    return lowerLeft;
  }

  public boolean isTop() {
    return top;
  }

  public boolean isRight() {
    return right;
  }

  public boolean isBottom() {
    return bottom;
  }

  public boolean isLeft() {
    return left;
  }

  public void setTop(boolean top) {
    this.top = top;
  }

  public void setRight(boolean right) {
    this.right = right;
  }

  public void setBottom(boolean bottom) {
    this.bottom = bottom;
  }

  public void setLeft(boolean left) {
    this.left = left;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  /**
   * @return true, if the cell is occupied,and false, if not.
   */
  public boolean isOccupied() {
    if (owner != null)
      return true;
    else
      return false;
  }

  public boolean isOutlined() {
    return top && right && bottom && left;
  }

  /**
   * Cell side installation.
   *
   * @param first  Start corner of side.
   * @param second End corner of side.
   */
  public void checkForSide(Corner first, Corner second) {
    if (upperLeft.sameTo(first)) {
      if (second.sameTo(upperRight)) {
        setTop(true);
      }
      if (second.sameTo(lowerLeft)) {
        setLeft(true);
      }
    }

    if (upperRight.sameTo(first)) {
      if (second.sameTo(lowerRight)) {
        setRight(true);
      }
      if (second.sameTo(upperLeft)) {
        setTop(true);
      }
    }

    if (lowerRight.sameTo(first)) {
      if (second.sameTo(upperRight)) {
        setRight(true);
      }
      if (second.sameTo(lowerLeft)) {
        setBottom(true);
      }
    }

    if (lowerLeft.sameTo(first)) {
      if (second.sameTo(lowerRight)) {
        setBottom(true);
      }
      if (second.sameTo(upperLeft)) {
        setLeft(true);
      }
    }
  }

  public int numberOfFreeSides() {
    int sides = 4;
    if (isTop())
      sides--;
    if (isRight())
      sides--;
    if (isBottom())
      sides--;
    if (isLeft())
      sides--;
    return sides;
  }
}
