package fi.tuni.prog3.wordle;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class WordleAnimations {

    static void showToast(Node node) {
        FadeTransition fadeIn = new FadeTransition(new Duration(400.0), node);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        FadeTransition fadeOut = new FadeTransition(new Duration(400.0), node);
        fadeIn.setFromValue(1.0);
        fadeIn.setToValue(0.0);

        PauseTransition pause = new PauseTransition(new Duration(2000.0));

        SequentialTransition sequence = new SequentialTransition(fadeIn, pause, fadeOut);
        sequence.play();
    }
}
