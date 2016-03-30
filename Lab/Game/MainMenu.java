package Game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MainMenu extends Pane {
    private MenuBox menu;
    private MenuBox newGame;
    private MenuBox onePlayer;
    private BoardBox board;

    public MainMenu(){
        setPrefSize(500,300);

        ImageView backgroundImage = new ImageView(new Image("resource/background.jpg"));
        backgroundImage.setFitWidth(500);
        backgroundImage.setFitHeight(300);

        MenuItem newGameButton = new MenuItem("NEW GAME");
        MenuItem exitButton = new MenuItem("EXIT");

        newGameButton.setOnMouseClicked(event -> {
            menu.hide();
            newGame.show();
        });
        exitButton.setOnMouseClicked(event -> System.exit(0));

        MenuItem onePlayerButton = new MenuItem("ONE PLAYER");
        MenuItem twoPlayersButton = new MenuItem("TWO PLAYERS");
        MenuItem backButton = new MenuItem("BACK");

        onePlayerButton.setOnMouseClicked(event -> {
            newGame.hide();
            onePlayer.show();
        });
        backButton.setOnMouseClicked(event -> {
            newGame.hide();
            menu.show();
        });

        MenuItem easyModeButton = new MenuItem("EASY");
        MenuItem hardModeButton = new MenuItem("HARD");
        MenuItem cancelButton = new MenuItem("CANCEL");

        easyModeButton.setOnMouseClicked(event1 -> {
            onePlayer.hide();
            board.show();
        });
        cancelButton.setOnMouseClicked(event -> {
            onePlayer.hide();
            newGame.show();
        });

        BoardItem returnToMainMenuButton = new BoardItem("MAIN MENU");

        returnToMainMenuButton.setOnMouseClicked(event -> {
            board.hide();
            menu.show();
        });

        menu = new MenuBox("DOTS & BOXES",newGameButton,exitButton);
        newGame = new MenuBox("NEW GAME",onePlayerButton,twoPlayersButton,backButton);
        onePlayer = new MenuBox("PC MODE",easyModeButton,hardModeButton,cancelButton);
        board = new BoardBox(returnToMainMenuButton);

        board.hide();
        newGame.hide();
        onePlayer.hide();

        getChildren().addAll(backgroundImage,menu,newGame,onePlayer,board);
    }
}
