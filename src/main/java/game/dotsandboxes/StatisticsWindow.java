package game.dotsandboxes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StatisticsWindow {
  public static void display(double firstPlayerWinsPercent, double firstPlayerAverageScore,
      double secondPlayerAverageScore) {
    Stage window = new Stage();
    VBox root = new VBox();
    Insets inset = new Insets(10);

    Text firstPlayerWinsPercentLabel =
        new Text("First player wins: " + firstPlayerWinsPercent + " %");
    Text firstPlayerAverageScoreLabel =
        new Text("First player average score: " + firstPlayerAverageScore);
    Text secondPlayerAverageScoreLabel =
        new Text("First player best score: " + secondPlayerAverageScore);

    firstPlayerWinsPercentLabel.setFill(Color.RED);
    firstPlayerAverageScoreLabel.setFill(Color.RED);
    secondPlayerAverageScoreLabel.setFill(Color.RED);

    Button okButton = new Button("OK");
    okButton.setOnMouseClicked(event -> window.close());

    root.getChildren().addAll(firstPlayerWinsPercentLabel, firstPlayerAverageScoreLabel,
        secondPlayerAverageScoreLabel, okButton);
    root.setAlignment(Pos.CENTER);
    root.setPadding(inset);
    root.setSpacing(20);
    root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

    window.setTitle("Statistics");
    window.initModality(Modality.APPLICATION_MODAL);
    window.setScene(new Scene(root, 250, 200));
    window.setResizable(false);
    window.showAndWait();
  }
}
