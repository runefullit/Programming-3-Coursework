/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Olli
 */
import java.util.Arrays;

public class Parameters {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Sorting by lengh to find the longest name
        Arrays.sort(args, (String a, String b) ->
                a.length() < b.length() ? 1:
                (a.length() > b.length() ? -1 : 0));
        int rows;
        int cols;
        int numWidth;
        int nameWidth;
        
        rows = args.length * 2 + 1;
        // This looks quite horrifying
        numWidth = Integer.toString(args.length).length();
        nameWidth = args[0].length();
        cols = numWidth + nameWidth + 7;
        
        // Sorting alphabetically
        Arrays.sort(args);
        
        System.out.println(new String(new char[cols]).replace("\0", "#"));
        for (int i = 0; i < args.length; i++) {
            System.out.format("# %"+numWidth+"d | %-"+nameWidth+"s #%n", i+1, args[i]);
            if (i == args.length - 1){
                System.out.println(new String(new char[cols]).replace("\0", "#"));
            } else {
                System.out.println("#" + 
                        new String(new char[numWidth + 2]).replace("\0", "-") + "+" +
                        new String(new char[nameWidth + 2]).replace("\0", "-") + "#");
            }
        }
    }
}
