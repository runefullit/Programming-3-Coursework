
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Olli
 */
public class Standings {

    public static class Team {
        
        String name;
        private int wins;
        private int ties;
        private int losses;
        private int scored;
        private int allowed;
        private int points;
        
        public Team(String name){
            this.name = name;
            this.wins = 0;
            this.ties = 0;
            this.losses = 0;
            this.scored = 0;
            this.allowed = 0;
            this.points = 0;
        }
        
        @Override
        public boolean equals (final Object o){
            if (o == null) {
                return false;
            }
            if (!(o instanceof Team)) {
                return false;
            }
            if (o == this) {
                return true;
            }
            
            final Team other = (Team) o;
            return other.name.equals(this.name);
        }
        
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            
            result = prime * result;
            if (this.name != null) {
                result += this.name.hashCode();
            }
            
            return result;
        }
        
        public String getName() {
            return name;
        }
        
        public int getWins() {
            return wins;
        }
        
        public int getTies() {
            return ties;
        }
        
        public int getLosses() {
            return losses;
        }
        
        public int getMatches() {
            return wins + losses + ties;
        }
        
        public int getScored() {
            return scored;
        }
        
        public int getAllowed() {
            return allowed;
        }
        
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
    
    ArrayList<Team> teams = new ArrayList<Team>();
    
    public Standings(){
        
    }
    
    public Standings(String filename) {
        try{
            readMatchData(filename);
        }
        catch(IOException e) {
            System.out.println("Error reading the file.");
        }
    }
    
    public void readMatchData(String filename) throws IOException {
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
    
    public void addMatchResult(String teamNameA, int goalsA, int goalsB, String teamNameB) {
        final Team needleA = new Team(teamNameA);
        if (!teams.contains(needleA)) {
            teams.add(new Team(teamNameA));
            System.out.println(teamNameA + " added.");
        }
        final Team needleB = new Team(teamNameB);
        if (!teams.contains(needleB)) {
            teams.add(new Team(teamNameB));
            System.out.println(teamNameB + " added.");
        }
        
        Team teamA = teams.get(teams.indexOf(needleA));
        Team teamB = teams.get(teams.indexOf(needleB));
        
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
    
    public ArrayList<Team> getTeams() {
        sortStandings();
        return teams;
    }
    
    public void printStandings() {
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
    
    private void sortStandings() {
        teams.sort((a,b) -> (b.getName().compareTo(a.getName())));
        teams.sort((a,b) -> (b.getScored() - a.getScored()));
        teams.sort((a,b) -> (b.getScored() - b.getAllowed()
                - (a.getScored() - a.getAllowed())));
        teams.sort((a,b) -> (b.getPoints() - a.getPoints()));
    }
}
