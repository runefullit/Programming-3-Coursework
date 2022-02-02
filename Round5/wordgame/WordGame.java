/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author Olli
 */
public class WordGame {

    public static class WordGameState {
        
        private static int mistakes;
        private static int limit;
        private static int missing;
        private static String word;
        private static String secretWord;
        
        private  WordGameState(String word, int mistakeLimit){
            this.word = "_".repeat(word.length());
            this.mistakes = 0;
            this.limit = mistakeLimit;
            this.missing = word.length();
            this.secretWord = word;
        }
        
        public static String getWord() {
            return word;
        }
        
        public static int getMistakes() {
            return mistakes;
        }
        
        public static int getMistakeLimit() {
            return limit;
        }
        
        public static int getMissingChars() {
            return missing;
        }
        
        private void updateWord(char c) {
            char lowerC = Character.toLowerCase(c);
            char[] updatedWord = this.getWord().toCharArray();
            int charCount = 0;
            for (int i = 0; i < secretWord.length(); i++) {
                if(isUndiscovered(lowerC, i)){
                    updatedWord[i] = lowerC;
                    charCount++;
                }
            }
            mistakes += (charCount == 0) ? 1 : 0;
            missing -= charCount;
            if (mistakes > limit) {
                word = secretWord;
            } else {
                word = String.valueOf(updatedWord);
            }
        }
        
        private void updateWord(String str){
            if(str.toLowerCase().equals(secretWord.toLowerCase())){
                word = secretWord;
                missing = 0;
            } else {
                mistakes++;
                if (mistakes > limit){
                    word = secretWord;
                }
            }
        }
        
        /**
         * Find out if the character in index i matches c (NOT casesensitive) and isn't already in the guessed bit.
         * @param c
         * @param i
         * @return Boolean, true if char at index of the hidden word matches and isn't already in the displayed word.
         */
        private boolean isUndiscovered(char c, int i){
            return (secretWord.toLowerCase().charAt(i) == c) && !(word.toLowerCase().charAt(i) == c);
        }
    }
    
    private ArrayList<String> words = new ArrayList<>();
    private WordGameState state;
    
    public WordGame(String wordFilename) throws IOException {
            try(var file = new BufferedReader(new FileReader(wordFilename))) {
                String line;
                while ((line = file.readLine()) != null) {
                    words.add(line);
                }
            }
        }

    public void initGame(int wordIndex, int mistakeLimit){
        state = new WordGameState(words.get(wordIndex % words.size()), mistakeLimit);
    }
    
    public boolean isGameActive(){
        if ( (state.getMissingChars() == 0) || (state.getMistakes() > state.getMistakeLimit()) ) {
            this.state = null;
        }
        return state != null;
    }
    
    public WordGameState getGameState() throws GameStateException{
        if (isGameActive()){
            return state;
        } else {
            throw new GameStateException("There is currently no active word game!");
        }
    }
    
    public WordGameState guess(char c) throws GameStateException{
        if (isGameActive()) {
            state.updateWord(c);
            return state;
        } else {
            throw new GameStateException("There is currently no active word game!");
        }
        
    }
    
    public WordGameState guess(String word) throws GameStateException{
        if (isGameActive()){
            state.updateWord(word);
            return state;
        } else {
            throw new GameStateException("There is currently no active word game!");
        }
    }
    
}
