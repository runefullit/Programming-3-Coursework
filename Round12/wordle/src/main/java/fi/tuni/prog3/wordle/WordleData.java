package fi.tuni.prog3.wordle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

/**
 * Class structure for maintaining and extracting random words from the input file.
 */
public class WordleData {

    private final List<String> words;
    private final Random rand = new Random();
    private int current;

    public WordleData() throws URISyntaxException, IOException {
//        URL url = WordleData.class.getResource("data/words.txt");
//        assert url != null;
//        Path path = Paths.get(url.toURI());
        Path path = Paths.get("words.txt");
        this.words = Files.readAllLines(path, StandardCharsets.UTF_8);
    }

    /**
     * Get the next word in given list.
     *
     * @return next word in list.
     */
    public String getWord() {
        String retVal = this.words.get(this.current);
        this.current++;
        return retVal;
    }

    /**
     * Get a random value from the given list of words.
     *
     * @return random entry from wordlist.
     */
    public String getRandomWord() {
        return words.get(rand.nextInt(words.size()));
    }
}
