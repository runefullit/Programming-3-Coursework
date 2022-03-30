package fi.tuni.prog3.wordle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;


public class Wordle extends Application {
    @Override
    public void start(Stage stage) throws URISyntaxException, IOException {
        WordleController controller = new WordleController();
        Scene scene = new Scene(controller.view);
        scene.getStylesheets().clear();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("css/wordle.css")).toExternalForm());
        stage.setTitle("Wordle");
        stage.setScene(scene);
        stage.show();
        controller.focus();
    }

    public static void main(String[] args) {
        launch();
    }
}