/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;
/**
 *
 * @author Olli
 */
public class Dates {
    
    public static class DateDiff {
        
        private LocalDate start;
        private LocalDate end;
        private int diff;
        
        private DateDiff(String start, String end){
            this.start = LocalDate.parse(start);
            this.end = LocalDate.parse(end);
            this.diff = (int)this.start.until(this.end, ChronoUnit.DAYS);
        }
        
        @Override
        public String toString(){
                String mOut = String.format("%s -> %s: %d days",
                        start.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).localizedBy(Locale.GERMANY)),
                        end.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).localizedBy(Locale.GERMANY)),
                       diff);
            return mOut;
        }
        
        public String getStart(){
            return start.toString();
        }
        
        public String getEnd(){
            return end.toString();
        }
        
        public int getDiff(){
            return diff;
        }
    }
 
    public static DateDiff[] dateDiffs(String ...dateStrs) throws DateTimeException{
        ArrayList<String> validDateStrs = new ArrayList<>();
        for (String dateStr : dateStrs){
            if (isDotSeparated(dateStr)) {
                dateStr = convertToISO(dateStr);
            }
            try {
                LocalDate d = LocalDate.parse(dateStr);
                validDateStrs.add(dateStr);
            } catch(DateTimeException e) {
                System.out.format("The date %s is illegal!%n", dateStr);
            }
        }
        validDateStrs.sort((a,b) -> (LocalDate.parse(a).compareTo(LocalDate.parse(b))));
        
        if (validDateStrs.size() < 2){
            DateDiff[] diffs = {};
            return diffs;
        } else {
            DateDiff[] diffs = new DateDiff[validDateStrs.size() - 1];
            for (int i = 0; i < (validDateStrs.size() - 1); i++){
                diffs[i] = new DateDiff(validDateStrs.get(i), validDateStrs.get(i+1));
            }
            return diffs;
        }
    }
    
    private static boolean isDotSeparated(String dateStr){
        if (dateStr.split("\\.").length == 3){
            return true;
        }
        return false;
    }
    
    private static String convertToISO(String dateStr) throws ArrayIndexOutOfBoundsException{
        String asISO = null;
        try{
            String[] parts = dateStr.split("\\.");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            
            asISO = String.format("%d-%02d-%02d", year, month, day);
        } catch (ArrayIndexOutOfBoundsException e) {
                System.out.format("The date %s is illegal! in conversion%n", dateStr);
        }
        return asISO;
    }
       
}
