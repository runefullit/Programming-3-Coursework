package fi.tuni.prog3.wordle;


import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record WordleModel() {
        static int wordLength = 6;
        static List<List<LetterModel>> letters = buildLetters();
        static ObservableList<Character> word = FXCollections.observableArrayList();
        static Map<Character, ObjectProperty<LetterStatus>> alphabet = IntStream.rangeClosed('A', 'Z')
                .collect(HashMap::new,
                        (map, c) -> map.put((char)c, new SimpleObjectProperty<>(LetterStatus.UNLOCKED)),
                        HashMap::putAll);
        static IntegerProperty currentRow = new SimpleIntegerProperty(0);
        static BooleanProperty darkMode = new SimpleBooleanProperty(false);
        static BooleanProperty wordGuessed = new SimpleBooleanProperty(false);
        static ObservableBooleanValue gameOver = Bindings.createBooleanBinding(
                () -> (currentRow.getValue() > 5 || wordGuessed.getValue()),
                currentRow,
                wordGuessed);


        private static List<List<LetterModel>> buildLetters() {
                int guesses = 6;
                List<List<LetterModel>> letters = new ArrayList<>(guesses);
                for(int i = 0; i < guesses; i++) {
                        final int finalI = i;
                        letters.add(
                                IntStream
                                        .range(0, wordLength)
                                        .mapToObj(a -> new LetterModel(finalI, a))
                                        .collect(Collectors.toList())
                                );
                }
                return letters;
        }
}
