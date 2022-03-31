package fi.tuni.prog3.wordle;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class WordleAnimations {

    static void wiggleRow(Node node) {
        node.setTranslateX(-5.0);
        TranslateTransition translation = new TranslateTransition(new Duration(50), node);
        translation.setByX(10);
        translation.setCycleCount(6);
        translation.setAutoReverse(true);
        translation.setOnFinished(e -> node.setTranslateX(0.0));
        translation.play();
    }

    static void showToast(Node node) {
        FadeTransition fadeIn = new FadeTransition(new Duration(200.0), node);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        FadeTransition fadeOut = new FadeTransition(new Duration(200.0), node);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        PauseTransition pause = new PauseTransition(new Duration(2000.0));

        SequentialTransition sequence = new SequentialTransition(fadeIn, pause, fadeOut);
        sequence.play();
        sequence.setOnFinished(actionEvent -> WordleModel.infoText.setValue(""));
    }
}
