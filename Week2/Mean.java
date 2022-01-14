/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Olli
 */
public class Mean {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            System.out.println("Mean: " + sum(args)/args.length);
        } else {
            System.out.println("Empty list, average can't be calculated.");
        }
        
    }
    
    private static Double sum(String[] args) {
        Double s = 0.0;
        for (int i = 0; i < args.length; i++) {
            s += Double.parseDouble(args[i]);
        }
        return s;
    }
}
