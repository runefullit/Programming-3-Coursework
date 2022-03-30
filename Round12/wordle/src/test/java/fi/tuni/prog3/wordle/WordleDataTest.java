package fi.tuni.prog3.wordle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WordleDataTest {
    WordleData wordleData;

    @Test
    public void wordleDataReturnsRandomString() {
        String word = this.wordleData.getWord();
        assertTrue(word.length() < 15);
    }

    @BeforeEach
    public void setup() throws URISyntaxException, IOException {
        this.wordleData = new WordleData();
    }
}