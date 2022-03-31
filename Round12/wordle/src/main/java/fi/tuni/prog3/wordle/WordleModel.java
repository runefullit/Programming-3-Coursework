package fi.tuni.prog3.wordle;


import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record WordleModel() {
    static ObservableList<Character> word = FXCollections.observableArrayList();
    static LetterModel[][] letters;
    static Map<Character, ObjectProperty<LetterStatus>> alphabet;
    static IntegerProperty currentRow = new SimpleIntegerProperty();
    static int currentCol = 0;
    static StringProperty infoText = new SimpleStringProperty("");
    static BooleanProperty darkMode = new SimpleBooleanProperty(false);
    static BooleanProperty wordGuessed = new SimpleBooleanProperty(false);
    static ObservableBooleanValue gameOver = Bindings.createBooleanBinding(() -> currentRow.get() > 5 || wordGuessed.get(), currentRow, wordGuessed);
    static BooleanProperty resizeWindow = new SimpleBooleanProperty(false);
    static ObservableBooleanValue windowShouldResize = Bindings.createBooleanBinding(() -> resizeWindow.get(), resizeWindow);

    private static void populateLetterModel() {
        LetterModel[][] letters = new LetterModel[6][word.size()];
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < word.size(); col++) {
                letters[row][col] = new LetterModel(row, col);
            }
        }
        WordleModel.letters = letters;
    }

    private static void initAlphabet() {
        alphabet = IntStream
                .rangeClosed('A', 'Z')
                .mapToObj(e -> (char) e)
                .collect(Collectors.toMap(
                        key -> key,
                        val -> (new SimpleObjectProperty<>(LetterStatus.UNLOCKED))
                ));
    }

    static void setNewWord(String word) {
        WordleModel.word.setAll(
                word
                        .toUpperCase()
                        .chars()
                        .mapToObj(e -> (char) e)
                        .collect(Collectors.toList())
        );
        populateLetterModel();
        initAlphabet();
        currentRow.set(0);
        currentCol = 0;
        wordGuessed.setValue(false);
        System.out.println(WordleModel.word);
    }
}
