package fi.tuni.prog3.wordle;

import java.io.IOException;
import java.net.URISyntaxException;

public class WordleInteractor {

    private WordleData data = new WordleData();

    public WordleInteractor() throws URISyntaxException, IOException {
    }

    public void checkWord() {
    }

    public void eraseLetter() {
        if (WordleModel.currentColumn > 0) {
            WordleModel.currentColumn--;
            LetterModel square = WordleModel.letters[WordleModel.currentRow][WordleModel.currentColumn];
            square.clear();
            System.out.format("Removed letter from spot %d,%d%n", square.row(), square.column());
        }
    }

    public void handleLetter(char c) {
        if (WordleModel.currentColumn <= WordleModel.wordLength - 1) {
            LetterModel square = WordleModel.letters[WordleModel.currentRow][WordleModel.currentColumn];
            square.status().setValue(LetterStatus.UNLOCKED);
            square.letter().setValue(c);
            WordleModel.currentColumn++;
            System.out.format("Added %c to spot %d,%d%n", c, square.row(), square.column());
        }
    }

    public void setNewWord() {
    }
}
