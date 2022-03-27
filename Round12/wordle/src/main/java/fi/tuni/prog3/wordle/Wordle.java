package fi.tuni.prog3.wordle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Wordle extends Application {
    @Override
    public void start(Stage stage) {
        ViewBuilder sManager = new ViewBuilder();
        Scene scene = new Scene(sManager, 320, 120);
        stage.setTitle("Wordle");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}