package fi.tuni.prog3.wordle;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Builder;

import static fi.tuni.prog3.wordle.WordleAnimations.wiggleRow;
import static fi.tuni.prog3.wordle.WordleInteractor.setNewWord;
import static javafx.scene.input.MouseEvent.MOUSE_ENTERED_TARGET;

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

        this.mainContainer.getStyleClass().add("main-screen");
        this.mainContainer.setAlignment(Pos.TOP_CENTER);
        return this.mainContainer;
    }

    private StackPane topRow() {
        StackPane topRow = new StackPane();

        Button startGameBtn = new Button("Start new game");
        startGameBtn.setId("newGameBtn");
        startGameBtn.addEventHandler(MOUSE_ENTERED_TARGET, mouseEvent -> startGameBtn.setBorder(new Border(new BorderStroke(Color.GRAY,
                BorderStrokeStyle.SOLID,
                new CornerRadii(2.0),
                new BorderWidths(2.0)
        ))));
        startGameBtn.setOnAction(actionEvent -> startNewGame());

        Label title = new Label("WordleFX");

        topRow.getChildren().addAll(startGameBtn, title);
        StackPane.setAlignment(startGameBtn, Pos.CENTER_LEFT);


        return topRow;
    }

    private VBox createTilePane() {
        System.out.println("Creating new TilePane");
        VBox tilePane = new VBox(7.0);
        for (int i = 0; i < 6; i++) {
            tilePane.getChildren().add(createRow(i));
        }
        return tilePane;
    }

    private Label createInfoBox() {
        System.out.println("Creating new infoBox");
        Label infoBox = new Label();
        infoBox.textProperty().bind(Bindings.createStringBinding(
                () -> WordleModel.infoText.get(),
                WordleModel.infoText
        ));
        infoBox.setId("infoBox");
        return infoBox;
    }

    private HBox createRow(int rowNumber) {
        HBox row = new HBox(5.0);
        row.setAlignment(Pos.TOP_CENTER);
        for (int col = 0; col < WordleModel.word.size(); col++) {
            Label letterBox = letterBox(rowNumber, col);
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

    private Label letterBox(int row, int col) {
        LetterModel letterModel = WordleModel.letters[row][col];

        // Text field
        Label label = new Label();
        label.setPrefSize(62, 61);
        label.setAlignment(Pos.CENTER);
        label.setBorder(new Border(new BorderStroke(Color.GRAY,
                BorderStrokeStyle.SOLID,
                new CornerRadii(2.0),
                new BorderWidths(2.0)
        )));
        label.setId(String.format("%d_%d", row, col));
        label.textProperty().bind(Bindings.createStringBinding(
                () -> {
                    String str = letterModel.letter().get().toString();
                    return str.equals(" ") ? "" : str;
                },
                letterModel.letter()
        ));

        letterModel.status().addListener((observableValue, letterStatus, t1) -> {
            LetterStatus status = observableValue.getValue();
            status.updateBackGround(label, status);
        });

        // Calling this upon creation to initialize the background color according to LetterStatus
        LetterStatus status = letterModel.status().getValue();
        status.updateBackGround(label, status);

        return label;
    }

    private void startNewGame() {
        setNewWord();
        this.mainContainer.requestFocus(); // Start button grabs focus, if this isn't here.
        // Removing containers used in old game,
        this.mainContainer.getChildren().removeAll(this.tilePane, this.infoBox, this.keyboard);
        // Instantiating new containers and adding them.
        this.tilePane = createTilePane();
        this.infoBox = createInfoBox();
        this.keyboard = new VirtualKeyBoard();
        this.mainContainer.getChildren().addAll(this.tilePane, this.infoBox, this.keyboard);
        // Tells stage that it should be resized.
        WordleModel.resizeWindow.setValue(true);
    }

}
