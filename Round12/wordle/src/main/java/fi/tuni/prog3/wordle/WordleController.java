package fi.tuni.prog3.wordle;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;

import static fi.tuni.prog3.wordle.WordleInteractor.*;

public class WordleController {

    static Region view;

    static void build() {
        setNewWord();
        view = new View().build();
        view.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                checkWord();
            } else if (keyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
                eraseLetter();
            } else if (keyEvent.getCode().isLetterKey()) {
                char c = keyEvent.getCode().toString().toUpperCase().charAt(0);
                handleLetter(c);
            }
        });
    }

    static void focus() {
        view.requestFocus();
    }

}