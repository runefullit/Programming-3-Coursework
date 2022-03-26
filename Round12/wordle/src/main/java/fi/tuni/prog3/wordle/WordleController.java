package fi.tuni.prog3.wordle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WordleController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void onStartButtonClick() {
    }

    private void readNextWord() {

    }

    private void generateGridForWord(String word) {

    }
}