package fi.tuni.prog3.wordle;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URISyntaxException;

public class WordleController {

    private final WordleInteractor interactor = new WordleInteractor();
    public Region view = new View();

    WordleController() throws URISyntaxException, IOException {
        view.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                interactor.checkWord();
            } else if (keyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
                interactor.eraseLetter();
            } else {
                char c = keyEvent.getCode().toString().toUpperCase().charAt(0);
                if (c >= 'A' && c <= 'Z') {
                    interactor.handleLetter(c);
                }
            }
        });
    }

    public void focus() {
        view.requestFocus();
    }

}