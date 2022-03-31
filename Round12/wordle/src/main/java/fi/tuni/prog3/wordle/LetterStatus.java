package fi.tuni.prog3.wordle;

import javafx.beans.property.ObjectProperty;
import javafx.css.PseudoClass;
import javafx.scene.Node;

public enum LetterStatus {
    EMPTY, UNLOCKED, WRONG, PRESENT, CORRECT;

    private final PseudoClass emptyPseudoClass = PseudoClass.getPseudoClass("empty");
    private final PseudoClass unlockedPseudoClass = PseudoClass.getPseudoClass("unlocked");
    private final PseudoClass wrongPseudoClass = PseudoClass.getPseudoClass("wrong");
    private final PseudoClass presentPseudoClass = PseudoClass.getPseudoClass("present");
    private final PseudoClass correctPseudoClass = PseudoClass.getPseudoClass("correct");

    public void updatePseudoClass(Node node, LetterStatus letterStatus) {
        node.pseudoClassStateChanged(emptyPseudoClass, false);
        node.pseudoClassStateChanged(unlockedPseudoClass, false);
        node.pseudoClassStateChanged(wrongPseudoClass, false);
        node.pseudoClassStateChanged(presentPseudoClass, false);
        node.pseudoClassStateChanged(correctPseudoClass, false);

        if (letterStatus == EMPTY) {
            node.pseudoClassStateChanged(emptyPseudoClass, true);
        } else if (letterStatus == UNLOCKED) {
            node.pseudoClassStateChanged(unlockedPseudoClass, true);
        } else if (letterStatus == WRONG) {
            node.pseudoClassStateChanged(wrongPseudoClass, true);
        } else if (letterStatus == PRESENT) {
            node.pseudoClassStateChanged(presentPseudoClass, true);
        } else if (letterStatus == CORRECT) {
            node.pseudoClassStateChanged(correctPseudoClass, true);
        }
    }

    public void addPseudoClass(Node node, ObjectProperty<LetterStatus> property) {
        property.addListener(e -> updatePseudoClass(node, property.get()));
    }
}
