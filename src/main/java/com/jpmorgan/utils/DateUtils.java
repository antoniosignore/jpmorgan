package com.jpmorgan.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;

public class DateUtils {

    static SimpleDateFormat yyyyMMddHHmmss2 = new SimpleDateFormat("MM/dd/yyyy");
    private static Date day;

    public static Date Date(String date) {
        Date s = null;
        try {
            yyyyMMddHHmmss2.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
            s = yyyyMMddHHmmss2.parse(date);
            return cleanse(s);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setToday(Date tempToday) {
        day = tempToday;
    }

    public static Date max(Date date1, Date date2) {
        if (date1 == null) {
            return date2;
        }
        if (date2 == null) {
            return date1;
        }
        if (isGreater(date1, date2)) {
            return date1;
        } else {
            return date2;
        }
    }

    public static Date min(Date date1, Date date2) {
        if (date1 == null) {
            return date2;
        }
        if (date2 == null) {
            return date1;
        }
        if (isLess(date1, date2)) {
            return date1;
        } else {
            return date2;
        }
    }

    public static Date dateTime(String value) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
    }

    public static Date cleanse(Date day) {
        if (day == null)
            return null;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(day);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.AM_PM, Calendar.AM);
        return cal.getTime();
    }

    public static boolean isLess(Date d1, Date d) {
        if (d1.compareTo(d) < 0) return true;
        return false;
    }

    public static boolean isGreater(Date d1, Date d) {
        if (d1.compareTo(d) > 0) return true;
        return false;
    }

    public static boolean isLessEqual(Date d1, Date d) {

        if (d1 == null)
            throw new IllegalArgumentException("The date must not be null");

        if (d == null)
            throw new IllegalArgumentException("The date must not be null");

        if (d1.compareTo(d) <= 0) return true;
        return false;
    }

    public static boolean isGreaterEqual(Date d1, Date d) {
        if (d1.compareTo(d) >= 0) return true;
        return false;
    }

    private static Date add(Date date, int calendarField, int amount) {
        if (date == null) throw new IllegalArgumentException("The date must not be null");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    public static Date addDays(Date date, int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    public static Date subtractDays(Date date, int index) {
        return add(date, Calendar.DAY_OF_MONTH, -index);

    }

    public static Date subtractMinutes(Date now, int minutesRange) {
        Calendar dateRange = null;
        dateRange = Calendar.getInstance();
        dateRange.add(Calendar.MINUTE, -minutesRange);
        return dateRange.getTime();

    }

    public static Date now() {
        return new Date(System.currentTimeMillis());

    }

    public Date prevDay(Date date) {
        return addDays(date, -1);
    }
}
