package fi.tuni.prog3.wordle;

import javafx.scene.paint.LinearGradient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        Set<Integer> correctIndexes = IntStream
                .range(0, WordleModel.word.size())
                .filter(i -> WordleModel.word.get(i) == guessList.get(i))
                .boxed()
                .collect(Collectors.toSet());

        List<Character> trimmedWord = IntStream
                .range(0, WordleModel.word.size())
                .filter(i -> !correctIndexes.contains(i))
                .mapToObj(c -> WordleModel.word.get(c))
                .toList();

        Set<Character> triedLetters = new HashSet<>();

        while (listIterator.hasNext()) {
            int index = listIterator.nextIndex();
            LetterModel letterModel = listIterator.next();
            char letter = letterModel.letter().get();
            if (WordleModel.word.get(index) == letter) {
                letterModel.status().set(LetterStatus.CORRECT);
            } else if (guessList.contains(letter) && trimmedWord.contains(letter) && !triedLetters.contains(letter)) {
                letterModel.status().set(LetterStatus.PRESENT);
            } else {
                letterModel.status().set(LetterStatus.WRONG);
            }
            triedLetters.add(letter);
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
