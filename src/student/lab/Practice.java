/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.lab;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.TimeZone;

/**
 *
 * @author ritu
 */
public class Practice {
    
    public static void main(String[] args) throws IllegalArgumentException {
        // Find last business day numerical value
        //LocalDate date = LocalDate.now();
        //LocalDate lastBusDay = date.with(TemporalAdjusters.lastInMonth(DayOfWeek.weekday));
        
        // Find difference between two dates
       // LocalDateTime secondDate = LocalDateTime.now().plusDays(5);
       // Duration diff = Duration.between(date, secondDate);
//        double value = diff.toMinutes()%60;
//        
//        System.out.println(value);
//        
        //TimeZone tz = TimeZone.getTimeZone("Europe/Berlin");
        
       // LocalDateTime berlinDate = LocalDateTime.now(tz.toZoneId());
        
       // System.out.println("The date time in Berlin is : " + berlinDate);
       
//       LocalDate dueDate = date.plusDays(15);
//       System.out.println("Due date is : " + dueDate);
               
        // What date will the next leap year happen ?
        LocalDate date2 = null;
        int count =0;
        
        while(date2 == null){
            LocalDate date = LocalDate.now();
            count++;
            date = date.plusYears(count);
            if (date.isLeapYear()){
                date2 = date;
                break;
            }
        }
        System.out.println("next leap year will be on: " + date2);
        
    }   
}
