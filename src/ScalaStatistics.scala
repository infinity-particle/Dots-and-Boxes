package sample

object ScalaStatistics {
  def averageScore(scores: Array[Int]) = {
    scores.sum / scores.length
  }

  def bestScore(scores: Array[Int]) = {
    scores.max
  }

  def winsPercent(first: Array[Int], second: Array[Int]) = {
    (for (i <- first.indices if first(i) > second(i)) yield first(i)).length * 100 / first.length
  }
}
