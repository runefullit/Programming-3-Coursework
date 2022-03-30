package fi.tuni.prog3.wordle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;


public class Wordle extends Application {
    @Override
    public void start(Stage stage) throws URISyntaxException, IOException {
        WordleController controller = new WordleController();
        Scene scene = new Scene(controller.view);
        stage.setTitle("Wordle");
        stage.setScene(scene);
        stage.show();
        controller.focus();
    }

    public static void main(String[] args) {
        launch();
    }
}