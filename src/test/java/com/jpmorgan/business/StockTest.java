package com.jpmorgan.business;

import com.jpmorgan.beans.Daily;
import com.jpmorgan.beans.Trade;
import com.jpmorgan.enums.StockTypeEnum;
import com.jpmorgan.enums.TradeEnum;
import com.jpmorgan.model.Portfolio;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JpMorganTest.class)
public class StockTest {

    public static final String FIRST_DATE = "2016-01-02 08:00:00";
    public static final String LAST_DATE = "2016-01-02 08:20:00";

    PortfolioService portfolioService = new PortfolioService();

    Stock TEA;
    Stock POP;
    Stock ALE;
    Stock GIN;
    Stock JOE;

    Portfolio portfolio;

    @Before
    public void setUp() throws Exception {

        TEA = new Stock.StockBuilder("Tea", "TEA")
                .setStockType(StockTypeEnum.COMMON)
                .setLastDividend(0)
                .setParValue(100)
                .build();

        POP = new Stock.StockBuilder("Pop", "POP")
                .setStockType(StockTypeEnum.COMMON)
                .setLastDividend(8)
                .setParValue(100)
                .build();

        ALE = new Stock.StockBuilder("Ale", "ALE")
                .setStockType(StockTypeEnum.COMMON)
                .setLastDividend(23)
                .setParValue(60)
                .build();

        GIN = new Stock.StockBuilder("Ginger", "GIN")
                .setStockType(StockTypeEnum.PREFERRED)
                .setLastDividend(8)
                .setFixedDividend(0.02D)
                .setParValue(100)
                .build();

        JOE = new Stock.StockBuilder("Joe", "JOE")
                .setStockType(StockTypeEnum.COMMON)
                .setLastDividend(13)
                .setParValue(250)
                .build();


        Date date5 = DateUtils.now();
        Date date4 = DateUtils.subtractMinutes(date5, 5);
        Date date3 = DateUtils.subtractMinutes(date4, 5);
        Date date2 = DateUtils.subtractMinutes(date3, 5);
        Date date1 = DateUtils.subtractMinutes(date2, 5);


        TEA.addDaily(new Daily.DailyBuilder(date1, 6, 3, 4, 5, 1000).build());
        TEA.addDaily(new Daily.DailyBuilder(date2, 7, 4, 5, 6, 1000).build());
        TEA.addDaily(new Daily.DailyBuilder(date3, 6, 3, 4, 5, 1000).build());
        TEA.addDaily(new Daily.DailyBuilder(date4, 7, 4, 5, 6, 1000).build());
        TEA.addDaily(new Daily.DailyBuilder(date5, 8, 5, 6, 7, 1000).build());

        POP.addDaily(new Daily.DailyBuilder(date1, 6, 3, 4, 5, 1000).build());
        POP.addDaily(new Daily.DailyBuilder(date2, 7, 4, 5, 6, 1000).build());
        POP.addDaily(new Daily.DailyBuilder(date3, 6, 3, 4, 5, 1000).build());
        POP.addDaily(new Daily.DailyBuilder(date4, 7, 4, 5, 6, 1000).build());
        POP.addDaily(new Daily.DailyBuilder(date5, 8, 5, 6, 7, 1000).build());

        ALE.addDaily(new Daily.DailyBuilder(date1, 6, 3, 4, 5, 1000).build());
        ALE.addDaily(new Daily.DailyBuilder(date2, 7, 4, 5, 6, 1000).build());
        ALE.addDaily(new Daily.DailyBuilder(date3, 6, 3, 4, 5, 1000).build());
        ALE.addDaily(new Daily.DailyBuilder(date4, 7, 4, 5, 6, 1000).build());
        ALE.addDaily(new Daily.DailyBuilder(date5, 8, 5, 6, 7, 1000).build());

        GIN.addDaily(new Daily.DailyBuilder(date1, 6, 3, 4, 5, 1000).build());
        GIN.addDaily(new Daily.DailyBuilder(date2, 7, 4, 5, 6, 1000).build());
        GIN.addDaily(new Daily.DailyBuilder(date3, 6, 3, 4, 5, 1000).build());
        GIN.addDaily(new Daily.DailyBuilder(date4, 7, 4, 5, 6, 1000).build());
        GIN.addDaily(new Daily.DailyBuilder(date5, 8, 5, 6, 7, 1000).build());


        JOE.addDaily(new Daily.DailyBuilder(date1, 6, 3, 4, 5, 1000).build());
        JOE.addDaily(new Daily.DailyBuilder(date2, 7, 4, 5, 6, 1000).build());
        JOE.addDaily(new Daily.DailyBuilder(date3, 6, 3, 4, 5, 1000).build());
        JOE.addDaily(new Daily.DailyBuilder(date4, 7, 4, 5, 6, 1000).build());
        JOE.addDaily(new Daily.DailyBuilder(date5, 8, 5, 6, 7, 1000).build());

        portfolio = new Portfolio("Super simple");


        portfolioService.add(portfolio, new Trade.TradeBuilder(TEA)
                .setAmount(1000)
                .setCost(10D)
                .setPrice(5D)
                .setTradeAction(TradeEnum.BUY)
                .setTradeDate(date1)
                .build());


        portfolioService.add(portfolio, new Trade.TradeBuilder(POP)
                .setAmount(1000)
                .setCost(10D)
                .setPrice(5D)
                .setTradeAction(TradeEnum.BUY)
                .setTradeDate(date2)
                .build());

        portfolioService.add(portfolio, new Trade.TradeBuilder(JOE)
                .setAmount(1000)
                .setCost(10D)
                .setPrice(5D)
                .setTradeAction(TradeEnum.BUY)
                .setTradeDate(date3)
                .build());

        portfolioService.add(portfolio, new Trade.TradeBuilder(ALE)
                .setAmount(1000)
                .setCost(10D)
                .setPrice(5D)
                .setTradeAction(TradeEnum.BUY)
                .setTradeDate(date4)
                .build());

        portfolioService.add(portfolio, new Trade.TradeBuilder(GIN)
                .setAmount(1000)
                .setCost(10D)
                .setPrice(5D)
                .setTradeAction(TradeEnum.BUY)
                .setTradeDate(date5)
                .build());

        portfolio.getTrades().forEach(trade -> {
                    System.out.println("trade = " + trade);

                }
        );

        Assert.assertEquals("Tea", portfolio.getItems().get(0).getInstrument().getName());
        Assert.assertEquals("Pop", portfolio.getItems().get(1).getInstrument().getName());
        Assert.assertEquals("Joe", portfolio.getItems().get(2).getInstrument().getName());
        Assert.assertEquals("Ale", portfolio.getItems().get(3).getInstrument().getName());
        Assert.assertEquals("Ginger", portfolio.getItems().get(4).getInstrument().getName());

        Assert.assertEquals(5, portfolio.getItems().size());
        portfolio.getItems().forEach(it -> {
                    Stock stock = (Stock) it.getInstrument();
                    System.out.println("stock.getTicker() = " + stock.getTicker());
                    System.out.println(stock.getDividendYield());
                    System.out.println(stock.getPeRatio());
                }
        );
    }

