/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Olli
 */
public class Date {
        
    public class LegalDate {
    
        static int[][] mDays = {{31,31}, {28,29}, {31,31}, {30,30}, {31,31}, {30,30},
                                {31,31}, {31,31}, {30,30}, {31,31}, {30,30}, {31,31}};

        private static boolean isLegalDate(int year, int month, int day){
            return (1 <= day) && (day <= monthDays(month, year));
        }

        private static boolean isLeapYear(int year){
            return (year % 4 == 0) && ((year % 100 != 0 || year % 400 == 0));
        }

        private static int monthDays(int month, int year){
            int days = -1;
            if (1 <= month && month <= 12) {
                days = isLeapYear(year) ? mDays[month-1][1] : mDays[month-1][0];
            }
            return days;
        }
    }
    
    private int year;
    private int month;
    private int day;
    
    public Date(int year, int month, int day) 
            throws DateException {
        this.year = year;
        this.month = month;
        this.day = day;
        if (!LegalDate.isLegalDate(year,month,day)){
            String eMsg = String.format("Illegal date %02d.%02d.%d",day, month, year);
            throw new DateException(eMsg);
        }
    }
    
    @Override
    public String toString(){
        return String.format("%02d.%02d.%d", day, month, year);
    }
    
    public int getYear(){
        return year;
    }
    
    public int getMonth(){
        return month;
    }
    
    public int getDay(){
        return day;
    }
    
}
