package fi.tuni.prog3.wordle;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

import java.util.Objects;

import static fi.tuni.prog3.wordle.WordleInteractor.setNewWord;

public class View implements Builder<Region> {
    private VBox tilePane;
    private Region keyboard;
    private VBox mainContainer;

    @Override
    public Region build() {
        // Initialising UI elements
        this.tilePane = createTilePane();
        this.keyboard = new VirtualKeyBoard();
        this.mainContainer = new VBox(40.0, topRow(), this.tilePane, this.keyboard);

        // CSS and positional tweaks.
        this.mainContainer.getStylesheets().add(Objects.requireNonNull(View.class.getResource("css/wordle.css")).toExternalForm());
        this.mainContainer.getStyleClass().add("main-screen");
        this.mainContainer.setAlignment(Pos.TOP_CENTER);
        return this.mainContainer;
    }

    private VBox topRow() {
        Button startGameBtn = new Button("Start new game");
        startGameBtn.setId("startGameBtn");
        startGameBtn.setOnAction(actionEvent -> {
            setNewWord();
            this.mainContainer.requestFocus(); // Startbutton grabs focus, if this isn't here.
            // Removing containers used in old game,
            this.mainContainer.getChildren().removeAll(this.tilePane, this.keyboard);
            // Instantiating new containers and adding them.
            this.tilePane = createTilePane();
            this.keyboard = new VirtualKeyBoard();
            this.mainContainer.getChildren().addAll(this.tilePane, this.keyboard);
            // Tells stage that it should be resized.
            WordleModel.resizeWindow.setValue(true);
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
        VBox tilePane = new VBox(7.0);
        for (int i = 0; i < 6; i++) {
            tilePane.getChildren().add(createRow(i));
        }
        return tilePane;
    }

    private HBox createRow(int rowNumber) {
        HBox row = new HBox(5.0);
        for (int col = 0; col < WordleModel.word.size(); col++) {
            StackPane letterBox = letterBox(rowNumber, col);
            row.getChildren().add(letterBox);
        }
        return row;
    }

    private StackPane letterBox(int row, int col) {
        StackPane stackPane = new StackPane();
        LetterModel letterModel = WordleModel.letters[row][col];

        // Text field
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
