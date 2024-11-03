package sample;

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

public class SortChooser {
  public static String display() {
    Stage window = new Stage();
    VBox root = new VBox();
    Insets inset = new Insets(10);

    ComboBox<String> sortType = new ComboBox<>();
    sortType.getItems().addAll("Java", "Scala");
    sortType.setValue("Java");

    Button sortButton = new Button("Sort");
    sortButton.setOnMouseClicked(event -> {
      window.close();
    });

    root.getChildren().addAll(sortButton, sortType);
    root.setSpacing(20);
    root.setPadding(inset);
    root.setAlignment(Pos.CENTER);
    root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

    window.setTitle("Choose sort type");
    window.initModality(Modality.APPLICATION_MODAL);
    window.setScene(new Scene(root, 100, 100));
    window.showAndWait();
    return sortType.getValue();
  }
}
