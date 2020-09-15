package com.twu.refactoring;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class DateParser {
    private final String dateAndTimeString;
    private static final HashMap<String, TimeZone> KNOWN_TIME_ZONES = new HashMap<String, TimeZone>();
    final String timeZone="UTC";
    static {
        KNOWN_TIME_ZONES.put(timeZone, TimeZone.getTimeZone(timeZone));
    }

    /**
     * Takes a date in ISO 8601 format and returns a date
     *
     * @param dateAndTimeString - should be in format ISO 8601 format
     *                          examples -
     *                          2012-06-17 is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17TZ is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17T15:00Z is 17th June 2012 - 15:00 in UTC TimeZone
     */
    public DateParser(String dateAndTimeString) {
        this.dateAndTimeString = dateAndTimeString;
    }

    public Date parse() {
        int year, month, date, hour, minute;

        year = getTime(year,0,4,2012,2000);

        month = getTime(month,5,7,1,2);

        date = getTime(date,8,10,1,31);

        if (dateAndTimeString.substring(11, 12).equals("Z")) {
            hour = 0;
            minute = 0;
        } else {
            hour = getTime(hour,11,13,0,23);

            minute = getTime(minute,14,16,0,59);

        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(year, month - 1, date, hour, minute, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    private int getTime(int data,int substring1 ,int substring2,int min,int max) throws IllegalArgumentException {

        try {
            String yearString = dateAndTimeString.substring(substring1, substring2);
            data = Integer.parseInt(yearString);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(data+"string is less than " +(substring2-substring1)+ "characters");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(data+"is not an integer");
        }
        if (data < min || data > max)
            throw new IllegalArgumentException(data +"cannot be less than"+ min+ "or more than" + max);
        return data;
    }
}
