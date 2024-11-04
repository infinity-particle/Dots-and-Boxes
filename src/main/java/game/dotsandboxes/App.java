package game.dotsandboxes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application implements Constants {
  private static Stage primaryStage;

  @Override public void start(Stage primaryStage) throws Exception {
    App.primaryStage = primaryStage;
    primaryStage.setTitle("Dots & Boxes");
    MainMenu menu = new MainMenu();
    primaryStage.setScene(menu.getScene());
    primaryStage.sizeToScene();
    primaryStage.show();
  }

  public static void setScene(Scene newScene) {
    primaryStage.setScene(newScene);
    primaryStage.sizeToScene();
  }

  public static void main(String[] args) {
    launch(args);
  }

  public static Stage getPrimaryStage() {
    return primaryStage;
  }
}
