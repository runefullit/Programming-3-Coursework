package fi.tuni.prog3.wordle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Wordle extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        WordleController controller = new WordleController();
        Scene scene = new Scene(controller.view);
        stage.setTitle("Wordle");
        stage.setScene(scene);
        stage.show();
        controller.focus();
    }
}