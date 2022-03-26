package fi.tuni.prog3.wordle;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

/**
 * Class structure for maintaining and extracting random words from the input file.
 */
public class WordleData {

    private final String[] words = Objects.requireNonNull(Wordle.class.getResource("words.txt")).toString().split("\n");
    private final Random rand = new Random();

    public WordleData() throws IOException {
    }

    /**
     * Get a random value from the given list of words.
     * @return random entry from wordlist.
     */
    public String getWord() {
        return words[rand.nextInt(words.length)];
    }
}
