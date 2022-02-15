
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Olli
 */
public class MovieAnalytics2 {
    
    private Movie[] movieList;
    
    private final Comparator<Map.Entry<String, Long>> byDirectorName =
            Map.Entry.comparingByKey();
    
    MovieAnalytics2(){
        this.movieList = new Movie[0];
    }
    
    void populateWithData(String fileName) throws IOException{
        try(var br = new BufferedReader(new FileReader(fileName))) {
            this.movieList = br.lines()
                    .map(line -> line.split(";"))
                    .map(info -> new Movie(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2]), info[3], Double.parseDouble(info[4]), info[5]))
                    .toArray(Movie[]::new);
        }
    }
    
    public void printCountByDirector(int n){
        final Comparator<Map.Entry<String, Long>> byMovieCount =
            Map.Entry.comparingByValue(Comparator.reverseOrder());
        final Comparator<Map.Entry<String, Long>> byDirectorName =
            Map.Entry.comparingByKey();
        Arrays.stream(movieList)
                .collect(Collectors.groupingBy(Movie::getDirector, Collectors.counting()))
                .entrySet().stream()
                .sorted(byMovieCount.thenComparing(byDirectorName))
                .limit(n)
                .forEach( (e) -> System.out.format("%s: %d movies%n", e.getKey(), e.getValue()));
    }
    
    public void printAverageDurationByGenre(){
        final Comparator<Map.Entry<String, Double>> byDuration= 
            Map.Entry.comparingByValue();
        final Comparator<Map.Entry<String, Double>> byGenre =
            Map.Entry.comparingByKey();
        Arrays.stream(movieList)
                .collect(Collectors.groupingBy(Movie::getGenre, Collectors.averagingInt(Movie::getDuration)))
                .entrySet().stream()
                .sorted(byDuration.thenComparing(byGenre))
                .forEach( (e) -> System.out.format("%s: %.2f%n", e.getKey(), e.getValue()));
    }
    
    public void printAverageScoreByGenre(){
        final Comparator<Map.Entry<String, Double>> byScore =
                Map.Entry.comparingByValue(Comparator.reverseOrder());
        final Comparator<Map.Entry<String, Double>> byGenre =
                Map.Entry.comparingByKey();
        Arrays.stream(movieList)
                .collect(Collectors.groupingBy(Movie::getGenre, Collectors.averagingDouble(Movie::getScore)))
                .entrySet().stream()
                .sorted(byScore.thenComparing(byGenre))
                .forEach( (e) -> System.out.format("%s: %.2f%n", e.getKey(), e.getValue()));
    }
       
}
