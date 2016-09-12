package com.jpmorgan.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

public class GenericMatrix<T> {

    private List<GenericTimeSeries<T>> listOfTimeSeries;

    public GenericMatrix() {
        listOfTimeSeries = new ArrayList<>();
    }

    public TreeMap<Date, T> map(int row) {
        GenericTimeSeries<T> gst = listOfTimeSeries.get(row);
        return gst.map();
    }

    public T get(int row, Date date) {
        return listOfTimeSeries.get(0).get(date);
    }

    public void put(int row, Date date, T value) {
        try {
            listOfTimeSeries.get(row);
        } catch (IndexOutOfBoundsException ex) {
            listOfTimeSeries.add(row, new GenericTimeSeries<T>());
        }
        listOfTimeSeries.get(row).put(date, value);
    }

    public boolean isEmpty(int row, Date date) {
        try {
            if (listOfTimeSeries.get(row).get(date) != null) return false;
        } catch (Throwable th) {
            return true;
        }
        return true;
    }

    public boolean isEmpty(int row) {
        try {
            if (listOfTimeSeries.get(row) == null || listOfTimeSeries.size() == 0)
                return true;
        } catch (Throwable th) {
            return true;
        }
        return true;
    }

    public T lastValidData(int row) {
        GenericTimeSeries<T> get = listOfTimeSeries.get(row);
        return get.lastValue();
    }

    public boolean isValidRow(int row) {
        if (row < 0 || row >= listOfTimeSeries.size()) return false;
        else return true;
    }

    public int size(int row) {
        GenericTimeSeries<T> get = listOfTimeSeries.get(row);
        return get.size();
    }

    public Date firstDate(int row) {
        try {
            GenericTimeSeries<T> gst = listOfTimeSeries.get(row);
            Date date = gst.firstDate();
            return date;
        } catch (Throwable rx) {
            return null;
        }
    }

    public Date nextDate(int row, Date date) {
        try {
            GenericTimeSeries<T> gst = listOfTimeSeries.get(row);
            return gst.nextDate(date);
        } catch (Throwable rx) {
            return null;
        }
    }

    public Date prevDate(int row, Date date) {
        try {
            GenericTimeSeries<T> gst = listOfTimeSeries.get(row);
            return gst.prevDate(date);
        } catch (Throwable rx) {
            return null;
        }
    }

    public Date lastDate(int row) {
        try {
            GenericTimeSeries<T> gst = listOfTimeSeries.get(row);
            return gst.lastDate();
        } catch (Throwable rx) {
            return null;
        }
    }

    public int noElements(int row, Date firstCalendarDate, Date lastCalendarDate) {
        try {
            if (firstCalendarDate == null || lastCalendarDate == null) return 0;
            GenericTimeSeries<T> gst = listOfTimeSeries.get(row);
            return gst.noElements(firstCalendarDate, lastCalendarDate);
        } catch (Throwable rx) {
            return 0;
        }
    }

    public int noElements(int row) {
        GenericTimeSeries<T> gst = listOfTimeSeries.get(row);
        return gst.noElements(firstDate(row), lastDate(row));
    }

    public int getNRows() {
        return listOfTimeSeries.size();
    }

    public Date dateByIndex(int i) {
        GenericTimeSeries<T> gst = listOfTimeSeries.get(0);
        return gst.dateByIndex(i);
    }

    public T value(int i) {
        GenericTimeSeries<T> gst = listOfTimeSeries.get(0);
        return gst.valueByIndex(i);
    }

    public int index(Date date) {
        GenericTimeSeries<T> gst = listOfTimeSeries.get(0);
        return gst.index(date);
    }
}
