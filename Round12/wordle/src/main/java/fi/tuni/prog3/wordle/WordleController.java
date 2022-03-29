package fi.tuni.prog3.wordle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class WordleController {

    public Region view = new View();

    @FXML
    private Button newGameBtn;
    @FXML
    private Label infoBox;

    @FXML
    public void onStartButtonClick() {
    }
}