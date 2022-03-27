package fi.tuni.prog3.wordle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Wordle extends Application {
    @Override
    public void start(Stage stage) {
        View view= new View();
        Scene scene = new Scene(view, 320, 120);
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource("css/wordle.css").toExternalForm());
        stage.setTitle("Wordle");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}