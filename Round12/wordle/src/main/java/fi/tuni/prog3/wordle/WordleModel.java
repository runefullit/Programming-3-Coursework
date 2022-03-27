package fi.tuni.prog3.wordle;

public record WordleModel(
        int currentColumn,
        String word,
        String alphabet,
        boolean wordGuessed,
        boolean gameOver
) {}
