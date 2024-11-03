package sample;

import java.util.Random;

public class Bot implements Constants {

  Bot() {

  }

  public void move(Board gameBoard) {
    Box[][] boxes = gameBoard.getBoxes();

    for (int i = 0; i < gameBoard.getRows() - 1; i++)
      for (int j = 0; j < gameBoard.getColumns() - 1; j++)
        if ((!boxes[i][j].isOccupied()) && (boxes[i][j].numberOfFreeSides() == 1)) {
          if (!boxes[i][j].isTop()) {
            gameBoard.makeMoveBot(boxes[i][j].getUpperLeft(), boxes[i][j].getUpperRight());
            return;
          }
          if (!boxes[i][j].isRight()) {
            gameBoard.makeMoveBot(boxes[i][j].getUpperRight(), boxes[i][j].getLowerRight());
            return;
          }
          if (!boxes[i][j].isBottom()) {
            gameBoard.makeMoveBot(boxes[i][j].getLowerRight(), boxes[i][j].getLowerLeft());
            return;
          }
          if (!boxes[i][j].isLeft()) {
            gameBoard.makeMoveBot(boxes[i][j].getLowerLeft(), boxes[i][j].getUpperLeft());
            return;
          }
        }
    randomMove(gameBoard);
  }

  /**
   * Bot makes random move.
   **/
  public void randomMove(Board gameBoard) {
    Box[][] boxes = gameBoard.getBoxes();
    Random randomValue = new Random();
    int i, j;
    int side;

    do {
      i = randomValue.nextInt(gameBoard.getRows() - 1);
      j = randomValue.nextInt(gameBoard.getColumns() - 1);
    } while (boxes[i][j].numberOfFreeSides() == 0);

    do {
      side = randomValue.nextInt(4);
      switch (side) {
        case TOP: {
          if (!boxes[i][j].isTop()) {
            gameBoard.makeMoveBot(boxes[i][j].getUpperLeft(), boxes[i][j].getUpperRight());
            return;
          } else {
            side = WRONG_SIDE;
          }
        }
        break;
        case RIGHT: {
          if (!boxes[i][j].isRight()) {
            gameBoard.makeMoveBot(boxes[i][j].getUpperRight(), boxes[i][j].getLowerRight());
            return;
          } else {
            side = WRONG_SIDE;
          }
        }
        break;
        case BOTTOM: {
          if (!boxes[i][j].isBottom()) {
            gameBoard.makeMoveBot(boxes[i][j].getLowerRight(), boxes[i][j].getLowerLeft());
            return;
          } else {
            side = WRONG_SIDE;
          }
        }
        break;
        case LEFT: {
          if (!boxes[i][j].isLeft()) {
            gameBoard.makeMoveBot(boxes[i][j].getLowerLeft(), boxes[i][j].getUpperLeft());
            return;
          } else {
            side = WRONG_SIDE;
          }
        }
        break;
      }
    } while (side == WRONG_SIDE);
  }
}
