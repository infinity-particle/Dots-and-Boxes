package sample

import java.io.PrintWriter

object ScalaPseudoCode {
  def pseudoCode(fileName: String) = {
    val move = "move from "
    val to = " to "
    val firstPlayer = "First player "
    val secondPlayer = "Second player "
    val output = new PrintWriter(new java.io.File("output.txt"))

    val file = new sample.File(fileName)
    file.createReadStream()
    val firstPlayerScore = file.loadScore()
    val secondPlayerScore = file.loadScore()
    val options = file.loadOptions()
    val moves = scala.collection.JavaConversions.asScalaBuffer(file.loadMoves())
    file.closeReadStream()
    for (i <- moves.indices) {
      moves(i).getCurrentPlayer match {
        case 0 => output.println(firstPlayer + move + "(" + moves(i).getMove.getStartCoordinates.
          getX / Constants.DEFAULT_OFFSET +
          "," + moves(i).getMove.getStartCoordinates.getY / Constants.DEFAULT_OFFSET + ")" +
          to + "(" + moves(i).getMove.getEndCoordinates.getX / Constants.DEFAULT_OFFSET +
          "," + moves(i).getMove.getEndCoordinates.getY / Constants.DEFAULT_OFFSET + ")")
        case 1 => output.println(secondPlayer + move + "(" + moves(i).getMove.getStartCoordinates.
          getX / Constants.DEFAULT_OFFSET +
          "," + moves(i).getMove.getStartCoordinates.getY / Constants.DEFAULT_OFFSET + ")" +
          to + "(" + moves(i).getMove.getEndCoordinates.getX / Constants.DEFAULT_OFFSET +
          "," + moves(i).getMove.getEndCoordinates.getY / Constants.DEFAULT_OFFSET + ")")
      }
      moves(i).getMarks.size() > 0 match {
        case true => moves(i).getCurrentPlayer match {
          case 0 => output.println(firstPlayer + "gets " + moves(i).getMarks.size() + " points")
          case 1 => output.println(secondPlayer + "gets " + moves(i).getMarks.size() + " points")
        }
        case false => {
          output.println("Switch to next player")
          output.println("-----------------------------------------------------")
        }
      }

    }
    firstPlayerScore > secondPlayerScore match {
      case true => output.println(firstPlayer + "win the game with a score of " + firstPlayerScore +
        " : " + secondPlayerScore)
      case false => output.println(firstPlayer + "lose the game with a score of " + firstPlayerScore
        + " : " + secondPlayerScore)
    }
    output.flush()
    output.close()
  }
}
