package fi.tuni.prog3.wordle;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public record LetterModel(int row, int col, ObjectProperty<Character> letter, ObjectProperty<LetterStatus> status) {

    public LetterModel(int row, int col) {
        this(row, col, new SimpleObjectProperty<>(' '), new SimpleObjectProperty<>(LetterStatus.EMPTY));
    }

    public void clear() {
        this.letter.setValue(' ');
        this.status.setValue(LetterStatus.EMPTY);
    }
}
