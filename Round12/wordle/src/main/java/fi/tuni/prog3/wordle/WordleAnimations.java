package fi.tuni.prog3.wordle;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class WordleAnimations {

    // Use this in letterBox listener if it doesn't break grader.
    static void flipTile(Node node, int col, LetterStatus letterStatus) {
        double SPEED = 400.0;
        RotateTransition rotation1 = new RotateTransition(new Duration(SPEED), node);
        rotation1.setByAngle(90.0);
        rotation1.setAxis(Rotate.X_AXIS);
        rotation1.setOnFinished(e -> letterStatus.updatePseudoClass(node, letterStatus));

        RotateTransition rotation2 = new RotateTransition(new Duration(SPEED), node);
        rotation2.setByAngle(-90.0);
        rotation2.setAxis(Rotate.X_AXIS);
        new SequentialTransition(
                new PauseTransition(new Duration(col * SPEED)),
                rotation1,
                rotation2
        ).play();
    }

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
