package sample

object ScalaSort {
  def scalaSort(files: Array[java.io.File]): Array[java.io.File] = {
    val sortedFiles = files

    def swap(i: Int, j: Int) {
      val temp = sortedFiles(i)
      sortedFiles(i) = sortedFiles(j)
      sortedFiles(j) = temp
    }

    def sort(begin: Int, end: Int) {
      val step = sortedFiles((begin + end) / 2)
      var i = begin
      var j = end
      while (i <= j) {
        while (compare(sortedFiles(i), step) == 1) i += 1
        while (compare(sortedFiles(j), step) == -1) j -= 1
        if (i <= j) {
          swap(i, j)
          i += 1
          j -= 1
        }
      }
      if (begin < j) sort(begin, j)
      if (j < end) sort(i, end)
    }

    sort(0, sortedFiles.length - 1)
    sortedFiles
  }

  def compare(first: java.io.File, second: java.io.File): Int = {
    val firstFile = new sample.File(first.getAbsolutePath)
    val secondFile = new sample.File(second.getAbsolutePath)

    firstFile.createReadStream()
    val firstScore = firstFile.loadScore()
    firstFile.closeReadStream()

    secondFile.createReadStream()
    val secondScore = secondFile.loadScore()
    secondFile.closeReadStream()

    if (firstScore > secondScore) {
      1
    } else {
      if (firstScore < secondScore) {
        -1
      }
      else {
        0
      }
    }
  }
}
