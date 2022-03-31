package fi.tuni.prog3.wordle;

public enum InfoBoxAnswers {
    PREMATURE("Give a complete word before pressing Enter!"),
    GAME_OVER("Game over, you lost!"),
    PLAYER_WON("Congratulations, you won!");

    private final String message;

    InfoBoxAnswers(String s) {
        this.message = s;
    }

    public boolean equalsMessage(String otherString) {
        return this.message.equals(otherString);
    }

    public String toString() {
        return this.message;
    }
}
