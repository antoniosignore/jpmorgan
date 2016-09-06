package com.jpmorgan.test;

import com.jpmorgan.beans.Daily;
import com.jpmorgan.model.Stock;
import com.jpmorgan.utils.DateUtils;
import jpmorgan.JpMorganTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JpMorganTest.class)
public class SuperSimpleStock {


/*
    a. For a given stock,
    i. calculate the dividend yield
    ii. calculate the P/E Ratio
    iii. record a trade, with timestamp, quantity of shares, buy or sell indicator and price
    iv. Calculate Stock Price based on trades recorded in past 15 minutes
    b. Calculate the GBCE All Share Index using the geometric mean of prices for all stocks
  */

    /*
    Dividend Yield : last dividend / ticker price

    P/E Ratio :

Geometric Mean

Stock Price


     */

    public static final String FIRST_DATE = "2016-01-02 08:00:00";
    public static final String LAST_DATE = "2016-01-02 08:20:00";

    Stock bud;

    @Before
    public void setUp() throws Exception {

        bud = new Stock("Budweiser", "BUD");

        bud.addDaily(new Daily.DailyBuilder(
                DateUtils.dateTime(FIRST_DATE),
                6, 3, 4, 5, 1000)
                .build());

        bud.addDaily(
                new Daily.DailyBuilder(
                        DateUtils.dateTime("2016-01-02 08:05:00"),
                        7, 4, 5, 6, 1000)
                        .build());

        bud.addDaily(
                new Daily.DailyBuilder(
                        DateUtils.dateTime("2016-01-02 08:10:00"),
                        6, 3, 4, 5, 1000)
                        .build());

        bud.addDaily(
                new Daily.DailyBuilder(
                        DateUtils.dateTime("2016-01-02 08:15:00"),
                        7, 4, 5, 6, 1000)
                        .build());

        bud.addDaily(
                new Daily.DailyBuilder(
                        DateUtils.dateTime(LAST_DATE),
                        8, 5, 6, 7, 1000)
                        .build());
    }

    @Test
    public void test() {

        TimeSeries closeSeries = bud.closeSeries();

        List<Double> closes = closeSeries.asList();

        System.out.println("closes.size() = " + closes.size());

        closes.forEach(it -> System.out.println("value = " + it));


        TimeSeries openSeries = bud.getOpenSeries();
        TimeSeries highSeries = bud.getHighSeries();
        TimeSeries lowSeries = bud.getLowSeries();

        TimeSeries volumeSeries = bud.getVolumeSeries();

    }


}
