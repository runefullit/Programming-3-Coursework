package fi.tuni.prog3.wordle;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class View extends Region {
    private final double HORIZ_SPACING = 5.0;
    private final double VERT_SPACING = 7.0;
    private int wordLength = 6;

    public View() {
        this.getStyleClass().clear();
        this.getStyleClass().add(Objects.requireNonNull(View.class.getResource("css/wordle.css")).toExternalForm());
        setPrefSize(200,200);

        VBox mainContainer = new VBox();
        this.getChildren().add(mainContainer);
        mainContainer.getChildren().add(topRow());
        mainContainer.getChildren().add(createTilePane());
    }

    private HBox topRow() {
        Button startGameBtn = new Button("Start new game");
        startGameBtn.setId("startGameBtn");

        Label infoBox = new Label("Testmessage");
        infoBox.setId("infoBox");

        return new HBox(startGameBtn, infoBox);
    }

    private VBox createTilePane() {
        VBox tilePane = new VBox(this.VERT_SPACING);
        for(int i = 1; i <= 6; i++) {
            tilePane.getChildren().add(createRow(i));
        }
        return tilePane;
    }

    private HBox createRow(int rowNumber) {
        HBox row = new HBox(this.HORIZ_SPACING);
        for (int column = 1; column <= this.wordLength; column++) {
            StackPane letterBox = letterBox();
            letterBox.setId(String.format("%d_%d", rowNumber, column));
            row.getChildren().add(letterBox);
        }
        return row;
    }

    private StackPane letterBox() {
        StackPane stackPane = new StackPane();

        Label label = new Label();
        label.getStyleClass().add("tile-letter");

        stackPane.getStyleClass().add("tile-box");
        stackPane.getChildren().add(label);

        return stackPane;
    }
}
