package com.jpmorgan.business;

import com.jpmorgan.beans.Daily;
import com.jpmorgan.utils.DateUtils;
import jpmorgan.JpMorganTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.TreeMap;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JpMorganTest.class)
public class DailyTimeSeriesTest {

    public static final String FIRST_DATE = "2016-01-02 08:00:00";
    public static final String LAST_DATE = "2016-01-02 08:20:00";
    GenericTimeSeries<Daily> timeSeries;

    @Before
    public void setUp() throws Exception {

        timeSeries = new GenericTimeSeries<Daily>();

        timeSeries.put(DateUtils.dateTime(FIRST_DATE),
                new Daily.DailyBuilder(
                        DateUtils.dateTime(FIRST_DATE),
                        6, 3, 4, 5, 1000)
                        .build());

        timeSeries.put(DateUtils.dateTime("2016-01-02 08:05:00"),
                new Daily.DailyBuilder(
                        DateUtils.dateTime("2016-01-02 08:05:00"),
                        7, 4, 5, 6, 1000)
                        .build());

        timeSeries.put(DateUtils.dateTime("2016-01-02 08:10:00"),
                new Daily.DailyBuilder(
                        DateUtils.dateTime("2016-01-02 08:10:00"),
                        6, 3, 4, 5, 1000)
                        .build());

        timeSeries.put(DateUtils.dateTime("2016-01-02 08:15:00"),
                new Daily.DailyBuilder(
                        DateUtils.dateTime("2016-01-02 08:15:00"),
                        7, 4, 5, 6, 1000)
                        .build());

        timeSeries.put(DateUtils.dateTime(LAST_DATE),
                new Daily.DailyBuilder(
                        DateUtils.dateTime(LAST_DATE),
                        8, 5, 6, 7, 1000)
                        .build());
    }

    @Test
    public void firstValue() throws Exception {

        Daily daily = timeSeries.firstValue();

        Assert.assertEquals(DateUtils.dateTime(FIRST_DATE), daily.getDailydate());

        Assert.assertEquals(6, daily.getHigh(), 0);
        Assert.assertEquals(3, daily.getLow(), 0);
        Assert.assertEquals(4, daily.getOpenprice(), 0);
        Assert.assertEquals(5, daily.getCloseprice(), 0);
        Assert.assertEquals(1000, daily.getVolume(), 0);

    }

    @Test
    public void nextDate() throws Exception {

        Daily first = timeSeries.firstValue();
        Daily daily = timeSeries.nextValue(first.getDailydate());
        Assert.assertEquals(DateUtils.dateTime("2016-01-02 08:05:00"), daily.getDailydate());

    }

    @Test
    public void prevDate() throws Exception {

        Daily first = timeSeries.firstValue();
        Daily daily = timeSeries.nextValue(first.getDailydate());
        Date back = timeSeries.prevDate(daily.getDailydate());
        Assert.assertEquals(DateUtils.dateTime(FIRST_DATE), back);

    }

    @Test
    public void nextValue() throws Exception {

        Daily first = timeSeries.firstValue();
        Daily daily = timeSeries.nextValue(first.getDailydate());
        Assert.assertEquals(DateUtils.dateTime("2016-01-02 08:05:00"), daily.getDailydate());

    }

    @Test
    public void prevValue() throws Exception {

        Daily first = timeSeries.firstValue();
        Daily daily = timeSeries.nextValue(first.getDailydate());
        Daily backToFirst = timeSeries.prevValue(daily.getDailydate());
        Assert.assertEquals(DateUtils.dateTime(FIRST_DATE), backToFirst.getDailydate());

    }

    @Test
    public void lastValue() throws Exception {
        Daily last = timeSeries.lastValue();

        Assert.assertEquals(DateUtils.dateTime(LAST_DATE), last.getDailydate());

    }

    @Test
    public void firstDate() throws Exception {
        Date first = timeSeries.firstDate();
        Assert.assertEquals(DateUtils.dateTime(FIRST_DATE), first);
    }

    @Test
    public void lastDate() throws Exception {
        Date last = timeSeries.lastDate();
        Assert.assertEquals(DateUtils.dateTime(LAST_DATE), last);
    }

    @Test
    public void map() throws Exception {
        TreeMap<Date, Daily> map = timeSeries.map();
        Assert.assertEquals("{Sat Jan 02 08:00:00 CET 2016=Daily{dailydate=Sat Jan 02 08:00:00 CET 2016, volume=1000, high=6.0, low=3.0, openprice=4.0, closeprice=5.0, openInterest=0}, Sat Jan 02 08:05:00 CET 2016=Daily{dailydate=Sat Jan 02 08:05:00 CET 2016, volume=1000, high=7.0, low=4.0, openprice=5.0, closeprice=6.0, openInterest=0}, Sat Jan 02 08:10:00 CET 2016=Daily{dailydate=Sat Jan 02 08:10:00 CET 2016, volume=1000, high=6.0, low=3.0, openprice=4.0, closeprice=5.0, openInterest=0}, Sat Jan 02 08:15:00 CET 2016=Daily{dailydate=Sat Jan 02 08:15:00 CET 2016, volume=1000, high=7.0, low=4.0, openprice=5.0, closeprice=6.0, openInterest=0}, Sat Jan 02 08:20:00 CET 2016=Daily{dailydate=Sat Jan 02 08:20:00 CET 2016, volume=1000, high=8.0, low=5.0, openprice=6.0, closeprice=7.0, openInterest=0}}", map.toString());
    }

    @Test
    public void noElements() throws Exception {
        int no = timeSeries.noElements(DateUtils.dateTime(FIRST_DATE),
                DateUtils.dateTime(LAST_DATE));

        Assert.assertEquals(5, no);
    }

    @Test
    public void dateByIndex() throws Exception {

        Date date = timeSeries.dateByIndex(0);
        Assert.assertEquals(DateUtils.dateTime(FIRST_DATE), date);

        Date last = timeSeries.dateByIndex(timeSeries.size() - 1);
        Assert.assertEquals(DateUtils.dateTime(LAST_DATE), last);

    }

    @Test
    public void valueByIndex() throws Exception {

        Daily daily = timeSeries.valueByIndex(0);
        Assert.assertEquals(DateUtils.dateTime(FIRST_DATE), daily.getDailydate());

        Assert.assertEquals(6, daily.getHigh(), 0);
        Assert.assertEquals(3, daily.getLow(), 0);
        Assert.assertEquals(4, daily.getOpenprice(), 0);
        Assert.assertEquals(5, daily.getCloseprice(), 0);
        Assert.assertEquals(1000, daily.getVolume(), 0);

    }

    @Test
    public void index() throws Exception {
        int index = timeSeries.index(DateUtils.dateTime(FIRST_DATE));
        Assert.assertEquals(index, 0);

    }

}