
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.stream.Stream;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Olli
 */
public class MovieAnalytics {
    
    private Movie[] movieList;
    private Comparator<Movie> yearComparator = Comparator.comparing(Movie::getReleaseYear)
            .thenComparing(Movie::getTitle);
    
    MovieAnalytics(){
        this.movieList = new Movie[0];
    }
    
    static Consumer<Movie> showInfo(){
        return new Consumer<Movie>(){
            public void accept(Movie t){
                System.out.format("%s (%s, %d)%n", t.getTitle(), t.getDirector(), t.getReleaseYear());
            }
        };
    }
    
    void populateWithData(String fileName) throws IOException{
        try(var br = new BufferedReader(new FileReader(fileName))) {
            this.movieList = br.lines()
                    .map(line -> line.split(";"))
                    .map(info -> new Movie(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2]), info[3], Double.parseDouble(info[4]), info[5]))
                    .toArray(Movie[]::new);
        }
    }
    
    Stream<Movie> moviesAfter(int year){
        return Arrays.stream(movieList).filter( a -> (a.getReleaseYear() >= year))
                .sorted(yearComparator);
    }
    
    Stream<Movie> moviesBefore(int year){
        return Arrays.stream(movieList).filter(a ->(a.getReleaseYear() <= year)).sorted(yearComparator);
    }
    
    Stream<Movie> moviesBetween(int yearA, int yearB){
        return Arrays.stream(movieList).filter(a -> (a.getReleaseYear() >= yearA && a.getReleaseYear() <= yearB))
                .sorted(yearComparator);
    }
    
    Stream<Movie> moviesByDirector(String director){
        return Arrays.stream(movieList).filter(a -> (a.getDirector().equals(director)))
                .sorted(yearComparator);
    }
    
}
