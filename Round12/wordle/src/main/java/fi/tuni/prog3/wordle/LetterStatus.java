package fi.tuni.prog3.wordle;

import javafx.css.PseudoClass;
import javafx.scene.Node;

public enum LetterStatus {
    EMPTY, WRONG, PRESENT, CORRECT;

    private final PseudoClass emptyPseudoClass = PseudoClass.getPseudoClass("empty");
    private final PseudoClass wrongPseudoClass = PseudoClass.getPseudoClass("wrong");
    private final PseudoClass presentPseudoClass = PseudoClass.getPseudoClass("present");
    private final PseudoClass correctPseudoClass = PseudoClass.getPseudoClass("correct");

    public void updatePseudoClass(Node node, LetterStatus letterStatus) {
        node.pseudoClassStateChanged(emptyPseudoClass, false);
        node.pseudoClassStateChanged(wrongPseudoClass, false);
        node.pseudoClassStateChanged(presentPseudoClass, false);
        node.pseudoClassStateChanged(correctPseudoClass, false);

        if (letterStatus == EMPTY) {
            node.pseudoClassStateChanged(emptyPseudoClass, true);
        } else if (letterStatus == WRONG) {
            node.pseudoClassStateChanged(wrongPseudoClass, true);
        } else if (letterStatus == PRESENT) {
            node.pseudoClassStateChanged(presentPseudoClass, true);
        } else if (letterStatus == CORRECT) {
            node.pseudoClassStateChanged(correctPseudoClass, true);
        }
    }
}
