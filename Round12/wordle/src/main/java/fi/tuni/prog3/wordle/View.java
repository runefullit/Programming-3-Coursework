package fi.tuni.prog3.wordle;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
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

    public View() {
        VBox mainContainer = new VBox(40.0, topRow(), createTilePane());
        this.getStylesheets().add(Objects.requireNonNull(View.class.getResource("css/wordle.css")).toExternalForm());
        this.getChildren().add(mainContainer);
        mainContainer.getStyleClass().add("main-screen");
        mainContainer.setAlignment(Pos.TOP_CENTER);

        // PseudoClass darkModePseudoClass = PseudoClass.getPseudoClass("dark-mode");
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
        for (int i = 0; i < 6; i++) {
            tilePane.getChildren().add(createRow(i));
        }
        return tilePane;
    }

    private HBox createRow(int rowNumber) {
        HBox row = new HBox(this.HORIZ_SPACING);
        for (int column = 0; column < WordleModel.word.size(); column++) {
            StackPane letterBox = letterBox(rowNumber, column);
            letterBox.setId(String.format("%d_%d", rowNumber, column));
            row.getChildren().add(letterBox);
        }
        return row;
    }

    private StackPane letterBox(int row, int column) {
        StackPane stackPane = new StackPane();
        LetterModel letterModel = WordleModel.letters[row][column];

        // Test field
        Label label = new Label();
        label.getStyleClass().add("tile-letter");
        label.textProperty().bind(Bindings.createStringBinding(
                () -> letterModel.letter().get().toString(),
                letterModel.letter()
        ));

        // Letter background
        stackPane.getStyleClass().add("tile-box");
        letterModel.status().addListener((observableValue, letterStatus, t1) -> {
            LetterStatus status = letterModel.status().get();
            status.updatePseudoClass(stackPane, status);
        });
        stackPane.getChildren().add(label);

        return stackPane;
    }
}
