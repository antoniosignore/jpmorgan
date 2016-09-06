package com.jpmorgan.model;

import com.jpmorgan.enums.StockTypeEnum;

import java.io.Serializable;

public class Stock extends Asset implements Serializable {

    private String ticker;
    private StockTypeEnum stockType = StockTypeEnum.COMMON;
    private double lastDividend = 0.0;
    private double fixedDividend = 0.0;
    private double parValue = 0.0;

    public Stock(String name, String ticker) {
        super(name);
        this.ticker = ticker;
    }

    private Stock(StockBuilder builder) {
        super(builder.name);
        this.ticker = builder.ticker;
        this.stockType = builder.stockType;
        this.lastDividend = builder.lastDividend;
        this.fixedDividend = builder.fixedDividend;
        this.parValue = builder.parValue;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public StockTypeEnum getStockType() {
        return stockType;
    }

    public void setStockType(StockTypeEnum stockType) {
        this.stockType = stockType;
    }

    public double getLastDividend() {
        return lastDividend;
    }

    public void setLastDividend(double lastDividend) {
        this.lastDividend = lastDividend;
    }

    public double getFixedDividend() {
        return fixedDividend;
    }

    public void setFixedDividend(double fixedDividend) {
        this.fixedDividend = fixedDividend;
    }

    public double getParValue() {
        return parValue;
    }

    public void setParValue(double parValue) {
        this.parValue = parValue;
    }

    public double getDividendYield() {

        double dividendYield = -1.0;

        if (getLast() > 0.0) {
            if (stockType == StockTypeEnum.COMMON) {
                dividendYield = lastDividend / getLast();
            } else {
                // PREFERRED
                dividendYield = (fixedDividend * parValue) / getLast();
            }
        }
        return dividendYield;
    }

    public double getPeRatio() {
        double peRatio = -1.0;
        if (getLast() > 0.0) peRatio = getLast() / getDividendYield();
        return peRatio;
    }

    public static class StockBuilder {
        private String name;
        private String ticker;
        private StockTypeEnum stockType = StockTypeEnum.COMMON;
        private double lastDividend = 0.0;
        private double fixedDividend = 0.0;
        private double parValue = 0.0;

        public StockBuilder(String name, String ticker) {
            this.ticker = ticker;
            this.name = name;
        }

        public StockBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public StockBuilder setStockType(StockTypeEnum stockType) {
            this.stockType = stockType;
            return this;
        }

        public StockBuilder setLastDividend(double lastDividend) {
            this.lastDividend = lastDividend;
            return this;
        }

        public StockBuilder setFixedDividend(double fixedDividend) {
            this.fixedDividend = fixedDividend;
            return this;
        }

        public StockBuilder setParValue(double parValue) {
            this.parValue = parValue;
            return this;
        }

        public Stock build() {
            return new Stock(this);
        }
    }
}
