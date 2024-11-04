package game.dotsandboxes;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainMenu extends Pane implements Constants {
  private static Scene mainMenuScene;

  public MainMenu() {
    setPrefSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);

    MenuItem newGameButton = new MenuItem("NEW GAME");
    MenuItem replayButton = new MenuItem("REPLAY");
    MenuItem sortButton = new MenuItem("SORT");
    MenuItem statisticButton = new MenuItem("STATISTICS");
    MenuItem pseudoButton = new MenuItem("PSEUDO");
    MenuItem exitButton = new MenuItem("EXIT");

    newGameButton.setOnMouseReleased(event -> {
      Options gameOptions = NewGameWindow.display();
      if (gameOptions != null) {
        new Thread(() -> {
          Game newGame = new Game(gameOptions);
          newGame.start();
          synchronized (newGame) {
            try {
              newGame.wait();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            Platform.runLater(() -> EndGameWindow.display(newGame.getPlayers()));
          }
        }).start();

      }
    });

    replayButton.setOnMouseClicked(event -> {
      FileChooser replayFileChooser = new FileChooser();
      FileChooser.ExtensionFilter filter =
          new FileChooser.ExtensionFilter("Replay files (*.replay)", "*.replay");
      replayFileChooser.getExtensionFilters().add(filter);
      java.io.File file = replayFileChooser.showOpenDialog(App.getPrimaryStage());
      if (file != null) {
        new Replay(file);
      }
    });

    statisticButton.setOnMouseClicked(event -> {
      FileChooser filesForStatistics = new FileChooser();

      FileChooser.ExtensionFilter filter =
          new FileChooser.ExtensionFilter("Replay files (*.replay)", "*.replay");

      filesForStatistics.getExtensionFilters().add(filter);

      List<java.io.File> files = filesForStatistics.showOpenMultipleDialog(App.getPrimaryStage());
      if (files != null) {
        int[] firstPlayerScores = new int[files.size()];
        int[] secondPlayerScores = new int[files.size()];
        int i = 0;
        for (java.io.File file : files) {
          game.dotsandboxes.File savedReplay = new game.dotsandboxes.File(file.getAbsolutePath());
          savedReplay.createReadStream();
          firstPlayerScores[i] = savedReplay.loadScore();
          secondPlayerScores[i] = savedReplay.loadScore();
          savedReplay.closeReadStream();
          i++;
        }
        double firstPlayerWinsPercent =
        ScalaStatistics.winsPercent(firstPlayerScores, secondPlayerScores);
        double firstPlayerAverageScore = ScalaStatistics.averageScore(firstPlayerScores);
        int secondPlayerAverageScore = ScalaStatistics.bestScore(secondPlayerScores);
        StatisticsWindow
            .display(firstPlayerWinsPercent, firstPlayerAverageScore, secondPlayerAverageScore);
      }
    });

    pseudoButton.setOnMouseClicked(event -> {
      FileChooser replayFileChooser = new FileChooser();
      FileChooser.ExtensionFilter filter =
          new FileChooser.ExtensionFilter("Replay files (*.replay)", "*.replay");
      replayFileChooser.getExtensionFilters().add(filter);
      java.io.File file = replayFileChooser.showOpenDialog(App.getPrimaryStage());
      if (file != null) {
        ScalaPseudoCode.pseudoCode(file.getAbsolutePath());
      }
    });

    sortButton.setOnMouseClicked(event -> {
      FileChooser filesForSort = new FileChooser();

      FileChooser.ExtensionFilter filter =
          new FileChooser.ExtensionFilter("Replay files (*.replay)", "*.replay");
      filesForSort.getExtensionFilters().add(filter);

      List<java.io.File> files = filesForSort.showOpenMultipleDialog(App.getPrimaryStage());
      if (files != null) {
        switch (SortChooser.display()) {
          case "Java": {
            long startTime, elapsedTime;

            startTime = System.currentTimeMillis();
            List<File> sortedFiles = sort(files);
            elapsedTime = (System.currentTimeMillis() - startTime) / 1000;

            System.out.println("********** JAVA(" + sortedFiles.size() + ") **********\n");
            System.out.println("Time: " + elapsedTime + " s");
            System.out.println("Best game: " + sortedFiles.get(FIRST).getAbsolutePath());
            System.out.println(
                "Worst game: " + sortedFiles.get(sortedFiles.size() - 1).getAbsolutePath());
            System.out.println("\n******************************\n");

            switch (SortOver.display()) {
              case "Best": {
                new Replay(sortedFiles.get(FIRST));
              }
              break;
              case "Worst": {
                new Replay(sortedFiles.get(sortedFiles.size() - 1));
              }
              break;
            }
          }
          break;

          case "Scala": {
            long startTime, elapsedTime;
            java.io.File[] filesArray = new java.io.File[files.size()];

            files.toArray(filesArray);
            startTime = System.currentTimeMillis();
            filesArray = ScalaSort.scalaSort(filesArray);
            elapsedTime = (System.currentTimeMillis() - startTime) / 1000;

            System.out.println("********** SCALA(" + filesArray.length + ") **********\n");
            System.out.println("Time: " + elapsedTime + " s");
            System.out.println("Best game: " + filesArray[FIRST].getAbsolutePath());
            System.out
                .println("Worst game: " + filesArray[filesArray.length - 1].getAbsolutePath());
            System.out.println("\n******************************\n");

            switch (SortOver.display()) {
              case "Best": {
                new Replay(filesArray[FIRST]);
              }
              break;
              case "Worst": {
                new Replay(filesArray[filesArray.length - 1]);
              }
              break;
            }
          }
          break;
        }
      }
    });
    exitButton.setOnMouseClicked(event -> System.exit(0));

    MenuBox menu =
        new MenuBox("DOTS & BOXES", newGameButton, replayButton, sortButton, statisticButton,
            pseudoButton, exitButton);

    getChildren().addAll(menu);

    Background background = new Background(new BackgroundFill(Color.BLACK, null, null));
    setBackground(background);

    mainMenuScene = new Scene(this, DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
  }

  public static Scene getMainMenuScene() {
    return mainMenuScene;
  }

  public List<File> sort(List<File> files) {
    List<File> sortedFiles = new ArrayList<>(files);
    sortedFiles.sort((o1, o2) -> {
      game.dotsandboxes.File firstFile = new game.dotsandboxes.File(o1.getAbsolutePath());
      game.dotsandboxes.File secondFile = new game.dotsandboxes.File(o2.getAbsolutePath());

      firstFile.createReadStream();
      int firstScore = firstFile.loadScore();
      firstFile.closeReadStream();

      secondFile.createReadStream();
      int secondScore = secondFile.loadScore();
      secondFile.closeReadStream();

      if (firstScore > secondScore)
        return -1;
      if (firstScore < secondScore)
        return 1;
      return 0;
    });
    return sortedFiles;
  }
}
