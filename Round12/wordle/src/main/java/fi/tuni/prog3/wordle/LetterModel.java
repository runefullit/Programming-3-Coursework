package fi.tuni.prog3.wordle;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public record LetterModel(int row, int column, ObjectProperty<Character> letter, ObjectProperty<LetterStatus> status) {

    public LetterModel(int row, int column) {
        this(row, column, new SimpleObjectProperty<>(' '), new SimpleObjectProperty<>(LetterStatus.EMPTY));
    }

    public void clear() {
        this.letter.setValue(' ');
        this.status.setValue(LetterStatus.EMPTY);
    }
}
