package fi.tuni.prog3.standings;

import java.io.PrintStream;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * A class for maintaining team statistics and standings. Team standings are determined by the following rules:
 * <ul>
 *     <li>Primary rule: points total. Higher points come first.</li>
 *     <li>Secondary rule: goal difference (scored minus allowed). Higher difference comes first.</li>
 *     <li>Tertiary rule: number of goals scored. Higher number comes first.</li>
 *     <li>Last rule: natural String order of team names.</li>
 * </ul>
 */
public class Standings {

    /**
     * A class for storing statistics of a single team. The class offers only public getter functions.
     * The enclosing class Standings is responsible for setting and updating team statistics.
     */
    public static class Team {
        
        private String name;
        private int wins;
        private int ties;
        private int losses;
        private int scored;
        private int allowed;
        private int points;

        /**
         * Constructs a Team object for storing statistics of the named team.
         * @param name the name of the team whose statistics the new team object stores.
         */
        public Team(String name){
            this.name = name;
            this.wins = 0;
            this.ties = 0;
            this.losses = 0;
            this.scored = 0;
            this.allowed = 0;
            this.points = 0;
        }

        /**
         * Returns the name of the team.
         * @return the name of the team.
         */
        public String getName() {
            return name;
        }

        /**
         * Returns the number of wins of the team.
         * @return the number of wins of the team.
         */
        public int getWins() {
            return wins;
        }

        /**
         * Returns the number of ties of the team.
         * @return the number of ties of the team.
         */
        public int getTies() {
            return ties;
        }

        /**
         * Returns the number of losses of the team.
         * @return the number of losss of the team.
         */
        public int getLosses() {
            return losses;
        }

        private int getMatches() {
            return wins + losses + ties;
        }

        /**
         * Returns the number of goals scored by the team.
         * @return the number of goals scored by the team.
         */
        public int getScored() {
            return scored;
        }

        /**
         * Returns the number of goals allowed (conceded) by the team.
         * @return the number of goals allowed (conceded) by the team.
         */
        public int getAllowed() {
            return allowed;
        }

        /**
         * Returns the overall number of points of the team.
         * @return the overall number of points of the team.
         */
        public int getPoints() {
            return points;
        }
        
        private void addWin() {
            this.wins += 1;
            this.points += 3;
        }
        
        private void addTie() {
            this.ties += 1;
            this.points += 1;
        }
        
        private void addLoss() {
            this.losses += 1;
        }
        
        private void addScored(int goals) {
            this.scored += goals;
        }
        
        private void addAllowed(int goals) {
            this.allowed += goals;
        }
        
    }
    
    private List<Team> teams = new ArrayList<Team>();

    /**
     * Constructs an empty Standings object.
     */
    public Standings(){}

    /**
     * Constructs a Standings object that is initialized with the game data read from the specified file.
     * The result is identical to first constructing an empty Standing object and then calling
     * {@link #readMatchData readMatchData(filename)}.
     * @param filename the name of the game data file to read.
     * @throws IOException if there is some kind of an IO error (e.g. if the specified file does not exist).
     */
    public Standings(String filename) throws IOException {
        readMatchData(filename);
    }

    /**
     * <p>Reads game data from the specified file and updates the team statistics and standings accordingly. </p>
     * <p>The match data file is expected to contain lines of form "teamNameA\tgoalsA-goalsB\tteamNameB".
     * Note that the '\t' are tabulator characters. </p>
     * <p>E.g. the line "Iceland\t3-2\tFinland" would describe a match between Iceland and Finland where
     * Iceland scored 3 and Finland 2 goals.</p>
     * @param filename the name of the game data file to read.
     * @throws IOException if there is some kind of an IO error (e.g. if the specified file does not exist).
     */
    public final void readMatchData(String filename) throws IOException {
        try (var file = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = file.readLine()) != null) {
                String[] input = line.split("\\t");
                String teamA = input[0];
                String teamB = input[2];

                String[] scores = input[1].split("-");
                int scoreA = Integer.parseInt(scores[0]);
                int scoreB = Integer.parseInt(scores[1]);
                
                addMatchResult(teamA, scoreA, scoreB, teamB);
            }
        }
    }

    /**
     * Updates the team statistics and standings according to the match result described by the parameters.
     * @param teamNameA the name of the first ("home") team.
     * @param goalsA the number of goals scored by the first team.
     * @param goalsB the number of goals socred by the second team.
     * @param teamNameB the name of the second ("away") team.
     */
    public void addMatchResult(String teamNameA, int goalsA, int goalsB, String teamNameB) {
        if (teamArrayIndex(teamNameA) == -1) {
            teams.add(new Team(teamNameA));
        }
        if (teamArrayIndex(teamNameB) == -1) {
            teams.add(new Team(teamNameB));
        }
        
        Team teamA = teams.get(teamArrayIndex(teamNameA));
        Team teamB = teams.get(teamArrayIndex(teamNameB));
        
        // Add info on goals scored
        teamA.addScored(goalsA);
        teamA.addAllowed(goalsB);
        teamB.addScored(goalsB);
        teamB.addAllowed(goalsA);
        
        // Add info on match results
        if (goalsA > goalsB) {
            teamA.addWin();
            teamB.addLoss();
        }
        else if (goalsA < goalsB) {
            teamA.addLoss();
            teamB.addWin();
        }
        else {
            teamA.addTie();
            teamB.addTie();
        }
    }

    /**
     * Returns a list of the teams in the same order as they would appear in a standings table.
     * @return a list of the teams in the same order as they would appear in a standings table.
     */
    public List<Team> getTeams() {
        sortStandings();
        return teams;
    }

    /**
     * Prints a formatted standings table to the provided output stream.
     * @param out  the output stream to use when printing the standings table.
     */
    public void printStandings(PrintStream out) {
        sortStandings();
        int longest = getLongest();
        for (Team t : teams) {
            String[] goals = {
                Integer.toString(t.getScored()),
                Integer.toString(t.getAllowed())
            };
            System.out.format("%-"+longest+"s %3d %3d %3d %3d %6s %3d%n", t.getName(),
                    t.getMatches(), t.getWins(), t.getTies(), t.getLosses(),
                    String.join("-", goals), t.getPoints());
        }
    }
    
    private int getLongest() {
        int l = 0;
        if(teams != null){
            for (Team t : teams) {
                if (t.name.length() > l) {
                    l = t.name.length();
                }
            }
        }
        return l;
    }
    
    private int teamArrayIndex(String teamName) {
        int index = 0;
        for (Team t : teams) {
            if(t.getName().equals(teamName)) {
                return index;
            }
            index++;
        }
        return -1;
    }
    
    private void sortStandings() {
        teams.sort((a,b) -> (b.getName().compareTo(a.getName())));
        teams.sort((a,b) -> (b.getScored() - a.getScored()));
        teams.sort((a,b) -> (b.getScored() - b.getAllowed()
                - (a.getScored() - a.getAllowed())));
        teams.sort((a,b) -> (b.getPoints() - a.getPoints()));
    }
}
