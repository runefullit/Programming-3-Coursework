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
        WordleModel.windowShouldResize.addListener((observableValue, aBoolean, t1) -> {
            stage.sizeToScene();
            WordleModel.resizeWindow.setValue(false);
            System.out.println("Window has been resized, starting new game complete.");
        });
        WordleController.build();
        Scene scene = new Scene(WordleController.view);
        stage.setTitle("Wordle");
        stage.setScene(scene);
        stage.show();
        WordleController.focus();
    }
}