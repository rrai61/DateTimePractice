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

/**
 * Singleton utility class to simplify managing and using dates and times, 
 * using the new Java Date/Time API (JDK 8). Fields are
 * provided for choice of units to be returned from date/time difference
 * calculations.
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
    
     /**
     * Used to find current date and time.
     * 
     * @return a date time object.
     */
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
    
     /**
     * Format a <code>LocalDateTime</code> to a String according to the default date pattern for the
     * current locale
     *
     * @param date - a <code>LocalDateTime</code> object
     * @return a String date formatted according to the default date pattern for the
     * current locale
     * @throws IllegalArgumentException if date is null
     */
    public String toString(LocalDateTime date) throws IllegalArgumentException {
        String stringDate = null;  
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        stringDate = formatter.format(date);
        return stringDate;
    }
    
     /**
     * Format a <code>LocalDateTime</code> to a String according to a specified pattern
     *
     * @param date - a <code>LocalDateTime</code> object
     * @param pattern - a <code>DateTimeFormatter</code> date/time pattern
     * For example:"MM/dd/yyyy hh:mm:ss"
     * @see <a href="http://joda-time.sourceforge.net/api-release/org/joda/time/format/DateTimeFormat.html"></a>
     * @return a date formatted to a String according to the specified pattern
     * @throws IllegalArgumentException if date is null or pattern not recognized
     */
    public String toString(LocalDateTime date, String pattern) throws IllegalArgumentException {
        String stringDate = null;  
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        stringDate = formatter.format(date);
        return stringDate;
    }
    
    /**
     * Format a String to a <code>LocalDateTime</code> according to a specified pattern
     *
     * @param dateString - a CharSequence that must follow ISO-8601 LocalDateTime format
     * For example: 2007-12-03T10:15:30
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html#ISO_LOCAL_DATE_TIME"></a>
     * @param pattern - a <code>DateTimeFormatter</code> date/time pattern 
     * For example:"MM/dd/yyyy hh:mm:ss"
     * @see <a href="http://joda-time.sourceforge.net/api-release/org/joda/time/format/DateTimeFormat.html"></a>
     * @return a date according to the specified pattern
     * @throws IllegalArgumentException if dateString is null or pattern not recognized
     */
    public LocalDateTime toDate(CharSequence dateString, String pattern) throws IllegalArgumentException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime date = LocalDateTime.parse(dateString, formatter);
        return date;
    }
    
     /**
     * Format a String to a <code>LocalDateTime</code> according to the default pattern
     *
     * @param dateString - a CharSequence that must follow ISO-8601 LocalDateTime format
     * For example: 2007-12-03T10:15:30
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html#ISO_LOCAL_DATE_TIME"></a>
     * @return a date according to the default pattern
     * @throws IllegalArgumentException if dateString is null or pattern not recognized
     */
    public LocalDateTime toDate(CharSequence dateString) throws IllegalArgumentException {
        LocalDateTime date = LocalDateTime.parse(dateString);
        return date;
    }
    
    /**
     * Calculate the difference, in DateUtilitities field units, for any two
     * <code>LocalDateTime</code> objects
     *
     * @param dateUnit - an enum representing a unit of measure in milliseconds
     * (e.g., a day is 1000L * 60L * 60L * 24L ms, etc.)
     * @param firstDate - a <code>LocalDateTime</code> object
     * @param secondDate - a <code>LocalDateTime</code> object
     * @return the difference in DateUtilities units as a positive whole number
     * @throws IllegalArgumentException if any argument is invalid
     */
    public int getDateDiff(LocalDateTime firstDate, LocalDateTime secondDate, DateUnit dateUnit)
            throws IllegalArgumentException {
        Duration diff = Duration.between(firstDate, secondDate);
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
    
    // main method
    public static void main(String[] args) throws ParseException, IllegalArgumentException {
        
        DateUtilities dateUtilities = DateUtilities.getInstance();        
        LocalDateTime firstDate = LocalDateTime.now();
        LocalDateTime secondDate = LocalDateTime.now().plusDays(5);
        
        String fDate = dateUtilities.toString(firstDate);
        String sDate = dateUtilities.toString(secondDate);
        System.out.println("Local Date Time to String without pattern: " + fDate);
        System.out.println("Local Date Time to String without pattern: " + sDate);
        
        String fDatePattern = dateUtilities.toString(firstDate, "M/d/yy h:mm:ss a");
        String sDatePattern = dateUtilities.toString(secondDate, "MM/dd/yyyy hh:mm:ss");
        System.out.println("Local Date Time to String with pattern: " + fDatePattern);
        System.out.println("Local Date Time to String with pattern: " + sDatePattern);
        
        LocalDateTime fStringPattern = dateUtilities.toDate("2011-12-03T10:15:30");
        //LocalDateTime sStringPattern = dateUtilities.toDate(fDatePattern, "MM/dd/yyyy hh:mm:ss");
        System.out.println("String to Local Date Time without pattern: " + fStringPattern);
       // System.out.println("String to Local Date Time with pattern: " + sStringPattern);
        
        int dayValue = dateUtilities.getDateDiff(firstDate, secondDate, DateUnit.DAY);
        System.out.println("Days between " + fDate + " and " + sDate + " is: " + dayValue);
        int hourValue = dateUtilities.getDateDiff(firstDate, secondDate, DateUnit.HOUR);
        System.out.println("Days between " + fDate + " and " + sDate + " is: " + hourValue);
        int minuteValue = dateUtilities.getDateDiff(firstDate, secondDate, DateUnit.MINUTE);
        System.out.println("Days between " + fDate + " and " + sDate + " is: " + minuteValue);
        int secondValue = dateUtilities.getDateDiff(firstDate, secondDate, DateUnit.SECOND);
        System.out.println("Days between " + fDate + " and " + sDate + " is: " + secondValue);
    }
}
