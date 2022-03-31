package fi.tuni.prog3.wordle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class WordleInteractor {

    private final WordleData data = new WordleData();

    public WordleInteractor() throws URISyntaxException, IOException {
        this.setNewWord();
    }

    public void checkWord() {
        if (WordleModel.currentColumn == WordleModel.word.size()) {
            LetterModel[] guess = WordleModel.letters[WordleModel.currentRow.get()];
            this.performCheck(guess);
            WordleModel.currentRow.set(WordleModel.currentRow.get() + 1);
            WordleModel.currentColumn = 0;
        }
    }

    private void performCheck(LetterModel[] guess) {
        // Flag the game over if it is.
        List<Character> guessList = Arrays.stream(guess).map(e -> e.letter().get()).toList();
        WordleModel.wordGuessed.setValue(guessList.equals(WordleModel.word));

        List<LetterModel> list = Arrays.asList(guess);
        ListIterator<LetterModel> listIterator = list.listIterator();
        Set<Character> checkedLetters = new HashSet<>();
        while (listIterator.hasNext()) {
            int index = listIterator.nextIndex();
            LetterModel letterModel = listIterator.next();
            char letter = letterModel.letter().get();
            if (WordleModel.word.get(index) == letter) {
                letterModel.status().set(LetterStatus.CORRECT);
            } else if (WordleModel.word.contains(letter) && !checkedLetters.contains(letter)) {
                letterModel.status().set(LetterStatus.PRESENT);
            } else {
                letterModel.status().set(LetterStatus.WRONG);
            }
            checkedLetters.add(letter);
        }
    }

    public void eraseLetter() {
        if (WordleModel.currentColumn > 0) {
            WordleModel.currentColumn--;
            LetterModel square = WordleModel.letters[WordleModel.currentRow.get()][WordleModel.currentColumn];
            square.clear();
        }
    }

    public void handleLetter(char c) {
        if (WordleModel.currentColumn <= WordleModel.word.size() - 1 && !WordleModel.gameOver.get()) {
            LetterModel square = WordleModel.letters[WordleModel.currentRow.get()][WordleModel.currentColumn];
            square.letter().setValue(c);
            WordleModel.currentColumn++;
        }
    }

    public void setNewWord() {
        WordleModel.setNewWord(data.getWord());
    }
}
