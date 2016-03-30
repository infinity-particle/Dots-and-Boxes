package Game;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MenuBox extends StackPane {
    public MenuBox(String title,MenuItem... items){
        DropShadow shadow = new DropShadow(7,5,0, Color.BLACK);
        shadow.setSpread(0.8);

        Rectangle background = new Rectangle(300,300);
        background.setOpacity(0.2);
        background.setEffect(shadow);

        Text text = new Text(title + "    ");
        text.setFont(Font.font(20));
        text.setFill(Color.WHITE);

        Line horizontalSeparator = new Line();
        horizontalSeparator.setEndX(300);
        horizontalSeparator.setStroke(Color.DARKGRAY);
        horizontalSeparator.setOpacity(0.4);

        Line verticalSeparator = new Line();
        verticalSeparator.setStartX(300);
        verticalSeparator.setEndX(300);
        verticalSeparator.setEndY(300);
        verticalSeparator.setStroke(Color.DARKGRAY);
        verticalSeparator.setOpacity(0.4);

        VBox verticalLayout = new VBox();
        verticalLayout.setAlignment(Pos.TOP_RIGHT);
        verticalLayout.setPadding(new Insets(60,0,0,0));
        verticalLayout.getChildren().addAll(text,horizontalSeparator);
        verticalLayout.getChildren().addAll(items);

        setAlignment(Pos.TOP_RIGHT);

        getChildren().addAll(background,verticalSeparator,verticalLayout);
    }

    public void hide(){
        TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5),this);
        animation.setToX(-300);
        animation.setOnFinished(event -> setVisible(false));
        animation.play();
    }

    public void show(){
        setVisible(true);
        TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5),this);
        animation.setToX(0);
        animation.play();
    }
}
