package game.dotsandboxes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SortOver {
  public static String display() {
    Stage window = new Stage();
    VBox root = new VBox();
    Insets inset = new Insets(10);

    ComboBox<String> replayChooser = new ComboBox<>();
    replayChooser.getItems().addAll("Best", "Worst");
    replayChooser.setValue("Best");

    Button play = new Button("Play");
    play.setOnMouseClicked(event -> window.close());

    root.getChildren().addAll(replayChooser, play);
    root.setAlignment(Pos.CENTER);
    root.setPadding(inset);
    root.setSpacing(20);
    root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

    window.setTitle("Result");
    window.initModality(Modality.APPLICATION_MODAL);
    window.setScene(new Scene(root, 100, 100));
    window.setResizable(false);
    window.showAndWait();
    return replayChooser.getValue();
  }
}
