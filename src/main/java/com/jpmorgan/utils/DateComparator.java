package com.jpmorgan.utils;


import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

public class DateComparator implements Comparator<Date>, Serializable {

    public int compare(Date date1, Date date2) {
        return date1.compareTo(date2);
    }
}