package fi.tuni.prog3.wordle;

import javafx.beans.property.ObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class VirtualKeyBoard extends VBox {

    private final List<Character> row1Keys = "QWERTYUIOP".toUpperCase().chars().mapToObj(e -> (char) e).toList();
    private final List<Character> row2Keys = "ASDFGHJKL".toUpperCase().chars().mapToObj(e -> (char) e).toList();
    private final List<Character> row3Keys = "ZXCVBNM".toUpperCase().chars().mapToObj(e -> (char) e).toList();

    private final Consumer<Character> keystrokeConsumer = WordleInteractor::handleLetter;
    private final Runnable enterHandler = WordleInteractor::checkWord;
    private final Runnable backspaceHandler = WordleInteractor::eraseLetter;
    private final Map<Character, ObjectProperty<LetterStatus>> alphabet = WordleModel.alphabet;

    VirtualKeyBoard() {
        this.init();
    }

    private void init() {
        this.setSpacing(8.0);
        this.setAlignment(Pos.TOP_CENTER);
        this.getChildren().addAll(createRow(row1Keys), createRow(row2Keys, 20.0), createRow(row3Keys, 0.0, true, true));
    }

    private HBox createRow(List<Character> letters, double leftPadding, Boolean includeBackspace, Boolean includeEnter) {
        HBox row = new HBox(6.0);
        row.setAlignment(Pos.TOP_CENTER);
        if (includeEnter) row.getChildren().add(createEnterKey());
        row.getChildren().addAll(letters.stream().map(this::letterButtonSetup).toList());
        if (includeBackspace) row.getChildren().add(createBackSpaceKey());
        row.setPadding(new Insets(0.0, 0.0, 0.0, leftPadding));
        return row;
    }

    private HBox createRow(List<Character> letters, double leftPadding) {
        return createRow(letters, leftPadding, false, false);
    }

    private HBox createRow(List<Character> letters) {
        return createRow(letters, 0.0);
    }

    private Button letterButtonSetup(char letter) {
        Button letterButton = createButton(String.valueOf(letter));
        letterButton.setOnMouseClicked(mouseEvent -> keystrokeConsumer.accept(letter));
        ObjectProperty<LetterStatus> letterStatus = alphabet.get(letter);
        letterStatus.get().addPseudoClass(letterButton, letterStatus);
        return letterButton;
    }

    private Button createButton(String letter) {
        Button button = new Button(letter);
        button.setMinSize(44.0, 58.0);
        button.getStyleClass().add("key-button");
        button.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, mouseEvent -> {
            button.setBorder(new Border(new BorderStroke(Color.GRAY,
                    BorderStrokeStyle.SOLID,
                    new CornerRadii(2.0),
                    new BorderWidths(2.0)
            )));
        });
        button.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, mouseEvent -> {
            button.setBorder(Border.EMPTY);
        });
        return button;
    }

    private Button createBackSpaceKey() {
        Button backspaceButton = createButton("<");
        backspaceButton.setMinWidth(64.0);
        backspaceButton.setOnAction(actionEvent -> backspaceHandler.run());
        return backspaceButton;
    }

    private Button createEnterKey() {
        Button enterButton = createButton("Enter");
        enterButton.setOnAction(actionEvent -> enterHandler.run());
        return enterButton;
    }

}
