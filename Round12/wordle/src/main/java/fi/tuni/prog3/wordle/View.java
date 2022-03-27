package fi.tuni.prog3.wordle;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class View extends Region {

    public View() {
        this.getStyleClass().clear();
        this.getStyleClass().add(View.class.getResource("css/wordle.css").toExternalForm());
        setPrefSize(200,200);
        VBox mainContainer = new VBox();
        this.getChildren().add(mainContainer);
        mainContainer.getChildren().add(topRow());
    }

    private HBox topRow() {
        Button startGameBtn = new Button("Start new game");
        startGameBtn.setId("startGameBtn");
        // Example of setting a CSS property.
        startGameBtn.getStyleClass().add("tile-box");

        Label infoBox = new Label("Testmessage");
        infoBox.setId("infoBox");

        return new HBox(startGameBtn, infoBox);
    }

    private StackPane letterbox(int width) {
        StackPane stackPane = new StackPane();
        return stackPane;
    }
}
