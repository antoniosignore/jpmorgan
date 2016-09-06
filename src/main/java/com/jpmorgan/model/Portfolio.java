package com.jpmorgan.model;

import com.jpmorgan.beans.Trade;
import com.jpmorgan.test.StockPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Portfolio extends Asset implements Serializable {

    private Logger logger = Logger.getLogger(Portfolio.class);

    private String description;
    private double wealth = 0;
    private Date firstDate;
    private Date lastDate;

    private List<PortfolioEntry> items = new ArrayList<>();
    private List<Trade> trades = new ArrayList<>();

    public Portfolio(String name) {
        super(name);
    }

    public List<PortfolioEntry> getItems() {
        return items;
    }

    public void setItems(List<PortfolioEntry> items) {
        this.items = items;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWealth() {
        return wealth;
    }

    public void setWealth(double wealth) {
        this.wealth = wealth;
    }

    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public List<Trade> getTrades() {
        return trades;
    }

    public void setTrades(List<Trade> trades) {
        this.trades = trades;
    }

    public double calculateAllShareIndex() {
        double allShareIndex = 0.0;

        ArrayList<Double> stockprices = new ArrayList<Double>();
        for (PortfolioEntry entry : items) {

            Stock instrument = (Stock) entry.getInstrument();

            double stockPrice1 = 0.0;
            logger.debug("Trades in the original collection: " + trades.size());

            Collection<Trade> filteredTrades = CollectionUtils.select(getTrades(), new StockPredicate(instrument, 15));

            logger.debug("Trades after filtering: " + filteredTrades.size());

            double totNo = 0.0;
            double tradeValue = 0.0;

            for (Trade trade : filteredTrades) {
                tradeValue += (trade.getPrice() * trade.getAmount());
                totNo += trade.getAmount();
            }

            if (totNo > 0.0) {
                stockPrice1 = tradeValue / totNo;
            }
            double stockPrice = stockPrice1;
            if (stockPrice > 0) {
                stockprices.add(stockPrice);
            }
        }

        if (stockprices.size() >= 1) {
            double[] stockPricesArray = new double[stockprices.size()];
            for (int i = 0; i <= (stockprices.size() - 1); i++) {
                stockPricesArray[i] = stockprices.get(i);
            }
            allShareIndex = StatUtils.geometricMean(stockPricesArray);
        }
        return allShareIndex;
    }

    private double calculateStockPriceinRange(Stock stock, int minutesRange) throws Exception {
        double stockPrice = 0.0;

        logger.debug("Trades in the original collection: " + getTrades().size());


        @SuppressWarnings("unchecked")
        Collection<Trade> trades = CollectionUtils.select(getTrades(), new StockPredicate(stock, minutesRange));

        logger.debug("Trades in the filtered collection by [" + stock.getTicker() + "," + minutesRange + "]: " + trades.size());

        // Calculate the summation
        double shareQuantityAcum = 0.0;
        double tradePriceAcum = 0.0;
        for (Trade trade : trades) {
            // Calculate the summation of Trade Price x Quantity
            tradePriceAcum += (trade.getPrice() * trade.getAmount());
            // Acumulate Quantity
            shareQuantityAcum += trade.getAmount();
        }

        // calculate the stock price
        if (shareQuantityAcum > 0.0) {
            stockPrice = tradePriceAcum / shareQuantityAcum;
        }


        return stockPrice;
    }


    /*
     * (non-Javadoc)
     * @see com.jpmorgan.stocks.simple.services.SimpleStockService#calculateStockPrice()
     */
    public double calculateStockPrice(Stock stock) {
        double stockPrice = 0.0;

        try {
            logger.debug("Calculating Stock Price for the stock symbol: " + stock);

            // If the stock is not supported the a exception is raised
            if (stock == null) {
                throw new Exception("The stock symbol [" + stock.getName() + "] is not supported by the Super Simple Stock system.");
            }

            stockPrice = calculateStockPriceinRange(stock, 15);

            logger.debug(" Stock Price calculated: " + stockPrice);


        } catch (Exception exception) {
            logger.error("Error calculating P/E Ratio for the stock symbol: " + stock + ".", exception);
            throw new RuntimeException("Error calculating P/E Ratio for the stock symbol: " + stock + ".", exception);

        }


        return stockPrice;
    }

}
