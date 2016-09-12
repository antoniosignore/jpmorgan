package com.jpmorgan.business;

import com.jpmorgan.utils.DateComparator;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class GenericTimeSeries<T> extends TreeMap<Date, T> {

    public GenericTimeSeries() {
        super(new DateComparator());
    }

    public T firstValue() {
        Date date = firstKey();
        return get(date);
    }

    public Date nextDate(Date date) {
        return higherKey(date);
    }

    public Date prevDate(Date date) {
        return lowerKey(date);
    }

    public T nextValue(Date date) {
        Map.Entry<Date, T> entry = higherEntry(date);
        if (entry != null) return entry.getValue();
        return null;
    }

    public T prevValue(Date date) {
        Map.Entry<Date, T> entry = lowerEntry(date);
        if (entry != null) return entry.getValue();
        return null;
    }

    public T lastValue() {
        Date key = lastKey();
        return get(key);
    }

    public Date firstDate() {
        return firstKey();
    }

    public Date lastDate() {
        return lastKey();
    }

    public TreeMap<Date, T> map() {
        return this;
    }

    int noElements(Date firstCalendarDate, Date lastCalendarDate) {
        return subMap(firstCalendarDate, true, lastCalendarDate, true).size();
    }

    public Date dateByIndex(int i) {
        Object[] array = keySet().toArray();
        return (Date) array[i];
    }

    public T valueByIndex(int i) {
        Object[] array = values().toArray();
        return (T) array[i];
    }

    public int index(Date date) {
        return headMap(date).size();
    }


}