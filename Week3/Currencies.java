/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Olli
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.TreeMap;
import java.util.Arrays;

public class Currencies {

    /**
     * @param args the command line arguments
     */
    
    static TreeMap<String, Double> rates = new TreeMap<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader user =
                new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            System.out.print("Enter command: ");
            String command = user.readLine();
            System.out.format("%s%n",command);
            String[] commands = command.split(" ");
            command = commands[0];
            String[] params = Arrays.copyOfRange(commands, 1, commands.length);
            
            if ("quit".equalsIgnoreCase(command)){
                System.out.println("Quit-command received, exiting...");
                break;
            } else if ("rate".equalsIgnoreCase(command)) {
                if (params.length != 2){
                    throw new IOException("Rate takes two parameters.");
                }
                rate(params);
            } else if ("convert".equalsIgnoreCase(command)) {
                if (params.length != 2){
                    throw new IOException("Convert takes two parameters.");
                }
                convert(params);
            } else if ("rates".equalsIgnoreCase(command)) {
                printRates();
            } else {
                throw new IOException("Unknown command");
            }
        }
    }
    private static void rate(String[] args) {
        String name = args[0].toUpperCase();
        Double rate = Double.parseDouble(args[1]);
        rates.put(name, rate);
        System.out.format("Stored the rate 1 EUR = %.3f %s%n",
                rate, name);
    }
    private static void convert(String[] args) {
        String name = args[1].toUpperCase();
        Double amount = Double.parseDouble(args[0]);
        if (rates.containsKey(name)) {
            Double eurAmount = amount / rates.get(name);
            System.out.format("%.3f %s = %.3f EUR%n", amount, name, eurAmount);
        } else {
            System.out.format("No rate for %s has been stored!%n", name);
        }
    }
    private static void printRates(){
       System.out.println("Stored euro rates:");
       if (rates.size() == 0){
           System.out.println("  No rates stored.");
       }
       for (var keyVal : rates.entrySet()){
           System.out.format("  %s %.3f%n", keyVal.getKey(), keyVal.getValue());
       }
    }
}
