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

import static fi.tuni.prog3.wordle.WordleAnimations.showToast;
import static fi.tuni.prog3.wordle.WordleAnimations.wiggleRow;
import static fi.tuni.prog3.wordle.WordleInteractor.setNewWord;

public class View implements Builder<Region> {
    private VBox tilePane;
    private Region keyboard;
    private Label infoBox;
    private VBox mainContainer;

    @Override
    public Region build() {
        // Initialising UI elements
        this.tilePane = createTilePane();
        this.infoBox = createInfoBox();
        this.keyboard = new VirtualKeyBoard();
        this.mainContainer = new VBox(40.0, topRow(), this.tilePane, this.infoBox, this.keyboard);

        // CSS and positional tweaks.
        this.mainContainer.getStylesheets().add(Objects.requireNonNull(View.class.getResource("css/wordle.css")).toExternalForm());
        this.mainContainer.getStyleClass().add("main-screen");
        this.mainContainer.setAlignment(Pos.TOP_CENTER);
        return this.mainContainer;
    }

    private StackPane topRow() {
        StackPane topRow = new StackPane();

        Button startGameBtn = new Button("Start new game");
        startGameBtn.setId("startGameBtn");
        startGameBtn.getStyleClass().add("startGame-button");
        startGameBtn.setOnAction(actionEvent -> {
            setNewWord();
            this.mainContainer.requestFocus(); // Startbutton grabs focus, if this isn't here.
            // Removing containers used in old game,
            this.mainContainer.getChildren().removeAll(this.tilePane, this.infoBox, this.keyboard);
            // Instantiating new containers and adding them.
            this.tilePane = createTilePane();
            this.infoBox = createInfoBox();
            this.keyboard = new VirtualKeyBoard();
            this.mainContainer.getChildren().addAll(this.tilePane, this.infoBox, this.keyboard);
            // Tells stage that it should be resized.
            WordleModel.resizeWindow.setValue(true);
        });

        Label title = new Label("WordleFX");
        title.getStyleClass().add("title");

        topRow.getChildren().addAll(startGameBtn, title);
        StackPane.setAlignment(startGameBtn, Pos.CENTER_LEFT);


        return topRow;
    }

    private VBox createTilePane() {
        VBox tilePane = new VBox(7.0);
        for (int i = 0; i < 6; i++) {
            tilePane.getChildren().add(createRow(i));
        }
        return tilePane;
    }

    private Label createInfoBox() {
        Label infoBox = new Label();
        infoBox.setOpacity(0.0);
        infoBox.textProperty().bind(Bindings.createStringBinding(
                () -> WordleModel.infoText.get(),
                WordleModel.infoText
        ));
        WordleModel.infoText.addListener((observableValue, s, t1) -> {
            if (observableValue.getValue() != "") showToast(infoBox);
        });
        infoBox.setId("infoBox");
        infoBox.getStyleClass().add("bad-word");
        return infoBox;
    }

    private HBox createRow(int rowNumber) {
        HBox row = new HBox(5.0);
        row.setAlignment(Pos.TOP_CENTER);
        for (int col = 0; col < WordleModel.word.size(); col++) {
            StackPane letterBox = letterBox(rowNumber, col);
            row.getChildren().add(letterBox);
        }
        WordleModel.infoText.addListener((observableValue, s, t1) -> {
            if (InfoBoxAnswers.PREMATURE.equalsMessage(WordleModel.infoText.get()) &&
                    WordleModel.currentRow.get() == rowNumber) {
                wiggleRow(row);
            }
        });
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
