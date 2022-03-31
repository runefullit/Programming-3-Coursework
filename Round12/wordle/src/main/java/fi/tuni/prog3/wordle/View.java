package fi.tuni.prog3.wordle;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.util.Objects;

import static fi.tuni.prog3.wordle.WordleInteractor.setNewWord;

public class View extends Region {
    private final double HORIZ_SPACING = 5.0;
    private final double VERT_SPACING = 7.0;
    private VBox tilePane = createTilePane();
    private final VBox mainContainer = new VBox(40.0, topRow(), tilePane);

    public View() {
        this.getStylesheets().add(Objects.requireNonNull(View.class.getResource("css/wordle.css")).toExternalForm());
        this.getChildren().add(mainContainer);
        this.mainContainer.getStyleClass().add("main-screen");
        this.mainContainer.setAlignment(Pos.TOP_CENTER);

        // PseudoClass darkModePseudoClass = PseudoClass.getPseudoClass("dark-mode");
    }

    private VBox topRow() {
        Button startGameBtn = new Button("Start new game");
        startGameBtn.setId("startGameBtn");
        startGameBtn.setOnAction(actionEvent -> {
            setNewWord();
            this.requestFocus();
            this.mainContainer.getChildren().remove(this.tilePane);
            this.tilePane = createTilePane();
            this.mainContainer.getChildren().add(this.tilePane);
        });

        Label infoBox = new Label();
        infoBox.textProperty().bind(Bindings.createStringBinding(
                () -> WordleModel.infoText.get(),
                WordleModel.infoText
        ));
        infoBox.setId("infoBox");

        VBox topRow = new VBox(startGameBtn, infoBox);
        topRow.setAlignment(Pos.CENTER);

        return topRow;
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
        for (int col = 0; col < WordleModel.word.size(); col++) {
            StackPane letterBox = letterBox(rowNumber, col);
            row.getChildren().add(letterBox);
        }
        return row;
    }

    private StackPane letterBox(int row, int col) {
        StackPane stackPane = new StackPane();
        LetterModel letterModel = WordleModel.letters[row][col];

        // Test field
        Label label = new Label();
        label.getStyleClass().add("tile-letter");
        label.setId(String.format("%d_%d", row, col));
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
