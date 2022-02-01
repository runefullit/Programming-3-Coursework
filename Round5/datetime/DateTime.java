/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Olli
 */
public class DateTime extends Date{
    
    private int hour;
    private int minute;
    private int second;
    
    public DateTime(int year, int month, int day, int hour, int minute, int second)
            throws DateException {
        super(year, month, day);
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        if (!isLegalTime(hour,minute,second)){
            String eMsg = String.format("Illegal time %02d:%02d:%02d",
                                        hour, minute, second);
            throw new DateException(eMsg);
        }
    }
     
    public String toString(){
        return String.format("%s %02d:%02d:%02d",super.toString(), hour, minute, second);
    }
    
    public int getHour(){
        return hour;
    }
    
    public int getMinute(){
        return minute;
    }
    
    public int getSecond(){
        return second;
    }
    
    private boolean isLegalTime(int hour, int minute, int second){
        return ((0 <= hour) && (hour <= 23)) &&
                ((0 <= minute) && (minute <= 59)) &&
                ((0 <= second) && (second <= 59));
    }
}
