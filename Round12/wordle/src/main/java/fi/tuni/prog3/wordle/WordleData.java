package fi.tuni.prog3.wordle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
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

    public WordleData() throws URISyntaxException, IOException {
        URL url = WordleData.class.getResource("data/words.txt");
        assert url != null;
        Path path = Paths.get(url.toURI());
        this.words = Files.readAllLines(path, StandardCharsets.UTF_8);
    }

    /**
     * Get a random value from the given list of words.
     * @return random entry from wordlist.
     */
    public String getWord() {
        return words.get(rand.nextInt(words.size()));
    }
}
