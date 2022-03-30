package fi.tuni.prog3.wordle;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public record WordleModel() {
    static ObservableList<Character> word = FXCollections.observableArrayList();
    static LetterModel[][] letters;
    static int currentRow = 0;
    static int currentColumn = 0;
    static BooleanProperty darkMode = new SimpleBooleanProperty(false);
    static BooleanProperty wordGuessed = new SimpleBooleanProperty(false);

    static void populateLetterModel() {
        LetterModel[][] letters = new LetterModel[6][word.size()];
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < word.size(); col++) {
                letters[row][col] = new LetterModel(row, col);
            }
        }
        WordleModel.letters = letters;
    }
}
