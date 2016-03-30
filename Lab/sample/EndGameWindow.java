package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Vector;

public class EndGameWindow implements Constants {
  public static void display(Vector<Player> players) {
    Stage window = new Stage();
    Pane root = new Pane();

    VBox mainLayout = new VBox();

    if (players.get(FIRST).getScore() == players.get(SECOND).getScore()) {
      window.setTitle("Draw");
    }
    if (players.get(FIRST).getScore() > players.get(SECOND).getScore()) {
      window.setTitle(players.get(FIRST).getName() + " wins!");
    }
    if (players.get(FIRST).getScore() < players.get(SECOND).getScore()) {
      window.setTitle(players.get(SECOND).getName() + " wins!");
    }

    Text firstPlayer =
        new Text(players.get(0).getName() + ": " + Integer.toString(players.get(0).getScore(), 10));
    firstPlayer.setFont(Font.font(15));
    firstPlayer.setFill(players.get(FIRST).getColor());
    Text secondPlayer =
        new Text(players.get(1).getName() + ": " + Integer.toString(players.get(1).getScore(), 10));
    secondPlayer.setFont(Font.font(15));
    secondPlayer.setFill(players.get(SECOND).getColor());

    Button okButton = new Button("OK");
    okButton.setPrefSize(50, 30);
    okButton.setOnMouseClicked(event -> {
      Main.setScene(MainMenu.getMainMenuScene());
      NewGameWindow.resetGameOptions();
      window.close();
    });

    mainLayout.getChildren().addAll(firstPlayer, secondPlayer, okButton);
    mainLayout.setSpacing(20);
    mainLayout.setPadding(
        new Insets(DEFAULT_OFFSET / 2, DEFAULT_OFFSET, DEFAULT_OFFSET / 2, DEFAULT_OFFSET));
    mainLayout.setAlignment(Pos.CENTER);

    root.getChildren().add(mainLayout);
    root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

    Scene scene = new Scene(root);

    window.initModality(Modality.APPLICATION_MODAL);
    window.setScene(scene);
    window.setResizable(false);
    window.show();
  }
}
