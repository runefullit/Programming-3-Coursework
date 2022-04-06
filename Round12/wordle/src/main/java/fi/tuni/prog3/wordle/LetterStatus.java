package fi.tuni.prog3.wordle;

import javafx.beans.property.ObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Labeled;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public enum LetterStatus {
    EMPTY, UNLOCKED, WRONG, PRESENT, CORRECT;

    public void updateBackGround(Labeled node, LetterStatus letterStatus) {
        Color color = Color.WHITE;
        if (letterStatus == WRONG) {
            color = Color.GRAY;
        } else if (letterStatus == PRESENT) {
            color = Color.ORANGE;
        } else if (letterStatus == CORRECT) {
            color = Color.GREEN;
        }
        node.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void addPseudoClass(Labeled node, ObjectProperty<LetterStatus> property) {
        property.addListener(e -> updateBackGround(node, property.get()));
    }
}
