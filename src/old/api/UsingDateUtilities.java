package old.api;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import utility.DateUtilities;

public class UsingDateUtilities {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
        
        // Current date and time
        Date date = new Date();
        System.out.println("Current date/time with built-in Date class format: " + date);
        
        // Now format it with specification in SimpleDateFormat constructor
        String fmtDate = sdf.format(date);
        System.out.println("Current date/time with custom format: " + fmtDate);
        
        // Now format it with the DateUtilities class
        DateUtilities dateUtil = DateUtilities.getInstance();
        fmtDate = dateUtil.toString(date, "M/d/yy");
        System.out.println("Current date/time from Date object with format provided by DateUtilities class: " + fmtDate);
        
        // Current current date and time using the Calendar class, which has more
        // information from a global perspective (preferred)
        Calendar calDate = Calendar.getInstance();
        System.out.println("Current date/time with built-in Calendar class format: " + calDate);     
        
        // Now format it with specification in SimpleDateFormat constructor
        // However, you cannot fomrat a Calendar object directly. You must first
        // convert it to a date object
        Date dateFromCalendar = calDate.getTime();
        fmtDate = sdf.format(dateFromCalendar);
        System.out.println("Current date/time from Calendar with custom format: " + fmtDate);
        
        // Now format it with the DateUtilities class
        fmtDate = dateUtil.toString(calDate, "EEEE, M/d/yyyy");
        System.out.println("Current date/time from Calendar object with format provided by DateUtilities class: " + fmtDate);
        
        // Now turn dates into Calendar objects
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(date);
        
        // Now determine the difference between two dates using the DateUtilities class
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 2);
        String strStartDate = dateUtil.toString(startDate, "M/d/yyyy");
        String strEndDate = dateUtil.toString(endDate, "M/d/yyyy");
        int daysDiff = dateUtil.dateDiff(DateUtilities.DateUnit.DAY, startDate, endDate);
        System.out.println("Difference in days between " + strStartDate + " and "
                    + strEndDate + " is: " + daysDiff);
        
        // There's much more that you can do ... please experiment on your own!
    }
}
