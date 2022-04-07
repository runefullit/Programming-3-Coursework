package fi.tuni.prog3.wordle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

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
        } else if (!WordleModel.gameOver.get()) {
            WordleModel.infoText.setValue(InfoBoxAnswers.PREMATURE.toString());
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
            square.status().setValue(LetterStatus.UNLOCKED);
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
        if (WordleModel.wordGuessed.get()) {
            WordleModel.infoText.setValue(InfoBoxAnswers.PLAYER_WON.toString());
        } else if (WordleModel.currentRow.get() == 5) {
            WordleModel.infoText.setValue(InfoBoxAnswers.GAME_OVER.toString());
        }

        List<LetterModel> list = Arrays.asList(guess);
        ListIterator<LetterModel> listIterator = list.listIterator();

        while (listIterator.hasNext()) {
            int index = listIterator.nextIndex();
            LetterModel letterModel = listIterator.next();
            char letter = letterModel.letter().get();
            if (WordleModel.word.get(index) == letter) {
                letterModel.status().set(LetterStatus.CORRECT);
            } else if (WordleModel.word.contains(letter)) {
                letterModel.status().set(LetterStatus.PRESENT);
            } else {
                letterModel.status().set(LetterStatus.WRONG);
            }
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
