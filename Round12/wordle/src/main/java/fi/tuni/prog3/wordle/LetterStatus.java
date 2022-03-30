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

        switch (letterStatus) {
            case EMPTY:
                node.pseudoClassStateChanged(emptyPseudoClass, true);
            case UNLOCKED:
                node.pseudoClassStateChanged(unlockedPseudoClass, true);
            case WRONG:
                node.pseudoClassStateChanged(wrongPseudoClass, true);
            case PRESENT:
                node.pseudoClassStateChanged(presentPseudoClass, true);
            case CORRECT:
                node.pseudoClassStateChanged(correctPseudoClass, true);
        }
    }

    public void addPseudoClass(Node node, ObjectProperty<LetterStatus> property) {
        property.addListener(a -> updatePseudoClass(node, property.get()));
    }
}
