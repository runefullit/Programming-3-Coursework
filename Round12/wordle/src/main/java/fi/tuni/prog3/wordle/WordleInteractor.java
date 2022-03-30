package fi.tuni.prog3.wordle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class WordleInteractor {

    private final WordleData data = new WordleData();

    public WordleInteractor() throws URISyntaxException, IOException {
        WordleModel.word.setAll(data.getWord().toUpperCase().chars().mapToObj(e -> (char) e).collect(Collectors.toList()));
        WordleModel.populateLetterModel();
        System.out.println(WordleModel.word);
    }

    public void checkWord() {
        if (WordleModel.currentColumn == WordleModel.word.size()) {
            LetterModel[] guess = WordleModel.letters[WordleModel.currentRow];
            performCheck(guess);
            WordleModel.currentRow++;
            WordleModel.currentColumn = 0;
        }
    }

    private void performCheck(LetterModel[] guess) {
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
            LetterModel square = WordleModel.letters[WordleModel.currentRow][WordleModel.currentColumn];
            square.clear();
            System.out.format("Removed letter from spot %d,%d%n", square.row(), square.column());
        }
    }

    public void handleLetter(char c) {
        if (WordleModel.currentColumn <= WordleModel.word.size() - 1) {
            LetterModel square = WordleModel.letters[WordleModel.currentRow][WordleModel.currentColumn];
            square.letter().setValue(c);
            WordleModel.currentColumn++;
            System.out.format("Added %c to spot %d,%d%n", c, square.row(), square.column());
        }
    }

    public void setNewWord() {
    }
}
