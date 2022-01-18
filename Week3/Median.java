/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Olli
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class Median {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Arrays.sort(args, (String a, String b) -> Double.parseDouble(a) > Double.parseDouble(b) ? 1: (Double.parseDouble(a) < Double.parseDouble(b) ? -1: 0));
        Double Median;
        
        if (args.length % 2 == 0) {
            Median = (Double.parseDouble(args[args.length / 2 - 1]) +
                    Double.parseDouble(args[args.length / 2])) / 2;
        } else {
            Median = Double.parseDouble(args[args.length / 2]);
        }
        System.out.println("Median: " + Median);
    }
    

}
