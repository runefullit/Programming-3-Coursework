package fi.tuni.prog3.wordle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class WordleInteractor {

    private static WordleData data = null;

    static {
        try {
            data = new WordleData();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    static void checkWord() {
        if (WordleModel.currentCol == WordleModel.word.size()) {
            LetterModel[] guess = WordleModel.letters[WordleModel.currentRow.get()];
            performCheck(guess);
            WordleModel.currentRow.set(WordleModel.currentRow.get() + 1);
            WordleModel.currentCol = 0;
            setAlphabet();
        } else {
            WordleModel.infoText.setValue("Give a complete word before pressing Enter!");
        }
    }

    static void eraseLetter() {
        if (WordleModel.currentCol > 0) {
            WordleModel.currentCol--;
            LetterModel square = WordleModel.letters[WordleModel.currentRow.get()][WordleModel.currentCol];
            square.clear();
        }
    }

    static void handleLetter(char c) {
        if (WordleModel.currentCol <= WordleModel.word.size() - 1 && !WordleModel.gameOver.get()) {
            LetterModel square = WordleModel.letters[WordleModel.currentRow.get()][WordleModel.currentCol];
            square.letter().setValue(c);
            WordleModel.currentCol++;
        }
    }

    static void setNewWord() {
        WordleModel.setNewWord(data.getWord());
    }

    private static void performCheck(LetterModel[] guess) {
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

    private static void setAlphabet() {
        WordleModel.alphabet.forEach((key, value) -> Arrays.stream(WordleModel.letters)
                .flatMap(Arrays::stream)
                .filter(letter -> letter.letter().get().equals(key))
                .forEach(e -> {
                    if (e.status().get().ordinal() > value.get().ordinal()) value.set(e.status().get());
                }));
    }
}