    @Test
    public void testSimpleStock() {

        //    i. calculate the dividend yield
        Assert.assertEquals(0.0D, portfolio.getItems().get(0).getInstrument().getDividendYield(), 0);
        Assert.assertEquals(1.1428571428571428D, portfolio.getItems().get(1).getInstrument().getDividendYield(), 0);
        Assert.assertEquals(1.8571428571428572, portfolio.getItems().get(2).getInstrument().getDividendYield(), 0);
        Assert.assertEquals(3.2857142857142856, portfolio.getItems().get(3).getInstrument().getDividendYield(), 0);
        Assert.assertEquals(0.2857142857142857, portfolio.getItems().get(4).getInstrument().getDividendYield(), 0);

        //    ii. calculate the P/E Ratio
        Assert.assertEquals(0.0D, portfolio.getItems().get(0).getInstrument().getDividendYield(), 0);
        Assert.assertEquals(6.125, portfolio.getItems().get(1).getInstrument().getPeRatio(), 0);
        Assert.assertEquals(3.769230769230769, portfolio.getItems().get(2).getInstrument().getPeRatio(), 0);
        Assert.assertEquals(2.130434782608696, portfolio.getItems().get(3).getInstrument().getPeRatio(), 0);
        Assert.assertEquals(24.5, portfolio.getItems().get(4).getInstrument().getPeRatio(), 0);

        // iii. record a trade, with timestamp, quantity of shares, buy or sell indicator and price
        Assert.assertEquals(5, portfolio.getTrades().size());


        // iv. Calculate Stock Price based on trades recorded in past 15 minutes
        // we expect the same value being ony 1 rhe trade that should get into the computation
        Assert.assertEquals(5.0, portfolio.calculateStockPrice(TEA), 0);
        Assert.assertEquals(5.0, portfolio.calculateStockPrice(GIN), 0);
        Assert.assertEquals(5.0, portfolio.calculateStockPrice(ALE), 0);
        Assert.assertEquals(5.0, portfolio.calculateStockPrice(JOE), 0);
        Assert.assertEquals(5.0, portfolio.calculateStockPrice(POP), 0);

        // b. Calculate the GBCE All Share Index using the geometric mean of prices for all stocks
        Assert.assertEquals(5, portfolio.calculateAllShareIndex(), 0.0001);

    }


}