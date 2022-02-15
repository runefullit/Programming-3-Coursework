
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
    
    MovieAnalytics2(){
        this.movieList = new Movie[0];
    }
    
    void populateWithData(String fileName) throws IOException{
        /*
        Read file into memory.
        */
        try(var br = new BufferedReader(new FileReader(fileName))) {
            this.movieList = br.lines()
                    .map(line -> line.split(";"))
                    .map(info -> new Movie(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2]), info[3], Double.parseDouble(info[4]), info[5]))
                    .toArray(Movie[]::new);
        }
    }
    
    public void printCountByDirector(int n){
        /*
        Prints movie count by top n most productive directors in descending order.
        Ties are broken with alphabetical order of the directors' names.
        */
        final Comparator<Map.Entry<String, Long>> byMovieCount =
            Map.Entry.comparingByValue(Comparator.reverseOrder());
        final Comparator<Map.Entry<String, Long>> byDirectorName =
            Map.Entry.comparingByKey();
        Arrays.stream(movieList)
                // Collect into map with Director names as keys and their movie counts as values.
                .collect(Collectors.groupingBy(Movie::getDirector, Collectors.counting()))
                .entrySet().stream() // Convert the map into an entry stream
                // Sort first by movie count in descending order, break ties with alphabetical order of names.
                .sorted(byMovieCount.thenComparing(byDirectorName))
                .limit(n) // Only considers the first n entries.
                .forEach( (e) -> System.out.format("%s: %d movies%n", e.getKey(), e.getValue()));
    }
    
    public void printAverageDurationByGenre(){
        /*
        Print average durations of different genres in ascending order.
        Ties are broken with alphabetical order of the genres' names.
        */
        final Comparator<Map.Entry<String, Double>> byDuration= 
            Map.Entry.comparingByValue();
        final Comparator<Map.Entry<String, Double>> byGenre =
            Map.Entry.comparingByKey();
        Arrays.stream(movieList) // Convert movieList into a stream
                // Collect into map with Genre as keys and average duration as value.
                .collect(Collectors.groupingBy(Movie::getGenre, Collectors.averagingInt(Movie::getDuration)))
                .entrySet().stream()
                // Sort first by duration and break ties with alphabetical sorting of the genres
                .sorted(byDuration.thenComparing(byGenre))
                .forEach( (e) -> System.out.format("%s: %.2f%n", e.getKey(), e.getValue()));
    }
    
    public void printAverageScoreByGenre(){
        /*
        Print average duration of different genres in descending order.
        Ties are broken with alphabetical order of the genres' names.
        */
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
