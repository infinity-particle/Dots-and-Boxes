package sample;

/**
 * This class implements the game options.
 */
public class Options {
  private Player firstPlayer;
  private Player secondPlayer;
  private int rows;
  private int columns;

  Options() {
    firstPlayer = null;
    secondPlayer = null;
    rows = 0;
    columns = 0;
  }

  public Player getFirstPlayer() {
    return firstPlayer;
  }

  public Player getSecondPlayer() {
    return secondPlayer;
  }

  public int getRows() {
    return rows;
  }

  public int getColumns() {
    return columns;
  }

  public void setFirstPlayer(Player firstPlayer) {
    this.firstPlayer = firstPlayer;
  }

  public void setSecondPlayer(Player secondPlayer) {
    this.secondPlayer = secondPlayer;
  }

  public void setRows(int rows) {
    this.rows = rows;
  }

  public void setColumns(int columns) {
    this.columns = columns;
  }
}
