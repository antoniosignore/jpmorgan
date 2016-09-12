package com.jpmorgan.business;

import com.jpmorgan.beans.Daily;
import com.jpmorgan.beans.Trade;
import com.jpmorgan.enums.TradeEnum;
import com.jpmorgan.model.Stock;
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
public class TradeTimeSeriesTest {

    public static final String FIRST_DATE = "2016-01-02 08:00:00";
    public static final String LAST_DATE = "2016-01-02 08:20:00";

    GenericTimeSeries<Trade> timeSeries;
    Stock bud;

    @Before
    public void setUp() throws Exception {

        bud = new Stock("Buweiser", "BUD");
        bud.addDaily(new Daily.DailyBuilder(DateUtils.dateTime(FIRST_DATE), 6, 3, 4, 5, 1000).build());
        bud.addDaily(new Daily.DailyBuilder(DateUtils.dateTime("2016-01-02 08:05:00"), 7, 4, 5, 6, 1000).build());
        bud.addDaily(new Daily.DailyBuilder(DateUtils.dateTime("2016-01-02 08:10:00"), 6, 3, 4, 5, 1000).build());
        bud.addDaily(new Daily.DailyBuilder(DateUtils.dateTime("2016-01-02 08:15:00"), 7, 4, 5, 6, 1000).build());
        bud.addDaily(new Daily.DailyBuilder(DateUtils.dateTime(LAST_DATE), 8, 5, 6, 7, 1000).build());

        timeSeries = new GenericTimeSeries<Trade>();

        timeSeries.put(DateUtils.dateTime(FIRST_DATE),
                new Trade.TradeBuilder(bud).setAmount(1000).setCost(10D).setPrice(5D).setTradeAction(TradeEnum.BUY).setTradeDate(DateUtils.dateTime(FIRST_DATE)).build());

        timeSeries.put(DateUtils.dateTime("2016-01-02 08:05:00"),
                new Trade.TradeBuilder(bud).setAmount(1000).setCost(10D).setPrice(5D).setTradeAction(TradeEnum.BUY).setTradeDate(DateUtils.dateTime("2016-01-02 08:05:00")).build());

        timeSeries.put(DateUtils.dateTime("2016-01-02 08:10:00"),
                new Trade.TradeBuilder(bud).setAmount(1000).setCost(10D).setPrice(5D).setTradeAction(TradeEnum.BUY).setTradeDate(DateUtils.dateTime("2016-01-02 08:10:00")).build());


        timeSeries.put(DateUtils.dateTime("2016-01-02 08:15:00"),
                new Trade.TradeBuilder(bud).setAmount(1000).setCost(10D).setPrice(5D).setTradeAction(TradeEnum.BUY).setTradeDate(DateUtils.dateTime("2016-01-02 08:20:00")).build());

        timeSeries.put(DateUtils.dateTime(LAST_DATE),
                new Trade.TradeBuilder(bud).setAmount(1000).setCost(10D).setPrice(5D).setTradeAction(TradeEnum.BUY).setTradeDate(DateUtils.dateTime(LAST_DATE)).build());

    }


    @Test
    public void firstValue() throws Exception {

        Trade trade = timeSeries.firstValue();

        Assert.assertEquals(DateUtils.dateTime(FIRST_DATE), trade.getTradeDate());

        Assert.assertEquals(1000, trade.getAmount(), 0);
        Assert.assertEquals(10D, trade.getCost(), 0);
        Assert.assertEquals(bud, trade.getInstrument());
        Assert.assertEquals(5D, trade.getPrice(), 0);
        Assert.assertEquals(TradeEnum.BUY, trade.getTradeAction());

    }

    @Test
    public void nextDate() throws Exception {

        Trade first = timeSeries.firstValue();
        Trade trade = timeSeries.nextValue(first.getTradeDate());
        Assert.assertEquals(DateUtils.dateTime("2016-01-02 08:05:00"), trade.getTradeDate());

    }

    @Test
    public void prevDate() throws Exception {

        Trade first = timeSeries.firstValue();
        Trade trade = timeSeries.nextValue(first.getTradeDate());
        Date back = timeSeries.prevDate(trade.getTradeDate());
        Assert.assertEquals(DateUtils.dateTime(FIRST_DATE), back);

    }

    @Test
    public void nextValue() throws Exception {

        Trade first = timeSeries.firstValue();
        Trade trade = timeSeries.nextValue(first.getTradeDate());
        Assert.assertEquals(DateUtils.dateTime("2016-01-02 08:05:00"), trade.getTradeDate());

    }

    @Test
    public void prevValue() throws Exception {

        Trade first = timeSeries.firstValue();
        Trade trade = timeSeries.nextValue(first.getTradeDate());
        Trade backToFirst = timeSeries.prevValue(trade.getTradeDate());
        Assert.assertEquals(DateUtils.dateTime(FIRST_DATE), backToFirst.getTradeDate());

    }

    @Test
    public void lastValue() throws Exception {
        Trade last = timeSeries.lastValue();

        Assert.assertEquals(DateUtils.dateTime(LAST_DATE), last.getTradeDate());

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
        TreeMap<Date, Trade> map = timeSeries.map();
        Assert.assertEquals(5, map.size());
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

        Trade trade = timeSeries.valueByIndex(0);
        Assert.assertEquals(DateUtils.dateTime(FIRST_DATE), trade.getTradeDate());


    }

    @Test
    public void index() throws Exception {
        int index = timeSeries.index(DateUtils.dateTime(FIRST_DATE));
        Assert.assertEquals(index, 0);

    }


}