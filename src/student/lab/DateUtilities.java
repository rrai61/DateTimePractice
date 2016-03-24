/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.lab;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;

/**
 *
 * @author ritu
 */
public class DateUtilities {
    
    // singleton instance
    private static DateUtilities instance;
    
    public enum DateUnit {

        DAY(1000L * 60L * 60L * 24L),
        HOUR(1000L * 60L * 60L),
        MINUTE(1000L * 60L),
        SECOND(1000L);

        DateUnit(long ms) {
            milliseconds = ms;
        }

        private final long milliseconds;

        public long getMilliseconds() {
            return milliseconds;
        }
    }
    
    // Prohibit instantiation -- support Singleton design pattern
    private DateUtilities() {
    }
    
     /**
     * Singleton support.
     *
     * @return one and only one global instance
     */
    public static DateUtilities getInstance() {
        if (instance == null) {
            instance = new DateUtilities();
        }
        return instance;
    }
    
    public String toString(LocalDate date) throws IllegalArgumentException {
        String stringDate = null;  
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        stringDate = formatter.format(date);
        return stringDate;
    }
    
    public String toString(LocalDate date, String pattern) throws IllegalArgumentException {
        String stringDate = null;  
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        stringDate = formatter.format(date);
        return stringDate;
    }
    
    public LocalDate toDate(String dateString, String pattern) throws IllegalArgumentException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate date = LocalDate.parse(dateString, formatter);
        return date;
    }
    
    public LocalDate toDate(String dateString) throws IllegalArgumentException {
        LocalDate date = LocalDate.parse(dateString);
        return date;
    }
    
    public int getDateDiff(LocalDate firstDate, LocalDate secondDate, DateUnit dateUnit)
            throws IllegalArgumentException {
    // Convert Calendars to LocalDateTime objects
        LocalDateTime startDate = LocalDateTime.ofInstant(firstDate., ZoneId.systemDefault());
        LocalDateTime endDate = LocalDateTime.ofInstant(secondDate, ZoneId.systemDefault());
        Duration diff = Duration.between(startDate, endDate);
        int value;
        switch (dateUnit) {
            case DAY:
                value = (int) diff.toDays();
                break;

            case HOUR:
                value = (int) diff.toHours();
                break;

            case MINUTE:
                value = (int) diff.toMinutes();
                break;

            case SECOND:
                value = (int) (diff.toMillis() / 1000L);
                break;

            default:
                value = (int) diff.toHours();
        }
        return value;
    }
     
    public static void main(String[] args) throws ParseException, IllegalArgumentException {
        
        DateUtilities dateUtilities = DateUtilities.getInstance();        
        LocalDate firstDate = LocalDate.now();
        LocalDate secondDate = LocalDate.now().plusDays(5);
        String fDate = dateUtilities.toString(firstDate);
        String sDate = dateUtilities.toString(secondDate);
        System.out.println("Local Date to String without pattern: " + fDate);
        System.out.println("Local Date to String without pattern: " + sDate);
        int value = dateUtilities.getDateDiff(firstDate, secondDate, DateUnit.DAY);
        System.out.println("Days between " + fDate + " and " + sDate + "is: " + value);
    }
}
