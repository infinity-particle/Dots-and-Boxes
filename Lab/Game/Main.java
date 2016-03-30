package Game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
  private Stage primaryStage;

  @Override public void start(Stage primaryStage) throws Exception {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("Dots & Boxes");
    this.primaryStage.setResizable(false);

    MainMenu menu = new MainMenu();
    this.primaryStage.setScene(new Scene(menu));
    this.primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
