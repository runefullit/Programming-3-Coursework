package fi.tuni.prog3.wordle;


import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public record WordleModel() {
    static ObservableList<Character> word = FXCollections.observableArrayList();
    static int wordLength = 6;
    static LetterModel[][] letters = populateLetterModel();
    static Map<Character, ObjectProperty<LetterStatus>> alphabet = IntStream.rangeClosed('A', 'Z')
            .collect(HashMap::new,
                    (map, c) -> map.put((char) c, new SimpleObjectProperty<>(LetterStatus.UNLOCKED)),
                    HashMap::putAll);
    static int currentRow = 0;
    static int currentColumn = 0;
    static BooleanProperty darkMode = new SimpleBooleanProperty(false);
    static BooleanProperty wordGuessed = new SimpleBooleanProperty(false);

    private static LetterModel[][] populateLetterModel() {
        LetterModel[][] letters = new LetterModel[6][wordLength];
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < wordLength; col++) {
                letters[row][col] = new LetterModel(row, col);
            }
        }
        return letters;
    }
}
